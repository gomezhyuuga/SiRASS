/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;

import skyforge.sirass.model.prestador.Inscripcion;



/**
 *
 * @author Eder Herget
 */
public class Carta_De_Presentacion implements Reporte_Base{
    
 public String url_img;
 public String carrera;
 public String matricula;
 public String avance;
 public String programa;
 public String puesto;
 public String clave;
 public String lugar;
 public String dependencia;
 public String clave1;
 public String responsable;
 public String nombre;
 public String promedio;
 public String fecha;
 public String horas;
 public String firma_digital;
 public String responsable_ss;
 public String datos_uacm;
 public String sitio_web;
 public String formato;

public Carta_De_Presentacion(Inscripcion inscripcion)  {
     
    url_img="UACM-icono-1.jpeg";	   
    carrera=inscripcion.getCarrera();	   
    matricula=inscripcion.getMatricula();	   
    avance=Double.toString(inscripcion.getAvanceCursos());	   
   // programa=inscripcion.getp;	   
    puesto="Diretora";	   
    clave="UACM/SS/11-12/148";	   
    lugar="Mexico, D.F.";	   
    dependencia="Suprema Corte De Justicia De la Nacion";	   
    clave1="UACM/CA/SS/CP-375/2012";	   
    responsable="IC. JACQUELINE ARGÜELLO CHIUNTI";	   
    nombre="Angelica Magali Téllez Beltran";	   
    promedio="9.18";	   
    fecha="22 demayo de 2012";	   
    horas="480";	   
    firma_digital="jk,knj";	   
    responsable_ss="LIC.MARTHA G. TERA PONCE";	   
    datos_uacm="Av. División del Norte No. 906, Col. Narvarte Poniente, Delegación Benito Juárez, C.P.03020 Conmutador 11-07-02-80 ext. 16671 ";	   
    sitio_web="www.uacm.edu.mx";	 
    formato="FORMATO: 005";
}


  /*  public static void main(String[] args) {
        try {
            Carta_De_Presentacion documento=new Carta_De_Presentacion();
            
            
           LinkedList<String> listaEmpleados = new LinkedList<String>(); 
           listaEmpleados.add(new String("ola")); 
             
            Map parametros = new HashMap();
            for(Field f:Carta_De_Presentacion.class.getDeclaredFields()){
            parametros.put(f.getName(), f.get(documento));
                System.out.println("Parametro enviado "+f.getName()+":"+f.get(documento));
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport("C:/Users/Eder Herget/Documents/NetBeansProjects/JRPRoyecto Actual/gomezhyuuga-SiRASS_test-8aaf3af/web/Archivos/Reportes/Carta_De_Presentacion.jasper"
                    , parametros, new JRBeanCollectionDataSource(listaEmpleados)); 
            JRExporter exporter = new JRPdfExporter(); 
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/Users/Eder Herget/Documents/NetBeansProjects/JRPRoyecto Actual/gomezhyuuga-SiRASS_test-8aaf3af/web/Archivos/Reportes/carta.pdf")); 
            exporter.exportReport();
            
            
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            Logger.getLogger(Carta_De_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
            Logger.getLogger(Carta_De_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            ex.printStackTrace();
            Logger.getLogger(Carta_De_Presentacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    @Override
    public void establecerObjResumen() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
