<%-- 
    Document   : vigencia
    Created on : 15-oct-2012, 16:16:52
    Author     : Jorge Macías
--%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="skyforge.sirass.model.programass.CategoriaPrograma"%>
<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
                    <div class=" breadcrumb form-actions">
                        <a class="btn btn-success btn-small" href="./activos.jsp"><i class="icon-plus icon-white"></i> Registrados</a> <span class="divider">/</span>
                        <a class="btn btn-warning btn-small" href="./suspendidos.jsp"><i class="icon-minus icon-white"></i> Solicitud de baja</a> <span class="divider">/</span>
                        <a class="btn btn-danger btn-small" href="./bajas.jsp"><i class="icon-remove icon-white"></i> Inactivos</a> <span class="divider">/</span>
                        <a class="btn btn-info btn-small" href="./activos.jsp"><i class="icon-question-sign icon-white"></i> Activos</a><span class="divider">/</span>
                        <a class="btn btn-inverse btn-small active" href="#"><i class="icon-exclamation-sign icon-white"></i> Vigencia</a>
                    </div>
                    <div class="vigenciaProg">
                        <%
                            ProgramaSSDAO pdao = new ProgramaSSDAO();
                            List<ProgramaSS> list3 = pdao.getListProgramasByVigencia();
                            if (list3 != null && !list3.isEmpty()) {
                                Iterator<ProgramaSS> it3 = list3.iterator();
                        %>
                        <table class = "table table-striped table-condensed">
                            <thead>
                                <tr>
                                    <th>
                                        Clave
                                    </th>
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
                                    while (it3.hasNext()) {
                                        ProgramaSS programaSS = it3.next();
                                %>
                                <tr>
                                    <th>
                                        <%= programaSS.getCvePrograma()%>
                                    </th>
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
                                        <a href="#" onclick="suspendProg(this)" data-id="<%= programaSS.getIdPrograma()%>"
                                           class="btn btn-danger btn-mini">Suspender
                                        </a>
                                    </th>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                        <%
                            } else {
                                out.println("<h1>Actualmente no hay programas cerca de terminar su vigencia</h1>");
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
            <jsp:param name="script" value="gAdminProg" />
        </jsp:include>
    </body>
</html>
