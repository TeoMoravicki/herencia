import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuManager menu = new MenuManager(scanner);
        menu.mostrarMenuPrincipal();
        scanner.close();
    }
}