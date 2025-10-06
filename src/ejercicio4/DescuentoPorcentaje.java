package ejercicio4;

public class DescuentoPorcentaje implements EstrategiaDescuento {
    private double porcentaje;

    public DescuentoPorcentaje(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100");
        }
        this.porcentaje = porcentaje;
    }

    @Override
    public double aplicarDescuento(double total, int cantidadItems) {
        return total * (1 - porcentaje / 100);
    }

    @Override
    public String getDescripcion() {
        return String.format("Descuento del %.1f%%", porcentaje);
    }

    public double getPorcentaje() {
        return porcentaje;
    }
}