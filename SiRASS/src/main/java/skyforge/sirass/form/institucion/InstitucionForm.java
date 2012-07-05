package skyforge.sirass.form.institucion;

import java.util.Date;
import java.util.Map;
import skyforge.sirass.dao.institucion.CInstitucionDAO;
import skyforge.sirass.dao.institucion.PlantelDAO;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.institucion.CInstitucion;
import skyforge.sirass.model.institucion.Institucion;
import skyforge.sirass.model.institucion.Plantel;

/**
 *
 * @author gomezhyuuga
 */
public class InstitucionForm extends Form {

    Institucion institucion;
    Date curDate;
    String modificadoPor;

    public InstitucionForm(Map<String, String[]> vars, String modificadoPor) {
        super(vars, true);
        institucion = null;
        curDate = new Date();
        this.modificadoPor = modificadoPor;
    }

    /**
     * Genera el objeto con los datos de un map clave-valor (request map)
     *
     * @return Objeto tipo Institucion con los datos encontrados en el map
     */
    public Institucion getObject() {
        this.institucion = new Institucion();

        if (this.getVars().get("domicilio") != null) {
            institucion.setDomicilio(this.getVars().get("domicilio")[0]);
        }
        if (this.getVars().get("area") != null) {
            institucion.setArea(this.getVars().get("area")[0]);
        }
        if (this.getVars().get("responsable") != null) {
            institucion.setResponsable(this.getVars().get("responsable")[0]);
        }
        if (this.getVars().get("cargo") != null) {
            institucion.setCargo(this.getVars().get("cargo")[0]);
        }
        if (this.getVars().get("tel") != null) {
            institucion.setTel(this.getVars().get("tel")[0]);
        }
        if (this.getVars().get("telExt") != null) {
            institucion.setTelExt(this.getVars().get("telExt")[0]);
        }
        if (this.getVars().get("email") != null) {
            institucion.setEmail(this.getVars().get("email")[0]);
        }

        // Detección de institución y plantel
        /*
         * CASOS (OK -> EXISTE)
         * 1) Institución OK | Plantel OK
         *      institucionList -> != "" && != unregistred && != null
         *      plantelList -> != "" && != unregistred && != 0
         * 2) Institución OK | Plantel NO
         *      institucionList -> != "" && != unregistred && != null
         *      plantelList -> != "" && != 0 && == unregistred
         *      nombrePlantel -> != "" && != null
         * 3) Institución OK | Plantel NULL
         *      institucionList -> != "" && != unregistred && != null
         *      plantelList -> != "" && == 0 && != unregistred
         * 4) Institucion NO | Plantel NO
         *      institucionList -> != "" && != null && == unregistred
         *      nombreInstitucion -> != "" && != null
         *      plantelList -> != "" && != 0 && == unregistred
         *      nombrePlantel -> != "" && != null
         * 5) Institucion NO | Plantel NULL
         *      institucionList -> != "" && != null && == unregistred
         *      nombreInstitucion -> != "" && != null
         *      plantelList -> != "" && == 0 && != unregistred
         */
        // Comprobación genérica
        if (this.getVars().get("institucionList") != null &&
            this.getVars().get("plantelList") != null) {
            String institucionList = this.getVars().get("institucionList")[0];
            String plantelList = this.getVars().get("plantelList")[0];
            // Casos 1-3
            if (institucionList != null &&
                !institucionList.equals("") &&
                !institucionList.equals("unregistred")) {
                int idCInstitucion = Integer.parseInt(institucionList);
                institucion.setIdCInstitucion(idCInstitucion);
                // Caso 1
                if (plantelList != null &&
                    !plantelList.equals("unregistred") &&
                    !plantelList.equals("0")) {
                    int idPlantel = Integer.parseInt(plantelList);
                    institucion.setIdPlantel(idPlantel);
                } else if (plantelList != null &&   // Caso 2
                        !plantelList.equals("0") &&
                        plantelList.equals("unregistred") &&
                        this.getVars().get("nombrePlantel") != null) {
                    String nombrePlantel = this.getVars().get("nombrePlantel")[0];
                    int idPlantel = this.registrarPlantel(nombrePlantel, idCInstitucion);
                    institucion.setIdPlantel(idPlantel);
                } else if (plantelList != null &&   // Caso 3
                        plantelList.equals("0") &&
                        !plantelList.equals("unregistred")) {
                    institucion.setIdPlantel(null);
                }
            } else if (institucionList != null &&
                !institucionList.equals("") &&
                institucionList.equals("unregistred") &&
                this.getVars().get("nombreInstitucion") != null) {
                // Casos 4-5
                String nombreInstitucion = this.getVars().get("nombreInstitucion")[0];
                int id = this.registrarCInstitucion(nombreInstitucion);
                institucion.setIdCInstitucion(id);
                
                // Caso 4
                if (plantelList != null && 
                    !plantelList.equals("0") && 
                    plantelList.equals("unregistred") &&
                    this.getVars().get("nombrePlantel") != null) {
                    String nombrePlantel = this.getVars().get("nombrePlantel")[0];
                    int idPlantel = this.registrarPlantel(nombrePlantel, id);
                    institucion.setIdPlantel(idPlantel);
                } else if (plantelList != null && 
                    plantelList.equals("0") && 
                    !plantelList.equals("registred")) {
                    institucion.setIdPlantel(null);
                }
            }
        }
        
        return this.institucion;
    }
    
    private int registrarPlantel(String nombrePlantel, int idCInstitucion) {
        PlantelDAO plantelDAO = new PlantelDAO();
        Plantel plantel = new Plantel();
        plantel.setIdCInstitucion(idCInstitucion);
        plantel.setNombre(nombrePlantel);
        plantel.setCreacion(curDate);
        plantel.setUltimaModif(curDate);
        plantel.setModificadoPor(modificadoPor);
        plantelDAO.insert(plantel);
        return plantel.getIdPlantel();
    }
    
    private int registrarCInstitucion(String nombre) {
        CInstitucion cInstitucion = new CInstitucion();
        cInstitucion.setNombre(nombre);
        cInstitucion.setCreacion(curDate);
        cInstitucion.setUltimaModif(curDate);
        cInstitucion.setModificadoPor(modificadoPor);
        CInstitucionDAO cInstitucionDAO = new CInstitucionDAO();
        cInstitucionDAO.insert(cInstitucion);
        return cInstitucion.getIdCInstitucion();
    }
}
