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
                    String tipo;
                    Short catego = Short.parseShort(request.getParameter("categoria"));
                    if(catego == 1){
                        tipo = "Internos";
                    }else{
                        tipo = "Externos";
                    }
                    ProgramaSSDAO programaSSDAO = new ProgramaSSDAO();
                    List<ProgramaSS> list = programaSSDAO.getListCatego(new CategoriaPrograma(catego), new CEstado((short) 1));
                    if (list != null && !list.isEmpty()) {
                        Iterator<ProgramaSS> it = list.iterator();
            %>
            <div class = "page-header">
                <h1>Programas <%= tipo %></h1>
            </div>
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
                            Vacantes
                        </th>
                        <th>
                            Lugar
                        </th>
                        <th>
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
                            <%= programaSS.getNombre()%>
                        </td>
                        <td>
                            <%= programaSS.getInstitucion()%>
                        </td>
                        <td>
                            <%= programaSS.getVacantes()%>
                        </td>
                        <td>
                            <%= programaSS.getLugar()%>
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
