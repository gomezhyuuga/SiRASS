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
import skyforge.sirass.dao.programass.ActividadProgramaDAO;
import skyforge.sirass.dao.programass.ProgramaSSDAO;
import skyforge.sirass.dao.programass.ResponsableProgramaDAO;
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
        prog.setIdPrograma(Integer.parseInt(request.getParameter("idPrograma")));
        prog.setIdInstitucion(ins.getIdInstitucion());
        prog.setCvePrograma(request.getParameter("cvePrograma"));
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
            if (deterDate == null && deterDate == "") {
                Calendar date = new GregorianCalendar();
                int anio = date.get(Calendar.YEAR);
                int mes = date.get(Calendar.MONTH);
                int dia = date.get(Calendar.DAY_OF_MONTH);
                deterDate = (anio + 1) + "-" + mes + "-" + dia;
            }
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
        String idRes[] = request.getParameterValues("idRespon");
        String idResCo[] = request.getParameterValues("idResponCop");
        HashSet<ResponsablePrograma> listResp = new HashSet<ResponsablePrograma>();
        int resCop = 0;
        int lenRes = 0;
        int lenResCop = 0;
        if (request.getParameterValues("idRespon") != null) {
            lenRes = idRes.length;
        } else if (request.getParameterValues("idRespon") == null) {
            lenRes = 0;
        }
        if (request.getParameterValues("idResponCop") != null) {
            lenResCop = idResCo.length;
        } else if (request.getParameterValues("idResponCop") == null) {
            lenResCop = 0;
        }
        for (int z = 0; z < respon.length; z++) {
            ResponsablePrograma r = new ResponsablePrograma();
            if (lenRes > z && lenRes != 0) {
                if (idRes[z] != null && idRes[z] != "") {
                    System.out.println("--------------------------");
                    r.setIdResponsable(Integer.parseInt(idRes[z]));
                }
            } else {
                if (lenResCop != 0 && resCop < lenResCop) {
                    System.out.println("-------------RESPON---------" + resCop + "<" + lenResCop);
                    r.setIdResponsable(Integer.parseInt(idResCo[resCop]));
                    resCop++;
                }
            }
            r.setResponsable(respon[z]);
            r.setCargo(cargoRes[z]);
            r.setEmail(mailRes[z]);
            r.setPrograma(prog);

            listResp.add(r);
        }
        prog.setResponsables(listResp);
        int idProg = Integer.parseInt(request.getParameter("idPrograma"));
        int stat = 0;
        ResponsableProgramaDAO resDao = new ResponsableProgramaDAO();
        while (resCop < lenResCop) {
            System.out.println("||||||||||||||||||||||||||||||||||");
            stat = resDao.deleteRespon(idResCo[resCop], idProg);
            resCop++;
        }
        if (resCop == 0 && lenResCop == 0) {
            stat = 1;
        }

        // De las licenciaturas
        String actsProg[] = request.getParameterValues("actProgIns");
        String licenProg[] = request.getParameterValues("licenProgIns");
        String vacanProg[] = request.getParameterValues("vacanProgIns");
        String idLicen[] = request.getParameterValues("idLicen");
        String idLicenCo[] = request.getParameterValues("idActCop");
        int pv = 0;
        int licenCop = 0;
        HashSet<ActividadPrograma> lisActs = new HashSet<ActividadPrograma>();
        int lenAct = 0;
        int lenActCop = 0;
        if (request.getParameterValues("idLicen") != null) {
            lenAct = idLicen.length;
        } else if (request.getParameterValues("idLicen") == null) {
            lenAct = 0;
        }
        if (request.getParameterValues("idActCop") != null) {
            lenActCop = idLicenCo.length;
        } else if (request.getParameterValues("idActCop") == null) {
            lenActCop = 0;
        }
        for (int j = 0; j < actsProg.length; j++) {
            int valPV;
            ActividadPrograma acts = new ActividadPrograma();
            if (lenAct > j && lenAct != 0) {
                if (idLicen[j] != null && idLicen[j] != "") {
                    System.out.println(idLicen[j]);
                    acts.setIdActividad(Integer.parseInt(idLicen[j]));
                }
            } else {
                if (lenActCop != 0 && licenCop < lenActCop) {
                    acts.setIdActividad(Integer.parseInt(idLicenCo[licenCop]));
                    System.out.println("|||||||||||||||" + idLicenCo[licenCop]);
                    System.out.println("-------------" + licenCop + "-------------");
                    licenCop++;
                }
            }
            acts.setIdPrograma(Integer.parseInt(request.getParameter("idPrograma")));
            acts.setActividad(actsProg[j]);
            acts.setLicenciatura(licenProg[j]);
            acts.setnSolicitados(Short.parseShort(vacanProg[j]));
            valPV = Integer.parseInt(vacanProg[j]);
            pv += valPV;
            acts.setPrograma(prog);
            lisActs.add(acts);
        }

        prog.setActividad(lisActs);
        int stat2 = 0;
        ActividadProgramaDAO actDAO = new ActividadProgramaDAO();
        while (licenCop < lenActCop) {
            stat2 = actDAO.deleteActiv(idLicenCo[licenCop], idProg);
            licenCop++;
        }
        if (licenCop == 0 && lenActCop == 0) {
            stat2 = 1;
        }
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
            String descripcionTipo = request.getParameter("nombreOtroTipo");
            registrarTipo(descripcionTipo);
            tipoProgramaDAO dtp = new tipoProgramaDAO();
            short idTipoNuevo = dtp.getIdTipoByName(descripcionTipo);
            TipoPrograma tipoP = new TipoPrograma();
            tipoP.setIdTipo(idTipoNuevo);
            tipoP.setDescripcion(descripcionTipo);
            lisTipo.add(tipoP);
            prog.setTipo(lisTipo);
        } else if (!tipo.equals("sinRegistro")) {
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

        estado.setIdEstado(
                (short) 4);
        estado.setDescripcion("Esperando");
        prog.setEstado(estado);
        Calendar date = new GregorianCalendar();
        int anio = date.get(Calendar.YEAR);
        int mes = date.get(Calendar.MONTH);
        int dia = date.get(Calendar.DAY_OF_MONTH);
        fechadate = sdf.parse(anio + "-" + mes + "-" + dia);

        prog.setNotas(request.getParameter("observaciones"));

        ProgramaSSDAO daoP = new ProgramaSSDAO();

        prog.setPlazas(pv);

        prog.setVacantes(pv);
        //int i = daoP.update(prog);
        int status = 0;
        if (stat != 0 && stat2 != 0) {
            status = 1;
        } else if (stat == 0 && stat2 == 0) {
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

    private void registrarTipo(String nombreTipo) {
        tipoProgramaDAO dao = new tipoProgramaDAO();
        TipoPrograma tipo = new TipoPrograma();
        tipo.setDescripcion(nombreTipo);
        dao.insert(tipo);
    }
}
