package ejercicio3;

public abstract class Audio {
    protected String titulo;
    protected String artista;
    protected double duracion; // en minutos
    protected EstadoReproduccion estado;

    public Audio(String titulo, String artista, double duracion) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracion = duracion;
        this.estado = EstadoReproduccion.DETENIDO;
    }

    // Métodos abstractos que deben implementar las subclases
    public abstract String getDetalles();

    // Métodos concretos para control de reproducción
    public void play() {
        if (estado == EstadoReproduccion.REPRODUCIENDO) {
            throw new IllegalStateException("Ya se está reproduciendo este audio");
        }
        estado = EstadoReproduccion.REPRODUCIENDO;
    }

    public void pause() {
        if (estado != EstadoReproduccion.REPRODUCIENDO) {
            throw new IllegalStateException("No se puede pausar si no se está reproduciendo");
        }
        estado = EstadoReproduccion.PAUSADO;
    }

    public void stop() {
        estado = EstadoReproduccion.DETENIDO;
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public double getDuracion() { return duracion; }
    public EstadoReproduccion getEstado() { return estado; }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f min) [%s]",
                artista, titulo, duracion, estado);
    }
}