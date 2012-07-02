package skyforge.sirass.model.programass;

/**
 *
 * @author gomezhyuuga
 */
public class PoblacionPrograma {

    private short idPoblacion;
    private String descripcion;

    public PoblacionPrograma() {
    }

    public PoblacionPrograma(short idPoblacion) {
        this.idPoblacion = idPoblacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getIdPoblacion() {
        return idPoblacion;
    }

    public void setIdPoblacion(short idPoblacion) {
        this.idPoblacion = idPoblacion;
    }
}
