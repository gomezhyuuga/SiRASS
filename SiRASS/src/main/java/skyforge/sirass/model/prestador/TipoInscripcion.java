package skyforge.sirass.model.prestador;

/**
 *
 * @author gomezhyuuga
 */
public class TipoInscripcion {

    private short idTipo;
    private String descripcion;

    public TipoInscripcion() {
    }

    public TipoInscripcion(short idTipo) {
        this.idTipo = idTipo;
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
