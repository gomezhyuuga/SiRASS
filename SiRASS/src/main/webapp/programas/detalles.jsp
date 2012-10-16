<%@page import="skyforge.sirass.model.Dia"%>
<%@page import="skyforge.sirass.model.programass.TipoPrograma"%>
<%@page import="skyforge.sirass.model.programass.PoblacionPrograma"%>
<%@page import="skyforge.sirass.model.programass.AlcancePrograma"%>
<%@page import="skyforge.sirass.model.programass.ActividadPrograma"%>
<%@page import="java.util.Iterator"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Página pública" />
        </jsp:include>
        <style type="text/css">
            .content p { text-align: justify; }

        </style>
    </head>
    <body>
        <!-- Navbar
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/header.jsp" />
        <!-- Contenido
            ============================== -->
        <div class="container content solid">
            <% if (request.getParameter("id") != null) {
                    try {
                        ProgramaSSDAO pdao = new ProgramaSSDAO();
                        ProgramaSS progamaSS = pdao.getByPK(Integer.parseInt(request.getParameter("id")));
                        if (!progamaSS.equals(null)) {
            %>
            <div class = "page-header">
                <h1><%= progamaSS.getNombre()%>
                    <small><%= progamaSS.getCvePrograma()%></small>
                </h1>
            </div>
            <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#DatosUnidad" data-toggle="tab">Datos de Unidad</a>
                    </li>
                    <li>
                        <a href="#DatosProg" data-toggle="tab">Datos de Programa</a>
                    </li>
                    <li>
                        <a href="#CaractProg" data-toggle="tab">Caracter&iacute;sticas</a>
                    </li>
                    <li>
                        <a href="#PerfAcad" data-toggle="tab">Perfil Acad&eacute;mico</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="DatosUnidad">
                        <div class="row">
                            <div class="span8">
                                <h3>Institución o Dependencia</h3>
                                <p><%= progamaSS.getInstitucion()%></p>
                                <h3>Área o Subdirección</h3>
                                <p><%= progamaSS.getArea()%></p>
                                <h3>Domicilio</h3>
                                <p><%= progamaSS.getDomicilio()%></p>
                            </div>
                            <div class="span4">
                                <h3>Tel&eacute;fono</h3>
                                <p><%= progamaSS.getTel()%>
                                    <%  if (progamaSS.getTelExt() != null) {
                                            out.print("ext. " + progamaSS.getTelExt());
                                        }%>
                                </p>
                                <h3>Plazas</h3>
                                <p><%= progamaSS.getPlazas()%></p>
                                <h3>Disponibles</h3>
                                <p><%= progamaSS.getVacantes()%></p>
                            </div>
                        </div>
                        <div class="page-header">
                            <h3>Responsable (s) del programa</h3>
                        </div>
                        <table class="table table-striped table-condensed">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Cargo</th>
                                    <th>Correo electr&oacute;nico</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    Set<ResponsablePrograma> responsables = (Set<ResponsablePrograma>) progamaSS.getResponsables();
                                    Iterator it3 = responsables.iterator();
                                    while (it3.hasNext()) {
                                        ResponsablePrograma res = (ResponsablePrograma) it3.next();
                                %>
                                <tr>
                                    <td><%= res.getResponsable()%></td>
                                    <td><%= res.getCargo()%></td>
                                    <td><%= res.getEmail()%></td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="DatosProg">
                        <h3>Objetivo General</h3>
                        <p><%= progamaSS.getObjGeneral()%></p>
                        <h3>Justificación</h3>
                        <p><%= progamaSS.getJustificacion()%></p>
                        <h3>Desarrollo</h3>
                        <p><%= progamaSS.getDesarrollo()%></p>
                        <h3>Recursos</h3>
                        <p><%= progamaSS.getRecursos()%></p>
                        <h3>Evaluaci&oacute;n</h3>
                        <p><%= progamaSS.getEvaluacion()%></p>
                        <h3>Resultados</h3>
                        <p><%= progamaSS.getResultados()%></p>
                    </div>
                    <div class="tab-pane" id="CaractProg">
                        <%
                            Set<ActividadPrograma> actividades = (Set<ActividadPrograma>) progamaSS.getActividad();
                            Iterator it = actividades.iterator();

                            Set<AlcancePrograma> alcances = (Set<AlcancePrograma>) progamaSS.getAlcance();
                            Iterator it1 = alcances.iterator();

                            Set<PoblacionPrograma> poblaciones = (Set<PoblacionPrograma>) progamaSS.getPoblacion();
                            Iterator it2 = poblaciones.iterator();

                            Set<TipoPrograma> tipos = (Set<TipoPrograma>) progamaSS.getTipo();
                            Iterator it4 = tipos.iterator();

                            Set<Dia> day = (Set<Dia>) progamaSS.getDias();
                            Iterator it5 = day.iterator();

                        %>
                        <table class="table table-bordered table-striped">
                            <thead>
                                <tr>
                                    <td><h3>Lugar de Realizaci&oacute;n</h3></td>
                                    <td><h3>Duraci&oacute;n</h3></td>
                                    <td><h3>Horario</h3></td>
                                    <td><h3>Tipo</h3></td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><%= progamaSS.getLugar()%></td>
                                    <td><%= progamaSS.getTiempo().getDescripcion()%> <strong>Vigencia </strong><%= progamaSS.getFechaTiempo()%></td>
                                    <td><%= progamaSS.getHorario().getDescripcion()%></td>
                                    <%while (it4.hasNext()) {
                                            TipoPrograma tp = (TipoPrograma) it4.next();
                                    %>
                                    <td>- <%= tp.getDescripcion()%></td>
                                    <% }%>
                                </tr>
                            </tbody>
                        </table>
                        <table class="table table-bordered">
                            <thead>
                                <tr>
                                    <td><h3>Alcance</h3></td>
                                    <td><h3>Poblaci&oacute;n a atender</h3></td>
                                    <td><h3>D&iacute;as de asistencia</h3></td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <table class="table table-bordered table-striped">
                                            <tbody>
                                                <%while (it1.hasNext()) {
                                                        AlcancePrograma al = (AlcancePrograma) it1.next();
                                                %>
                                                <tr>
                                                    <td>- <%= al.getDescripcion()%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </td>
                                    <td>
                                        <table class="table table-bordered table-striped">
                                            <tbody>
                                                <%while (it2.hasNext()) {
                                                        PoblacionPrograma pp = (PoblacionPrograma) it2.next();
                                                %>
                                                <tr>
                                                    <td>- <%= pp.getDescripcion()%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </td>
                                    <td>
                                        <table class="table table-bordered table-striped">
                                            <tbody>
                                                <%while (it5.hasNext()) {
                                                        Dia d = (Dia) it5.next();
                                                %>
                                                <tr>
                                                    <td>- <%= d.getDiaSemana()%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane" id="PerfAcad">
                        <table class="table table-striped table-bordered" style="width: 100%;">
                            <thead>
                                <tr>
                                    <th>Actividades</th>
                                    <th>Licenciatura</th>
                                    <th>Solicitados</th>
                                    <th>Vacantes</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    while (it.hasNext()) {
                                        ActividadPrograma ap = (ActividadPrograma) it.next();
                                %>
                                <tr>
                                    <td>
                                        <textarea rows="10" readonly="true" style="resize:none; margin: 0 auto; display: block;" class="input-xxlarge">
                                            <%= ap.getActividad()%>
                                        </textarea>
                                    </td>
                                    <td><%= ap.getLicenciatura()%></td>
                                    <td><%= ap.getnSolicitados()%></td>
                                    <td>X</td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <%  if (request.isUserInRole("admin")) {%>
            <h6 class="right">ID: <%= progamaSS.getIdPrograma()%></h6>
            <h6 class="right">Creaci&oacute;n: <%= progamaSS.getCreacion()%></h6>
            <h6 class="right">Ultima Modificaci&oacute;n: <%= progamaSS.getUltimaModif()%></h6>
            <h6 class="right">Modificado Por: <%= progamaSS.getModificadoPor()%></h6>
            <%  }
                if (request.isUserInRole("institucion")) {%>
            <h6 class="right">Creaci&oacute;n: <%= progamaSS.getCreacion()%></h6>
            <h6 class="right">Ultima Modificaci&oacute;n: <%= progamaSS.getUltimaModif()%></h6>
            <h6 class="right">Modificado Por: <%= progamaSS.getModificadoPor()%></h6>
            <%  }%>
            <% } else {
                            out.println("<h1>Programa no encontrado</h1>");
                        }
                    } catch (Exception e) {
                        out.println("<h1>Programa no encontrado</h1>");
                    }
                } else {
                    out.println("<h1>Programa no válido</h1>");
                }
            %>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="../WEB-INF/jspf/footer.jsp" />
    </body>
</html>
