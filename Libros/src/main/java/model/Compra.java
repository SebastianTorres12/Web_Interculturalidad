package model;


public class Compra {
    private Long id;
    private String comprador;
    private String fechaCompra;
    private Vuelo vuelo;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getComprador() { return comprador; }
    public void setComprador(String comprador) { this.comprador = comprador; }

    public String getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(String fechaCompra) { this.fechaCompra = fechaCompra; }

    public Vuelo getVuelo() { return vuelo; }
    public void setVuelo(Vuelo vuelo) { this.vuelo = vuelo; }
}
