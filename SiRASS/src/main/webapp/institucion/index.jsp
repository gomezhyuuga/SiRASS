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
        misProgramas = prodao.getIdInstitutoByEdo(id, 1);
        misProgramas2 = prodao.getListProgramasByIdEdoCont(id, new CEstado((short) 3));
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
                    <% int tamMisProg = misProgramas.size();
                       int tam2 = misProgramas2.size();
                       int tam3 = misProgramas3.size();
                       
                        if (misProgramas != null && tamMisProg != 0) {%>
                        <div class="page-header">
                            <h1>Bienvenido <%= request.getUserPrincipal().getName() %></h1>
                        </div>
                        <p>Esta es tu lista de programas registrados</p>
                        <%@include file="listProgramaSS.jsp" %>
                    <% }%>
                    <% if (misProgramas == null || tamMisProg == 0) {%>
                        <% if ((misProgramas2 == null || tam2 == 0) && tam3 != 0) {%>
                            <h1>Bienvenido, <%= (String) session.getAttribute("username")%></h1>
                            <p>Actualmente no te han revisado ning&uacute;n programa de Servicio Social, pero si tienes una propuesta más enviala</p>
                            <p><a class="btn btn-large btn-primary " href="./enviarPropuesta.jsp">Enviar propuesta</a></p>
                        <% } %>
                        <% if ((misProgramas3 == null || tam3 == 0) && tam2 != 0) {%>
                            <h1>Bienvenido, <%= (String) session.getAttribute("username")%></h1>
                            <p>Actualmente <%= tam2 %> de tus programas de Servicio Social tienen problemas y es necesario corregir en el apartado <i class = "icon-exclamation-sign"></i>Avisos podrás ver si hay error en tu solicitud y las observaciones</p>
                        <% } %>
                    <%}%>
                    <% if ((misProgramas2 != null && tam2 != 0) && (tam3 != 0 && misProgramas3 != null)) {%>
                            <h1>Bienvenido, <%= (String) session.getAttribute("username")%></h1>
                            <p>Actualmente <%= tam2 %> de tus programas de Servicio Social necesita ser corregido en el apartado <i class = "icon-exclamation-sign"></i>Avisos podrás ver el/los error(s) en las observaciones</p>
                            <p>Y <%= tam2 %> son programas aún o revisados</p>
                        <% } %>
                </div>
            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
