package consola;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;
import usuarios.Comprador;


public class ConsolaComprador {
    
    public static void main(Galeria galeria, Comprador comprador) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            System.out.println("----- MENÚ DEL COMPRADOR -----");
            System.out.println("1. Ver catálogo de productos");
            System.out.println("2. Realizar una compra");
            System.out.println("3. Ver mi historial de compras");
            System.out.println("4. Ver historial de una pieza");
            System.out.println("5. Ver historial de un artista");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            Collection<Compra> compras=galeria.getCompras().values();
            System.out.println(compras.size());
            for (Compra compra : compras) {
                System.out.println("1");
                System.out.println(compra.getTituloPieza());
            }


            switch (opcion) {
                case 1:
                    verCatalogo(galeria);
                    break;
                case 2:
                    realizarCompra(galeria, comprador);
                    break;
                case 3:
                    verHistorialCompras(comprador);
                    break;
                case 4:
                    verHistorialPieza(galeria);
                    break;
                case 5:
                    verHistorialArtista(galeria);
                    break;
                case 6:
                    System.out.println("Saliendo del menú del comprador...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
            }
            
            System.out.println();
        } while (opcion != 4);
        
        scanner.close();
    }

    public static void verCatalogo(Galeria galeria){
        List<Pieza> piezasDisponibleVenta = galeria.getInventario().getPiezasDisponibleVenta();
        for (Pieza pieza: piezasDisponibleVenta) {
            System.out.println(pieza.getTipoPieza() + " - " + pieza.getTitulo() + " - " + pieza.getPrecioFijo());
        }
    }

    public static void realizarCompra(Galeria galeria, Comprador comprador){
        System.out.println("Ingrese el titulo de la pieza que desea comprar: ");
        Scanner scanner = new Scanner(System.in);
        String titulo = scanner.nextLine();
        scanner.close();
        Pieza pieza = galeria.getInventario().buscarPiezaEnDisponibles(titulo);
        if (pieza != null){
            String answer = comprador.realizarCompraFija(pieza);
            System.out.println(answer);
        } else {
            System.out.println("La pieza no se encuentra disponible para la venta.");
        }
    }

    public static void verHistorialCompras(Comprador comprador){
        comprador.verHistorialCompras();
    }

    public static void verHistorialPieza(Galeria galeria){
        System.out.println("Ingrese el título de la pieza: ");
        Scanner scanner = new Scanner(System.in);
        String titulo = scanner.nextLine();
        scanner.close();
        galeria.verHistorialPieza(titulo);
    }

    public static void verHistorialArtista(Galeria galeria){
        System.out.println("Ingrese el nombre del artista: ");
        Scanner scanner = new Scanner(System.in);
        String nombreArtista = scanner.nextLine();
        scanner.close();
        galeria.verHistorialArtista(nombreArtista);
    }

    

}
