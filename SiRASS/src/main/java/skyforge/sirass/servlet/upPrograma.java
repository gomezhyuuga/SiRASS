/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.programass.ProgramaSSDAO;
import skyforge.sirass.model.programass.ActividadPrograma;
import skyforge.sirass.model.programass.ProgramaSS;
import skyforge.sirass.model.programass.ResponsablePrograma;

/**
 *
 * @author Jorge Macias
 */
public class upPrograma extends HttpServlet {

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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");
        ProgramaSSDAO pdao = new ProgramaSSDAO();
        ProgramaSS programaSS = upProg(request);
        programaSS.setModificadoPor(request.getParameter("usuario"));
        programaSS.setUltimaModif(sqlTimestamp);
        programaSS.setIdPrograma(Integer.parseInt(request.getParameter("idPrograma")));
        String respon[] = request.getParameterValues("respoIns");
        String cargo[] = request.getParameterValues("cargoRespoIns");
        String emailres[] = request.getParameterValues("emailInst");
        HashSet<ResponsablePrograma> listResp = new HashSet<ResponsablePrograma>();
        for (int i = 0; i < respon.length; i++) {
            ResponsablePrograma r = new ResponsablePrograma();
            r.setResponsable(respon[i]);
            r.setCargo(cargo[i]);
            r.setEmail(emailres[i]);
            r.setPrograma(programaSS);
            listResp.add(r);
        }
        programaSS.setResponsables(listResp);

        String licen[] = request.getParameterValues("licenProg");
        String acts[] = request.getParameterValues("actsProg");
        String vacan[] = request.getParameterValues("vacanProg");
        HashSet<ActividadPrograma> lisActs = new HashSet<ActividadPrograma>();
        for (int i = 0; i < licen.length; i++) {
            ActividadPrograma actP = new ActividadPrograma();
            actP.setActividad(acts[i]);
            actP.setLicenciatura(licen[i]);
            actP.setnSolicitados(Short.parseShort(vacan[i]));
            actP.setPrograma(programaSS);
            lisActs.add(actP);
        }
        programaSS.setActividad(lisActs);

        String command = "UPDATE ProgramaSS SET nombre = :nameProg, objGeneral = :objG, desarrollo = :desa,"
                + "observaciones = :obs, modificadoPor = :mp, ultimaModif = :um "
                + "WHERE idPrograma = :id";
        

        int stat = pdao.upProgSS(programaSS, command);
        
        if (stat == 1) {
            response.sendRedirect(request.getContextPath() + "/institucion/actualProgramas.jsp");
        }
        else{
            response.sendRedirect(request.getContextPath() + "/institucion/editarPrograma.jsp?idPrograma="+request.getParameter("idPrograma"));
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

    private ProgramaSS upProg(HttpServletRequest request) {
        ProgramaSS programaSS = new ProgramaSS();
        programaSS.setNombre(request.getParameter("nomProgIns"));
        programaSS.setObjGeneral(request.getParameter("objProgIns"));
        programaSS.setDesarrollo(request.getParameter("desProgIns"));
        programaSS.setObservaciones(request.getParameter("obsProgIns"));

        return programaSS;
    }
    
}
