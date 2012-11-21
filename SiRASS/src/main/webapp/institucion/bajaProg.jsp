<%-- 
    Document   : bajaProg
    Created on : 18/11/2012, 04:14:37 PM
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
    List<ProgramaSS> misProgramas = new ArrayList<ProgramaSS>();
    ProgramaSSDAO prodao = new ProgramaSSDAO();
    if (user != null) {
        int id = user.getInstitucion().getIdInstitucion();
        misProgramas = prodao.getListProgramasByEdoSimple(id, new CEstado((short) 1));
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
                    <jsp:param name="active" value="bajaPropues" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <header>
                        <% int tamaMisProg = misProgramas.size();
                            if (misProgramas != null && tamaMisProg != 0) {%>
                        <h1>Solicitud de baja de programa</h1>
                        <p>Selecciona el programa del que quieras solicitar la baja presionando el botón <i class="icon-remove"></i>
                            Baja</p>
                        <h1 class="titleEsp">Lista de Programas</h1>
                        <hr>
                        <table class="table table-striped table-bordered">
                            <tr>
                                <th>Nombre Programa:</th>
                                <th>Area:</th>
                                <th>Plazas</th>
                                <th>Vacantes</th>
                                <th>Ocupadas</th>
                                <th>Acci&oacute;n</th>
                            </tr>
                            <%for (ProgramaSS p : misProgramas) {%>
                            <tr>
                                <% String cve = p.getCvePrograma();
                                    if (cve == "" && cve.equals(null)) {
                                        cve = "Sin clave";
                                    }%>
                                <td ><%=p.getNombre()%></td>
                                <td ><%=p.getArea()%></td>
                                <td class="center"><%=p.getPlazas()%></td>
                                <td class="center"><%=p.getVacantes()%></td>
                                <td class="center"><%=p.getOcupadas()%></td>
                                <td>
                                    <input type="hidden" id="<%= p.getIdPrograma() %>" name="<%= p.getIdPrograma() %>" value="<%= cve %>" />
                                    <a class="btn btn-small btn-danger" href="#"
                                       onclick="suspendProg(this)" data-id="<%= p.getIdPrograma() %>">
                                        <i class="icon-remove icon-white"></i>
                                        Baja
                                    </a>
                                </td>
                            </tr>
                            <% }%>
                        </table>
                        <% }%>
                        <% if (tamaMisProg == 0 || misProgramas == null) {%>
                        <h1>Actualizar programa</h1>
                        <p>Actualmente no tienes programas</p>
                        <% }%>
                    </header>
                </div>

            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="/WEB-INF/jspf/footer.jsp">
            <jsp:param name="form" value="true" />
            <jsp:param name="bootbox" value="true" />
        </jsp:include>
    </body>
</html>

