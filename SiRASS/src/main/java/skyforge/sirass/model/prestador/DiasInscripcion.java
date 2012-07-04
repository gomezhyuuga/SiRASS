/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.model.prestador;

import java.io.Serializable;

/**
 *
 * @author Saul Alberto Campos
 */
public class DiasInscripcion implements Serializable {
    
    private int idInscripcion;
    private int idDia;

    public DiasInscripcion() {
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }
    
}
