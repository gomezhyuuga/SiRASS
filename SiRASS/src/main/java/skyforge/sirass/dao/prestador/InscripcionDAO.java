package skyforge.sirass.dao.prestador;

import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import skyforge.sirass.HibernateUtil;
import skyforge.sirass.dao.DAO;
import skyforge.sirass.model.prestador.EstadoInscripcion;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.prestador.TipoInscripcion;
import skyforge.sirass.model.programass.CategoriaPrograma;
import skyforge.sirass.model.programass.ProgramaSS;
import skyforge.sirass.model.programass.TipoPrograma;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionDAO extends DAO {
    
    /***
     * Inscribe a a lguien en un programa de Servicio Social
     * @param inscripcion - La inscripcion
     * @return - 1 si se incribe, 0 si hay un error
     */
    public int insert(Inscripcion inscripcion) {
        return super.insert(inscripcion);
    }
    
    /**
     * Actualiza el estado de una inscripción
     * @param idInscripcion ID de la inscripción a actualizar
     * @param nuevoEstado - El nuevo estado
     * @param user - El usuario que está haciendo las modificaciones
     * @return true si lo cambia, false si hay un error
     */
    public boolean updateEstado(int idInscripcion, Short nuevoEstado, String user) {
        boolean status = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Inscripcion set ultimaModif=?, modificadoPor=?, estado=? where idInscripcion=?";
            query = session.createQuery(q);
            query.setShort(2, nuevoEstado);
            query.setInteger(3, idInscripcion);
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = true;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO INSCRIPCIÓN");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    /**
     * Actualiza el estado de una inscripción
     * @param idInscripcion ID de la inscripción a actualizar
     * @param nuevoEstado - El nuevo estado
     * @param observaciones - Las nuevas observaciones
     * @param user - El usuario que está haciendo las modificaciones
     * @return true si lo cambia, false si hay un error
     */
    public boolean updateEstadoYObservaciones(int idInscripcion, String observaciones,
            Short nuevoEstado, String user) {
        boolean status = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Inscripcion set ultimaModif=?, modificadoPor=?, estado=?, observaciones=? where idInscripcion=?";
            query = session.createQuery(q);
            query.setShort(2, nuevoEstado);
            query.setString(3, observaciones);
            query.setInteger(4, idInscripcion);
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = true;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO INSCRIPCIÓN");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    /**
     * Actualiza las observaciones de una inscripción
     * @param idInscripcion ID de la inscripción a actualizar
     * @param nuevoEstado - Las nuevas obsercacioens
     * @param observaciones - Las nuevas observaciones
     * @param user - El usuario que está haciendo las modificaciones
     * @return true si lo cambia, false si hay un error
     */
    public boolean updateObservaciones(int idInscripcion, String observaciones, String user) {
        boolean status = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;
        try {
            Date curDate = new Date();
            String q = "update Inscripcion set ultimaModif=?, modificadoPor=?, observaciones=? where idInscripcion=?";
            query = session.createQuery(q);
            query.setString(2, observaciones);
            query.setInteger(3, idInscripcion);
            query.setTimestamp(0, curDate);
            query.setString(1, user);
            int rows = query.executeUpdate();
            transaction.commit();
            if (rows > 0) {
                status = true;
            }
        } catch (Exception ex) {
            transaction.rollback();
            System.out.println("ERROR ACTUALIZANDO INSCRIPCIÓN");
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
    
    /**
     * Obtiene una inscripcion a partir de su ID
     * @param id - el id de la inscripcion
     * @return 
     */
    public Inscripcion getByPK(int id) {
        Inscripcion inscripcion = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        // Obtener el control de Horas
        inscripcion = (Inscripcion) session.createCriteria(Inscripcion.class)
                .add(Restrictions.eq("idInscripcion", id))
                .setFetchMode("prestador", FetchMode.JOIN)
                .setFetchMode("tipo", FetchMode.JOIN)
                .setFetchMode("estado", FetchMode.JOIN)
                .setFetchMode("dias", FetchMode.JOIN)
                .setFetchMode("idInstitucion", FetchMode.JOIN)
                .setFetchMode("idPlantel", FetchMode.JOIN)
                .uniqueResult();
        session.close();
        return inscripcion;
    }
    
    /**
     * Obtiene una lista de inscripciones con únicamnete los atributos:
     * idInscripcion, prestador, tipo, estado, programa
     * 
     * @param ESTADO - Estado de la inscripción para filtrar
     * @return Lista de inscripciones, null si no hay
     */
    public List<Inscripcion> getFewWithStatus(short ESTADO) {
        Criterion[] crits = {Restrictions.eq("i.estado", new EstadoInscripcion((short) ESTADO))};
        return this.getAllFew(crits);
    }
    
    /**
     * Obtiene una lista de inscripciones con únicamnete los atributos:
     * idInscripcion, prestador, tipo, estado, programa
     * 
     * @param crits - Restricciones a aplicar al hacer la consulta
     * @return Lista de inscripciones, null si no hay
     */
    private List<Inscripcion> getAllFew(Criterion[] crits) {
        List<Inscripcion> inscripciones = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        ProjectionList plist = Projections.projectionList();
        plist.add(Projections.property("i.idInscripcion").as("idInscripcion"));
        plist.add(Projections.property("i.prestador").as("prestador"));
        plist.add(Projections.property("i.tipo").as("tipo"));
        plist.add(Projections.property("i.estado").as("estado"));
        plist.add(Projections.property("i.programa").as("programa"));
        Criteria criteria = session.createCriteria(Inscripcion.class, "i")
                .setProjection(plist)
                .setResultTransformer(new AliasToBeanResultTransformer(Inscripcion.class));
        // Agregar restrecciín
        if (crits != null) {
            for (Criterion crit : crits) {
                criteria.add(crit);
            }
        }
        inscripciones = criteria.list();
        return inscripciones;
    }
    
    /**
     * Devuelve la cantidad de horas realizadas en el servicio social
     * @param idInscripcion - ID de la inscripción
     * @return Las horas acumuladas pudiendo ser 0 <= horas <= 480
     */
    public short getHorasRealizadas(int idInscripcion) {
        Criterion criterio = Restrictions.eq("idInscripcion", idInscripcion);
        return this.getHorasRealizadas(criterio);
    }
    
    public String getObservaciones(int idInscripcion) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        String observaciones = (String) s.createQuery("select observaciones from Inscripcion where idInscripcion=:id")
                .setInteger("id", idInscripcion)
                .uniqueResult();
        s.close();
        return observaciones;
    }
    
    private short getHorasRealizadas(Criterion crit) {
        // Obtener solamente horas realizadas
        Session session = HibernateUtil.getSessionFactory().openSession();
        Short horas = (Short) session.createCriteria(Inscripcion.class)
                .add(crit)
                .setProjection(Projections.property("horasRealizadas"))
                .uniqueResult();
        session.close();
        // No tiene horas
        if (horas == null) {
            return 0;
        } else {
            return horas;
        }
    }
    
    public String generarNumControl(int idInscripcion, Integer start) {
        String tipo = null;
        int consecutivo = 0;
        Integer actual;
        String numControl = null;
        // Establecer si ya inicia con un valor
        if (start != null) {
            consecutivo = start;
        }
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            // Determinar el tipo de inscripcion
            ProjectionList plist = Projections.projectionList();
            plist.add(Projections.property("idInscripcion").as("idInscripcion"));
            plist.add(Projections.property("tipo").as("tipo"));
            plist.add(Projections.property("idPrograma").as("idPrograma"));
            Inscripcion i = (Inscripcion) s.createCriteria(Inscripcion.class)
                    .add(Restrictions.idEq(idInscripcion))
                    .setProjection(plist)
                    .setResultTransformer(new AliasToBeanResultTransformer(Inscripcion.class))
                    .uniqueResult();
            if (i != null) {
                Short t = i.getTipo().getIdTipo();
                int idPrograma = i.getIdPrograma();
                if (t == TipoInscripcion.SERVICIO_SOCIAL) {
                    tipo = "SS";
                } else if (t == TipoInscripcion.PRACTICA_PROFESIONAL) {
                    tipo = "PP";
                }
                System.out.println("TIPO: " + tipo);
                numControl = tipo + "-";
                
                // Determinar si está en un programa interno o externo
                Integer tPr = (Integer) s.createCriteria(ProgramaSS.class)
                        .add(Restrictions.idEq(idPrograma))
                        .setProjection(Projections.property("categoria.idCategoria"))
                        .uniqueResult();
                String prog = null;
                if (tPr == CategoriaPrograma.INTERNO) {
                    prog = "I";
                } else if (tPr == CategoriaPrograma.EXTERNO) {
                    prog = "E";
                }
                numControl += prog + "-";
                System.out.println("PROGRAMA: " + prog);
                // Obtener la cantidad de prestadores (consecutivo actual)
                actual = this.getTotalPrestadores();
                consecutivo += actual;
                consecutivo++;
                System.out.println("NUEVO: " + consecutivo);
                String consec = String.valueOf(consecutivo);
                while (consec.length() < 4) {
                    consec = "0" + consec;
                }
                System.out.println("NUEVO_fill: " + consec);
                numControl += consec;
            }
        } catch (Exception e) {
            System.out.println("ERROR GENERANDO NUM DE CONTROL");
            e.printStackTrace();
        } finally {
            s.close();
        }
        return numControl;
    }

    public InscripcionDAO() {
    }
    
    private Integer getTotalPrestadores() {
        Integer actual = 0;
        // Obtener la cantidad de prestadores (consecutivo actual)
        Session s = HibernateUtil.getSessionFactory().openSession();
        try {
            actual = (Integer) s.createCriteria(Inscripcion.class)
                    .add(Restrictions.ne("estado.idEstado", EstadoInscripcion.CON_ERRORES))
                    .add(Restrictions.ne("estado.idEstado", EstadoInscripcion.CORRECTA))
                    .add(Restrictions.ne("estado.idEstado", EstadoInscripcion.EN_ESPERA))
                    .setProjection(Projections.rowCount())
                    .uniqueResult();
            System.out.println("ACTUAL: " + actual);
        } catch(Exception e) {
            System.out.println("ERROR OBTENIENDO total de prestadores");
            e.printStackTrace();
            actual = null;
        } finally {
            s.close();
        }
        return actual;
    }
    
    /**
     * Eliminar una inscripción de la BD
     * @param id
     * @return 
     */
    public boolean eliminar(int id) {
        boolean ok = false;
        Inscripcion inscripcion = null;
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();
        try {
            inscripcion = (Inscripcion) s.load(Inscripcion.class, id);
            Prestador p = inscripcion.getPrestador();
            s.delete(inscripcion);
            p.setInscripcion(null);
            s.update("Prestador", p);
            tx.commit();
            ok = true;
        } catch (Exception e) {
            System.out.println("ERROR BORRANDO INSCRIPCION");
            ok = false;
            tx.rollback();
            e.printStackTrace();
        } finally {
            s.close();
        }
        return ok;
    }
}
