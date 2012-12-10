<%@page import="skyforge.sirass.model.procesos.CProcess"%>
<%@page import="skyforge.sirass.dao.procesos.ProcessDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="span3" id="sidebar">
    <%  if (request.getParameter("active") != null) {%>
    <input type="hidden" id="sidebarActive" value="<%= request.getParameter("active")%>" />
    <%  }%>
    <ul class = "nav nav-list">
        <li id="home">
            <a href="/SiRASS/institucion/"><i class="icon-home"></i>
                Inicio</a>
        </li>
        <li class = "nav-header"> Gestionar Perfil </li>

        <li id="gestPerf"><a href="perfilInst.jsp">
                <i class="icon-pencil"></i> 
                Editar Datos</a>
        </li>
        <li class = "nav-header"> Genestionar Programas </li>
        <%
            ProcessDao dao = new ProcessDao();
            CProcess p = new CProcess();
            boolean status = dao.exists(p.Vigente, "Vigencia");
            if(status){
        %>
        <li id="sendPropues"><a href="enviarPropuesta.jsp">
                <i class = "icon-pencil"></i> 

                Enviar propuesta de programa</a></li>
        <li id="actualPropues"><a href="actualProgramas.jsp"> 
                <i class = "icon-refresh"></i> 
                Actualizar programa</a>
        </li>
        <% } %>
        <li id="bajaPropues"><a href="bajaProg.jsp"> 
                <i class = "icon-remove"></i> 
                Solicitar baja de programa</a>
        </li>
        <!-- Avisos -->
        <li class = "divider"></li>
        <li id="avisosPropues">
            <a href="notifis.jsp">
                <i class = "icon-exclamation-sign"></i>
                Avisos
            </a>
        </li>		
        <li class="divider"></li>
    </ul>
</nav>