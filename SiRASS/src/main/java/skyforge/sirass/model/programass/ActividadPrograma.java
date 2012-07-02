package skyforge.sirass.model.programass;

/**
 *
 * @author gomezhyuuga
 */
public class ActividadPrograma {

    private int idActividad;
    private int idPrograma;
    private String actividad;
    private String licenciatura;
    private short nSolicitados;
    private ProgramaSS programa;

    public ActividadPrograma() {
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getLicenciatura() {
        return licenciatura;
    }

    public void setLicenciatura(String licenciatura) {
        this.licenciatura = licenciatura;
    }

    public short getnSolicitados() {
        return nSolicitados;
    }

    public void setnSolicitados(short nSolicitados) {
        this.nSolicitados = nSolicitados;
    }

    public ProgramaSS getPrograma() {
        return programa;
    }

    public void setPrograma(ProgramaSS programa) {
        this.programa = programa;
    }
}
