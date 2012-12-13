package skyforge.sirass.dao.institucion;

import java.util.Date;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
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
    
    public List<Institucion> getListAllFew() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Institucion> adm = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.idInstitucion").as("idInstitucion"));
        plist.add(Projections.property("p.idCInstitucion").as("idCInstitucion"));
        plist.add(Projections.property("p.idPlantel").as("idPlantel"));
        plist.add(Projections.property("p.responsable").as("responsable"));
        Criteria criteria = session.createCriteria(Institucion.class, "p")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(Institucion.class))
                .addOrder(Order.asc("responsable"));
        adm = (List<Institucion>) criteria.list();
        session.close();
        return adm;
    }
    
    public Institucion getByIdInstitucion(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Institucion ins = null;
        Criteria criteria = session.createCriteria(Institucion.class)
                .add(Restrictions.eq("idInstitucion", id));
        ins = (Institucion) criteria.uniqueResult();
        session.close();
        return ins;
    }
    
}
