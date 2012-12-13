/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.admin;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.admin.Administrador;

/**
 *
 * @author JL Macias
 */
public class AdministradorDAO extends DAO {

    public int upAdminDat(Administrador a) {
        int stat = 0;
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
            Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            String q = "update Administrador set email=?,"
                    + "ultimaModif=? WHERE idAdmin=?";
            query = session.createQuery(q);
            query.setString(0, a.getEmail());
            query.setTimestamp(1, sqlTimestamp);
            query.setInteger(2, a.getIdAdmin());
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
    
    public int insert(Administrador admin) {
        return super.insert(admin);
    }
    
    public int delete(Administrador admin) {
        return super.delete(admin);
    }
    
    public List<Administrador> getAdministradores() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Administrador> admins = new ArrayList<Administrador>();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Administrador.class);
            admins = criteria.list();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return admins;
    }
    
    public Administrador getAdministradorByPK(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Administrador admin = new Administrador();
        try {
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Administrador.class);
            criteria.add(Restrictions.eq("idAdmin", id));
            admin = (Administrador) criteria.uniqueResult();
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo select. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return admin;
    }
    
    public List<Administrador> getListAllFew() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Administrador> adm = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.idAdmin").as("idAdmin"));
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.aPaterno").as("aPaterno"));
        plist.add(Projections.property("p.email").as("email"));
        plist.add(Projections.property("p.cargo").as("cargo"));
        Criteria criteria = session.createCriteria(Administrador.class, "p")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(Administrador.class))
                .addOrder(Order.asc("nombre"));
        adm = (List<Administrador>) criteria.list();
        session.close();
        return adm;
    }
    
    public int upAdminCargo(Administrador administrador) {
         Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        session.update(administrador);
        transaction.commit();
        session.close();
        return 1;
    }
    
}
