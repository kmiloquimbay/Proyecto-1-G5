package consola;
import usuarios.AdministradorGaleria;
import usuarios.Comprador;
import usuarios.Propietario;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;

public class ConsolaAdministrador {

	public static void main(Galeria galeria, AdministradorGaleria admin) {
        int opcion;
        
        do {
            System.out.println("\n--- Menú Administrador de Galería ---");
            System.out.println("1. Registrar ingreso de pieza");
            System.out.println("2. Confirmar venta");
            System.out.println("3. Devolución de pieza");
            System.out.println("4. Verificar comprador");
            System.out.println("5. Aumentar límite de crédito");
            System.out.println("6. Verificar seriedad de oferta");
            System.out.println("7. Bloquear pieza"); 
            System.out.println("8. Desbloquear pieza");
            System.out.println("9. Ver historia de una pieza");
            System.out.println("10 Ver historia de un artista");
            System.out.println("11. Ver historial de compras de un comprador");
            System.out.println("0. Volver al Menú Principal\n");
            opcion = Integer.parseInt(GaleriaConsole.input("Seleccion una opción: "));

            switch (opcion) {
                case 1:
                    registrarIngresoPieza(galeria, admin);
                    break;
                case 2:
                    confirmarVenta(galeria, admin);
                    break;
                case 3:
                    devolucionPieza(galeria, admin);
                    break;
                case 4:
                    verificarComprador(galeria, admin);
                    break;

                case 5:
                    aumentarLimite(galeria, admin);
                    break;
                case 6:
                    verificarSeriedadOferta(galeria, admin);
                    break;
                case 7:
                    bloquearPieza(galeria, admin);
                    break;
                case 8:
                    desbloquearPieza(galeria, admin);
                    break;
                case 9:
                    verHistoriaPieza(galeria);
                    break;
                case 10:
                    verHistoriaArtista(galeria);
                    break;
                case 11:
                    verHisoriaComprador(galeria, admin);
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

    }

    private static void registrarIngresoPieza(Galeria galeria, AdministradorGaleria admin) {

        String idPieza = GaleriaConsole.input("Ingrese el ID de la pieza: ");
        
        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        if (pieza != null){
            admin.registrarIngresoPieza(pieza);
            System.out.println("Se registró el ingreso de la pieza con ID: " + idPieza);
        } 
        else {
            System.out.println("No se encontró una pieza con el ID: " + idPieza);
        }
    }

    private static void confirmarVenta(Galeria galeria, AdministradorGaleria admin) {
        
        String idCompra = GaleriaConsole.input("Ingrese el ID de la compra: ");


        String idPieza = GaleriaConsole.input("Ingrese el ID de la pieza: ");

        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Compra compra = galeria.encontrarCompra(idCompra);
        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if(compra != null && pieza != null && comprador != null) {
            admin.confirmarVenta(compra, pieza, idComprador);
            System.out.println("Venta confirmada");
        } else {
            System.out.println("No se pudo confirmar la venta");
        }

    }

    private static void devolucionPieza(Galeria galeria, AdministradorGaleria admin) {


        String idPieza = GaleriaConsole.input("Ingrese el ID de la pieza: ");

        String idPropietario = GaleriaConsole.input("Ingrese el ID del propietario: ");

        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);
        Propietario propietario = galeria.getControladorUsuarios().obtenerPropietario(idPropietario);

        if(pieza != null && propietario != null) {
            admin.devolucionPieza(pieza, idPropietario);
            System.out.println("Devolución realizada");
        } else {
            System.out.println("No se pudo realizar la devolución");
        }

            
        }
    

    private static void verificarComprador(Galeria galeria, AdministradorGaleria admin) {
        
        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if(comprador != null) {
            admin.verificarComprador(idComprador);
            System.out.println("Se verificó al comprador con ID: " + idComprador);
        }
        else {
            System.out.println("No se encontró un comprador con el ID: " + idComprador);
        }
        
    }

    private static void aumentarLimite(Galeria galeria, AdministradorGaleria admin) {

        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        int aumento = Integer.parseInt(GaleriaConsole.input("Ingrese el valor del aumento: "));

        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);
        if (comprador != null) {
            admin.aumentarLimite(idComprador, aumento);
            System.out.println("El límite del comprador con ID: " + idComprador + " ha sido aumentado en " + Integer.toString(aumento));
        }
        else {
            System.out.println("No se encontró un comprador con el ID: " + idComprador);
        }

    }



    private static void verificarSeriedadOferta(Galeria galeria, AdministradorGaleria admin) {

        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        int valorOferta = Integer.parseInt(GaleriaConsole.input("Ingrese el valor de la oferta: "));
        
        Comprador comprador= galeria.getControladorUsuarios().obtenerComprador("547293");

        if (comprador != null) {
            System.out.println(admin.verificarSeriedadOferta(idComprador, valorOferta));
        }
        else {
            System.out.println("No se encontró un comprador con el ID: " + idComprador);
        }

    }

    private static void bloquearPieza(Galeria galeria, AdministradorGaleria admin) {

        String titulo = GaleriaConsole.input("Ingrese el título de la pieza: ");

        Pieza pieza = galeria.getInventario().buscarPieza(titulo);
        if (pieza != null) {
            admin.bloquearPieza(titulo);
            System.out.println("Se bloqueó la pieza con título: " + titulo);
            System.out.println("Esta bloqueada: " + pieza.isBloqueada());
        } else {
            System.out.println("No se encontró una pieza con el título: " + titulo);
        }

    }

    private static void desbloquearPieza(Galeria galeria, AdministradorGaleria admin) {

        String titulo = GaleriaConsole.input("Ingrese el título de la pieza: ");

        Pieza pieza = galeria.getInventario().buscarPieza(titulo);

        if (pieza != null) {
            admin.desbloquearPieza(titulo);
            System.out.println("Se desbloqueó la pieza con título: " + titulo);
            System.out.println("Esta bloqueada: " + pieza.isBloqueada());
        } else {
            System.out.println("No se encontró una pieza con el título: " + titulo);
        }

    }

    private static void verHistoriaPieza(Galeria galeria) {

        String idPieza = GaleriaConsole.input("Ingrese el ID de la pieza: ");

        Pieza pieza = galeria.getInventario().buscarPieza(idPieza);

        if (pieza != null) {
            galeria.verHistorialPieza(idPieza);
        } else {
            System.out.println("No se encontró una pieza con el ID: " + idPieza);
        }

    }

    private static void verHistoriaArtista(Galeria galeria) {

        String nombreArtista = GaleriaConsole.input("Ingrese el nombre del artista: ");
        
        galeria.verHistorialArtista(nombreArtista);

    }

    private static void verHisoriaComprador(Galeria galeria, AdministradorGaleria admin) {

        String idComprador = GaleriaConsole.input("Ingrese el ID del comprador: ");

        Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);

        if (comprador != null) {
            admin.historiaComprador(idComprador);
        } else {
            System.out.println("No se encontró un comprador con el ID: " + idComprador);
        }          
    }


}
