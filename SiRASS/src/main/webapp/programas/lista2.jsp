<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="skyforge.sirass.model.programass.CategoriaPrograma"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Página pública" />
        </jsp:include>
    </head>
    <body>
        <!-- Navbar
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/header.jsp" />
        <!-- Contenido
            ============================== -->
        <div class="container content solid">
            
            <%
                if (request.getParameter("categoria") != null) {
                    try {
                        int pageNumber = 1;
                        try {
                            pageNumber = Integer.parseInt(request.getParameter("page"));
                        } catch (Exception e) {
                            pageNumber = 1;
                        }
                        int total = 0;
                        String tipo;
                        Short catego = Short.parseShort(request.getParameter("categoria"));
                        if(catego == 1){
                            tipo = "Internos";
                        }else{
                            tipo = "Externos";
                        }
                        total = ProgramaSSDAO.count(catego);
                        ProgramaSSDAO programaSSDAO = new ProgramaSSDAO();
                        //List<ProgramaSS> list = programaSSDAO.getListCatego(new CategoriaPrograma(catego), new CEstado((short) 1));
                        CategoriaPrograma categoria = new CategoriaPrograma(catego);
                        CEstado estado = new CEstado((short) 1);
                        int maxResults = 10;
                        pageNumber--;
                        int start = (pageNumber * maxResults) + 1;
                        List<ProgramaSS> list = ProgramaSSDAO.myList(categoria, estado, start, maxResults);
                        if (list != null && !list.isEmpty()) {
                            Iterator<ProgramaSS> it = list.iterator();
            %>
            <div class = "page-header">
                <h1>Programas <%= tipo %> <small><%= total %></small></h1>
            </div>
            <table class="table table-striped table-condensed table-bordered">
                <thead>
                    <tr>
                        <th class="span3">
                            Nombre
                        </th>
                        <th class="span2">
                            Clave
                        </th>
                        <th class="span3">
                            Instituci&oacute;n
                        </th>
                        <th class="span1">
                            Vacantes
                        </th>
                        <th>
                            Lugar
                        </th>
                        <th style="width: 90px;">
                            Tel&eacute;fonos
                        </th>
                        <th>
                            Detalles
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <%while (it.hasNext()) {
                            ProgramaSS programaSS = it.next();
                    %>
                    <tr>
                        <td>
                            <% 
                                String nombre = programaSS.getNombre();
                                if (nombre.length() > 60) {
                                    nombre = nombre.substring(0, 60) + "...";
                                }
                                out.print(nombre);
                            %>
                        </td>
                        <td>
                            <%= programaSS.getCvePrograma() %>
                        </td>
                        <td>
                            <% 
                                String institucion = programaSS.getInstitucion();
                                if (institucion.length() > 60) {
                                    institucion = institucion.substring(0, 60) + "...";
                                }
                                out.print(institucion);
                            %>
                        </td>
                        <td style="text-align: center; vertical-align: middle;">
                            <%= programaSS.getVacantes()%>
                        </td>
                        <td>
                            <%  String lugar = programaSS.getLugar();
                                if (lugar.length() > 30) {
                                    lugar = lugar.substring(0, 30) + "...";
                                }
                                out.print(lugar);
                            %>
                        </td>
                        <td>
                            <%= programaSS.getTel()%>
                        </td>
                        <td>
                            <a class="btn btn-success btn-mini" href = "detalles.jsp?id=<%= programaSS.getIdPrograma() %>">
                                <i class = "icon-plus-sign icon-white"></i>
                                M&aacute;s
                            </a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <ul class="pager">
                <%  if (request.getParameter("page") != null &&
                        !request.getParameter("page").equals("1")) {%>
                    <li class="previous">
                        <a href="lista2.jsp?page=<%=pageNumber%>&categoria=<%=catego%>">&larr; Anteriores</a>
					</li>
                <%  }%>
                <%  if (list.size() == maxResults) {%>
                    <li class="next">
						<a href="lista2.jsp?page=<%= pageNumber + 2%>&categoria=<%=catego%>">Siguientes &rarr;</a>
					</li>
                <%  }%>
            </ul>
            <h6 class="right">
                <%  int range = pageNumber * maxResults;
                    int rangeFin = range + maxResults;
                    if (rangeFin > total) {
                        rangeFin = total;
                    }
                    out.print((range + 1) + "-" + rangeFin);
                    out.print(" de " + total);
                %>
            </h6>
                <%      } else {
                            out.println("<h1>Sin Programas</h1>");
                        }
                    } catch(Exception e){
                                out.println("<h1>Categoría incorrecta</h1>");
                    } 
                } else {
                    out.println("<h1>Categoría incorrecta</h1>");
                }
            %>

        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/footer.jsp" />
    </body>
</html>
