/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.user;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
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

    public int delete(Usuario usuario) {
        return super.delete(usuario);
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
            System.out.println("El usuario existe!");
            return true;
        } else {
            System.out.println("El usuario no existe!");
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
                .setFetchMode("prestador", FetchMode.JOIN)
                .setFetchMode("institucion", FetchMode.JOIN)
                .setFetchMode("administrador", FetchMode.JOIN)
                .setFetchMode("roles", FetchMode.JOIN)
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
    
    
    public List<Usuario> getUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Usuario> user = new ArrayList<Usuario>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Usuario.class);
            user = criteria.list();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return user;
    }
}
