/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.form.prestador;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import skyforge.sirass.form.Form;
import skyforge.sirass.model.prestador.InformeBimensual;
import skyforge.sirass.model.prestador.InformeFinal;

/**
 *
 * @author gomezhyuuga
 */
public class InformeFinalForm extends Form {

    private InformeFinal informe;
    Date curDate;
    String modificadoPor;

    public InformeFinalForm(Map<String, String[]> vars, String modificadoPor) {
        super(vars, true);
        this.informe = null;
        this.modificadoPor = modificadoPor;
        curDate = new Date();
    }

    public InformeFinal getObject(InformeFinal obj) {
        if (obj == null) {
            this.informe = new InformeFinal();
        } else {
            this.informe = obj;
        }

        if (this.getVars().get("intro") != null) {
            String data = this.getVars().get("intro")[0];
            informe.setIntroduccion(data);
        }
        if (this.getVars().get("objGeneral") != null) {
            String data = this.getVars().get("objGeneral")[0];
            informe.setObjetivoGeneral(data);
        }
        if (this.getVars().get("objEspecif") != null) {
            String data = this.getVars().get("objEspecif")[0];
            informe.setObjetivosEspecificos(data);
        }
        if (this.getVars().get("metodologia") != null) {
            String data = this.getVars().get("metodologia")[0];
            informe.setMetodologia(data);
        }
        if (this.getVars().get("actividades") != null) {
            String data = this.getVars().get("actividades")[0];
            informe.setActividades(data);
        }
        if (this.getVars().get("metas") != null) {
            String data = this.getVars().get("metas")[0];
            informe.setMetasAlcanzadas(data);
        }
        if (this.getVars().get("resultados") != null) {
            String data = this.getVars().get("resultados")[0];
            informe.setResultados(data);
        }
        if (this.getVars().get("inscripcion") != null) {
            String data = this.getVars().get("inscripcion")[0];
            try {
                int idInscripcion = Integer.parseInt(data);
                informe.setIdInscripcion(idInscripcion);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO inscripcion");
            }
        }
        if (this.getVars().get("horasAcumuladas") != null) {
            String data = this.getVars().get("horasAcumuladas")[0];
            try {
                int horas = Integer.parseInt(data);
                informe.setHorasAcumuladas(horas);
            } catch (Exception e) {
                System.out.println("ERROR OBTENIENDO horasAcumuladas");
            }
        }

        informe.setModificadoPor(modificadoPor);
        return informe;
    }
}
