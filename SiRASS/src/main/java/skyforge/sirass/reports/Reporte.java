/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;


import skyforge.sirass.security.Signature;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import skyforge.sirass.model.prestador.RegistroHora;


/**
 *
 * @author Eder Herget
 */
public class Reporte {
    
    private Map parametros;
    private JasperPrint jasperPrint;
    private String rutaRaiz;
    private String rutaReporteRaiz;
    private String tipo ;
    private Object obj;
    public static final short  TOTAL_DE_HORAS=480;
    public static final String Control_Mensual_De_Horas= "ControlHoras";
    public static final String Carta_De_Presentacion="Carta_De_Presentacion";
    public static final String Carta_De_Aceptacion="Carta_De_Aceptacion";
    
    
    public Reporte(String rutaRaiz) {       
      parametros = new HashMap();
      JasperPrint jasperPrint=null;
      tipo =".jasper";
      this.rutaRaiz=rutaRaiz;
      rutaReporteRaiz=rutaRaiz.concat("/Reportes");
    }
    
    public void establecerDatos(String documento, Object obj){
       HashSet hash=null;
       Signature signature ;
       this.obj=obj;
       LinkedList lista=new LinkedList<String>();
       lista.add(new String("ok"));
       
            try {
                for(Field f:obj.getClass().getDeclaredFields()){
                    if(f.get(obj).getClass().getName().equals("java.util.HashSet")){
                        hash=(HashSet) f.get(obj);
                        lista=new LinkedList<RegistroHora>(hash);
                    }else if(f.getName().equals("url_img")){
                        parametros.put(f.getName(), rutaReporteRaiz.concat("/Imagenes/").concat((String)f.get(obj)));
                    }else if(f.getName().equals("firma_digital")){
                        if((Boolean)f.get(obj)){
                            signature=new Signature(rutaRaiz);
                            signature.sendObject(obj.getClass().getField("objResumen").get(obj));
                            parametros.put(f.getName(), signature.getStringSign());
                        }else{
                           parametros.put(f.getName(), " "); 
                        }
                    }else if(f.getName().equals("objResumen")){
                        
                    }else{
                        parametros.put(f.getName(), f.get(obj));
                    }
                }
                jasperPrint = JasperFillManager.fillReport(rutaReporteRaiz.concat("/").concat(documento).concat(tipo)
                    , parametros, new JRBeanCollectionDataSource(lista));
            } catch (NoSuchFieldException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
                System.err.println("__Error__");
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                System.err.println("__Error__");
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                System.err.println("__Error__");
                Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void generar(OutputStream flujoSalida){
        try {
            JRExporter exporter = new JRPdfExporter(); 
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, flujoSalida); 
            exporter.exportReport();
            System.out.println("Reporte ".concat(obj.getClass().getName()).concat(" Exportado Con Éxito ").concat(new Date().toLocaleString()));
            } catch (JRException ex) {
            System.err.println("__Error__");
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
