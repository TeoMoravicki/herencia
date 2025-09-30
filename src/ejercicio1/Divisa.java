package ejercicio1;

public abstract class Divisa {
    private String codigo;
    private String simbolo;

    public Divisa(String codigo, String simbolo) {
        this.codigo = codigo;
        this.simbolo = simbolo;
    }

    public abstract double convertir(double monto, Divisa destino);

    // Getters
    public String getCodigo() { return codigo; }
    public String getSimbolo() { return simbolo; }
}