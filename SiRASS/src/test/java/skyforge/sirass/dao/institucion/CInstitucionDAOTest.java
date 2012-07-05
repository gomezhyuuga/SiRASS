/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.institucion;

import java.util.Iterator;
import java.util.List;
import org.junit.*;
import skyforge.sirass.model.institucion.CInstitucion;

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
        instituciones = dao.getAll(CInstitucion.class);
        Assert.assertNotNull(instituciones);
    }
}
