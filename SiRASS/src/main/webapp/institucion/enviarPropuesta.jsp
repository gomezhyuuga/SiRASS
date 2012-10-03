<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.TipoPrograma"%>
<%@page import="java.util.List"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="skyforge.sirass.dao.programass.tipoProgramaDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
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
                    <header>
                        <h1>Registro de Programa de Servicio Social</h1>
                        <p>Env&iacute;a una propuesta para que tengas prestadores contigo en Servicio Social.</p>
                    </header>
                    <div class ="tabbable">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">Datos del programa</a></li>
                            <li><a href="#tab2" data-toggle="tab">Caracter&iacute;sticas del programa</a></li>
                            <li><a href="#tab3" data-toggle="tab">Perfil Acad&eacute;mico</a></li>
                            <li><a href="#tab4" data-toggle="tab">Responsable</a></li>
                            <li><a href="#tab5" data-toggle="tab">Observaciones del Programa</a></li>
                        </ul>
                        <!-- Formulario
                        ============================================== -->
                        <div id = "formulario">
                            <form method="post" action="#" name = "enviaPropuesta" id = "enviaPropuesta" class = "form-horizontal">
                                <div class ="tab-content">
                                    <!-- Datos del programa y caracteristicas
                                    ====================================== -->
                                    <div class="tab-pane active" id="tab1">
                                        <!-- datos del programa -->
                                        <div class = "row well">
                                            <fieldset>
                                                <legend>Datos del programa</legend>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "nomProgIns">  Nombre del programa: </label>
                                                    <div class = "controls">
                                                        <input type = "text" id = "nomProgIns" name = "nomProgIns" maxlength = "100" />
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "objProgIns">  Objetivo General: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id="objProgIns" name="objProgIns" maxlength = "400" placeholder = "Escribe informaci&oacute;n en max. 400 letras"></textarea>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "justProgIns">  Justificaci&oacute;n: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id = "justProgIns" name = "justProgIns" maxlength = "300" placeholder = "Escribe informaci&oacute;n en max. 300 letras"></textarea>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "desProgIns">  Desarrollo: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id = "desProgIns" name = "desProgIns" maxlength = "500" placeholder = "Escribe informaci&oacute;n en max. 500 letras"></textarea>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "recurProgIns">  Recursos: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id = "recurProgIns" name = "recurProgIns" maxlength = "100" placeholder = "Escribe informaci&oacute;n en max. 100 letras"></textarea>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "evalProgIns">  Evaluaci&oacute;n: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id = "evalProgIns" name = "evalProgIns" maxlength = "400" placeholder = "Escribe informaci&oacute;n en max. 400 letras"></textarea>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "resulProgIns">  Resultados: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id = "resulProgIns" name = "resulProgIns" maxlength = "300" placeholder = "Escribe informaci&oacute;n en max. 300 letras"></textarea>
                                                    </div>
                                                </div>
                                            </fieldset><!-- fin datos del programa -->
                                        </div>
                                        <div class="form-actions">
                                            <h6>Pulsa en <a href="#" id="next">Caracter&iacute;sticas del programa</a> para continuar</h6>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab2">
                                        <!-- caracteristicas del programa -->
                                        <div class = "row well">
                                            <fieldset>
                                                <legend>Caracter&iacute;sticas del programa</legend>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "tipoProgIns"> Tipo: </label>
                                                    <div class = "controls">
                                                        <select name = "tipoProgIns" id = "tipoProgIns" onchange="changeTypeProgram(this)">
                                                            <% tipoProgramaDAO tipDAO = new tipoProgramaDAO();
                                                                List<TipoPrograma> type = tipDAO.getAllTypes();
                                                                if (type != null && !type.isEmpty()) {
                                                                    Iterator<TipoPrograma> it = type.iterator();
                                                                    while (it.hasNext()) {
                                                                        TipoPrograma tp = it.next();
                                                            %>
                                                            <option value="<%= tp.getIdTipo()%>"><%= tp.getDescripcion()%></option>
                                                            <%}
                                                                }
                                                            %>
                                                            <option value = "sinRegistro">Otra...</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="control-group hide" id="otroTipo">
                                                    <label for="nombreOtroTipo" class="control-label">Nombre:</label>
                                                    <div class="controls">
                                                        <input type="text" id="nombreOtroTipo" name="nombreOtroTipo"
                                                               placeholder="Nombre del Tipo no existente" class="input-xlarge" maxlength="45" />
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "duraProgIns"> Duraci&oacute;n: </label>
                                                    <div class = "controls">
                                                        <select name = "duraProgIns" id = "duraProgIns" onchange="changeTypeProgram(this)">
                                                            <option value="1">Indeterminado</option>
                                                            <option value="2">Determinado</option>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="control-group hide" id="fechaDeterminado">
                                                    <label class="control-label" for="vencimiento">Fecha de Vencimiento:</label>
                                                    <div class="controls">
                                                        <input type="text" name="vencimiento" id="vencimiento" class="input-small center" />
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "alcanProgIns"> Alcance: </label>
                                                    <div class = "controls">
                                                        <select multiple = "multiAlcan" name = "alcanProgIns" id = "alcanProgIns">
                                                            <option value = "1">Zona Rural</option>
                                                            <option value = "2">Zona Urbana</option>
                                                            <option value = "3">Estatal</option>
                                                            <option value = "4">Nacional</option>
                                                            <option value = "5">Regional</option>
                                                        </select>
                                                        <p class="help-block"><em>Para seleccionar múltiples Alcances mantén presionada la tecla <b>Ctrl</b> (o <b>Cmd</b> en Mac) y selecciona los demás.</em></p>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "poblaProgIns"> Poblaci&oacute;n a atender: </label>
                                                    <div class = "controls">
                                                        <select multiple = "multiPobla" name = "poblaProgIns" id = "poblaProgIns">
                                                            <option value = "1">Marginada</option>
                                                            <option value = "2">No marginada</option>
                                                            <option value = "3">J&oacute;venes</option>
                                                            <option value = "4">Adultos</option>
                                                            <option value = "5">Ni&ntilde;os</option>
                                                            <option value = "6">Tercera edad</option>
                                                        </select>
                                                        <p class="help-block"><em>Para seleccionar múltiples datos de población mantén presionada la tecla <b>Ctrl</b> (o <b>Cmd</b> en Mac) y selecciona los demás.</em></p>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "lugarProgIns"> Lugar de Realizaci&oacute;n: </label>
                                                    <div class = "controls">
                                                        <input type = "text" name = "lugarProgIns" id = "lugarProgIns" maxlength = "45" placeholder = "Lugar o direcci&oacute;n" />
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "diasProgIns"> D&iacute;as de asistencia: </label>
                                                    <div class = "controls">
                                                        <select multiple = "multiPobla" name = "diasProgIns" id = "diasProgIns">
                                                            <option value = "1">Lunes</option>
                                                            <option value = "2">Martes</option>
                                                            <option value = "3">Mi&eacute;rcoles</option>
                                                            <option value = "4">Jueves</option>
                                                            <option value = "5">Viernes</option>
                                                            <option value = "6">S&aacute;bado</option>
                                                            <option value = "7">Domingo</option>
                                                        </select>
                                                        <p class="help-block"><em>Para seleccionar múltiples días mantén presionada la tecla <b>Ctrl</b> (o <b>Cmd</b> en Mac) y selecciona los demás.</em></p>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "horaProgIns"> Horario: </label>
                                                    <div class = "controls">
                                                        <select name = "horaProgIns" id = "horaProgIns">
                                                            <option value = "1">Matutino</option>
                                                            <option value = "2">Vespertino</option>
                                                            <option value = "3">Mixto</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </fieldset><!-- fin caracteristicas del programa -->
                                        </div>
                                        <div class="form-actions">
                                            <h6>Pulsa en <a href="#" id="next">Perfil Acad&eacute;mico</a> para continuar</h6>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab3">
                                        <!-- Perfil academico -->
                                        <div class = "row well">
                                            <fieldset>
                                                <legend>Perfil Acad&eacute;mico</legend>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "actProgIns"> Actividades: </label>
                                                    <div class = "controls">
                                                        <textarea name = "actProgIns" id = "actProgIns" rows = "5" maxlength = "400" placeholder = "Write 5 activities how min in MAX 400 chars" ></textarea>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "licenProgIns"> Licenciatura: </label>
                                                    <div class = "controls">
                                                        <input type = "text" name = "licenProgIns" id = "licenProgIns" maxlength ="50" />
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "vacanProgIns"> No. Vacantes: </label>
                                                    <div class = "controls">
                                                        <input class="input-small" type = "text" name = "vacanProgIns" id = "vacanProgIns" maxlength ="3" />
                                                    </div>
                                                </div>
                                                <!--<button class = "btn btn-info" type = "button"><i class="icon-plus icon-white"></i> Agregar</button>
                                                <p class = "help-block"><em> Si quieres agregar otra licenciatura presiona el bot&oacute;n</em></p>-->
                                            </fieldset> <!-- Fin perfil Academico -->
                                        </div>
                                        <div class="form-actions">
                                            <h6>Pulsa en <a href="#" id="next">Responsable</a> para continuar</h6>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab4">
                                        <div class="row well">
                                            <fieldset>
                                                <legend>Responsable del programa</legend>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "respoIns"> Responsable </label>
                                                    <div class = "controls">
                                                        <input type = "text" id = "respoIns" name = "respoIns" maxlength = "100"/>
                                                    </div>
                                                </div>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "cargoRespoIns"> Cargo: </label>
                                                    <div class = "controls">
                                                        <input type = "text" id = "cargoRespoIns" name = "cargoRespoIns" maxlength = "100"/>
                                                    </div>
                                                </div>
                                                <div class="control-group">
                                                    <label class="control-label" for="emailInst">Email:</label>
                                                    <div class="controls">
                                                        <div class="input-prepend">
                                                            <span class="add-on">
                                                                <i class="icon-envelope"></i>
                                                            </span>
                                                        </div><input class="input-large" type="text" id="emailInst" name="emailInst" maxlength="30" />
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                        <div class="form-actions">
                                            <h6>Pulsa en <a href="#" id="next">Observaciones del Programa</a> para continuar</h6>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="tab5">
                                        <div class="row well">
                                            <fieldset>
                                                <legend>Observaciones sobre el programa</legend>
                                                <div class = "control-group">
                                                    <label class = "control-label" for = "obsProgIns">  Observaciones: </label>
                                                    <div class = "controls">
                                                        <textarea class="span5" id = "obsProgIns" name = "obsProgIns" maxlength = "600" placeholder = "write information about this in max 600 chars"></textarea>
                                                    </div>
                                                </div>
                                            </fieldset>
                                        </div>
                                        <!-- botón de accion -->
                                        <div class = "form-actions">
                                            <a href="#" onclick="enviarPropuesta(this)" class = "btn btn-primary btn-large" >Enviar Datos</a>
                                        </div>
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
        <jsp:include page="/WEB-INF/jspf/footer.jsp">
            <jsp:param name="datepicker" value="true" />
            <jsp:param name="bootbox" value="true" />
            <jsp:param name="form" value="true" />
            <jsp:param name="script" value="programas" />
        </jsp:include>
    </body>
</html>
