/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import skyforge.sirass.model.Dia;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.programass.ProgramaSS;

/**
 *
 * @author Eder Herget
 */
public class Carta_De_Aceptacion implements Reporte_Base{
    public String escuela_de_procedencia;
    public String carrera;
    public String programa_institucional;
    public String responsable_institucional;
    public String cargo;
    public Date fecha_de_inicio;
    public String matricula;
    public String nombre;
    public String clave_de_registro;
    public Date fecha_de_termino;
    public String clave1;
    public String plantel;
    public String area;
    public short horas;
    public String asistencia;
    public Date hora_Entrada;
    public Date hora_Salida;
    public Date fecha;
    public String responsable_ss;
    public String datos_uacm;
    public String sitio_web;
    public String actividad;
    public String url_img;
    public boolean firma_digital;
    public String lugar;
    public String no_formato;
    public ArrayList<Object>objResumen;
    
    
    public Carta_De_Aceptacion(Prestador prestador, Inscripcion inscripcion ,ProgramaSS programaSS ,String actividadesARealizar ,String responsableSS ,boolean firma_digital){
        this.firma_digital=firma_digital;
        no_formato="01";
        clave1=programaSS.getCvePrograma();
        escuela_de_procedencia=inscripcion.getInstitucion().getNombre();
        carrera=inscripcion.getCarrera();
        programa_institucional=inscripcion.getProgramaInst();
        responsable_institucional=inscripcion.getResponsable(); //Responsable institucional
        cargo=inscripcion.getCargoResponsable();
        fecha_de_inicio=inscripcion.getFechaInicio();
        matricula=inscripcion.getMatricula();
        nombre=prestador.getaPaterno()+" "+prestador.getaMaterno()+" "+prestador.getNombre();
        clave_de_registro="";//Clave con la que esta registrado el programa (2012 - 54 / 1 - 835), no usado en el formato
        fecha_de_termino=inscripcion.getFechaFin();
        plantel=inscripcion.getPlantel().getNombre();
        area=programaSS.getArea();
        horas=Reporte.TOTAL_DE_HORAS;
        generarAsistencia(inscripcion.getDias());
        hora_Entrada=inscripcion.gethEntrada();
        hora_Salida=inscripcion.gethSalida();
        fecha=new Date();
        responsable_ss=responsableSS;
        datos_uacm="Av. División del Norte No. 906, Col. Narvarte Poniente, Delegación Benito Juárez, C.P.03020 Conmutador 110702800 ext. 16631 al 16634 y 16671";
        sitio_web="www.uacm.edu.mx";
        actividad=actividadesARealizar;
        lugar="la Ciudad de México D.F.";
        url_img="UACM-icono-1.jpeg";
        establecerObjResumen();
    }
    
