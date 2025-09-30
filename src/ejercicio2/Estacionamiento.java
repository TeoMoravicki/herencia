package ejercicio2;

import java.util.ArrayList;
import java.util.List;

public class Estacionamiento {
    private List<Vehiculo> vehiculosEstacionados;
    private List<Ticket> ticketsHistoricos;
    private double recaudacionTotal;

    public Estacionamiento() {
        this.vehiculosEstacionados = new ArrayList<>();
        this.ticketsHistoricos = new ArrayList<>();
        this.recaudacionTotal = 0.0;
    }

    public Ticket estacionar(Vehiculo vehiculo) {
        // Verificar si el vehículo ya está estacionado
        for (Vehiculo v : vehiculosEstacionados) {
            if (v.getPatente().equals(vehiculo.getPatente())) {
                throw new IllegalArgumentException("El vehículo ya está estacionado");
            }
        }

        vehiculosEstacionados.add(vehiculo);
        Ticket ticket = new Ticket(vehiculo);
        return ticket;
    }

    public double retirar(String patente) {
        Vehiculo vehiculoARetirar = null;

        // Buscar el vehículo
        for (Vehiculo vehiculo : vehiculosEstacionados) {
            if (vehiculo.getPatente().equals(patente)) {
                vehiculoARetirar = vehiculo;
                break;
            }
        }

        if (vehiculoARetirar == null) {
            throw new IllegalArgumentException("No se encontró el vehículo con patente: " + patente);
        }

        // Crear y finalizar ticket
        Ticket ticket = new Ticket(vehiculoARetirar);
        ticket.finalizarTicket();

        // Actualizar recaudación
        recaudacionTotal += ticket.getImportePagado();

        // Mover a histórico y quitar de estacionados
        ticketsHistoricos.add(ticket);
        vehiculosEstacionados.remove(vehiculoARetirar);

        return ticket.getImportePagado();
    }

    public void generarReporteDiario() {
        System.out.println("\n=== REPORTE DIARIO DEL ESTACIONAMIENTO ===");
        System.out.printf("Recaudación total: $%.2f\n", recaudacionTotal);
        System.out.printf("Vehículos actualmente estacionados: %d\n", vehiculosEstacionados.size());
        System.out.printf("Tickets históricos: %d\n", ticketsHistoricos.size());

        System.out.println("\nVehículos estacionados:");
        if (vehiculosEstacionados.isEmpty()) {
            System.out.println("  No hay vehículos estacionados");
        } else {
            for (Vehiculo vehiculo : vehiculosEstacionados) {
                System.out.printf("  %s [%s] - Entrada: %s\n",
                        vehiculo.getTipo(), vehiculo.getPatente(), vehiculo.getHoraEntrada());
            }
        }

        System.out.println("\nÚltimos 5 tickets:");
        int count = Math.min(5, ticketsHistoricos.size());
        for (int i = ticketsHistoricos.size() - 1; i >= ticketsHistoricos.size() - count && i >= 0; i--) {
            System.out.println("  " + ticketsHistoricos.get(i));
        }
    }

    // Getters encapsulados
    public List<Vehiculo> getVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados); // Copia para encapsulación
    }

    public List<Ticket> getTicketsHistoricos() {
        return new ArrayList<>(ticketsHistoricos); // Copia para encapsulación
    }

    public double getRecaudacionTotal() {
        return recaudacionTotal;
    }
}