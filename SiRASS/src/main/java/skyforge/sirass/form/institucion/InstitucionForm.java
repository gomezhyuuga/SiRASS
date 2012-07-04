package skyforge.sirass.form.institucion;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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

    public InstitucionForm(Map<String, String[]> vars) {
        super(vars, true);
        institucion = null;
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

        // D de institución
        if (this.getVars().get("institucionList") != null) {
            // Institución NO existe, registrar
            if (this.getVars().get("institucionList")[0].equals("unregistred")) {
                CInstitucion cInstitucion = new CInstitucion();
                // Obtener nombre de institución
                if (this.getVars().get("nombreInstitucion") != null) {
                    cInstitucion.setNombre(this.getVars().get("nombreInstitucion")[0]);
                    Date curDate = new Date();
                    cInstitucion.setCreacion(curDate);
                    cInstitucion.setModificadoPor("system");
                    cInstitucion.setUltimaModif(curDate);
                    institucion.setIdCInstitucion(cInstitucion.getIdCInstitucion());
                }
            } else {
                // Institución EXISTE
                int id = Integer.parseInt(this.getVars().get("institucionList")[0]);
                institucion.setIdCInstitucion(id);
            }
        }
        // Detección de plantel
        if (this.getVars().get("plantelList") != null) {
            /*
             * Institución existe y plantel existe
             */
            if (this.getVars().get("institucionList") != null
                    && !this.getVars().get("institucionList")[0].equals("unregistred")
                    && !this.getVars().get("institucionList")[0].equals("0")
                    && !this.getVars().get("plantelList")[0].equals("unregistred")
                    && !this.getVars().get("plantelList")[0].equals("0")) {
                System.out.println("## Institución y plantel exiten");
                int idPlantel = Integer.parseInt(this.getVars().get("plantelList")[0]);
                int idCInstitucion = Integer.parseInt(this.getVars().get("institucionList")[0]);
                institucion.setIdPlantel(idPlantel);
                institucion.setIdCInstitucion(idCInstitucion);
            }
            /*
             * Institución existe pero plantel no
             */
            if (this.getVars().get("institucionList") != null
                    && !this.getVars().get("institucionList")[0].equals("unregistred")
                    && !this.getVars().get("institucionList")[0].equals("0")
                    && this.getVars().get("plantelList")[0].equals("unregistred")
                    && this.getVars().get("nombrePlantel") != null) {
                System.out.println("## Institución existe pero plantel no");
                Plantel plantel = new Plantel();
                plantel.setNombre(this.getVars().get("nombrePlantel")[0]);
                Date curDate = new Date();
                int idCInstitucion = Integer.parseInt(this.getVars().get("institucionList")[0]);
                plantel.setIdCInstitucion(idCInstitucion);
                plantel.setCreacion(curDate);
                plantel.setModificadoPor("system");
                plantel.setUltimaModif(curDate);
                System.out.println("plantel: " + plantel.getNombre());
                institucion.setIdPlantel(44);

                // Registrar plantel
                PlantelDAO dao = new PlantelDAO();
                dao.insert(plantel);
                institucion.setIdCInstitucion(idCInstitucion);
                institucion.setIdPlantel(plantel.getIdPlantel());
            }
            /*
             * No existe institución ni plantel
             */
            if (this.getVars().get("institucionList") != null
                    && this.getVars().get("institucionList")[0].equals("unregistred")
                    && !this.getVars().get("institucionList")[0].equals("0")
                    && this.getVars().get("nombreInstitucion") != null) {
                System.out.println("## Institución no existe ni plantel");
                Date curDate = new Date();
                CInstitucion cInstitucion = new CInstitucion();
                cInstitucion.setNombre(this.getVars().get("nombreInstitucion")[0]);
                cInstitucion.setCreacion(curDate);
                cInstitucion.setModificadoPor("system");
                cInstitucion.setUltimaModif(curDate);

                System.out.println("cinstitucion: " + cInstitucion.getNombre());

                if (this.getVars().get("plantelList")[0].equals("unregistred")) {
                    Plantel plantel = new Plantel();
                    plantel.setNombre(this.getVars().get("nombrePlantel")[0]);
                    plantel.setCreacion(curDate);
                    plantel.setModificadoPor("system");
                    plantel.setUltimaModif(curDate);
                    System.out.println("plantel: " + plantel.getNombre());

                    Set<Plantel> planteles = new HashSet<Plantel>();
                    planteles.add(plantel);
                    cInstitucion.setPlanteles(planteles);
                    // Registrar institución y plantel
                    CInstitucionDAO dao = new CInstitucionDAO();
                    dao.insert(cInstitucion);
                    institucion.setIdPlantel(plantel.getIdPlantel());
                } else {
                    CInstitucionDAO dao = new CInstitucionDAO();
                    dao.insert(cInstitucion);
                    institucion.setIdCInstitucion(cInstitucion.getIdCInstitucion());
                }
            }
        }
        return this.institucion;
    }
}
