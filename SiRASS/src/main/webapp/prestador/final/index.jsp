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
					<h1>Env&iacute;a tu Informe Final</h1>
				</div>
                <div id="feedback"></div>
                <form method="post" action="/SiRASS/FormReceiver" name="form-InformeFinal" id="form-InformeFinal">
                    <input type="hidden" name="class" value="EnvioInformeFin" />
                    <input type="hidden" name="inscripcion" value="<%= inscripcion.getIdInscripcion() %>" />
                    <%@include file="/forms/envioInformeFin.jsp" %>
                    <div class="form-actions">
		            	<button class="btn btn-success btn-large" type="submit">Enviar</button>
		            	<button class="btn btn-primary btn-large" type="reset">Restablecer</button>
		            </div>
                </form>
                <h6 class="right">Fechas en formato d&iacute;a/mes/a&ntilde;o. Ej: 01/04/2012</h6>
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
        <jsp:param name="bootbox" value="true" />
        <jsp:param name="form" value="true" />
        <jsp:param name="script" value="informeFin" />
    </jsp:include>
</body>
</html>