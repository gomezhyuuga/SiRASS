/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.institucion;

import java.util.Date;
import org.junit.*;
import skyforge.sirass.model.institucion.CInstitucion;
import skyforge.sirass.model.institucion.Institucion;

/**
 *
 * @author gomezhyuuga
 */
public class InstitucionDAOTest {

    InstitucionDAO dao;
    Institucion institucion;
    static int random = (int) (Math.random() * 1000);
    Date curDate = new Date();
    String modificadoPor;

    public InstitucionDAOTest() {
        dao = new InstitucionDAO();
        modificadoPor = "jUnit" + random;
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

    private Institucion createObject() {
        Institucion obj = new Institucion();
        obj.setArea("area" + random);
        obj.setCargo("cargo" + random);
        obj.setDomicilio("domicilio" + random);
        obj.setEmail("email" + random);
        obj.setResponsable("responsable" + random);
        obj.setTel("tel" + random);
        obj.setTelExt("telExt" + random);

        obj.setCreacion(curDate);
        obj.setUltimaModif(curDate);
        obj.setModificadoPor(modificadoPor);
        return obj;
    }

    @Test
    public void testInsertCInstitucionExisteConPlantel() {
        System.out.println("testInsertCInstitucionExisteConPlantel...");
        institucion = this.createObject();
        institucion.setIdCInstitucion(1);
        institucion.setIdPlantel(1);
        System.out.println("Insertando institucion...");
        int expected = 1;
        int result = dao.insert(institucion);
        Assert.assertEquals(expected, result);
        this.testDelete(institucion.getIdInstitucion());
    }

    @Test
    public void testInsertCInstitucionExisteSinPlantel() {
        System.out.println("testInsertCInstitucionExisteConPlantel...");
        institucion = this.createObject();
        institucion.setIdCInstitucion(1);
        System.out.println("Insertando institucion...");
        int expected = 1;
        int result = dao.insert(institucion);
        Assert.assertEquals(expected, result);
        this.testDelete(institucion.getIdInstitucion());
    }

    public void testDelete(int id) {
        System.out.println("testDelete...");
        institucion = (Institucion) dao.get(Institucion.class, id);
        System.out.println("Borrando... ");
        int expected = 1;
        int result = dao.delete(institucion);
        Assert.assertEquals(expected, result);
    }
}
