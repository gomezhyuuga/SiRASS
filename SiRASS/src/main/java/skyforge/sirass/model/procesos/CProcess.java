package skyforge.sirass.model.procesos;

/**
 *
 * @author Jorge Macias
 */
public class CProcess {
    
    static public final String Vigente = "Activo";
    static public final String NVigente = "Inactivo";
    private String NOMBRE;
    private String VALOR;

    public CProcess() {
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }
    
}
