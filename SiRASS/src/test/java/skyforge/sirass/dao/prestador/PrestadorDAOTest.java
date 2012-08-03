package skyforge.sirass.dao.prestador;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import skyforge.sirass.form.prestador.InscripcionForm;
import skyforge.sirass.model.prestador.EstadoInscripcion;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;

/**
 *
 * @author gomezhyuuga
 */
public class PrestadorDAOTest {
    
    PrestadorDAO dao;
    Prestador prestador;
    
    public PrestadorDAOTest() {
        dao = new PrestadorDAO();
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
    public void testGetUsername() {
        System.out.println("### testGetUsername ###");
        String username = dao.getUsername(8);
        Assert.assertNotNull(username);
        System.out.println("username (4): " + username);
    }
//    @Test
    public void inscribir() {
        System.out.println("## inscribir...");
        Map<String, String[]> vars = null;
        vars = new HashMap<String, String[]>();
        vars.put("tipoSS", new String[]{"1"});
        vars.put("difundir", new String[]{"1"});
        vars.put("institucionList", new String[]{"1"});
        vars.put("plantelList", new String[]{"1"});
        vars.put("semestre", new String[]{"6"});
        vars.put("especialidad", new String[]{"Técnico en programación"});
        vars.put("matricula", new String[]{"2010090251"});
        vars.put("promedio", new String[]{"8.25"});
        vars.put("avanceCurso", new String[]{"76"});
        vars.put("fechaIngreso", new String[]{"2009"});
        vars.put("creditos", new String[]{"100"});
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
        InscripcionForm form = new InscripcionForm(vars, "jUnit44");
        Inscripcion inscripcion = form.getObject(null);
        Date curDate = new Date();
        inscripcion.setCreacion(curDate);
        inscripcion.setUltimaModif(curDate);
        inscripcion.setModificadoPor("jUnit");
        inscripcion.setEstado(new EstadoInscripcion((short) 1));
        
        int result = dao.inscribir(inscripcion, 4);
        result = dao.inscribir(inscripcion, "gomezhyuuga");
        Assert.assertEquals(result, 1);
        Assert.assertEquals(result, 1);
    }
    
//    @Test
    public void getCurrentIDInscripcion() {
        System.out.println("## getCurrentIDInscripcion...");
        Integer i = dao.getCurrentIDInscripcion(2);
        System.out.println("idInscripcion para idPrestador 2: " + i);
        Integer i2 = dao.getCurrentIDInscripcion(4);
        System.out.println("idInscripcion para idPrestador 4: " + i2);
        Assert.assertNotNull(i);
        Assert.assertNull(i2);
    }
//    @Test
    public void getCurrentIDInscripcionPorUsername() {
        System.out.println("## getCurrentIDInscripcion...");
        Integer i = dao.getCurrentIDInscripcion("prestador2");
        System.out.println("idInscripcion para prestador2: " + i);
        Integer i2 = dao.getCurrentIDInscripcion("gomezhyuuga");
        System.out.println("idInscripcion para gomezhyuuga: " + i2);
        Assert.assertNotNull(i);
        Assert.assertNull(i2);
    }
    
//    @Test
    public void getCurrentInscripcionUsername() {
        System.out.println("## getCurrentInscripcionUsername...");
        Inscripcion ins = dao.getCurrentInscripcion("gomezhyuuga");
        Inscripcion ins2 = dao.getCurrentInscripcion("prestador2");
        Assert.assertNotNull(ins2);
        Assert.assertNull(ins);
    }
//    @Test
    public void getCurrentInscripcionIDPrestador() {
        System.out.println("## getCurrentInscripcionIDPrestador...");
        Inscripcion ins = dao.getCurrentInscripcion(4);
        Inscripcion ins2 = dao.getCurrentInscripcion(2);
        Assert.assertNotNull(ins2);
        Assert.assertNull(ins);
    }
//    @Test
    public void testSetInscripcion() {
        System.out.println("## testSetInscripcion...");
        int idInscripcion = 44;
        int idPrestador = 1;
        String username = "gomezhyuuga";
        int result = dao.setInscripcion(idInscripcion, idPrestador);
        Assert.assertEquals(1, result);
        result = dao.setInscripcion(22, "prestador1");
        Assert.assertEquals(1, result);
    }
}
