package ejercicio4;

public class DescuentoLleva2Paga1 implements EstrategiaDescuento {

    @Override
    public double aplicarDescuento(double total, int cantidadItems) {
        // Esta estrategia no se aplica al total general, sino que debe manejarse
        // a nivel de ítems específicos. Por simplicidad, mostraremos el concepto.
        return total; // En una implementación real, esto sería más complejo
    }

    @Override
    public String getDescripcion() {
        return "Promoción: Lleva 2, paga 1 (aplicable a productos seleccionados)";
    }
}