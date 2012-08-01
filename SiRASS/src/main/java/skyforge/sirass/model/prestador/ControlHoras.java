package skyforge.sirass.model.prestador;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author gomezhyuuga
 */
public class ControlHoras {

    private int idControlHoras;
    private int idInscripcion;
    private EstadoReporte estado;
    private short nReporte;
    private Date fechaInicio;
    private Date fechaFin;
    private String supervisor;
    private Integer horasMes;
    private Short minutosMes;
    private Integer horasAnteriores;
    private Short minutosAnteriores;
    private Integer horasAcumuladas;
    private Short minutosAcumulados;
    private Set<RegistroHora> horas = new HashSet<RegistroHora>(0);
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public ControlHoras() {
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

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Set<RegistroHora> getHoras() {
        return horas;
    }

    public void setHoras(Set<RegistroHora> horas) {
        this.horas = horas;
    }

    public Integer getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(Integer horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
    }

    public Integer getHorasAnteriores() {
        return horasAnteriores;
    }

    public void setHorasAnteriores(Integer horasAnteriores) {
        this.horasAnteriores = horasAnteriores;
    }

    public Integer getHorasMes() {
        return horasMes;
    }

    public void setHorasMes(Integer horasMes) {
        this.horasMes = horasMes;
    }

    public Short getMinutosAcumulados() {
        return minutosAcumulados;
    }

    public void setMinutosAcumulados(Short minutosAcumulados) {
        this.minutosAcumulados = minutosAcumulados;
    }

    public Short getMinutosAnteriores() {
        return minutosAnteriores;
    }

    public void setMinutosAnteriores(Short minutosAnteriores) {
        this.minutosAnteriores = minutosAnteriores;
    }

    public Short getMinutosMes() {
        return minutosMes;
    }

    public void setMinutosMes(Short minutosMes) {
        this.minutosMes = minutosMes;
    }

    public int getIdControlHoras() {
        return idControlHoras;
    }

    public void setIdControlHoras(int idControlHoras) {
        this.idControlHoras = idControlHoras;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public short getnReporte() {
        return nReporte;
    }

    public void setnReporte(short nReporte) {
        this.nReporte = nReporte;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }

    public void printInfo() {
        System.out.println("idControlHoras: " + idControlHoras);
        System.out.println("idInscripcion: " + idInscripcion);
        System.out.println("nReporte: " + nReporte);
        System.out.println("fechaInicio: " + this.fechaInicio.toString());
        System.out.println("fechaFin" + this.fechaFin.toString());
        System.out.println("supervisor: " + supervisor);
        System.out.println("horasMes: " + horasMes);
        System.out.println("horasAnteriores: " + horasAnteriores);
        System.out.println("horasAcumuladas: " + horasAcumuladas);
        this.estado.printInfo();
        printDatosRegistro();
    }

    public void printHoras() {
        Iterator<RegistroHora> it = this.horas.iterator();
        while (it.hasNext()) {
            RegistroHora r = it.next();
            r.printInfo();
        }
    }

    private void printDatosRegistro() {
        System.out.println("creacion: " + creacion.toString());
        System.out.println("modificadoPor: " + modificadoPor);
        System.out.println("ultimaModif: " + ultimaModif.toString());
    }
}
