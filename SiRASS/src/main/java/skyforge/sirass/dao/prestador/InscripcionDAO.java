package skyforge.sirass.dao.prestador;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
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
