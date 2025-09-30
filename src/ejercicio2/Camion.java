package ejercicio2;

public class Camion extends Vehiculo {
    public Camion(String patente) {
        super(patente);
    }

    @Override
    public double getTarifaPorHora() {
        return 80.0; // $80 por hora para camiones
    }

    @Override
    public String getTipo() {
        return "Camión";
    }
}