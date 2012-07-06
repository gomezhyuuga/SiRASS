package skyforge.sirass.dao.prestador;

import org.junit.*;
import static org.junit.Assert.*;
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
    public void getCurrentInscripcionPorID() {
        System.out.println("## getCurrentInscripcionPorID...");
        Inscripcion inscripcion = dao.getCurrentInscripcion("gomezhyuuga");
        Assert.assertNull(inscripcion);
        inscripcion = dao.getCurrentInscripcion(4);
        Assert.assertNull(inscripcion);
        inscripcion = dao.getCurrentInscripcion("prestador2");
        Assert.assertNotNull(inscripcion);
        Assert.assertNotNull(inscripcion.getIdInscripcion());
        Assert.assertNotNull(inscripcion.getEstado());
        System.out.println("idInscripcion: " + inscripcion.getIdInscripcion());
        System.out.println("estado: " + inscripcion.getEstado().getDescripcion());
        System.out.println("horasRealizadas: " + inscripcion.getHorasRealizadas());
    }
}
