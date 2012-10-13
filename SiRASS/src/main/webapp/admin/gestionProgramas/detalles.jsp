<%-- 
    Document   : admin
    Created on : 10-jul-2012, 16:16:52
    Author     : gomezhyuuga
--%>

<%@page import="skyforge.sirass.model.Dia"%>
<%@page import="skyforge.sirass.model.programass.TipoPrograma"%>
<%@page import="skyforge.sirass.model.programass.PoblacionPrograma"%>
<%@page import="skyforge.sirass.model.programass.AlcancePrograma"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="skyforge.sirass.model.programass.ResponsablePrograma"%>
<%@page import="java.util.Set"%>
<%@page import="skyforge.sirass.model.programass.ActividadPrograma"%>
<%@page import="skyforge.sirass.model.programass.CEstado"%>
<%@page import="skyforge.sirass.dao.programass.ProgramaSSDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.sun.org.apache.bcel.internal.generic.AALOAD"%>
<%@page import="skyforge.sirass.model.programass.ProgramaSS"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/jspf/head.jsp">
            <jsp:param name="title" value="Bienvenido Administrador" />
        </jsp:include>
    </head>
    <body>
        <!-- Navbar
        ============================== -->
        <jsp:include page="../jspf/header.jsp" />
        <!-- Body
        ============================== -->
        <div class="container content">
            <div class="row">
                <!-- Contenido
                ============================== -->
                <div class="container content solid">
                    <% if (request.getParameter("id") != null) {
                            try {
                                ProgramaSSDAO pdao = new ProgramaSSDAO();
                                ProgramaSS progamaSS = pdao.getByPK(Integer.parseInt(request.getParameter("id")));
                                int id = progamaSS.getIdPrograma();
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
                                <h3>Institución o Dependencia</h3>
                                <p><%= progamaSS.getInstitucion()%></p>
                                <h3>Área o Subdirección</h3>
                                <p><%= progamaSS.getArea()%></p>
                                <h3>Domicilio</h3>
                                <p><%= progamaSS.getDomicilio()%></p>
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
                                <h3>Tipo</h3>
                                <%while (it4.hasNext()) {
                                        TipoPrograma tp = (TipoPrograma) it4.next();
                                %>
                                <p>- <%= tp.getDescripcion()%></p>
                                <% }%>
                                <h3>Duraci&oacute;n</h3>
                                <p><%= progamaSS.getTiempo().getDescripcion()%> <strong>Vigencia </strong><%= progamaSS.getFechaTiempo()%></p>
                                <h3>Alcance</h3>
                                <%while (it1.hasNext()) {
                                        AlcancePrograma al = (AlcancePrograma) it1.next();
                                %>
                                <p>- <%= al.getDescripcion()%></p>
                                <% }%>
                                <h3>Poblaci&oacute;n a atender</h3>
                                <%while (it2.hasNext()) {
                                        PoblacionPrograma pp = (PoblacionPrograma) it2.next();
                                %>
                                <p>- <%= pp.getDescripcion()%></p>
                                <% }%>
                                <h3>Lugar de Realizaci&oacute;n</h3>
                                <p><%= progamaSS.getLugar()%></p>
                                <h3>Días de Realización</h3>
                                <%while (it5.hasNext()) {
                                        Dia d = (Dia) it5.next();
                                %>
                                <p>- <%= d.getDiaSemana()%></p>
                                <% }%>
                                <h3>Horario</h3>
                                <p><%= progamaSS.getHorario().getDescripcion()%></p>
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
                        <hr />
                        <h2>Observaciones</h2>
                        <p><i class="icon-question-sign"></i>
                            Escribir en el siguiente campo si hay alg&uacute;n error en el registro (<em>especificar dato</em>) o si es necesario hacerle notificar algo a la persona que envi&oacute; la solicitud de registro.</p>
                        <p><i class="icon-question-sign"></i>
                            Si desea actualizar, pulsar en <em><strong>Actualizar observaciones</strong></em>.</p>
                            <%--<form action="/SiRASS/Services?service=observProgram" method="post">--%>
                        <textarea name="observaciones" id="observaciones" maxlength="300" class="span8" rows="4" placeholder="Escribe alguna observaci&oacute;n"><% if(progamaSS.getNotas() != null) {
                            out.print(progamaSS.getNotas());
                            }%></textarea>
                        <input type="hidden" name="id" value="<%= progamaSS.getIdPrograma()%>" />
                        <div class="form-actions">
                            <a class="btn btn-warning" href="#" onclick="actualObservProg(this)" data-id="<%= progamaSS.getIdPrograma()%>"><i class="icon-warning-sign icon-white"></i>
                                Actualizar observaciones
                            </a>
                        </div>
                        <%--</form>--%>
                    </div>
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
            </div>
        </div>
        <!-- Footer
            ============================== -->
        <jsp:include page="/WEB-INF/jspf/footer.jsp">
            <jsp:param name="form" value="true" />
            <jsp:param name="bootbox" value="true" />
        </jsp:include>
    </body>
</html>
