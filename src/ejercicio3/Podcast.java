package ejercicio3;

public class Podcast extends Audio {
    private int temporada;
    private int episodio;

    public Podcast(String titulo, String autor, double duracion, int temporada, int episodio) {
        super(titulo, autor, duracion);
        this.temporada = temporada;
        this.episodio = episodio;
    }

    @Override
    public String getDetalles() {
        return String.format("️ Podcast: %s - %s | Temporada %d, Episodio %d | Duración: %.2f min | Estado: %s",
                artista, titulo, temporada, episodio, duracion, estado);
    }

    // Getters específicos
    public int getTemporada() { return temporada; }
    public int getEpisodio() { return episodio; }
}