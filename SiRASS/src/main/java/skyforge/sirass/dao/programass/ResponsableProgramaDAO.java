package skyforge.sirass.dao.programass;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.model.programass.ResponsablePrograma;

/**
 *
 * @author gomezhyuuga
 */
public class ResponsableProgramaDAO {

    public List<ResponsablePrograma> getByPrograma(int id) {
        Criterion criterio = Restrictions.eq("programa.idPrograma", id);
        return this.getWithRestriction(criterio, null);
    }
    public ResponsablePrograma getFirstByPrograma(int id) {
        Criterion criterio = Restrictions.eq("programa.idPrograma", id);
        ResponsablePrograma responsable = this.getWithRestriction(criterio, 1).get(0);
        if (responsable != null) {
            return responsable;
        } else {
            return null;
        }
    }
    
    private List<ResponsablePrograma> getWithRestriction(Criterion crit, Integer maxResults) {
        List<ResponsablePrograma> responsables = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener los responsables de programas
        Criteria criteria = session.createCriteria(ResponsablePrograma.class)
                .setFetchMode("programa", FetchMode.SELECT)
                .add(crit);
        // Establecer un máximo de resultados si se puso
        if (maxResults != null) {
            criteria.setMaxResults(maxResults);
        }
        // Devolver lista
        responsables = (List<ResponsablePrograma>) criteria.list();
        session.close();
        return responsables;
    }
}