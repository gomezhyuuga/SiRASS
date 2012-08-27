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
                <jsp:param name="active" value="controlHoras" />
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
					<h1>Reportes envíados <small>Control Mensual de Horas</small></h1>
				</div>
				<div id="feedback"></div>
				<p>Desde esta página puedes ver los reportes que haz enviado y el estado de los mismos.</p>
                <%
                    ControlHorasDAO controlHorasDAO = new ControlHorasDAO();
                    List<ControlHoras> reportes = controlHorasDAO.getListByInscripcion(inscripcion.getIdInscripcion());
                    if (reportes.size() >= 1) {
                        Iterator<ControlHoras> it = reportes.iterator();
                %>
                <table class="table table-bordered table-striped table-condensed">
					<thead>
                        <tr>
							<th rowspan="2">#</th>
							<th colspan="2">Fecha</th>
							<th colspan="3">Horas</th>
							<th rowspan="2">Acción</th>
						</tr>
						<tr>
							<th>Inicio</th>
							<th>Fin</th>
							<th>Mes</th>
							<th>Anteriores</th>
							<th>Acumuladas</th>
						</tr>
					</thead>
                    <tbody>
                        <%
                            while (it.hasNext()) {
                                ControlHoras c = it.next(); %>
                            <tr>
							<td><%= c.getnReporte()%></td>
							<td><%= c.getFechaInicio()%></td>
							<td><%= c.getFechaFin()%></td>
							<td><%= c.getHorasMes() + ":" + c.getMinutosMes() %></td>
							<td><%= c.getHorasAnteriores() + ":" + c.getMinutosAnteriores()%></td>
							<td><%= c.getHorasAcumuladas() + ":" + c.getMinutosAcumulados()%></td>
							<td>
								<div class="btn-group center">
									<a class="btn dropdown-toggle btn-primary btn-small" data-toggle="dropdown" href="#">
									    Acción
									    <span class="caret"></span>
									  </a>
									<ul class="dropdown-menu">
										<li>
											<a href="detalles.jsp?id=<%=c.getIdControlHoras()%>"><i class="icon-exclamation-sign"></i>
												Detalles
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
