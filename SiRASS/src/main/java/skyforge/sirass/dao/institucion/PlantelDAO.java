package skyforge.sirass.dao.institucion;

import java.util.List;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.institucion.Plantel;

/**
 *
 * @author gomezhyuuga
 */
public class PlantelDAO extends DAO {
    
    public List<Plantel> getPlanteles(int idCInstitucion) {
        Criterion[] crits = {Restrictions.eq("idCInstitucion", idCInstitucion)};
        return (List<Plantel>) super.list(Plantel.class, crits);
    }
}
