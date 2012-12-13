<%-- 
    Document   : detalles
    Created on : 12/12/2012, 03:36:06 PM
    Author     : Jorge Macias
--%>

<%@page import="skyforge.sirass.dao.institucion.InstitucionDAO"%>
<%@page import="skyforge.sirass.model.institucion.Institucion"%>
<%@page import="skyforge.sirass.dao.institucion.PlantelDAO"%>
<%@page import="skyforge.sirass.model.institucion.Plantel"%>
<%@page import="skyforge.sirass.model.institucion.CInstitucion"%>
<%@page import="skyforge.sirass.dao.institucion.CInstitucionDAO"%>
<%
    CInstitucionDAO cdao = new CInstitucionDAO();
    Plantel plant = new Plantel();
    PlantelDAO pdao = new PlantelDAO();
    Institucion ins = new Institucion();
    InstitucionDAO idao = new InstitucionDAO();
    ins = idao.getByIdInstitucion(Integer.parseInt(request.getParameter("idI")));
    CInstitucion cInstitucion = cdao.getById(Integer.parseInt(request.getParameter("id")));
    String plantel = request.getParameter("idP");
    if (plantel != null || plantel != "") {
        plant = pdao.getPlantelById(Integer.parseInt(plantel));
    }
%>

<div id="bodyId">
    <div class="tabbable">
        <div class="form-horizontal">
            <div class="tab-content">
                <div class ="tab-pane active" id="tab1">
                    <!-- Datos de la Unidad
                    ================================================== -->
                    <fieldset>
                        <legend class="center">Datos de la unidad prestataria</legend>
                        <div class="control-group">
                            <label class="control-label">Instituci&oacute;n:</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= cInstitucion.getNombre()%>" />
                            </div>
                        </div>
                        <% if (plantel != "null") {%>
                        <div class="control-group">
                            <label class="control-label">Plantel:</label>
                            <div class="controls">
                                <input type="text" readonly value ="<%= plant.getNombre()%>"/>
                            </div>
                        </div>
                        <% }%>
                        <div class="control-group">
                            <label class="control-label">Area/Subdirecci&oacute;n:</label>
                            <div class="controls">
                                <input type="text" readonly value ="<%= ins.getArea()%>"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Domicilio:</label>
                            <div class="controls">
                                <input type="text" readonly value ="<%= ins.getDomicilio()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Responsable:</label>
                            <div class="controls">
                                <input type="text" readonly value ="<%= ins.getResponsable()%>"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Cargo:</label>
                            <div class="controls">
                                <input type="text" readonly value ="<%= ins.getCargo()%>"/>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Tel&eacute;fono: </label>
                            <div class="controls">
                                Tel. <input class = "input-small" type="text" readonly value ="<%= ins.getTel()%>" />
                                Ext. <input class = "input-small" type="text" readonly value ="<%= ins.getTelExt()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Email:</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <i class="icon-envelope"></i>
                                    </span>
                                </div><input class="input-large" type="text" readonly value ="<%= ins.getEmail()%>" />
                            </div>
                        </div>
                    </fieldset> <!-- end datos de Unidad -->
                </div>
            </div>
        </div>
    </div>
</div>
