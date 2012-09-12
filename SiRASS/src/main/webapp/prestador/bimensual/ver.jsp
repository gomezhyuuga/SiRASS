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
            <%
                if (request.getParameter("id") != null) {
                    InformeBimensual informe = null;
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        InformeBimensualDAO dao = new InformeBimensualDAO();
                        informe = dao.getByPK(id);
            %>
                <div class="row">
                        <div class="span5 pull-left">
                            <%
                                String label = "label-";
                                int idEstado = informe.getEstado().getIdEstado();
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
                            <h1>Informe Bimensual 
                                <span class="label <%= label%>"><%= informe.getEstado().getDescripcion()%></span>
                            </h1>
                            <h3>Revisor: <%= informe.getModificadoPor()%></h3>
                        </div>
                        <div class="span2 pull-right right">
                            <h4>N&deg; reporte: <%= informe.getNumReporte()%></h4>
                            <h4>Del: <%= informe.getInicioPeriodo()%></h4>
                            <h4>Al: <%= informe.getTerminoPeriodo()%></h4>
                            <h6>id: <%= id%></h6>
                        </div>
                    </div>
                    <br />
                    <div class="row">
                        <div class="span2 pull-left">
                            <h4>Mes: <%= informe.getHorasBimestre()%></h4>
                            <h4>Acumuladas: <%= informe.getHorasAcumuladas()%></h4>
                        </div>
                        <div class="span3 pull-right">
                            <div class="btn-group pull-right">
                                <button class="btn btn-success btn-large">Descargar</button>
                                <button class="btn btn-success btn-large dropdown-toggle" data-toggle="dropdown">
                                    <span class="caret"> </span>
                                </button>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="#"><i class="icon-pencil"></i>
                                            Editar</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <br />
                    <h2>Actividades</h2>
                    <textarea class="paragraph" readonly="readonly" cols="40" rows="10"><%= informe.getActividades()%></textarea>
                    <%  if (informe.getObservaciones() != null) {
                            if (!informe.getObservaciones().trim().equals("")) {%>
                    <h2>Observaciones</h2>
                    <textarea class="paragraph" readonly="readonly" cols="40" rows="6"><%= informe.getObservaciones()%></textarea>
                    <%      }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        out.println("<h1>Informe Bimensual inv&aacute;lido! (" + request.getParameter("idBimensual") + ")</h1>");
                    }
                } else {
                    out.println("<h1>Informe Bimensual inv&aacute;lido!</h1>");
                }
            %>
                </div>
			</div> <!-- end contenido -->
		</div> <!-- end row -->
	</div> <!-- end container -->
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>