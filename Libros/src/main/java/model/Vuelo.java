package model;


public class Vuelo {
    private Long id;
    private String ciudadOrigen;
    private String ciudadDestino;
    private String fecha;
    private String horaSalida;
    private double valor;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCiudadOrigen() { return ciudadOrigen; }
    public void setCiudadOrigen(String ciudadOrigen) { this.ciudadOrigen = ciudadOrigen; }

    public String getCiudadDestino() { return ciudadDestino; }
    public void setCiudadDestino(String ciudadDestino) { this.ciudadDestino = ciudadDestino; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getHoraSalida() { return horaSalida; }
    public void setHoraSalida(String horaSalida) { this.horaSalida = horaSalida; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}
