<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="skyforge.sirass.dao.prestador.PrestadorDAO"%>
<%@page import="skyforge.sirass.dao.user.UsuarioDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.user.Dia"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Date"%>
<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
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
                <jsp:param name="active" value="home" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
            <%  if (request.getParameter("id") == null) {%>
                <h1>Inscripci&oacute;n inv&aacute;lida</h1>
            <%} else {
                    try {
                        String idInscripcion = request.getParameter("id");
                        int id = Integer.parseInt(idInscripcion);
                        InscripcionDAO idao = new InscripcionDAO();
                        Inscripcion i = idao.getByPK(id);
                        if (i == null) {
                            out.println("<h1>No se encontr&oacute; la inscripci&oacute;n</h1>");
                        } else {
                            String estado = i.getEstado().getDescripcion();
                            String tipo = i.getTipo().getDescripcion();
                            String nombre = i.getPrestador().getNombre();
                            String aPaterno = i.getPrestador().getaPaterno();
                            String aMaterno = i.getPrestador().getaMaterno();
                            String institucion = i.getInstitucion().getNombre();
                            String plantel = i.getPlantel().getNombre();
                            String matricula = i.getMatricula();
                            String carrera = i.getCarrera();
                            String nombrePrograma = i.getPrograma();
                            String clavePrograma = i.getCvePrograma();
                            String programaInst = i.getProgramaInst();
                            String claveInst = i.getCveProgramaInst();
                            String area = i.getArea();
                            String responsable = i.getResponsable();
                            String cargoResponsable = i.getCargoResponsable();
                            String observaciones = i.getObservaciones();
                            String modificadoPor = i.getModificadoPor();
                            Date fechaInicio = i.getFechaInicio();
                            Date fechaTermino = i.getFechaFin();
                            Date horaEntrada = i.gethEntrada();
                            Date horaSalida = i.gethSalida();
                            Date creacion = i.getCreacion();
                            Date ultimaModif = i.getUltimaModif();
                            String diasAsistencia = "";
                            Set<Dia> dias = i.getDias();
                            Iterator<Dia> it = dias.iterator();
                            while (it.hasNext()) {
                                Dia dia = it.next();
                                diasAsistencia += dia.getDiaSemana();
                                diasAsistencia += " ";
                            }
                            Integer hrsAcum = (i.getHorasRealizadas() == null) ? 0 : i.getHorasRealizadas();
                            Short minsAcum = (i.getMinutosRealizados() == null) ? 0 : i.getMinutosRealizados();
                            int anioIngreso = i.getAnioIngreso();
                            int semestre = i.getSemestre();
                            double avanceCursos = i.getAvanceCursos();
                            double promedio = i.getPromedio();
                            
                            boolean dif = i.isDifundir();
                            String difundir = (dif) ? "S&iacute;" : "No";
                            String usuario;
                            int idPrestador = i.getPrestador().getIdPrestador();
                            PrestadorDAO pdao = new PrestadorDAO();
                            usuario = pdao.getUsername(idPrestador);
            %>
                <div class="page-header">
					<h1>Detalles de inscripci&oacute;n <small><%=idInscripcion%></small></h1>
				</div>
				<div class="row">
					<div class="span3"
						<p><strong>Tipo</strong>: <%=tipo%></p>
						<p><strong>Estado</strong>: <%=estado%></p>
					</div>
					<div class="span3 pull-right">
						<p><strong>Horas realizadas:</strong> <%=hrsAcum%>:<%= minsAcum%></p>
						<p><strong>Usuario:</strong> <a href="#"><%=usuario%></a></p>
					</div>
				</div>
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#datosPrestador" data-toggle="tab">Datos del prestador</a>
					</li>
					<li class="">
						<a href="#datosPrograma" data-toggle="tab">Datos del programa</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="datosPrestador">
						<h2>Nombre del solicitante</h2>
						<p><%=nombre%> <%=aPaterno%> <%=aMaterno%></p>
						<div class="row">
							<div class="span5">
								<h2>Instituci&oacute;n</h2>
								<p><%=institucion%></p>
								<h2>Plantel</h2>
								<p><%=plantel%></p>
								<h2>Avance de cursos</h2>
								<p><%=avanceCursos%>%</p>
								<h2>Semestre</h2>
								<p><%=semestre%></p>
							</div>
							<div class="span3 pull-right">
								<h2>Carrera</h2>
								<p><%=carrera%></p>
								<h2>Matr&iacute;cula</h2>
								<p><%=matricula%></p>
								<h2>Ingreso</h2>
								<p><%=anioIngreso%></p>
								<h2>Promedio</h2>
								<p><%=promedio%></p>
							</div>
						</div> <!-- end row -->
                <%      if (i.getTipo().getIdTipo() == 2) { 
                            int nCursosBasico = 0;
                            int nCursosSuperior = 0;
                            int creditos = 0;
                            if (i.getnCursosBasicos() != null) {
                                nCursosBasico = i.getnCursosBasicos();
                            }
                            if (i.getnCursosSuperior() != null) {
                                nCursosSuperior = i.getnCursosSuperior();
                            }
                            if (i.getCreditos() != null) {
                                creditos = i.getCreditos();
                            }
                %>
                        <!-- DATOS DE PRACTICAS PROFESIONALES -->
						<div class="row">
							<div class="span2">
								<h3>Cursos b&aacute;sicos</h3>
								<p><%=nCursosBasico%></p>
							</div>
							<div class="span3">
								<h3>Cursos superior</h3>
								<p><%=nCursosSuperior%></p>
							</div>
							<div class="span2">
								<h3>Creditos</h3>
								<p><%=creditos%></p>
							</div>
						</div> <!-- end row -->
                <%      }%>
						<h2>Confidencialidad de informaci&oacute;n</h2>
						<p><%=difundir%></p>
					</div> <!-- end tab-pane -->
					<div class="tab-pane" id="datosPrograma">
						<div class="row">
							<div class="span4">
								<h2>Programa</h2>
								<p><%=nombrePrograma%></p>
								<h2>Clave</h2>
								<p><%=clavePrograma%></p>
								<h2>&Aacute;rea</h2>
								<p><%=area%></p>
								<h2>Fecha de inicio</h2>
								<p><%=fechaInicio%></p>
								<h2>Fecha de t&eacute;rmino</h2>
								<p><%=fechaTermino%></p>
							</div>
							<div class="span4">
								<h2>Programa inst.</h2>
								<p><%=programaInst%></p>
								<h2>Clave</h2>
								<p><%=claveInst%></p>
								<h2>D&iacute;as de asistencia</h2>
								<p><%=diasAsistencia%></p>
								<h2>Hora de entrada</h2>
								<p><%=horaEntrada%></p>
								<h2>Hora de salida</h2>
								<p><%=horaSalida%></p>
							</div>
						</div> <!-- end row -->
						<h2>Responsable</h2>
						<p><%=responsable%> - <%=cargoResponsable%></p>
					</div> <!-- end tab pane -->
				</div> <!-- end tab-content -->
				<hr />
				<h2>Observaciones</h2>
				<p><i class="icon-question-sign"></i>
					Escribir en el siguiente campo si hay alg&uacute;n error en la inscripci&oacute;n (<em>especificar dato</em>) o si es necesario hacerle notificar algo a la persona que envi&oacute; la solicitud de inscripci&oacute;n.</p>
				<p><i class="icon-question-sign"></i>
				En caso de que sea una solicitud de inscripci&oacute;n y tenga errores, pulsar el bot&oacute;n <em><strong>Con errores</strong></em>.
				Si lo que se desea es actualizar &uacute;nicamente el campo de observaciones, pulsar en <em><strong>Actualizar observaciones</strong></em>.</p>
				<form method="get" action="./actualizar.html">
					<textarea name="observaciones" maxlength="300" class="span8" rows="4"
                              placeholder="Escribe alguna observaci&oacute;n"><%= observaciones%></textarea>
					<div class="form-actions">
						<button class="btn btn-danger" name="errores" value="1" type="submit"><i class="icon-exclamation-sign icon-white"></i>
							Inscripci&oacute;n con errores
						</button>
						<button class="btn btn-warning" name="actualizar" value="1" type="submit"><i class="icon-warning-sign icon-white"></i>
							Actualizar observaciones
						</button>
					</div>
				</form>
				<div class="right">
					<h6>Creaci&oacute;n: <small><%=creacion%></small></h6>
					<h6>Ultima modif.: <small><%=ultimaModif%></small></h6>
					<h6>Modificada por: <small><%=modificadoPor%></small></h6>
				</div>
                <%      }
                    } catch (Exception ex) {
                        out.println("<h1>Error obteniendo inscripción</h1>");
                        ex.printStackTrace();
                    }
                }%>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
