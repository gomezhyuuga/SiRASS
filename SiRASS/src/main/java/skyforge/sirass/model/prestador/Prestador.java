package skyforge.sirass.model.prestador;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gomezhyuuga
 */
public class Prestador implements Serializable {

    private Integer inscripcion;
    private int idPrestador;
    private String nControl;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String email;
    private Date nacimiento;
    private char sexo;
    private String dCalle;
    private String dNumInt;
    private String dNumExt;
    private String dCP;
    private String dDelegacion;
    private String dColonia;
    private String telCasa;
    private String telCel;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public Prestador() {
    }

    public Integer getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Integer inscripcion) {
        this.inscripcion = inscripcion;
    }

    @Override
    public String toString() {
        System.out.println("## Información de clase: " + this.getClass().getSimpleName());
        return "idPrestador: " + this.idPrestador + " | "
                + "nControl: " + this.nControl + " | "
                + "nombre: " + this.nombre + " | "
                + "aPaterno: " + this.aPaterno + " | "
                + "aMaterno: " + this.aMaterno + " | "
                + "emaiL: " + this.email + " | "
                + "nacimiento: " + this.nacimiento.toString() + " | "
                + "sexo: " + this.sexo + " | "
                + "dCalle: " + this.dCalle + " | "
                + "dNumInt: " + this.dNumInt + " | "
                + "dNumExt: " + this.dNumExt + " | "
                + "dCP: " + this.dCP + " | "
                + "dDelegacion: " + this.dDelegacion + " | "
                + "dColonia: " + this.dColonia + " | "
                + "telCasa: " + this.telCasa + " | "
                + "telCel: " + this.telCel + " | ";
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

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getdCP() {
        return dCP;
    }

    public void setdCP(String dCP) {
        this.dCP = dCP;
    }

    public String getdCalle() {
        return dCalle;
    }

    public void setdCalle(String dCalle) {
        this.dCalle = dCalle;
    }

    public String getdColonia() {
        return dColonia;
    }

    public void setdColonia(String dColonia) {
        this.dColonia = dColonia;
    }

    public String getdDelegacion() {
        return dDelegacion;
    }

    public void setdDelegacion(String dDelegacion) {
        this.dDelegacion = dDelegacion;
    }

    public String getdNumExt() {
        return dNumExt;
    }

    public void setdNumExt(String dNumExt) {
        this.dNumExt = dNumExt;
    }

    public String getdNumInt() {
        return dNumInt;
    }

    public void setdNumInt(String dNumInt) {
        this.dNumInt = dNumInt;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public String getnControl() {
        return nControl;
    }

    public void setnControl(String nControl) {
        this.nControl = nControl;
    }

    public String getTelCasa() {
        return telCasa;
    }

    public void setTelCasa(String telCasa) {
        this.telCasa = telCasa;
    }

    public String getTelCel() {
        return telCel;
    }

    public void setTelCel(String telCel) {
        this.telCel = telCel;
    }
}
