<%@page import="java.text.SimpleDateFormat"%>
<%@page import="skyforge.sirass.model.prestador.EstadoReporte"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.model.prestador.ControlHoras"%>
<%@page import="skyforge.sirass.dao.prestador.ControlHorasDAO"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Bienvenido Prestador" />
    </jsp:include>
    <style type="text/css">
		table thead tr th {
			text-align: center !important;
			vertical-align: middle !important;
		}
        td {
            vertical-align: middle !important;
        }
	</style>
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
                <jsp:param name="active" value="revControl" />
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
					<h1>Control Mensual de Horas <small>Reportes env&iacute;ados</small></h1>
				</div>
				<div id="feedback"></div>
				<p>Desde esta página puedes ver los reportes que haz enviado y el estado de los mismos.</p>
                <%
                    ControlHorasDAO controlHorasDAO = new ControlHorasDAO();
                    List<ControlHoras> reportes = controlHorasDAO.getListByInscripcion(inscripcion.getIdInscripcion());
                    if (reportes.size() >= 1) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Iterator<ControlHoras> it = reportes.iterator();
                %>
                <table class="table table-bordered table-striped table-condensed">
					<thead>
                        <tr>
							<th rowspan="2">#</th>
							<th colspan="2">Fecha</th>
							<th colspan="2">Horas</th>
                            <th rowspan="2" style="width: 85px;">Estado</th>
							<th rowspan="2">Acción</th>
						</tr>
						<tr>
							<th>Inicio</th>
							<th>Fin</th>
							<th>Mes</th>
							<th>Acum.</th>
						</tr>
					</thead>
                    <tbody>
                        <%
                            while (it.hasNext()) {
                                ControlHoras c = it.next(); %>
                        <tr>
                            <td class="right"><%= c.getnReporte()%></td>
                            <td class="center"><%= dateFormat.format(c.getFechaInicio())%></td>
                            <td class="center"><%= dateFormat.format(c.getFechaFin())%></td>
                            <td class="right"><%= c.getHorasMes() + ":" + c.getMinutosMes() %></td>
                            <td class="right"><%= c.getHorasAcumuladas() + ":" + c.getMinutosAcumulados()%></td>
                            <td class="center">
                                <%
                                    String lbl = "";
                                    String estado = "";
                                    estado = c.getEstado().getDescripcion();
                                    switch (c.getEstado().getIdEstado()) {
                                        case EstadoReporte.CON_ERRORES:
                                            lbl = "label-important";
                                            break;
                                        case EstadoReporte.CORRECTO:
                                            lbl = "label-success";
                                            break;
                                    }
                                %>
                                <span class="label block <%=lbl%>"><%=estado%></span>
                            </td>
							<td>
								<div class="btn-group">
									<a class="btn dropdown-toggle btn-primary" data-toggle="dropdown" href="#">
									    Acción
									    <span class="caret"></span>
									  </a>
									<ul class="dropdown-menu">
										<li>
											<a href="ver.jsp?id=<%=c.getIdControlHoras()%>"><i class="icon-exclamation-sign"></i>
												Detalles
											</a>
										</li>
                                        <li>
											<a href="/SiRASS/Generator?doc=ControlHoras&id=<%= c.getIdControlHoras()%>"><i class="icon-download"></i>
												Generar
											</a>
										</li>
										<li>
											<a href="editar.jsp?id=<%=c.getIdControlHoras()%>"><i class="icon-edit"></i>
												Editar
											</a>
										</li>
									</ul>
								</div>
							</td>
						</tr>
                        <%  }   %>
					</tbody>
				</table>
                <%  }   %>
<%              }
            } else { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="0" />
                </jsp:include>
<%          } %>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp">
        <jsp:param name="datepicker" value="true" />
        <jsp:param name="bootbox" value="true" />
        <jsp:param name="form" value="true" />
        <jsp:param name="script" value="controlHoras" />
    </jsp:include>
</body>
</html>
