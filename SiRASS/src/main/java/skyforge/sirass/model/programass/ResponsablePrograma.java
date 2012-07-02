package skyforge.sirass.model.programass;

import java.io.Serializable;

/**
 *
 * @author gomezhyuuga
 */
public class ResponsablePrograma implements Serializable {

    private int idResponsable;
    private String responsable;
    private String cargo;
    private String email;
    private ProgramaSS programa;

    public ResponsablePrograma() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public ProgramaSS getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaSS programa) {
        this.programa = programa;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }
}
