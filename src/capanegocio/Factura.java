package capanegocio;

public class Factura {
    private String numFactura;
    private String fechaEmision;
    private String montoTotal;
    
    public Factura(String fechaEmision, String factura, String total){
        this.fechaEmision = fechaEmision;
        this.numFactura = factura;
        this.montoTotal = total;
    }

    public String getNumFactura() {
        return numFactura;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public String getMontoTotal() {
        return montoTotal;
    }
}
