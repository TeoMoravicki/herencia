package ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private double saldo;
    private Divisa divisa;
    private List<Movimiento> movimientos;

    public Cuenta(double saldoInicial, Divisa divisa) {
        this.saldo = saldoInicial;
        this.divisa = divisa;
        this.movimientos = new ArrayList<>();
    }

    public void depositar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        saldo += monto;
        movimientos.add(new Movimiento("DEPOSITO", monto, saldo));
    }

    public void retirar(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (monto > saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
        saldo -= monto;
        movimientos.add(new Movimiento("RETIRO", monto, saldo));
    }

    public double getSaldoEn(Divisa divisaDestino) {
        return this.divisa.convertir(saldo, divisaDestino);
    }

    // Getters
    public double getSaldo() { return saldo; }
    public Divisa getDivisa() { return divisa; }
    public List<Movimiento> getMovimientos() {
        return new ArrayList<>(movimientos); // Copia para encapsulación
    }
}