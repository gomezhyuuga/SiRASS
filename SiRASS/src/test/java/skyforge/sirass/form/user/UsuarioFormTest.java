/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.form.user;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class UsuarioFormTest {

    UsuarioForm form;
    Usuario usuario;
    Map<String, String[]> vars;
    int random;

    public UsuarioFormTest() {
        random = (int) (Math.random() * 1000);
        usuario = null;
        vars = new HashMap<String, String[]>();
        vars.put("username", new String[]{"jUnit" + random});
        vars.put("password", new String[]{"jUnit" + random});
        vars.put("difundir", new String[]{"1"});
        form = new UsuarioForm(vars);
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
        usuario = form.getObject();
        System.out.println("Creando usuario...");
        System.out.println("usuario: " + usuario.getUsuario());
        System.out.println("passwowrd: " + usuario.getPassword());
        System.out.println("difundir: " + usuario.isDifundir());
        assert false;
//        Assert.assertNotNull(usuario);
    }
}
