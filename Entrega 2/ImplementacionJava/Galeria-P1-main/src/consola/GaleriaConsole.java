package consola;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import persistencia.PersistenciaGaleria;
import usuarios.AdministradorGaleria;
import usuarios.Comprador;
import usuarios.Empleado;
import galeria.Galeria;

public class GaleriaConsole {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int opcionMenuPrincipal;
        Galeria galeria = null;
        Map<String, Comprador> compradores = new HashMap<String, Comprador>();
        Map<String, Empleado> empleados = new HashMap<String, Empleado>();
        AdministradorGaleria administrador = null;
        
        

        // Bienvenida
        System.out.println("Bienvenido a la Galería y Casa de Subastas");

        do {
            // Menú principal
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Cargar Galería");
            System.out.println("2. Salvar Galería");
            System.out.println("3. Ingresar como Usuario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcionMenuPrincipal = scanner.nextInt();

            switch (opcionMenuPrincipal) {
                case 1:
                    galeria = PersistenciaGaleria.cargarGaleria();
                    compradores = galeria.getControladorUsuarios().getMapaLoginCompradores();
                    empleados = galeria.getControladorUsuarios().getMapaLoginEmpleados();
                    System.out.println("Se ha cargado la galeria: "+galeria.equals(galeria));
                    administrador = galeria.getAdministrador();
                case 2:
                    ConsolaUsuarios.salvar();
                    break;
                case 3:
                    ingresarComoUsuario(scanner, compradores, empleados, administrador, galeria);
                    break;
                case 0:
                    System.out.println("Gracias por utilizar la Galería y Casa de Subastas. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcionMenuPrincipal != 0);

        scanner.close();
    }

    private static void ingresarComoUsuario(Scanner scanner, Map<String, Comprador> compradores, Map<String, Empleado> empleados, AdministradorGaleria administrador, Galeria galeria) throws IOException{
        int opcionIngresarUsuario;
        
        do{
        
            System.out.println("\n--- ¿Cómo que tipo de usuario desea ingresar?---");
            System.out.println("1. Comprador");
            System.out.println("2. Empleado");
            System.out.println("3. Administrador de Galería");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione cómo desea ingresar: ");
            opcionIngresarUsuario = scanner.nextInt();
        
            switch (opcionIngresarUsuario) {
                case 1:
                    Comprador comprador = autenticarComprador(scanner, compradores);
                    if (comprador != null) {
                        ConsolaComprador.main(galeria, comprador);
                    }
                    break;
                case 2:
                    Empleado empleado = autenticarEmpleado(scanner, empleados);
                    if (empleado != null) {
                        ConsolaEmpleado.main(galeria, empleado);
                    }
                    break;
                case 3:
                    AdministradorGaleria adminGaleria = autenticarAdministrador(scanner, administrador);
                    if (adminGaleria != null) {
                        ConsolaAdministrador.main(galeria, adminGaleria);
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }

        } while (opcionIngresarUsuario != 0);


    }

    private static Comprador autenticarComprador(Scanner scanner, Map<String, Comprador> compradores){
        
        System.out.print("Ingrese su login: ");
        String login = scanner.next();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.next();
        Comprador rta = null;
        if (compradores.containsKey(login)){
            Comprador comprador = compradores.get(login);
            if (comprador.getPassword().equals(password)){
                rta = comprador;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return rta;
    }

    private static Empleado autenticarEmpleado(Scanner scanner, Map<String, Empleado> empleados){
        System.out.print("Ingrese su login: ");
        String login = scanner.next();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.next();
        Empleado rta = null;
        if (empleados.containsKey(login)){
            Empleado empleado = empleados.get(login);
            if (empleado.getPassword().equals(password)){
                rta = empleado;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return rta;
    }

    private static AdministradorGaleria autenticarAdministrador(Scanner scanner, AdministradorGaleria administrador){
        System.out.print("Ingrese su login: ");
        String login = scanner.next();
        System.out.print("Ingrese su contraseña: ");
        String password = scanner.next();
        AdministradorGaleria rta = null;
        if (administrador.getLogin().equals(login)){
            if (administrador.getPassword().equals(password)){
                rta = administrador;
            } else {
                System.out.println("Contraseña incorrecta.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
        return rta;
    }



}


