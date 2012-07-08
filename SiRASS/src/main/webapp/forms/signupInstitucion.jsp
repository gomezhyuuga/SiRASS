<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="skyforge.sirass.model.institucion.CInstitucion"%>
<%@page import="skyforge.sirass.dao.institucion.CInstitucionDAO"%>
<%@page import="skyforge.sirass.dao.institucion.PlantelDAO"%>
<input type="hidden" name="class" value="SignupInstitucion" />
<div class="row">
	<div class="span6">
		<!-- Datos de la instituci&oacute;n
		============================== -->
		<legend>Datos de la instituci&oacute;n</legend>
		<div class="control-group">
			<label for="institucionList" class="control-label">Instituci&oacute;n o dependencia:</label>
			<div class="controls">
				<select name="institucionList" id="institucionList" class="input-xlarge" onchange="changeInstitucion(this)">
					<option selected="selected" value="">-- Selecciona tu instituci&oacute;n --</option>
					<optgroup label="No registrada">
						<option value="unregistred">Otra</option>
					</optgroup>
					<optgroup id="instRegistradas" label="Registradas">
                    <%  CInstitucionDAO cidao = new CInstitucionDAO();
                        List<CInstitucion> insts = cidao.getAll(CInstitucion.class);
                        if (insts != null && !insts.isEmpty()) { 
                            Iterator<CInstitucion> it = insts.iterator();
                            while (it.hasNext()) {
                                CInstitucion inst = it.next(); %>
                        <option value="<%= inst.getIdCInstitucion() %>"><%= inst.getNombre() %></option>
                    <%      }
                        } else { %>
                        <option value="">Sin instituciones</option>
                    <%  } %>
					</optgroup>
				</select>
			</div>
		</div>
		<div class="control-group hide" id="otraInstitucion">
			<label for="nombreInstitucion" class="control-label">Nombre:</label>
			<div class="controls">
				<input type="text" id="nombreInstitucion" name="nombreInstitucion"
					placeholder="Nombre de la instituci&oacute;n" class="input-xlarge" maxlength="150" />
			</div>
		</div>
		<div class="control-group">
			<label for="plantelList" class="control-label">Plantel:</label>
			<div class="controls">
				<select name="plantelList" id="plantelList" class="input-xlarge" onchange="changeInstitucion(this)">
					<option value="">-- Selecciona tu plantel --</option>
                    <option value="0">Sin plantel</option>
					<optgroup label="No registrado">
						<option value="unregistred">Otro</option>
					</optgroup>
					<optgroup id="plantRegistrados" label="Registrados">
					</optgroup>
				</select>
			</div>
		</div>
		<div class="control-group hide" id="otroPlantel">
			<label for="nombrePlantel" class="control-label">Nombre:</label>
			<div class="controls">
				<input type="text" id="nombrePlantel" name="nombrePlantel"
					placeholder="Nombre del plantel" class="input-xlarge" maxlength="100" />
			</div>
		</div>
		<div class="control-group">
            <label class="control-label" for="domicilio">Domicilio:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" id="domicilio" name="domicilio" maxlength="60" placeholder="Calle, N&uacute;mero" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="area">&Aacute;rea o subdirecci&oacute;n:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" id="area" name="area" maxlength="50" placeholder="Subdirecci&oacute;n administrativa" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="responsable">Responsable del programa:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" id="responsable" name="responsable" maxlength="50" placeholder="Persona que est&aacute; registrando la cuenta" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="cargo">Cargo:</label>
            <div class="controls">
                <input class="input-xlarge" type="text" id="cargo" name="cargo" maxlength="50" placeholder="Responsable de Administraci&oacute;n" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="tel">Tel&eacute;fono:</label>
            <div class="controls form-inline">
                <input class="span2" type="text" id="tel" name="tel" maxlength="25"
                       placeholder="Tel&eacute;fono"/>
                 Ext.
                <input type="text" id="telExt" name="telExt" maxlength="15"
                	placeholder="Extensi&oacute;n" style="width: 99px;"/>
            </div>
        </div>
        <div class="control-group">
            <label for="email" class="control-label">Email:</label>
            <div class="controls">
            	<div class="input-prepend">
            		<span class="add-on">
            			<i class="icon-envelope"></i>
            		</span><input type="text" id="email" name="email" style="width: 184px;"
            			placeholder="john@doe.com" />
            	</div>
            </div>        </div>
	</div> <!-- fin datos instituci&oacute;n -->
	<!-- Datos de usuario
	============================== -->
	<div class="span6">
		<legend>Informaci&oacute;n de usuario</legend>
		<div class="control-group">
			<label class="control-label" for="username">Nombre de usuario:</label>
			<div class="controls">
				<input type="text" id="username" name="username" maxlength="15"
					placeholder="Usuario [6-15 caracteres]" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">Contrase&ntilde;a:</label>
			<div class="controls">
				<input type="password" id="password" name="password" maxlength="16"
					placeholder="Password [6-16 caracteres]" />
			</div>
		</div>					
		<div class="control-group">
			<label class="control-label" for="passwordVerif">Verificar contrase&ntilde;a:</label>
			<div class="controls">
				<input type="password" id="passwordVerif" name="passwordVerif" maxlength="16"
					placeholder="Repite tu password"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="difundir">Autorizo que mis datos sean difundidos:</label>
			<div class="controls">
				<label class="radio inline">
					<input type="radio" id="difundir" name="difundir" value="1" checked="checked" /> S&iacute;
				</label>
				<label class="radio inline">
					<input type="radio" name="difundir" value="0" /> No
				</label>
			</div>
		</div>
	</div> <!-- fin datos de usuario -->
</div> <!-- fin row -->
<div class="form-actions">
	<button type="submit" class="btn btn-success input-large">Registrar</button>
	<button type="reset" class="btn input-large">Limpiar</button>
</div>