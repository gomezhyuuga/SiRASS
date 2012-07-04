<%@page contentType="text/html;charset=uft-8" pageEncoding="UTF-8"%>
	<nav class="navbar navbar-fixed-top">
	  <div class="navbar-inner">
	    <div class="container">
	    	<a class="brand" href="<%= application.getContextPath() %>">
	    	  SiRASS
	    	</a>
	    	<ul class="nav">
	    		<li class="divider-vertical"></li>
				<li>
					<a href="<%= application.getContextPath() + "/programas/" %>">Programas</a>
				</li>
				<li>
					<a href="<%= application.getContextPath() + "/ayuda/proceso.jsp" %>">Proceso</a>
				</li>
				<li>
					<a href="<%= application.getContextPath() + "/ayuda/" %>">Ayuda</a>
				</li>
	    	</ul>
	    	<span class="navbar-text pull-right">
	    		<a href="<%= application.getContextPath() + "/signup/" %>">Reg&iacute;strate</a>
	    	</span>
	    </div>
	  </div>
	</nav>
