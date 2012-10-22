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
			<h1>Registro de Instituci&oacute;n</h1>
			<p>Al registrarte podr&aacute;s solicitar la afiliaci&oacute;n de <em>Programas de Servicio Social</em> con la <b>Universidad Aut&oacute;noma de la Ciudad de M&eacute;xico</b> y gestionar los mismos.</p>
		</div>
		<!-- feedback -->
		<div class="alert alert-block hide alert-error" id="feedback">
			<a class="close" data-dismiss="alert" href="#">&times;</a>
			<h4 class="alert-heading">Datos inv&aacute;lidos!</h4>
			<p>Por favor revisa los campos marcados en <strong>rojo</strong>. Los datos que ingresaste
			en ellos no son v&aacute;lidos.</p>
		</div>
		<form method="post" action="#" name="form-signup" id="form-signup" class="form-horizontal">
                    <jsp:include page="../forms/signupInstitucion.jsp" />
		</form>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="../WEB-INF/jspf/footer.jsp">
        <jsp:param name="form" value="true" />
        <jsp:param name="datepicker" value="true" />
        <jsp:param name="bootbox" value="true" />
        <jsp:param name="script" value="signup" />
        <jsp:param name="script" value="inscripcion" />
    </jsp:include>
</body>
</html>
