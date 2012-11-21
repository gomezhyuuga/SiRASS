<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="skyforge.sirass.model.user.Usuario"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% UsuarioDAO dao = new UsuarioDAO();
    Usuario user = dao.getByUsername(String.valueOf(session.getAttribute("username")));
    List<ProgramaSS> misProgramas = new ArrayList<ProgramaSS>();
    List<ProgramaSS> misProgramas2 = new ArrayList<ProgramaSS>();
    List<ProgramaSS> misProgramas5 = new ArrayList<ProgramaSS>();
    List<ProgramaSS> misProgramas6 = new ArrayList<ProgramaSS>();
    ProgramaSSDAO prodao = new ProgramaSSDAO();
    if (user != null) {
        int id = user.getInstitucion().getIdInstitucion();
        misProgramas = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 1));
        misProgramas2 = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 3));
        misProgramas5 = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 4));
        misProgramas6 = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 5));
    }
%>

<!DOCTYPE html>

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
                    <jsp:param name="active" value="actualPropues" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <header>
                        <% if (misProgramas == null && misProgramas2 == null && misProgramas5 == null && misProgramas6 == null) {%>
                        <h1>Actualizar programa</h1>
                        <p>Actualmente no tienes programas</p>
                        <% }%>
                        <% if (misProgramas != null || misProgramas2 != null || misProgramas5 != null || misProgramas6 != null) {%>
                        <h1>Actualizar programa</h1>
                        <p>Selecciona el programa que quieres actualizar presionando el botón <i class="icon-info-sign"></i>
                            Editar</p>
                            <%@include file="actualprog.jsp" %>
                            <% }%>
                    </header>
                </div>

            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
