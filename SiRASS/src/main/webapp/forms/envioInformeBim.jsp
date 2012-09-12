<%@page import="skyforge.sirass.model.prestador.InformeBimensual"%>
<%@page import="skyforge.sirass.dao.prestador.InformeBimensualDAO"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Inscripcion insc = (Inscripcion) session.getAttribute("inscripcion");
    InformeBimensualDAO ibdao = new InformeBimensualDAO();
    InformeBimensual lastReport = ibdao.getLastReport(insc.getIdInscripcion());
    int acum = 0;
    int anteriores = 0;
    short reportN = 1;
    if (lastReport != null) {
        reportN = lastReport.getNumReporte();
        reportN++;
    }
%>
<input type="hidden" name="inscripcion" value="<%= insc.getIdInscripcion()%>" />
<h2>Periodo</h2>
					<div class="row form-inline">
						<div class="span2">
							<label for="inicioPeriodo">Del:</label>
							<input type="text" name="inicioPeriodo" id="inicioPeriodo" class="input-small"
								placeholder="dd/mm/aaaa" />
						</div>
						<div class="span2">
							<label for="terminoPeriodo">Al:</label>
							<input type="text" name="terminoPeriodo" id="terminoPeriodo" class="input-small"
								placeholder="dd/mm/aaaa" />
						</div>
					</div>
					<br />
					<div class="row">
						<div class="span2">
							<label for="numReporte">Número de reporte</label>
							<select name="numReporte" id="numReporte" class="input-small">
								<option value="">Elegir</option>
                                <%  for (short i = 1; i <= 12; i++) {
                                        if (reportN == i) { %>
                                <option value="<%= i %>" selected="selected"><%= i %></option>  
                                        <%} else {
                                %>
								<option value="<%= i %>"><%= i %></option>
                                <%      }
                                    }%>
							</select>
						</div>
						<div class="span2">
							<label for="horasBimestre">Horas reportadas:</label>
							<div class="input-append">
								<input type="text" name="horasBimestre" id="horasBimestre" class="input-mini" /><span class="add-on">##</span>
							</div>
						</div>
						<div class="span2">
							<label for="horasAnteriores">Horas anteriores:</label>
							<div class="input-append">
								<input type="text" name="horasAnteriores" id="horasAnteriores" class="input-mini" /><span class="add-on">##</span>
							</div>
						</div>
						<div class="span2">
							<label for="horasAcumuladas">Horas acumuladas:</label>
							<input type="text" name="horasAcumuladas" id="horasAcumuladas" readonly="readonly" class="input-small" />
						</div>
					</div> <!-- end row -->
					<label for="actividades">Actividades:</label>
					<textarea name="actividades" id="actividades" cols="30" rows="6" style="width: 100%; resize: none;"></textarea>
					<div class="form-actions">
						<button class="btn btn-success" type="submit">Enviar reporte</button>
						<button class="btn btn-primary" type="reset">Restablecer datos</button>
					</div>