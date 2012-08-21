package skyforge.sirass.form.prestador;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import skyforge.sirass.dao.institucion.CInstitucionDAO;
import skyforge.sirass.dao.institucion.PlantelDAO;
import skyforge.sirass.dao.programass.ResponsableProgramaDAO;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.institucion.CInstitucion;
import skyforge.sirass.model.institucion.Plantel;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.TipoInscripcion;
import skyforge.sirass.model.programass.ResponsablePrograma;
import skyforge.sirass.model.Dia;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionForm extends Form {

    Inscripcion inscripcion;
    Date curDate;
    String modificadoPor;
    
    public InscripcionForm(Map<String, String[]> vars, String modificadoPor) {
        super(vars, true);
        this.inscripcion = null;
        this.modificadoPor = modificadoPor;
        curDate = new Date();
    }
    
    /**
     * Genera el objeto con los datos de un map clave-valor (request map)
     *
     * @return Objeto tipo inscripción con los datos encontrados en el map
     */
    public Inscripcion getObject(Inscripcion obj) {
        if (obj == null) {
            this.inscripcion = new Inscripcion();
        } else {
            this.inscripcion = obj;
        }
        if (this.getVars().get("tipoSS") != null) {
            short tipoSS;
            try {
                tipoSS = Short.parseShort(this.getVars().get("tipoSS")[0]);
                inscripcion.setTipo(new TipoInscripcion(tipoSS));
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el tipoSS");
            }
        }
        if (this.getVars().get("difundir") != null) {
            String data = this.getVars().get("difundir")[0];
            if (data.equals("1")) {
                inscripcion.setDifundir(true);
            } else if (data.equals("0")) {
                inscripcion.setDifundir(false);
            }
        }
        if (this.getVars().get("semestre") != null) {
            short semestre;
            try {
                semestre = Short.parseShort(this.getVars().get("semestre")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el semestre");
                semestre = 0;
            }
            inscripcion.setSemestre(semestre);
        }
        if (this.getVars().get("especialidad") != null) {
            String data = this.getVars().get("especialidad")[0];
            inscripcion.setCarrera(data);
        }
        if (this.getVars().get("matricula") != null) {
            String data = this.getVars().get("matricula")[0];
            inscripcion.setMatricula(data);
        }
        if (this.getVars().get("promedio") != null) {
            double promedio;
            try {
                promedio = Double.parseDouble(this.getVars().get("promedio")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el promedio");
                promedio = 0.0;
            }
            inscripcion.setPromedio(promedio);
        }
        if (this.getVars().get("avanceCurso") != null) {
            double avanceCurso;
            try {
                avanceCurso = Double.parseDouble(this.getVars().get("avanceCurso")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el avanceCurso");
                avanceCurso = 0.0;
            }
            inscripcion.setAvanceCursos(avanceCurso);
        }
        if (this.getVars().get("fechaIngreso") != null) {
            int anoIngreso;
            try {
                anoIngreso = Integer.parseInt(this.getVars().get("fechaIngreso")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el fechaIngreso");
                anoIngreso = 0;
            }
            inscripcion.setAnioIngreso(anoIngreso);
        }
        if (this.getVars().get("creditos") != null) {
            Integer creditos;
            try {
                creditos = Integer.parseInt(this.getVars().get("creditos")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el creditos");
                creditos = 0;
            }
            inscripcion.setCreditos(creditos);
        }
        if (this.getVars().get("cursosBasico") != null) {
            Integer cursosBasico;
            try {
                cursosBasico = Integer.parseInt(this.getVars().get("cursosBasico")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el cursosBasico");
                cursosBasico = 0;
            }
            inscripcion.setnCursosBasicos(cursosBasico);
        }
        if (this.getVars().get("cursosSuperior") != null) {
            Integer cursosSuperior;
            try {
                cursosSuperior = Integer.parseInt(this.getVars().get("cursosSuperior")[0]);
            } catch (Exception ex) {
                System.out.println("No se pudo establecer el cursosSuperior");
                cursosSuperior = 0;
            }
            inscripcion.setnCursosSuperior(cursosSuperior);
        }
        if (this.getVars().get("area") != null) {
            String data = this.getVars().get("area")[0];
            inscripcion.setArea(data);
        }
        if (this.getVars().get("programaInst") != null) {
            String data = this.getVars().get("programaInst")[0];
            inscripcion.setProgramaInst(data);
        }
        if (this.getVars().get("cveProgramaInst") != null) {
            String data = this.getVars().get("cveProgramaInst")[0];
            inscripcion.setCveProgramaInst(data);
        }
        if (this.getVars().get("diasAsistencia") != null) {
            String diasVals[] = this.getVars().get("diasAsistencia");
            Set<Dia> dias = new HashSet<Dia>();
            for (String diaVal : diasVals) {
                short idDia;
                try {
                    idDia = Short.parseShort(diaVal);
                    Dia dia = new Dia(idDia);
                    dias.add(dia);
                } catch (Exception ex) {
                    System.out.println("No se pudo establecer el dia");
                }
            }
            inscripcion.setDias(dias);
        }
        if (this.getVars().get("fInicio") != null) {
            String data = this.getVars().get("fInicio")[0];
            try {
                Date fInicio = this.getDateFormat().parse(data);
                inscripcion.setFechaInicio(fInicio);
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "No se pudo convertir la fecha");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get("fTermino") != null) {
            String data = this.getVars().get("fTermino")[0];
            try {
                Date fInicio = this.getDateFormat().parse(data);
                inscripcion.setFechaFin(fInicio);
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "No se pudo convertir la fecha");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get("horaEntrada") != null) {
            String data = this.getVars().get("horaEntrada")[0];
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
                Date horaEntrada = timeFormat.parse(data);
                inscripcion.sethEntrada(new Time(horaEntrada.getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "No se pudo convertir horaEntrada");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get("horaSalida") != null) {
            String data = this.getVars().get("horaSalida")[0];
            try {
                SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
                Date horaSalida = timeFormat.parse(data);
                inscripcion.sethSalida(new Time(horaSalida.getTime()));
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "No se pudo convertir horaSalida");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.modificadoPor != null) {
            inscripcion.setModificadoPor(this.modificadoPor);
        } else {
            inscripcion.setModificadoPor("system");
        }
        // Detección de programa
        if (this.getVars().get("idPrograma") != null &&
            !this.getVars().get("idPrograma").equals("") &&
            !this.getVars().get("idPrograma").equals("0")) {
            String data = this.getVars().get("idPrograma")[0];
            int idPrograma;
            try {
                idPrograma = Integer.parseInt(data);
                inscripcion.setIdPrograma(idPrograma);
                // Obtención de responsable
                ResponsableProgramaDAO responsableProgramaDAO = new ResponsableProgramaDAO();
                ResponsablePrograma responsable = responsableProgramaDAO.getFirstByPrograma(idPrograma);
                inscripcion.setResponsable(responsable.getResponsable());
                inscripcion.setCargoResponsable(responsable.getCargo());
            } catch (Exception ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "No se pudo convertir idPrograma");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            // Obtención de nombre y clave
            if (this.getVars().get("nombrePrograma") != null) {
                inscripcion.setPrograma(this.getVars().get("nombrePrograma")[0]);
            }
            if (this.getVars().get("cvePrograma") != null) {
                inscripcion.setCvePrograma(this.getVars().get("cvePrograma")[0]);
            }
        }
        
        // Detección de institución y plantel
        /*
         * CASOS (OK -> EXISTE)
         * 1) Institución OK | Plantel OK
         *      institucionList -> != "" && != unregistred && != null
         *      plantelList -> != "" && != unregistred && != 0
         * 2) Institución OK | Plantel NO
         *      institucionList -> != "" && != unregistred && != null
         *      plantelList -> != "" && != 0 && == unregistred
         *      nombrePlantel -> != "" && != null
         * 3) Institución OK | Plantel NULL
         *      institucionList -> != "" && != unregistred && != null
         *      plantelList -> != "" && == 0 && != unregistred
         * 4) Institucion NO | Plantel NO
         *      institucionList -> != "" && != null && == unregistred
         *      nombreInstitucion -> != "" && != null
         *      plantelList -> != "" && != 0 && == unregistred
         *      nombrePlantel -> != "" && != null
         * 5) Institucion NO | Plantel NULL
         *      institucionList -> != "" && != null && == unregistred
         *      nombreInstitucion -> != "" && != null
         *      plantelList -> != "" && == 0 && != unregistred
         */
        // Comprobación genérica
        if (this.getVars().get("institucionList") != null &&
            this.getVars().get("plantelList") != null) {
            String institucionList = this.getVars().get("institucionList")[0];
            String plantelList = this.getVars().get("plantelList")[0];
            // Casos 1-3
            if (institucionList != null &&
                !institucionList.equals("") &&
                !institucionList.equals("unregistred")) {
                int idCInstitucion = Integer.parseInt(institucionList);
                inscripcion.setInstitucion(new CInstitucion(idCInstitucion));
                // Caso 1
                if (plantelList != null &&
                    !plantelList.equals("unregistred") &&
                    !plantelList.equals("0")) {
                    int idPlantel = Integer.parseInt(plantelList);
                    inscripcion.setPlantel(new Plantel(idPlantel, idCInstitucion));
                } else if (plantelList != null &&   // Caso 2
                        !plantelList.equals("0") &&
                        plantelList.equals("unregistred") &&
                        this.getVars().get("nombrePlantel") != null) {
                    String nombrePlantel = this.getVars().get("nombrePlantel")[0];
                    int idPlantel = this.registrarPlantel(nombrePlantel, idCInstitucion);
                    inscripcion.setPlantel(new Plantel(idPlantel, idCInstitucion));
                } else if (plantelList != null &&   // Caso 3
                        plantelList.equals("0") &&
                        !plantelList.equals("unregistred")) {
                    inscripcion.setPlantel(null);
                }
            } else if (institucionList != null &&
                !institucionList.equals("") &&
                institucionList.equals("unregistred") &&
                this.getVars().get("nombreInstitucion") != null) {
                // Casos 4-5
                String nombreInstitucion = this.getVars().get("nombreInstitucion")[0];
                int id = this.registrarCInstitucion(nombreInstitucion);
                inscripcion.setInstitucion(new CInstitucion(id));
                
                // Caso 4
                if (plantelList != null && 
                    !plantelList.equals("0") && 
                    plantelList.equals("unregistred") &&
                    this.getVars().get("nombrePlantel") != null) {
                    String nombrePlantel = this.getVars().get("nombrePlantel")[0];
                    int idPlantel = this.registrarPlantel(nombrePlantel, id);
                    inscripcion.setPlantel(new Plantel(idPlantel, id));
                } else if (plantelList != null && 
                    plantelList.equals("0") && 
                    !plantelList.equals("registred")) {
                    inscripcion.setPlantel(null);
                }
            }
        }
        return this.inscripcion;
    }
    
    private int registrarPlantel(String nombrePlantel, int idCInstitucion) {
        PlantelDAO plantelDAO = new PlantelDAO();
        Plantel plantel = new Plantel();
        plantel.setIdCInstitucion(idCInstitucion);
        plantel.setNombre(nombrePlantel);
        plantel.setCreacion(curDate);
        plantel.setUltimaModif(curDate);
        plantel.setModificadoPor(modificadoPor);
        plantelDAO.insert(plantel);
        return plantel.getIdPlantel();
    }
    
    private int registrarCInstitucion(String nombre) {
        CInstitucion cInstitucion = new CInstitucion();
        cInstitucion.setNombre(nombre);
        cInstitucion.setEducativa(true);
        cInstitucion.setCreacion(curDate);
        cInstitucion.setUltimaModif(curDate);
        cInstitucion.setModificadoPor(modificadoPor);
        CInstitucionDAO cInstitucionDAO = new CInstitucionDAO();
        cInstitucionDAO.insert(cInstitucion);
        return cInstitucion.getIdCInstitucion();
    }
}
