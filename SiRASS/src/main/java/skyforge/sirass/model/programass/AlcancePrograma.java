package skyforge.sirass.model.programass;

/**
 *
 * @author gomezhyuuga
 */
public class AlcancePrograma {

    private short idAlcance;
    private String descripcion;

    public AlcancePrograma() {
    }

    public AlcancePrograma(short idAlcance) {
        this.idAlcance = idAlcance;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getIdAlcance() {
        return idAlcance;
    }

    public void setIdAlcance(short idAlcance) {
        this.idAlcance = idAlcance;
    }
}
