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

/**
 *
 * @author gomezhyuuga
 */
public class Login extends HttpServlet {

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
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String usuario = (String) request.getParameter("username");
        String pass = (String) request.getParameter("password");

        // Intentar hacer login
        try {
            request.login(usuario, pass);
            // Lo siguiente se ejecuta si se autentifica correctamente
            log("######## Sesión iniciada #########");
            log("username: " + usuario);
            log("######## ######## ######## ######## #####\n");
            // Poner usuario como atributo en sesión
            request.getSession().setAttribute("username", usuario);
            String referer = request.getHeader("Referer");
            String pag = "";
            String redirect = referer.substring(referer.length() - 7);
            // Detectar pantalla de inicio del tipo de usuario
            if (request.isUserInRole("prestador")) {
                pag = "prestador";
            } else if (request.isUserInRole("admin")) {
                pag = "admin";
            } else if (request.isUserInRole("institucion")) {
                pag = "institucion";
            }
            if (redirect.equals("SiRASS/") || redirect.equals("SiRASS") || redirect.equals("dex.jsp")) {
                redirect = pag;
            } else {
                redirect = referer;
            }
            // establecer atributo de pag de inicio
            request.getSession().setAttribute("home", pag);
            // Enviar redirección
            response.sendRedirect(redirect);
        } catch (ServletException ex) { // En caso de que no se pueda iniciar sesión
            log("######## Error al iniciar sesión ########");
            ex.printStackTrace();
            log("######## ######## ######## ######## #####\n");
            response.setStatus(401);
            response.addHeader("requestURI", request.getRequestURI());
            response.setStatus(response.SC_UNAUTHORIZED);
            response.sendRedirect(request.getContextPath() + "?error=true");

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
