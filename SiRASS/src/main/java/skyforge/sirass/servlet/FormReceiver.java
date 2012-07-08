/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.prestador.InscripcionDAO;
import skyforge.sirass.dao.prestador.PrestadorDAO;
import skyforge.sirass.form.prestador.InscripcionForm;
import skyforge.sirass.model.prestador.EstadoInscripcion;
import skyforge.sirass.model.prestador.Inscripcion;

/**
 *
 * @author gomezhyuuga
 */
public class FormReceiver extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String clase;
        Map<String, String[]> map = request.getParameterMap();
        clase = request.getParameter("class");
        int status = 0;

        if (clase != null) {
            // Inscripcion de un prestador a un programa de servicio social
            if (clase.equals("Inscripcion")) {
                System.out.println("Haciendo inscripción...");
                status = inscripcion(map, request.getUserPrincipal().getName());
            }
        }
        PrintWriter out = response.getWriter();
        try {
            System.out.println("status: " + status);
            out.print(status);
        } finally {
            out.close();
            out.flush();
        }
    }

    private int inscripcion(Map<String, String[]> map, String user) {
        InscripcionForm form = new InscripcionForm(map, user);
        Inscripcion inscripcion = form.getObject();
        if (inscripcion != null) {
            inscripcion.setEstado(new EstadoInscripcion((short) 6));

            Date curDate = new Date();
            inscripcion.setCreacion(curDate);
            inscripcion.setUltimaModif(curDate);

            // Hacer registro en DB
            InscripcionDAO dao = new InscripcionDAO();
            int status = dao.insert(inscripcion);
            if (status == 1) {
                // Establecer inscripción de prestador
                PrestadorDAO pdao = new PrestadorDAO();
                return pdao.setInscripcion(inscripcion.getIdInscripcion(), user);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
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
