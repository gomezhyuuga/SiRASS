package skyforge.sirass.model.institucion;


import java.util.Date;

/**
 *
 * @author gomezhyuuga
 */
public class Plantel {

    private int idPlantel;
    private int idCInstitucion;
    private String nombre;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public Plantel() {
    }

    public Plantel(int idPlantel, int idCInstitucion) {
        this.idPlantel = idPlantel;
        this.idCInstitucion = idCInstitucion;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public int getIdCInstitucion() {
        return idCInstitucion;
    }

    public void setIdCInstitucion(int idCInstitucion) {
        this.idCInstitucion = idCInstitucion;
    }

    public int getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(int idPlantel) {
        this.idPlantel = idPlantel;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }
}