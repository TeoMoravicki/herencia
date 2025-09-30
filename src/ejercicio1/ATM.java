package ejercicio1;

import java.util.List;

public class ATM {
    private Cliente cliente;

    public ATM(Cliente cliente) {
        this.cliente = cliente;
    }

    public void depositar(double monto) {
        cliente.getCuenta().depositar(monto);
    }

    public void retirar(double monto) {
        cliente.getCuenta().retirar(monto);
    }

    public double consultarSaldo(Divisa divisa) {
        return cliente.getCuenta().getSaldoEn(divisa);
    }

    public List<Movimiento> verExtracto() {
        return cliente.getCuenta().getMovimientos();
    }

    public Cliente getCliente() {
        return cliente;
    }
}