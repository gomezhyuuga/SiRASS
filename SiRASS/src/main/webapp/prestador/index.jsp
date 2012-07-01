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
                <jsp:param name="active" value="home" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
				<div class="page-header">
					<h1>Bienvenido <small>username</small></h1>
				</div>
				<p class="lead">Parece que a&uacute;n no est&aacute;s inscrito en ning&uacute;n <strong>programa de Servicio Social</strong>. Puedes hacerlo pulsando la opci&oacute;n de <em>Inscripci&oacute;n</em> en la barra lateral de navegaci&oacute;n o pulsando el siguiente bot&oacute;n.</p>
				<a href="#" class="btn btn-primary btn-large">Inscripci&oacute;n al Servicio Social</a>
				<p>Actualmente est&aacute;s inscrito en el siguiente programa de Servicio Social:</p>
				<p class="lead"><strong>Apoyo al &aacute;rea acad&eacute;mico-administrativa en la UACM</strong></p>
				<h3>Horas acumuladas</h3>
				<div class="progress progress-success" style="height: 30px; border: 1px solid #8c8c8c;">
					<div class="bar" style="width: 70%; height: 30px;"></div>
				</div>
				<p class="lead">Tu solicitud de inscripci&oacute;n est&aacute; siendo validada por un responsable de Servicio Social. <a href="#"><strong>Ver estado</strong></a></p>
				<p>Una vez que se compruebe que es correcta, genera tu <em>Carta Compromiso</em> y entrega los papeles respectivos en las oficinas de Servicio Social.Una vez que se compruebe que es correcta, genera tu <em>Carta Compromiso</em> y entrega los papeles respectivos en las oficinas de Servicio Social.</p>
				<a href="#" class="btn btn-inverse btn-small">Generar carta compromiso</a>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
</body>
</html>
