package skyforge.sirass.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.transform.AliasToBeanResultTransformer;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class DAO {

    /**
     * Lógica general para hacer las operaciones: 1) Crear y abrir sesión con
     * HibernateUtil 2) Crear y comenzar Transaction 3) Realizar operación
     * (insert, delete, update, select, etc) 4) Hacer commit 5) Cerrar sesión
     */
    /**
     * Realiza un registro en la BD del objeto pasado como parámetro
     *
     * @param obj - El objeto a regsitrar en la BD
     * @return - Devuelve 1 si se registra, 0 si hay un error.
     */
    public int insert(Object obj) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(obj);
            transaction.commit();
            return 1;
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo insert. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("#### --- ####");
            return e.getErrorCode();
        } catch (DataException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo insert. Datos incorrectos. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("errorCode: " + e.getErrorCode());
            System.out.println("sql: " + e.getSQL());
            System.out.println("sqlState: " + e.getSQLState());
            e.printStackTrace();
            System.out.println("#### --- ####");
            return e.getErrorCode();
        } finally {
            session.close();
        }
    }
    
    public int update(Object obj) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(obj);
            transaction.commit();
            return 1;
        } catch (ConstraintViolationException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo update. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("#### --- ####");
            return e.getErrorCode();
        } catch (DataException e) {
            transaction.rollback();
            System.out.println("#### --- ####");
            System.out.println("Error haciendo update. Datos incorrectos. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("errorCode: " + e.getErrorCode());
            System.out.println("sql: " + e.getSQL());
            System.out.println("sqlState: " + e.getSQLState());
            e.printStackTrace();
            System.out.println("#### --- ####");
            return e.getErrorCode();
        } finally {
            session.close();
        }
    }

    public Object get(Class clase, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Transaction trans = session.beginTransaction();
            Object obj = session.load(clase, id);
            trans.commit();
            return obj;
        } catch (Exception e) {
            System.out.println("### Error obteniendo objeto ###");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * Obtiene todos los objetos de una tabla
     *
     * @param clase - La entidad a obtener
     * @return Lista de objetos
     */
    public List getAll(Class clase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = null;
        try {
            Criteria criteria = session.createCriteria(clase);
            list = criteria.list();
        } catch (ConstraintViolationException e) {
            System.out.println("#### --- ####");
            System.out.println("Error haciendo query getAll. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return list;
    }

    public int delete(Object obj) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(obj);
            transaction.commit();
            return 1;
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("### Error borrando objeto ###");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }

    public List list(Class clase, Criterion[] crits) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Usuario> list = null;
        try {
            Criteria criteria = session.createCriteria(clase);
            // Agregar restricciones
            if (crits != null) {
                for (Criterion crit : crits) {
                    criteria.add(crit);
                }
            }
            list = criteria.list();
        } catch (ConstraintViolationException e) {
            System.out.println("#### --- ####");
            System.out.println("Error haciendo query. Motivo:");
            System.out.println(e.getLocalizedMessage());
            System.out.println("#### --- ####");
        } finally {
            session.close();
        }
        return list;
    }
    
    /**
     * Obtiene una serie de registros de cualquier tabla con restricciones y projections
     * opcionales.
     * 
     * @param clase - Entidad que representa la table sobre la cual se hace la consulta
     * @param crits - Array de criterios a agregar.
     * @param plist - ProjectionList para obtener solo los elemento sseleccinoados
     * @return Lista de registros
     */
    public List customList(Class clase, Criterion[] crits, ProjectionList plist) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        List list = null;
        Criteria criteria = s.createCriteria(clase);
        // Aplicar criterios en caso de ser necesario
        if (crits != null && crits.length > 0) {
            for (Criterion c : crits) {
                criteria.add(c);
            }
        }
        // Aplicar projections en caso de ser necesario
        if (plist != null) {
            criteria.setProjection(plist)
                    .setResultTransformer(new AliasToBeanResultTransformer(clase));
        }
        list = criteria.list();
        s.close();
        return list;
    }
    
    /**
     * Obtiene un registro de cualquier tabla con restricciones y projections
     * opcionales.
     * 
     * @param clase - Entidad que representa la table sobre la cual se hace la consulta
     * @param crits - Array de criterios a agregar.
     * @param plist - ProjectionList para obtener solo los elemento sseleccinoados
     * @return Unico objeto con el registro encontrado
     */
    public Object customUniqueResult(Class clase, Criterion[] crits, ProjectionList plist) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Object obj = null;
        Criteria criteria = s.createCriteria(clase);
        // Aplicar criterios en caso de ser necesario
        if (crits != null && crits.length > 0) {
            for (Criterion c : crits) {
                criteria.add(c);
            }
        }
        // Aplicar projections en caso de ser necesario
        if (plist != null) {
            criteria.setProjection(plist)
                    .setResultTransformer(new AliasToBeanResultTransformer(clase));
        }
        obj = criteria.uniqueResult();
        s.close();
        return obj;
    }
}