/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import skyforge.sirass.dao.institucion.CInstitucionDAO;
import skyforge.sirass.dao.institucion.PlantelDAO;
import skyforge.sirass.dao.programass.ProgramaSSDAO;
import skyforge.sirass.dao.user.UsuarioDAO;
import skyforge.sirass.model.Dia;
import skyforge.sirass.model.institucion.CInstitucion;
import skyforge.sirass.model.institucion.Institucion;
import skyforge.sirass.model.institucion.Plantel;
import skyforge.sirass.model.programass.*;
import skyforge.sirass.model.user.Usuario;

/**
 *
 * @author JL Macias
 */
public class sendPropuesta extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/plain;charset=utf-8");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechadate = null;

        UsuarioDAO dao = new UsuarioDAO();
        Plantel plant = new Plantel();
        PlantelDAO pdao = new PlantelDAO();
        CInstitucionDAO cdao = new CInstitucionDAO();
        System.err.println(String.valueOf(request.getParameter("usuario")));
        Usuario user = dao.getByUsername(String.valueOf(request.getParameter("usuario")));
        Institucion ins = user.getInstitucion();
        System.err.println("------------------------");
        System.err.println("Institucion "+user.getInstitucion().getIdInstitucion());
        CInstitucion cInstitucion = cdao.getById(ins.getIdCInstitucion());
        String plantel = String.valueOf(user.getInstitucion().getIdPlantel());
        if (!"null".equals(plantel)) {
            plant = pdao.getPlantelById(Integer.parseInt(plantel));
        }
        int categoInt = 0;
        String catego = cInstitucion.getNombre();
        if ("UACM".equals(catego)) {
            categoInt = 1;
        } else {
            categoInt = 2;
        }

        Date curDate = new Date(System.currentTimeMillis());

        ProgramaSS prog = new ProgramaSS();
        prog.setIdInstitucion(ins.getIdInstitucion());
        prog.setCvePrograma("");
        prog.setCategoria(new CategoriaPrograma(categoInt));
        prog.setInstitucion(catego + " - " + plant.getNombre());
        prog.setArea(ins.getArea());
        prog.setDomicilio(ins.getDomicilio());
        prog.setTel(ins.getTel());
        prog.setTelExt(ins.getTelExt());
        prog.setEmail(ins.getEmail());
        prog.setNombre(request.getParameter("nomProgIns"));
        prog.setObjGeneral(request.getParameter("objProgIns"));
        prog.setJustificacion(request.getParameter("justProgIns"));
        prog.setDesarrollo(request.getParameter("desProgIns"));
        prog.setRecursos(request.getParameter("recurProgIns"));
        prog.setEvaluacion(request.getParameter("evalProgIns"));
        prog.setResultados(request.getParameter("resulProgIns"));
        prog.setLugar(request.getParameter("lugarProgIns"));
        int pv = Integer.parseInt(request.getParameter("vacanProgIns"));
        prog.setPlazas(0);
        prog.setVacantes(0);
        System.err.println("------------------------");
        System.err.println("plazas "+ prog.getPlazas());
        System.err.println("vacantes "+prog.getVacantes());
        prog.setOcupadas(0);
        
        int hora = Integer.parseInt(request.getParameter("horaProgIns"));
        prog.setHorario(new HorarioPrograma((short) hora));
        prog.setPlazas(50);
        prog.setVacantes(40);
        prog.setObservaciones(request.getParameter("obsProgIns"));
        int tTiempo = Integer.parseInt(request.getParameter("duraProgIns"));
        prog.setTiempo(new TipoTIempoPrograma((short) tTiempo));
        if (tTiempo == 1) {
            String deterDate = "";
            deterDate = request.getParameter("vAno");
            deterDate = deterDate + "-" + request.getParameter("vMes");
            deterDate = deterDate + "-" + request.getParameter("vDia");
            fechadate = sdf.parse(deterDate);
        } else {
            Calendar date = new GregorianCalendar();
            int anio = date.get(Calendar.YEAR);
            int mes = date.get(Calendar.MONTH);
            int dia = date.get(Calendar.DAY_OF_MONTH);
            fechadate = sdf.parse((anio + 1) + "-" + mes + "-" + dia);
        }
        prog.setFechaTiempo(fechadate);

        prog.setCreacion(curDate);
        prog.setModificadoPor(request.getParameter("usuario"));
        prog.setUltimaModif(curDate);

        //Del responsable

        HashSet<ResponsablePrograma> listResp = new HashSet<ResponsablePrograma>();
        ResponsablePrograma r = new ResponsablePrograma();
        r.setResponsable(request.getParameter("respoIns"));
        r.setCargo(request.getParameter("cargoRespoIns"));
        r.setEmail(request.getParameter("emailInst"));
        r.setPrograma(prog);
        listResp.add(r);
        prog.setResponsables(listResp);

        //Select Multpiple

        String alcances[] = request.getParameterValues("alcanProgIns");
        HashSet<AlcancePrograma> lisAlcan = new HashSet<AlcancePrograma>();
        for (int i = 0; i < alcances.length; i++) {
            AlcancePrograma alcan = new AlcancePrograma();
            alcan.setIdAlcance(Short.parseShort(alcances[i]));
            lisAlcan.add(alcan);
        }
        prog.setAlcance(lisAlcan);
        
        String tipo[] = request.getParameterValues("tipoProgIns");
        HashSet<TipoPrograma> lisTipo = new HashSet<TipoPrograma>();
        for(int i = 0; i< tipo.length; i++){
            TipoPrograma tipoP = new TipoPrograma();
            tipoP.setIdTipo(Short.parseShort(tipo[i]));
            lisTipo.add(tipoP);
        }
        prog.setTipo(lisTipo);
        
        String poblaProg[] = request.getParameterValues("poblaProgIns");
        HashSet<PoblacionPrograma> lisPobla = new HashSet<PoblacionPrograma>();
        for (int i = 0; i < poblaProg.length; i++){
            PoblacionPrograma pobla = new PoblacionPrograma();
            pobla.setIdPoblacion(Short.parseShort(poblaProg[i]));
            lisPobla.add(pobla);
        }
        prog.setPoblacion(lisPobla);
        
        String diasProg[] = request.getParameterValues("diasProgIns");
        HashSet<Dia> lisdia = new HashSet<Dia>();
        for (int i = 0; i < diasProg.length; i++){
            Dia dia = new Dia();
            dia.setIdDia(Short.parseShort(diasProg[i]));
            lisdia.add(dia);
        }
        prog.setDias(lisdia);
        
        // De las licenciaturas
        HashSet<ActividadPrograma> lisActs = new HashSet<ActividadPrograma>();
        ActividadPrograma acts = new ActividadPrograma();
        acts.setActividad(request.getParameter("actProgIns"));
        acts.setLicenciatura(request.getParameter("licenProgIns"));
        acts.setnSolicitados(Short.parseShort(request.getParameter("vacanProgIns")));
        System.err.println("------------------------");
        System.err.println(request.getParameter("actProgIns"));
        System.err.println("......" + request.getParameter("licenProgIns"));
        System.err.println(Short.parseShort(request.getParameter("vacanProgIns")));
        
        acts.setPrograma(prog);
        lisActs.add(acts);
        prog.setActividad(lisActs);
        
        //Estado En espera
        CEstado estado = new CEstado();
        estado.setIdEstado((short) 4);
        estado.setDescripcion("Esperando");
        prog.setEstado(estado);
        
        prog.setNotas("Programa Registrado por "+request.getParameter("usuario")+" a la fecha y hora "+curDate);

        ProgramaSSDAO daoP = new ProgramaSSDAO();
        int i = daoP.insert(prog);
        if(i != 0){
            response.sendRedirect("/SiRASS/institucion/");
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(sendPropuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(sendPropuesta.class.getName()).log(Level.SEVERE, null, ex);
        }
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
