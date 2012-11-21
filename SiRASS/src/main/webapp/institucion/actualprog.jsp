<%-- 
    Document   : actualprog
    Created on : 15/05/2012, 11:25:35 PM
    Author     : JL Macias
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
                    <td ><%= p.getNombre() %></td>
                    <td ><%= p.getArea() %></td>
                    <td class="center"><%= p.getPlazas() %></td>
                    <td class="center"><%= p.getVacantes() %></td>
                    <td class="center"><%= p.getOcupadas() %></td>
                    <td>
                        <form action="editarPrograma.jsp" method="post">
                            <button name="idPrograma" class="btn btn-small btn-warning"
                                    value="<%= p.getIdPrograma() %>" type="submit">
                                <i class="icon-info-sign icon-white"></i>
                                Editar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
                <%for (ProgramaSS p2 : misProgramas2) {%>
                <tr>
                    <td ><%= p2.getNombre() %></td>
                    <td ><%= p2.getArea() %></td>
                    <td class="center"><%= p2.getPlazas() %></td>
                    <td class="center"><%= p2.getVacantes() %></td>
                    <td class="center"><%= p2.getOcupadas() %></td>
                    <td>
                        <form action="editarPrograma.jsp" method="post">
                            <button name="idPrograma" class="btn btn-small btn-warning"
                                    value="<%= p2.getIdPrograma() %>" type="submit">
                                <i class="icon-info-sign icon-white"></i>
                                Editar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
                <%for (ProgramaSS p5 : misProgramas5) {%>
                <tr>
                    <td ><%= p5.getNombre() %></td>
                    <td ><%= p5.getArea() %></td>
                    <td class="center"><%= p5.getPlazas() %></td>
                    <td class="center"><%= p5.getVacantes() %></td>
                    <td class="center"><%= p5.getOcupadas() %></td>
                    <td>
                        <form action="editarPrograma.jsp" method="post">
                            <button name="idPrograma" class="btn btn-small btn-warning"
                                    value="<%= p5.getIdPrograma() %>" type="submit">
                                <i class="icon-info-sign icon-white"></i>
                                Editar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
                <%for (ProgramaSS p6 : misProgramas6) {%>
                <tr>
                    <td ><%= p6.getNombre() %></td>
                    <td ><%= p6.getArea() %></td>
                    <td class="center"><%= p6.getPlazas() %></td>
                    <td class="center"><%= p6.getVacantes() %></td>
                    <td class="center"><%= p6.getOcupadas() %></td>
                    <td>
                        <form action="editarPrograma.jsp" method="post">
                            <button name="idPrograma" class="btn btn-small btn-warning"
                                    value="<%= p6.getIdPrograma() %>" type="submit">
                                <i class="icon-info-sign icon-white"></i>
                                Editar
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>
