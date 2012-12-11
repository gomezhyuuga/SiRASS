/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.admin;

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
        UsuarioDAO udao = new UsuarioDAO();
        AdministradorDAO adao = new AdministradorDAO();
        Usuario user = udao.getByUsername("jorgeadmin");
        Administrador admin = user.getAdministrador();
        int idAdmin = admin.getIdAdmin();
        admin = adao.getAdministradorByPK(idAdmin);

    }
}
