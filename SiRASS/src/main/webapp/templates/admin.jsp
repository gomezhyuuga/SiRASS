<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

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
					<h1>Bienvenido admin</h1>
				</div>
			</div>
		</div>
	</div>
    <!-- Footer
	============================== -->
    <jsp:include page="/WEB-INF/jspf/footer.jsp" />
    </body>
</html>
