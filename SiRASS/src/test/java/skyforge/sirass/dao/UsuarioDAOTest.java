package skyforge.sirass.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
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
    }

    @Test
    public void testDelete() {
        System.out.println("testDelete...");
        System.out.println("borrando: jUnit" + random);
        int expected = dao.delete("jUnit" + random);
        if (expected >= 1) {
            assert true;
        } else {
            assert false;
        }
    }
}
