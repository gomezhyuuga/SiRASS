<%-- 
    Document   : index
    Created on : 11/12/2012, 07:07:14 PM
    Author     : Jorge Macias
--%>

<%@page import="skyforge.sirass.dao.admin.AdministradorDAO"%>
<%@page import="skyforge.sirass.model.admin.Administrador"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Edita tus datos" />
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
                    <jsp:param name="active" value="gU" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <h1>Gestionar Usuarios</h1>
                    <p>Modifica usuarios del tipo Institución, Prestador y Administrador. Agregalos, eliminalos o actualizarlos</p>
                    <p class="lead">Selecciona una opción para filtrar los usuarios.</p>
                    <ul class="nav nav-pills">
                        <li class="active" id="Adm">
                            <a id="Admin" href="#">Administradores</a>
                        </li>
                        <li id="Pre">
                            <a id="Prestador" href="#">Prestadores</a>
                        </li>
                        <li id="Ins">
                            <a id="Instituto" href="#">Instituciones</a>
                        </li>
                    </ul>
                    <div id="form">
                        <jsp:include page="Administrador/" />
                    </div>
                </div> <!-- end contenido -->
            </div> <!-- end row -->
        </div> <!-- end container -->
        <!-- Footer
            ============================== -->
        <jsp:include page="../../WEB-INF/jspf/footer.jsp" >
            <jsp:param name="form" value="true" />
            <jsp:param name="bootbox" value="true" />
            <jsp:param name="script" value="gAdminProg" />
        </jsp:include>
    </body>
</html>
