/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.institucion.InstitucionDAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.institucion.Institucion;

/**
 *
 * @author Jorge Macias
 */
public class actualDate extends HttpServlet {

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
    java.util.Date utilDate = new java.util.Date();
    java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
    int stat = 0, stat2 = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (!request.getParameter("idR").equals(null)) {
            if (request.getParameter("idR").equals("Institucion")) {
                this.upDatosInstitucion(request, response);
            }
            if (request.getParameter("idR").equals("Administrador")) {
                this.upDatosAdministrador(request, response);
            }
            if (request.getParameter("idR").equals("Prestador")) {
                this.upDatosPrestador(request, response);
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

    private void upDatosInstitucion(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String domic, respo, cargo, tel, tex, mail, modby;
        int idins;
        InstitucionDAO idao = new InstitucionDAO();
        UsuarioDAO udao = new UsuarioDAO();
        domic = String.valueOf(request.getParameter("domiU"));
        respo = String.valueOf(request.getParameter("responU"));
        cargo = String.valueOf(request.getParameter("cargoResU"));
        tel = String.valueOf(request.getParameter("telU"));
        tex = String.valueOf(request.getParameter("telExtU"));
        mail = String.valueOf(request.getParameter("emailInst"));
        idins = Integer.parseInt(request.getParameter("idInstituto"));
        modby = String.valueOf(request.getParameter("usuario"));

        stat = idao.upIns(domic, respo, cargo, tel, tex, mail, modby, idins);
        if (stat == 1) {
            stat2 = udao.upPass(request.getParameter("usuario"), request.getParameter("npassword"), request.getParameter("passwordVeif"));
            if (stat2 == 1) {
                response.sendRedirect(request.getContextPath() + "/institucion/");
            } else {
                response.sendRedirect(request.getContextPath() + "/institucion/perfilInst.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/institucion/perfilInst.jsp");
        }
    }

    private void upDatosAdministrador(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void upDatosPrestador(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
