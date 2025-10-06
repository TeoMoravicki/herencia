package ejercicio4;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<ItemCarrito> items;
    private EstrategiaDescuento estrategiaDescuento;

    public Carrito() {
        this.items = new ArrayList<>();
        this.estrategiaDescuento = null;
    }

    public void agregarItem(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        items.add(new ItemCarrito(producto, cantidad));
    }

    public void setEstrategiaDescuento(EstrategiaDescuento estrategiaDescuento) {
        this.estrategiaDescuento = estrategiaDescuento;
    }

    public void removerEstrategiaDescuento() {
        this.estrategiaDescuento = null;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemCarrito item : items) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double calcularTotal() {
        double subtotal = calcularSubtotal();

        if (estrategiaDescuento != null) {
            return estrategiaDescuento.aplicarDescuento(subtotal, items.size());
        }

        return subtotal;
    }

    public void mostrarDesglose() {
        System.out.println("\n--- DESGLOSE DEL CARRITO ---");

        if (items.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        // Mostrar items
        for (ItemCarrito item : items) {
            System.out.printf("%s x%d - $%.2f c/u - Subtotal: $%.2f%s\n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.getProducto().getPrecio(),
                    item.getSubtotal(),
                    item.getDetallePromocion());
        }

        // Mostrar resumen
        double subtotal = calcularSubtotal();
        double total = calcularTotal();

        System.out.println("\n--- RESUMEN ---");
        System.out.printf("Subtotal: $%.2f\n", subtotal);

        if (estrategiaDescuento != null) {
            double descuento = subtotal - total;
            System.out.printf("Descuento aplicado (%s): -$%.2f\n",
                    estrategiaDescuento.getDescripcion(), descuento);
        }

        System.out.printf("TOTAL: $%.2f\n", total);
    }

    // Getters encapsulados
    public List<ItemCarrito> getItems() {
        return new ArrayList<>(items);
    }

    public EstrategiaDescuento getEstrategiaDescuento() {
        return estrategiaDescuento;
    }

    public int getCantidadTotalItems() {
        return items.size();
    }
}