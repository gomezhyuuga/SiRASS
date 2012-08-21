<%-- 
    Document   : test
    Created on : 10-may-2012, 15:16:43
    Author     : Eder Herget
--%>




<%@page import="skyforge.sirass.reports.Reporte"%>
<%@page import="skyforge.sirass.reports.Reporte"%>
<%@page import="skyforge.sirass.reports.Control_Mensual_De_Horas"%>
<%@page import="skyforge.sirass.reports.Carta_De_Aceptacion"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="skyforge.sirass.model.prestador.RegistroHora"%>
<%@page import="skyforge.sirass.model.prestador.ControlHoras"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.Dia"%>
<%@page import="java.sql.Time"%>
<%@page import="java.util.Date"%>
<%@page import="skyforge.sirass.model.institucion.CInstitucion"%>
<%@page import="skyforge.sirass.model.institucion.Plantel"%>
<%@page import="skyforge.sirass.model.prestador.Prestador"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page import="java.io.OutputStream"%>




<%
Prestador prestador=new Prestador();
prestador.setaPaterno("González");
prestador.setaMaterno("Ruiz");
prestador.setNombre("Eder");
prestador.setnControl("SS-E-0660");

Inscripcion inscripcion=new Inscripcion();
CInstitucion cInstitucion=new CInstitucion();
cInstitucion.setNombre("Univeridad Nacional Autonoma De México");
inscripcion.setInstitucion(cInstitucion);
Plantel plantel=new Plantel();
plantel.setNombre("CECyT No 9");
inscripcion.setPlantel(plantel);
inscripcion.setResponsable("DRA. Gloria Villegas Moreno");
inscripcion.setCargoResponsable("Directora");
inscripcion.setFechaInicio(new Date(112,3,16));
inscripcion.setMatricula("306219234");
inscripcion.setCarrera("Filosofia");
inscripcion.setFechaFin(new Date(112,9,23));
inscripcion.setProgramaInst("Multidisiplinario de servicio social en apoyo en a las actividades academico administrativas de la UACM");
inscripcion.sethEntrada(new Time(8,0,0));
inscripcion.sethSalida(new Time(12,0,0));
Dia lunes=new Dia();
lunes.setDiaSemana("lunes");
lunes.setIdDia((short)1);
Dia martes =new Dia();
martes.setDiaSemana("martes");
martes.setIdDia((short)2);
Dia miercoles=new Dia();
miercoles.setIdDia((short)3);
miercoles.setDiaSemana("Miércoles");
Dia jueves=new Dia();
jueves.setDiaSemana("jueves");
Dia viernes=new Dia();
    viernes.setDiaSemana("viernes");
Dia sabado=new Dia();
sabado.setDiaSemana("sábado");
Dia domingo=new Dia();
domingo.setDiaSemana("domingo");

Set<Dia> sDia=new HashSet<Dia>(0);
sDia.add(lunes);
sDia.add(martes);
sDia.add(miercoles);
sDia.add(jueves);
sDia.add(viernes);
sDia.add(sabado)  ;      
sDia.add(domingo);
inscripcion.setDias(sDia);

Set<ResponsablePrograma> responsablesPrograma=new HashSet<ResponsablePrograma>(0);
ResponsablePrograma res1=new ResponsablePrograma();
res1.setResponsable("REsponsable");
responsablesPrograma.add(res1);
ResponsablePrograma res2=new ResponsablePrograma();
res2.setResponsable("REsponsable");
responsablesPrograma.add(res2);

ControlHoras controlHoras=new ControlHoras();
controlHoras.setFechaInicio(new Date(112, 3, 12));
controlHoras.setFechaFin(new Date(112, 11, 14));
controlHoras.setnReporte((short)1);
controlHoras.setHorasAcumuladas(120);
controlHoras.setHorasAnteriores(123);
controlHoras.setHorasMes(88);
controlHoras.setSupervisor("Supervisor");

Set<RegistroHora> registro =new HashSet<RegistroHora>(0);
RegistroHora registroh1=new RegistroHora();
registroh1.setFecha(new Date( 112,11,2));
        registroh1.setHoraEntrada(new Time(3, 2, 1));
        registroh1.setHoraSalida(new Time(4, 5, 10));
        registroh1.setHorasDia(new Time(4,3,2));
        registroh1.setIdRegistroHora(1);
 registro.add(registroh1);
 controlHoras.setHoras(registro);


 ProgramaSS programaSS=new ProgramaSS();
 programaSS.setCvePrograma("UACM/CA/SS/CA-013/2012");
 programaSS.setArea("Colegio De Humanidades Y Ciencias");
 
/** Aqui termina la creación de objetos . **/
 //------------------------------------------------------

response.setContentType("application/pdf");
response.setHeader("Content-Disposition","Attachment;filename= "+Reporte.Control_Mensual_De_Horas);//Ponemos el tipo de documento   
response.reset();//Para que se vea en el explorador

 
//Para Generar un control mensual de mhoras

Control_Mensual_De_Horas controldehoras=new Control_Mensual_De_Horas(prestador, inscripcion, responsablesPrograma, controlHoras,true);
Reporte r=new Reporte(request.getRealPath("Archivos/"));        
r.establecerDatos(Reporte.Control_Mensual_De_Horas, controldehoras);
r.generar(response.getOutputStream());

 
//Para generar una carta de aceptacion

/*Carta_De_Aceptacion cartadeaceptacion=new Carta_De_Aceptacion(prestador, inscripcion, programaSS, "Apoyo a la investigacion y recopilacion de biografia par elaboracion de articulo",  "Martha Tera", true);
    Reporte reporte=new Reporte(request.getRealPath("Archivos"));
    reporte.establecerDatos(Reporte.Carta_De_Aceptacion, cartadeaceptacion);
    reporte.generar(response.getOutputStream());*/

 
 
//Codigo para verificar la firma digital
//Signature sig=new Signature(request.getRealPath("Archivos").concat("/"));
//sig.sendObject(controldehoras.objResumen);
//System.out.println(sig.isAuthentic("48.44.2.20.82.-74.-68.-92.30.37.-86.71.-98.-3.-21.-115.-96.33.-121.5.-25.-36.-69.-45.2.20.105.-107.124.12.-45.79.-87.-41.108.-125.33.-89.-60.-76.-54.10.-103.89.-29.31"));

%>
