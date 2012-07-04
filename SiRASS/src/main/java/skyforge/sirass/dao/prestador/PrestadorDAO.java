package skyforge.sirass.dao.prestador;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.prestador.Prestador;

/**
 *
 * @author gomezhyuuga
 */
public class PrestadorDAO extends DAO {

    /**
     * Registra un prestador en su respectiva tabla en la BD, junto con su
     * usuario, contraseña y roles
     *
     * @param prestador : Prestador - El prestador que desea registrarse
     * @return status : int - Devuelve 1 si se agregó, 0 si hubo un error, 1062
     * si el usuario ya existe.
     */
    public int insert(Prestador prestador) {
        return super.insert(prestador);
    }
    
    /**
     * Comprueba si un prestador está inscrito en un programa de SS
     * @param username - El nombre de usuario del prestador
     * @return true si está inscrito, false si no
     */
    public boolean isInscrito(String username) {
        boolean inscrito = (this.getCurrentInscripcion(username) != 0) ? true : false;
        return inscrito;
    }
    /**
     * Comprueba si un prestador está inscrito en un programa de SS
     * @param idPrestador - El id del prestador
     * @return true si está inscrito, false si no
     */
    public boolean isInscrito(int idPrestador) {
        boolean inscrito = (this.getCurrentInscripcion(idPrestador) != 0) ? true : false;
        return inscrito;
    }
    
    /**
     * Devuelve el ID de la inscripción actual del prestador
     * 
     * @param username - El nombre de usuario del prestador
     * @return - ID de la inscripción, 0 si no tiene ninguna
     */
    public int getCurrentInscripcion(String username) {
        UsuarioDAO dao = new UsuarioDAO();
        int idPrestador = dao.getIdPrestador(username);
        if (idPrestador != 0) {
            return this.getCurrentInscripcion(idPrestador);
        } else {
            return 0;
        }
    }
    
    /**
     * Devuelve el ID de la inscripción actual del prestador
     * 
     * @param idPrestador - El id del prestador
     * @return - ID de la inscripción, 0 si no tiene ninguna
     */
    public int getCurrentInscripcion(int idPrestador) {
        return this.getCurrentInscripcion(
                Restrictions.eq("idPrestador", idPrestador)
                );
    }
    
    private int getCurrentInscripcion(Criterion crit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer inscripcion = (Integer) session.createCriteria(Prestador.class)
                .add(crit)
                .setProjection(Projections.property("inscripcion"))
                .uniqueResult();
        session.close();
        // No tiene ninguna inscripción
        if(inscripcion == null) {
            return 0;
        } else {
            return inscripcion;
        }
    }

    public int upPrestador(Prestador prestador, String comand) {
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        int updateDates = session.createQuery(comand)
                .setString("mail", prestador.getEmail())
                .setString("calle", prestador.getdCalle())
                .setString("numInt", prestador.getdNumInt())
                .setString("numExt", prestador.getdNumExt())
                .setString("cp", prestador.getdCP())
                .setString("del", prestador.getdDelegacion())
                .setString("col", prestador.getdColonia())
                .setString("telcas", prestador.getTelCasa())
                .setString("tecel", prestador.getTelCel())
                .setString("modifBy", prestador.getModificadoPor())
                .setString("ultimod", String.valueOf(prestador.getUltimaModif()))
                .setString("idPresta", String.valueOf(prestador.getIdPrestador()))
                .executeUpdate();
        transaction.commit();
        session.close();
        return updateDates;
    }

    
    public List<Prestador> getPrestadorByNumControl(String numControl) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Prestador> prestador = new ArrayList<Prestador>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Prestador.class);
            criteria.add(Restrictions.eq("nControl", numControl));
            prestador = criteria.list();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return prestador;
    }
    
    public Prestador getPrestadorByPK(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Prestador prestador = new Prestador();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Prestador.class);
            criteria.add(Restrictions.eq("idPrestador", id));
            prestador = (Prestador) criteria.uniqueResult();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return prestador;
    }
    
        public List<Prestador> getPrestadorByName(String name, String aPaterno, String aMaterno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Prestador> prestador = new ArrayList<Prestador>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Prestador.class);
            if((!name.equals(""))){
                criteria.add(Restrictions.eq("nombre", name));
            }
            if((!aPaterno.equals(""))){
                criteria.add(Restrictions.eq("aPaterno", aPaterno));
            }
            if((!aMaterno.equals(""))){
                criteria.add(Restrictions.eq("aMaterno", aMaterno));
            }  
            prestador = criteria.list();
            transaction.commit();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return prestador;
    }
    
    /**
    * Obtiene la lista completa de prestadores
    * @return - Lista de prestadores
    */
    public List<Prestador> getPrestadores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Prestador> prestador = new ArrayList<Prestador>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Prestador.class);
            prestador = criteria.list();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return prestador;
    }
}
