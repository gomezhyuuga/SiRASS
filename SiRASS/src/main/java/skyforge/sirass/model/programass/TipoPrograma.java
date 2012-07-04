package skyforge.sirass.model.programass;

/**
 *
 * @author gomezhyuuga
 */
public class TipoPrograma {

    private short idTipo;
    private String descripcion;

    public TipoPrograma() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(short idTipo) {
        this.idTipo = idTipo;
    }
}
