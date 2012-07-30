<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="skyforge.sirass.model.prestador.EstadoInscripcion"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.prestador.InscripcionDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Bienvenido Administrador" />
        </jsp:include>
    </head>
    <body>
        <!-- Navbar
	============================== -->
    <jsp:include page="../jspf/header.jsp" />
	<!-- Body
	============================== -->
	<div class="container content">
		<div class="row">
			<!-- Sidebar
			============================== -->
            <jsp:include page="../jspf/sidebar.jsp">
                <jsp:param name="active" value="nav-inscripciones" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
				<div class="page-header">
					<h1>Inscripciones</h1>
				</div>
				<p class="lead">Selecciona una opción para filtrar las inscripciones.</p>
				<ul class="nav nav-pills">
					<li>
						<a href="./">En espera</a>
					</li>
					<li>
						<a href="invalidas.jsp">Con errores</a>
					</li>
					<li>
						<a href="correctas.jsp">Correctas</a>
					</li>
					<li class="active">
						<a href="#">En servicio</a>
					</li>
					<li>
						<a href="suspendidas.jsp">Suspendido</a>
					</li>
					<li>
						<a href="canceladas.jsp">Cancelado</a>
					</li>
					<li>
						<a href="finalizadas.jsp">Finalizado</a>
					</li>
				</ul>
                <div id="registros">
                <%
                    InscripcionDAO idao = new InscripcionDAO();
                    List<Inscripcion> inscripciones = idao.getFewWithStatus(EstadoInscripcion.EN_SERVICIO);
                    if (inscripciones == null || inscripciones.isEmpty()) {
                        out.print("<h1>Sin inscripciones</h1>");
                    } else {%>
                    <table class="table table-striped table-bordered">
						<thead>
							<tr>
								<th>Prestador</th>
                                <th class="span1">Tipo</th>
								<th>Programa</th>
                                <th style="width: 100px;">Acciones</th>
							</tr>
						</thead>
						<tbody>
                        <%  for (Inscripcion i : inscripciones) {
                            String nombre = "";
                            nombre = i.getPrestador().getNombre() + " ";
                            nombre += i.getPrestador().getaPaterno() + " ";
                            nombre += i.getPrestador().getaMaterno();
                            String tipo = "";
                            tipo = i.getTipo().getDescripcion();
                            String programa = "";
                            programa = i.getPrograma();
                            int id = i.getIdInscripcion();
                        %>
							<tr>
								<td><%= nombre %></td>
								<td><%= tipo %></td>
								<td><%= programa %></td>
								<td>
									<div class="btn-group">
										<a class="btn btn-info btn-mini" href="detalles.jsp?id=<%=id%>">Revisar</a>
										<a class="btn btn-info btn-mini dropdown-toggle" data-toggle="dropdown">
											<span class="caret"></span>
										</a>
										<ul class="dropdown-menu">
											<li>
                                                <a href="#" onclick="inscribirPrestador(this)" data-id="<%=id%>"><i class="icon-ok"></i>
													Aceptar
												</a>
											</li>
											<li>
												<a href="#" onclick="rechazarPrestador(this)" data-id="<%=id%>"><i class="icon-remove"></i>
													Rechazar
												</a>
											</li>
											<li>
												<a href="#" onclick="reactivarInscripcion(this)" data-id="<%=id%>">Reactivar</a>
											</li>
											<li>
												<a href="#" onclick="eliminarInscripcion(this)" data-id="<%=id%>">Eliminar</a>
											</li>
											<li>
												<a href="#" onclick="suspenderIncripcion(this)" data-id="<%=id%>">Suspender</a>
											</li>
											<li>
												<a href="#" onclick="liberarServicio(this)" data-id="<%=id%>">Liberar servicio</a>
											</li>
										</ul>
									</div>
								</td>
							</tr>
                        <%}%>
						</tbody>
					</table>
                <%} %>
                </div>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp">
        <jsp:param name="form" value="true" />
        <jsp:param name="bootbox" value="true" />
    </jsp:include>
    </body>
</html>
