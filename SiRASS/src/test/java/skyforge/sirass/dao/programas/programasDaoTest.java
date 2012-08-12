/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.dao.programas;

import java.util.Iterator;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import skyforge.sirass.dao.programass.ProgramaSSDAO;
import skyforge.sirass.model.programass.CEstado;
import skyforge.sirass.model.programass.CategoriaPrograma;
import skyforge.sirass.model.programass.ProgramaSS;

/**
 *
 * @author JL Macias
 */
public class programasDaoTest {

    ProgramaSSDAO pdao;

    public programasDaoTest() {
        pdao = new ProgramaSSDAO();
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

    /*@Test
    public void testProgramas() {
        CategoriaPrograma categoriaPrograma = new CategoriaPrograma();
        CEstado cEstado = new CEstado();
        categoriaPrograma.setIdCategoria(2);
        cEstado.setIdEstado((short) 1);
        List<ProgramaSS> list = pdao.getListCatego(categoriaPrograma, cEstado);
        Iterator<ProgramaSS> it = list.iterator();
        while (it.hasNext()) {
            ProgramaSS programaSS = it.next();
            System.out.println(programaSS.getInstitucion());
        }
        /*
         * TODO
         * Comprobar que el test sea correcto. Revisa los otros tests.
         */
    //}

    /*@Test
    public void testProgByPK() {
        ProgramaSS programaSS = pdao.getByPK(2);
        System.out.println(programaSS.getNombre());
        System.out.println(programaSS.getIdPrograma());
        System.out.println(programaSS.getIdInstitucion());
        System.out.println(programaSS.getResultados());
        System.out.println(programaSS.getCvePrograma());
        System.out.println(programaSS.getArea());
        System.out.println(programaSS.getAlcance());
        /*
         * TODO
         * Comprobar que el test sea correcto. Revisa los otros tests.
         */
//    }

    /*@Test
    public void testGetListFew() {
        System.out.println("testGetListFew...");
        List<ProgramaSS> list = pdao.getListAllFew();
        Assert.assertNotNull(list);
        Assert.assertNotNull(list.get(0).getNombre());
        Assert.assertNotNull(list.get(0).getIdPrograma());
        Assert.assertNotNull(list.get(0).getCvePrograma());
    }
    @Test
    public void testGetListFewExternos() {
        System.out.println("testGetListFewExternos...");
        List<ProgramaSS> list = pdao.getListFewExternos();
        Assert.assertNotNull(list);
        Assert.assertNotNull(list.get(0).getNombre());
        Assert.assertNotNull(list.get(0).getIdPrograma());
        Assert.assertNotNull(list.get(0).getCvePrograma());
    }
    @Test
    public void testGetListFewInternos() {
        System.out.println("testGetListFewInternos...");
        List<ProgramaSS> list = pdao.getListFewInternos();
        Assert.assertNotNull(list);
    }*/
    @Test
    public void testUpObservacion(){
        ProgramaSSDAO pdao = new ProgramaSSDAO();
        pdao.uObsP(79, "aaaaaaa" , "cool");
    }
}
