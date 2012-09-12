/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.reports;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author gomezhyuuga
 */
public class Report {
    
    Map params;
    JasperReport report;
    ServletContext servlet;
    Connection connection;
    ServletOutputStream out;
    String REPORTS_PATH = "/WEB-INF/reports/";
    String user = "skyforge";
    String password = "skyforge";
    String database = "jdbc:mysql://localhost:3306/SiRASS";

    public Report(ServletContext servlet, ServletOutputStream out, Map params) {
        this.params = params;
        this.servlet = servlet;
        this.out = out;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(database,user,password);
            System.out.println("CONEXION OK");
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (servlet != null) {
            REPORTS_PATH = servlet.getRealPath("") + REPORTS_PATH;
        }
    }
    
    public void generarSolicitud() {
        try {
            this.report = (JasperReport) JRLoader.loadObjectFromFile(REPORTS_PATH + "SolicitudSS.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params,
                    connection);
            System.out.println("...");
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
            
                if (connection != null)
                    connection.close();
            
        } catch (JRException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
