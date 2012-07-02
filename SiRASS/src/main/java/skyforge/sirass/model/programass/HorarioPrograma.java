package skyforge.sirass.model.programass;

/**
 *
 * @author JL Macias
 */
public class HorarioPrograma {

    private short idHorario;
    private String descripcion;

    public HorarioPrograma() {
    }

    public HorarioPrograma(short idHorario) {
        this.idHorario = idHorario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public short getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(short idHorario) {
        this.idHorario = idHorario;
    }
}
