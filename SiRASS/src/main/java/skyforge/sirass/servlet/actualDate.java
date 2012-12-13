/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.admin.AdministradorDAO;
import skyforge.sirass.dao.institucion.InstitucionDAO;
import skyforge.sirass.dao.prestador.PrestadorDAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.admin.Administrador;
import skyforge.sirass.model.institucion.Institucion;
import skyforge.sirass.model.prestador.Prestador;

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
        PrintWriter out = response.getWriter();
        String domic, respo, cargo, tel, tex, mail, modby;
        int idins;
        String pass = String.valueOf(request.getParameter("npassword"));
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
        if (stat == 1 && !"".equals(pass) && pass != null) {
            stat = udao.upPass(request.getParameter("usuario"), request.getParameter("npassword"), request.getParameter("passwordVeif"));
        } 
        try {
            out = response.getWriter();
            out.print(stat);
        } catch (IOException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void upDatosAdministrador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String pass = String.valueOf(request.getParameter("npassword"));
        UsuarioDAO dao = new UsuarioDAO();
        AdministradorDAO adao = new AdministradorDAO();
        Administrador admin = new Administrador();
        admin.setEmail(request.getParameter("email"));
        admin.setModificadoPor(request.getParameter("usuario"));
        admin.setIdAdmin(Integer.parseInt(request.getParameter("idAdmin")));
        stat = adao.upAdminDat(admin);
        if (stat == 1 && !"".equals(pass) && pass != null) {
            stat = dao.upPass(request.getParameter("usuario"), request.getParameter("npassword"), request.getParameter("passwordVeif"));
        }
        try {
            out = response.getWriter();
            out.print(stat);
        } catch (IOException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    private void upDatosPrestador(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        Prestador pres = new Prestador();
        UsuarioDAO udao = new UsuarioDAO();
        PrestadorDAO prdao = new PrestadorDAO();
        String pass = String.valueOf(request.getParameter("npassword"));
        pres.setIdPrestador(Integer.parseInt(request.getParameter("idPrestador")));
        pres.setdCalle(request.getParameter("calle"));
        pres.setdNumExt(request.getParameter("nExt"));
        pres.setdNumInt(request.getParameter("nInt"));
        pres.setdCP(request.getParameter("cp"));
        pres.setdColonia(request.getParameter("col"));
        pres.setdDelegacion(request.getParameter("dele"));
        pres.setTelCasa(request.getParameter("telC"));
        pres.setTelCel(request.getParameter("telMov"));
        pres.setEmail(request.getParameter("emailPres"));
        pres.setModificadoPor(request.getParameter("usuario"));
        
        stat = prdao.upPrestador(pres);
        if (stat == 1 && !"".equals(pass) && pass != null) {
            stat = udao.upPass(request.getParameter("usuario"), request.getParameter("npassword"), request.getParameter("passwordVeif"));
        }
        try {
            out = response.getWriter();
            out.print(stat);
        } catch (IOException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }
}
