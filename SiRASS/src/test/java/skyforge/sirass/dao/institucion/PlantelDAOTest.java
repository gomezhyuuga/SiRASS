/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.institucion;

import java.util.Iterator;
import java.util.List;
import org.junit.*;
import skyforge.sirass.model.institucion.Plantel;

/**
 *
 * @author gomezhyuuga
 */
public class PlantelDAOTest {
    
    PlantelDAO dao;
    List<Plantel> planteles;
    Iterator<Plantel> it;
    
    public PlantelDAOTest() {
        dao = new PlantelDAO();
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
    public void testGetPlanteles() {
        planteles = dao.getPlanteles(1);
        Assert.assertNotNull(planteles);
    }
}
