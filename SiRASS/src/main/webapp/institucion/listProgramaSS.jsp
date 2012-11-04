<%-- 
    Document   : listProgramaSS
    Created on : 15/05/2012, 10:05:33 AM
    Author     : JL Macias
--%>

<p class="lead">Esta es tu lista lista de Programas Registrados</p>
<table class="table table-striped table-bordered">
    <tr>
        <th>Nombre Programa:</th>
        <th>Area:</th>
        <th>Plazas</th>
        <th>Vacantes</th>
        <th>Ocupadas</th>
        <th>Acci&oacute;n</th>
    </tr>
    <%for (ProgramaSS p : misProgramas) {%>
    <tr>
        <td ><%=p.getNombre()%></td>
        <td ><%=p.getArea()%></td>
        <td class="center"><%=p.getPlazas()%></td>
        <td class="center"><%=p.getVacantes()%></td>
        <td class="center"><%=p.getOcupadas()%></td>
        <td>
            <a href="../programas/detalles.jsp?id=<%=p.getIdPrograma()%>" target="_blank" class="btn btn-small btn-warning">
                <i class="icon-info-sign icon-white"></i>
                Ver
            </a>
        </td>
    </tr>
    <% }%>
</table>