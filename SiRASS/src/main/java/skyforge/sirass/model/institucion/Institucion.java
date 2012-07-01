package skyforge.sirass.model.institucion;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gomezhyuuga
 */
public class Institucion implements Serializable {

    private int idInstitucion;
    private int idCInstitucion;
//    private CInstitucion cinstitucion;
//    private Plantel plantel;
    private Integer idPlantel;
    private String domicilio;
    private String area;
    private String responsable;
    private String cargo;
    private String tel;
    private String telExt;
    private String email;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public Institucion() {
    }

    public Institucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

//    public CInstitucion getCinstitucion() {
//        return cinstitucion;
//    }
//
//    public void setCinstitucion(CInstitucion cinstitucion) {
//        this.cinstitucion = cinstitucion;
//    }
//
//    public Plantel getPlantel() {
//        return plantel;
//    }
//
//    public void setPlantel(Plantel plantel) {
//        this.plantel = plantel;
//    }

    @Override
    public String toString() {
        System.out.println("## Información de clase: " + this.getClass().getSimpleName());
        return "idInstitucion: " + idInstitucion + " | "
                + "idCInstitucion: " + idCInstitucion + " | "
                + "idPlantel: " + idPlantel + " | "
                + "domicilio: " + domicilio + " | "
                + "area: " + area + " | "
                + "responsable: " + responsable + " | "
                + "cargo: " + cargo + " | "
                + "tel: " + tel + " | "
                + "telExt: " + telExt + " | "
                + "email: " + email;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public int getIdCInstitucion() {
        return idCInstitucion;
    }

    public void setIdCInstitucion(int idCInstitucion) {
        this.idCInstitucion = idCInstitucion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTelExt() {
        return telExt;
    }

    public void setTelExt(String telExt) {
        this.telExt = telExt;
    }

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Integer getIdPlantel() {
        return idPlantel;
    }

    public void setIdPlantel(Integer idPlantel) {
        this.idPlantel = idPlantel;
    }
}