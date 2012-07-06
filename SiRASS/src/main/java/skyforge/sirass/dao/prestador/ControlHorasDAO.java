package skyforge.sirass.dao.prestador;

import java.util.Collections;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.dao.prestador.PrestadorDAO;
import skyforge.sirass.model.prestador.ControlHoras;
import skyforge.sirass.model.prestador.EstadoReporte;
import skyforge.sirass.model.prestador.Inscripcion;

/**
 *
 * @author gomezhyuuga
 */
public class ControlHorasDAO extends DAO {

    /**
     * Guarda un control de horas en la BD
     *
     * @param controlHoras - El control de horas a guardar con sus registros
     * RegistroHora ya asignados
     * @return - 1 si se guardó correctamente, 0 si hubo un error
     */
    public int insert(ControlHoras controlHoras) {
        return super.insert(controlHoras);
    }

    public int deleteByPK(int idControlHoras) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            transaction.begin();
            Query q = session.createQuery("delete from ControlHoras where idControlHoras = :id");
            q.setInteger("id", idControlHoras);
            int rows = q.executeUpdate();
            transaction.commit();
            return rows;
        } catch (Exception e) {
            System.out.println("Error borrando control de horas...");
            e.printStackTrace();
            transaction.rollback();
            return 0;
        } finally {
            session.close();
        }
    }

    /**
     * Obtener un control de horas por el ID específico
     *
     * @param id - El id del control de horas
     * @return - ControlHoras con sus datos respectivos y registros de
     * RegistroHora con las horas específicas
     */
    public ControlHoras getByPK(int id) {
        ControlHoras controlHoras = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        controlHoras = (ControlHoras) session.createCriteria(ControlHoras.class).add(Restrictions.eq("idControlHoras", id)).setFetchMode("horas", FetchMode.JOIN).setFetchMode("estado", FetchMode.JOIN).uniqueResult();
        session.close();
        return controlHoras;
    }

    /**
     * Devuelve todos los controles de horas de una inscripción. Solo contiene
     * los datos generales del reporte, no los registros de horas.
     *
     * @param idInscripcion - El id de la inscripcion sobre la cual obtener los
     * reportes
     * @return - Lista con los controles de horas pero sin registros de horas
     */
    public List<ControlHoras> getListByInscripcion(int idInscripcion) {
        List<ControlHoras> lista = null;
        Criterion crit[] = new Criterion[1];
        crit[0] = Restrictions.eq("idInscripcion", idInscripcion);
        lista = this.getListWithRestriction(crit, FetchMode.SELECT);
        return lista;
    }

    /**
     * Devuelve todos los controles de horas de una inscripción. Solo contiene
     * los datos generales del reporte, no los registros de horas.
     *
     * @param username - Nombre de un usuario inscrito a un programa de servicio
     * @return - Lista con los controles de horas
     */
    public List<ControlHoras> getListByUsername(String username) {
        List<ControlHoras> lista = null;
        int idInscripcion = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        PrestadorDAO pdao = new PrestadorDAO();
        Inscripcion inscripcion = pdao.getCurrentInscripcion(username);
        if (inscripcion != null) {
            idInscripcion = inscripcion.getIdInscripcion();
            // Obtener el control de Horas
            Criterion crit[] = new Criterion[1];
            crit[0] = Restrictions.eq("idInscripcion", idInscripcion);
            lista = this.getListWithRestriction(crit, FetchMode.SELECT);
        }
        session.close();
        return lista;
    }

    /**
     * Devuelve la lista de todos los informes en un estado en específico de un
     * usuario
     *
     * @param estado - Estado en el que se encuentra el reporte
     * @param username - Nombre de usuario que envío los reportes
     * @return
     */
    public List<ControlHoras> getListByState(EstadoReporte estado, String username) {
        List<ControlHoras> lista = null;
        Criterion crit[] = new Criterion[2];
        PrestadorDAO pdao = new PrestadorDAO();
        Inscripcion inscripcion = pdao.getCurrentInscripcion(username);
        if (inscripcion != null) {
            int idInscripcion = inscripcion.getIdInscripcion();
            crit[0] = Restrictions.eq("estado", estado);
            crit[1] = Restrictions.eq("idInscripcion", idInscripcion);
            lista = this.getListWithRestriction(crit, FetchMode.SELECT);
        }
        return lista;
    }

    private List<ControlHoras> getListWithRestriction(Criterion res[], FetchMode mode) {
        List<ControlHoras> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        Criteria criteria = session.createCriteria(ControlHoras.class).setFetchMode("horas", mode).setFetchMode("estado", FetchMode.JOIN);
        for (Criterion crit : res) {
            criteria.add(crit);
        }
        lista = Collections.checkedList(criteria.list(), ControlHoras.class);
        session.close();
        return lista;
    }
}
