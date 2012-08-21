<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="P&aacute;gina no encontrada!" />
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
            <h1 class="center" style="color: #b53c3c">UPS! P&aacute;gina no encontrada :-(</h1>
		</div>
		<p class="lead">Lo sentimos pero la p&aacute;gina que solicitaste no pudo encontrarse en el servidor.</p>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
