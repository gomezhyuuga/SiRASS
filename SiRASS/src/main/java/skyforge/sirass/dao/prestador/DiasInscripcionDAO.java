/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.prestador;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.prestador.DiasInscripcion;

/**
 *
 * @author Saul Alberto Campos
 */
public class DiasInscripcionDAO extends DAO {
    
    public int insert(DiasInscripcion inscripcion) {
        return super.insert(inscripcion);
    }
    
    public int delete(DiasInscripcion inscripcion) {
        return super.delete(inscripcion);
    }
    
    public List<DiasInscripcion> getByInscripcion(int id) {
        List<DiasInscripcion> inscripcion = new  ArrayList<DiasInscripcion>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        inscripcion = (List<DiasInscripcion>) session.createCriteria(DiasInscripcion.class)
                .add(Restrictions.eq("idInscripcion", id))
                .list();
        session.close();
        return inscripcion;
    }
    
}
