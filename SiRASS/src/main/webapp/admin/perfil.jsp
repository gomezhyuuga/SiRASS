<%@page import="skyforge.sirass.model.admin.Administrador"%>
<%@page import="skyforge.sirass.dao.admin.AdministradorDAO"%>
<%@page import="skyforge.sirass.model.user.Usuario"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>

<%
    UsuarioDAO udao = new UsuarioDAO();
    AdministradorDAO adao = new AdministradorDAO();
    Usuario user = udao.getByUsername(String.valueOf(session.getAttribute("username")));
    Administrador admin = user.getAdministrador();
    int idAdmin = admin.getIdAdmin();
    admin = adao.getAdministradorByPK(idAdmin);

%>
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
        <jsp:include page="jspf/header.jsp" />
        <!-- Body
        ============================== -->
        <div class="container content">
            <div class="row">
                <!-- Sidebar
                ============================== -->
                <jsp:include page="jspf/sidebar.jsp">
                    <jsp:param name="active" value="gperfil" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <div class="page-header">
                        <h1>Edita tus datos personales</h1>
                    </div>
                    <!-- feedback -->
                    <div class="alert alert-block hide alert-error" id="feedback">
                        <a class="close" data-dismiss="alert" href="#">&times;</a>
                        <h4 class="alert-heading">Datos inv&aacute;lidos!</h4>
                        <p>Por favor revisa los campos marcados en <strong>rojo</strong>. Los datos que ingresaste
                            en ellos no son v&aacute;lidos.</p>
                    </div>
                    <div class ="tabbable">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">Datos de Administrador</a></li>
                            <li><a href="#tab2" data-toggle="tab">Datos de Usuario</a></li>
                        </ul>
                        <div id="formulario">
                            <form method="post" action="/SiRASS/actualDate" id="gAdmin" name="gAdmin" class="form-horizontal">
                                <div class="tab-content">
                                    <div class="tab-pane active" id="tab1">
                                        <fieldset>
                                            <legend>Datos de Admin</legend>
                                            <input type="hidden" id="idAdmin" name="idAdmin" value="<%= admin.getIdAdmin()%>" />
                                            <input type="hidden" id="idR" name="idR" value="Administrador" />
                                            <div class="control-group">
                                                <label class="control-label" for="nombre">Nombre</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="nombre" name="nombre" value="<%= admin.getNombre()%>" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="apPaterno">Apellido Paterno</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="apPaterno" name="aPaterno" value="<%= admin.getaPaterno()%>" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="aMaterno">Apellido Materno</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="aMaterno" name="aMaterno" value="<%= admin.getaMaterno()%>" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="fNac">Fecha de Nacimiento</label>
                                                <div class="controls">
                                                    <input type="text" class="input-small" readonly="readonly" id="fNac" name="fNac" value="<%= admin.getNacimiento()%>" />
                                                </div>
                                            </div>
                                                <% 
                                                    String chM = "",chF = "";
                                                    if(admin.getSexo() == 'M'){
                                                        chM = "checked";
                                                    }if(admin.getSexo() == 'F'){
                                                        chF = "checked";
                                                    }
                                                %>
                                            <div class="control-group">
                                                <label class="control-label" for="sexo">Sexo:</label>
                                                <div class="controls">
                                                    <label class="radio inline">
                                                        <input type="radio" id="sexo" onchange="false" name="sexo" value="M" <%= chM %> />
                                                        Masculino
                                                    </label>
                                                    <label class="radio inline">
                                                        <input type="radio" name="sexo" onchange="false" value="F" <%= chF %>/>
                                                        Femenino
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="cargo">Cargo</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="cargo" name="cargo" value="<%= admin.getCargo()%>" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="email">Email:</label>
                                                <div class="controls">
                                                    <div class="input-prepend">
                                                        <span class="add-on">
                                                            <i class="icon-envelope"></i>
                                                        </span>
                                                    </div><input class="input-large" type="text" id="email" name="email" value ="<%= admin.getEmail()%>" maxlength="30" />
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="form-actions">
                                            <h6>Pulsa en <a href="#" id="next">Datos de Usuario</a> para continuar</h6>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab2">
                                        <fieldset>
                                            <legend>Datos de Usuario</legend>
                                            <div class="control-group">
                                                <label class="control-label" for="usuario">Nombre de usuario:</label>
                                                <div class="controls">
                                                    <input type="text" id="usuario" name="usuario" readonly="readonly" maxlength="15" value="<%= session.getAttribute("username")%>" />
                                                    <p class="help-block"><em>6-15 caracteres.</em></p>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="password">Nueva Contrase&ntilde;a:</label>
                                                <div class="controls">
                                                    <input type="password" id="npassword" name="npassword" maxlength="15" placeholder="password" />
                                                    <p class="help-block"><em>6-16 caracteres.</em></p>
                                                </div>
                                            </div>					
                                            <div class="control-group">
                                                <label class="control-label" for="passwordVeif">Verifica contrase&ntilde;a:</label>
                                                <div class="controls">
                                                    <input type="password" id="passwordVeif" name="passwordVeif" maxlength="15" placeholder = "verify password" />
                                                </div>
                                            </div>
                                        </fieldset>
                                        <div class="row center">
                                            <button class="btn btn-primary btn-large input-large" type="submit">Actualizar datos</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div> <!-- end contenido -->
            </div> <!-- end row -->
        </div> <!-- end container -->
        <!-- Footer
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/footer.jsp">
            <jsp:param name="bootbox" value="true" />
            <jsp:param name="form" value="true" />
            <jsp:param name="script" value="perfil" />
        </jsp:include>
    </body>
</html>