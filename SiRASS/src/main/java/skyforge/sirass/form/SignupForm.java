package skyforge.sirass.form;

import java.util.Map;
import skyforge.sirass.form.prestador.PrestadorForm;
import skyforge.sirass.model.prestador.Prestador;

/**
 *
 * @author gomezhyuuga
 */
public class SignupForm extends Form {

    public SignupForm(Map<String, String[]> vars) {
        super(vars);
    }

    public Prestador getPrestador() {
        return this.createPrestador(this.getVars());
    }

    public Prestador createPrestador(Map<String, String[]> vars) {
        PrestadorForm pForm = new PrestadorForm(vars);
        return pForm.getObject();
    }
}
