package skyforge.sirass.dao.institucion;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.institucion.Plantel;

/**
 *
 * @author gomezhyuuga
 */
public class PlantelDAO extends DAO {

    /**
     * Registra un plantel en la base de datos
     *
     * @param plantel - El plantel a registrar
     * @return - Devuelve 1 si se registró, 0 si hubo un error.
     */
    public int insert(Plantel plantel) {
        return super.insert(plantel);
    }

    /**
     * Obtiene lista de planteles asociados a una institución
     *
     * @param idCInstitucion ID de la institución
     * @return Lista de planteles
     */
    public List<Plantel> getPlanteles(int idCInstitucion) {
        Criterion[] crits = {Restrictions.eq("idCInstitucion", idCInstitucion)};
        return (List<Plantel>) super.list(Plantel.class, crits);
    }

    public Plantel getPlantelById(int id){
        Plantel plantel = new Plantel();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Plantel.class);
            plantel = (Plantel) criteria.add(Restrictions.eq("idPlantel", id)).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error obteniendo institucion");
        } finally {
            session.close();
        }
        
        return plantel;
    }
}
