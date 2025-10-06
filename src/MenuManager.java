import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import ejercicio1.*;
import ejercicio2.*;
import ejercicio3.*;
import ejercicio4.*;

public class MenuManager {
    private Scanner scanner;
    private ATM atm;
    private Estacionamiento estacionamiento;
    private Reproductor reproductor;
    private Carrito carrito;

    public MenuManager(Scanner scanner) {
        this.scanner = scanner;
        // Inicializar con datos de ejemplo
        inicializarDatosEjemplo();
    }

    private void inicializarDatosEjemplo() {
        // Inicializar ATM (ejercicio1)
        Divisa peso = new Peso();
        Cuenta cuenta = new Cuenta(50000, peso);
        Cliente cliente = new Cliente("Juan Pérez", "12345678", cuenta);
        this.atm = new ATM(cliente);

        // Inicializar Estacionamiento (ejercicio2)
        this.estacionamiento = new Estacionamiento();

        // Inicializar Reproductor (ejercicio3)
        this.reproductor = new Reproductor();

        // Agregar algunos audios de ejemplo
        reproductor.agregarAudio(new Cancion("Bohemian Rhapsody", "Queen", 5.55, "Rock"));
        reproductor.agregarAudio(new Cancion("Blinding Lights", "The Weeknd", 3.20, "Pop"));
        reproductor.agregarAudio(new Podcast("Tecnología y Futuro", "Tech Podcast", 45.30, 2, 15));
        reproductor.agregarAudio(new Podcast("Historias Perdidas", "Radio Mysteria", 30.15, 1, 8));

        // Inicializar Carrito (ejercicio4)
        this.carrito = new Carrito();

        // Agregar algunos productos de ejemplo
        Producto laptop = new Producto("Laptop Gamer", 1500.0);
        Producto mouse = new Producto("Mouse RGB", 45.0, true); // Aplica 2x1
        Producto teclado = new Producto("Teclado Mecánico", 120.0);

        carrito.agregarItem(laptop, 1);
        carrito.agregarItem(mouse, 3); // Aplicará 2x1
        carrito.agregarItem(teclado, 1);
    }
    public void mostrarMenuPrincipal() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== SISTEMA DE EJERCICIOS ===");
            System.out.println("1. Ejercicio 1 (Cajero Automático)");
            System.out.println("2. Ejercicio 2 (Estacionamiento)");
            System.out.println("3. Ejercicio 3 (Streaming Música)");
            System.out.println("4. Ejercicio 4 (E-commerce)");
            System.out.println("5. Ejercicio 5 (Agenda Médica)");
            System.out.println("6. Ejercicio 6 (Sensores IoT)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        gestionarCajeroAutomatico();
                        break;
                    case 2:
                        gestionarEstacionamiento();
                        break;
                    case 3:
                        gestionarStreamingMusica();
                        break;
                    case 4:
                        gestionarEcommerce();
                        break;
                    case 5:
                        gestionarAgendaMedica();
                        break;
                    case 6:
                        gestionarSensoresIoT();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    private void gestionarCajeroAutomatico() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- CAJERO AUTOMÁTICO ---");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Ver extracto");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        depositar();
                        break;
                    case 2:
                        retirar();
                        break;
                    case 3:
                        consultarSaldo();
                        break;
                    case 4:
                        verExtracto();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida (número).");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void depositar() {
        System.out.print("Ingrese el monto a depositar: ");
        try {
            double monto = Double.parseDouble(scanner.nextLine());
            atm.depositar(monto);
            System.out.println("Depósito realizado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un monto válido.");
        }
    }

    private void retirar() {
        System.out.print("Ingrese el monto a retirar: ");
        try {
            double monto = Double.parseDouble(scanner.nextLine());
            atm.retirar(monto);
            System.out.println("Retiro realizado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un monto válido.");
        }
    }

    private void consultarSaldo() {
        System.out.println("Seleccione la divisa:");
        System.out.println("1. Pesos");
        System.out.println("2. Dólares");
        System.out.print("Opción: ");

        try {
            int opcionDivisa = Integer.parseInt(scanner.nextLine());
            Divisa divisa;

            if (opcionDivisa == 1) {
                divisa = new Peso();
            } else if (opcionDivisa == 2) {
                divisa = new Dolar();
            } else {
                System.out.println("Opción no válida.");
                return;
            }

            double saldo = atm.consultarSaldo(divisa);
            System.out.printf("Saldo: %.2f %s\n", saldo, divisa.getSimbolo());
        } catch (NumberFormatException e) {
            System.out.println("Ingrese una opción válida.");
        }
    }

    private void verExtracto() {
        List<Movimiento> movimientos = atm.verExtracto();
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos para mostrar.");
        } else {
            System.out.println("\n--- EXTRACTO DE MOVIMIENTOS ---");
            for (Movimiento movimiento : movimientos) {
                System.out.println(movimiento);
            }
        }
    }

    private void gestionarEstacionamiento() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- ESTACIONAMIENTO URBANO ---");
            System.out.println("1. Ingresar vehículo");
            System.out.println("2. Retirar vehículo");
            System.out.println("3. Ver vehículos estacionados");
            System.out.println("4. Reporte diario");
            System.out.println("5. Simulación (100 vehículos)");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        ingresarVehiculo();
                        break;
                    case 2:
                        retirarVehiculo();
                        break;
                    case 3:
                        verVehiculosEstacionados();
                        break;
                    case 4:
                        generarReporteDiario();
                        break;
                    case 5:
                        ejecutarSimulacion();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida (número).");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void ingresarVehiculo() {
        System.out.println("Seleccione el tipo de vehículo:");
        System.out.println("1. Auto");
        System.out.println("2. Moto");
        System.out.println("3. Camión");
        System.out.print("Opción: ");

        try {
            int tipo = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese la patente: ");
            String patente = scanner.nextLine().toUpperCase();

            Vehiculo vehiculo;
            switch (tipo) {
                case 1:
                    vehiculo = new Auto(patente);
                    break;
                case 2:
                    vehiculo = new Moto(patente);
                    break;
                case 3:
                    vehiculo = new Camion(patente);
                    break;
                default:
                    System.out.println("Tipo de vehículo no válido.");
                    return;
            }

            Ticket ticket = estacionamiento.estacionar(vehiculo);
            System.out.println("Vehículo ingresado con éxito.");
            System.out.printf("Ticket generado: %s [%s] - Entrada: %s\n",
                    vehiculo.getTipo(), patente, vehiculo.getHoraEntrada());

        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
        }
    }

    private void retirarVehiculo() {
        System.out.print("Ingrese la patente del vehículo a retirar: ");
        String patente = scanner.nextLine().toUpperCase();

        try {
            double importe = estacionamiento.retirar(patente);
            System.out.printf("Vehículo retirado con éxito. Importe a pagar: $%.2f\n", importe);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void verVehiculosEstacionados() {
        List<Vehiculo> vehiculos = estacionamiento.getVehiculosEstacionados();

        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehículos estacionados.");
        } else {
            System.out.println("\n--- VEHÍCULOS ESTACIONADOS ---");
            for (Vehiculo vehiculo : vehiculos) {
                System.out.printf("%s [%s] - Entrada: %s - Tarifa: $%.2f/hora\n",
                        vehiculo.getTipo(), vehiculo.getPatente(),
                        vehiculo.getHoraEntrada(), vehiculo.getTarifaPorHora());
            }
        }
    }

    private void generarReporteDiario() {
        estacionamiento.generarReporteDiario();
    }

    private void ejecutarSimulacion() {
        System.out.println("Ejecutando simulación de 100 vehículos...");

        Estacionamiento simulacion = new Estacionamiento();
        double recaudacionEsperada = 0;

        // Simular entrada y salida de 100 vehículos
        for (int i = 1; i <= 100; i++) {
            Vehiculo vehiculo;
            String patente = "SIM" + i;

            // Distribuir tipos de vehículos: 50% autos, 30% motos, 20% camiones
            if (i <= 50) {
                vehiculo = new Auto(patente);
                recaudacionEsperada += 50; // 1 hora por vehículo
            } else if (i <= 80) {
                vehiculo = new Moto(patente);
                recaudacionEsperada += 30; // 1 hora por vehículo
            } else {
                vehiculo = new Camion(patente);
                recaudacionEsperada += 80; // 1 hora por vehículo
            }

            simulacion.estacionar(vehiculo);
            simulacion.retirar(patente);
        }

        System.out.println("Simulación completada.");
        System.out.printf("Recaudación esperada: $%.2f\n", recaudacionEsperada);
        System.out.printf("Recaudación real: $%.2f\n", simulacion.getRecaudacionTotal());
        System.out.printf("Validación: %s\n",
                Math.abs(recaudacionEsperada - simulacion.getRecaudacionTotal()) < 0.01 ? "ÉXITO" : "FALLO");
    }

    private void gestionarStreamingMusica() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- STREAMING DE MÚSICA ---");
            System.out.println("1. Agregar canción");
            System.out.println("2. Agregar podcast");
            System.out.println("3. Mostrar biblioteca");
            System.out.println("4. Reproducir audio");
            System.out.println("5. Pausar audio");
            System.out.println("6. Reanudar audio");
            System.out.println("7. Detener audio");
            System.out.println("8. Mostrar audio actual");
            System.out.println("9. Test de polimorfismo");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarCancion();
                        break;
                    case 2:
                        agregarPodcast();
                        break;
                    case 3:
                        mostrarBiblioteca();
                        break;
                    case 4:
                        reproducirAudio();
                        break;
                    case 5:
                        pausarAudio();
                        break;
                    case 6:
                        reanudarAudio();
                        break;
                    case 7:
                        detenerAudio();
                        break;
                    case 8:
                        mostrarAudioActual();
                        break;
                    case 9:
                        testPolimorfismo();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida (número).");
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void agregarCancion() {
        System.out.print("Título de la canción: ");
        String titulo = scanner.nextLine();

        System.out.print("Artista: ");
        String artista = scanner.nextLine();

        System.out.print("Duración (minutos): ");
        double duracion = Double.parseDouble(scanner.nextLine());

        System.out.print("Género: ");
        String genero = scanner.nextLine();

        Cancion cancion = new Cancion(titulo, artista, duracion, genero);
        reproductor.agregarAudio(cancion);
        System.out.println("Canción agregada con éxito.");
    }

    private void agregarPodcast() {
        System.out.print("Título del podcast: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Duración (minutos): ");
        double duracion = Double.parseDouble(scanner.nextLine());

        System.out.print("Temporada: ");
        int temporada = Integer.parseInt(scanner.nextLine());

        System.out.print("Episodio: ");
        int episodio = Integer.parseInt(scanner.nextLine());

        Podcast podcast = new Podcast(titulo, autor, duracion, temporada, episodio);
        reproductor.agregarAudio(podcast);
        System.out.println("Podcast agregado con éxito.");
    }

    private void mostrarBiblioteca() {
        reproductor.mostrarBiblioteca();
    }

    private void reproducirAudio() {
        System.out.print("Ingrese el número del audio a reproducir: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;

        reproductor.reproducir(indice);
    }

    private void pausarAudio() {
        reproductor.pausar();
    }

    private void reanudarAudio() {
        reproductor.reanudar();
    }

    private void detenerAudio() {
        reproductor.detener();
    }

    private void mostrarAudioActual() {
        reproductor.mostrarAudioActual();
    }

    private void testPolimorfismo() {
        System.out.println("\n--- TEST DE POLIMORFISMO ---");

        // Crear una lista polimórfica
        List<Audio> audios = new ArrayList<>();
        audios.add(new Cancion("Test Song", "Test Artist", 3.0, "Test Genre"));
        audios.add(new Podcast("Test Podcast", "Test Host", 30.0, 1, 1));

        System.out.println("Reproduciendo todos los audios (polimorfismo):");
        for (Audio audio : audios) {
            try {
                audio.play();
                System.out.println("✅ " + audio.getDetalles());
                audio.stop();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        System.out.println("\nDemostración de métodos específicos:");
        for (Audio audio : audios) {
            if (audio instanceof Cancion) {
                Cancion cancion = (Cancion) audio;
                System.out.println("🎵 Canción - Género: " + cancion.getGenero());
            } else if (audio instanceof Podcast) {
                Podcast podcast = (Podcast) audio;
                System.out.println("🎙️ Podcast - Temporada: " + podcast.getTemporada() + ", Episodio: " + podcast.getEpisodio());
            }
        }
    }

    private void gestionarEcommerce() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- E-COMMERCE CON DESCUENTOS ---");
            System.out.println("1. Agregar producto al carrito");
            System.out.println("2. Aplicar descuento por porcentaje");
            System.out.println("3. Aplicar descuento por monto fijo");
            System.out.println("4. Aplicar promoción 2x1");
            System.out.println("5. Remover descuento");
            System.out.println("6. Mostrar desglose del carrito");
            System.out.println("7. Test con diferentes estrategias");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarProductoAlCarrito();
                        break;
                    case 2:
                        aplicarDescuentoPorcentaje();
                        break;
                    case 3:
                        aplicarDescuentoMontoFijo();
                        break;
                    case 4:
                        aplicarPromocion2x1();
                        break;
                    case 5:
                        removerDescuento();
                        break;
                    case 6:
                        mostrarDesgloseCarrito();
                        break;
                    case 7:
                        testEstrategiasDescuento();
                        break;
                    case 0:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese una opción válida (número).");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private void agregarProductoAlCarrito() {
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Precio del producto: ");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.print("¿Aplica promoción 2x1? (s/n): ");
        String aplica2x1 = scanner.nextLine().toLowerCase();
        boolean promocion2x1 = aplica2x1.equals("s");

        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        Producto producto = new Producto(nombre, precio, promocion2x1);
        carrito.agregarItem(producto, cantidad);
        System.out.println("Producto agregado al carrito.");
    }

    private void aplicarDescuentoPorcentaje() {
        System.out.print("Ingrese el porcentaje de descuento (0-100): ");
        double porcentaje = Double.parseDouble(scanner.nextLine());

        EstrategiaDescuento estrategia = new DescuentoPorcentaje(porcentaje);
        carrito.setEstrategiaDescuento(estrategia);
        System.out.printf("Descuento del %.1f%% aplicado.\n", porcentaje);
    }

    private void aplicarDescuentoMontoFijo() {
        System.out.print("Ingrese el monto fijo de descuento: ");
        double monto = Double.parseDouble(scanner.nextLine());

        EstrategiaDescuento estrategia = new DescuentoMontoFijo(monto);
        carrito.setEstrategiaDescuento(estrategia);
        System.out.printf("Descuento fijo de $%.2f aplicado.\n", monto);
    }

    private void aplicarPromocion2x1() {
        EstrategiaDescuento estrategia = new DescuentoLleva2Paga1();
        carrito.setEstrategiaDescuento(estrategia);
        System.out.println("Promoción 'Lleva 2, paga 1' aplicada (en productos seleccionados).");
    }

    private void removerDescuento() {
        carrito.removerEstrategiaDescuento();
        System.out.println("Descuento removido.");
    }

    private void mostrarDesgloseCarrito() {
        carrito.mostrarDesglose();
    }

    private void testEstrategiasDescuento() {
        System.out.println("\n--- TEST CON DIFERENTES ESTRATEGIAS ---");

        // Crear un carrito de prueba
        Carrito carritoTest = new Carrito();
        Producto producto1 = new Producto("Producto Test 1", 100.0);
        Producto producto2 = new Producto("Producto Test 2", 50.0, true); // Con 2x1

        carritoTest.agregarItem(producto1, 2);
        carritoTest.agregarItem(producto2, 4); // Aplicará 2x1

        System.out.println("Carrito de prueba creado con:");
        System.out.println("  2 x Producto Test 1 @ $100 c/u");
        System.out.println("  4 x Producto Test 2 @ $50 c/u (con 2x1)");

        // Probar sin descuento
        System.out.println("\n1. Sin descuento:");
        carritoTest.removerEstrategiaDescuento();
        carritoTest.mostrarDesglose();

        // Probar con descuento del 10%
        System.out.println("\n2. Con descuento del 10%:");
        carritoTest.setEstrategiaDescuento(new DescuentoPorcentaje(10));
        carritoTest.mostrarDesglose();

        // Probar con descuento de $50
        System.out.println("\n3. Con descuento de $50:");
        carritoTest.setEstrategiaDescuento(new DescuentoMontoFijo(50));
        carritoTest.mostrarDesglose();

        // Probar con promoción 2x1 (ya aplicada en los items)
        System.out.println("\n4. Con promoción 2x1:");
        carritoTest.setEstrategiaDescuento(new DescuentoLleva2Paga1());
        carritoTest.mostrarDesglose();

        System.out.println("\n✅ Test completado - Mismo carrito, diferentes totales según la estrategia");
    }


    private void gestionarAgendaMedica() {
        System.out.println("Ejercicio 5 - En construcción");
    }

    private void gestionarSensoresIoT() {
        System.out.println("Ejercicio 6 - En construcción");
    }
}