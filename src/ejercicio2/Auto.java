package ejercicio2;

public class Auto extends Vehiculo {
    public Auto(String patente) {
        super(patente);
    }

    @Override
    public double getTarifaPorHora() {
        return 50.0; // $50 por hora para autos
    }

    @Override
    public String getTipo() {
        return "Auto";
    }
}