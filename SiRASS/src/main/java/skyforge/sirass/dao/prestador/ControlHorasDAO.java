package skyforge.sirass.dao.prestador;

import java.util.Collections;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
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
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Inscripcion insc = (Inscripcion) session.load(Inscripcion.class, controlHoras.getIdInscripcion());
            insc.setHorasRealizadas(controlHoras.getHorasAcumuladas());
            insc.setMinutosRealizados(controlHoras.getMinutosAcumulados());
            session.save(controlHoras);
            session.update(insc);
            transaction.commit();
            return 1;
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo insert de controlHoras. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("#### --- ####");
            return e.getErrorCode();
        } catch (DataException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo insert de controlHoras. Datos incorrectos. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("errorCode: " + e.getErrorCode());
            System.out.println("sql: " + e.getSQL());
            System.out.println("sqlState: " + e.getSQLState());
            e.printStackTrace();
            System.out.println("#### --- ####");
            return e.getErrorCode();
        } finally {
            session.close();
        }
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
        controlHoras = (ControlHoras) session.createCriteria(ControlHoras.class)
                .add(Restrictions.eq("idControlHoras", id))
                .setFetchMode("horas", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .uniqueResult();
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
        Criteria criteria = session.createCriteria(ControlHoras.class)
                .setFetchMode("horas", mode)
                .setFetchMode("estado", FetchMode.JOIN);
        for (Criterion crit : res) {
            criteria.add(crit);
        }
        lista = Collections.checkedList(criteria.list(), ControlHoras.class);
        session.close();
        return lista;
    }
    
    public ControlHoras getLastReport(int idInscripcion) {
        ControlHoras control = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        DetachedCriteria maxQuery = DetachedCriteria.forClass( ControlHoras.class );
        maxQuery.setProjection( Projections.max( "creacion" ) );
        Criteria query = session.createCriteria( ControlHoras.class );
        query.add( Property.forName( "creacion" ).eq( maxQuery ) );
        control = (ControlHoras) query.uniqueResult();
        // Obtener el último control de horas
        session.close();
        return control;
    }
}
