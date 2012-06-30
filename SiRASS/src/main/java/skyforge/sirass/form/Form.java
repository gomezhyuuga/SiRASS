package skyforge.sirass.form;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 *
 * @author gomezhyuuga
 */
public abstract class Form {

    Map<String, String[]> vars;
    SimpleDateFormat dateFormat;

    /**
     * Constructor básico de un form
     *
     * @param vars Map de clave-valor
     */
    public Form(Map<String, String[]> vars) {
        this.vars = vars;
    }

    /**
     * Constructor de un form básico con soporte de fechas formateadas
     * "dd/MM/yyyy"
     *
     * @param vars Map de clave-valor
     * @param dateFormat True para el soporte del dateFormat
     */
    public Form(Map<String, String[]> vars, boolean dateFormat) {
        this.vars = vars;
        if (dateFormat) {
            this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        }
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Map<String, String[]> getVars() {
        return vars;
    }

    public void setVars(Map<String, String[]> vars) {
        this.vars = vars;
    }
}
