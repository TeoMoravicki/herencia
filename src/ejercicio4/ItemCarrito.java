package ejercicio4;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        double subtotal = producto.getPrecio() * cantidad;

        // Aplicar promoción 2x1 si corresponde
        if (producto.isAplicaPromocion2x1() && cantidad >= 2) {
            int gruposDeDos = cantidad / 2;
            int sobrantes = cantidad % 2;
            subtotal = (gruposDeDos * producto.getPrecio()) + (sobrantes * producto.getPrecio());
        }

        return subtotal;
    }

    public String getDetallePromocion() {
        if (producto.isAplicaPromocion2x1() && cantidad >= 2) {
            int gruposDeDos = cantidad / 2;
            int sobrantes = cantidad % 2;
            double ahorro = (producto.getPrecio() * cantidad) - getSubtotal();
            return String.format(" (2x1 aplicado - Ahorro: $%.2f)", ahorro);
        }
        return "";
    }

    // Getters
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
}