<%@page import="skyforge.sirass.dao.procesos.ProcessDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="span3" id="sidebar">
    <%  if (request.getParameter("active") != null) {%>
    <input type="hidden" id="sidebarActive" value="<%= request.getParameter("active")%>" />
    <%  }%>
    <ul class="nav nav-list">
        <li class="nav-header">Convocatoria</li>
        <% ProcessDao pdao = new ProcessDao();
            String tipoBoton, tipoAccion, tipoIcon;
            tipoBoton = "btn-info";
            tipoAccion = "Activa";
            tipoIcon = "ok";
            boolean stat = pdao.exists("Activo", "Vigencia");
            if (stat) {
                tipoBoton = "btn-danger";
                tipoAccion = "Inactiva";
                tipoIcon = "remove";
            }
        %>
        <li>
            <button onclick="convocatoria(this)" data-id = "<%= tipoAccion%>" class="btn-mini <%= tipoBoton%>">
                <i class="icon-<%= tipoIcon%>"></i>
                <%= tipoAccion%>
            </button>
        </li>
        <li class="divider"></li>
        <li id="home">
            <a href="/SiRASS/admin/"><i class="icon-home"></i>
                Inicio
            </a>
        </li>
        <li class="nav-header">Gestionar perfil</li>
        <li id="gperfil">
            <a href="/SiRASS/admin/perfil.jsp">
                <i class="icon-edit"></i>
                Editar datos
            </a>
        </li>
        <!--    					<li class="nav-header">Generar reportes</li>
                                                <li><a href="#gemPresentacion">Carta de Presentaci&oacute;n</a></li>
                                                <li><a href="#genAceptacion">Carta de Aceptaci&oacute;n</a></li>
                                                <li><a href="#genTermino">Carta de T&eacute;rmino</a></li>
                                                <li><a href="#genKardex">K&aacute;rdex</a></li>
                                                <li><a href="#genBaja">Formato de baja</a></li>
                                                <li class="divider"></li>-->
        <!-- pendientes -->
        <li class="nav-header">Pendientes</li>
        <li id="nav-inscripciones">
            <a href="/SiRASS/admin/inscripciones"><i class="icon-tasks"></i>
                Inscripciones
            </a>
        </li>
        <!--    					<li>
                                                        <a href="#control">
                                                                <i class="icon-pencil"></i>
                                                                Control de Horas
                                                        </a>
                                                </li>
                                                <li>
                                                        <a href="#bimensual">
                                                                <i class="icon-pencil"></i>
                                                                Informe Bimensual
                                                        </a>
                                                </li>
                                                <li><a href="#final">
                                                        <i class="icon-pencil"></i>
                                                                Informe Final
                                                        </a>
                                                </li>-->
        <!-- buscar -->
        <li>
            <a href="/SiRASS/admin/consultaPrestadores.jsp">
                <i class="icon-search"></i>
                Buscar
            </a>
        </li>
        <li id="gU" >
            <a href="/SiRASS/admin/gestionUsuarios/">
                <i class="icon-pencil"></i>
                Gestion usuarios
            </a>
        </li>
        <li id="gp">
            <a href="/SiRASS/admin/gestionProgramas/">
                <i class="icon-pencil"></i>
                Gestion Programas
            </a>
        </li>
        <!-- avisos -->
        <li class="divider"></li>
        <li>
            <a href="#avisos">
                <i class="icon-exclamation-sign"></i>
                Avisos
            </a>
        </li>
    </ul>
    <script src="/SiRASS/js/makeusabrew-bootbox-85c33b4/bootbox.min.js" charset="UTF-8" type="text/javascript"></script>
</nav>