package capanegocio;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numFactura;
    private Fecha fechaEmision;
    private double montoTotal;
    private boolean pagada;
    private String descripcion;
    private Habitacion habitacion;
    private MetodoPago metodoPago;

    public Factura(int numFactura, String cliente, Fecha fechaEmision, double montoTotal, MetodoPago metodoPago) {
        this.numFactura = numFactura;
        this.fechaEmision = fechaEmision;
        this.montoTotal = montoTotal;
        this.pagada = false;
        this.metodoPago = metodoPago;
    }

    public int getNumFactura() {
        return numFactura;
    }

    public Fecha getFechaEmision() {
        return fechaEmision;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setPagada(boolean pagada) {
        this.pagada = pagada;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void pagar(){
        this.pagada = true;
    }

}
