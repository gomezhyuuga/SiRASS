package skyforge.sirass.dao.procesos;

import skyforge.sirass.dao.DAO;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.model.procesos.CProcess;

/**
 *
 * @author Jorge Macias
 */
public class ProcessDao extends DAO{
    
    /*
     * Insertar a futuro algún proceso que incluya una vigencia y tenga como valores verdadero falso
     */
    public int insert(CProcess cprocess){
        return super.insert(cprocess);
    }
    
    public int update(CProcess cprocess){
        return super.update(cprocess);
    }
    
    /*
     * Comprueba si el valor mandado como parametro String esta o no en la tabla
     */
    public boolean exists(String value, String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        // Comprobar si el usuario existe
        CProcess cProcess = (CProcess) session.createCriteria(CProcess.class)
                .add(Restrictions.eq("VALOR", value))
                .add(Restrictions.eq("NOMBRE", nombre))
                .setCacheable(true)
                .uniqueResult();
        transaction.commit();
        session.close();
        if (cProcess != null) {
            return true;
        } else {
            return false;
        }
    }
}
