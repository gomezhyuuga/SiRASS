<%-- 
    Document   : suspendidos
    Created on : 10-jul-2012, 16:16:52
    Author     : jolmes
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
                    <jsp:param name="active" value="gp" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <div class = "page-header">
                        <h1>Estado de Programas</h1>
                    </div>
                    <p class="lead">Selecciona una opción para filtrar los programas.</p>
                    <div class="breadcrumb">
                        <a class="btn btn-success" href="./"><i class="icon-plus icon-white"></i> Registrados recientemente</a> <span class="divider">/</span>
                        <a class="btn btn-warning active" href="#"><i class="icon-minus icon-white"></i> Solicitud de baja</a> <span class="divider">/</span>
                        <a class="btn btn-danger" href="./bajas.jsp"><i class="icon-remove icon-white"></i> Inactivos</a> <span class="divider">/</span>
                        <a class="btn btn-info" href="./activos.jsp"><i class="icon-question-sign icon-white"></i> Activos</a>
                    </div>
                    <%
                        ProgramaSSDAO pdao = new ProgramaSSDAO();
                    %>
                    <div id="suspendidos">
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
                                        Detalles
                                    </th>
                                    <th style="width: 100px;">
                                        Confirmar
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    while (it2.hasNext()) {
                                        ProgramaSS programaSS = it2.next();
                                %>
                                <tr>
                                    <th>
                                        <%= programaSS.getNombre()%>
                                        <input type="hidden" name="<%= programaSS.getIdPrograma()%>" id="<%= programaSS.getIdPrograma()%>" value="<%= programaSS.getCvePrograma()%>" />
                                    </th>
                                    <th>
                                        <%= programaSS.getInstitucion()%>
                                    </th>
                                    <th>
                                        <a class="btn btn-info btn-mini" href="detalles.jsp?id=<%= programaSS.getIdPrograma()%>">
                                            <i class = "icon-plus-sign icon-white"></i>M&aacute;s
                                        </a>
                                    </th>
                                    <th>
                            <div class="btn-group">
                                <a 
                                    href="#" onclick="inactProg(this)" data-id="<%= programaSS.getIdPrograma()%>" class="btn btn-danger btn-mini">Inactivo
                                </a>
                                <a class="btn btn-danger btn-mini dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="#" onclick="activarProg(this)" data-id="<%= programaSS.getIdPrograma()%>"><i class="icon-plus"></i> Reactivar</a>
                                    </li>
                                </ul>
                            </div>
                            </th>
                            </tr>
                            <% }%>
                            </tbody>
                        </table>
                        <%
                            } else {
                                out.println("<h1>No se ha solicitado baja de programas actualmente</h1>");
                            }
                        %>
                    </div>
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
