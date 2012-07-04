package skyforge.sirass.model.prestador;

/**
 *
 * @author gomezhyuuga
 */
public class EstadoInscripcion {

    private short idEstado;
    private String descripcion;

    public EstadoInscripcion() {
    }

    public EstadoInscripcion(short idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(short idEstado) {
        this.idEstado = idEstado;
    }
}
