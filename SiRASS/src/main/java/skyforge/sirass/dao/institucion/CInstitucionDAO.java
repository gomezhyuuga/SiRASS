package skyforge.sirass.dao.institucion;

import java.util.List;
import org.hibernate.criterion.*;
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
}
