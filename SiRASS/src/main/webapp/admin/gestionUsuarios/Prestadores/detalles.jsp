<%-- 
    Document   : detalles
    Created on : 12/12/2012, 03:36:34 PM
    Author     : Jorge Macias
--%>


<%@page import="skyforge.sirass.model.prestador.Prestador"%>
<%@page import="skyforge.sirass.dao.prestador.PrestadorDAO"%>
<%
    PrestadorDAO dao = new PrestadorDAO();
    Prestador prestador = dao.getPrestadorByPK(Integer.parseInt(request.getParameter("id")));
%>

<div id="bodyId">
    <div class ="tabbable">
        <div class="form-horizontal">
            <div class="tab-content">
                <div class="tab-pane active" id="tab1">
                    <fieldset>
                        <legend class="center">Datos de Prestador</legend>
                        <div class="control-group">
                            <label class="control-label">N&uacute;mero de Control</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= prestador.getnControl()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Nombre</label>
                            <div class="controls">
                                <input type="text" readonly="readonly" value="<%= prestador.getNombre()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Apellido Paterno</label>
                            <div class="controls">
                                <input type="text" readonly="readonly" value="<%= prestador.getaPaterno()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Apellido Materno</label>
                            <div class="controls">
                                <input type="text" readonly="readonly" value="<%= prestador.getaMaterno()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Fecha de Nacimiento</label>
                            <div class="controls">
                                <input type="text" class="input-small" readonly="readonly" value="<%= prestador.getNacimiento()%>" />
                            </div>
                        </div>
                        <%
                            String chM = "", chF = "";
                            if (prestador.getSexo() == 'M') {
                                chM = "checked";
                            }
                            if (prestador.getSexo() == 'F') {
                                chF = "checked";
                            }
                        %>
                        <div class="control-group">
                            <label class="control-label" for="sexo">Sexo:</label>
                            <div class="controls">
                                <label class="radio inline">
                                    <input type="radio" disabled id="sexo" name="sexo" value="M" <%= chM%> />
                                    Masculino
                                </label>
                                <label class="radio inline">
                                    <input type="radio" disabled name="sexo" value="F" <%= chF%>/>
                                    Femenino
                                </label>
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Calle</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= prestador.getdCalle()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">N&uacute;mero</label>
                            <div class="controls">
                                <input type="text" readonly class="input-mini" value="<%= prestador.getdNumExt()%>" />
                                <input type="text" readonly class="input-mini" value="<%= prestador.getdNumInt()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">C&oacute;digo Postal</label>
                            <div class="controls">
                                <input type="text" class="input-mini"  readonly value="<%= prestador.getdCP()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Colonia</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= prestador.getdColonia()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Delegaci&oacute;n</label>
                            <div class="controls">
                                <input type="text" readonly value="<%= prestador.getdDelegacion()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label">Tel&eacute;fono</label>
                            <div class="controls">
                                Casa <input type="text" class="input-small" readonly value="<%= prestador.getTelCasa()%>" />
                                Celular <input type="text" class="input-small" readonly value="<%= prestador.getTelCel()%>" />
                            </div>
                        </div>
                        <div class="control-group">
                            <label class="control-label" >Email:</label>
                            <div class="controls">
                                <div class="input-prepend">
                                    <span class="add-on">
                                        <i class="icon-envelope"></i>
                                    </span>
                                </div><input class="input-large" type="text" readonly value ="<%= prestador.getEmail()%>" />
                            </div>
                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
</div>
