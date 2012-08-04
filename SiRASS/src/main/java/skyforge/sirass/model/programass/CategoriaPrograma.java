package skyforge.sirass.model.programass;

/**
 *
 * @author JL Macias
 */
public class CategoriaPrograma {

    static public final short INTERNO = 1;
    static public final short EXTERNO = 2;
    
    private int idCategoria;
    private String descripcion;

    public CategoriaPrograma() {
    }

    public CategoriaPrograma(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
