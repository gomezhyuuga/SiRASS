package skyforge.sirass.dao.programass;

import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.model.programass.ResponsablePrograma;

/**
 *
 * @author gomezhyuuga
 */
public class ResponsableProgramaDAO {

    public List<ResponsablePrograma> getByPrograma(int id) {
        Criterion criterio = Restrictions.eq("programa.idPrograma", id);
        return this.getWithRestriction(criterio, null);
    }

    public ResponsablePrograma getFirstByPrograma(int id) {
        Criterion criterio = Restrictions.eq("programa.idPrograma", id);
        ResponsablePrograma responsable = this.getWithRestriction(criterio, 1).get(0);
        if (responsable != null) {
            return responsable;
        } else {
            return null;
        }
    }

    private List<ResponsablePrograma> getWithRestriction(Criterion crit, Integer maxResults) {
        List<ResponsablePrograma> responsables = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener los responsables de programas
        Criteria criteria = session.createCriteria(ResponsablePrograma.class).setFetchMode("programa", FetchMode.SELECT).add(crit);
        // Establecer un máximo de resultados si se puso
        if (maxResults != null) {
            criteria.setMaxResults(maxResults);
        }
        // Devolver lista
        responsables = (List<ResponsablePrograma>) criteria.list();
        session.close();
        return responsables;
    }

    public int deleteRespon(String idResponsable, int idPrograma) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String q;
            Query query = null;
            if (idPrograma != 0 && idResponsable != null) {
                q = "DELETE FROM ResponsablePrograma where idPrograma=? AND id=?";
                query = session.createQuery(q);
                query.setInteger(0, idPrograma);
                query.setString(1, idResponsable);
            } else if (idPrograma == 0 && idResponsable == null) {
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