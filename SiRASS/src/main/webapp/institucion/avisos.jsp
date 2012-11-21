<%-- 
    Document   : avisos
    Created on : 20/11/2012, 09:10:23 PM
    Author     : Jorge Macias
--%>
    <h1 class="titleEsp">Lista de Programas</h1>
            <hr>
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
                        <form action="editarPrograma.jsp" method="post">
                            <button name="idPrograma" class="btn btn-small btn-warning"
                                    value="<%=p.getIdPrograma()%>" type="submit">
                                <i class="icon-info-sign icon-white"></i>
                                Editar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>

