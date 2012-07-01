<%@page contentType="text/html" pageEncoding="UTF-8"%>
            <nav class="span3" id="sidebar">
                <%  if (request.getParameter("active") != null) { %>
                <input type="hidden" id="sidebarActive" value="<%= request.getParameter("active") %>" />
                <%  } %>
				<ul class="nav nav-list">
					<li id="home">
						<a href="<%= application.getContextPath() + "/prestador" %>"><i class="icon-home"></i>
							Inicio
						</a>
						</li>
					<li id="inscripcion"><a href="inscripcion.jsp">Inscripci&oacute;n</a></li>
			        <li class="nav-header">Gestionar perfil</li>
					<li id="perfil">
						<a href="perfil.jsp">
							<i class="icon-edit"></i>
							Editar datos
						</a>
					</li>
					<li class="nav-header">Generar reportes</li>
			                        <li id="controlHoras"><a href="controlHoras.jsp">Control Mensual de Horas</a></li>
			                        <li id="informeBim"><a href="informeBim.jsp">Informe Bimensual</a></li>
					<li id="informeFin"><a href="informeFin.jsp">Informe Final</a></li>
					<li class="divider"></li>
					<!-- pendientes -->
					<li class="nav-header">Revisiones</li>
					<li id="revControl">
						<a href="revControl.jsp">
							<i class="icon-calendar"></i>
							Control de Horas
						</a>
					</li>
			                        <li id="revBim"><a href="revBimensual.jsp"><i class="icon-tasks"></i>
							Informe Bimensual
						</a>
					</li>
			                        <li id="revFin"><a href="revFinal.jsp">
						<i class="icon-eye-open"></i>
							Informe Final
						</a>
					</li>
					<!-- avisos -->
					<li class="divider"></li>
			                        <li id="avisos">
						<a href="avisos.jsp">
						<i class="icon-exclamation-sign"></i>
							Avisos
						</a>
					</li>
				</ul>
			</nav>