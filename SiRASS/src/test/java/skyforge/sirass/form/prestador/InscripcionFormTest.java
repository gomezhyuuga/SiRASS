package skyforge.sirass.form.prestador;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.*;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.user.Dia;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionFormTest {

    InscripcionForm form;
    Inscripcion inscripcion;
    static int random = (int) (Math.random() * 1000);
    Map<String, String[]> vars;

    public InscripcionFormTest() {
        vars = new HashMap<String, String[]>();
        vars.put("semestre", new String[]{"6"});
        vars.put("especialidad", new String[]{"Técnico en programación"});
        vars.put("matricula", new String[]{"2010090251"});
        vars.put("promedio", new String[]{"8.25"});
        vars.put("avanceCurso", new String[]{"76"});
        vars.put("fechaIngreso", new String[]{"2009"});
        vars.put("creditos", new String[]{"200"});
        vars.put("cursosBasico", new String[]{"40"});
        vars.put("cursosSuperior", new String[]{"100"});
        vars.put("area", new String[]{"Oficina de Servicio Social"});
        vars.put("programaInst", new String[]{"Apoyo académico-administrativo en la UACM"});
        vars.put("cveProgramaInst", new String[]{"2ed020234edf"});
        vars.put("diasAsistencia", new String[]{"3", "1", "5"});
        vars.put("fInicio", new String[]{"01/03/2012"});
        vars.put("fTermino", new String[]{"03/09/2012"});
        vars.put("horaSalida", new String[]{"9:00"});
        vars.put("horaEntrada", new String[]{"13:30"});
        vars.put("idPrograma", new String[]{"1"});
        vars.put("nombrePrograma", new String[]{"Apoyo académico en la UACM"});
        vars.put("cvePrograma", new String[]{"SS/03/94"});
        form = new InscripcionForm(vars, "jUnit" + random);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetObject() {
        vars.put("institucionList", new String[]{"1"});
        vars.put("plantelList", new String[]{"3"});
        System.out.println("testGetObject...");
        System.out.println("Creando inscripción...");
        inscripcion = form.getObject();
        System.out.println("semestre: " + inscripcion.getSemestre());
        System.out.println("fechaIngreso: " + inscripcion.getAnioIngreso());
        System.out.println("area: " + inscripcion.getArea());
        System.out.println("especialidad: " + inscripcion.getCarrera());
        System.out.println("cveProgramaInst: " + inscripcion.getCveProgramaInst());
        System.out.println("matricula: " + inscripcion.getMatricula());
        System.out.println("programaInst: " + inscripcion.getProgramaInst());
        System.out.println("avanceCurso: " + inscripcion.getAvanceCursos());
        System.out.println("creditos: " + inscripcion.getCreditos());
        System.out.println("fechaFin: " + inscripcion.getFechaFin());
        System.out.println("fechaInicio: " + inscripcion.getFechaInicio());
        System.out.println("promedio: " + inscripcion.getPromedio());
        System.out.println("cursosBasico: " + inscripcion.getnCursosBasicos());
        System.out.println("cursosSuperior: " + inscripcion.getnCursosSuperior());
        System.out.print("dias: ");
        Set<Dia> dias = inscripcion.getDias();
        for (Dia dia : dias) {
            System.out.print(dia.getIdDia() + " ");
        }
        System.out.println("");
        System.out.println("horaEntrada: " + inscripcion.gethEntrada());
        System.out.println("horaSalida: " + inscripcion.gethSalida());
        System.out.println("idPrograma: " + inscripcion.getIdPrograma());
        System.out.println("nombrePrograma: " + inscripcion.getPrograma());
        System.out.println("cvePrograma: " + inscripcion.getCvePrograma());
        System.out.println("responsable: " + inscripcion.getResponsable());
        System.out.println("cargo: " + inscripcion.getCargoResponsable());
        System.out.println("institucion: " + inscripcion.getInstitucion().getIdCInstitucion());
        System.out.println("plantel: " + inscripcion.getPlantel().getIdPlantel());
        assert false;
    }
}
