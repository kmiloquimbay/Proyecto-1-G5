package consola;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Oferta;
import galeria.compraYsubasta.Subasta;
import galeria.inventarioYpiezas.Pieza;
import usuarios.Cajero;
import usuarios.Comprador;
import usuarios.Empleado;
import usuarios.OperadorSubasta;


public class ConsolaEmpleado {
    public static void main(Galeria galeria, Empleado empleado) {
        if (empleado instanceof Cajero) {
            menuCajero(galeria, (Cajero) empleado);
        } else if (empleado instanceof OperadorSubasta) {
            menuOperadorSubasta(galeria, (OperadorSubasta) empleado);
        } else {
            System.out.println("Error: Invalid employee type");
        }
    }

    public static void menuCajero(Galeria galeria, Cajero cajero) {

        int option = 0;

        do {
            System.out.println("Menu Cajero");
            System.out.println("1. Registrar pago");
            System.out.println("2. Entregar pieza");
            System.out.println("3. Salir");
            option = Integer.parseInt(GaleriaConsole.input("Ingrese una opcion: "));

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
        } while (option != 3) ;
        
    }

    public static void menuOperadorSubasta(Galeria galeria, OperadorSubasta operadorSubasta) {
        
        int option = 0;
        do {
        
            System.out.println("Menu Operador de Subasta");
            System.out.println("1. Terminar subasta");
            System.out.println("2. Recibir y registrar oferta");
            System.out.println("3. Evaluar oferta");
            System.out.println("0. Salir");
            option = Integer.parseInt(GaleriaConsole.input("Ingrese una opcion: "));

            switch (option) {
                case 1:
                    terminarSubasta(galeria, operadorSubasta);
                    break;
                case 2:
                    recibirRegistrarOferta(galeria, operadorSubasta);
                    break;
                case 3:
                    evaluarOferta(galeria, operadorSubasta);
                    break;
                case 0:
                    System.out.println("Saliendo del menú Operador de Subasta...");
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;

            }
        }
        while (option != 0) ;
        
    }

    public static void registrarPago(Galeria galeria, Cajero cajero){
        
        String idCompra = GaleriaConsole.input("Ingrese el ID de la compra: ");
        String idPieza = GaleriaConsole.input("Ingrese el nombre de la pieza: ");
        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Compra compra = galeria.encontrarCompra(idCompra);
        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (compra != null && pieza != null && comprador != null) {
            cajero.registrarPago(compra, pieza, idComprador);
        }
        else {
            System.out.println("Error: Invalid purchase, piece or buyer ID");
        }

        
    } 

    public static void entregarPieza(Galeria galeria, Cajero cajero){
        
        String idPieza = GaleriaConsole.input("Ingrese el nombre de la pieza: ");
        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (pieza != null && comprador != null) {
            cajero.entregarPieza(pieza, idComprador);
        }
        else {
            System.out.println("Error: Invalid piece or buyer ID");
        }

        
        
    }

    public static void terminarSubasta(Galeria galeria, OperadorSubasta operadorSubasta){
    
        String idSubasta = GaleriaConsole.input("Ingrese el ID de la subasta: ");

        operadorSubasta.terminarSubasta(idSubasta);
        
    }

    public static void recibirRegistrarOferta(Galeria galeria, OperadorSubasta operadorSubasta){
    
        String idSubasta = GaleriaConsole.input("Ingrese el ID de la subasta: ");
        String idOferta = GaleriaConsole.input("Ingrese el ID de la oferta:");

        Subasta subasta = galeria.encontrarSubasta(idSubasta);
        Oferta oferta = subasta.encontrarOferta(idOferta);
        
        operadorSubasta.recibirRegistrarOferta(oferta, idSubasta);
        

    }

    public static void evaluarOferta(Galeria galeria, OperadorSubasta operadorSubasta){
        
        String idSubasta = GaleriaConsole.input("Ingrese el ID de la subasta:");
        String idOferta = GaleriaConsole.input("Ingrese el ID de la oferta:");

        Subasta subasta = galeria.encontrarSubasta(idSubasta);
        if (subasta == null) {
            System.out.println("Error: Invalid auction ID");
        }
        else {
            Oferta oferta = subasta.encontrarOferta(idOferta);
            if (oferta == null) {
                System.out.println("Error: Invalid offer ID");
            }
            else {
                operadorSubasta.evaluarOferta(oferta, idSubasta);

            }

        }
        
    }    






}
