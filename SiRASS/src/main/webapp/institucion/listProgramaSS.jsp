<%-- 
    Document   : listProgramaSS
    Created on : 15/05/2012, 10:05:33 AM
    Author     : JL Macias
--%>

    <h1 class="titleEsp">Lista de Programas Registrados</h1>
            <hr>
            <table class="table table-striped table-bordered">
                <tr>
                    <th>Nombre Programa:</th>
                    <th>Area:</th>
                    <th>Email:</th>
                    <th>Telefono</th>
                    <th>Plazas</th>
                    <th>Vacantes</th>
                    <th>Ocupadas</th>
                    <th>Acci&oacute;n</th>
                </tr>
                <%for (ProgramaSS p : misProgramas) {%>
                <tr>
                    <td ><%=p.getNombre()%></td>
                    <td ><%=p.getArea()%></td>
                    <td><%=p.getEmail()%></td>
                    <% String telext = p.getTelExt();
                       String tel = p.getTel();
                    %>
                    <% if(telext == null){ telext = "sin extensi&oacute;n";} %>
                    <td ><%= tel %> - <%= telext %></td>
                    <td class="center"><%=p.getPlazas()%></td>
                    <td class="center"><%=p.getVacantes()%></td>
                    <td class="center"><%=p.getOcupadas()%></td>
                    <td>
                        <form action="viewProgramSS.jsp" method="get">
                            <button name="idPrograma" class="btn btn-small btn-warning"
                                    value="<%=p.getIdPrograma()%>" type="submit">
                                <i class="icon-info-sign icon-white"></i>
                                Ver
                            </button>
                        </form>
                    </td>
                </tr>
                <% } %>
            </table>