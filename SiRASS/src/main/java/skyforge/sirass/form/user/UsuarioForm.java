package skyforge.sirass.form.user;

import java.util.Map;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class UsuarioForm extends Form {

    Usuario usuario;

    public UsuarioForm(Map<String, String[]> vars) {
        super(vars, true);
        usuario = null;
    }

    /**
     * Genera el objeto con los datos de un map clave-valor (request map)
     *
     * @return Objeto tipo usuario con los datos encontrados en el map. No
     * incluye los roles
     */
    public Usuario getObject() {
        usuario = new Usuario();
        if (this.getVars().get("username") != null) {
            usuario.setUsuario(this.getVars().get("username")[0]);
        }
        if (this.getVars().get("password") != null) {
            usuario.setPassword(this.getVars().get("password")[0]);
        }
        if (this.getVars().get("difundir") != null) {
            boolean difundir = false;
            if (this.getVars().get("difundir")[0].equalsIgnoreCase("1")) {
                difundir = true;
            } else {
                difundir = Boolean.parseBoolean(this.getVars().get("difundir")[0]);
            }
            usuario.setDifundir(difundir);
        }
        return usuario;
    }
}
