/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.admin;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.admin.Administrador;

/**
 *
 * @author JL Macias
 */
public class AdministradorDAO extends DAO {

    public int upAdminDat(Administrador administrador, String comand) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        int updateDates = session.createQuery(comand)
                .setString("mail", administrador.getEmail())
                .setString("modifBy", administrador.getModificadoPor())
                .setString("ultimod", String.valueOf(administrador.getUltimaModif()))
                .setString("idAdmin", String.valueOf(administrador.getIdAdmin()))
                .executeUpdate();
        transaction.commit();
        session.close();
        return updateDates;
        
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
