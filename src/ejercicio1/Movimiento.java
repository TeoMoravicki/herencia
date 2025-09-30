package ejercicio1;

import java.time.LocalDateTime;

public class Movimiento {
    private String tipo;
    private double monto;
    private LocalDateTime fecha;
    private double saldoPosterior;

    public Movimiento(String tipo, double monto, double saldoPosterior) {
        this.tipo = tipo;
        this.monto = monto;
        this.saldoPosterior = saldoPosterior;
        this.fecha = LocalDateTime.now();
    }

    // Getters
    public String getTipo() { return tipo; }
    public double getMonto() { return monto; }
    public LocalDateTime getFecha() { return fecha; }
    public double getSaldoPosterior() { return saldoPosterior; }

    @Override
    public String toString() {
        return String.format("[%s] %s: %.2f - Saldo: %.2f",
                fecha, tipo, monto, saldoPosterior);
    }
}