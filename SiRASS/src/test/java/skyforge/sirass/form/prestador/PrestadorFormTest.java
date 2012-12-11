/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.form.prestador;

import java.util.HashMap;
import java.util.Map;
import org.junit.*;
import skyforge.sirass.dao.prestador.PrestadorDAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.form.prestador.PrestadorForm;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class PrestadorFormTest {

    PrestadorForm form;
    Prestador prestador;
    Map<String, String[]> vars;

    public PrestadorFormTest() {
        vars = new HashMap<String, String[]>();
        vars.put("nombre", new String[]{"Fernando"});
        vars.put("aPaterno", new String[]{"Gómez"});
        vars.put("aMaterno", new String[]{"Herrera"});
        vars.put("email", new String[]{"gomezhyuuga@me.com"});
        vars.put("nacimiento", new String[]{"04/02/1994"});
        vars.put("sexo", new String[]{"M"});
        vars.put("dCalle", new String[]{"Victor Hernández Covarrubias"});
        vars.put("dNumInt", new String[]{"A3-101"});
        vars.put("dNumExt", new String[]{"S/N"});
        vars.put("dCP", new String[]{"02430"});
        vars.put("dDelegacion", new String[]{"Azcapotzalco"});
        vars.put("dColonia", new String[]{"Presidente Madero"});
        vars.put("telCasa", new String[]{"46229731"});
        vars.put("telCel", new String[]{"5510697423"});
        form = new PrestadorForm(vars);
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

    /*
     * @Test public void testGetObject() {
     * System.out.println("testGetPrestador..."); prestador = form.getObject();
     * System.out.println("Creando prestador..."); System.out.println("Nombre: "
     * + prestador.getNombre()); System.out.println("aPaterno: " +
     * prestador.getaPaterno()); System.out.println("aMaterno: " +
     * prestador.getaMaterno()); System.out.println("email: " +
     * prestador.getEmail()); System.out.println("nacimiento: " +
     * prestador.getNacimiento()); System.out.println("sexo: " +
     * prestador.getSexo()); System.out.println("dCalle: " +
     * prestador.getdCalle()); System.out.println("dNumExt: " +
     * prestador.getdNumExt()); System.out.println("dNumInt: " +
     * prestador.getdNumInt()); System.out.println("dCP: " +
     * prestador.getdCP()); System.out.println("dDelegacion: " +
     * prestador.getdDelegacion()); System.out.println("dColonia: " +
     * prestador.getdColonia()); System.out.println("telCasa: " +
     * prestador.getTelCasa()); System.out.println("telCel: " +
     * prestador.getTelCel()); Assert.assertNotNull(prestador);
    }
     */
    @Test
    public void TEST() {
        UsuarioDAO udao = new UsuarioDAO();
        PrestadorDAO pdao = new PrestadorDAO();
        Usuario user = udao.getByUsername("prestador1");
        Prestador prestador = user.getPrestador();
        int idPres = prestador.getIdPrestador();
        prestador = pdao.getPrestadorByPK(idPres);
        System.out.println("PRESTADOR: "+prestador.getNombre());
        System.out.println("IDPRESTADOR: "+prestador.getIdPrestador());
    }
}
