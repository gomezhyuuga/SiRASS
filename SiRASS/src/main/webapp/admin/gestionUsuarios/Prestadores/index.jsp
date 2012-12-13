<%-- 
    Document   : index
    Created on : 11/12/2012, 11:55:21 PM
    Author     : Jorge Macias
--%>
<%@page import="skyforge.sirass.model.prestador.Prestador"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.prestador.PrestadorDAO"%>
<%
    PrestadorDAO pdao = new PrestadorDAO();
    List<Prestador> pres = pdao.getListAllFew();
%>
<div id="form-interno">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Nombre</th>
                <th>Mail</th>
                <th># Tel.</th>
                <th>Celular</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% for (Prestador p : pres) {
            %>
            <tr>
                <td><%= p.getNombre()%> <%= p.getaPaterno()%></td>
                <td><%= p.getEmail()%></td>
                <td><%= p.getTelCasa()%></td>
                <td><%= p.getTelCel()%></td>
                <td>
                    <div class="btn-group">
                        <a class="btn btn-success btn-mini" 
                           href="#" onclick="" data-id="<%= p.getIdPrestador()%>">Actualizar</a>
                        <a class="btn btn-success btn-mini dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" onclick="" data-id="<%= p.getIdPrestador()%>"><i class="icon-remove"></i> Eliminar</a>
                            </li>
                            <li>
                                <a href="#" onclick="changeDiv(this)" id="detalles" name="Pres" data-id="<%= p.getIdPrestador()%>"><i class="icon-question-sign"></i> Detalles</a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>
