<%-- 
    Document   : index
    Created on : 11/12/2012, 11:55:11 PM
    Author     : Jorge Macias
--%>

<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.institucion.Plantel"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.institucion.CInstitucion"%>
<%@page import="skyforge.sirass.dao.institucion.CInstitucionDAO"%>
<%@page import="skyforge.sirass.dao.prestador.InscripcionDAO"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.model.institucion.Institucion"%>
<%@page import="skyforge.sirass.dao.institucion.InstitucionDAO"%>
<%
    InstitucionDAO idao = new InstitucionDAO();
    List<Institucion> ins = idao.getListAllFew();
%>
<div id="form-interno">
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Plantel</th>
                <th>Responsable</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <%
                CInstitucionDAO cdao = new CInstitucionDAO();
                for (Institucion i : ins) {
                    CInstitucion cIns = new CInstitucion();
                    cIns = cdao.getById(i.getIdCInstitucion());
                    Set<Plantel> p = (Set<Plantel>) cIns.getPlanteles();
                    Iterator it = p.iterator();
                    Plantel plantel = (Plantel) it.next();
            %>
            <tr>
                <td><%= plantel.getNombre()%><input type="hidden" id="Plantel" name="Plantel" value="<%= plantel.getIdPlantel() %>" /></td>
                <td><%= i.getResponsable()%><input type="hidden" id="idIns" name="idIns" value="<%= i.getIdInstitucion() %>" /></td>
                <td>
                    <div class="btn-group">
                        <a class="btn btn-success btn-mini" 
                           href="#" onclick="" data-id="<%= i.getIdCInstitucion()%>">Actualizar</a>
                        <a class="btn btn-success btn-mini dropdown-toggle" data-toggle="dropdown">
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#" onclick="" data-id="<%= i.getIdCInstitucion() %>"><i class="icon-remove"></i> Eliminar</a>
                            </li>
                            <li>
                                <a href="#" onclick="changeDiv(this)" id="detalles" name="Inst" data-id="<%= i.getIdCInstitucion() %>"><i class="icon-question-sign"></i> Detalles</a>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
            <% }%>
        </tbody>
    </table>
</div>