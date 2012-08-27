<%@page import="skyforge.sirass.model.prestador.Inscripcion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/jspf/head.jsp">
        <jsp:param name="title" value="Bienvenido Prestador" />
    </jsp:include>
    <style>
        .red {color: red;}
    </style>
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
                <jsp:param name="active" value="controlHoras" />
            </jsp:include>
			<!-- Contenido
			============================== -->
			<div class="span8 solid">
<%          if (session.getAttribute("inscripcion") != null) {
                Inscripcion inscripcion = (Inscripcion) session.getAttribute("inscripcion");
                int idEstado = inscripcion.getEstado().getIdEstado();
                if (idEstado != 2) { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="<%= idEstado%>" />
                </jsp:include>
<%              } else { %>
                <div class="page-header">
					<h1>Control Mensual de Horas</h1>
				</div>
				<p>Desde esta página puedes enviar tu <em><strong>Control Mensual de Horas</strong></em>. Llena los siguientes campos, después pulsa en enviar y espera a que un <em>administrador</em> <strong>revise</strong> tu reporte.</p>
				<div id="feedback"></div>
                <form method="get" action="/SiRASS/FormReceiver" class="form-horizontal" name="form-controlHoras" id="form-controlHoras">
                    <input type="hidden" name="class" value="EnvioControlHoras" />
                    <%@include file="../../forms/envioCtrlHoras.jsp" %>
                </form>
                <h6 class="right">Fechas en formato día/mes/año. Ej: 01/04/2012</h6>
				<h6 class="right">Horas en formato HH:MM. Ej: 09:30</h6>
<%              }
            } else { %>
                <jsp:include page="/WEB-INF/jspf/avisos/inscripcion.jsp">
                        <jsp:param name="aviso" value="0" />
                </jsp:include>
<%          } %>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp">
        <jsp:param name="datepicker" value="true" />
        <jsp:param name="bootbox" value="true" />
        <jsp:param name="form" value="true" />
        <jsp:param name="script" value="controlHoras" />
    </jsp:include>
</body>
</html>
