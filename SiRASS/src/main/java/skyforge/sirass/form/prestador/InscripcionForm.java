package skyforge.sirass.form.prestador;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.user.Dia;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionForm extends Form {

    Inscripcion inscripcion;
    
    public InscripcionForm(Map<String, String[]> vars, boolean dateFormat) {
        super(vars, dateFormat);
        this.inscripcion = null;
    }
    
    /**
     * Genera el objeto con los datos de un map clave-valor (request map)
     *
     * @return Objeto tipo inscripción con los datos encontrados en el map
     */
    public Inscripcion getObject() {
        this.inscripcion = new Inscripcion();
        if (this.getVars().get("semestre") != null) {
            short semestre;
            try {
                semestre = Short.parseShort(this.getVars().get("dDelegacion")[0]);
            } catch (NumberFormatException ex) {
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
            } catch (NumberFormatException ex) {
                System.out.println("No se pudo establecer el promedio");
                promedio = 0.0;
            }
            inscripcion.setPromedio(promedio);
        }
        if (this.getVars().get("avanceCurso") != null) {
            double avanceCurso;
            try {
                avanceCurso = Double.parseDouble(this.getVars().get("avanceCurso")[0]);
            } catch (NumberFormatException ex) {
                System.out.println("No se pudo establecer el avanceCurso");
                avanceCurso = 0.0;
            }
            inscripcion.setAvanceCursos(avanceCurso);
        }
        if (this.getVars().get("fechaIngreso") != null) {
            int anoIngreso;
            try {
                anoIngreso = Integer.parseInt(this.getVars().get("fechaIngreso")[0]);
            } catch (NumberFormatException ex) {
                System.out.println("No se pudo establecer el fechaIngreso");
                anoIngreso = 0;
            }
            inscripcion.setAnioIngreso(anoIngreso);
        }
        if (this.getVars().get("creditos") != null) {
            short creditos;
            try {
                creditos = Short.parseShort(this.getVars().get("creditos")[0]);
            } catch (NumberFormatException ex) {
                System.out.println("No se pudo establecer el creditos");
                creditos = 0;
            }
            inscripcion.setCreditos(creditos);
        }
        if (this.getVars().get("cursosBasico") != null) {
            short cursosBasico;
            try {
                cursosBasico = Short.parseShort(this.getVars().get("cursosBasico")[0]);
            } catch (NumberFormatException ex) {
                System.out.println("No se pudo establecer el cursosBasico");
                cursosBasico = 0;
            }
            inscripcion.setnCursosBasicos(cursosBasico);
        }
        if (this.getVars().get("cursosSuperior") != null) {
            short cursosSuperior;
            try {
                cursosSuperior = Short.parseShort(this.getVars().get("cursosSuperior")[0]);
            } catch (NumberFormatException ex) {
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
                } catch (NumberFormatException ex) {
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
        return this.inscripcion;
    }
    
}
