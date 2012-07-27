<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Bienvenido Administrador" />
        </jsp:include>
    </head>
    <body>
        <!-- Navbar
        ============================== -->
        <jsp:include page="../jspf/header.jsp" />
        <!-- Body
        ============================== -->
        <div class="container content">
            <div class="row">
                <!-- Sidebar
                ============================== -->
                <jsp:include page="../jspf/sidebar.jsp">
                    <jsp:param name="active" value="home" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <div class = "page-header">
                        <h1>Estado de Programas</h1>
                    </div>
                    <ul class="breadcrumb">
                        <li>
                            <a class="btn btn-success" href="#enEspera" data-toggle="tab"><i class="icon-plus icon-white"></i> Registrados recientemente</a> <span class="divider">/</span>
                        </li>
                        <li>
                            <a class="btn btn-warning" href="#suspendidos" data-toggle="tab"><i class="icon-minus icon-white"></i> Solicitud de baja</a> <span class="divider">/</span>
                        </li>
                        <li>
                            <a class="btn btn-danger" href="#inactivos" data-toggle="tab"><i class="icon-remove icon-white"></i> Inactivos</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="tab-pane active" id="enEspera">
                            <%
                                ProgramaSSDAO pdao = new ProgramaSSDAO();
                                List<ProgramaSS> list = pdao.getListProgramasByEdo(new CEstado((short) 4));
                                if (list != null && !list.isEmpty()) {
                                    Iterator<ProgramaSS> it = list.iterator();
                            %>
                            <table class = "table table-striped table-condensed">
                                <thead>
                                    <tr>
                                        <th>
                                            Nombre
                                        </th>
                                        <th>
                                            Instituci&oacute;n
                                        </th>
                                        <th>
                                            Tel&eacute;fono
                                        </th>
                                        <th>
                                            Detalles
                                        </th>
                                        <th>
                                            Confirmar
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        while(it.hasNext()){
                                            ProgramaSS programaSS = it.next();
                                    %>
                                    <tr>
                                        <th>
                                            <%= programaSS.getNombre() %>
                                        </th>
                                        <th>
                                            <%= programaSS.getInstitucion() %>
                                        </th>
                                        <th>
                                            <%= programaSS.getTel() %>
                                        </th>
                                        <th>
                                            <a class="btn btn-info btn-mini" target="_blank" href="detalles.jsp?id=<%= programaSS.getIdPrograma() %>">
                                                <i class = "icon-plus-sign icon-white"></i>
                                                M&aacute;s
                                            </a>
                                        </th>
                                        <th>
                                            <a class="btn btn-success btn-mini">
                                                <i class = "icon-ok-sign icon-white"></i>
                                                Activar
                                            </a>
                                        </th>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                            <%
                                }else{
                                    out.println("<h1>No se han registrado Programas actualmente</h1>");
                                }
                            %>
                        </div>
                        <div class="tab-pane" id="suspendidos">
                            <%
                                List<ProgramaSS> list2 = pdao.getListProgramasByEdo(new CEstado((short) 3));
                                if (list2 != null && !list2.isEmpty()) {
                                    Iterator<ProgramaSS> it2 = list2.iterator();
                            %>
                            <table class = "table table-striped table-condensed">
                                <thead>
                                    <tr>
                                        <th>
                                            Nombre
                                        </th>
                                        <th>
                                            Instituci&oacute;n
                                        </th>
                                        <th>
                                            Tel&eacute;fono
                                        </th>
                                        <th>
                                            Detalles
                                        </th>
                                        <th>
                                            Confirmar
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        while(it2.hasNext()){
                                            ProgramaSS programaSS = it2.next();
                                    %>
                                    <tr>
                                        <th>
                                            <%= programaSS.getNombre() %>
                                        </th>
                                        <th>
                                            <%= programaSS.getInstitucion() %>
                                        </th>
                                        <th>
                                            <%= programaSS.getTel() %>
                                        </th>
                                        <th>
                                            <a class="btn btn-info btn-mini" target="_blank" href="detalles.jsp?id=<%= programaSS.getIdPrograma() %>">
                                                <i class = "icon-plus-sign icon-white"></i>
                                                M&aacute;s
                                            </a>
                                        </th>
                                        <th>
                                            <a class="btn btn-danger btn-mini">
                                                <i class = "icon-remove-sign icon-white"></i>
                                                Inactivo
                                            </a>
                                        </th>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                            <%
                                }else{
                                    out.println("<h1>No se ha solicitado baja de programas actualmente</h1>");
                                }
                            %>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
