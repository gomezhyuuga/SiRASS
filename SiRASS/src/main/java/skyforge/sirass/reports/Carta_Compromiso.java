/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;

import java.util.ArrayList;
import java.util.Date;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.programass.ProgramaSS;

/**
 *
 * @author Eder Herget
 */
public class Carta_Compromiso implements Reporte_Base {
    public String url_img;
    public String nombre;
    public String ap_materno;
    public String domicilioDepRec;
    public String calle;
    public String numero;
    public String colonia;
    public String delegacion;
    public String cPostal;
    public String telCasa;
    public String telCel;
    public String email;
    public String ap_paterno;
    public String colegio;
    public String plantel;
    public String carrera;
    public String matricula;
    public short semestre;
    public String dependenciaRec;
    public String calleDepRec;
    public String numeroDepRec;
    public String coloniaDepRec;
    public String delegacionDepRec;
    public String cPostalDepRec;
    public String responsable_Programa;
    public Date fechaInicio;
    public Date fechaFin;
    public String ubicacion;
    public Date fechaHoy;
    public boolean firma_digital;
    public String no_formato;
    public boolean autorizacionDatos;
    public String escolaridadRespSDP;
    public String nombreRespSDP;
    public String ubicacionOIP;
    public String telefonoOIP;
    public String extensionOIP;
    public String horarioAtOIP;
    public String emailOIP;
    public ArrayList<Object>objResumen;
    
    public Carta_Compromiso(Prestador prestador, Inscripcion inscripcion,ProgramaSS programaSS, String gradoDeEstudio, String responsableSS, boolean firma_digital){//En grado de estudio va una cadena de texto de la forma "la Licenciada", "el Ingeniero", etc.
        url_img="Imagen-Encabezado.png";
        nombre=prestador.getNombre();
        ap_paterno=prestador.getaPaterno();
        ap_materno=prestador.getaMaterno();
        calle=prestador.getdCalle();
        numero=prestador.getdNumExt();
        colonia=prestador.getdColonia();
        delegacion=prestador.getdDelegacion();
        cPostal=prestador.getdCP();
        telCasa=prestador.getTelCasa();
        telCel=prestador.getTelCel();
        email=prestador.getEmail();
        colegio=inscripcion.getInstitucion().getNombre();
        plantel=inscripcion.getPlantel().getNombre();
        carrera=inscripcion.getCarrera();
        matricula=inscripcion.getMatricula();
        semestre=inscripcion.getSemestre();
        dependenciaRec=programaSS.getInstitucion();
        domicilioDepRec=programaSS.getDomicilio();
        calleDepRec="";
        numeroDepRec="";
        coloniaDepRec="";
        delegacionDepRec="";
        cPostalDepRec="";
        responsable_Programa=inscripcion.getResponsable();
        fechaInicio=inscripcion.getFechaInicio();
        fechaFin=inscripcion.getFechaFin();
        ubicacion="la ciudad de México Distrito Federal";
        fechaHoy=new Date();
        this.firma_digital=firma_digital;
        no_formato="01";
        autorizacionDatos=inscripcion.isDifundir();
        escolaridadRespSDP=gradoDeEstudio;
        nombreRespSDP=responsableSS;
        ubicacionOIP="Avenida Fray Servando Teresa de Mier número 92, cuarto piso, colonia Centro, código postal 06080, delegación Cuauhtémoc, Distrito Federal";
        telefonoOIP="5134-9804";
        extensionOIP="11412";
        horarioAtOIP="de 9:00 a 15:00 horas";
        emailOIP="oipuacm@uacm.edu.mx";
        
        
        establecerObjResumen();
    }
    
    public void establecerObjResumen() {
        objResumen=new ArrayList<Object>();
        objResumen.add(nombre);
        objResumen.add(ap_paterno);
        objResumen.add(ap_materno);
        objResumen.add(matricula);
        objResumen.add("Carta_Compromiso");
        
        
    }
    
}
