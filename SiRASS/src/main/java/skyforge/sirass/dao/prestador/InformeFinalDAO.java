package skyforge.sirass.dao.prestador;

import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.prestador.InformeFinal;

/**
 *
 * @author gomezhyuuga
 */
public class InformeFinalDAO extends DAO {

    /**
     * Guarda un Informe Final en la BD
     *
     * @param informe - El informe a guardar
     * @return - 1 si se guardó, 0 si hubo un error
     */
    public int insert(InformeFinal informe) {
        return super.insert(informe);
    }
    
    public InformeFinal getByInscripcion(int idInscripcion) {
        InformeFinal informe = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List list = session.createCriteria(InformeFinal.class)
                .add(Restrictions.eq("idInscripcion", idInscripcion))
                .setFetchMode("estado", FetchMode.JOIN)
                .addOrder(Order.desc("creacion"))
                .list();
        if (list != null && !list.isEmpty()) {
            informe = (InformeFinal) list.get(0);
        }
        session.close();
        return informe;
    }
}
