/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import skyforge.sirass.model.prestador.ControlHoras;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.prestador.RegistroHora;
import skyforge.sirass.model.programass.ResponsablePrograma;

/**
 *
 * @author Eder Herget
 */
public class Control_Mensual_De_Horas implements Reporte_Base{
     
    
     public ArrayList<Object>objResumen;
     public Date periodo_reportado_inicial;
     public Date periodo_reportado_final;
     public Set<RegistroHora> horas;
     public Integer horas_acumuladas;
     public Integer suma_de_meses_anteriores;
     public Integer horas_mes;
     public short no_reporte;
     public String no_formato;
     public String url_img;
     public String ap_paterno;
     public String ap_materno;
     public String nombre;
     public String no_control;
     public String escuela;
     public String supervisor;
     public String responsable_servicio_social;
     public boolean firma_digital;
     
     
    
    public Control_Mensual_De_Horas() {
        url_img="Imagen-Encabezado.png";	   
        
    }

    public Control_Mensual_De_Horas(Prestador prestador,Inscripcion inscripcion, String responsable, ControlHoras controlHoras, boolean firma_digital) {
        url_img="Imagen-Encabezado.png";
        no_formato="05";
        no_reporte=controlHoras.getnReporte();
        periodo_reportado_inicial=controlHoras.getFechaInicio(); 
        periodo_reportado_final=controlHoras.getFechaFin();
        ap_paterno=prestador.getaPaterno();
        ap_materno=prestador.getaMaterno();
        nombre=prestador.getNombre();
        no_control=prestador.getnControl();
        escuela=inscripcion.getInstitucion().getNombre();
        if (inscripcion.getPlantel() != null &&
                inscripcion.getPlantel().getNombre() != null) {
            escuela += " - " + inscripcion.getPlantel().getNombre();
        }
        horas_acumuladas=controlHoras.getHorasAcumuladas();
        suma_de_meses_anteriores=controlHoras.getHorasAnteriores();
        horas_mes=controlHoras.getHorasMes();
        supervisor=controlHoras.getSupervisor();
        horas=controlHoras.getHoras();
        this.firma_digital=firma_digital;
//        if(responsablesPrograma.size()>1){
//            responsable_servicio_social="";
//        }else{
//            responsable_servicio_social=responsablesPrograma.iterator().next().getResponsable();}
        responsable_servicio_social = responsable;
        establecerObjResumen();
    }

    public String getAp_materno() {
        return ap_materno;
    }

    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    public String getAp_paterno() {
        return ap_paterno;
    }

    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public boolean getFirma_digital() {
        return firma_digital;
    }

    public void setFirma_digital(boolean firma_digital) {
        this.firma_digital = firma_digital;
    }

    public Set<RegistroHora> getHoras() {
        return horas;
    }

    public void setHoras(Set<RegistroHora> horas) {
        this.horas = horas;
    }

    public Integer getHoras_acumuladas() {
        return horas_acumuladas;
    }

    public void setHoras_acumuladas(Integer horas_acumuladas) {
        this.horas_acumuladas = horas_acumuladas;
    }

    public Integer getHoras_mes() {
        return horas_mes;
    }

    public void setHoras_mes(Integer horas_mes) {
        this.horas_mes = horas_mes;
    }

    public String getNo_control() {
        return no_control;
    }

    public void setNo_control(String no_control) {
        this.no_control = no_control;
    }

    public short getNo_reporte() {
        return no_reporte;
    }

    public void setNo_reporte(short no_reporte) {
        this.no_reporte = no_reporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Object> getObjResumen() {
        return objResumen;
    }

    public void setObjResumen(ArrayList<Object> objResumen) {
        this.objResumen = objResumen;
    }

    public Date getPeriodo_reportado_final() {
        return periodo_reportado_final;
    }

    public void setPeriodo_reportado_final(Date periodo_reportado_final) {
        this.periodo_reportado_final = periodo_reportado_final;
    }

    public Date getPeriodo_reportado_inicial() {
        return periodo_reportado_inicial;
    }

    public void setPeriodo_reportado_inicial(Date periodo_reportado_inicial) {
        this.periodo_reportado_inicial = periodo_reportado_inicial;
    }

    public String getResponsable_servicio_social() {
        return responsable_servicio_social;
    }

    public void setResponsable_servicio_social(String responsable_servicio_social) {
        this.responsable_servicio_social = responsable_servicio_social;
    }

    public Integer getSuma_de_meses_anteriores() {
        return suma_de_meses_anteriores;
    }

    public void setSuma_de_meses_anteriores(Integer suma_de_meses_anteriores) {
        this.suma_de_meses_anteriores = suma_de_meses_anteriores;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public void establecerObjResumen() {//Este se debe llamar al establecer todas los parametros de este objeto.
        objResumen=new ArrayList<Object>();
        objResumen.add(no_reporte);
        objResumen.add(no_control);
        objResumen.add(horas_mes);
        objResumen.add(horas);
    }

}
