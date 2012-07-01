package skyforge.sirass.model.institucion;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gomezhyuuga
 */
public class CInstitucion implements Serializable {

    private int idCInstitucion;
    private String nombre;
    private Set<Plantel> planteles = new HashSet<Plantel>(0);
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public CInstitucion() {
    }

    public CInstitucion(int idCInstitucion) {
        this.idCInstitucion = idCInstitucion;
    }

    public CInstitucion(String nombre) {
        this.nombre = nombre;
    }

    public Set<Plantel> getPlanteles() {
        return planteles;
    }

    public int getIdCInstitucion() {
        return idCInstitucion;
    }

    public void setIdCInstitucion(int idCInstitucion) {
        this.idCInstitucion = idCInstitucion;
    }

    public void setPlanteles(Set<Plantel> planteles) {
        this.planteles = planteles;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
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