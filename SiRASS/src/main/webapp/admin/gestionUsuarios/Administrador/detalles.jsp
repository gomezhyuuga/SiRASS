<%-- 
    Document   : detalles
    Created on : 12/12/2012, 03:27:40 PM
    Author     : Jorge Macias
--%>

<%@page import="skyforge.sirass.model.admin.Administrador"%>
<%@page import="skyforge.sirass.dao.admin.AdministradorDAO"%>
<%
    AdministradorDAO dao = new AdministradorDAO();
    Administrador admin = dao.getAdministradorByPK(Integer.parseInt(request.getParameter("id")));

%>

<div id="bodyId">
    <div class ="tabbable">
        <div class="form-horizontal">
            <div class="tab-content">
                <div class="tab-pane active" id="tab1">
                    <fieldset>
                        <legend class="center">Datos de Admin</legend>
                        <div class="control-group">
                            <label class="control-label">Nombre</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= admin.getNombre()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Apellido Paterno</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= admin.getaPaterno()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Apellido Materno</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= admin.getaMaterno()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Fecha de Nacimiento</label>
                            <div class="controls">
                                <input type="text" class="input-small" readonly value="<%= admin.getNacimiento()%>" />
                            </div>
                        </div>
                        <%
                            String chM = "", chF = "";
                            if (admin.getSexo() == 'M') {
                                chM = "checked";
                            }
                            if (admin.getSexo() == 'F') {
                                chF = "checked";
                            }
                        %>
                        <div class="control-group">
                            <label class="control-label" >Sexo:</label>
                            <div class="controls">
                                <label class="radio inline">
                                    <input type="radio" disabled value="M" <%= chM%> />
                                    Masculino
                                </label>
                                <label class="radio inline">
                                    <input type="radio" disabled value="F" <%= chF%>/>
                                    Femenino
                                </label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Cargo</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= admin.getCargo()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Email:</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <i class="icon-envelope"></i>
                                    </span>
                                </div><input class="input-large" readonly type="text" value ="<%= admin.getEmail()%>" />
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
</div>
