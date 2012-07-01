<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Página pública" />
    </jsp:include>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="../WEB-INF/jspf/header.jsp" />
    <!-- Contenido
	============================== -->
	<div class="container content solid">
		<h1>Hola!</h1>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="../WEB-INF/jspf/footer.jsp" />
</body>
</html>
