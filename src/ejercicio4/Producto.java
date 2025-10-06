package ejercicio4;

public class Producto {
    private String nombre;
    private double precio;
    private boolean aplicaPromocion2x1;

    public Producto(String nombre, double precio) {
        this(nombre, precio, false);
    }

    public Producto(String nombre, double precio, boolean aplicaPromocion2x1) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.aplicaPromocion2x1 = aplicaPromocion2x1;
    }

    // Getters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public boolean isAplicaPromocion2x1() { return aplicaPromocion2x1; }

    @Override
    public String toString() {
        return String.format("%s - $%.2f%s", nombre, precio,
                aplicaPromocion2x1 ? " [2x1]" : "");
    }
}