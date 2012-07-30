package skyforge.sirass.model.prestador;

/**
 *
 * @author gomezhyuuga
 */
public class EstadoInscripcion {
    
    static public final short EN_ESPERA = 1;
    static public final short EN_SERVICIO = 2;
    static public final short SUSPENDIDA = 3;
    static public final short CANCELADA = 4;
    static public final short FINALIZADO = 5;
    static public final short CON_ERRORES = 6;
    static public final short CORRECTA = 7;

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
