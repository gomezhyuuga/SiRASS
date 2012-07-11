package skyforge.sirass.model;

/**
 *
 * @author gomezhyuuga
 */
public class Dia {

    private short idDia;
    private String diaSemana;

    public Dia() {
    }

    public Dia(short idDia) {
        this.idDia = idDia;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public short getIdDia() {
        return idDia;
    }

    public void setIdDia(short idDia) {
        this.idDia = idDia;
    }
}
