package ejercicio1;

public class Peso extends Divisa {
    public Peso() {
        super("ARS", "$");
    }

    @Override
    public double convertir(double monto, Divisa destino) {
        if (destino instanceof Dolar) {
            return monto / 1000.0; // 1 USD = 1000 ARS (ejemplo)
        } else if (destino instanceof Peso) {
            return monto;
        }
        throw new IllegalArgumentException("Conversión no soportada");
    }
}