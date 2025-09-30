package ejercicio3;

import java.util.ArrayList;
import java.util.List;

public class Reproductor {
    private List<Audio> biblioteca;
    private Audio audioActual;

    public Reproductor() {
        this.biblioteca = new ArrayList<>();
        this.audioActual = null;
    }

    public void agregarAudio(Audio audio) {
        if (audio == null) {
            throw new IllegalArgumentException("El audio no puede ser nulo");
        }
        biblioteca.add(audio);
    }

    public void reproducir(int indice) {
        if (indice < 0 || indice >= biblioteca.size()) {
            throw new IllegalArgumentException("Índice no válido");
        }

        // Detener el audio actual si hay uno reproduciéndose
        if (audioActual != null && audioActual.getEstado() == EstadoReproduccion.REPRODUCIENDO) {
            audioActual.stop();
        }

        audioActual = biblioteca.get(indice);
        audioActual.play();

        System.out.println("▶️ Reproduciendo: " + audioActual.getDetalles());
    }

    public void pausar() {
        if (audioActual == null) {
            throw new IllegalStateException("No hay ningún audio reproduciéndose");
        }

        audioActual.pause();
        System.out.println("⏸️ Pausado: " + audioActual.getDetalles());
    }

    public void reanudar() {
        if (audioActual == null) {
            throw new IllegalStateException("No hay ningún audio para reanudar");
        }

        audioActual.play();
        System.out.println("▶️ Reanudando: " + audioActual.getDetalles());
    }

    public void detener() {
        if (audioActual == null) {
            throw new IllegalStateException("No hay ningún audio reproduciéndose");
        }

        audioActual.stop();
        System.out.println("⏹️ Detenido: " + audioActual.getDetalles());
        audioActual = null;
    }

    public void mostrarBiblioteca() {
        if (biblioteca.isEmpty()) {
            System.out.println("La biblioteca está vacía.");
            return;
        }

        System.out.println("\n--- BIBLIOTECA DE AUDIO ---");
        for (int i = 0; i < biblioteca.size(); i++) {
            Audio audio = biblioteca.get(i);
            String tipo = (audio instanceof Cancion) ? "Canción" : "Podcast";
            System.out.printf("%d. [%s] %s\n", i + 1, tipo, audio);
        }
    }

    public void mostrarAudioActual() {
        if (audioActual == null) {
            System.out.println("No hay ningún audio reproduciéndose actualmente.");
        } else {
            System.out.println("🎧 Reproduciendo actualmente:");
            System.out.println("   " + audioActual.getDetalles());
        }
    }

    // Getters encapsulados
    public List<Audio> getBiblioteca() {
        return new ArrayList<>(biblioteca); // Copia para encapsulación
    }

    public Audio getAudioActual() {
        return audioActual;
    }
}