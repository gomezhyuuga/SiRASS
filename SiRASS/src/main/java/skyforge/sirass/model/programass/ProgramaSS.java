package skyforge.sirass.model.programass;

import skyforge.sirass.model.Dia;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author gomezhyuuga
 */
public class ProgramaSS {

    static public final short ACTIVO = 1;
    static public final short INACTIVO = 2;
    static public final short SUSPENDIDO = 3;
    static public final short ESPERANDO = 4;
    private CEstado estado;
    private int idPrograma;
    private int idInstitucion;
    private String cvePrograma;
    private String institucion;
    private String area;
    private String domicilio;
    private String tel;
    private String telExt;
    private String email;
    private String nombre;
    private String objGeneral;
    private String justificacion;
    private String desarrollo;
    private String recursos;
    private String evaluacion;
    private String resultados;
    private String lugar;
    private int plazas;
    private int vacantes;
    private int ocupadas;
    private String observaciones;
    private String notas;
    private Date fechaTiempo;
    private CategoriaPrograma categoria;
    private HorarioPrograma horario;
    private TipoTIempoPrograma tiempo;
    private Set<TipoPrograma> tipo;
    private Set<AlcancePrograma> alcance;
    private Set<PoblacionPrograma> poblacion;
    private Set<Dia> dias;
    private Set<ResponsablePrograma> responsables;
    private Set<ActividadPrograma> actividad;
    // Del registro
    private String modificadoPor;
    private Date ultimaModif;
    private Date creacion;

    public ProgramaSS() {
    }

    public void printInfo() {
        System.out.println("idPrograma: " + this.idPrograma);
        System.out.println("categoria: " + this.categoria.getDescripcion());
        System.out.println("idInstitucion: " + this.idInstitucion);
        System.out.println("cvePrograma: " + this.cvePrograma);
        System.out.println("institucion: " + this.institucion);
        System.out.println("area: " + this.area);
        System.out.println("domicilio: " + this.domicilio);
        System.out.println("tel: " + this.tel);
        System.out.println("telExt: " + this.telExt);
        System.out.println("email: " + this.email);
        System.out.println("nombre: " + this.nombre);
        System.out.println("objGeneral: " + this.objGeneral);
        System.out.println("justificacion: " + this.justificacion);
        System.out.println("desarrollo: " + this.desarrollo);
        System.out.println("recursos: " + this.recursos);
        System.out.println("evaluacion: " + this.evaluacion);
        System.out.println("resultados: " + this.resultados);
        System.out.println("lugar: " + this.lugar);
        System.out.println("plazas: " + this.plazas);
        System.out.println("vacantes: " + this.vacantes);
        System.out.println("ocupadas: " + this.ocupadas);
        System.out.println("observaciones: " + this.observaciones);
        System.out.println("tipoTiempo: " + this.tiempo.getIdTiempo() + " (" + this.tiempo.getDescripcion() + ")");
        System.out.println("fechaTiempo: " + this.fechaTiempo);
        Iterator it = null;
        System.out.println("+ Tipo: " + this.getTipo().size());
        if (this.getTipo() != null && !this.getTipo().isEmpty()) {
            it = this.getTipo().iterator();
            while (it.hasNext()) {
                TipoPrograma tipo = (TipoPrograma) it.next();
                System.out.println("\t - " + tipo.getIdTipo() + "(" + tipo.getDescripcion() + ")");
            }
        }
        System.out.println("+ Alcance: " + this.getAlcance().size());
        if (this.getAlcance() != null && !this.getAlcance().isEmpty()) {
            it = this.getAlcance().iterator();
            while (it.hasNext()) {
                AlcancePrograma obj = (AlcancePrograma) it.next();
                System.out.println("\t - " + obj.getIdAlcance() + "(" + obj.getDescripcion() + ")");
            }
        }
        System.out.println("+ Poblacion: " + this.getPoblacion().size());
        if (this.getPoblacion() != null && !this.getPoblacion().isEmpty()) {
            it = this.getPoblacion().iterator();
            while (it.hasNext()) {
                PoblacionPrograma obj = (PoblacionPrograma) it.next();
                System.out.println("\t - " + obj.getIdPoblacion() + "(" + obj.getDescripcion() + ")");
            }
        }
        System.out.println("+ Dia: " + this.getDias().size());
        if (this.getDias() != null && !this.getDias().isEmpty()) {
            it = this.getDias().iterator();
            while (it.hasNext()) {
                Dia obj = (Dia) it.next();
                System.out.println("\t - " + obj.getIdDia() + "(" + obj.getDiaSemana() + ")");
            }
        }
        System.out.println("+ Responsables: " + this.getResponsables().size());
        if (this.getResponsables() != null && !this.getResponsables().isEmpty()) {
            it = this.getResponsables().iterator();
            while (it.hasNext()) {
                ResponsablePrograma obj = (ResponsablePrograma) it.next();
                System.out.println("\t - id: " + obj.getIdResponsable());
                System.out.println("\t - responsable: " + obj.getResponsable());
                System.out.println("\t - cargo: " + obj.getCargo());
                System.out.println("\t - email: " + obj.getEmail());
            }
        }
        System.out.println("+ Actividades: " + this.getActividad().size());
        if (this.getActividad() != null && !this.getActividad().isEmpty()) {
            it = this.getActividad().iterator();
            while (it.hasNext()) {
                ActividadPrograma obj = (ActividadPrograma) it.next();
                System.out.println("\t - id: " + obj.getIdActividad());
                System.out.println("\t - actividad: " + obj.getActividad());
                System.out.println("\t - licenciatura: " + obj.getLicenciatura());
                System.out.println("\t - numEstudiantesSolicitados: " + obj.getnSolicitados());
            }
        }
        System.out.println("horario: " + this.horario.getIdHorario() + " (" + this.horario.getDescripcion() + ")");
    }

