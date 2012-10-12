/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.programass;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.programass.TipoPrograma;

/**
 *
 * @author Jorge Macias
 */
public class tipoProgramaDAO extends DAO{
    
    //Registrar nuevos tipos de programa
    public int insert(TipoPrograma tipoPrograma) {
        return super.insert((TipoPrograma) tipoPrograma);
    }
    
    public short getIdTipoByName(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener los responsables de programas
        Criteria criteria = session.createCriteria(TipoPrograma.class)
                .add(Restrictions.eq("descripcion", nombre));
        TipoPrograma t = (TipoPrograma) criteria.list().get(0);
        session.close();
        if (t != null) {
            return t.getIdTipo();
        } else {
            return 0;
        }
    }
    
    public List<TipoPrograma> getAllTypes(){
        List<TipoPrograma> typeProg = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Obtener los tipos de programa existentes
        Criteria criteria = session.createCriteria(TipoPrograma.class);
        typeProg = (List<TipoPrograma>) criteria.list();
        session.close();
        
        return typeProg;
    }
    
}
