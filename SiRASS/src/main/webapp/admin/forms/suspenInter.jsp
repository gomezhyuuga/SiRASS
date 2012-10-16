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
<%
    ProgramaSSDAO pdao = new ProgramaSSDAO();
%>
<div id="suspendidos">
    <%
        List<ProgramaSS> list2 = pdao.getListProgramasByEdoCatego(new CategoriaPrograma(1),new CEstado((short) 3));
        if (list2 != null && !list2.isEmpty()) {
            Iterator<ProgramaSS> it2 = list2.iterator();
    %>
    <table class = "table table-striped table-condensed">
        <thead>
            <tr>
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
                while (it2.hasNext()) {
                    ProgramaSS programaSS = it2.next();
            %>
            <tr>
                <th>
                    <%= programaSS.getNombre()%>
                    <input type="hidden" name="<%= programaSS.getIdPrograma()%>" id="<%= programaSS.getIdPrograma()%>" value="<%= programaSS.getCvePrograma()%>" />
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
            <a 
                href="#" onclick="inactProg(this)" data-id="<%= programaSS.getIdPrograma()%>" class="btn btn-danger btn-mini">Inactivo
            </a>
            <a class="btn btn-danger btn-mini dropdown-toggle" data-toggle="dropdown">
                <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#" onclick="activarProg(this)" data-id="<%= programaSS.getIdPrograma()%>"><i class="icon-plus"></i> Reactivar</a>
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
            out.println("<h1>No se ha solicitado baja de Programas Internos actualmente</h1>");
        }
    %>
</div>