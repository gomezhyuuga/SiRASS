<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.institucion.CInstitucion"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.institucion.CInstitucionDAO"%>
<input type="hidden" name="class" value="Inscripcion" />
<div id="tipoServicio" class="form-actions">
	<input type="hidden" name="tipoSS" id="tipoSS" value="1" />
	<div class="btn-group" data-toggle="buttons-radio">
        <button type="button" id="ss" value="1"
                class="btn btn-large btn-info active">Servicio Social</button>
        <button type="button" id="pp" value="2"
                class="btn btn-large btn-info">Pr&aacute;ctica Profesional</button>
    </div>
</div>
<!-- tabs -->
<div class="tabbable">
    <ul class="nav nav-tabs">
        <li class="active">
            <a href="#datosEscolares" data-toggle="tab">Datos escolares</a>
        </li>
        <li>
            <a href="#datosPrograma" data-toggle="tab">Datos del programa</a>
        </li>
    </ul>
</div>
<div class="tab-content">
	<div class="tab-pane active" id="datosEscolares">
		<div class="control-group">
			<label for="institucionList" class="control-label">Instituci&oacute;n o dependencia:</label>
			<div class="controls">
				<select name="institucionList" id="institucionList" class="input-xlarge" onchange="changeInstitucion(this)">
					<option selected="selected" value="">-- Selecciona tu instituci&oacute;n --</option>
					<optgroup label="No registrada">
						<option value="unregistred">Otra</option>
					</optgroup>
					<optgroup id="instRegistradas" label="Registradas">
                    <%  CInstitucionDAO cidao = new CInstitucionDAO();
                        List<CInstitucion> insts = cidao.getAll(CInstitucion.class);
                        if (insts != null && !insts.isEmpty()) { 
                            Iterator<CInstitucion> it = insts.iterator();
                            while (it.hasNext()) {
                                CInstitucion inst = it.next(); %>
                        <option value="<%= inst.getIdCInstitucion() %>"><%= inst.getNombre() %></option>
                    <%      }
                        } else { %>
                        <option value="">Sin instituciones</option>
                    <%  } %>
					</optgroup>
				</select>
			</div>
		</div>
		<div class="control-group hide" id="otraInstitucion">
			<label for="nombreInstitucion" class="control-label">Nombre:</label>
			<div class="controls">
				<input type="text" id="nombreInstitucion" name="nombreInstitucion"
					placeholder="Nombre de la instituci&oacute;n" class="input-xlarge" maxlength="150" />
			</div>
		</div>
		<div class="control-group">
			<label for="plantelList" class="control-label">Plantel:</label>
			<div class="controls">
				<select name="plantelList" id="plantelList" class="input-xlarge" onchange="changeInstitucion(this)">
					<option value="">-- Selecciona tu plantel --</option>
					<option value="0">Sin plantel</option>
					<optgroup label="No registrado">
						<option value="unregistred">Otro</option>
					</optgroup>
					<optgroup id="plantRegistrados" label="Registrados">
					</optgroup>
				</select>
			</div>
		</div>
		<div class="control-group hide" id="otroPlantel">
			<label for="nombrePlantel" class="control-label">Nombre:</label>
			<div class="controls">
				<input type="text" id="nombrePlantel" name="nombrePlantel"
					placeholder="Nombre del plantel" class="input-xlarge" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
            <label class="control-label" for="semestre">Semestre:</label>
            <div class="controls">
                <select id="semestre" name="semestre" class="input-mini">
                    <!-- insertar del 1-12 -->
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>
        </div>
		<div class="control-group">
			<label class="control-label" for="especialidad">Especialidad:</label>
			<div class="controls">
			   <input class="input-xlarge" type="text" id="especialidad" name="especialidad" maxlength="25" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="matricula">Matricula:</label>
			<div class="controls">
			   <input class="input-xlarge" type="text" id="matricula" name="matricula" maxlength="15" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="promedio">Promedio:</label>
			<div class="controls">
			   <div class="input-append">
			       <input class="input-mini right" type="text" id="promedio" name="promedio" maxlength="4" /><span class="add-on">#.#</span>
			   </div>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="avanceCurso">Avance de cursos:</label>
			<div class="controls">
			   <div class="input-append">
			       <input class="input-mini right" type="text" id="avanceCurso" name="avanceCurso" maxlength="3" /><span class="add-on">%</span>
			   </div>
			</div>
		</div>
		<div class="control-group">
            <label class="control-label" for="fechaIngreso">Año de ingreso:</label>
            <div class="controls">
                <select id="fechaIngreso" name="fechaIngreso" class="input-mini">
                <%  for (int year = 2010; year >= 1940; year--) {%>
                    <option value="<%= year %>"><%= year %></option>
                <%  }%>
                </select>
            </div>
        </div>
        <!-- solo en caso de ser práctica profesional -->
		<div id="practicas">
			<div class="control-group">
			   <label class="control-label" for="creditos">Total de cr&eacute;ditos:</label>
			   <div class="controls">
			       <input class="input-mini" type="text" id="creditos" name="creditos" maxlength="4" />
			   </div>
			</div>
			<p><strong>Total de cursos</strong></p>
			<div class="control-group">
			   <label class="control-label" for="cursosBasico">Ciclo b&aacute;sico:</label>
			   <div class="controls">
			       <input class="input-mini" type="text" id="cursosBasico" name="cursosBasico" maxlength="3" />
			       <span class="input-inline">Ciclo superior</span>
			       <input class="input-mini" type="text" id="cursosSuperior" name="cursosSuperior" maxlength="3" />
			   </div>
			</div> 
		</div> <!-- end prácticas profesionales -->
        <div class="form-actions">
            <h6>Pulsa en <a href="#" id="next">Datos del programa</a> para continuar</h6>
        </div>
	</div>
	<div class="tab-pane" id="datosPrograma">
		<fieldset>
            <legend>Datos del Programa de Servicio Social</legend>
            <div class="control-group">
                <label class="control-label">Programa:</label>
                <div class="controls">
                    <input type="hidden" name="nombrePrograma" id="nombrePrograma" value="" />
                    <select class="input-xlarge" name="idPrograma" size="10" onchange="changePrograma(this)">
                        <option value="0">-- Seleccionar programa --</option>
                        <optgroup label="Externos">
                        <%  ProgramaSSDAO programaSSDAO = new ProgramaSSDAO();
                            List<ProgramaSS> externos = programaSSDAO.getListFewExternos();
                            List<ProgramaSS> internos = programaSSDAO.getListFewInternos();
                            if (externos == null || externos.isEmpty()) {
                                out.println("<option value=\"0\">Sin programas</optoin>");
                            } else {
                                for (ProgramaSS p : externos) {%>
                            <option value="<%= p.getIdPrograma()%>" data-cve="<%= p.getCvePrograma()%>"><%= p.getNombre()%></option>
                        <%      }
                            }%>
                        </optgroup>
                        <optgroup label="Internos">
                        <% if (internos == null || internos.isEmpty()) {
                                out.println("<option value=\"0\">Sin programas</optoin>");
                           } else {
                                for (ProgramaSS p : internos) {%>
                            <option value="<%= p.getIdPrograma()%>" data-cve="<%= p.getCvePrograma()%>"><%= p.getNombre()%></option>
                        <%      }
                            }%>
                        </optgroup>
                    </select>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="cvePrograma">Clave:</label>
                <div class="controls">
                    <input class="input-xlarge" type="text" id="cvePrograma" name="cvePrograma" maxlength="30"
                           readonly="readonly" value="" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="area">&Aacute;rea:</label>
                <div class="controls">
                    <input class="input-xlarge" type="text" id="area" name="area" maxlength="30" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="programaInst">Programa institucional:</label>
                <div class="controls">
                    <input class="input-xlarge" type="text" id="programaInst" name="programaInst" maxlength="30" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="cveProgramaInst">Clave:</label>
                <div class="controls">
                    <input class="input-xlarge" type="text" id="cveProgramaInst" name="cveProgramaInst" maxlength="30" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">D&iacute;as de asistencia:</label>
                <div class="controls">
                    <div class="btn-group" data-toggle="buttons-checkbox">
                        <select multiple="multiple" size="7" class="input-small" name="diasAsistencia">
                            <option value="1">Lunes</option>
                            <option value="2">Martes</option>
                            <option value="3">Mi&eacute;rcoles</option>
                            <option value="4">Jueves</option>
                            <option value="5">Viernes</option>
                            <option value="6">S&aacute;bado</option>
                            <option value="7">Domingo</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="horaEntrada">Hora de entrada:</label>
                <div class="controls">
                    <input type="text" name="horaEntrada" id="horaEntrada" class="input-small center" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="horaSalida">Hora de salida:</label>
                <div class="controls">
                    <input type="text" name="horaSalida" id="horaSalida" class="input-small center" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="fInicio">Fecha de inicio:</label>
                <div class="controls">
                    <input type="text" name="fInicio" id="fInicio" class="input-small center" />
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="fTermino">Fecha de t&eacute;rmino</label>
                <div class="controls">
                    <input type="text" name="fTermino" id="fTermino" class="input-small center" />
                </div>
            </div>
        </fieldset> <!-- end datos del programa SS -->
        <div class="control-group">
            <label class="control-label" for="difundir">Difundir:</label>
            <div class="controls">
                <label class="radio">
                    <input type="radio" name="difundir" id="difundirOK" value="1" checked="checked" />
                    Acepto que mis datos puedan ser difundidos
                </label>
                <label class="radio">
                    <input type="radio" name="difundir" id="difundirNO" value="0" />
                    Deseo que mis datos se mantengan en privado
                </label>
            </div>
        </div>
        <div class="form-actions">
            <button type="submit" class="btn btn-success btn-large">Inscribirse</button>
            <button type="reset" class="btn btn-inverse btn-large">Restablecer</button>
        </div>
	</div>
</div>