<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.ActividadPrograma"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
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

                <%
                    String id = request.getParameter("idPrograma");
                    int idPrograma = Integer.parseInt(id);
                    ProgramaSSDAO progrDAO = new ProgramaSSDAO();
                    ProgramaSS programaSS = new ProgramaSS();
                    programaSS = progrDAO.getByPK(idPrograma);

                    Set<ActividadPrograma> actividades = (Set<ActividadPrograma>) programaSS.getActividad();
                    Iterator it = actividades.iterator();

                    Set<ResponsablePrograma> responsables = (Set<ResponsablePrograma>) programaSS.getResponsables();
                    Iterator it3 = responsables.iterator();
                %>

                <!-- Contenido
                ============================== -->
                <div class="span8 solid">
                    <div id = "contenido">
                        <header>
                            <h1>Actualizaci&oacute;n de Programa de Servicio Social</h1>
                        </header>
                        <div class ="tabbable">
                            <ul class="nav nav-tabs">
                                <li class="active"><a href="#tab1" data-toggle="tab">Datos del programa</a></li>
                                <li><a href="#tab3" data-toggle="tab">Perfil Acad&eacute;mico</a></li>
                                <li><a href="#tab4" data-toggle="tab">Responsable</a></li>
                                <li><a href="#tab5" data-toggle="tab">Observaciones del Programa</a></li>
                            </ul>
                            <!-- Formulario
                            ============================================== -->
                            <div id = "formulario">
                                <form method="post" action="/SiRASS/upPrograma" name = "editPropuesta" id = "editPropuesta" class = "form-horizontal">
                                    <div class ="tab-content">
                                        <!-- Datos del programa y caracteristicas
                                        ====================================== -->
                                        <input type="hidden" id="idPrograma" name="idPrograma" value ="<%= id%>" />
                                        <input type="hidden" id="usuario" name="usuario" value ="<%= session.getAttribute("username")%>" />
                                        <div class="tab-pane" id="tab4">
                                            <div class="row well">
                                                <fieldset>
                                                    <legend>Responsable del programa</legend>
                                                    <% int j = 0;
                                                        while (it3.hasNext()) {
                                                            j++;
                                                            ResponsablePrograma resp = (ResponsablePrograma) it3.next();
                                                    %>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "respoIns"> Responsable </label>
                                                        <div class = "controls">
                                                            <input type = "text" name = "respoIns" id = "respoIns" value  ="<%=resp.getResponsable()%>" maxlength = "100"/>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "cargoRespoIns"> Cargo: </label>
                                                        <div class = "controls">
                                                            <input type = "text" name = "cargoRespoIns" id = "cargoRespoIns" value="<%= resp.getCargo()%>" maxlength = "100"/>
                                                        </div>
                                                    </div>
                                                    <div class="control-group">
                                                        <label class="control-label" for="emailInst">Email:</label>
                                                        <div class="controls">
                                                            <div class="input-prepend">
                                                                <span class="add-on">
                                                                    <i class="icon-envelope"></i>
                                                                </span>
                                                            </div><input class="input-large" type="text" name = "emailInst" id = "emailInst" value ="<%= resp.getEmail()%>" maxlength="30" />
                                                        </div>
                                                    </div>
                                                    <% }%>
                                                    <input type="hidden" name="ultimoRes" id ="ultimoRes" value ="<%= j%>" />
                                                </fieldset>
                                                
                                            </div>
                                            <div class="form-actions">
                                                <h6>Pulsa en <a href="#" id="next">Observaciones del Programa</a> para continuar</h6>
                                            </div>                                        
                                        </div>
                                        <div class="tab-pane active" id="tab1">
                                            <!-- datos del programa -->
                                            <div class = "row well">
                                                <fieldset>
                                                    <legend>Datos del programa</legend>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "nomProgIns">  Nombre del programa: </label>
                                                        <div class = "controls">
                                                            <input class="span5" type = "text" id = "nomProgIns" name = "nomProgIns" value ="<%= programaSS.getNombre()%>" maxlength = "100" />
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "objProgIns">  Objetivo General: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" id="objProgIns" name="objProgIns" maxlength = "400" placeholder = "write information about the objective in max 400 chars"><%= programaSS.getObjGeneral()%></textarea>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "justProgIns">  Justificaci&oacute;n: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" readonly ="readonly"  id = "justProgIns" name = "justProgIns" maxlength = "300" placeholder = "write information about this in max 300 chars"><%= programaSS.getJustificacion()%></textarea>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "desProgIns">  Desarrollo: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" id = "desProgIns" name = "desProgIns" maxlength = "500" placeholder = "write information about this in max 500 chars"><%= programaSS.getDesarrollo()%></textarea>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "recurProgIns">  Recursos: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" readonly ="readonly" id = "recurProgIns" name = "recurProgIns" maxlength = "100" placeholder = "write information about this in max 100 chars"><%= programaSS.getRecursos()%></textarea>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "evalProgIns">  Evaluaci&oacute;n: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" readonly ="readonly" id = "evalProgIns" name = "evalProgIns" maxlength = "400" placeholder = "write information about this in max 400 chars"><%= programaSS.getEvaluacion()%></textarea>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "resulProgIns">  Resultados: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" readonly ="readonly" id = "resulProgIns" name = "resulProgIns" maxlength = "300" placeholder = "write information about this in max 300 chars"><%= programaSS.getResultados()%></textarea>
                                                        </div>
                                                    </div>
                                                </fieldset><!-- fin datos del programa -->
                                                <div class="form-actions">
                                                    <h6>Pulsa en <a href="#" id="next">Perfil Acad&eacute;mico</a> para continuar</h6>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="tab-pane" id="tab3">
                                            <!-- Perfil academico -->
                                            <div class = "row well">
                                                <fieldset>
                                                    <legend>Perfil Acad&eacute;mico</legend>
                                                    <% int i = 0;
                                                        while (it.hasNext()) {
                                                            i++;
                                                            ActividadPrograma act = (ActividadPrograma) it.next();
                                                            String licen = act.getLicenciatura();
                                                    %>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "licenProg"> Licenciatura: </label>
                                                        <div class = "controls">
                                                            <input type ="text" name = "licenProg" id = "licenProg" value="<%= licen%>" />
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "actsProg"> Actividades: </label>
                                                        <div class = "controls">
                                                            <textarea name = "actsProg" id = "actsProg" rows = "5" maxlength = "400" placeholder = "Write 5 activities how min in MAX 400 chars" ><%= act.getActividad()%></textarea>
                                                        </div>
                                                    </div>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "vacanProg"> No. Vacantes: </label>
                                                        <div class = "controls">
                                                            <input class="input-small" type = "text" name = "vacanProg" id = "vacanProg" value="<%= act.getnSolicitados()%>" maxlength ="3" />
                                                        </div>
                                                    </div>
                                                    <% }%>
                                                    <input type="hidden" name="ultimaLicen" value="<%= i%>" />
                                                    <!--<button class = "btn btn-info" type = "button"><i class="icon-plus icon-white"></i> Agregar</button>
                                                    <p class = "help-block"><em> Si quieres agregar otra licenciatura presiona el bot&oacute;n</em></p>-->
                                                </fieldset> <!-- Fin perfil Academico -->
                                            </div>
                                            <div class="form-actions">
                                                <h6>Pulsa en <a href="#" id="next">Responsable</a> para continuar</h6>
                                            </div>
                                        </div>
                                        <div class="tab-pane" id="tab5">
                                            <div class="row well">
                                                <fieldset>
                                                    <legend>Observaciones sobre el programa</legend>
                                                    <div class = "control-group">
                                                        <label class = "control-label" for = "obsProgIns">  Observaciones: </label>
                                                        <div class = "controls">
                                                            <textarea class="span5" id = "obsProgIns" name = "obsProgIns" maxlength = "600" placeholder = "write information about this in max 600 chars"><%= programaSS.getObservaciones()%></textarea>
                                                        </div>
                                                    </div>
                                                </fieldset>
                                            </div>
                                            <div class = "form-actions">
                                                <button class = "btn btn-primary btn-large" type = "submit">Enviar Datos</button>
                                                <button class = "btn btn-large" type ="reset">Limpiar campos</button>
                                            </div>                                            
                                        </div>
                                </form>
                            </div>
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
