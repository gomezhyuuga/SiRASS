<%-- 
    Document   : perfilInst
    Created on : 18/09/2012, 08:13:26 PM
    Author     : Jorge Macias
--%>

<%@page import="skyforge.sirass.model.institucion.CInstitucion"%>
<%@page import="skyforge.sirass.model.institucion.Institucion"%>
<%@page import="skyforge.sirass.model.user.Usuario"%>
<%@page import="skyforge.sirass.dao.institucion.CInstitucionDAO"%>
<%@page import="skyforge.sirass.dao.institucion.PlantelDAO"%>
<%@page import="skyforge.sirass.model.institucion.Plantel"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>
<% UsuarioDAO dao = new UsuarioDAO();
    Plantel plant = new Plantel();
    PlantelDAO pdao = new PlantelDAO();
    CInstitucionDAO cdao = new CInstitucionDAO();
    Usuario user = dao.getByUsername(String.valueOf(session.getAttribute("username")));
    Institucion ins = user.getInstitucion();
    CInstitucion cInstitucion = cdao.getById(ins.getIdCInstitucion());
    String plantel = String.valueOf(user.getInstitucion().getIdPlantel());
    if (plantel != "null") {
        plant = pdao.getPlantelById(Integer.parseInt(plantel));
    }
%>

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
                    <jsp:param name="active" value="gestPerf" />
                </jsp:include>
                <!-- Contenido
                ============================== -->
                <div class="span8 solid">

                    <header>
                        <h1>Configuración de Perfil</h1>
                        <p>Cambia tus datos personales o datos de usuario.</p>
                    </header>
                    <div class ="tabbable">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">Datos de la Unidad</a></li>
                            <li><a href="#tab2" data-toggle="tab">Datos de Usuario</a></li>
                        </ul>
                        <div id="formulario">
                            <form method="post" action ="/SiRASS/actualDate" id="gInstitu" name ="gInstitu" class="form-horizontal">
                                <div class="tab-content">
                                    <div class ="tab-pane active" id="tab1">
                                        <!-- Datos de la Unidad
                                        ================================================== -->
                                        <fieldset>
                                            <legend>Datos de la unidad prestataria</legend>
                                            <input type="hidden" id="idInstituto" name="idInstituto" value ="<%= ins.getIdInstitucion()%>" />
                                            <input type="hidden" id="idR" name ="idR" value ="Institucion" />
                                            <div class="control-group">
                                                <label class="control-label" for="nombreInst">Instituci&oacute;n:</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="nombreInst" value ="<%= cInstitucion.getNombre()%>" name="nombreInst" maxlength="25" />
                                                </div>
                                            </div>
                                            <% if (plantel != "null") {%>
                                            <div class="control-group">
                                                <label class="control-label" for="nombreU">Plantel:</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="nombreU" name="nombreU" value ="<%= plant.getNombre()%>" maxlength="25" />
                                                </div>
                                            </div>
                                            <% }%>
                                            <div class="control-group">
                                                <label class="control-label" for="areaSub">Area/Subdirecci&oacute;n:</label>
                                                <div class="controls">
                                                    <input type="text" readonly="readonly" id="areaSub" value ="<%= ins.getArea()%>" name="areaSub" maxlength="15" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="domiU">Domicilio:</label>
                                                <div class="controls">
                                                    <input type="text" id="domiU" name="domiU" value ="<%= ins.getDomicilio()%>" maxlength="50" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="responU">Responsable:</label>
                                                <div class="controls">
                                                    <input type="text" id="responU" name="responU" value ="<%= ins.getResponsable()%>" maxlength="50" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="cargoResU">Cargo:</label>
                                                <div class="controls">
                                                    <input type="text" id="cargoResU" name="cargoResU" value ="<%= ins.getCargo()%>" maxlength="50" />
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label">Tel&eacute;fono: </label>
                                                <div class="controls">
                                                    <input class = "input-small" type="text" id="telU" name="telU" value ="<%= ins.getTel()%>" maxlength="10" />
                                                    <input class = "input-small" type="text" id="telExtU" name="telExtU" maxlength="7" value ="<%= ins.getTelExt()%>" placeholder = "Ext."/>
                                                </div>
                                            </div>
                                            <div class="control-group">
                                                <label class="control-label" for="emailInst">Email:</label>
                                                <div class="controls">
                                                    <div class="input-prepend">
                                                        <span class="add-on">
                                                            <i class="icon-envelope"></i>
                                                        </span>
                                                    </div><input class="input-large" type="text" id="emailInst" name="emailInst" value ="<%= ins.getEmail()%>" maxlength="30" />
                                                </div>
                                            </div>
                                        </fieldset> <!-- end datos de Unidad -->
                                        <div class="form-actions">
                                            <h6>Pulsa en <a href="#" id="next">Datos de Usuario</a> para continuar</h6>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab2">
                                        <!-- Datos de usuario
                                        ================================================== -->
                                        <!-- usuario -->
                                        <fieldset>
                                            <legend>Informacion de usuario</legend>
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
                                                    <p class="help-block"><em>6-15 caracteres.</em></p>
                                                </div>
                                            </div>					
                                            <div class="control-group">
                                                <label class="control-label" for="passwordVeif">Verifica contrase&ntilde;a:</label>
                                                <div class="controls">
                                                    <input type="password" id="passwordVeif" name="passwordVeif" maxlength="15" placeholder = "verify password" />
                                                </div>
                                            </div>
                                        </fieldset> <!-- end usuario -->
                                        <div class="row center">
                                            <button class="btn btn-primary btn-large input-large" type="submit">Actualizar datos</button>
                                        </div> <!-- end row -->
                                    </div>
                                </div>
                            </form>
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
