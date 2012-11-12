package skyforge.sirass.dao.programass;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import skyforge.sirass.HibernateUtil;

/**
 *
 * @author Jorge Macias
 */
public class ActividadProgramaDAO {
    public int deleteActiv(String idActividad, int idPrograma) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String q;
            Query query = null;
            if (idPrograma != 0 && idActividad != null) {
                q = "DELETE FROM ActividadPrograma where idPrograma=? AND id=?";
                query = session.createQuery(q);
                query.setInteger(0, idPrograma);
                query.setString(1, idActividad);
            } else if (idPrograma == 0 && idActividad == null) {
                status = 0;
            }
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = 1;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO RESPONSABLE");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
}
