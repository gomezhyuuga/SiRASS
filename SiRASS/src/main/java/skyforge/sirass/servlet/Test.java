/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.model.prestador.RegistroHora;

/**
 *
 * @author gomezhyuuga
 */
public class Test extends HttpServlet {

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
//        Gson gson = new Gson();
//        Type collectionType = new TypeToken<RegistroHora>(){}.getType();
        RegistroHora registro;
        String jsonOutput = "{\"fecha\":\"2012.02.10 00:00:00\",\"horaEntrada\":\"20:00\",\"horaSalida\":\"24:00\"}";
        System.out.println("jsonOutput:");
        System.out.println(jsonOutput);
//        registro = gson.fromJson(jsonOutput, collectionType);
        System.out.println("REQUEST");
//        System.out.println(registro.getHoraEntrada());
        Enumeration<String> enumer = request.getParameterNames();
        String v = "";
        while (enumer.hasMoreElements()) {
            v = "";
            String el = enumer.nextElement();
            System.out.println("key: " + el);
            System.out.println("val: ");
            String vals[] = request.getParameterValues(el);
            for (String s : vals) {
                v += s + ",";
            }
            v = v.substring(0, v.length()-1);
            System.out.println(v);
        }
        PrintWriter out = response.getWriter();
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
