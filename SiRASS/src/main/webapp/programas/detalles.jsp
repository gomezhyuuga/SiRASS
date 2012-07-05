<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="../WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Página pública" />
    </jsp:include>
</head>
<body>
    <!-- Navbar
	============================== -->
    <jsp:include page="../WEB-INF/jspf/header.jsp" />
    <!-- Contenido
	============================== -->
	<div class="container content solid">
            <% if(request.getParameter("id") != null){ 
                    try{
                        ProgramaSSDAO pdao = new ProgramaSSDAO();
                        ProgramaSS progamaSS = pdao.getByPK(Integer.parseInt(request.getParameter("id")));
                        if(!progamaSS.equals(null)){
            %>
		<div class = "page-header">
			<h1><%= progamaSS.getNombre() %>
                            <small><%= progamaSS.getCvePrograma() %></small>
			</h1>
		</div>
		<div class="tabbable">
		  <ul class="nav nav-tabs">
		    <li class="active">
		    	<a href="#DatosUnidad" data-toggle="tab">Datos de Unidad</a>
		    </li>
		    <li>
		    	<a href="#DatosProg" data-toggle="tab">Datos de Programa</a>
		    </li>
		    <li>
		    	<a href="#CaractProg" data-toggle="tab">Caracter&iacute;sticas</a>
		    </li>
		    <li>
		    	<a href="#PerfAcad" data-toggle="tab">Perfil Acad&eacute;mico</a>
		    </li>
		  </ul>
		  <div class="tab-content">
		    <div class="tab-pane active" id="DatosUnidad">
		    	<h3>Institución o Dependencia</h3>
		    		<p><%= progamaSS.getInstitucion() %></p>
				<h3>Área o Subdirección</h3>
					<p><%= progamaSS.getArea() %></p>
				<h3>Domicilio</h3>
					<p><%= progamaSS.getDomicilio() %></p>
                                <div class="page-header">
                                    <h3>Responsable (s) del programa</h3>
                                </div>
                                <table class="table table-striped table-condensed">
                                    <thead>
                                        <tr>
                                            <th>Nombre</th>
                                            <th>Cargo</th>
                                            <th>Tel&eacute;fono</th>
                                            <th>Correo electr&oacute;nico</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th>Rodrigo Vidal Tamayo Ramírez</th>
                                            <th>Director de Educación, Ciencia y Sociedad</th>
                                            <th>55 12 10 12 <strong>Ext.</strong> 248</th>
                                            <th>rvidal@icyt.df.gob.mx</th>
                                        </tr>
                                    </tbody>
                                </table>
		    </div>
		    <div class="tab-pane" id="DatosProg">
		    	<h3>Objetivo General</h3>
		    		<p><%= progamaSS.getObjGeneral() %></p>
		    	<h3>Justificación</h3>
		    		<p><%= progamaSS.getJustificacion() %></p>
                        <h3>Desarrollo</h3>
                            <p><%= progamaSS.getDesarrollo() %></p>
			<h3>Recursos</h3>
                            <p><%= progamaSS.getRecursos() %></p>
				<h3>Evaluaci&oacute;n</h3>
					<p><%= progamaSS.getEvaluacion() %></p>
				<h3>Resultados</h3>
					<p><%= progamaSS.getResultados() %></p>
		    </div>
		    <div class="tab-pane" id="CaractProg">
		    	<h3>Tipo</h3>
		    		<p><%= progamaSS.getTipo() %></p>
		    	<h3>Duraci&oacute;n</h3>
		    		<p><%= progamaSS.getTiempo() %></p>
		    	<h3>Alcance</h3>
		    		<p><%= progamaSS.getAlcance() %></p>
		    	<h3>Poblaci&oacute;n a atender</h3>
		    		<p><%= progamaSS.getPoblacion() %></p>
		    	<h3>Lugar de Realizaci&oacute;n</h3>
		    		<p><%= progamaSS.getLugar() %></p>
		    	<h3>Días de Realización</h3>
		    		<p><%= progamaSS.getDias() %></p>
		    	<h3>Horario</h3>
		    		<p><%= progamaSS.getHorario() %></p>
		    </div>
		    <div class="tab-pane" id="PerfAcad">
		    	<table class="table table-striped table-bordered" style="width: 100%;">
		    		<thead>
		    			<tr>
		    				<th>Actividades</th>
		    				<th>Licenciatura</th>
		    				<th>Solicitados</th>
		    				<th>Vacantes</th>
		    			</tr>
		    		</thead>
		    		<tbody>
		    			<tr>
		    				<td>
<textarea rows="10" readonly="true" style="resize:none; margin: 0 auto; display: block;" class="input-xxlarge">
<%= progamaSS.getActividad() %>
</textarea>
							</td>
							<td> -Comunicación y cultura</td>
							<td>5</td>
							<td>X</td>
		    			</tr>
		    			<tr>
		    				<td></td>
		    				<td>-Arte y patrimonio cultural</td>
		    				<td>5</td>
		    				<td>X</td>
		    			</tr>
		    			<tr>
		    				<td></td>
		    				<td>-Promoción de la salud</td>
		    				<td>5</td>
		    				<td>X</td>
		    			</tr>
		    		</tbody>
		    	</table>
		    </div>
		  </div>
		</div>
           <% }else{
                    out.println("<h1>Programa no encontrado</h1>");
               }
                    }catch(Exception e){
                    out.println("<h1>Programa no encontrado</h1>");
               }
               }else{
                out.println("<h1>Programa no válido</h1>");
               }
           %>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="../WEB-INF/jspf/footer.jsp" />
</body>
</html>