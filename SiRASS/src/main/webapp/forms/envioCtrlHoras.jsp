<%@page import="skyforge.sirass.model.prestador.ControlHoras"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page import="skyforge.sirass.dao.prestador.ControlHorasDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ControlHorasDAO cDAO = new ControlHorasDAO();
    Inscripcion insc = (Inscripcion) session.getAttribute("inscripcion");
    ControlHoras control = cDAO.getLastReport(insc.getIdInscripcion());
    int horasAnteriores = 0;
    short minsAnteriores = 0;
    short lastReport = 0;
    String total = "0:0";
    if (control != null) {
        if (control.getHorasAcumuladas() != null) {
            horasAnteriores = control.getHorasAcumuladas();
        }
        if (control.getMinutosAcumulados() != null) {
            minsAnteriores = control.getMinutosAcumulados();
        }
        total = horasAnteriores + ":" + minsAnteriores;
        lastReport = control.getnReporte();
    }
%>
<input type="hidden" name="inscripcion" value="<%= insc.getIdInscripcion()%>" />
<ul class="nav nav-tabs">
					<li class="active">
						<a href="#datosReporte" data-toggle="tab">Datos del reporte</a>
					</li>
					<li>
						<a href="#registroHoras" data-toggle="tab">Registro de horas</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="datosReporte">
						<legend>Datos del reporte</legend>
						<div class="row">
							<div class="span4">
								<div class="control-group">
									<label class="control-label" for="nReporte">Número de reporte:</label>
									<div class="controls">
										<select name="nReporte" id="nReporte" class="input-mini">
                                        <%  for (int i = 1; i < 25; i++) {
                                                if (i == (lastReport+1)) { %>
                                            <option value="<%= i %>" selected="selected"><%=i%></option>
                                        <%      } else {
                                        %>
                                            <option value="<%= i %>"><%=i%></option>
                                        <%      }
                                            }   %>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="supervisor">Supervisor:</label>
									<div class="controls">
										<input type="text" name="supervisor" id="supervisor" placeholder="Jefe inmediato"
											class="input-medium" maxlength="45" />
									</div>
								</div>
							</div> <!-- end span -->
							<div class="span4">
								<div class="control-group">
									<label class="control-label" for="fInicio">Del:</label>
									<div class="controls">
										<input type="text" name="fInicio" id="fInicio" placeholder="dd/mm/aaaa"
											class="input-small" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label" for="fTermino">Al:</label>
									<div class="controls">
										<input type="text" name="fTermino" id="fTermino" placeholder="dd/mm/aaaa" class="input-small" />
									</div>
								</div>
							</div> <!-- end span -->
						</div> <!-- end row -->
						<div class="well center">
							<h6>Pulsa en la pestaña <a href="#">Registro de Horas</a> para continuar.</h6>
						</div>
					</div> <!-- end tab-pane -->
					<div class="tab-pane" id="registroHoras">
						<table class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>
									<th class="span2">Reporte</th>
									<th>Horas</th>
									<th>Minutos</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th>Actual</th>
									<td>
										<span class="hrsReporte"></span>
										<input type="hidden" name="hrsReporte"/>
									</td>
									<td>
										<span class="minsReporte"></span>
										<input type="hidden" name="minsReporte" />
									</td>
									<td>
										<span class="totalReporte"></span>
										<input id="totalReporte" name="totalReporte" type="hidden" />
									</td>
								</tr>
								<tr>
									<th>Anteriores</th>
									<td>
										<span class="hrsAnteriores"><%= horasAnteriores %></span>
                                        <input type="hidden" name="hrsAnteriores" value="<%= horasAnteriores %>" />
									</td>
									<td>
										<span class="minsAnteriores"><%= minsAnteriores %></span>
										<input type="hidden" name="minsAnteriores" value="<%= minsAnteriores %>" />
									</td>
									<td>
										<span class="totalAnterior"><%= total %></span>
									</td>
								</tr>
								<tr>
									<th>
										Acumulado
										<a href="#" id="help1" rel="tooltip" title="Actual + Anteriores"><i class="icon-question-sign"></i></a>
									</th>
									<td>
										<span class="hrsAcumuladas"></span>
										<input type="hidden" name="hrsAcumuladas" />
									</td>
									<td>
										<span class="minsAcumulados"></span>
										<input type="hidden" name="minsAcumulados" />
									</td>
									<td>
										<span class="totalAcumulado"></span>
										<input type="hidden" name="totalAcumulado" />
									</td>
								</tr>
							</tbody>
						</table>
						<legend>Registra las horas realizadas</legend>
						<div class="well well-small" style="text-align: center;">
							<a class="btn btn-primary" id="addHour">Agregar hora</a>
							<a class="btn btn-inverse" id="autoAdd">Llenado automático</a>
						</div>
						<table class="table table-bordered table-condensed table-striped" id="table-horas">
		                    <thead>
		                        <tr>
		                            <th>N&deg;</th>
		                            <th>Fecha</th>
		                            <th>Hora entrada</th>
		                            <th>Hora salida</th>
		                            <th>Horas d&iacute;a</th>
		                            <th>Eliminar</th>
		                        </tr>
		                    </thead>
		                    <tbody id="rows-horas">
		                        <tr id="row1">
		                            <td>1</td>
		                            <td>
		                                <input type="text" id="fecha1" name="fecha1"
		                                       class="input-small" placeholder="dd/mm/aaaa"/>
		                            </td>
		                            <td>
		                                <input type="text" id="hEntrada1" name="hEntrada1" data-reg="1"
		                                       class="input-small" placeholder="hh:mm" />
		                            </td>
		                            <td>
		                                <input type="text" id="hSalida1" name="hSalida1" data-reg="1"
		                                       class="input-small" placeholder="hh:mm"/>
		                            </td>
		                            <td>
		                                <span id="suma1"></span>
		                                <input type="hidden" name="acum1" id="acum1" value="" />
		                                <input type="hidden" name="sumaHrs1" id="sumaHrs1" value="" data-tipo="hr" />
		                                <input type="hidden" name="sumaMins1" id="sumaMins1" value="" data-tipo="min" />
		                            </td>
		                            <td>
		                                <input class="btn btn-mini btn-danger" type="button" id="elim1" onclick="delRow(1)" value="Eliminar" />
		                            </td>
		                        </tr>
		                    </tbody>
		                </table>
						<div class="form-actions">
							<button type="submit" class="btn btn-success">Enviar</button>
							<button type="reset" class="btn btn-warning">Restablecer</button>
						</div>
					</div> <!-- end tab-pane -->
				</div> <!-- end tab-content -->