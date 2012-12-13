/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.admin;

import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.admin.Administrador;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author Jorge Macias
 */
public class AdminDAOTest {

    public AdminDAOTest() {
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
    public void AdminDAOTEST() {
        AdministradorDAO adao = new AdministradorDAO();
        List<Administrador> adm = adao.getListAllFew();
        for(Administrador admin: adm){
            System.out.println("NOMBRE: "+admin.getNombre());
            System.out.println("APELLIDO: "+admin.getaPaterno());
            System.out.println("CARGO: "+admin.getCargo());
        }

    }
}
