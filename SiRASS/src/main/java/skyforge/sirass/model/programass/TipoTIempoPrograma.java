package skyforge.sirass.model.programass;

/**
 *
 * @author gomezhyuuga
 */
public class TipoTIempoPrograma {

    private short idTiempo;
    private String descripcion;

    public TipoTIempoPrograma() {
    }

    public TipoTIempoPrograma(short idTiempo) {
        this.idTiempo = idTiempo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getIdTiempo() {
        return idTiempo;
    }

    public void setIdTiempo(short idTiempo) {
        this.idTiempo = idTiempo;
    }
}
