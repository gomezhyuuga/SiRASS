package skyforge.sirass.form;

import java.util.Map;
import skyforge.sirass.form.institucion.InstitucionForm;
import skyforge.sirass.form.prestador.PrestadorForm;
import skyforge.sirass.form.user.UsuarioForm;
import skyforge.sirass.model.institucion.Institucion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class SignupForm extends Form {

    public SignupForm(Map<String, String[]> vars) {
        super(vars);
    }

    public Usuario getUsuario() {
        return this.createUsuario(this.getVars());
    }

    public Prestador getPrestador() {
        return this.createPrestador(this.getVars());
    }

    public Institucion getInstitucion(String usuario) {
        return this.createInstitucion(this.getVars(), usuario);
    }

    private Usuario createUsuario(Map<String, String[]> vars) {
        UsuarioForm usuarioForm = new UsuarioForm(vars);
        return usuarioForm.getObject();
    }

    private Prestador createPrestador(Map<String, String[]> vars) {
        PrestadorForm pForm = new PrestadorForm(vars);
        return pForm.getObject();
    }

    private Institucion createInstitucion(Map<String, String[]> vars, String usuario) {
        InstitucionForm institucionForm = new InstitucionForm(vars, usuario);
        return institucionForm.getObject();
    }
}
