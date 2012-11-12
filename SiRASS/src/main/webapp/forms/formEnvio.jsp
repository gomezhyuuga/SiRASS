<%-- 
    Document   : formEnvio
    Created on : 8/10/2012, 05:04:14 PM
    Author     : Jorge Macias
--%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.TipoPrograma"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.programass.tipoProgramaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="tabbable">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#tab1" data-toggle="tab">Datos</a></li>
        <li><a href="#tab2" data-toggle="tab">Caracter&iacute;sticas</a></li>
        <li><a href="#tab3" data-toggle="tab">Perfil Acad&eacute;mico</a></li>
        <li><a href="#tab4" data-toggle="tab">Responsable</a></li>
        <li><a href="#tab5" data-toggle="tab">Observaciones</a></li>
    </ul>
</div>
<div class="tab-content">
    <div class="tab-pane active" id="tab1">
        <div class = "control-group">
            <label class = "control-label" for = "nomProgIns">  Nombre del programa: </label>
            <div class = "controls">
                <input type = "text" id = "nomProgIns" name = "nomProgIns"/>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "objProgIns">  Objetivo General: </label>
            <div class = "controls">
                <textarea class="span5" id="objProgIns" name="objProgIns" placeholder = "Escribe informaci&oacute;n en min. 400 letras"></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "justProgIns">  Justificaci&oacute;n: </label>
            <div class = "controls">
                <textarea class="span5" id = "justProgIns" name = "justProgIns" placeholder = "Escribe informaci&oacute;n en min. 300 letras"></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "desProgIns">  Desarrollo: </label>
            <div class = "controls">
                <textarea class="span5" id = "desProgIns" name = "desProgIns" placeholder = "Escribe informaci&oacute;n en min. 500 letras"></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "recurProgIns">  Recursos: </label>
            <div class = "controls">
                <textarea class="span5" id = "recurProgIns" name = "recurProgIns" placeholder = "Escribe informaci&oacute;n en min. 100 letras"></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "evalProgIns">  Evaluaci&oacute;n: </label>
            <div class = "controls">
                <textarea class="span5" id = "evalProgIns" name = "evalProgIns" placeholder = "Escribe informaci&oacute;n en min. 400 letras"></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "resulProgIns">  Resultados: </label>
            <div class = "controls">
                <textarea class="span5" id = "resulProgIns" name = "resulProgIns" placeholder = "Escribe informaci&oacute;n en min. 300 letras"></textarea>
            </div>
        </div>
        <div class="form-actions">
            <h6>Pulsa en <a href="#" id="nextC">Caracter&iacute;sticas del programa</a> para continuar</h6>
        </div>
    </div>
    <div class="tab-pane" id="tab2">
        <div class = "control-group">
            <label class = "control-label" for = "tipoProgIns"> Tipo: </label>
            <div class = "controls">
                <select name = "tipoProgIns" id = "tipoProgIns" onchange="changeTypeProgram(this)">
                    <% tipoProgramaDAO tipDAO = new tipoProgramaDAO();
                        List<TipoPrograma> type = tipDAO.getAllTypes();
                        int w;
                        w = 1;
                        if (type != null && !type.isEmpty()) {
                            Iterator<TipoPrograma> it = type.iterator();
                            while (it.hasNext()) {
                                TipoPrograma tp = it.next();
                    %>
                    <option value="<%= tp.getIdTipo()%>"><%= tp.getDescripcion()%></option>
                    <%
                                w++;
                            }
                        }
                    %>
                    <option value = "sinRegistro">Otra...</option>
                </select>
                <input type="hidden" name="ultType" id="ultType" value="<%= w%>"/>
            </div>
        </div>
        <div class="control-group hide" id="otroTipo">
            <label for="nombreOtroTipo" class="control-label">Nombre:</label>
            <div class="controls">
                <input type="text" name="nombreOtroTipo"
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
            <label class = "control-label"> Alcance: </label>
            <div class = "controls">
                <select multiple = "multiple" name = "alcanProgIns">
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
            <label class = "control-label"> Poblaci&oacute;n a atender: </label>
            <div class = "controls">
                <select multiple = "multiple" name = "poblaProgIns">
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
            <label class = "control-label"> Lugar de Realizaci&oacute;n: </label>
            <div class = "controls">
                <input type = "text" name = "lugarProgIns" id = "lugarProgIns" placeholder = "Lugar o direcci&oacute;n" />
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label"> D&iacute;as de asistencia: </label>
            <div class = "controls">
                <select multiple = "multiple" name = "diasProgIns">
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
            <label class = "control-label"> Horario: </label>
            <div class = "controls">
                <select name = "horaProgIns" id = "horaProgIns">
                    <option value = "1">Matutino</option>
                    <option value = "2">Vespertino</option>
                    <option value = "3">Mixto</option>
                </select>
            </div>
        </div>
        <div class="form-actions">
            <h6>Pulsa en <a href="#" id="nextP">Perfil Acad&eacute;mico</a> para continuar</h6>
        </div>
    </div>
    <div class="tab-pane" id="tab3">
        <div class="span5" style="text-align: right;">
            <a class="btn btn-primary" id="addLicen">Agregar Licenciatura</a>
            <p style="text-align: left;" class = "help-block"><em> Si quieres agregar otra licenciatura presiona el bot&oacute;n</em></p>
        </div>
        <table class="table table-condensed " id="table-acts">
            <thead>
                <tr>
                    <th>Licenciatura</th>
                    <th>Vacantes</th>
                    <th>Actividades</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody id="rows-acts">
                <tr id="row1">
                    <td>
                        <input class="input-large" type = "text" name = "licenProgIns" id = "licenProgIns" />
                    </td>
                    <td> 
                        <input class="input-mini" type = "text" name = "vacanProgIns" id = "vacanProgIns" />
                    </td>
                    <td>
                        <textarea name = "actProgIns" id = "actProgIns" rows = "5" placeholder = "Escribe 5 actividades como  mínimo" ></textarea>
                    </td>
                    <td><input class="btn btn-mini btn-danger" type="button" id="elim1" onclick="delRowAct(1)" value="Eliminar" /></td>
                </tr>
            </tbody>
        </table>
        <div class="form-actions">
            <h6>Pulsa en <a href="#" id="nextR">Responsable</a> para continuar</h6>
        </div>
    </div>
    <div class="tab-pane" id="tab4">
        <div class="span5" style="text-align: right;">
            <a class="btn btn-primary" id="addRespon">Agregar Responsable</a>
            <p style="text-align: left;" class = "help-block"><em> Si quieres agregar otro responsable presiona el bot&oacute;n</em></p>
        </div>
        <table class="table table-condensed " id="table-acts">
            <thead>
                <tr>
                    <th>Resposable</th>
                    <th>Cargo</th>
                    <th><span class="add-on"><i class="icon-envelope"></i></span>Email</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody id="rows-respo">
                <tr id="rowr1">
                    <td>
                        <input class="input-medium" type = "text" id = "respoIns" name = "respoIns" />
                    </td>
                    <td> 
                        <input class="input-medium" type = "text" id = "cargoRespoIns" name = "cargoRespoIns" />
                    </td>
                    <td>
                        <input class="input-medium" type="text" id="emailInst" name="emailInst" maxlength="30" />
                    </td>
                    <td><input class="btn btn-mini btn-danger" type="button" id="elim1" onclick="delRowRes(1)" value="Eliminar" /></td>
                </tr>
            </tbody>
        </table>
        <div class="form-actions">
            <h6>Pulsa en <a href="#" id="nextO">Observaciones del Programa</a> para continuar</h6>
        </div>
    </div>
    <div class="tab-pane" id="tab5">
        <div class = "control-group">
            <label class = "control-label" for = "obsProgIns">  Observaciones: </label>
            <div class = "controls">
                <textarea class="span5" id = "obsProgIns" name = "obsProgIns" maxlength = "600" placeholder = "Escribe alguna observación, no debe ser necesaria"></textarea>
            </div>
        </div>
        <div class = "form-actions">
            <button type="submit" class = "btn btn-primary btn-large" >Enviar Datos</button>
        </div>
    </div>
</div>