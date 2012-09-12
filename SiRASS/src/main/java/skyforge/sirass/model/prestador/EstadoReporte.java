package skyforge.sirass.model.prestador;

import java.io.Serializable;

/**
 *
 * @author gomezhyuuga
 */
public class EstadoReporte implements Serializable {
    
    public static final short SIN_REVISION = 1;
    public static final short CON_ERRORES = 2;
    public static final short CORRECTO = 3;
    
    private String descripcion;
    private short idEstado;

    public EstadoReporte() {
    }

    public EstadoReporte(short idEstado) {
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

    public void printInfo() {
        System.out.println("idEstado: " + this.idEstado + " ( " + descripcion + " )");
    }
}
