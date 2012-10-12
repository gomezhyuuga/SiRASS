/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.model.programass;

import java.io.Serializable;

/**
 *
 * @author Jorge Macias
 */
public class DTipoPrograma implements Serializable{
    
    private int idPrograma;
    private int idTipo;

    public DTipoPrograma(int idPrograma, int idTipo) {
        this.idPrograma = idPrograma;
        this.idTipo = idTipo;
    }

    public DTipoPrograma() {
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
}