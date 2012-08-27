/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.form.prestador;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.prestador.ControlHoras;
import skyforge.sirass.model.prestador.RegistroHora;

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
        if (this.getVars().get("inscripcion") != null) {
            String data = this.getVars().get("inscripcion")[0];
            try {
                int idInscripcion = Integer.parseInt(data);
                this.controlHoras.setIdInscripcion(idInscripcion);
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
        
        // OBTENER HORAS
        HashSet<RegistroHora> registros = new HashSet<RegistroHora>();
        int counter;
        String fechaStr;
        String horaEntradaStr;
        String horaSalidaStr;
        String horasDiaStr;
        RegistroHora regHora;
        for (counter = 1; counter < 30; counter++) {
            if (this.getVars().get("hEntrada" + counter) != null &&
                this.getVars().get("hSalida" + counter) != null &&
                this.getVars().get("acum" + counter) != null &&
                this.getVars().get("fecha" + counter) != null) {
                fechaStr = this.getVars().get("fecha" + counter)[0];
                horaEntradaStr = this.getVars().get("hEntrada" + counter)[0];
                horaSalidaStr = this.getVars().get("hSalida" + counter)[0];
                horasDiaStr = this.getVars().get("acum" + counter)[0];
                Date fecha;
                Time horaEntrada;
                Time horaSalida;
                Time horas;
                try {
                    regHora = new RegistroHora();
                    regHora.setControlHoras(controlHoras);
                    fecha = this.getDateFormat().parse(fechaStr);
                    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm");
                    horaEntrada = new Time(timeFormat.parse(horaEntradaStr).getTime());
                    horaSalida = new Time(timeFormat.parse(horaSalidaStr).getTime());
                    horas = new Time(timeFormat.parse(horasDiaStr).getTime());

                    regHora.setFecha(fecha);
                    regHora.setHoraEntrada(horaEntrada);
                    regHora.setHoraSalida(horaSalida);
                    regHora.setHorasDia(horas);
                    registros.add(regHora);
                } catch (ParseException ex) {
                    Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, "ERROR EN FECHAS");
                    Logger.getLogger(InscripcionForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        controlHoras.setHoras(registros);
        
        controlHoras.setModificadoPor(modificadoPor);
        return this.controlHoras;
    }
}
