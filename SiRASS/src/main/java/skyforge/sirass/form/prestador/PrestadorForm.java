package skyforge.sirass.form.prestador;

import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.prestador.Prestador;

/**
 *
 * @author gomezhyuuga
 */
public class PrestadorForm extends Form {

    Prestador prestador;

    public PrestadorForm(Map<String, String[]> vars) {
        super(vars, true);
        prestador = null;
    }

    /**
     * Genera el objeto con los datos de un map clave-valor (request map)
     *
     * @return Objeto tipo prestador con los datos encontrados en el map
     */
    public Prestador getObject() {
        this.prestador = new Prestador();
        if (this.getVars().get("nombre") != null) {
            prestador.setNombre(this.getVars().get("nombre")[0]);
        }
        if (this.getVars().get("aPaterno") != null) {
            prestador.setaPaterno(this.getVars().get("aPaterno")[0]);
        }
        if (this.getVars().get("aMaterno") != null) {
            prestador.setaMaterno(this.getVars().get("aMaterno")[0]);
        }
        if (this.getVars().get("email") != null) {
            prestador.setEmail(this.getVars().get("email")[0]);
        }
        if (this.getVars().get("nacimiento") != null) {
            try {
                prestador.setNacimiento(
                        this.getDateFormat().parse(this.getVars().get("nacimiento")[0]));
            } catch (ParseException ex) {
                Logger.getLogger(PrestadorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (this.getVars().get("sexo") != null) {
            prestador.setSexo(this.getVars().get("sexo")[0].charAt(0));
        }
        if (this.getVars().get("dCalle") != null) {
            prestador.setdCalle(this.getVars().get("dCalle")[0]);
        }
        if (this.getVars().get("dNumExt") != null) {
            prestador.setdNumExt(this.getVars().get("dNumExt")[0]);
        }
        if (this.getVars().get("dNumInt") != null) {
            prestador.setdNumInt(this.getVars().get("dNumInt")[0]);
        }
        if (this.getVars().get("dCP") != null) {
            prestador.setdCP(this.getVars().get("dCP")[0]);
        }
        if (this.getVars().get("dDelegacion") != null) {
            prestador.setdDelegacion(this.getVars().get("dDelegacion")[0]);
        }
        if (this.getVars().get("dColonia") != null) {
            prestador.setdColonia(this.getVars().get("dColonia")[0]);
        }
        if (this.getVars().get("telCasa") != null) {
            prestador.setTelCasa(this.getVars().get("telCasa")[0]);
        }
        if (this.getVars().get("telCel") != null) {
            prestador.setTelCel(this.getVars().get("telCel")[0]);
        }
        return this.prestador;
    }
}
