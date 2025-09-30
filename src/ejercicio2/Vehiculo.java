package ejercicio2;

import java.time.LocalDateTime;

public abstract class Vehiculo {
    protected String patente;
    protected LocalDateTime horaEntrada;

    public Vehiculo(String patente) {
        this.patente = patente;
        this.horaEntrada = LocalDateTime.now();
    }

    public abstract double getTarifaPorHora();
    public abstract String getTipo();

    // Getters
    public String getPatente() { return patente; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
}