package ejercicio3;

public class Cancion extends Audio {
    private String genero;

    public Cancion(String titulo, String artista, double duracion, String genero) {
        super(titulo, artista, duracion);
        this.genero = genero;
    }

    @Override
    public String getDetalles() {
        return String.format(" Canción: %s - %s | Género: %s | Duración: %.2f min | Estado: %s",
                artista, titulo, genero, duracion, estado);
    }

    // Getter específico
    public String getGenero() { return genero; }
}