package skyforge.sirass.dao.institucion;

import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.institucion.CInstitucion;

/**
 *
 * @author gomezhyuuga
 */
public class CInstitucionDAO extends DAO {

    /**
     * Registra una institución en la base de datos
     *
     * @param institucion - La institucion a registrar
     * @return - Devuelve 1 si se registró, 0 si hubo un error.
     */
    public int insert(CInstitucion institucion) {
        return super.insert(institucion);
    }
    
    /**
     * Obtener solo las instituciones educativas en la BD
     * @return Lista con las instituciones educativas
     */
    public List<CInstitucion> getEducativas() {
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("idCInstitucion").as("idCInstitucion"));
        plist.add(Projections.property("nombre").as("nombre"));
        Criterion[] crits = {Restrictions.eq("educativa", true)};
        return this.customList(CInstitucion.class, crits, plist);
    }

    public CInstitucion getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CInstitucion cInstitucion = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(CInstitucion.class);
            criteria.setFetchMode("planteles", FetchMode.JOIN);
            cInstitucion = (CInstitucion) criteria.add(Restrictions.eq("idCInstitucion", id)).uniqueResult();
            transaction.commit();
        } catch (HibernateException ex) {
            System.out.println("Error obteniendo institucion");
        } finally {
            session.close();
        }
        return cInstitucion;
    }
}
