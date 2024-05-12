package consola;
import java.util.List;
import galeria.Galeria;
import galeria.inventarioYpiezas.Pieza;
import usuarios.Comprador;


public class ConsolaComprador {
    
    public static void main(Galeria galeria, Comprador comprador) {

        int opcion = -1;
        
        while (opcion != 0){
            System.out.println("----- MENÚ DEL COMPRADOR -----");
            System.out.println("1. Ver catálogo de productos");
            System.out.println("2. Realizar una compra");
            System.out.println("3. Ver mi historial de compras");
            System.out.println("4. Ver historial de una pieza");
            System.out.println("5. Ver historial de un artista");
            System.out.println("0. Salir");
            opcion = Integer.parseInt(GaleriaConsole.input("Seleccione una opción: "));


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
                    
                case 0:
                    System.out.println("Saliendo del menú del comprador...");
                    break;
                    
                default:
                    System.out.println("Opción inválida. Por favor, ingrese una opción válida.");
                    break;
                    
            }
            
            System.out.println();
        } 
        
    }

    public static void verCatalogo(Galeria galeria){
        List<Pieza> piezasDisponibleVenta = galeria.getInventario().getPiezasDisponibleVenta();
        for (Pieza pieza: piezasDisponibleVenta) {
            System.out.println(pieza.getTipoPieza() + " - " + pieza.getTitulo() + " - " + pieza.getPrecioFijo());
        }
    }

    public static void realizarCompra(Galeria galeria, Comprador comprador){
        String titulo = GaleriaConsole.input("Ingrese el título de la pieza que desea comprar: ");
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
        String titulo = GaleriaConsole.input("Ingrese el título de la pieza que desea ver el historial: ");
        galeria.verHistorialPieza(titulo);
    }

    public static void verHistorialArtista(Galeria galeria){
        String nombreArtista = GaleriaConsole.input("Ingrese el nombre del artista que desea ver el historial: ");
        galeria.verHistorialArtista(nombreArtista);
    }

    

}
