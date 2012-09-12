/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.prestador.ControlHorasDAO;
import skyforge.sirass.dao.prestador.InscripcionDAO;
import skyforge.sirass.model.Dia;
import skyforge.sirass.model.prestador.ControlHoras;
import skyforge.sirass.model.prestador.DiasInscripcion;
import skyforge.sirass.model.prestador.Inscripcion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.programass.ResponsablePrograma;
import skyforge.sirass.reports.Control_Mensual_De_Horas;
import skyforge.sirass.reports.Report;
import skyforge.sirass.reports.Reporte;

/**
 *
 * @author gomezhyuuga
 */
public class Generator extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/pdf");
        String doc = "";
        // Obtener el tipo de doc. a generar
        if (request.getParameter("doc") != null) {
            doc = request.getParameter("doc");
            if (doc.equals("ControlHoras")) {
                this.generarControlHoras(request, response);
            }
            if (doc.equals("SolicitudSS")) {
                this.generarSolicitud(request, response);
            }
        } else {
            errorReporte(request, response, "No se seleccionó reporte");
        }
    }
    
    public void generarSolicitud(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            response.setHeader("Content-Disposition", "Attachment;filename=Solicitud.pdf");
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("GENERANDO!");
            Map params = new HashMap();
            params.put("ID", id);
            InscripcionDAO idao = new InscripcionDAO();
            List<DiasInscripcion> dias = idao.getDiasInscripcion(id);
            String diasStr = "";
            if (dias != null && dias.size() > 0) {
                Iterator<DiasInscripcion> it = dias.iterator();
                while (it.hasNext()) {
                    short dia = (short) it.next().getIdDia();
                    Dia d = new Dia();
                    diasStr += d.getNombreDia(dia) + " ";
                }
            }
            params.put("DIAS", diasStr);
            Report r = new Report(getServletContext(), response.getOutputStream(), params);
            r.generarSolicitud();
        } else {
            errorReporte(request, response, "ERROR EN ID DE INSCRIPCIÓN");
        }
    }
    
    public void generarControlHoras(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
//                response.setHeader("Content-Disposition", "Attachment;filename= "
//                        + Reporte.Control_Mensual_De_Horas);//Ponemos el tipo de documento   
                response.setHeader("Content-Disposition", "Attachment;filename=ok.xls ");//Ponemos el tipo de documento   
                ControlHoras controlHoras = null;
                Prestador prestador = null;
                Inscripcion inscripcion = null;

                // Obtener controlHoras
                ControlHorasDAO cdao = new ControlHorasDAO();
                controlHoras = cdao.getByPK(id);
                if (controlHoras != null) {
                    // Obtener inscrpción
                    InscripcionDAO idao = new InscripcionDAO();
                    inscripcion = idao.getByPKForReport(controlHoras.getIdInscripcion());
                    System.out.println("HORAS: " + controlHoras.getHoras().size());
                    System.out.println("FECHA: " + controlHoras.getHoras().iterator().next().getFecha());
                    if (inscripcion != null && inscripcion.getPrestador() != null) {
                        prestador = inscripcion.getPrestador();
                        HashSet<ResponsablePrograma> res = new HashSet<ResponsablePrograma>();
                        ResponsablePrograma m = new ResponsablePrograma();
                        m.setResponsable("ALGUIEN X");
                        res.add(m);
//                        Control_Mensual_De_Horas reporte = new Control_Mensual_De_Horas(
//                                prestador, inscripcion, res, controlHoras, false);
                        response.reset();
                        Control_Mensual_De_Horas reporte = new Control_Mensual_De_Horas(
                                prestador, inscripcion, inscripcion.getResponsable(), controlHoras, false);
                        Reporte generator = new Reporte(request.getRealPath("Archivos/"));
                        generator.establecerDatos(Reporte.Control_Mensual_De_Horas, reporte);
                        generator.generar(response.getOutputStream());
                    } else {
                        errorReporte(request, response, "Inscripción no encontrada");
                    }
                } else {
                    errorReporte(request, response, "Reporte no encontrado");
                }
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO ID CONTROLHORAS");
                e.printStackTrace();
                errorReporte(request, response, "Error obteniendo ID de reporte");
            }
        } else {
            errorReporte(request, response, "ID de reporte incorrecto");
        }
    }
    
    public void errorReporte(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        response.sendError(500, error);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
