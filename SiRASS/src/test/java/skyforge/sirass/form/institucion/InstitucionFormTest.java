/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.form.institucion;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import skyforge.sirass.model.institucion.Institucion;

/**
 *
 * @author gomezhyuuga
 */
public class InstitucionFormTest {
    
    InstitucionForm form;
    Institucion institucion;
    static int random = (int) (Math.random() * 1000);
    Map<String, String[]> vars;
    
    public InstitucionFormTest() {
        vars = new HashMap<String, String[]>();
        vars.put("domicilio", new String[] {"jUnit" + random});
        vars.put("area", new String[] {"jUnit" + random});
        vars.put("responsable", new String[] {"jUnit" + random});
        vars.put("cargo", new String[] {"jUnit" + random});
        vars.put("tel", new String[] {"jUnit" + random});
        vars.put("telExt", new String[] {"jUnit" + random});
        vars.put("email", new String[] {"jUnit" + random});
        form = new InstitucionForm(vars, "jUnit" + random);
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
        System.out.println("testGetObject...");
        institucion = form.getObject();
        System.out.println("Creando institución básico...");
        System.out.println(institucion.toString());
        Assert.assertNotNull(institucion);
    }
    
    @Test
    public void testGetObjectWithInstitucionPlantel() {
        System.out.println("testGetObjectWithInstitucionPlantel...");
        vars.put("institucionList", new String[] {"1"});
        vars.put("plantelList", new String[] {"1"});
        institucion = form.getObject();
        System.out.println("Creando institución con plantel...");
        System.out.println(institucion.toString());
        Assert.assertNotNull(institucion);
        Assert.assertNotNull(institucion.getIdCInstitucion());
        Assert.assertNotNull(institucion.getIdPlantel());
    }
    
    @Test
    public void testGetObjectWithInstitucionSinPlantel() {
        System.out.println("testGetObjectWithInstitucionSinPlantel...");
        vars.put("institucionList", new String[] {"1"});
        vars.put("plantelList", new String[] {"unregistred"});
        vars.put("nombrePlantel", new String[] {"jUnit" + random});
        institucion = form.getObject();
        System.out.println("Creando institución sin plantel...");
        System.out.println(institucion.toString());
        Assert.assertNotNull(institucion);
        Assert.assertNotNull(institucion.getIdCInstitucion());
        Assert.assertNotNull(institucion.getIdPlantel());
    }
    
    @Test
    public void testGetObjectNoInstitucionNoPlantel() {
        System.out.println("testGetObjectNoInstitucionNoPlantel...");
        vars.put("institucionList", new String[] {"unregistred"});
        vars.put("nombreInstitucion", new String[] {"jUnit" + random});
        vars.put("plantelList", new String[] {"unregistred"});
        vars.put("nombrePlantel", new String[] {"jUnit" + random});
        institucion = form.getObject();
        System.out.println("Creando institución y plantel...");
        System.out.println(institucion.toString());
        Assert.assertNotNull(institucion);
        Assert.assertNotNull(institucion.getIdCInstitucion());
        Assert.assertNotNull(institucion.getIdPlantel());
    }
    @Test
    public void testGetObjectNoInstitucionPlantelNull() {
        System.out.println("testGetObjectNoInstitucionPlantelNull...");
        vars.put("institucionList", new String[] {"unregistred"});
        vars.put("nombreInstitucion", new String[] {"jUnit" + random});
        vars.put("plantelList", new String[] {"0"});
        institucion = form.getObject();
        System.out.println("Creando institución con plantel null...");
        System.out.println(institucion.toString());
        Assert.assertNotNull(institucion);
        Assert.assertNotNull(institucion.getIdCInstitucion());
        Assert.assertNull(institucion.getIdPlantel());
    }
}
