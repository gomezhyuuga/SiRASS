/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.prestador;

import org.junit.*;
import static org.junit.Assert.*;
import skyforge.sirass.model.prestador.ControlHoras;

/**
 *
 * @author gomezhyuuga
 */
public class ControlHorasDAOTest {
    
    ControlHorasDAO dao;
    ControlHoras controlHoras;
    
    
    public ControlHorasDAOTest() {
        dao = new ControlHorasDAO();
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testGetLastReport() {
        System.out.println("testGetLastReport...");
        controlHoras = dao.getLastReport(15);
        Assert.assertNotNull(controlHoras);
        System.out.println("CONTROL");
        System.out.println("ID: " + controlHoras.getIdControlHoras());
        System.out.println("ID_INSCRIPCION: " + controlHoras.getIdInscripcion());
        System.out.println("N_REPORTE: " + controlHoras.getnReporte());
        System.out.println("FECHA_INICIO: " + controlHoras.getFechaInicio());
        System.out.println("FECHA_FIN: " + controlHoras.getFechaFin());
        System.out.println("SUPERVISOR: " + controlHoras.getSupervisor());
        System.out.println("HRS_ACUM: " + controlHoras.getHorasAcumuladas());
        System.out.println("HRS_ANT: " + controlHoras.getHorasAnteriores());
        System.out.println("HRS_MES: " + controlHoras.getHorasMes());
        
    }
}
