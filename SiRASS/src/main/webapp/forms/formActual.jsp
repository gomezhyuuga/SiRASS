<%-- 
    Document   : formActual
    Created on : 25/10/2012, 07:59:51 PM
    Author     : Jorge Macias
--%>
<%@page import="skyforge.sirass.model.programass.ActividadPrograma"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="skyforge.sirass.model.Dia"%>
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

<%
    int idprog;
    idprog = 0;
    ProgramaSS misProgramas = new ProgramaSS();
    ProgramaSSDAO prodao = new ProgramaSSDAO();
    idprog = Integer.parseInt(request.getParameter("idPrograma"));
    if (idprog != 0) {
        misProgramas = prodao.getByPK(idprog);
    }
%>
<textarea name="observaciones" readonly id="observaciones" maxlength="300" class="span8" rows="4" placeholder="Escribe alguna observaci&oacute;n"><%= misProgramas.getNotas() %></textarea>
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
        <input type="hidden" name="idPrograma" id="idPrograma" value="<%= idprog %>"/>
        <input type="hidden" name="cvePrograma" id="cvePrograma" value="<%= misProgramas.getCvePrograma() %>"/>
        <div class = "control-group">
            <label class = "control-label" for = "nomProgIns">  Nombre del programa: </label>
            <div class = "controls">
                <input type = "text" id = "nomProgIns" name = "nomProgIns" value="<%= misProgramas.getNombre()%>" />
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "objProgIns">  Objetivo General: </label>
            <div class = "controls">
                <textarea class="span5" id="objProgIns" name="objProgIns"><%= misProgramas.getObjGeneral()%></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "justProgIns">  Justificaci&oacute;n: </label>
            <div class = "controls">
                <textarea class="span5" id = "justProgIns" name = "justProgIns"><%= misProgramas.getJustificacion()%></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "desProgIns">  Desarrollo: </label>
            <div class = "controls">
                <textarea class="span5" id = "desProgIns" name = "desProgIns"><%= misProgramas.getDesarrollo()%></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "recurProgIns">  Recursos: </label>
            <div class = "controls">
                <textarea class="span5" id = "recurProgIns" name = "recurProgIns"><%= misProgramas.getRecursos()%></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "evalProgIns">  Evaluaci&oacute;n: </label>
            <div class = "controls">
                <textarea class="span5" id = "evalProgIns" name = "evalProgIns"><%= misProgramas.getEvaluacion()%></textarea>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label" for = "resulProgIns">  Resultados: </label>
            <div class = "controls">
                <textarea class="span5" id = "resulProgIns" name = "resulProgIns"><%= misProgramas.getResultados()%></textarea>
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
                    <%
                        Set<TipoPrograma> typeNow = (Set<TipoPrograma>) misProgramas.getTipo();
                        Iterator it4 = typeNow.iterator();
                        int tipoActual, value;
                        tipoActual = value = 0;
                        while (it4.hasNext()) {
                            TipoPrograma tip = (TipoPrograma) it4.next();
                            tipoActual = tip.getIdTipo();
                            value = tipoActual;
                        }
                        tipoProgramaDAO tipDAO = new tipoProgramaDAO();
                        List<TipoPrograma> type = tipDAO.getAllTypes();
                        int w;
                        w = 1;
                        if (type != null && !type.isEmpty()) {
                            Iterator<TipoPrograma> it = type.iterator();
                            while (it.hasNext()) {
                                TipoPrograma tp = it.next();
                                if (value == tp.getIdTipo()) {
                    %>
                    <option selected="selected" value="<%= tp.getIdTipo()%>"><%= tp.getDescripcion()%></option>
                    <%} else {%>
                    <option value="<%= tp.getIdTipo()%>"><%= tp.getDescripcion()%></option>
                    <%
                                }
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
                    <% String deter, indeter;
                        deter = indeter = "";
                        if (misProgramas.getTiempo().getIdTiempo() == 1) {
                            indeter = "selected";
                        } else {
                            deter = "selected";
                        }
                    %>
                    <option value="1" <%= indeter%>>Indeterminado</option>
                    <option value="2" <%= deter%>>Determinado</option>
                </select>
            </div>
        </div>
        <div class="control-group hide" id="fechaDeterminado">
            <label class="control-label" for="vencimiento">Fecha de Vencimiento:</label>
            <div class="controls">
                <input type="text" name="vencimiento" id="vencimiento" class="input-small center" value="<%= misProgramas.getFechaTiempo()%>"/>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label"> Alcance: </label>
            <div class = "controls">
                <select multiple = "multiple" name = "alcanProgIns">
                    <%
                        String active[] = new String[5];
                        Set<AlcancePrograma> alcances = (Set<AlcancePrograma>) misProgramas.getAlcance();
                        Iterator it1 = alcances.iterator();
                        while (it1.hasNext()) {
                            AlcancePrograma al = (AlcancePrograma) it1.next();
                            if (al.getIdAlcance() == 1) {
                                active[0] = "selected";
                            }
                            if (al.getIdAlcance() == 2) {
                                active[1] = "selected";
                            }
                            if (al.getIdAlcance() == 3) {
                                active[2] = "selected";
                            }
                            if (al.getIdAlcance() == 4) {
                                active[3] = "selected";
                            }
                            if (al.getIdAlcance() == 5) {
                                active[4] = "selected";
                            }
                        }
                    %>
                    <option value = "1" <%= active[0]%>>Zona Rural</option>
                    <option value = "2" <%= active[1]%>>Zona Urbana</option>
                    <option value = "3" <%= active[2]%>>Estatal</option>
                    <option value = "4" <%= active[3]%>>Nacional</option>
                    <option value = "5" <%= active[4]%>>Regional</option>
                </select>
                <p class="help-block"><em>Para seleccionar múltiples Alcances mantén presionada la tecla <b>Ctrl</b> (o <b>Cmd</b> en Mac) y selecciona los demás.</em></p>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label"> Poblaci&oacute;n a atender: </label>
            <div class = "controls">
                <%
                    String active2[] = new String[6];
                    Set<PoblacionPrograma> poblaciones = (Set<PoblacionPrograma>) misProgramas.getPoblacion();
                    Iterator it2 = poblaciones.iterator();
                    while (it2.hasNext()) {
                        PoblacionPrograma pp = (PoblacionPrograma) it2.next();
                        if (pp.getIdPoblacion() == 1) {
                            active2[0] = "selected";
                        }
                        if (pp.getIdPoblacion() == 2) {
                            active2[1] = "selected";
                        }
                        if (pp.getIdPoblacion() == 3) {
                            active2[2] = "selected";
                        }
                        if (pp.getIdPoblacion() == 4) {
                            active2[3] = "selected";
                        }
                        if (pp.getIdPoblacion() == 5) {
                            active2[4] = "selected";
                        }
                        if (pp.getIdPoblacion() == 6) {
                            active2[5] = "selected";
                        }
                    }
                %>
                <select multiple = "multiple" name = "poblaProgIns">
                    <option value = "1" <%= active2[0]%> >Marginada</option>
                    <option value = "2" <%= active2[1]%> >No marginada</option>
                    <option value = "3" <%= active2[2]%> >J&oacute;venes</option>
                    <option value = "4" <%= active2[3]%> >Adultos</option>
                    <option value = "5" <%= active2[4]%> >Ni&ntilde;os</option>
                    <option value = "6" <%= active2[5]%> >Tercera edad</option>
                </select>
                <p class="help-block"><em>Para seleccionar múltiples datos de población mantén presionada la tecla <b>Ctrl</b> (o <b>Cmd</b> en Mac) y selecciona los demás.</em></p>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label"> Lugar de Realizaci&oacute;n: </label>
            <div class = "controls">
                <input type = "text" name = "lugarProgIns" id = "lugarProgIns" placeholder = "Lugar o direcci&oacute;n" value="<%= misProgramas.getLugar()%>" />
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label"> D&iacute;as de asistencia: </label>
            <div class = "controls">
                <%
                    String active3[] = new String[7];
                    Set<Dia> day = (Set<Dia>) misProgramas.getDias();
                    Iterator it5 = day.iterator();
                    while (it5.hasNext()) {
                        Dia d = (Dia) it5.next();
                        if (d.getIdDia() == 1) {
                            active3[0] = "selected";
                        }
                        if (d.getIdDia() == 2) {
                            active3[1] = "selected";
                        }
                        if (d.getIdDia() == 3) {
                            active3[2] = "selected";
                        }
                        if (d.getIdDia() == 4) {
                            active3[3] = "selected";
                        }
                        if (d.getIdDia() == 5) {
                            active3[4] = "selected";
                        }
                        if (d.getIdDia() == 6) {
                            active3[5] = "selected";
                        }
                        if (d.getIdDia() == 7) {
                            active3[6] = "selected";
                        }

                    }
                %>
                <select multiple = "multiple" name = "diasProgIns">
                    <option value = "1" <%= active3[0]%>>Lunes</option>
                    <option value = "2" <%= active3[1]%>>Martes</option>
                    <option value = "3" <%= active3[2]%>>Mi&eacute;rcoles</option>
                    <option value = "4" <%= active3[3]%>>Jueves</option>
                    <option value = "5" <%= active3[4]%>>Viernes</option>
                    <option value = "6" <%= active3[5]%>>S&aacute;bado</option>
                    <option value = "7" <%= active3[6]%>>Domingo</option>
                </select>
                <p class="help-block"><em>Para seleccionar múltiples días mantén presionada la tecla <b>Ctrl</b> (o <b>Cmd</b> en Mac) y selecciona los demás.</em></p>
            </div>
        </div>
        <div class = "control-group">
            <label class = "control-label"> Horario: </label>
            <div class = "controls">
                <% String mat, ves, mix;
                    mat = ves = mix = "";
                    if (misProgramas.getHorario().getIdHorario() == 1) {
                        mat = "selected";
                    }
                    if (misProgramas.getHorario().getIdHorario() == 2) {
                        ves = "selected";
                    }
                    if (misProgramas.getHorario().getIdHorario() == 3) {
                        mix = "selected";
                    }
                %>

                <select name = "horaProgIns" id = "horaProgIns">
                    <option value = "1" <%= mat%>>Matutino</option>
                    <option value = "2" <%= ves%>>Vespertino</option>
                    <option value = "3" <%= mix%>>Mixto</option>
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
                <%
                    Set<ActividadPrograma> actividades = (Set<ActividadPrograma>) misProgramas.getActividad();
                    Iterator it = actividades.iterator();
                    int num = 1;
                    while (it.hasNext()) {
                        ActividadPrograma res = (ActividadPrograma) it.next();
                %>
                <tr id="row<%=num%>">
                    <td>
                        <input class="input-large" type = "text" name = "licenProgIns" id = "licenProgIns" value="<%= res.getLicenciatura()%>" />
                    </td>
                    <td> 
                        <input class="input-mini" type = "text" name = "vacanProgIns" id = "vacanProgIns" value="<%= res.getnSolicitados()%>"/>
                    </td>
                    <td>
                        <textarea name = "actProgIns" id = "actProgIns" rows = "5" placeholder = "Escribe 5 actividades como  mínimo" ><%=res.getActividad()%></textarea>
                    </td>
                    <td><input class="btn btn-mini btn-danger" type="button" id="elim1" onclick="delRowAct(<%=num%>)" value="Eliminar" /></td>
                </tr>
                <% num++;
                    }%>
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
                <%
                    Set<ResponsablePrograma> responsables = (Set<ResponsablePrograma>) misProgramas.getResponsables();
                    Iterator it3 = responsables.iterator();
                    num = 1;
                    while (it3.hasNext()) {
                        ResponsablePrograma res = (ResponsablePrograma) it3.next();
                %>
                <tr id="row<%= num%>">
                    <td>
                        <input class="input-medium" type = "text" id = "respoIns" name = "respoIns" value="<%= res.getResponsable()%>" />
                    </td>
                    <td> 
                        <input class="input-medium" type = "text" id = "cargoRespoIns" name = "cargoRespoIns" value="<%= res.getCargo()%>" />
                    </td>
                    <td>
                        <input class="input-medium" type="text" id="emailInst" name="emailInst" maxlength="30" value="<%= res.getEmail()%>" />
                    </td>
                    <td><input class="btn btn-mini btn-danger" type="button" id="elim1" onclick="delRowAct(<%=num%>)" value="Eliminar" /></td>
                </tr>
                <% num++;
                    }%>
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
                <textarea class="span5" id = "obsProgIns" name = "obsProgIns" maxlength = "600" placeholder = "Escribe tus observaciones, pero no son necesarias"><%= misProgramas.getObservaciones()%></textarea>
            </div>
        </div>
        <div class = "form-actions">
            <button type="submit" class = "btn btn-primary btn-large" >Enviar Datos</button>
        </div>
    </div>
</div>
