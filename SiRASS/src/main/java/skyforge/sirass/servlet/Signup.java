/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.form.SignupForm;
import skyforge.sirass.model.institucion.Institucion;
import skyforge.sirass.model.prestador.Prestador;
import skyforge.sirass.model.user.Rol;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author gomezhyuuga
 */
public class Signup extends HttpServlet {

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

        Prestador prestador = null;
        Institucion institucion = null;
        Usuario usuario = null;
        UsuarioDAO dao = null;
        SignupForm form = new SignupForm(request.getParameterMap());
        int status = 0;
        Date curDate = new Date();
        String modificadoPor = "system";

        usuario = form.getUsuario();

        if (request.getParameter("class") != null
                && request.getParameter("class").equals("SignupPrestador")) {
            prestador = form.getPrestador();
            prestador.setCreacion(curDate);
            prestador.setModificadoPor(modificadoPor);
            prestador.setUltimaModif(curDate);
            
            Set<Rol> roles = new HashSet<Rol>();
            roles.add(new Rol("prestador"));
            
            usuario.setPrestador(prestador);
            usuario.setRoles(roles);
            
            log("Registrando prestador...");
            log(prestador.toString());
        } else if (request.getParameter("class") != null
                && request.getParameter("class").equals("SignupInstitucion")) {
            String nombreUsuario = "system";
            if (request.getUserPrincipal() != null &&
                    request.getUserPrincipal().getName() != null) {
                nombreUsuario = request.getUserPrincipal().getName();
            }
            institucion = form.getInstitucion(nombreUsuario);
            institucion.setCreacion(curDate);
            institucion.setModificadoPor(modificadoPor);
            institucion.setUltimaModif(curDate);
            
            usuario.setInstitucion(institucion);
            Set<Rol> roles = new HashSet<Rol>();
            roles.add(new Rol("institucion"));
            usuario.setRoles(roles);
            log("Registrando institución...");
            log(institucion.toString());
        }

        usuario.setCreacion(curDate);
        usuario.setUltimaModif(curDate);
        usuario.setModificadoPor(modificadoPor);

        dao = new UsuarioDAO();
        status = dao.insert(usuario);

        PrintWriter out = response.getWriter();
        try {
            out.print(status);
        } finally {
            out.close();
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
