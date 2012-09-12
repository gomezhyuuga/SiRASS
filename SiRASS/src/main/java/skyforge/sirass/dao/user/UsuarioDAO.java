/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.user;

import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class UsuarioDAO extends DAO {

    /**
     * Registra un usuario en su respectiva tabla en la BD
     *
     * @param usuario : Usuario - El usuario que desea registrarse
     * @return status : int - Devuelve 1 si se agregó, 0 si hubo un error, 1062
     * si el usuario ya existe.
     */
    public int insert(Usuario usuario) {
        return super.insert(usuario);
    }
    
    /**
     * Comprueba si un usuario determinado existe
     * @param username - El nombre de usuario a comprobar
     * @return true si existe, false si no existe
     */
    public boolean exists(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        // Comprobar si el usuario existe
        Usuario usuario = (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("usuario", username))
                .setFetchMode("prestador", FetchMode.SELECT)
                .setFetchMode("institucion", FetchMode.SELECT)
                .setFetchMode("administrador", FetchMode.SELECT)
                .setFetchMode("roles", FetchMode.SELECT)
                .setCacheable(true)
                .uniqueResult();
        transaction.commit();
        session.close();
        if (usuario != null) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Devuelve un usuario
     * @param username - El nombre de usuario a obtener
     * @return  - El usuario junto con sus objetos prestador, institucion o admin
     */
    public Usuario getByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        // Comprobar si el usuario existe
        Usuario usuario = (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("usuario", username))
                .setFetchMode("prestador", FetchMode.SELECT)
                .setFetchMode("institucion", FetchMode.JOIN)
                .setFetchMode("administrador", FetchMode.SELECT)
                .setFetchMode("roles", FetchMode.SELECT)
                .uniqueResult();
        transaction.commit();
        session.close();
        if (usuario != null) {
            System.out.println("El usuario existe!");
            return usuario;
        } else {
            System.out.println("El usuario no existe!");
            return null;
        }
    }
    
    /**
     * Obtener lista de usuarios en el sistema (fetchMode para usuarios desactivado)
     * 
     * @return Lista de usuarios
     */
    public List<Usuario> getAllJoined() {
        return this.list(null, FetchMode.JOIN);
    }
    
    public List<Usuario> getAll() {
        return super.getAll(Usuario.class);
    }
    
    public int deleteByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuario usuario = (Usuario) session.load(Usuario.class, username);
//            Query q = session.createQuery("delete from Usuario where usuario = :username");
//            q.setString("username", username);
//            int rows = q.executeUpdate();
            session.delete(usuario);
            transaction.commit();
            return 1;
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("### Error borrando objeto ###");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }
    
    private List<Usuario> list(Criterion[] crits, FetchMode fetchMode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> users = null;
        try {
            Criteria criteria = session.createCriteria(Usuario.class);
            criteria
                    .setFetchMode("prestador", fetchMode)
                    .setFetchMode("institucion", fetchMode)
                    .setFetchMode("administrador", fetchMode)
                    .setFetchMode("roles", fetchMode);
            // Agregar restricciones
            if (crits != null) {
                for (Criterion crit : crits) {
                    criteria.add(crit);
                }
            }
            users = criteria.list();
        } catch (ConstraintViolationException e) {
            System.out.println("#### --- ####");
            System.out.println("Error haciendo query. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return users;
    }
    
        /**
        * Obtiene el ID de un prestador a paritr de su nombre de usuario
        * @param username - Nombre de usuario
        * @return - El id del prestador, 0 si no es un prestador o no se encuentra
        */
        public int getIdPrestador(String username) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Prestador p = (Prestador) session.createCriteria(Usuario.class)
                    .add(Restrictions.eq("usuario", username))
                    .setFetchMode("prestador", FetchMode.SELECT)
                    .setFetchMode("institucion", FetchMode.SELECT)
                    .setProjection(Projections.property("prestador"))
                    .uniqueResult();
            session.close();
            if (p != null) return p.getIdPrestador();
            else return 0;
        }

        public int upPass(Usuario user, String command) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            int updateDates = session.createQuery(command)
                    .setString("npass", user.getPassword())
                    .setString("modifyBy", user.getModificadoPor())
                    .setString("ultimod", String.valueOf(user.getUltimaModif()))
                    .setString("usuario", user.getUsuario())
                    .executeUpdate();
            transaction.commit();
            session.close();
            return updateDates;
        }
}