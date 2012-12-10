<%-- 
    Document   : enviarPropuesta
    Created on : 10-jul-2012, 16:16:52
    Author     : JL Macías
--%>

<%@page import="skyforge.sirass.dao.procesos.ProcessDao"%>
<%@page import="skyforge.sirass.model.procesos.CProcess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <jsp:param name="active" value="sendPropues" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <%
                        CProcess p = new CProcess();
                        ProcessDao dao = new ProcessDao();
                        boolean stat = dao.exists(p.Vigente, "vigencia");
                        if (stat) {
                    %>
                    <div class="page-header">
                        <h1>Registro de Programa de Servicio Social</h1>
                        <p>Env&iacute;a una propuesta para que tengas prestadores contigo en Servicio Social.</p>
                    </div>
                    <!-- feedback -->
                    <div class="alert alert-block hide alert-error" id="feedback">
                        <a class="close" data-dismiss="alert" href="#">&times;</a>
                        <h4 class="alert-heading">Datos inv&aacute;lidos!</h4>
                        <p>Por favor revisa los campos marcados en <strong>rojo</strong>. Los datos que ingresaste
                            en ellos no son v&aacute;lidos.</p>
                    </div>
                    <form method="post" action="/SiRASS/sendPropuesta" name="form-sendP" id="form-sendP" class="form-horizontal">
                        <jsp:include page="/forms/formEnvio.jsp" />
                    </form>
                    <% }
                        if (stat != true) {
                    %>
                    <div class="page-header">
                        <h1>Registro de Programa de Servicio Social</h1>
                        <p>La convocatoria para registro de programas no esta vigente por el momento, espere a la nueva convocatoria.</p>
                    </div> 
                    <% } %>
                </div>
            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/footer.jsp">
            <jsp:param name="datepicker" value="true" />
            <jsp:param name="bootbox" value="true" />
            <jsp:param name="form" value="true" />
            <jsp:param name="script" value="programas" />
        </jsp:include>
    </body>
</html>
