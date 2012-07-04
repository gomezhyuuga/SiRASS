/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skyforge.sirass.model.user;

import java.io.Serializable;

/**
 *
 * @author gomezhyuuga
 */
public class Test implements Serializable {

    private String name;
    private int edad;
    private String[] dias;
    private String[] otro;

    public Test() {
    }

    public String[] getOtro() {
        return otro;
    }

    public void setOtro(String[] otro) {
        this.otro = otro;
    }

    public String[] getDias() {
        return dias;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
