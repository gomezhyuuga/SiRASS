<%--  
    Document   : instituto
    Created on : 10-jul-2012, 16:16:52
    Author     : jolmes
--%>

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
    ProgramaSSDAO prodao = new ProgramaSSDAO();
    if (user != null) {
        int id = user.getInstitucion().getIdInstitucion();
        misProgramas = prodao.getIdInstitutoByEdo(id, 1);
    }
%>

<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Bienvenido Administrador" />
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
                        if (misProgramas != null && tamMisProg != 0) {%>
                        <div class="page-header">
                            <h1>Bienvenido <%= request.getUserPrincipal().getName() %></h1>
                        </div>
                        <p>Esta es tu lista de programas registrados</p>
                        <%@include file="listProgramaSS.jsp" %>
                    <% }%>
                    <% if (misProgramas == null || tamMisProg == 0) {%>
                    <h1>Bienvenido, <%= (String) session.getAttribute("username")%></h1>
                    <p>Actualmente no tienes inscrito ning&uacute;n programa de Servicio Social.</p>
                    <p><a class="btn btn-large btn-primary " href="./enviarPropuesta.jsp">Inscribir un programa</a></p>
                    <% }%>
                </div>
            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
