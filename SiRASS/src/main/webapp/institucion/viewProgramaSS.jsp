<%-- 
    Document   : viewProgramaSS
    Created on : 18/09/2012, 05:28:22 PM
    Author     : JL Macías
--%>

<%@page import="skyforge.sirass.model.programass.TipoPrograma"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="skyforge.sirass.model.programass.PoblacionPrograma"%>
<%@page import="skyforge.sirass.model.programass.AlcancePrograma"%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.ActividadPrograma"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/jspf/head.jsp">
            <jsp:param name="title" value="Bienvenido"/>
        </jsp:include>
        <link href="../css/reg.css" rel="stylesheet" />
        <style type="text/css">
            h1 {
                color: #DFDFDF;
                alignment-adjust: middle !important;
            }
        </style>
    </head>
    
    <body>
        <header>
            <jsp:include page="jspf/header.jsp">
                <jsp:param name="active" value="inicio"></jsp:param>
            </jsp:include>
        </header>
        
        <div class="container-fluid">
            <div class="row-fluid">
                <!-- Sidebar
                ================================================== -->
                <jsp:include page="jspf/sidebar.jsp">
                    <jsp:param name="active" value="0"/>
                </jsp:include>
                <%
                    String id = request.getParameter("idPrograma");
                    int idPrograma = Integer.parseInt(id);
                    ProgramaSSDAO progrDAO = new ProgramaSSDAO();
                    ProgramaSS programaSS = new ProgramaSS();
                    programaSS = progrDAO.getByPK(idPrograma);

                    Set<ActividadPrograma> actividades = (Set<ActividadPrograma>) programaSS.getActividad();
                    Iterator it = actividades.iterator();

                    Set<AlcancePrograma> alcances = (Set<AlcancePrograma>) programaSS.getAlcance();
                    Iterator it1 = alcances.iterator();

                    Set<PoblacionPrograma> poblaciones = (Set<PoblacionPrograma>) programaSS.getPoblacion();
                    Iterator it2 = poblaciones.iterator();

                    Set<ResponsablePrograma> responsables = (Set<ResponsablePrograma>) programaSS.getResponsables();
                    Iterator it3 = responsables.iterator();

                    Set<TipoPrograma> tipos = (Set<TipoPrograma>) programaSS.getTipo();
                    Iterator it4 = tipos.iterator();
                %>
                <div class="container bg span8">
                    <div class="contenido">
                        <h1>Programa: </h1>
                        <h2 align="right"><%=programaSS.getNombre()%></h2>
                        <h2 align="right"><%=programaSS.getCvePrograma()%></h2>
                        <h2>Institución: <%=programaSS.getInstitucion()%></h2>
                        <div class="formulario">
                            <div class="tabbable">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#datosPrograma" data-toggle="tab">Datos Programa</a>
                                    </li>
                                    <li>
                                        <a href="#datosProgramaMas" data-toggle="tab">Más Datos Programa</a>
                                    </li>
                                    <li>
                                        <a href="#datosGenerales" data-toggle="tab">Datos Generales</a>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="tab-content">
                            <div class="tab-pane active" id="datosPrograma">
                                <%@include file="../programa/datosPrograma.jsp" %>
                            </div>
                            <div class="tab-pane fade ac" id="datosProgramaMas">
                                <%@include file="../programa/datosProgramaMas.jsp" %>
                            </div>
                            <div class="tab-pane fade ac" id="datosGenerales">
                                <%@include file="../programa/datosGenerales.jsp" %>
                            </div>
                        </div>                
                    </div>
                    <h6>Fecha Creación: <%=programaSS.getCreacion()%></h6>
                    <h6>Ultima Modificación: <%=programaSS.getUltimaModif()%></h6>
                    <h6>Ultima Modificación Por: <%=programaSS.getModificadoPor()%></h6>
                </div>
            </div>
        </div>
    </body>
</html>
