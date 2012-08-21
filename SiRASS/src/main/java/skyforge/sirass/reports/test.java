/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;



import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;


/**
 *
 * @author Eder Herget
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
//               Signature sign=new Signature("C:/Users/Eder Herget/Downloads/Proyecto/gomezhyuuga-SiRASS_test-0.9-34-g8aaf3af (1)/gomezhyuuga-SiRASS_test-8aaf3af/web/Archivos");
//       sign.generateKeys();
//       
//       System.out.println(sign.isAuthentic("48.44.2.20.84.-125.-23.30.91.-100.-74.-116.-35.-104.28.-87.-125.70.33.9.-79.-115.-46.84.2.20.96.-38.3.-122.49.65.47.-36.-110.-19.-79.-16.-16.75.-63.90.-122.-48.91.-7"));
////       try {
////            // Control_Mensual_De_Horas control=new Control_Mensual_De_Horas();
//
//             
//     KeyPairGenerator keyPairGenerator;
//     keyPairGenerator=KeyPairGenerator.getInstance("RSA");
//     keyPairGenerator.initialize(5120);
//     KeyPair keyPair=keyPairGenerator.genKeyPair();
//            fos = new FileOutputStream("C:/Users/Eder Herget/Downloads/Proyecto/gomezhyuuga-SiRASS_test-0.9-34-g8aaf3af (1)/gomezhyuuga-SiRASS_test-8aaf3af/web/Archivos/a.txt");
//            
//     KeyFactory fact=KeyFactory.getInstance("RSA");
//     RSAPublicKeySpec pub=fact.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
//     RSAPrivateKeySpec priv=fact.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);
//     System.out.println("Publica :");
//     System.out.println("Modulo:"+pub.getModulus());
//     System.out.println("Exponente:"+ pub.getPublicExponent());
//     
//     System.out.println("Privada :");
//     System.out.println("Modulo:"+priv.getModulus());
//     System.out.println("Exponente:"+ priv.getPrivateExponent());
//            
//     
//     Cipher cipher= Cipher.getInstance("RSA");
//     cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
//     byte[] cifrado=cipher.doFinal("hola".getBytes());
//     System.out.println(new String(cifrado));
//     
//     
//     
//     
//        } catch (InvalidKeySpecException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        }  catch (IllegalBlockSizeException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (BadPaddingException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeyException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchPaddingException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        
////    
//    
//
        try{
            Map parametros = new HashMap();
          
parametros.put("escuela_de_procedencia","ola");	   
parametros.put("carrera","ola");	   
LinkedList lista=new LinkedList<String>();
       lista.add(new String("ok"));
  JasperPrint jasperPrint = JasperFillManager.fillReport("C:/P/reportapoyo.jasper", parametros, new JRBeanCollectionDataSource(lista)); 
JRExporter exporter = new JRPdfExporter(); 
    exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
    exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("C:/P/reporteEnPdf.pdf")); 
    exporter.exportReport(); 
    System.out.print("ok");
    
        } catch (JRException ex) {
            System.err.println("Error "+ex.getMessage()+" "+ex.getLocalizedMessage());
            
            ex.printStackTrace(); 
        }
              
    }}
