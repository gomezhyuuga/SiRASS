<%@page import="skyforge.sirass.model.prestador.EstadoReporte"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="skyforge.sirass.model.prestador.InformeFinal"%>
<%@page import="skyforge.sirass.dao.prestador.InformeFinalDAO"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Revisi&oacute;n de Informe Final" />
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
                <jsp:param name="active" value="revFin" />
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
<%              } else { 
                int idInscripcion = inscripcion.getIdInscripcion();
                InformeFinalDAO finalDAO = new InformeFinalDAO();
                InformeFinal informe = finalDAO.getByInscripcion(idInscripcion);
                if (informe == null) {
                    out.println("<h1>Aún no envías tu informe</h1>");
                } else {
                    String label = "";
                    Map<Short, String> labels = new HashMap<Short, String>();
                    labels.put(EstadoReporte.CON_ERRORES, "label-danger");
                    labels.put(EstadoReporte.CORRECTO, "label-success");
                    labels.put(EstadoReporte.SIN_REVISION, "label-inverse");
                    label = labels.get(informe.getEstado().getIdEstado());
                    informe.setObservaciones("OK estas son mis observaciones\n Nueva linea");
                %>
                <div class="page-header">
                        <h1>Tu informe final
                            <small class="label <%= label%>"><%= informe.getEstado().getDescripcion()%></small>
                        </h1>
                </div>
                <div class="form-actions">
                    <a href="#download" id="download" class="btn btn-large btn-success">
                        <i class="icon-download-alt icon-white"></i>
                        Descargar</a>
                    <a href="updateFinal.jsp" class="btn btn-large btn-warning">
                        <i class="icon-edit icon-white"></i>
                        Editar</a>
                    <button type="button" class="btn btn-large btn-danger">
                        <i class="icon-remove icon-white"></i>
                        Eliminar</button>
                </div>
                <form action="/SiRASS/Generate" id="generate">
                    <input type="hidden" name="tipoDocumento" value="informeFinal" />
                    <input type="hidden" name="inscripcion" value="<%= idInscripcion%>" />
                </form>
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#intro" data-toggle="tab">Introducci&oacute;n</a>
                    </li>
                    <li>
                        <a href="#objetivos" data-toggle="tab">Objetivos</a>
                    </li>
                    <li>
                        <a href="#actividades" data-toggle="tab">Activdades</a>
                    </li>
                    <li>
                        <a href="#resultados" data-toggle="tab">Resultados</a>
                    </li>
                </ul>                            
                <div class="tab-content">
                    <div class="tab-pane active" id="intro">
                        <h2>Introducc&oacute;n</h2>
                        <p><%= informe.getIntroduccion()%></p>
                    </div>
                    <div class="tab-pane" id="objetivos">
                        <h2>Objetivo General</h2>
                        <p><%= informe.getObjetivoGeneral()%></p>
                        <h2>Objetivos espec&iacute;ficos</h2>
                        <p><%= informe.getObjetivosEspecificos()%></p>
                    </div>
                    <div class="tab-pane" id="actividades">
                        <h2>Metodolog&iacute;a utilizada</h2>
                        <p><%= informe.getMetodologia()%></p>
                        <h2>Actividades realizadas</h2>
                        <p><%= informe.getActividades()%></p>
                    </div>
                    <div class="tab-pane" id="resultados">
                        <h2>Metas alcanzadas</h2>
                        <p><%= informe.getMetasAlcanzadas()%></p>
                        <h2>Resultados y conclusiones</h2>
                        <p><%= informe.getResultados()%></p>
                    </div>
                </div>
                <div>
                    <h2>Observaciones</h2>
                </div>
                <%  if (informe.getObservaciones() != null) {
                        if (!informe.getObservaciones().trim().equals("")) {%>
                <h2>Observaciones</h2>
                <textarea class="paragraph" readonly="readonly" cols="40" rows="6"><%= informe.getObservaciones()%></textarea>
                <%      }
                    }%>
            <%  }
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