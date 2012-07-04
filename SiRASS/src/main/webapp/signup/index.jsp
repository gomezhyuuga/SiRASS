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
			<h1>Registro</h1>
			<p>Reg&iacute;strate en el sistema y empieza ahora mismo. Selecciona tu tipo de usuario.</p>
			<div id="tipoUsuario" class="centerDiv center" style="width: 205px;">
				<h4>Tipo de usuario</h4>
				<div class="btn-group" style="display: inline-block;" data-toggle="buttons-radio">
					<button class="btn btn-large btn-inverse active" id="btnPrestador">Prestador</button>
					<button class="btn btn-large btn-inverse" id="btnInstitucion">Instituci&oacute;n</button>
				</div>
			</div>
		</div>
		<!-- feedback -->
		<div class="alert alert-block hide alert-error" id="feedback">
			<a class="close" data-dismiss="alert" href="#">&times;</a>
			<h4 class="alert-heading">Datos inv&aacute;lidos!</h4>
			<p>Por favor revisa los campos marcados en <strong>rojo</strong>. Los datos que ingresaste
			en ellos no son v&aacute;lidos.</p>
		</div>
		<form method="post" action="#" name="form-signup" id="form-signup" class="form-horizontal">
            <jsp:include page="../forms/signupPrestador.jsp" />
		</form>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="../WEB-INF/jspf/footer.jsp">
        <jsp:param name="form" value="true" />
        <jsp:param name="datepicker" value="true" />
    </jsp:include>
    <script src="/SiRASS/js/signup.js" charset="ISO-8859-1" type="text/javascript"></script>
</body>
</html>
