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
    
    public programasDaoTest() {
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
    public void testProgramas() {
        ProgramaSSDAO pdao = new ProgramaSSDAO();
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
    }
    
    @Test
    public void testProgByPK(){
        ProgramaSSDAO pdao = new ProgramaSSDAO();
        ProgramaSS programaSS = pdao.getByPK(2);
            System.out.println(programaSS.getNombre());
            System.out.println(programaSS.getIdPrograma());
            System.out.println(programaSS.getIdInstitucion());
            System.out.println(programaSS.getResultados());
            System.out.println(programaSS.getCvePrograma());
            System.out.println(programaSS.getArea());
            System.out.println(programaSS.getAlcance());
    }
}
