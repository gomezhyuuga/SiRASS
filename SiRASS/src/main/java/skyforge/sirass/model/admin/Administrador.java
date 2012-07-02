package skyforge.sirass.model.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author gomezhyuuga
 */
public class Administrador {

    private int idAdmin;
    private String nombre;
    private String aPaterno;
    private String aMaterno;
    private String email;
    private Date nacimiento;
    private char sexo;
    private String cargo;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public Administrador() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public String getNacimientoStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.getNacimiento());
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

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }
}