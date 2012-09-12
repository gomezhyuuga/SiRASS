<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.prestador.RegistroHora"%>
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
        <jsp:param name="title" value="Detalles de Control de Horas" />
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
                <jsp:param name="active" value="revControl" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
            <%
                    if (request.getParameter("id") != null) {
                        ControlHoras controlHoras = null;
                        try {
                            int id = Integer.parseInt(request.getParameter("id"));
                            ControlHorasDAO dao = new ControlHorasDAO();
                            controlHoras = dao.getByPK(id);
                            Set<RegistroHora> horas = controlHoras.getHoras();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                %>
                <div class="row">
                    <div class="span5">
                        <%
                            String label = "label-";
                            int idEstado = controlHoras.getEstado().getIdEstado();
                            switch (idEstado) {
                                case 1:
                                    label += "info";
                                    break;
                                case 2:
                                    label += "important";
                                    break;
                                case 3:
                                    label += "success";
                            }
                        %>
                        <h1>Control de Horas 
                            <span class="label <%= label%>"><%= controlHoras.getEstado().getDescripcion()%></span>
                        </h1>
                        <h3>Supervisor: <%= controlHoras.getSupervisor()%></h3>
                        <h3>Revisor: <%= controlHoras.getModificadoPor()%></h3>
                    </div>
                    <div class="span3 aright">
                        <h4>N&deg; reporte: <%= controlHoras.getnReporte()%></h4>
                        <h4>Del: <%= dateFormat.format(controlHoras.getFechaInicio())%></h4>
                        <h4>Al: <%= dateFormat.format(controlHoras.getFechaFin())%></h4>
                        <h6>id: <%= id%></h6>
                    </div>
                </div>
                <br />
                <div class="row">
                    <div class="span3">
                        <h4>Mes: <%= controlHoras.getHorasMes()%></h4>
                        <h4>Anteriores: <%= controlHoras.getHorasAnteriores()%></h4>
                        <h4>Acumuladas: <%= controlHoras.getHorasAcumuladas()%></h4>
                    </div>
                    <form id="generate" action="/SiRASS/Generate" method="post">
                        <input type="hidden" name="tipoDocumento" value="controlHoras" />
                        <input type="hidden" name="idControl" value="<%= controlHoras.getIdControlHoras()%>" />
                    </form>
                    <div class="span4">
                        <div class="btn-group pull-right">
                            <a class="btn btn-success btn-large" href="#download" id="generar">Descargar</a>
                            <a class="btn btn-success btn-large dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"> </span>
                            </a>
                            <ul class="dropdown-menu left">
                                <li>
                                    <a href="#"><i class="icon-pencil"></i>
                                        Editar</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <br />
                <!--                        <div class="row">-->
                <h2>Registro de horas</h2>
                <table class="table table-bordered table-striped">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Fecha</th>
                            <th>Entrada</th>
                            <th>Salida</th>
                            <th>Horas</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int n = 1;
                            for (RegistroHora reg : horas) {
                        %>
                        <tr>
                            <td><%= n%></td>
                            <td><%= dateFormat.format(reg.getFecha())%></td>
                            <td><%= reg.getHoraEntrada()%></td>
                            <td><%= reg.getHoraSalida()%></td>
                            <td><%= reg.getHorasDia()%></td>
                        </tr>
                        <%
                                n++;
                            }
                        %>
                    </tbody>
                </table>
                <%
                        } catch (Exception e) {
                            out.println("<h1>Control de Horas inválido!</h1>");
                        }
                    } else {
                        out.println("<h1>Control de Horas inválido!</h1>");
                    }
                %>
			</div> <!-- end content -->
		</div> <!-- end row -->
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp"/>
</body>
</html>