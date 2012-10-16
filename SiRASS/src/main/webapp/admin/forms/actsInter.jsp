<%-- 
    Document   : actsInter
    Created on : 15/10/2012, 09:24:56 PM
    Author     : Jorge Macias
--%>

<%@page import="skyforge.sirass.model.programass.CategoriaPrograma"%>
<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>

<div id="progActs">
    <%
        ProgramaSSDAO pdao = new ProgramaSSDAO();
        List<ProgramaSS> list = pdao.getListProgramasByEdoCatego(new CategoriaPrograma(1), new CEstado((short) 1));
        if (list != null && !list.isEmpty()) {
            Iterator<ProgramaSS> it = list.iterator();
    %>
    <table class = "table table-striped table-condensed">
        <thead>
            <tr>
                <th>
                    Clave
                </th>
                <th>
                    Nombre
                </th>
                <th>
                    Instituci&oacute;n
                </th>
                <th>
                    Detalles
                </th>
                <th style="width: 100px;">
                    Confirmar
                </th>
            </tr>
        </thead>
        <tbody>
            <%
                while (it.hasNext()) {
                    ProgramaSS programaSS = it.next();
                    String cve = programaSS.getCvePrograma();
                    if (cve == "null" && cve.equals(null)) {
                        cve = "Sin clave";
                    }
            %>
            <tr>
                <th>
                    <% if (cve != "Sin clave") {%>
                    <input class="input-medium" type="text"maxlength="18" name="<%= programaSS.getIdPrograma()%>" id="<%= programaSS.getIdPrograma()%>" value="<%= cve%>" />
                    <% } else {%>
                    <input class="input-medium" type="text"maxlength="18" name="<%= programaSS.getIdPrograma()%>" id="<%= programaSS.getIdPrograma()%>" placeholder="<%= cve%>" />
                    <% }%>
                </th>
                <th>
                    <%= programaSS.getNombre()%>
                </th>
                <th>
                    <%= programaSS.getInstitucion()%>
                </th>
                <th>
                    <a class="btn btn-info btn-mini" href="detalles.jsp?id=<%= programaSS.getIdPrograma()%>">
                        <i class = "icon-plus-sign icon-white"></i>M&aacute;s
                    </a>
                </th>
                <th>
        <div class="btn-group">
            <a class="btn btn-info btn-mini" href="#" onclick="cambiarCve(this)" data-id="<%= programaSS.getIdPrograma()%>">Cambiar
            </a>
            <a class="btn btn-info btn-mini dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#" onclick="suspendProg(this)" data-id="<%= programaSS.getIdPrograma()%>">
                        <i class="icon-minus"></i> Suspender</a>
                </li>
                <li>
                    <a href="#" onclick="inactProg(this)" data-id="<%= programaSS.getIdPrograma()%>">
                        <i class="icon-remove"></i> Remover</a>
                </li>
            </ul>
        </div>
        </th>
        </tr>
        <% }%>
        </tbody>
    </table>
    <%
        } else {
            out.println("<h1>No hay Programas Internos actualmente Activos</h1>");
        }
    %>
</div>
