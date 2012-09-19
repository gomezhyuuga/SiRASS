    <nav class="navbar navbar-fixed-top" id="navbar">
		<div class="navbar-inner">
	    	<div class="container">
		    	<a class="brand" href="/SiRASS/">
		    	  SiRASS
		    	</a>
		    	<ul class="nav">
		    		<li class="divider-vertical"></li>
					<li>
						<a href="/SiRASS/programas">Programas</a>
					</li>
					<li>
						<a href="/SiRASS/ayuda">Ayuda</a>
					</li>
		    	</ul>
		    	<form class="navbar-search pull-left" method="post" action="">
		    		<input class="search-query"
		    			placeholder="Buscar prestador..."
			    		type="text" name="search" value="" />
		    	</form>
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
							<li><a href="#"><i class="icon-remove"></i>
								Gestionar programas
								</a>
							</li>
                                                        <li><a href="#"><i class="icon-exclamation-sign"></i>
								Avisos
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
