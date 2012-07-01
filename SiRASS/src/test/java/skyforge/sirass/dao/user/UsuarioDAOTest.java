package skyforge.sirass.dao.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class UsuarioDAOTest {

    Usuario usuario;
    UsuarioDAO dao;
    Date creacion;
    Date ultimaModif;
    List<Usuario> usuarios;
    Date curDate;
    static int random = (int) (Math.random() * 1000);
    String modificadoPor = "jUnit" + random;
    static int delete;

    public UsuarioDAOTest() {
        curDate = new Date();
        dao = new UsuarioDAO();
        usuarios = null;
        creacion = curDate;
        ultimaModif = curDate;
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetUsers() {
        System.out.println("testGetUsers...");
        usuarios = dao.getAll();
        usuarios = dao.getAllJoined();
        Iterator<Usuario> it = usuarios.iterator();
        assertNotNull(usuarios);
    }

    @Test
    public void testInsert() {
        System.out.println("testInsert...");
        usuario = new Usuario();
        usuario.setUsuario("jUnit" + random);
        usuario.setPassword("jUnit" + random);
        usuario.setModificadoPor(modificadoPor);
        usuario.setCreacion(creacion);
        usuario.setUltimaModif(ultimaModif);
        System.out.println("insertando: jUnit" + random);
        int expected = dao.insert(usuario);
        assertEquals(expected, 1);
        this.testDelete(usuario.getUsuario());
    }

    public void testDelete(String username) {
        System.out.println("testDelete...");
        System.out.println("borrando: jUnit" + random);
        int expected = dao.deleteByUsername(username);
        System.out.println("expected: " + expected);
        if (expected >= 1) {
            assert true;
        } else {
            assert false;
        }
    }
}