    public void generarAsistencia(Set<Dia> dias){//Se forma la cadena que se enviara al reporte, cuando se establecen los datos sin el contructo se tiene que mandar llamar.
        Iterator<Dia> iterator=dias.iterator();
        asistencia="";
        int i=0;
        boolean[] diasSemana=new boolean[7];
        for (boolean b : diasSemana) {b=false;}
        Dia dia=new Dia();
        while(iterator.hasNext()){
            dia=iterator.next();
            if(dia.getDiaSemana().toUpperCase().equals("LUNES")){
                diasSemana[0]=true;
            }else if((dia.getDiaSemana().toUpperCase().equals("MARTES"))){
                diasSemana[1]=true;
            }else if((dia.getDiaSemana().toUpperCase().equals("MIÉRCOLES"))){
                diasSemana[2]=true;
            }else if((dia.getDiaSemana().toUpperCase().equals("JUEVES"))){
                diasSemana[3]=true;
            }else if((dia.getDiaSemana().toUpperCase().equals("VIERNES"))){
                diasSemana[4]=true;
            }else if((dia.getDiaSemana().toUpperCase().equals("SÁBADO"))){
                diasSemana[5]=true;
            }else if((dia.getDiaSemana().toUpperCase().equals("DOMINGO"))){
                diasSemana[6]=true;
            }
        }
        if(diasSemana[0]){    
            i++;
            asistencia=asistencia+"lunes ,";
            if(i==dias.size()){
                asistencia=asistencia.substring(0, asistencia.length()-2);
            }
            if(i==(dias.size()-1)){
                asistencia=asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
        } if(diasSemana[1]){
            i++;
            asistencia=asistencia+"martes ,";
            if(i==dias.size()){
                    asistencia=asistencia.substring(0, asistencia.length()-2);
            }
            if(i==(dias.size()-1)){
                asistencia=asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
            
        } if(diasSemana[2]){
            i++;
            asistencia=asistencia+"miércoles ,";
            if(i==dias.size()){
                asistencia=asistencia.substring(0, asistencia.length()-2);
            }if(i==(dias.size()-1)){
              asistencia=  asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
        } if(diasSemana[3]){
            i++;
            asistencia=asistencia+"jueves ,";
            if(i==dias.size()){
               asistencia= asistencia.substring(0, asistencia.length()-2);
            }if(i==(dias.size()-1)){
              asistencia=  asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
        } if(diasSemana[4]){
            i++;
            asistencia=asistencia+"viernes ,";
            if(i==dias.size()){
           asistencia=     asistencia.substring(0, asistencia.length()-2);
            }if(i==(dias.size()-1)){
              asistencia=  asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
        } if(diasSemana[5]){
            i++;
            asistencia=asistencia+"sábado ,";
            if(i==dias.size()){
             asistencia=   asistencia.substring(0, asistencia.length()-2);
            }if(i==(dias.size()-1)){
             asistencia=   asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
        } if(diasSemana[6]){
            i++;
            asistencia=asistencia+"domingo ,";
            if(i==dias.size()){
             asistencia=   asistencia.substring(0, asistencia.length()-2);
            }if(i==(dias.size()-1)){
             asistencia=   asistencia.substring(0, asistencia.length()-2);
                asistencia=asistencia+" y ";
            }
        }
    }
    
    @Override
    public void establecerObjResumen() {
        objResumen=new ArrayList<Object>();
        objResumen.add(nombre);
        objResumen.add(responsable_institucional);
        objResumen.add(cargo);
        objResumen.add(plantel);
        objResumen.add(escuela_de_procedencia);
        objResumen.add(programa_institucional);
        objResumen.add(fecha_de_inicio);
        objResumen.add(fecha_de_termino);
        objResumen.add(actividad);
                
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave_de_registro() {
        return clave_de_registro;
    }

    public void setClave_de_registro(String clave_de_registro) {
        this.clave_de_registro = clave_de_registro;
    }

    public String getDatos_uacm() {
        return datos_uacm;
    }

    public void setDatos_uacm(String datos_uacm) {
        this.datos_uacm = datos_uacm;
    }

    public String getEscuela_de_procedencia() {
        return escuela_de_procedencia;
    }

    public void setEscuela_de_procedencia(String escuela_de_procedencia) {
        this.escuela_de_procedencia = escuela_de_procedencia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha_de_inicio() {
        return fecha_de_inicio;
    }

    public void setFecha_de_inicio(Date fecha_de_inicio) {
        this.fecha_de_inicio = fecha_de_inicio;
    }

    public Date getFecha_de_termino() {
        return fecha_de_termino;
    }

    public void setFecha_de_termino(Date fecha_de_termino) {
        this.fecha_de_termino = fecha_de_termino;
    }

    public boolean isFirma_digital() {
        return firma_digital;
    }

    public void setFirma_digital(boolean firma_digital) {
        this.firma_digital = firma_digital;
    }

    public Date getHora_Entrada() {
        return hora_Entrada;
    }

    public void setHora_Entrada(Date hora_Entrada) {
        this.hora_Entrada = hora_Entrada;
    }

    public Date getHora_Salida() {
        return hora_Salida;
    }

    public void setHora_Salida(Date hora_Salida) {
        this.hora_Salida = hora_Salida;
    }

    public short getHoras() {
        return horas;
    }

    public void setHoras(short horas) {
        this.horas = horas;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNo_formato() {
        return no_formato;
    }

    public void setNo_formato(String no_formato) {
        this.no_formato = no_formato;
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

    public String getPlantel() {
        return plantel;
    }

    public void setPlantel(String plantel) {
        this.plantel = plantel;
    }

    public String getPrograma_institucional() {
        return programa_institucional;
    }

    public void setPrograma_institucional(String programa_institucional) {
        this.programa_institucional = programa_institucional;
    }

    public String getResponsable_institucional() {
        return responsable_institucional;
    }

    public void setResponsable_institucional(String responsable_institucional) {
        this.responsable_institucional = responsable_institucional;
    }

    public String getResponsable_ss() {
        return responsable_ss;
    }

    public void setResponsable_ss(String responsable_ss) {
        this.responsable_ss = responsable_ss;
    }

    public String getSitio_web() {
        return sitio_web;
    }

    public void setSitio_web(String sitio_web) {
        this.sitio_web = sitio_web;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setUrl_img(String url_img) {
        this.url_img = url_img;
    }
    
}
