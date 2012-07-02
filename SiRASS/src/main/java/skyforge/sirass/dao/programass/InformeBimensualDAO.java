package skyforge.sirass.dao.programass;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.dao.prestador.PrestadorDAO;
import skyforge.sirass.model.prestador.InformeBimensual;

/**
 *
 * @author gomezhyuuga
 */
public class InformeBimensualDAO extends DAO {

    /**
     * Guarda un informe bimensual en la BD
     *
     * @param informe - El informe a guardar
     * @return - Devuelve 1 si se guardó, 0 si hubo un error
     */
    public int insert(InformeBimensual informe) {
        return super.insert(informe);
    }

    /**
     * Obtener un informe bimensual por su ID
     *
     * @param id - ID del informe bimensual
     * @return - InformeBimensual con sus respectivos datos
     */
    public InformeBimensual getByPK(int id) {
        InformeBimensual informe = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        informe = (InformeBimensual) session.createCriteria(InformeBimensual.class)
                .add(Restrictions.eq("idInformeBimensual", id))
                .setFetchMode("estado", FetchMode.JOIN)
                .uniqueResult();
        session.close();
        return informe;
    }

    /**
     * Obtener todos los informes bimensuales de un usuario
     *
     * @param username - Nombre de usuario
     * @return - Lista de informes bimensuales del usuario
     */
    public List<InformeBimensual> getByUsername(String username) {
        List<InformeBimensual> informes = null;
        int idInscripcion = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        PrestadorDAO pdao = new PrestadorDAO();
        idInscripcion = pdao.getCurrentInscripcion(username);
        System.out.println("inscripcion " + idInscripcion);
        if (idInscripcion != 0) {
            // Obtener el control de Horas
            Criterion crit[] = new Criterion[1];
            crit[0] = Restrictions.eq("idInscripcion", idInscripcion);
            informes = this.getListWithRestriction(crit, FetchMode.SELECT);
            session.close();
        }
        return informes;
    }

    private List<InformeBimensual> getListWithRestriction(Criterion res[], FetchMode mode) {
        List<InformeBimensual> lista = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        Criteria criteria = session.createCriteria(InformeBimensual.class).setFetchMode("estado", FetchMode.JOIN);
        for (Criterion crit : res) {
            criteria.add(crit);
        }
        lista = Collections.checkedList(criteria.list(), InformeBimensual.class);
        session.close();
        return lista;
    }

    public int update(InformeBimensual informe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        int status = 0;
        Transaction trans = session.beginTransaction();
        Query q = session.createQuery("from InformeBimensual where idInformeBimensual = :idInforme");
        InformeBimensual nuevo = (InformeBimensual) q.setInteger("idInforme", informe.getIdInformeBimensual()).list().get(0);
        nuevo.setActividades(informe.getActividades());
        nuevo.setEstado(informe.getEstado());
        nuevo.setHorasAcumuladas(informe.getHorasAcumuladas());
        nuevo.setHorasBimestre(informe.getHorasBimestre());
        nuevo.setInicioPeriodo(informe.getInicioPeriodo());
        nuevo.setTerminoPeriodo(informe.getTerminoPeriodo());
        nuevo.setModificadoPor(informe.getModificadoPor());
        nuevo.setNumReporte(informe.getNumReporte());
        nuevo.setUltimaModif(new Date(System.currentTimeMillis()));
        session.update(nuevo);

        try {
            trans.commit();
            status = 1;
        } catch (Exception e) {
            trans.rollback();
        } finally {
            session.close();
        }
        return status;
    }
}
