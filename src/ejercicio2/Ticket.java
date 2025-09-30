package ejercicio2;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Ticket {
    private Vehiculo vehiculo;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;
    private double importePagado;

    public Ticket(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        this.horaEntrada = vehiculo.getHoraEntrada();
    }

    public void finalizarTicket() {
        this.horaSalida = LocalDateTime.now();
        this.importePagado = calcularImporte();
    }

    private double calcularImporte() {
        if (horaSalida == null) return 0;

        long horas = ChronoUnit.HOURS.between(horaEntrada, horaSalida);
        // Mínimo 1 hora
        if (horas == 0) horas = 1;

        return horas * vehiculo.getTarifaPorHora();
    }

    // Getters
    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDateTime getHoraEntrada() { return horaEntrada; }
    public LocalDateTime getHoraSalida() { return horaSalida; }
    public double getImportePagado() { return importePagado; }

    @Override
    public String toString() {
        return String.format("Ticket - %s [%s] - Entrada: %s - Importe: $%.2f",
                vehiculo.getTipo(), vehiculo.getPatente(), horaEntrada, importePagado);
    }
}