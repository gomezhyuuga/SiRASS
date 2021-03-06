package skyforge.sirass.dao.prestador;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class PrestadorDAO extends DAO {
    
    /**
     * Inscribe a un prestador en un programa de servicio social
     * @param inscripcion - Objeto con los datos de la inscripción
     * @param idPrestador - El id del prestador
     * @return  1 si lo inscribe, 0 si hay un error
     */
    public int inscribir(Inscripcion inscripcion, int idPrestador) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            Prestador p = (Prestador) session.get(Prestador.class, idPrestador);
            inscripcion.setPrestador(p);
            p.setInscripcion(inscripcion.getIdInscripcion());
            session.save(inscripcion);
            session.save(p);
            transaction.commit();
            status = 1;
        } catch (Exception e) {
            System.out.println("ERROR EN METODO INSCRIBIR");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    /**
     * Inscribe a un prestador en un programa de servicio social
     * @param inscripcion - Objeto con los datos de la inscripción
     * @param username - Nombre de usuario del prestador a inscribir
     * @return  1 si lo inscribe, 0 si hay un error
     */
    public int inscribir(Inscripcion inscripcion, String username) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            transaction.begin();
            Usuario user = (Usuario) session.get(Usuario.class, username);
            Prestador p = user.getPrestador();
            int idPrestador = p.getIdPrestador();
            inscripcion.setPrestador(p);
            session.save(inscripcion);
            p.setInscripcion(inscripcion.getIdInscripcion());
            transaction.commit();
            status = 1;
        } catch (Exception e) {
            System.out.println("ERROR EN METODO INSCRIBIR");
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }

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
    public int update(Prestador prestador) {
        return super.update(prestador);
    }
    
    /**
     * Comprueba si un prestador está inscrito en un programa de SS
     * @param username - El nombre de usuario del prestador
     * @return true si está inscrito, false si no
     */
    public boolean isInscrito(String username) {
        boolean inscrito = (this.getCurrentInscripcion(username) != null) ? true : false;
        return inscrito;
    }
    /**
     * Comprueba si un prestador está inscrito en un programa de SS
     * @param idPrestador - El id del prestador
     * @return true si está inscrito, false si no
     */
    public boolean isInscrito(int idPrestador) {
        boolean inscrito = (this.getCurrentInscripcion(idPrestador) != null) ? true : false;
        return inscrito;
    }
    
    /**
     * Establece la inscripción de un prestador en el servicio social
     * @param idInscripcion ID de inscripción
     * @param idPrestador ID del prestador
     * @return 1 si OK, 0 si ocurrió un error
     */
    public int setInscripcion(Integer idInscripcion, int idPrestador) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = null;
        try {
             trans = session.beginTransaction();
             Query query = session.createQuery("update Prestador set inscripcion = :newInsc where idPrestador = :idPrestador");
             if (idInscripcion != null ) {
                query.setInteger("newInsc", idInscripcion);
             } else {
                query.setParameter("newInsc", null);
             }
             query.setInteger("idPrestador", idPrestador);
             status = query.executeUpdate();
             trans.commit();
        } catch (Exception e) {
            System.out.println("ERROR ESTABLECIENDO INSCRIPCIÓN DE PRESTADOR");
            e.printStackTrace();
            status = 0;
            trans.rollback();
        } finally {
            session.close();
        }
        return status;
    }
    /**
     * Establece la inscripción de un prestador en el servicio social
     * @param idInscripcion ID de inscripción
     * @param username usuario del prestador
     * @return 1 si OK, 0 si ocurrió un error
     */
    public int setInscripcion(Integer idInscripcion, String username) {
        UsuarioDAO udao = new UsuarioDAO();
        int idPrestador = udao.getIdPrestador(username);
        if (idPrestador != 0) {
            return this.setInscripcion(idInscripcion, idPrestador);
        } else {
            return 0;
        }
    }
    
    /**
     * Obtiene el ID de la inscripción actual de un prestador
     * @param idPrestador - El ID del prestador sobre el cual se está consultado
     * @return (Integer) ID de la inscripción, null si no tiene una inscripción 
     * o si el prestador no existe
     */
    public Integer getCurrentIDInscripcion(Integer idPrestador) {
        if (idPrestador != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String queryStr = "select p.inscripcion from Prestador p where p.idPrestador=?";
            Query query = session.createQuery(queryStr);
            query.setInteger(0, idPrestador);
            Integer idInscripcion = (Integer) query.uniqueResult();
            session.close();
            return idInscripcion;
        } else {
            return null;
        }
    }
    /**
     * Obtiene el ID de la inscripción actual de un prestador
     * @param username - El nombre de usuario del prestador sobre el cual se está consultado
     * 
     * @return (Integer) ID de la inscripción, null si no tiene una inscripción 
     * o si el prestador no existe
     */
    public Integer getCurrentIDInscripcion(String username) {
        UsuarioDAO udao = new UsuarioDAO();
        int idPrestador = udao.getIdPrestador(username);
        if (idPrestador != 0 ) {
            return this.getCurrentIDInscripcion(idPrestador);
        } else {
            return null;
        }
    }
    
    /**
     * Devuelve el ID y el estado de la inscripción actual del prestador
     * 
     * @param username - El nombre de usuario del prestador
     * @return - Inscripcion, null si no tiene ninguna
     */
    public Inscripcion getCurrentInscripcion(String username) {
        Integer idInscripcion = this.getCurrentIDInscripcion(username);
        if (idInscripcion != null) {
            UsuarioDAO udao = new UsuarioDAO();
            int idPrestador = udao.getIdPrestador(username);
            if (idPrestador != 0) {
                return this.getCurrentInscripcion(idPrestador);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
    
    /**
     * Devuelve el ID y el estado de la inscripción actual del prestador
     * 
     * @param idPrestador - El id del prestador
     * @return - Inscripcion, null si no tiene ninguna
     */
    public Inscripcion getCurrentInscripcion(int idPrestador) {
        Integer idInscripcion = this.getCurrentIDInscripcion(idPrestador);
        if (idInscripcion != null) {
            return this.getCurrentInscripcion(Restrictions.idEq(idInscripcion));
        } else {
            return null;
        }
    }
    
    private Inscripcion getCurrentInscripcion(Criterion crit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("i.idInscripcion").as("idInscripcion"));
        plist.add(Projections.property("i.estado").as("estado"));
        plist.add(Projections.property("i.horasRealizadas").as("horasRealizadas"));
        Criteria criteria = session.createCriteria(Inscripcion.class, "i")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(Inscripcion.class));
        // Agregar restricciones
        criteria.add(crit);
        Inscripcion inscripcion = (Inscripcion) criteria.uniqueResult();
        session.close();
        return inscripcion;
    }

    public int upPrestador(Prestador p) {
        int stat = 0;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            String q = "update Prestador set email=?, dCalle=?, "
                    + "dNumInt=?, dNumExt=?, dCP=?, dDelegacion=?, dColonia=?, telCasa=?, telCel=?, "
                    + "ultimaModif=? WHERE idPrestador=?";
            query = session.createQuery(q);
            query.setString(0, p.getEmail());
            query.setString(1, p.getdCalle());
            query.setString(2, p.getdNumInt());
            query.setString(3, p.getdNumExt());
            query.setString(4, p.getdCP());
            query.setString(5, p.getdDelegacion());
            query.setString(6, p.getdColonia());
            query.setString(7, p.getTelCasa());
            query.setString(8, p.getTelCel());
            query.setTimestamp(9, sqlTimestamp);
            query.setInteger(10, p.getIdPrestador());
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                stat = 1;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO DATOS");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return stat;
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
    
    public List<Prestador> getListAllFew() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Prestador> pre = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.idPrestador").as("idPrestador"));
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.aPaterno").as("aPaterno"));
        plist.add(Projections.property("p.email").as("email"));
        plist.add(Projections.property("p.telCasa").as("telCasa"));
        plist.add(Projections.property("p.telCel").as("telCel"));
        Criteria criteria = session.createCriteria(Prestador.class, "p")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(Prestador.class))
                .addOrder(Order.asc("nombre"));
        pre = (List<Prestador>) criteria.list();
        session.close();
        return pre;
    }
    
    /**
     * Método para obtener el nombre de usuario de un prestador
     * 
     * @param idPrestador - id del prestador sobre el cual se quiere obtener
     * el nombre de usuario
     * @return nombre de usuario si se encuentra, "notFound" si no existe.
     */
    public String getUsername(int idPrestador) {
        String username;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select u.usuario from Usuario u where prestador=:idPrestador");
        query.setInteger("idPrestador", idPrestador);
        username = (String) query.uniqueResult();
        session.close();
        if (username != null) {
            return username;
        } else {
            return "notFound";
        }
    }
}
