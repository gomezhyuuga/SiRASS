package skyforge.sirass.dao.prestador;

import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.prestador.EstadoInscripcion;
import skyforge.sirass.model.prestador.Inscripcion;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionDAO extends DAO {
    
    /***
     * Inscribe a a lguien en un programa de Servicio Social
     * @param inscripcion - La inscripcion
     * @return - 1 si se incribe, 0 si hay un error
     */
    public int insert(Inscripcion inscripcion) {
        return super.insert(inscripcion);
    }
    
    /**
     * Actualiza el estado de una inscripción
     * @param idInscripcion ID de la inscripción a actualizar
     * @param nuevoEstado - El nuevo estado
     * @param user - El usuario que está haciendo las modificaciones
     * @return true si lo cambia, false si hay un error
     */
    public boolean updateEstado(int idInscripcion, Short nuevoEstado, String user) {
        boolean status = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Inscripcion set ultimaModif=?, modificadoPor=?, estado=? where idInscripcion=?";
            query = session.createQuery(q);
            query.setShort(2, nuevoEstado);
            query.setInteger(3, idInscripcion);
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = true;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO INSCRIPCIÓN");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    /**
     * Actualiza el estado de una inscripción
     * @param idInscripcion ID de la inscripción a actualizar
     * @param nuevoEstado - El nuevo estado
     * @param observaciones - Las nuevas observaciones
     * @param user - El usuario que está haciendo las modificaciones
     * @return true si lo cambia, false si hay un error
     */
    public boolean updateEstadoYObservaciones(int idInscripcion, String observaciones,
            Short nuevoEstado, String user) {
        boolean status = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Inscripcion set ultimaModif=?, modificadoPor=?, estado=?, observaciones=? where idInscripcion=?";
            query = session.createQuery(q);
            query.setShort(2, nuevoEstado);
            query.setString(3, observaciones);
            query.setInteger(4, idInscripcion);
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = true;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO INSCRIPCIÓN");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    /**
     * Actualiza las observaciones de una inscripción
     * @param idInscripcion ID de la inscripción a actualizar
     * @param nuevoEstado - Las nuevas obsercacioens
     * @param observaciones - Las nuevas observaciones
     * @param user - El usuario que está haciendo las modificaciones
     * @return true si lo cambia, false si hay un error
     */
    public boolean updateObservaciones(int idInscripcion, String observaciones, String user) {
        boolean status = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Inscripcion set ultimaModif=?, modificadoPor=?, observaciones=? where idInscripcion=?";
            query = session.createQuery(q);
            query.setString(2, observaciones);
            query.setInteger(3, idInscripcion);
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = true;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO INSCRIPCIÓN");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    
    /**
     * Obtiene una inscripcion a partir de su ID
     * @param id - el id de la inscripcion
     * @return 
     */
    public Inscripcion getByPK(int id) {
        Inscripcion inscripcion = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        inscripcion = (Inscripcion) session.createCriteria(Inscripcion.class)
                .add(Restrictions.eq("idInscripcion", id))
                .setFetchMode("prestador", FetchMode.JOIN)
                .setFetchMode("tipo", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .setFetchMode("dias", FetchMode.JOIN)
                .setFetchMode("idInstitucion", FetchMode.JOIN)
                .setFetchMode("idPlantel", FetchMode.JOIN)
                .uniqueResult();
        session.close();
        return inscripcion;
    }
    
    /**
     * Obtiene una lista de inscripciones con únicamnete los atributos:
     * idInscripcion, prestador, tipo, estado, programa
     * 
     * @param ESTADO - Estado de la inscripción para filtrar
     * @return Lista de inscripciones, null si no hay
     */
    public List<Inscripcion> getFewWithStatus(short ESTADO) {
        Criterion[] crits = {Restrictions.eq("i.estado", new EstadoInscripcion((short) ESTADO))};
        return this.getAllFew(crits);
    }
    
    /**
     * Obtiene una lista de inscripciones con únicamnete los atributos:
     * idInscripcion, prestador, tipo, estado, programa
     * 
     * @param crits - Restricciones a aplicar al hacer la consulta
     * @return Lista de inscripciones, null si no hay
     */
    private List<Inscripcion> getAllFew(Criterion[] crits) {
        List<Inscripcion> inscripciones = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("i.idInscripcion").as("idInscripcion"));
        plist.add(Projections.property("i.prestador").as("prestador"));
        plist.add(Projections.property("i.tipo").as("tipo"));
        plist.add(Projections.property("i.estado").as("estado"));
        plist.add(Projections.property("i.programa").as("programa"));
        Criteria criteria = session.createCriteria(Inscripcion.class, "i")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(Inscripcion.class));
        // Agregar restrecciín
        if (crits != null) {
            for (Criterion crit : crits) {
                criteria.add(crit);
            }
        }
        inscripciones = criteria.list();
        return inscripciones;
    }
    
    /**
     * Devuelve la cantidad de horas realizadas en el servicio social
     * @param idInscripcion - ID de la inscripción
     * @return Las horas acumuladas pudiendo ser 0 <= horas <= 480
     */
    public short getHorasRealizadas(int idInscripcion) {
        Criterion criterio = Restrictions.eq("idInscripcion", idInscripcion);
        return this.getHorasRealizadas(criterio);
    }
    
    private short getHorasRealizadas(Criterion crit) {
        // Obtener solamente horas realizadas
        Session session = HibernateUtil.getSessionFactory().openSession();
        Short horas = (Short) session.createCriteria(Inscripcion.class)
                .add(crit)
                .setProjection(Projections.property("horasRealizadas"))
                .uniqueResult();
        session.close();
        // No tiene horas
        if (horas == null) {
            return 0;
        } else {
            return horas;
        }
    }
}
