<%@page import="java.text.SimpleDateFormat"%>
<%@page import="skyforge.sirass.model.prestador.EstadoReporte"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="skyforge.sirass.model.prestador.InformeBimensual"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.prestador.InformeBimensualDAO"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Lista de Informes Bimensuales" />
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
                <jsp:param name="active" value="revBim" />
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
                <header class="page-header">
                    <h1>Informe Bimensual <small>Reportes env&iacute;ados</small></h1>
				</header>
                <%
                    InformeBimensualDAO ibdao = new InformeBimensualDAO();
                    List<InformeBimensual> informes = ibdao.getByUsername(request.getUserPrincipal().getName());
                    if (informes == null || informes.isEmpty()) {
                        out.println("<p class=\"lead\">A&uacute;n no haz enviado reportes.</p>");
                    } else {
                %>
				<p>Estos son los informes bimensuales que haz enviado.</p>
				<table class="table table-striped table-bordered">
					<thead>
						<tr>
                            <th>Num.</th>
                            <th>Inicio</th>
                            <th>Fin</th>
                            <th>Horas</th>
                            <th>Acumuladas</th>
                            <th>Estado</th>
                            <th>Acci&oacute;n</th>
                        </tr>
					</thead>
					<tbody>
                        <%
                            Map<Short, String> estados = new HashMap<Short, String>();
                            estados.put(EstadoReporte.CON_ERRORES, "label-danger");
                            estados.put(EstadoReporte.CORRECTO, "label-success");
                            estados.put(EstadoReporte.SIN_REVISION, "label-inverse");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            for (InformeBimensual inf : informes) {%>
						<tr>
                            <td class="right"><%= inf.getNumReporte() %></td>
                            <td class="center"><%= dateFormat.format(inf.getInicioPeriodo())%></td>
                            <td class="center"><%= dateFormat.format(inf.getTerminoPeriodo())%></td>
                            <td class="right"><%= inf.getHorasBimestre() %></td>
                            <td class="right"><%= inf.getHorasAcumuladas() %></td>
                            <td class="center">
                                <span class="label block<%= " " + estados.get(inf.getEstado().getIdEstado()) %>">
                                    <%= inf.getEstado().getDescripcion() %>
                                </span>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-small btn-warning"
                                       href="ver.jsp?id=<%=inf.getIdInformeBimensual()%>">
                                        <i class="icon-info-sign icon-white"></i>
                                        Ver
                                    </a>
                                    <a class="btn dropdown-toggle btn-small btn-warning" data-toggle="dropdown">
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="/SiRASS/Generate?doc=bimensual&id=<%=inf.getIdInformeBimensual()%>" id="descargar">
                                                <i class="icon-download-alt"></i>
                                                Descargar
                                            </a>
                                        </li>
                                        <li>
                                            <a href="actualizar.jsp?id=<%=inf.getIdInformeBimensual()%>" id="editar">
                                                <i class="icon-edit"></i>
                                                Editar
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <%  }
                        %>
					</tbody>
				</table> <!-- end table -->
<%                  }
                }
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
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>