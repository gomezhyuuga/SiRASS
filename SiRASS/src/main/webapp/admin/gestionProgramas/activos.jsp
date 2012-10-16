<%-- 
    Document   : index
    Created on : 10-jul-2012, 16:16:52
    Author     : jolmes
--%>
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
                        <a class="btn btn-success btn-small" href="./"><i class="icon-plus icon-white"></i> Registrados</a> <span class="divider">/</span>
                        <a class="btn btn-warning btn-small" href="./suspendidos.jsp"><i class="icon-minus icon-white"></i> Solicitud de baja</a> <span class="divider">/</span>
                        <a class="btn btn-danger btn-small" href="./bajas.jsp"><i class="icon-remove icon-white"></i> Inactivos</a> <span class="divider">/</span>
                        <a class="btn btn-info btn-small active" href="#"><i class="icon-question-sign icon-white"></i> Activos</a><span class="divider">/</span>
                        <a class="btn btn-inverse btn-small" href="./vigencia.jsp"><i class="icon-exclamation-sign icon-white"></i> Vigencia</a>
                    </div>
                    <div class="page-header">
                        <p>Selecciona el tipo de filtro</p>
                        <div id="tipoUsuario" class="centerDiv center" style="width: 205px;">
                            <h4>Tipo de programa</h4>
                            <div class="btn-group" style="display: inline-block;" data-toggle="buttons-radio">
                                <button class="btn btn-large btn-inverse active" id="internoA">Interno</button>
                                <button class="btn btn-large btn-inverse" id="externoA">Externo</button>
                            </div>
                        </div>
                    </div>
                    <form method="post" action="#" name="form-prog" id="form-prog" class="form-horizontal">
                        <jsp:include page="../forms/actsInter.jsp" />
                    </form>
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
