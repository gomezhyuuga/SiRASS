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
import skyforge.sirass.dao.prestador.ControlHorasDAO;
import skyforge.sirass.dao.prestador.InformeBimensualDAO;
import skyforge.sirass.dao.prestador.InscripcionDAO;
import skyforge.sirass.dao.prestador.PrestadorDAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.form.prestador.ControlHorasForm;
import skyforge.sirass.form.prestador.InformeBimensualForm;
import skyforge.sirass.form.prestador.InscripcionForm;
import skyforge.sirass.model.prestador.*;
import skyforge.sirass.model.user.Usuario;

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
        String user = "system";
        if (request.getUserPrincipal() != null &&
                request.getUserPrincipal().getName() != null) {
            user = request.getUserPrincipal().getName();
        }

        if (clase != null) {
            // Inscripcion de un prestador a un programa de servicio social
            if (clase.equals("Inscripcion")) {
                System.out.println("Haciendo inscripción...");
                status = inscripcion(map, user);
            } else if (clase.equals("RevisarInscripcion")) {
                System.out.println("Actualizando estado y observaciones de inscripción....");
                status = this.revisionInscripcion(map, user);
            } else if (clase.equals("EnvioControlHoras")) {
                System.out.println("Registrando un control de horas....");
                status = this.registroControlHoras(map, user);
            } else if (clase.equals("EnvioInformeBim")) {
                System.out.println("Registrando un informe bimensual....");
                status = this.registroInformeBim(map, user);
            } else if (clase.equals("ActualizarInformeBim")) {
                System.out.println("Actualizando un informe bimensual....");
                status = this.updateInformeBim(map, user);
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

    /*
     * Realiza inscripción de un prestador en un programa de servicio social
     */
    private int inscripcion(Map<String, String[]> map, String user) {
        InscripcionForm form = new InscripcionForm(map, user);
        Inscripcion inscripcion = form.getObject(null);
        if (inscripcion != null) {
            inscripcion.setEstado(new EstadoInscripcion(EstadoInscripcion.EN_ESPERA));

            // Detectar programa institucional y su clave
            // TODO establecidadas a modo hardcore :S
            String programaInst = "Multidiciplinario de Servicio Social en Apoyo a las Actividades Académico-Administrativas de la Universidad Autónoma de la Ciudad de México";
            String claveInst = "CLAVE_INSTITUCIONAL";
            if (inscripcion.getInstitucion().getIdCInstitucion() == 2) {
                programaInst = "Multidiciplinario en la UACM";
            }
            inscripcion.setProgramaInst(programaInst);
            inscripcion.setCveProgramaInst(claveInst);
            
            Date curDate = new Date();
            inscripcion.setCreacion(curDate);
            inscripcion.setUltimaModif(curDate);

            PrestadorDAO pdao = new PrestadorDAO();
            return pdao.inscribir(inscripcion, user);
        } else {
            return 0;
        }
    }
    
    private int cambiarEstadoInscripcion(Map<String, String[]> map, String user) {
        int status = 0;
        if (map.get("id") != null) {
            try {
                int idInscripcion = Integer.parseInt(map.get("id")[0]);
                short estado = Short.parseShort(map.get("estado")[0]);
                InscripcionDAO idao = new InscripcionDAO();
                boolean ok = idao.updateEstado(idInscripcion, estado, user);
                status = (ok) ? 1 : 0;
            } catch (Exception ex) {
                System.out.println("NO SE PUDO ACTUALIZAR INSCRIPCIÓN");
                ex.printStackTrace();
                status = 0;
            }
        }
        return status;
    }
    /*
     * Actualizar estado de una inscripción
     */
    private int revisionInscripcion(Map<String, String[]> map, String user) {
        int status = 0;
        if (map.get("id") != null) {
            try {
                int idInscripcion = Integer.parseInt(map.get("id")[0]);
                InscripcionDAO idao = new InscripcionDAO();
                String observaciones = null;
                if (map.get("observaciones") != null) {
                    observaciones = map.get("observaciones")[0];
                }
                if (map.get("actualizar") != null) {
                    System.out.println("actualizando observaciones");
                    boolean ok = idao.updateObservaciones(idInscripcion, observaciones, user);
                    if (ok) {
                        status = 1;
                    }
                } else if (map.get("errores") != null) {
                    System.out.println("inscripcion con errores");
                    boolean ok = idao.updateEstadoYObservaciones(idInscripcion, observaciones, EstadoInscripcion.CON_ERRORES, user);
                    if (ok) {
                        status = 1;
                    }
                }
            } catch (Exception ex) {
                System.out.println("NO SE PUDO ACTUALIZAR INSCRIPCIÓN");
                ex.printStackTrace();
                status = 0;
            }
        }
        
        return status;
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

    /*
     * Hacer un registor de un control de horas
     */
    private int registroControlHoras(Map<String, String[]> map, String user) {
        short status = 0;
        ControlHorasForm form = new ControlHorasForm(map, user);
        ControlHoras controlHoras;
        
        controlHoras = form.getObject(null);
        controlHoras.setCreacion(new Date());
        controlHoras.setUltimaModif(new Date());
        controlHoras.setEstado(new EstadoReporte(EstadoReporte.SIN_REVISION));
        System.out.println("INSCRIPCION: " + controlHoras.getIdInscripcion());
        System.out.println("NREPORTE: " + controlHoras.getnReporte());
        System.out.println("ESTADO: " + controlHoras.getEstado().getIdEstado());
        System.out.println("SUPERVISOR: " + controlHoras.getSupervisor());
        System.out.println("FECHA_INICIO: " + controlHoras.getFechaInicio());
        System.out.println("FECHA_TERMINO: " + controlHoras.getFechaFin());
        System.out.println("HRS_MES: " + controlHoras.getHorasMes());
        System.out.println("MINS_MES: " + controlHoras.getMinutosMes());
        System.out.println("HRS_ANTERIORES: " + controlHoras.getHorasAnteriores());
        System.out.println("MINS_ANTERIORES: " + controlHoras.getMinutosAnteriores());
        System.out.println("HRS_ACUMULADAS: " + controlHoras.getHorasAcumuladas());
        System.out.println("MINS_ACUMULADOS: " + controlHoras.getMinutosAcumulados());
        System.out.println("MODIFICADO POR: " + controlHoras.getModificadoPor());
        System.out.println("HORAS: " + controlHoras.getHoras().size());
        if (controlHoras != null &&
            controlHoras.getHoras() != null &&
            controlHoras.getHoras().size() >= 1) {
            ControlHorasDAO dao = new ControlHorasDAO();
            int stat = dao.insert(controlHoras);
            System.out.println("STAT: " + stat);
            if (stat == 1) {
                status = 1;
            }
        }
        
        return status;
    }

    private int registroInformeBim(Map<String, String[]> map, String user) {
        int status = 0;
        InformeBimensualForm form = new InformeBimensualForm(map, user);
        InformeBimensual informe = form.getObject(null);
        System.out.println("obteniendo datos...");
        if (informe != null) {
            System.out.println("datos ok");
            informe.setEstado(new EstadoReporte(EstadoReporte.SIN_REVISION));
            informe.setUltimaModif(new Date());
            informe.setCreacion(new Date());
            
            InformeBimensualDAO idao = new InformeBimensualDAO();
            status = idao.insert(informe);
        }
        return status;
    }

    private int updateInformeBim(Map<String, String[]> map, String user) {
        int status = 0;
        if (map.get("id") != null) {
            try {
                int id = Integer.parseInt(map.get("id")[0]);
                InformeBimensualForm form = new InformeBimensualForm(map, user);
                InformeBimensualDAO bimensualDAO = new InformeBimensualDAO();
                InformeBimensual informe = bimensualDAO.getByPK(id);
                informe = form.getObject(informe);
                status = bimensualDAO.update(informe);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ERROR ACTUALIZANDO INFORME BIMENSUAL");
            }
        }
        return status;
    }
}
