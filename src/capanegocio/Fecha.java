package capanegocio;

import java.util.GregorianCalendar;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    public Fecha() {
    }
    public int getDia() {
        return dia;
    }
 
    public String getHoy(){
        GregorianCalendar gc = new GregorianCalendar();
        int dia = gc.get(gc.DAY_OF_MONTH);
        int mes = gc.get(gc.MONTH) + 1;
        int año = gc.get(gc.YEAR);
        return String.format("%-2d/%-2d/%-4d",dia,mes,año);
    }


    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }


}
