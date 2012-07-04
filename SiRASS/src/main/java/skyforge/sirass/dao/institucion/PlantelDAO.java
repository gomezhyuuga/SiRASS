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
}
