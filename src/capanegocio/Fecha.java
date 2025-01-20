package capanegocio;

public class Fecha {
    private int dia;
    private int mes;
    private int anio;

    public Fecha(int dia, int mes, int anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        validarFecha();
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
        validarFecha();
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
        validarFecha();
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
        validarFecha();
    }

    private void validarFecha() {
        if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1) {
            throw new IllegalArgumentException("Fecha inválida");
        }
    }

}
