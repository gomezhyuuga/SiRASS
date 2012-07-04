package skyforge.sirass.model.programass;

    
/**
 *
 * @author JL Macias
 */
public class CEstado {
    
    private short idEstado;
    private String descripcion;

    public CEstado() {
    }

    public CEstado(short idEstado){
        this.idEstado = idEstado;
    }

    public short getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(short idEstado) {
        this.idEstado = idEstado;
    }
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
