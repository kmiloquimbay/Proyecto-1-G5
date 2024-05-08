package consola;
import usuarios.AdministradorGaleria;
import usuarios.Comprador;
import persistencia.PersistenciaGaleria;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import galeria.Galeria;
import galeria.inventarioYpiezas.Pieza;

public class ConsolaAdministrador {
	private AdministradorGaleria admin;


	public ConsolaAdministrador() throws IOException {
		Galeria galeriaConsola = PersistenciaGaleria.cargarGaleria();
		this.admin = new AdministradorGaleria("fabio24", "1226745", "Admin",galeriaConsola, "562901");
		menuAdministradorGaleria();
	}

	public static void menuAdministradorGaleria() {
        Scanner scanner = new Scanner(System.in);
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
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarIngresoPieza();
                    break;
                case 2:
                    confirmarVenta();
                    break;
                case 3:
                    devolucionPieza();
                    break;
                case 4:
                    verificarComprador();
                    break;
                case 5:
                    aumentarLimite();
                    break;
                case 6:
                    verificarSeriedadOferta();
                    break;
                case 7:
                    bloquearPieza();
                    break;
                case 8:
                    desbloquearPieza();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarIngresoPieza() {
        admin.registrarIngresoPieza(pinturaAgregar);
        System.out.println("Se agrego la pieza con la siguiente info a la bodega de la galeria:");
        System.out.println("Título: "+pinturaAgregar.getTitulo());
        System.out.println("Año Creación: "+pinturaAgregar.getAnioCreacion());
        System.out.println("Lugar Creación: "+pinturaAgregar.getLugarCreacion());
        
    }

    private static void confirmarVenta() {
        
        admin.confirmarVenta(compra1,foto1,"547293");
        System.out.println("Se confirmo la venta de la pieza "+foto1.getTitulo()+" por un precio de "+compra1.getValorPagado());
    }

    private static void devolucionPieza() {
        
        System.out.println("El propietario tenia "+ propietario.getMisPiezasActuales().size()+" piezas.");
        admin.devolucionPieza(video1,"547902");
        List<Pieza> piezasActuales=propietario.getMisPiezasActuales();
        System.out.println("Se elimino la pieza: "+video1.getTitulo()+ " y al propietario le quedaron: "+ piezasActuales.size()+" piezas.");
        
            
        }
    

    private static void verificarComprador() {
        
        
        System.out.println("El resultado de la verificación de la existencia del comprador con id 547293 fue: ");
        System.out.println(admin.verificarComprador("547293"));
        
    }

    private static void aumentarLimite() {
       
        System.out.println("El límite anterior del comprador 547293 es: "+comprador.getLimiteCompras());
        admin.aumentarLimite("547293", 200000);
        System.out.println("El nuevo límite del comprador 547293 es: "+comprador.getLimiteCompras());
        
    }



    private static void verificarSeriedadOferta() {
        
        Comprador comprador=galeriaConsola.getControladorUsuarios().obtenerComprador("547293");
        System.out.println("El limite de compras del comprador 547293 es "+comprador.getLimiteCompras());
        System.out.println("La oferta es de 20000");
        System.out.println(admin.verificarSeriedadOferta("547293", 20000));
    }

    private static void bloquearPieza() {
        admin.bloquearPieza("La niña y el buitre");
        System.out.println("Se bloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+foto1.getTitulo());
        System.out.println("Esta bloqueada: "+foto1.isBloqueada());
        
    }

    private static void desbloquearPieza() {
        admin.desbloquearPieza("La Flor");
        System.out.println("Se desbloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+pintura1.getTitulo());
        System.out.println("Esta bloqueada: "+pintura1.isBloqueada());
    }

	
	
	
	
	
		public static void main(String[] args) throws IOException {
			new ConsolaAdministrador();
		}
}
