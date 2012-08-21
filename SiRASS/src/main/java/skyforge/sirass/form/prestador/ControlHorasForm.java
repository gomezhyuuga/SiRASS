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
import skyforge.sirass.model.prestador.ControlHoras;

/**
 *
 * @author gomezhyuuga
 */
public class ControlHorasForm extends Form {
    
    private ControlHoras controlHoras;
    Date curDate;
    String modificadoPor;
    
    public ControlHorasForm(Map<String, String[]> vars, String modificadoPor) {
        super(vars, true);
        this.controlHoras = null;
        this.modificadoPor = modificadoPor;
        curDate = new Date();
    }
    
    public ControlHoras getObject(ControlHoras obj) {
        if (obj == null) {
            this.controlHoras = new ControlHoras();
        } else {
            this.controlHoras = obj;
        }
        String nReporteField = "nReporte";
        String supervisorField = "supervisor";
        String fInicioField = "fInicio";
        String fTerminoField = "fTermino";
        if (this.getVars().get(nReporteField) != null) {
            String data = this.getVars().get(nReporteField)[0];
            short nReporte = 1;
            try {
                nReporte = Short.parseShort(data);
                this.controlHoras.setnReporte(nReporte);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO NUM_REPORTE");
            }
        }
        if (this.getVars().get(supervisorField) != null) {
            String data = this.getVars().get(supervisorField)[0];
            this.controlHoras.setSupervisor(data);
        }
        if (this.getVars().get(fInicioField) != null) {
            String data = this.getVars().get(fInicioField)[0];
            try {
                Date fInicio = this.getDateFormat().parse(data);
                this.controlHoras.setFechaInicio(fInicio);
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "ERROR EN FECHA_INICIO");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get(fTerminoField) != null) {
            String data = this.getVars().get(fTerminoField)[0];
            try {
                Date fInicio = this.getDateFormat().parse(data);
                this.controlHoras.setFechaFin(fInicio);
            } catch (ParseException ex) {
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "ERROR EN FECHA_TERMINO");
                Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        controlHoras.setModificadoPor(modificadoPor);
        return this.controlHoras;
    }
}
