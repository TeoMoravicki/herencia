package ejercicio2;

public class Moto extends Vehiculo {
    public Moto(String patente) {
        super(patente);
    }

    @Override
    public double getTarifaPorHora() {
        return 30.0; // $30 por hora para motos
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}