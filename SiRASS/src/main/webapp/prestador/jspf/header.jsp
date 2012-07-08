<%@page import="skyforge.sirass.model.prestador.EstadoInscripcion"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page import="skyforge.sirass.dao.prestador.PrestadorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <nav class="navbar navbar-fixed-top" id="navbar">
		<div class="navbar-inner">
	    	<div class="container">
		    	<a class="brand" href="<%= application.getContextPath() %>">
		    	  SiRASS
		    	</a>
		    	<ul class="nav">
		    		<li class="divider-vertical"></li>
					<li>
						<a href="#">Programas</a>
					</li>
					<li>
						<a href="#">Reportes</a>
					</li>
					<li>
						<a href="#">Ayuda</a>
					</li>
		    	</ul>
				<!-- opciones de cuenta -->
				<ul class="nav pull-right">
					<li class="divider-vertical"></li>
					<li class="dropdown">
						<a href="#"
							class="dropdown-toggle" data-toggle="dropdown">
						<i class="icon-user icon-white"></i>
							<%= request.getUserPrincipal().getName() %>
							<b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li><a href="perfil.jsp"><i class="icon-user"></i>
								Perfil personal
								</a>
							</li>
							<li class="divider"></li>
							<li class="nav-header">Gestionar Servicio</li>
							<li><a href="#"><i class="icon-remove"></i>
								Solicitar baja
								</a>
							</li>
							<li><a href="#"><i class="icon-share-alt"></i>
								Cambiar de programa
								</a>
							</li>
							<li><a href="./suspender.jsp"><i class="icon-pause"></i>
								Suspender servicio
								</a>
							</li>
							<li class="divider"></li>
							<li><a href="/SiRASS/Logout "><i class="icon-off"></i>
									Cerrar sesi&oacute;n
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
<%  // Obtención de inscripción
    if (session.getAttribute("inscripcion") == null) {
        PrestadorDAO prestadorDAO = new PrestadorDAO();
        Inscripcion inscripcion = prestadorDAO.getCurrentInscripcion(request.getUserPrincipal().getName());
        if (inscripcion != null) {
            session.setAttribute("inscripcion", inscripcion);
        } else {
            Inscripcion insc = new Inscripcion();
            insc.setEstado(new EstadoInscripcion((short) 6));
            session.setAttribute("inscripcion", insc);
        }
    }
%>