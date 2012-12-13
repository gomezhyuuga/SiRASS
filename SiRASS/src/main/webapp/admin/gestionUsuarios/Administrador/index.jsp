<%-- 
    Document   : formAdmin
    Created on : 12/12/2012, 01:24:43 AM
    Author     : Jorge Macias
--%>

<%@page import="skyforge.sirass.model.admin.Administrador"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.admin.AdministradorDAO"%>
<%
    AdministradorDAO adao = new AdministradorDAO();
    List<Administrador> users = adao.getListAllFew();
%>
<div id="form-interno">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Mail</th>
                <th>Cargo</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% for (Administrador a : users) {
            %>
            <tr>
                <td><%= a.getNombre()%> <%= a.getaPaterno()%></td>
                <td><%= a.getEmail()%></td>
                <td><%= a.getCargo()%></td>
                <td>
                    <div class="btn-group">
                        <a class="btn btn-success btn-mini" 
                           href="#" onclick="" data-id="<%= a.getIdAdmin()%>">Actualizar</a>
                        <a class="btn btn-success btn-mini dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" onclick="" data-id="<%= a.getIdAdmin()%>"><i class="icon-remove"></i> Eliminar</a>
                            </li>
                            <li>
                                <a href="#" onclick="changeDiv(this)" id="detalles" name="Admin" data-id="<%= a.getIdAdmin()%>"><i class="icon-question-sign"></i> Detalles</a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>
