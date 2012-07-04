package skyforge.sirass.model.prestador;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gomezhyuuga
 */
public class InformeFinal implements Serializable {

    private int idInformeFinal;
    private int idInscripcion;
    private short horasAcumuladas;
    private String introduccion;
    private String objetivoGeneral;
    private String objetivosEspecificos;
    private String metodologia;
    private String actividades;
    private String metasAlcanzadas;
    private String resultados;
    private EstadoReporte estado;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public InformeFinal() {
    }

    public short getHorasAcumuladas() {
        return horasAcumuladas;
    }

    public void setHorasAcumuladas(short horasAcumuladas) {
        this.horasAcumuladas = horasAcumuladas;
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

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public EstadoReporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoReporte estado) {
        this.estado = estado;
    }

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public int getIdInformeFinal() {
        return idInformeFinal;
    }

    public void setIdInformeFinal(int idInformeFinal) {
        this.idInformeFinal = idInformeFinal;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public String getMetasAlcanzadas() {
        return metasAlcanzadas;
    }

    public void setMetasAlcanzadas(String metasAlcanzadas) {
        this.metasAlcanzadas = metasAlcanzadas;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public String getObjetivosEspecificos() {
        return objetivosEspecificos;
    }

    public void setObjetivosEspecificos(String objetivosEspecificos) {
        this.objetivosEspecificos = objetivosEspecificos;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }
}
