package skyforge.sirass.dao.prestador;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.prestador.Inscripcion;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionDAO extends DAO{
    /***
     * Inscribe a a lguien en un programa de Servicio Social
     * @param inscripcion - La inscripcion
     * @return - 1 si se incribe, 0 si hay un error
     */
    public int insert(Inscripcion inscripcion) {
        return super.insert(inscripcion);
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
                .setFetchMode("idPrestador", FetchMode.JOIN)
                .setFetchMode("tipo", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .setFetchMode("idInstitucion", FetchMode.JOIN)
                .setFetchMode("idPlantel", FetchMode.JOIN)
                .uniqueResult();
        session.close();
        return inscripcion;
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
