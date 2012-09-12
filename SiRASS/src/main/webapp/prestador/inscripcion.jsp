<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Bienvenido Prestador" />
    </jsp:include>
    <style>
        .red {color: red;}
    </style>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="jspf/header.jsp" />
	<!-- Body
	============================== -->
	<div class="container content">
		<div class="row">
			<!-- Sidebar
			============================== -->
            <jsp:include page="jspf/sidebar.jsp">
                <jsp:param name="active" value="inscripcion" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
				<div class="page-header">
					<h1>Bienvenido <small><%= request.getUserPrincipal().getName() %></small></h1>
				</div>
<%          if (session.getAttribute("inscripcion") != null) {
                Inscripcion inscripcion = (Inscripcion) session.getAttribute("inscripcion");
                int idEstado = inscripcion.getEstado().getIdEstado();
                if (idEstado == 2) { %>
                <p class="lead">Ya est&aacute;s inscrito. No puedes volver a inscribirte a
                    otro programa hasta que hayas
                    terminado este periodo de Servicio Social.</p>
<%              } else { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="<%= idEstado%>" />
                </jsp:include>
<%              }
            } else { %>
                <div class="page-header">
					<h1>Inscripción al Servicio Social</h1>
				</div>
				<p>Para inscribirte en un programa de Servicio Social llena los siguientes datos.</p>
                <div id="feedback"></div>
                <form method="post" action="/SiRASS/FormReceiver" class="form-horizontal" id="form-inscripcion" name="form-inscripcion">
                    <jsp:include page="/forms/inscripcion.jsp" />
                </form>
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
        <jsp:param name="script" value="inscripcion" />
    </jsp:include>
</body>
</html>
