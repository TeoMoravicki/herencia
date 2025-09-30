package ejercicio1;

public class Cliente {
    private String nombre;
    private String dni;
    private Cuenta cuenta;

    public Cliente(String nombre, String dni, Cuenta cuenta) {
        this.nombre = nombre;
        this.dni = dni;
        this.cuenta = cuenta;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public Cuenta getCuenta() { return cuenta; }
}