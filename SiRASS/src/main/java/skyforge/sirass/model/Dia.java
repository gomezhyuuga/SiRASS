package skyforge.sirass.model;

/**
 *
 * @author gomezhyuuga
 */
public class Dia {

    private short idDia;
    private String diaSemana;
    public static final short LUNES = 1;
    public static final short MARTES = 2;
    public static final short MIERCOLES = 3;
    public static final short JUEVES = 4;
    public static final short VIERNES = 5;
    public static final short SABADO = 6;
    public static final short DOMINGO = 7;

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
    
    public String getNombreDia(short id) {
        String dia = "";
        switch (id) {
            case 1:
                dia = "Lunes";
                break;
            case 2:
                dia = "Martes";
                break;
            case 3:
                dia = "Miércoles";
                break;
            case 4:
                dia = "Jueves";
                break;
            case 5:
                dia = "Viernes";
                break;
            case 6:
                dia = "Sábado";
                break;
            case 7:
                dia = "Domingo";
                break;
        }
        return dia;
    }
}
