<%-- 
    Document   : enviarPropuesta
    Created on : 10-jul-2012, 16:16:52
    Author     : JL Macías
--%>
<%@page import="skyforge.sirass.dao.procesos.ProcessDao"%>
<%@page import="skyforge.sirass.model.procesos.CProcess"%>
<%@page import="skyforge.sirass.model.programass.PoblacionPrograma"%>
<%@page import="skyforge.sirass.model.programass.AlcancePrograma"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="java.util.ArrayList"%>
<%@page import="skyforge.sirass.model.user.Usuario"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.TipoPrograma"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.programass.tipoProgramaDAO"%>
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
                    <jsp:param name="active" value="actualPropues" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <%
                        CProcess p = new CProcess();
                        ProcessDao proDao = new ProcessDao();
                        boolean stat = proDao.exists(p.Vigente, "vigencia");
                        if (stat) {
                    %>
                    <div class="page-header">
                        <h1>Actualizaci&oacute;n de propuesta</h1>
                        <p>Actualiza tu propuesta corrigiendo los datos que te son indicados en el recuadro de observaciones de abajo.</p>
                    </div>
                    <!-- feedback -->
                    <div class="alert alert-block hide alert-error" id="feedback">
                        <a class="close" data-dismiss="alert" href="#">&times;</a>
                        <h4 class="alert-heading">Datos inv&aacute;lidos!</h4>
                        <p>Por favor revisa los campos marcados en <strong>rojo</strong>. Los datos que ingresaste
                            en ellos no son v&aacute;lidos.</p>
                    </div>
                    <form method="post" action="/SiRASS/upPrograma" name="form-actualP" id="form-actualP" class="form-horizontal">
                        <jsp:include page="/forms/formActual.jsp" />
                    </form>
                    <% }
                        if (stat != true) {
                    %>
                    <div class="page-header">
                        <h1>Actualizar programa</h1>
                        <p>La convocatoria de programas no esta vigente por el momento y por lo tanto no podr&aacute; actualizar, espere a la nueva convocatoria.</p>
                    </div> 
                    <% }%>
                </div>
            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/footer.jsp">
            <jsp:param name="datepicker" value="true" />
            <jsp:param name="bootbox" value="true" />
            <jsp:param name="form" value="true" />
            <jsp:param name="script" value="actualP" />
        </jsp:include>
    </body>
</html>