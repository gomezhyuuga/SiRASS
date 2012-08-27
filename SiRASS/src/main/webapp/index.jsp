<%
    if (request.getUserPrincipal() != null &&
            session.getAttribute("home") != null) {
        String pag = (String) session.getAttribute("home");
        response.sendRedirect(application.getContextPath() + "/" + pag);
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <jsp:include page="WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Sistema de Registro y Administraci&oacute;n de Servicio Social" />
        <jsp:param name="style" value="home" />
    </jsp:include>
</head>
<body>
	<!-- Header
	============================== -->
	<header class="gradient">
		<div class="container">
			<h1>SiRASS, <em>Servicio Social</em></h1>
			<p>Servicio Social simplificado. Olv&iacute;date de llenar tus formatos a mano y de las tediosas revisiones.</p>
            <a class="btn btn-primary btn-large" data-toggle="modal" href="#modal-login">Iniciar sesi&oacute;n</a>
            <a class="btn btn-success btn-large" href="/SiRASS/signup/">Registrarse</a>
		</div>
	</header>
	<!-- Contenido
	============================== -->
	<div class="container content solid">
		<div class="row">
			<div class="span4">
				<img src="/SiRASS/assets/list_icon.png" alt="Lista de programas de Servicio Social" />
				<h2>Programas</h2>
				<p>Conoce todos los programas de Servicio Social que ofrece para ti la UACM.</p>
				<a class="btn btn-info" href="/SiRASS/programas/">Lista de programas &raquo;</a>
			</div>
			<div class="span4">
				<img src="/SiRASS/assets/process_icon.png" alt="Pasos a realizar para empezar el Servicio Social" />
				<h2>Proceso</h2>
				<p>Echa un vistazo a los procesos que se deben llevar a cabo para realizar tu Servicio Social.</p>
				<a class="btn btn-info" href="#">Ver diagramas &raquo;</a>
			</div>
			<div class="span4">
				<img src="/SiRASS/assets/help_icon.png" alt="Obtén ayuda acerca del Servicio Social en la UACM" />
				<h2>Ayuda</h2>
				<p>Aclara tus dudas respecto a qu&eacute; necesitas para realizar tu Servicio Social.</p>
				<a class="btn btn-info" href="#">Obtener ayuda &raquo;</a>
			</div>
		</div>
	</div>
	<!-- Login oculto
	============================== -->
	<div class="modal hide fade" id="modal-login">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">×</button>
			<h3>Iniciar sesión</h3>
		</div>
		<form method="post" action="/SiRASS/Login" name="form-login" id="form-login" class="form-inline">
			<div class="modal-body">
				<div id="feedback" class="alert alert-block hide alert-error">
					<a class="close" data-dismiss="alert" href="#">x</a>
					<h4 class="alert-heading">Datos inv&aacute;lidos!</h4>
                    <ul></ul>
				</div>
                <%  if (request.getParameter("error") != null &&
                        request.getParameter("error").equals("true")) { %>
                    <input type="hidden" name="errorLogin" id="errorLogin" value="true" />
                    <div class="alert alert-block alert-error">
                        <a class="close" data-dismiss="alert" href="#">x</a>
                        <h4 class="alert-heading">Ha ocurrido un error!</h4>
                        <p>Lo sentimos pero tu <em><strong>usuario y/o contraseña</strong></em> parecen estar erróneos. 
                        Intenta de nuevo por favor.</p>
                    </div>
                <%  } %>
				<fieldset>
					<input type="text" name="username" id="username" class="input-medium" placeholder="Usuario"
						maxlength="15" autofocus="autofocus" />
					<input type="password" name="password" id="password" class="input-medium" placeholder="Contraseña"
						maxlength="16" />
					<label for="recordar" class="checkbox">
						<input type="checkbox" name="recordar" id="recordar" />
						Recordarme
					</label>
				</fieldset>
			</div>
			<div class="modal-footer">
				<a href="#" class="btn" data-dismiss="modal">Cerrar</a>
				<button type="submit" class="btn btn-success">Login</button>
			</div>
		</form>
	</div>
	<!-- Footer
	============================== -->
    <jsp:include page="WEB-INF/jspf/footer.jsp">
        <jsp:param name="form" value="true" />
        <jsp:param name="script" value="login" />
    </jsp:include>
</body>
</html>