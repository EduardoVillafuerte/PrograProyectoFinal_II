package capanegocio;

import java.util.List;

public class Factura {
    private Number numFactura;
    private String fechaEmision;
    private float montoTotal;
    private String montoTotalStr;
    private List<Articulo> articulos;
    private String nombreFactura;
    
    public Factura(String fechaEmision, Number factura, float total, List<Articulo> articulos){
        this.fechaEmision = fechaEmision;
        this.numFactura = factura;
        this.montoTotal = total;
        this.articulos = articulos;
        
    }
    
    public Factura(String fecha, String nombre, String valor){
        this.fechaEmision = fecha;
        this.nombreFactura = nombre;
        this.montoTotalStr = valor;
    }
    
    public String getFecha(){
        return this.fechaEmision;
    }
    
    public List<Articulo> getArticulos(){
        return this.articulos;
    }
    
    public float getTotal(){
        return this.montoTotal;
    }
    
    public Number getNumFac(){
        return this.numFactura;
    }
    
    public String getTotalStr(){
        return this.montoTotalStr;
    }
    
    public String getnombre(){
        return this.nombreFactura;
    }
    
}
