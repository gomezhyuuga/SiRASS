/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.form.prestador;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.prestador.InformeBimensual;

/**
 *
 * @author gomezhyuuga
 */
public class InformeBimensualForm extends Form {

    private InformeBimensual informe;
    Date curDate;
    String modificadoPor;

    public InformeBimensualForm(Map<String, String[]> vars, String modificadoPor) {
        super(vars, true);
        this.informe = null;
        this.modificadoPor = modificadoPor;
        curDate = new Date();
    }

    public InformeBimensual getObject(InformeBimensual obj) {
        if (obj == null) {
            this.informe = new InformeBimensual();
        } else {
            this.informe = obj;
        }

        if (this.getVars().get("inicioPeriodo") != null) {
            String data = this.getVars().get("inicioPeriodo")[0];
            try {
                Date date = this.getDateFormat().parse(data);
                informe.setInicioPeriodo(date);
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "ERROR EN FECHA_INICIO");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get("terminoPeriodo") != null) {
            String data = this.getVars().get("terminoPeriodo")[0];
            try {
                Date date = this.getDateFormat().parse(data);
                informe.setTerminoPeriodo(date);
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "ERROR EN FECHA_INICIO");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get("numReporte") != null) {
            String data = this.getVars().get("numReporte")[0];
            try {
                short numReporte = Short.parseShort(data);
                informe.setNumReporte(numReporte);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO NUM_REPORTE");
            }
        }
        if (this.getVars().get("horasBimestre") != null) {
            String data = this.getVars().get("horasBimestre")[0];
            try {
                int horas = Integer.parseInt(data);
                informe.setHorasBimestre(horas);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO horasBimestre ");
            }
        }
        if (this.getVars().get("horasAcumuladas") != null) {
            String data = this.getVars().get("horasAcumuladas")[0];
            try {
                int horas = Integer.parseInt(data);
                informe.setHorasAcumuladas(horas);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO horasAcumuladas ");
            }
        }
        if (this.getVars().get("actividades") != null) {
            String data = this.getVars().get("actividades")[0];
            informe.setActividades(data);
        }
        if (this.getVars().get("inscripcion") != null) {
            String data = this.getVars().get("inscripcion")[0];
            try {
                int idInscripcion = Integer.parseInt(data);
                informe.setIdInscripcion(idInscripcion);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO inscripcion");
            }
        }

        informe.setModificadoPor(modificadoPor);
        return informe;
    }
}
