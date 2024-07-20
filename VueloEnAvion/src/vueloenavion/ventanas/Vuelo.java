package vueloenavion.ventanas;

import java.util.Date;

public class Vuelo {
    private String aerolinea;
    private String origen;
    private String destino;
    private Date fechaSalida;
    private java.sql.Time horaSalida;
    private Date fechaLlegada;
    private java.sql.Time horaLlegada;
    private java.sql.Time duracion;
    private double precio;

    // Getters y setters

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public java.sql.Time getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(java.sql.Time horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(Date fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public java.sql.Time getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(java.sql.Time horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public java.sql.Time getDuracion() {
        return duracion;
    }

    public void setDuracion(java.sql.Time duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "aerolinea='" + aerolinea + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", fechaSalida=" + fechaSalida +
                ", horaSalida=" + horaSalida +
                ", fechaLlegada=" + fechaLlegada +
                ", horaLlegada=" + horaLlegada +
                ", duracion=" + duracion +
                ", precio=" + precio +
                '}';
    }
}
