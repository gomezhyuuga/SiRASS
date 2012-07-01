/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.institucion.PlantelDAO;
import skyforge.sirass.model.institucion.Plantel;

/**
 *
 * @author gomezhyuuga
 */
public class Services extends HttpServlet {

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
        // Detectar tipo de servicio requerido
        if (request.getParameter("service") != null) {
            // Obtener lista de planteles por ID de institución
            if (request.getParameter("service").equals("plantelesByInst")) {
                returnPlanteles(request, response);
            }
        }
    }

    /**
     * Obtiene una lista de planteles en formato HTML como <option>
     *
     * @param request Debe contener un parámetro de nombre 'id' que indica la
     * institución sobre la cual se obtendrán los planteles
     * @param response
     * @throws IOException
     */
    private void returnPlanteles(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                List<Plantel> planteles;
                PlantelDAO dao = new PlantelDAO();
                planteles = dao.getPlanteles(id);
                if (planteles != null && !planteles.isEmpty()) {
                    try {
                        Iterator<Plantel> it = planteles.iterator();
                        while (it.hasNext()) {
                            Plantel plantel = it.next();
                            out.println("<option value=\""
                                    + plantel.getIdPlantel() + "\">"
                                    + plantel.getNombre() + "</option>");
                        }
                    } finally {
                        out.close();
                    }
                } else {
                    try {
                        out.println("<option value=\"0\">-- Sin planteles --</option>");
                    } finally {
                        out.close();
                    }
                }
            } catch (Exception e) {
                try {
                    out.println("<option value=\"0\">-- Sin planteles --</option>");
                } finally {
                    out.close();
                }
            }
        } else {
            try {
                out.println("<option value=\"0\">-- Sin planteles --</option>");
            } finally {
                out.close();
            }
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
