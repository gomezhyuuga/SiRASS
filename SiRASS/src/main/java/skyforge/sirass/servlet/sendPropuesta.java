/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import skyforge.sirass.dao.programass.tipoProgramaDAO;
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
        PrintWriter out = response.getWriter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fechadate = null;

        UsuarioDAO dao = new UsuarioDAO();
        Plantel plant = new Plantel();
        PlantelDAO pdao = new PlantelDAO();
        CInstitucionDAO cdao = new CInstitucionDAO();
        Usuario user = dao.getByUsername(String.valueOf(request.getUserPrincipal().getName()));
        Institucion ins = user.getInstitucion();
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
        prog.setPlazas(pv);
        prog.setVacantes(pv);


        prog.setOcupadas(0);

        int hora = Integer.parseInt(request.getParameter("horaProgIns"));
        prog.setHorario(new HorarioPrograma((short) hora));
        prog.setPlazas(50);
        prog.setVacantes(40);
        prog.setObservaciones(request.getParameter("obsProgIns"));
        int tTiempo = Integer.parseInt(request.getParameter("duraProgIns"));
        prog.setTiempo(new TipoTIempoPrograma((short) tTiempo));
        if (tTiempo == 2) {
            String deterDate = "";
            deterDate = request.getParameter("vencimiento").substring(6, 10).concat("-").concat(request.getParameter("vencimiento").substring(3, 5).concat("-").concat(request.getParameter("vencimiento").substring(0, 2)));
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
        prog.setModificadoPor(request.getUserPrincipal().getName());
        prog.setUltimaModif(curDate);

        //Del responsable
        String respon[] = request.getParameterValues("respoIns");
        String cargoRes[] = request.getParameterValues("cargoRespoIns");
        String mailRes[] = request.getParameterValues("emailInst");
        HashSet<ResponsablePrograma> listResp = new HashSet<ResponsablePrograma>();
        for (int z = 0; z < respon.length; z++) {
            ResponsablePrograma r = new ResponsablePrograma();
            r.setResponsable(respon[z]);
            r.setCargo(cargoRes[z]);
            r.setEmail(mailRes[z]);
            r.setPrograma(prog);
            listResp.add(r);
        }
        prog.setResponsables(listResp);

        // De las licenciaturas
        String actsProg[] = request.getParameterValues("actProgIns");
        String licenProg[] = request.getParameterValues("licenProgIns");
        String vacanProg[] = request.getParameterValues("vacanProgIns");
        HashSet<ActividadPrograma> lisActs = new HashSet<ActividadPrograma>();
        for (int j = 0; j < actsProg.length; j++) {
            ActividadPrograma acts = new ActividadPrograma();
            acts.setActividad(actsProg[j]);
            acts.setLicenciatura(licenProg[j]);
            acts.setnSolicitados(Short.parseShort(vacanProg[j]));
            acts.setPrograma(prog);
            lisActs.add(acts);
        }
        prog.setActividad(lisActs);

        //Select Multpiple

        String alcances[] = request.getParameterValues("alcanProgIns");
        HashSet<AlcancePrograma> lisAlcan = new HashSet<AlcancePrograma>();
        for (int i = 0; i < alcances.length; i++) {
            AlcancePrograma alcan = new AlcancePrograma();
            alcan.setIdAlcance(Short.parseShort(alcances[i]));
            lisAlcan.add(alcan);
        }
        prog.setAlcance(lisAlcan);

        HashSet<TipoPrograma> lisTipo = new HashSet<TipoPrograma>();
        String tipo = request.getParameter("tipoProgIns");
        if (tipo.equals("sinRegistro")) {
            tipoProgramaDAO tdao = new tipoProgramaDAO();
            TipoPrograma tipoP = new TipoPrograma();
            tipoP.setDescripcion(request.getParameter("nombreOtroTipo"));
            //tdao.insert(tipoP);
            lisTipo.add(tipoP);
            prog.setTipo(lisTipo);
        } else {
            TipoPrograma tipoP = new TipoPrograma();
            tipoP.setIdTipo(Short.parseShort(tipo));
            lisTipo.add(tipoP);
            prog.setTipo(lisTipo);
        }

        String poblaProg[] = request.getParameterValues("poblaProgIns");
        HashSet<PoblacionPrograma> lisPobla = new HashSet<PoblacionPrograma>();
        for (int i = 0; i < poblaProg.length; i++) {
            PoblacionPrograma pobla = new PoblacionPrograma();
            pobla.setIdPoblacion(Short.parseShort(poblaProg[i]));
            lisPobla.add(pobla);
        }
        prog.setPoblacion(lisPobla);

        String diasProg[] = request.getParameterValues("diasProgIns");
        HashSet<Dia> lisdia = new HashSet<Dia>();
        for (int i = 0; i < diasProg.length; i++) {
            Dia dia = new Dia();
            dia.setIdDia(Short.parseShort(diasProg[i]));
            lisdia.add(dia);
        }
        prog.setDias(lisdia);

        //Estado En espera
        CEstado estado = new CEstado();
        estado.setIdEstado((short) 4);
        estado.setDescripcion("Esperando");
        prog.setEstado(estado);
        
        Calendar date = new GregorianCalendar();
            int anio = date.get(Calendar.YEAR);
            int mes = date.get(Calendar.MONTH);
            int dia = date.get(Calendar.DAY_OF_MONTH);
            fechadate = sdf.parse(anio + "-" + mes + "-" + dia);

        prog.setNotas("Programa Registrado por " + request.getUserPrincipal().getName() + " a la fecha y hora " + sdf.parse(anio + "-" + mes + "-" + dia));

        ProgramaSSDAO daoP = new ProgramaSSDAO();
        int i = daoP.insert(prog);
        int j = daoP.upPV(pv, prog.getIdPrograma());
        int status;
        if (i == 1 && j == 1) {
            status = 1;
        } else {
            status = 0;
        }
        try {
            out = response.getWriter();
            out.print(status);
        } catch (IOException ex) {
            Logger.getLogger(Services.class.getName()).log(Level.SEVERE, null, ex);
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
