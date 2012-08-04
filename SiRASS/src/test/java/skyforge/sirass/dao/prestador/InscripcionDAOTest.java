package skyforge.sirass.dao.prestador;

import java.util.List;
import org.junit.*;
import skyforge.sirass.model.prestador.EstadoInscripcion;
import skyforge.sirass.model.prestador.Inscripcion;

/**
 *
 * @author gomezhyuuga
 */
public class InscripcionDAOTest {
    
    InscripcionDAO dao;
    Inscripcion inscripcion;
    List<Inscripcion> inscripciones;
    
    public InscripcionDAOTest() {
        dao = new InscripcionDAO();
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
    public void testGenerarNumControl() {
        System.out.println("## testGenerarNumControl...");
        String numControl;
        numControl = dao.generarNumControl(1, null);
        Assert.assertNotNull(numControl);
        System.out.println("NUMCONTROL: " + numControl);
//        numControl = dao.generarNumControl(1, 0);
//        Assert.assertNotNull(numControl);
//        System.out.println("NUMCONTROL: " + numControl);
//        numControl = dao.generarNumControl(1, 100);
//        Assert.assertNotNull(numControl);
//        System.out.println("NUMCONTROL: " + numControl);
//        numControl = dao.generarNumControl(1, 1000);
//        Assert.assertNotNull(numControl);
//        System.out.println("NUMCONTROL: " + numControl);
    }
    
//    @Test
    public void testGetFewWithStatus() {
        System.out.println("## testGetFewWithStatus...");
        inscripciones = dao.getFewWithStatus(EstadoInscripcion.EN_ESPERA);
        Assert.assertNotNull(inscripciones);
        boolean ok = (inscripciones.size() > 0 ) ? true : false;
        assert ok;
        inscripcion = inscripciones.get(0);
        System.out.println("prestador: " + inscripcion.getPrestador().getNombre());
        System.out.println("programa: " + inscripcion.getPrograma());
        System.out.println("estado: " + inscripcion.getEstado().getDescripcion());
    }
    
//    @Test
    public void testGetByPK(){
        System.out.println("## testGetByPK...");
        inscripcion = dao.getByPK(1);
        System.out.println("#####");
        System.out.println("id: " + inscripcion.getIdInscripcion());
        System.out.println("nombrePrestador: " + inscripcion.getPrestador().getNombre());
        inscripcion.getDias();
        inscripcion.getEstado();
        inscripcion.getTipo();
        Assert.assertNotNull(inscripcion);
    }
}
