package skyforge.sirass.dao.institucion;

import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.institucion.Institucion;

/**
 *
 * @author gomezhyuuga
 */
public class InstitucionDAO extends DAO {

    public int insert(Institucion obj) {
        return super.insert(obj);
    }

    public int delete(Institucion obj) {
        return super.delete(obj);
    }

    public int upIns(String domicilio, String respon, String cargo, String tel, String tex, String mail, String modby, int idins) {
        int stat = 0;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Institucion set domicilio=?, responsable=?, "
                    + "cargo=?, tel=?, telExt=?, email=?, modificadoPor=?, "
                    + "ultimaModif=? WHERE idInstitucion=?";
            query = session.createQuery(q);
            query.setString(0, domicilio);
            query.setString(1, respon);
            query.setString(2, cargo);
            query.setString(3, tel);
            query.setString(4, tex);
            query.setString(5, mail);
            query.setString(6, modby);
            query.setTimestamp(7, sqlTimestamp);
            query.setInteger(8, idins);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                stat = 1;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO DATOS");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return stat;
    }
}
