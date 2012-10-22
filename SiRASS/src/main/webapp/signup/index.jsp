<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="../WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Registro en el sistema" />
    </jsp:include>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="../WEB-INF/jspf/header.jsp" />
    <!-- Contenido
	============================== -->
	<div class="container content solid">
		<div class="hero-unit">
			<h1>Regístrate</h1>
			<p>Para este tipo de registro debes ser alumno de la UACM y estar <b>inscrito</b>. Si eres alumno externo, contacta con la oficina de Servicio Social para saber cómo inscribirte.</p>
			<form method="post" class="">
				<input type="text" name="matricula" value="" placeholder="Matrícula" class="input-medium" />
				<br />
				<button class="btn btn-primary btn-large">Registrar</button>
			</form>
		</div>
                <button class="btn btn-warning btn-mini pull-right">
			<a href="institucion.jsp" style="color: inherit;">
				<i class="icon-white icon-chevron-right"></i>
				Soy una instituci&oacute;n que desea afiliar un programa de servicio social.
			</a>
		</button>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="../WEB-INF/jspf/footer.jsp">
        <jsp:param name="form" value="true" />
        <jsp:param name="datepicker" value="true" />
        <jsp:param name="bootbox" value="true" />
    </jsp:include>
</body>
</html>
