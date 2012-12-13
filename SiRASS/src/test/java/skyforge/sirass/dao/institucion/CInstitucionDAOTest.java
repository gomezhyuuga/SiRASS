/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.institucion;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.junit.*;
import skyforge.sirass.model.institucion.CInstitucion;
import skyforge.sirass.model.institucion.Plantel;

/**
 *
 * @author gomezhyuuga
 */
public class CInstitucionDAOTest {
    
    CInstitucionDAO dao;
    List<CInstitucion> instituciones;
    Iterator<CInstitucion> it;
    CInstitucion cInstitucion;
    
    public CInstitucionDAOTest() {
        dao = new CInstitucionDAO();
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
    public void testGetAll() {
        cInstitucion = dao.getById(2);
        Set<Plantel> p = (Set<Plantel>) cInstitucion.getPlanteles();
        Iterator it = p.iterator();
        while(it.hasNext()){
            Plantel pl = (Plantel) it.next();
            System.out.println("PLANTEL "+pl.getNombre());
        }
    }
}
