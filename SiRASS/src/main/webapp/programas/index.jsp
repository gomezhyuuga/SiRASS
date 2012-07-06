<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Programas de Servicio Social" />
    </jsp:include>
    <style>
			#categorias h2 { text-align: center; }
			#categorias p {	text-align: justify; }
			#categorias a {
				text-align: center;
				display: block;
			}
			
    </style>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="../WEB-INF/jspf/header.jsp" />
    <!-- Contenido
	============================== -->
	<div class="container content solid">
		<div class="hero-unit">
			<h1>Programas de Servicio Social</h1>
			<p>Por favor selecciona el tipo de programa que deseas consultar; <em>interno o externo</em>.</p>
		</div>
		<div class="row" id="categorias">
			<div class="span6">
				<h2>Externos</h2>
				<p>Se refieren al desarrollo de actividades propias del perfil académico dentro de instituciones del Sector Público Federal, Estatal o Municipal, Asociaciones Civiles sin fin de lucro y Organizaciones No Gubernamentales.</p>
				<p>Son<strong>únicamente para estudiantes de la UACM</strong>. Si eres de alguna otra institución debes revisar los programas <strong>internos</strong>.</p>
				<a href="lista.jsp?categoria=2" class="btn btn-large btn-primary" title="Lista de programas externos"><i class="icon-th-list icon-white"></i> Ver lista de programas</a>
			</div>
			<div class="span6">
				<h2>Internos</h2>
				<p>Se refieren al desarrollo de diversas actividades con el propósito de apoyar proyectos de investigación, académicos y administrativos, bajo la supervisión de personal calificado de la Universidad.</p>
				<p>Se llevan acabo en diferentes áreas de esta casa de estudios, según el perfil del estudiante.</p>
				<a href="lista.jsp?categoria=1" class="btn btn-large btn-primary" title="Lista de programas internos"><i class="icon-th-list icon-white"></i> Ver lista de programas</a>
			</div>
		</div>

	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="../WEB-INF/jspf/footer.jsp" />
</body>
</html>
