package ejercicio4;

public class DescuentoMontoFijo implements EstrategiaDescuento {
    private double monto;

    public DescuentoMontoFijo(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        this.monto = monto;
    }

    @Override
    public double aplicarDescuento(double total, int cantidadItems) {
        double resultado = total - monto;
        return resultado < 0 ? 0 : resultado;
    }

    @Override
    public String getDescripcion() {
        return String.format("Descuento fijo de $%.2f", monto);
    }

    public double getMonto() {
        return monto;
    }
}