package ejercicio4;

public interface EstrategiaDescuento {
    double aplicarDescuento(double total, int cantidadItems);
    String getDescripcion();
}