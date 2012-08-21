<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
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
										<span class="hrsAnteriores">20</span>
										<input type="hidden" name="hrsAnteriores" />
									</td>
									<td>
										<span class="minsAnteriores">12</span>
										<input type="hidden" name="minsAnteriores" />
									</td>
									<td>
										<span class="totalAnterior">20:12</span>
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