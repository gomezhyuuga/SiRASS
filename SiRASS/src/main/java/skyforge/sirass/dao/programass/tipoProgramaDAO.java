/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.programass;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
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
        return super.insert(tipoPrograma);
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
