<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <footer class="container">
  		<hr />
		<div class="row">
			<div class="span4">
				<h6>Skyforge - 2012</h6>
			</div>
			<div class="span4 offset4 right">
				<h6><a href="http://www.uacm.edu.mx/">UACM - "Nada humano me es ajeno"</a></h6>
			</div>
		</div>
	</footer>
	<!-- Scripts
	============================== -->
	<!-- jQuery 1.7.2
	============================== -->
	<script src="/SiRASS/js/jquery-1.7.2.min.js" type="text/javascript"></script>
	<!-- Bootstrap
	============================== -->
	<script src="/SiRASS/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <%  if (request.getParameter("form") != null &&
                request.getParameter("form").equals("true")) { %>
        <!-- AJAX FORM -->
	<script src="/SiRASS/js/jquery.form.js" type="text/javascript"></script>
        <!-- jquery-validation
	============================== -->
	<script src="/SiRASS/js/jquery-validation-1.9.0/jquery.validate.min.js" type="text/javascript"></script>
	<script src="/SiRASS/js/jquery-validation-1.9.0/additional-methods.min.js" type="text/javascript"></script>
	<script src="/SiRASS/js/jquery-validation-1.9.0/localization/messages_es.js" type="text/javascript"></script>
        <%  } %>