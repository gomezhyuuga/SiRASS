package skyforge.sirass.model.prestador;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Set;
import skyforge.sirass.model.institucion.CInstitucion;
import skyforge.sirass.model.institucion.Plantel;
import skyforge.sirass.model.Dia;

/**
 *
 * @author gomezhyuuga
 */
public class Inscripcion implements Serializable {

    static public final short EN_ESPERA = 1;
    static public final short EN_SERVICIO = 2;
    static public final short SUSPENDIDA = 3;
    static public final short CANCELADA = 4;
    static public final short FINALIZADO = 5;
    static public final short CON_ERRORES = 6;
    private int idInscripcion;
    private Prestador prestador;
    private EstadoInscripcion estado;
    private TipoInscripcion tipo;
    private String area;
    // Propios de la inscripción
    private Integer horasRealizadas;
    private Short minutosRealizados;
    private CInstitucion institucion;
    private Plantel plantel;
    private String carrera;
    private String matricula;
    private int anioIngreso;
    private short semestre;
    private double avanceCursos;
    private double promedio;
    private Integer nCursosBasicos;
    private Integer nCursosSuperior;
    private Integer creditos;
    private String programaInst;
    private String cveProgramaInst;
    private int idPrograma;
    private String programa;
    private String cvePrograma;
    private Date fechaInicio;
    private Date fechaFin;
    private Time hEntrada;
    private Time hSalida;
    private boolean difundir;
    private String responsable;
    private String cargoResponsable;
    private String observaciones;
    private Set<Dia> dias;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public Inscripcion() {
    }

    public Integer getHorasRealizadas() {
        return horasRealizadas;
    }

    public void setHorasRealizadas(Integer horasRealizadas) {
        this.horasRealizadas = horasRealizadas;
    }

    public Set<Dia> getDias() {
        return dias;
    }

    public void setDias(Set<Dia> dias) {
        this.dias = dias;
    }

    public Time gethEntrada() {
        return hEntrada;
    }

    public void sethEntrada(Time hEntrada) {
        this.hEntrada = hEntrada;
    }

    public Time gethSalida() {
        return hSalida;
    }

    public void sethSalida(Time hSalida) {
        this.hSalida = hSalida;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public double getAvanceCursos() {
        return avanceCursos;
    }

    public void setAvanceCursos(double avanceCursos) {
        this.avanceCursos = avanceCursos;
    }

    public String getCargoResponsable() {
        return cargoResponsable;
    }

    public void setCargoResponsable(String cargoResponsable) {
        this.cargoResponsable = cargoResponsable;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public String getCvePrograma() {
        return cvePrograma;
    }

    public void setCvePrograma(String cvePrograma) {
        this.cvePrograma = cvePrograma;
    }

    public String getCveProgramaInst() {
        return cveProgramaInst;
    }

    public void setCveProgramaInst(String cveProgramaInst) {
        this.cveProgramaInst = cveProgramaInst;
    }

    public boolean isDifundir() {
        return difundir;
    }

    public void setDifundir(boolean difundir) {
        this.difundir = difundir;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public void setEstado(EstadoInscripcion estado) {
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

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public CInstitucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(CInstitucion institucion) {
        this.institucion = institucion;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(String modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Integer getnCursosBasicos() {
        return nCursosBasicos;
    }

    public void setnCursosBasicos(Integer nCursosBasicos) {
        this.nCursosBasicos = nCursosBasicos;
    }

    public Integer getnCursosSuperior() {
        return nCursosSuperior;
    }

    public void setnCursosSuperior(Integer nCursosSuperior) {
        this.nCursosSuperior = nCursosSuperior;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Plantel getPlantel() {
        return plantel;
    }

    public void setPlantel(Plantel plantel) {
        this.plantel = plantel;
    }

    public Prestador getPrestador() {
        return prestador;
    }

    public void setPrestador(Prestador prestador) {
        this.prestador = prestador;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getProgramaInst() {
        return programaInst;
    }

    public void setProgramaInst(String programaInst) {
        this.programaInst = programaInst;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public short getSemestre() {
        return semestre;
    }

    public void setSemestre(short semestre) {
        this.semestre = semestre;
    }

    public TipoInscripcion getTipo() {
        return tipo;
    }

    public void setTipo(TipoInscripcion tipo) {
        this.tipo = tipo;
    }

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }

    public Short getMinutosRealizados() {
        return minutosRealizados;
    }

    public void setMinutosRealizados(Short minutosRealizados) {
        this.minutosRealizados = minutosRealizados;
    }
}
