<%@page import="java.text.SimpleDateFormat"%>
<%@page import="skyforge.sirass.model.prestador.InformeBimensual"%>
<%@page import="skyforge.sirass.dao.prestador.InformeBimensualDAO"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Env&iacute; tu Informe Bimensual" />
    </jsp:include>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="../jspf/header.jsp" />
	<!-- Body
	============================== -->
	<div class="container content">
		<div class="row">
			<!-- Sidebar
			============================== -->
            <jsp:include page="../jspf/sidebar.jsp">
                <jsp:param name="active" value="informeBim" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
<%          if (session.getAttribute("inscripcion") != null) {
                Inscripcion inscripcion = (Inscripcion) session.getAttribute("inscripcion");
                int idEstado = inscripcion.getEstado().getIdEstado();
                if (idEstado != 2) { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="<%= idEstado%>" />
                </jsp:include>
<%              } else { %>
                <div class="page-header">
					<h1>Edita tu Informe Bimensual</h1>
				</div>
                <div id="feedback"></div>
                <%
                    if (request.getParameter("id") != null) {
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            Inscripcion insc = (Inscripcion) session.getAttribute("inscripcion");
                            InformeBimensualDAO ibdao1 = new InformeBimensualDAO();
                            InformeBimensual inf = ibdao1.getByPK(id);
                            if (inf != null) {
                                int reportN = inf.getNumReporte();
                                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                %>
                                <form method="post" action="/SiRASS/FormReceiver" name="form-InformeBimensual" id="form-InformeBimensual">
                                    <input type="hidden" name="class" value="ActualizarInformeBim" />
                                    <input type="hidden" name="id" value="<%= inf.getIdInformeBimensual() %>" />
                                    <input type="hidden" name="inscripcion" value="<%= insc.getIdInscripcion()%>" />
                                    <h2>Periodo</h2>
                                    <div class="row form-inline">
                                        <div class="span2">
                                            <label for="inicioPeriodo">Del:</label>
                                            <input type="text" name="inicioPeriodo" id="inicioPeriodo" class="input-small"
                                                   placeholder="dd/mm/aaaa" value="<%=df.format(inf.getInicioPeriodo())%>" />
                                        </div>
                                        <div class="span2">
                                            <label for="terminoPeriodo">Al:</label>
                                            <input type="text" name="terminoPeriodo" id="terminoPeriodo" class="input-small"
                                                   placeholder="dd/mm/aaaa" value="<%=df.format(inf.getTerminoPeriodo())%>" />
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
                                                <input type="text" name="horasBimestre" id="horasBimestre" class="input-mini"
                                                       value="<%= inf.getHorasBimestre()%>"/><span class="add-on">##</span>
                                            </div>
                                        </div>
                                        <div class="span2">
                                            <label for="horasAcumuladas">Horas acumuladas:</label>
                                            <div class="input-append">
                                                <input type="text" name="horasAcumuladas" id="horasAcumuladas" class="input-mini"
                                                       value="<%= inf.getHorasAcumuladas()%>"/><span class="add-on">##</span>
                                            </div>
                                        </div>
                                    </div> <!-- end row -->
                                    <label for="actividades">Actividades:</label>
                                    <textarea name="actividades" id="actividades" cols="30" rows="6" style="width: 100%; resize: none;"><%=inf.getActividades()%></textarea>
                                    <div class="form-actions">
                                        <button class="btn btn-success" type="submit">Actualizar reporte</button>
                                        <button class="btn btn-primary" type="reset">Restablecer datos</button>
                                    </div>
                                </form>
                                <h6 class="right">Fechas en formato d&iacute;a/mes/a&ntilde;o. Ej: 01/04/2012</h6>
                        <%  }
                        } catch (Exception e) {
                            
                        }
                    }
                %>
<%              }
            } else { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="0" />
                </jsp:include>
<%          } %>
			</div> <!-- end contenido -->
		</div> <!-- end row -->
	</div> <!-- end container -->
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp">
        <jsp:param name="datepicker" value="true" />
        <jsp:param name="bootbox" value="true" />
        <jsp:param name="form" value="true" />
        <jsp:param name="script" value="informeBim" />
    </jsp:include>
</body>
</html>