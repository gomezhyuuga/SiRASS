/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.procesos;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jorge Macias
 */
public class ProcessTest {
    
    public ProcessTest() {
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
    @Test
    public void PocessTest() {
        boolean stat;
        ProcessDao pdao = new ProcessDao();
        stat = pdao.exists("Activo", "Vigencia");
        
        if(stat == true){
            System.out.println("ACTIVO");
        }else{
            System.out.println("INACTIVO");
        }
        
    }
}
