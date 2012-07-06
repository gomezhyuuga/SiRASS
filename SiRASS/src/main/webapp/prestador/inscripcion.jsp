<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Bienvenido Prestador" />
    </jsp:include>
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
                if (idEstado == 6) { %>
                <p>Puedes inscribirte!</p>
<%              } else if (idEstado == 2) { %>
                <p class="lead">Ya est&aacute;s inscrito. No puedes volver a inscribirte a
                    otro programa hasta que hayas
                    terminado este periodo de Servicio Social.</p>
<%              } else { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="<%= idEstado%>" />
                </jsp:include>
<%              }
            } else { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="6" />
                </jsp:include>
<%          } %>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
