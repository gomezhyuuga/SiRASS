package skyforge.sirass.dao.user;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import skyforge.sirass.dao.admin.AdministradorDAO;
import skyforge.sirass.model.admin.Administrador;
import skyforge.sirass.model.institucion.Institucion;
import skyforge.sirass.model.prestador.Prestador;
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

    /*@Test
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

    @Test
    public void testInsertWithPrestador() {
        System.out.println("testInsertWithPrestador...");
        usuario = new Usuario();
        usuario.setUsuario("jUnit" + random);
        usuario.setPassword("jUnit" + random);
        
        Prestador prestador = new Prestador();
        prestador.setNombre("nombre" + random);
        prestador.setaPaterno("aPaterno" + random);
        prestador.setaMaterno("aMaterno" + random);
        prestador.setEmail("email" + random);
        prestador.setSexo('M');
        prestador.setNacimiento(curDate);
        prestador.setdCalle("calle" + random);
        prestador.setdColonia("colonia" + random);
        prestador.setdDelegacion("delegacion" + random);
        prestador.setdCP("CP" + random);
        prestador.setdNumExt("" + random);
        prestador.setdNumInt("" + random);
        prestador.setTelCasa("tel" + random);
        prestador.setTelCel("cel" + random);
        
        prestador.setCreacion(curDate);
        prestador.setModificadoPor(modificadoPor);
        prestador.setUltimaModif(curDate);
        usuario.setPrestador(prestador);
        
        usuario.setModificadoPor(modificadoPor);
        usuario.setCreacion(creacion);
        usuario.setUltimaModif(ultimaModif);
        
        System.out.println("insertando: jUnit" + random + " con prestador");
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
    }*/
    
    /*@Test
    public void TestMethod(){
        int length;
        AdministradorDAO adao = new AdministradorDAO();
        UsuarioDAO dao = new UsuarioDAO();
        List<Administrador> user = dao.getAdmin();
        length = user.size();
        System.out.println("TAMAÑO: "+length);
        for(Administrador u: user){
            Administrador admin = adao.getAdministradorByPK(u.getIdAdmin());
            System.out.println("NOMBRE: "+admin.getNombre());
            System.out.println("APELLIDO: "+admin.getaPaterno());
            System.out.println("CARGO: "+admin.getCargo());
        }
        
    }*/
}
