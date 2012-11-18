<%-- 
    Document   : notifis
    Created on : 18/11/2012, 03:24:00 PM
    Author     : Jorge Macias
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
    List<ProgramaSS> misProgramas = new ArrayList<ProgramaSS>(), misProgramas2 = new ArrayList<ProgramaSS>();
    ProgramaSSDAO prodao = new ProgramaSSDAO();
    if (user != null) {
        int id = user.getInstitucion().getIdInstitucion();
        misProgramas = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 5));
        misProgramas2 = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 2));
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
                    <jsp:param name="active" value="avisosPropues" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <header>
                        <h1>Avisos de programa</h1>
                        <% int tamaMisProg = misProgramas.size();
                            if (misProgramas != null && tamaMisProg != 0) {%>
                        <p>Programa(s) detectados con anomalías y con observaciones hechas por el administrador</p>
                        <%@include file="actualprog.jsp" %>
                        <% }%>
                        <% if (tamaMisProg == 0 || misProgramas == null) {%>
                        <p>Actualmente no tienes programas con observaciones hechas</p>
                        <% }%>
                        <% int tamaMisProg2 = misProgramas2.size();
                            if (misProgramas2 != null && tamaMisProg2 != 0) {%>
                        <h1 class="titleEsp">Lista de Programas</h1>
                        <p>Programa(s) dados de baja por el administrador debido a solicitud de baja</p>
                        <hr>
                        <table class="table table-striped table-bordered">
                            <tr>
                                <th>Nombre Programa:</th>
                                <th>Area:</th>
                            </tr>
                            <%for (ProgramaSS p2 : misProgramas2) {%>
                            <tr>
                                <td ><%=p2.getNombre()%></td>
                                <td ><%=p2.getArea()%></td>
                            </tr>
                            <% }%>
                        </table>
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