    public Set<ActividadPrograma> getActividad() {
        return actividad;
    }

    public void setActividad(Set<ActividadPrograma> actividad) {
        this.actividad = actividad;
    }

    public Set<AlcancePrograma> getAlcance() {
        return alcance;
    }

    public void setAlcance(Set<AlcancePrograma> alcance) {
        this.alcance = alcance;
    }

    public Set<Dia> getDias() {
        return dias;
    }

    public void setDias(Set<Dia> dias) {
        this.dias = dias;
    }

    public Set<PoblacionPrograma> getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(Set<PoblacionPrograma> poblacion) {
        this.poblacion = poblacion;
    }

    public Set<ResponsablePrograma> getResponsables() {
        return responsables;
    }

    public void setResponsables(Set<ResponsablePrograma> responsables) {
        this.responsables = responsables;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public CategoriaPrograma getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPrograma categoria) {
        this.categoria = categoria;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public String getCvePrograma() {
        return cvePrograma;
    }

    public void setCvePrograma(String cvePrograma) {
        this.cvePrograma = cvePrograma;
    }

    public String getDesarrollo() {
        return desarrollo;
    }

    public void setDesarrollo(String desarrollo) {
        this.desarrollo = desarrollo;
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

    public String getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(String evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Date getFechaTiempo() {
        return fechaTiempo;
    }

    public void setFechaTiempo(Date fechaTiempo) {
        this.fechaTiempo = fechaTiempo;
    }

    public HorarioPrograma getHorario() {
        return horario;
    }

    public void setHorario(HorarioPrograma horario) {
        this.horario = horario;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
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

    public String getObjGeneral() {
        return objGeneral;
    }

    public void setObjGeneral(String objGeneral) {
        this.objGeneral = objGeneral;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }
    
    public int getOcupadas() {
        return ocupadas;
    }

    public void setOcupadas(int ocupadas) {
        this.ocupadas = ocupadas;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
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

    public TipoTIempoPrograma getTiempo() {
        return tiempo;
    }

    public void setTiempo(TipoTIempoPrograma tiempo) {
        this.tiempo = tiempo;
    }

    public Set<TipoPrograma> getTipo() {
        return tipo;
    }

    public void setTipo(Set<TipoPrograma> tipo) {
        this.tipo = tipo;
    }

    public Date getUltimaModif() {
        return ultimaModif;
    }

    public void setUltimaModif(Date ultimaModif) {
        this.ultimaModif = ultimaModif;
    }

    public int getVacantes() {
        return vacantes;
    }

    public void setVacantes(int vacantes) {
        this.vacantes = vacantes;
    }

    public CEstado getEstado() {
        return estado;
    }

    public void setEstado(CEstado estado) {
        this.estado = estado;
    }
    
}
