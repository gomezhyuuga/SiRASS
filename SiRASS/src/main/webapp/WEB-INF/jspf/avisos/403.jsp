<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Acceso denegado!" />
    </jsp:include>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="/WEB-INF/jspf/header.jsp" />
    <!-- Contenido
	============================== -->
	<div class="container content solid">
        <div class="page-header">
            <h1 class="center" style="color: #b53c3c">Acceso denegado!</h1>
		</div>
		<p class="lead">No tienes permisos suficientes para realizar esta solicitud.</p>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
