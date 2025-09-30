package ejercicio1;

public class Dolar extends Divisa {
    public Dolar() {
        super("USD", "US$");
    }

    @Override
    public double convertir(double monto, Divisa destino) {
        if (destino instanceof Peso) {
            return monto * 1000.0; // 1 USD = 1000 ARS (ejemplo)
        } else if (destino instanceof Dolar) {
            return monto;
        }
        throw new IllegalArgumentException("Conversión no soportada");
    }
}