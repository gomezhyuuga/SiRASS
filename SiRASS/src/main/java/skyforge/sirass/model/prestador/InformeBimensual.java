package skyforge.sirass.model.prestador;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gomezhyuuga
 */
public class InformeBimensual implements Serializable {

    private int idInformeBimensual;
    private int idInscripcion;
    private EstadoReporte estado;
    private short numReporte;
    private Date inicioPeriodo;
    private Date terminoPeriodo;
    private short horasBimestre;
    private short horasAcumuladas;
    private String actividades;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public InformeBimensual() {
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }

    public short getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(short horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }

    public short getHorasBimestre() {
        return horasBimestre;
    }

    public void setHorasBimestre(short horasBimestre) {
        this.horasBimestre = horasBimestre;
    }

    public int getIdInformeBimensual() {
        return idInformeBimensual;
    }

    public void setIdInformeBimensual(int idInformeBimensual) {
        this.idInformeBimensual = idInformeBimensual;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Date getInicioPeriodo() {
        return inicioPeriodo;
    }

    public void setInicioPeriodo(Date inicioPeriodo) {
        this.inicioPeriodo = inicioPeriodo;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public short getNumReporte() {
        return numReporte;
    }

    public void setNumReporte(short numReporte) {
        this.numReporte = numReporte;
    }

    public Date getTerminoPeriodo() {
        return terminoPeriodo;
    }

    public void setTerminoPeriodo(Date terminoPeriodo) {
        this.terminoPeriodo = terminoPeriodo;
    }

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }

    public void printInfo() {
        System.out.println("id: " + idInformeBimensual);
        System.out.println("idInscripcion: " + idInscripcion);
        System.out.println("numReporte: " + numReporte);
        System.out.println("inicioPeriodo: " + inicioPeriodo.toString());
        System.out.println("terminoPeriodo: " + terminoPeriodo.toString());
        System.out.println("horasBimestre: " + horasBimestre);
        System.out.println("horasAcumuladas: " + horasAcumuladas);
        System.out.println("actividades: " + actividades);
        this.estado.printInfo();
        this.printDatosRegistro();
    }

    private void printDatosRegistro() {
        System.out.println("creacion: " + creacion.toString());
        System.out.println("modificadoPor: " + modificadoPor);
        System.out.println("ultimaModif: " + ultimaModif.toString());
    }
}