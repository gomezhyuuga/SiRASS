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
					<h1>Consulta el estado de tu inscripción</h1>
				</div>
                
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
