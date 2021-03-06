package skyforge.sirass.dao.programass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.programass.CEstado;
import skyforge.sirass.model.programass.CategoriaPrograma;
import skyforge.sirass.model.programass.ProgramaSS;

/**
 *
 * @author gomezhyuuga
 */
public class ProgramaSSDAO extends DAO {

    /**
     * Guarda un programa de SS en la BD
     *
     * @param programa - El programa con todos sus datos
     * @return - 1 si se guard, 0 si hubo un error
     */
    public int insert(ProgramaSS programa) {
        return super.insert(programa);
    }

    public int update(ProgramaSS programa){
        return super.update(programa);
    }
    
    public int uObsP(int idPrograma, short nvoEdo, String obser, String user, String cveProg) {
        return this.updateEstadoPrograma(idPrograma, nvoEdo, obser, user, null);
    }
    
    public int uCveP(int idPrograma, String user, String cveProg) {
        return this.updateClavePrograma(idPrograma,  user, cveProg);
    }
    
    public int uStatP(int idPrograma, short nvoStado, String obser, String user, String cveProg){
        return this.updateEstadoPrograma(idPrograma, nvoStado, null, user, cveProg);
    }

    /**
     * Obtiene pocos datos de todos los programas (nombre, id y clave)
     *
     * @return - Lista de Programas con poca informacin
     */
    public List<ProgramaSS> getListAllFew() {
        return this.getListAllFew(null);
    }
    /**
     * Obtiene pocos datos de los programas EXTERNOS (nombre, id y clave)
     *
     * @return - Lista de Programas EXTERNOS con poca informacin
     */
    public List<ProgramaSS> getListFewExternos() {
        Criterion[] restrictions = {Restrictions.eq("categoria", new CategoriaPrograma(2))};
        return this.getListAllFew(restrictions);
    }
    /**
     * Obtiene pocos datos de los programas INTERNOS (nombre, id y clave)
     *
     * @return - Lista de Programas INTERNOS con poca informacin
     */
    public List<ProgramaSS> getListFewInternos() {
        Criterion[] restrictions = {Restrictions.eq("categoria", new CategoriaPrograma(1))};
        return this.getListAllFew(restrictions);
    }
    /**
     * Obtiene pocos datos de todos los programas (nombre, id y clave)
     *
     * @param crits - Restricciones a aplicar
     * @return - Lista de Programas con poca informacin
     */
    private List<ProgramaSS> getListAllFew(Criterion[] crits) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProgramaSS programa = null;
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.cvePrograma").as("cvePrograma"));
        Criteria criteria = session.createCriteria(ProgramaSS.class, "p")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .addOrder(Order.asc("nombre"));
        // Aplicar restricciones
        if (crits != null && crits.length != 0) {
            for (Criterion crit : crits) {
                criteria.add(crit);
            }
        }
        programas = (List<ProgramaSS>) criteria.list();
        session.close();
        return programas;
    }
    /**
     * Obtiene pocos datos de todos los programas (nombre, id, institucion, vacantes, lugar y telefono)
     *
     * @return - Lista de Programas con poca informacin
     */
    public List<ProgramaSS> getListCatego(CategoriaPrograma categoria, CEstado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProgramaSS programa = null;
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.institucion").as("institucion"));
        plist.add(Projections.property("p.vacantes").as("vacantes"));
        plist.add(Projections.property("p.lugar").as("lugar"));
        plist.add(Projections.property("p.tel").as("tel"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.eq("categoria", categoria))
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("categoria", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }
    
    public static List<ProgramaSS> myList(CategoriaPrograma categoria, CEstado estado,
            int start, int maxResults) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProgramaSS programa = null;
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.institucion").as("institucion"));
        plist.add(Projections.property("p.cvePrograma").as("cvePrograma"));
        plist.add(Projections.property("p.vacantes").as("vacantes"));
        plist.add(Projections.property("p.lugar").as("lugar"));
        plist.add(Projections.property("p.tel").as("tel"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.eq("categoria", categoria))
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("categoria", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .setProjection(plist)
                .setFirstResult(start)
                .setMaxResults(maxResults)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }
    
    public static int count(short categoria) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Integer total = (Integer) session
                .createCriteria(ProgramaSS.class)
                .add(Restrictions.eq("categoria", new CategoriaPrograma(categoria)))
                .setProjection(Projections.rowCount()).uniqueResult();
        session.close();
        return total;
    }

    /**
     * Obtener un programa a partir de su ID
     *
     * @param id - ID del programa
     * @return - El programa solicitado con todos sus datos (incluyendo:
     * responsables, actividades, dias....)
     */
    public ProgramaSS getByPK(int id) {
        Criterion criterios[] = new Criterion[1];
        criterios[0] = Restrictions.eq("idPrograma", id);
        return this.getWithRestrictions(criterios);
    }

    /**
     * Obtener un programa a partir de su clave
     *
     * @param id - ID del programa
     * @return - El programa solicitado con todos sus datos (incluyendo:
     * responsables, actividades, dias....)
     */
    public ProgramaSS getByCve(String cvePrograma) {
        Criterion criterios[] = new Criterion[1];
        criterios[0] = Restrictions.eq("cvePrograma", cvePrograma);
        return this.getWithRestrictions(criterios);
    }

    /**
     * Obtiene un programa con ciertas restricciones. Todas sus relaciones puestas
     * como JOIN
     * @param crit Restricciones a aplicar
     * @return Objeto tipo ProgramaSS
     */
    private ProgramaSS getWithRestrictions(Criterion crit[]) {
        ProgramaSS programa = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        Criteria criteria = session.createCriteria(ProgramaSS.class)
                .setFetchMode("tipo", FetchMode.JOIN)
                .setFetchMode("alcance", FetchMode.JOIN)
                .setFetchMode("categoria", FetchMode.JOIN)
                .setFetchMode("poblacion", FetchMode.JOIN)
                .setFetchMode("dias", FetchMode.JOIN)
                .setFetchMode("responsables", FetchMode.JOIN)
                .setFetchMode("actividad", FetchMode.JOIN)
                .setFetchMode("horario", FetchMode.JOIN)
                .setFetchMode("tiempo", FetchMode.JOIN);
        // Agregar todos los criterios
        for (Criterion criterion : crit) {
            criteria.add(criterion);
        }
        // Devolver un solo resultado
        programa = (ProgramaSS) criteria.uniqueResult();
        session.close();
        return programa;
    }
    
    /**
     * Obtiene un programa con ciertas restricciones. Todas sus relaciones puestas
     * como JOIN
     * @param crit Restricciones a aplicar
     * @return Objeto tipo ProgramaSS
     */
    private List<ProgramaSS> getListWithRestrictions(Criterion crit[]) {
        List<ProgramaSS> programas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        Criteria criteria = session.createCriteria(ProgramaSS.class)
                .setFetchMode("tipo", FetchMode.SELECT)
                .setFetchMode("alcance", FetchMode.SELECT)
                .setFetchMode("categoria", FetchMode.SELECT)
                .setFetchMode("poblacion", FetchMode.SELECT)
                .setFetchMode("dias", FetchMode.SELECT)
                .setFetchMode("responsables", FetchMode.SELECT)
                .setFetchMode("actividad", FetchMode.SELECT)
                .setFetchMode("horario", FetchMode.SELECT)
                .setFetchMode("tiempo", FetchMode.SELECT);
        // Agregar todos los criterios
        for (Criterion criterion : crit) {
            criteria.add(criterion);
        }
        // Devolver un solo resultado
        programas = (List<ProgramaSS>) criteria.list();
        session.close();
        return programas;
    }

    public List<ProgramaSS> getProgramaByCateg(CategoriaPrograma categoria, CEstado estado) {
        List<ProgramaSS> programas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        Criteria criteria = session.createCriteria(ProgramaSS.class)
                .add(Restrictions.eq("categoria", categoria))
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("categoria", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN);
        // Devolver un solo resultado
        programas = (List<ProgramaSS>) criteria.list();
        session.close();
        return programas;
    }

    public List<ProgramaSS> getByIdInstituto(int id) {
        List<ProgramaSS> programas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Obtener programas de la institucion por su ID
        Criteria criteria = session.createCriteria(ProgramaSS.class).add(Restrictions.eq("idInstitucion", id));
        programas = criteria.list();
        session.close();
        return programas;
    }
    
    public List<ProgramaSS> getIdInstitutoByEdo(int id, int edo) {
        List<ProgramaSS> programas = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        //Obtener programas de la institucion por su ID
        CEstado estado = new CEstado((short) edo);
        Criteria criteria = session.createCriteria(ProgramaSS.class).add(Restrictions.eq("idInstitucion", id)).add(Restrictions.eq("estado", estado)).setFetchMode("estado", FetchMode.JOIN);
        programas = criteria.list();
        session.close();
        return programas;
    }
    public List<ProgramaSS> getListProgramasByEdoSimple(int id, CEstado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.cvePrograma").as("cvePrograma"));
        plist.add(Projections.property("p.area").as("area"));
        plist.add(Projections.property("p.plazas").as("plazas"));
        plist.add(Projections.property("p.vacantes").as("vacantes"));
        plist.add(Projections.property("p.ocupadas").as("ocupadas"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.eq("idInstitucion", id))
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("estado", FetchMode.JOIN)
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }

    public int upProgSS(ProgramaSS programaSS, String command) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();
        int stat = session.createQuery(command)
                .setString("nameProg", programaSS.getNombre())
                .setString("objG", programaSS.getObjGeneral())
                .setString("desa", programaSS.getDesarrollo())
                .setString("obs", programaSS.getObservaciones())
                .setString("mp", programaSS.getModificadoPor())
                .setString("um", String.valueOf(programaSS.getUltimaModif()))
                .setString("id", String.valueOf(programaSS.getIdPrograma()))
                .executeUpdate();
        transaction.commit();
        session.close();
        return stat;
    }

    public int delProg(ProgramaSS programaSS, String command) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        transaction.begin();

        int delProg = session.createQuery(command)
                .setString("stado", String.valueOf(3))
                .setString("modifBy", programaSS.getModificadoPor())
                .setString("ultModBy", String.valueOf(programaSS.getUltimaModif()))
                .setString("id", String.valueOf(programaSS.getIdPrograma()))
                .executeUpdate();
        
        transaction.commit();
        session.close();
        return delProg;
    }
    public List<ProgramaSS> getListProgramasByEdo(CEstado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.cvePrograma").as("cvePrograma"));
        plist.add(Projections.property("p.institucion").as("institucion"));
        plist.add(Projections.property("p.tel").as("tel"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("estado", FetchMode.JOIN)
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }
    public List<ProgramaSS> getListProgramasByEdoCatego(CategoriaPrograma categoria, CEstado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.cvePrograma").as("cvePrograma"));
        plist.add(Projections.property("p.institucion").as("institucion"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.eq("categoria", categoria))
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("categoria", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }
    public List<ProgramaSS> getListProgramasByVigencia() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProgramaSS> programas = null;
        String q = "date_sub(fechaTiempo, interval 5 DAY) <= curdate() AND status=1";
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.nombre").as("nombre"));
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        plist.add(Projections.property("p.cvePrograma").as("cvePrograma"));
        plist.add(Projections.property("p.institucion").as("institucion"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.sqlRestriction(q))
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }
    public List<ProgramaSS> getListProgramasByIdEdoCont(int id, CEstado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ProgramaSS> programas = null;
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("p.idPrograma").as("idPrograma"));
        programas = (List<ProgramaSS>) session.createCriteria(ProgramaSS.class, "p")
                .add(Restrictions.eq("idInstitucion", id))
                .add(Restrictions.eq("estado", estado))
                .setFetchMode("estado", FetchMode.JOIN)
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(ProgramaSS.class))
                .list();
        session.close();
        return programas;
    }
    
    private int updateEstadoPrograma(int idInscripcion, Short nuevoEstado, String observaciones,
             String user, String cveProg) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String q;
            Query query = null;
            if (observaciones != null && cveProg == null) {
                q = "update ProgramaSS set ultimaModif=?, modificadoPor=?, notas=?, estado=? where idPrograma=?";
                query = session.createQuery(q);
                query.setString(2, observaciones);
                query.setShort(3, nuevoEstado);
                query.setInteger(4, idInscripcion);
            } else if (cveProg != null) {
                q = "update ProgramaSS set ultimaModif=?, modificadoPor=?, cvePrograma=?, notas = null, estado=?"
                        + "where idPrograma=?";
                query = session.createQuery(q);
                query.setString(2, cveProg);
                query.setShort(3, nuevoEstado);
                query.setInteger(4, idInscripcion);
            }
            Date curDate = new Date(System.currentTimeMillis());
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = 1;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO ESTADO");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }

    private int updateClavePrograma(int idPrograma, String user, String cveProg) {
        int status = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            String q;
            Query query = null;
            if (cveProg != null) {
                q = "update ProgramaSS set cvePrograma=?, ultimaModif=?, modificadoPor=? where idPrograma=?";
                query = session.createQuery(q);
                query.setString(0, cveProg);
                query.setInteger(3, idPrograma);
            }
            Date curDate = new Date(System.currentTimeMillis());
            query.setTimestamp(1, curDate);
            query.setString(2, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = 1;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO CLAVE");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }

}
