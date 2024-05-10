package consola;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;
import usuarios.Cajero;
import usuarios.Comprador;
import usuarios.Empleado;
import usuarios.OperadorSubasta;

import java.util.Scanner;

public class ConsolaEmpleado {
    public static void main(Galeria galeria, Empleado empleado) {
        if (empleado instanceof Cajero) {
            menuCajero(galeria, (Cajero) empleado);
        } else if (empleado instanceof OperadorSubasta) {
            menuOperadorSubasta();
        } else {
            System.out.println("Error: Invalid employee type");
        }
    }

    public static void menuCajero(Galeria galeria, Cajero cajero) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 3) {
            System.out.println("Menu Cajero");
            System.out.println("1. Registrar pago");
            System.out.println("2. Entregar pieza");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    registrarPago(galeria, cajero);
                    break;
                case 2:
                    entregarPieza(galeria, cajero);
                    break;
                case 3:
                    System.out.println("Saliendo del menú Cajero...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        scanner.close();
    }

    public static void menuOperadorSubasta() {
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while (option != 0) {
            System.out.println("Menu Operador de Subasta");
            System.out.println("1. Terminar subasta");
            System.out.println("2. Recibir y registrar oferta");
            System.out.println("0. Evaluar oferta");
            System.out.print("Ingrese una opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    // Code for starting auction goes here
                    break;
                case 2:
                    // Code for ending auction goes here
                    break;
                case 0:
                    System.out.println("Saliendo del menú Operador de Subasta...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
        scanner.close();
    }

    public static void registrarPago(Galeria galeria, Cajero cajero){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID de la compra: ");
        String idCompra = scanner.nextLine();
        System.out.print("Ingrese el nombre de la pieza: ");
        String idPieza = scanner.nextLine();
        System.out.print("Ingrese el ID del comprador: ");
        String idComprador = scanner.nextLine();

        Compra compra = galeria.encontrarCompra(idCompra);
        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (compra != null && pieza != null && comprador != null) {
            cajero.registrarPago(compra, pieza, idComprador);
        }
        else {
            System.out.println("Error: Invalid purchase, piece or buyer ID");
        }

        scanner.close();
    } 

    public static void entregarPieza(Galeria galeria, Cajero cajero){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la pieza: ");
        String idPieza = scanner.nextLine();
        System.out.print("Ingrese el ID del comprador: ");
        String idComprador = scanner.nextLine();

        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (pieza != null && comprador != null) {
            cajero.entregarPieza(pieza, idComprador);
        }
        else {
            System.out.println("Error: Invalid piece or buyer ID");
        }

        scanner.close();
        
    }




}
