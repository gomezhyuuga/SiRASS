<%--
    Document   : instituto
    Created on : 10-jul-2012, 16:16:52
    Author     : jolmes
--%>

<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="skyforge.sirass.model.user.Usuario"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% UsuarioDAO dao = new UsuarioDAO();
    Usuario user = dao.getByUsername(String.valueOf(session.getAttribute("username")));
    List<ProgramaSS> misProgramas = new ArrayList<ProgramaSS>();
    List<ProgramaSS> misProgramas2 = new ArrayList<ProgramaSS>();
    List<ProgramaSS> misProgramas3 = new ArrayList<ProgramaSS>();
    ProgramaSSDAO prodao = new ProgramaSSDAO();
    if (user != null) {
        int id = user.getInstitucion().getIdInstitucion();
        misProgramas = prodao.getListProgramasByEdoSimple(id, new CEstado ((short) 1));
        misProgramas2 = prodao.getListProgramasByIdEdoCont(id, new CEstado((short) 5));
        misProgramas3 = prodao.getListProgramasByIdEdoCont(id, new CEstado((short) 4));
    }
%>

<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Bienvenido Institución" />
        </jsp:include>
    </head>
    <body>
        <!-- Navbar
        ============================== -->
        <jsp:include page="jspf/header.jsp" />
        <!-- Body
        ============================== -->
        <div class="container content">
            <div class="row">
                <!-- Sidebar
                ============================== -->
                <jsp:include page="jspf/sidebar.jsp">
                    <jsp:param name="active" value="home" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <div class="page-header">
                                <h1><strong>Bienvenido</strong>, <small><%= (String) session.getAttribute("username")%></small></h1>
                    </div>
                    <% int tamMisProg = misProgramas.size();
                        int tam2 = misProgramas2.size();
                        int tam3 = misProgramas3.size();
                        String plural,progPlu,rP,pP;
                        plural = "";progPlu = "";rP = "";pP = "";
                        if(tam2>1){ plural = "Necesitan ser corregidos";pP = "tienen problemas";progPlu = "tus programas";}
                        if(tam2 == 1){ plural = "Necesita ser corregido";pP = "tiene problema";progPlu = "tu programa";}
                        if(tam3>1){ progPlu = "tus programas";rP = "han sido revisados";}
                        if(tam3 == 1){ progPlu = "tu programa";rP = "ha sido revisado";}
                        if((tam2 + tam3) > 1 ){ progPlu = "tus programas"; }
                        %>
                    <% if ((misProgramas == null || tamMisProg == 0) || tam3 != 0 || tam2 != 0) {%>
                        <% if ((misProgramas2 == null || tam2 == 0) && tam3 == 0) {%>
                                <p class="lead">¿No haz enviado ning&uacute;n programa de Servicio Social?</p>
                                <p class="lead"><strong>¡Si tienes una propuesta enviala!</strong></p>
                                <p><a class="btn btn-large btn-primary " href="./enviarPropuesta.jsp">Enviar propuesta</a></p>
                        <% }%>
                        <% if ((misProgramas2 == null || tam2 == 0) && tam3 != 0) {%>
                                <p class="lead">Actualmente <%= progPlu %> de Servicio Social no <%= rP %></p>
                                <p><a class="btn btn-large btn-primary " href="./enviarPropuesta.jsp">Enviar propuesta</a></p>
                        <% }%>
                        <% if ((misProgramas3 == null || tam3 == 0) && tam2 != 0) {%>
                                <p>Actualmente <%= progPlu %> de Servicio Social <%= pP %><small>...</small></p>
                                <ul>
                                    <li><p class="text-error">Es necesario corregir <strong><%= tam2%></strong>, y en <small><a href="notifis.jsp"><i class = "icon-exclamation-sign"></i>Avisos</a></small> podrás ver ¿Por qué?</p></li>
                                </ul>
                                 
                        <% }%>
                        <%}%>
                        <% if ((misProgramas2 != null && tam2 != 0) && (tam3 != 0 && misProgramas3 != null)) {%>
                            <p class="lead">Actualmente de <%= progPlu %> de Servicio Social<small>...</small></p>
                            <ul>
                                <li><p class="text-error"><%= plural%> <strong><%= tam2 %></strong>, y en <small><a href="notifis.jsp"><i class = "icon-exclamation-sign"></i>Avisos</a></small> podrás ver los errores</p></li>
                                <li><p class="text-warning">Y aún sin revisión <strong><%= tam3%></strong></p></li>
                            </ul>
                        <% }%>
                        <% if (misProgramas != null && tamMisProg != 0) { %> <%@include file="listProgramaSS.jsp" %> <%}%>
                    </div>
                </div>
            </div>
            <!-- Footer
                ============================== -->
            <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
