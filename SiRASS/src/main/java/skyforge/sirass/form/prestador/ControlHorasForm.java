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
        String horasMesField = "hrsReporte";
        String minutosMesField = "minsReporte";
        String horasAnterioresField = "hrsAnteriores";
        String minutosAnterioresField = "minsAnteriores";
        String horasAcumField = "hrsAcumuladas";
        String minutosAcumField = "minsAcumulados";
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
        if (this.getVars().get(horasMesField) != null) {
            String data = this.getVars().get(horasMesField)[0];
            try {
                int num = Integer.parseInt(data);
                controlHoras.setHorasMes(num);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO HRS_MES");
            }
        }
        if (this.getVars().get(minutosMesField) != null) {
            String data = this.getVars().get(minutosMesField)[0];
            try {
                short num = Short.parseShort(data);
                controlHoras.setMinutosMes(num);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO MINS_MES");
            }
        }
        if (this.getVars().get(horasAnterioresField) != null) {
            String data = this.getVars().get(horasAnterioresField)[0];
            try {
                int num = Integer.parseInt(data);
                controlHoras.setHorasAnteriores(num);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO HRS_ANTERIORES");
            }
        }
        if (this.getVars().get(minutosAnterioresField) != null) {
            String data = this.getVars().get(minutosAnterioresField)[0];
            try {
                short num = Short.parseShort(data);
                controlHoras.setMinutosAnteriores(num);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO MINS_ANTERIORES");
            }
        }
        if (this.getVars().get(horasAcumField) != null) {
            String data = this.getVars().get(horasAcumField)[0];
            try {
                int num = Integer.parseInt(data);
                controlHoras.setHorasAcumuladas(num);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO HRS_ACUMULADAS");
            }
        }
        if (this.getVars().get(minutosAcumField) != null) {
            String data = this.getVars().get(minutosAcumField)[0];
            try {
                short num = Short.parseShort(data);
                controlHoras.setMinutosAcumulados(num);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO MINS_ACUMULADOS");
            }
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
