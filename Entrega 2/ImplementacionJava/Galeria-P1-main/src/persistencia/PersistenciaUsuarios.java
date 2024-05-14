package persistencia;

import galeria.Galeria;
import usuarios.AdministradorGaleria;
import usuarios.Cajero;
import usuarios.Cliente;
import usuarios.Empleado;
import usuarios.OperadorSubasta;
import usuarios.Comprador;
import usuarios.Propietario;
import usuarios.Usuario;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;
import java.util.ArrayList;
import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import usuarios.ControladorUsuarios;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;




public class PersistenciaUsuarios {

    public static void guardarUsuarios(Galeria galeria) throws FileNotFoundException{
        JSONObject usuariosJSON = new JSONObject();
        JSONArray empleados = new JSONArray();
        JSONArray compradores = new JSONArray();
        JSONArray propietarios = new JSONArray();

        JSONObject administrador = guardarAdministrador(galeria.getAdministrador());
        usuariosJSON.put("administrador", administrador);

        ControladorUsuarios controladorUsuarios = galeria.getControladorUsuarios();
        Map<String, Empleado> mapaEmpleados = controladorUsuarios.getMapaEmpleados();
        Map<String, Comprador> mapaCompradores = controladorUsuarios.getMapaCompradores();
        Map<String, Propietario> mapaPropietarios = controladorUsuarios.getMapaPropietarios();

        for (Usuario usuario : mapaEmpleados.values()) {

            JSONObject empleado = guardarUsuario(usuario);
            empleados.put(empleado);
        }

        for (Usuario usuario : mapaCompradores.values()) {
            JSONObject comprador = guardarUsuario(usuario);
            compradores.put(comprador);
        }

        for (Usuario usuario : mapaPropietarios.values()) {
            JSONObject propietario = guardarUsuario(usuario);
            propietarios.put(propietario);
        }

        usuariosJSON.put("empleados", empleados);
        usuariosJSON.put("compradores", compradores);
        usuariosJSON.put("propietarios", propietarios);


        PrintWriter pw = new PrintWriter("DocsPersistencia/usuarios.json");
        pw.write(usuariosJSON.toString());
        pw.close();

    }

    public static JSONObject guardarAdministrador(AdministradorGaleria administrador){
        JSONObject administradorJSON = new JSONObject();
        administradorJSON.put("login", administrador.getLogin());
        administradorJSON.put("password", administrador.getPassword());
        administradorJSON.put("rol", administrador.getRol());
        administradorJSON.put("id", administrador.getId());

        return administradorJSON;
    }

    public static JSONObject guardarUsuario(Usuario usuario){
        JSONObject usuarioJSON = new JSONObject();
        usuarioJSON.put("login", usuario.getLogin());
        usuarioJSON.put("password", usuario.getPassword());
        
        if (usuario instanceof Empleado){
            guardarEmpleado((Empleado) usuario, usuarioJSON);
            usuarioJSON.put("tipoUsuario", "empleado");
        }
        else if (usuario instanceof Cliente){
            guardarCliente((Cliente) usuario, usuarioJSON);
            usuarioJSON.put("tipoUsuario", "cliente");
        }

        return usuarioJSON;
    }


    public static void guardarEmpleado(Empleado empleado, JSONObject usuarioJSON){


        usuarioJSON.put("rol", empleado.getRol());
        usuarioJSON.put("id", empleado.getId());


    }

    public static void guardarCliente(Cliente cliente, JSONObject usuarioJSON){
        usuarioJSON.put("nombre", cliente.getNombre());
        usuarioJSON.put("telefono", cliente.getTelefono());
        usuarioJSON.put("id", cliente.getId());

        if (cliente instanceof Comprador){
            guardarComprador((Comprador) cliente, usuarioJSON);
            usuarioJSON.put("tipoCliente", "comprador");
        }
        else if (cliente instanceof Propietario){
            guardarPropietario((Propietario) cliente, usuarioJSON);
            usuarioJSON.put("tipoCliente", "propietario");
        }
    }

    public static void guardarComprador(Comprador comprador, JSONObject usuarioJSON){
        usuarioJSON.put("limiteCompras", comprador.getLimiteCompras());
        JSONArray misCompras = new JSONArray();

        for (Compra compra : comprador.getmisCompras()) {
            misCompras.put(compra.getId());
        }
        usuarioJSON.put("misCompras", misCompras);

    }

    public static void guardarPropietario(Propietario propietario, JSONObject usuarioJSON){
        JSONArray misPiezasActuales = new JSONArray();
        JSONArray misPiezasPasadas = new JSONArray();

        for (Pieza pieza : propietario.getMisPiezasActuales()) {
            misPiezasActuales.put(pieza.getTitulo());
        }
        usuarioJSON.put("misPiezasActuales", misPiezasActuales);

        for (Pieza pieza : propietario.getMisPiezasPasadas()) {
            misPiezasPasadas.put(pieza.getTitulo());
        }
        usuarioJSON.put("misPiezasPasadas", misPiezasPasadas);
    }


    public static Galeria cargarUsuarios(Galeria galeria) throws IOException{
        String jsonCompleto = new String(Files.readAllBytes(new File("DocsPersistencia/usuarios.json").toPath()));
        JSONObject raiz = new  JSONObject(jsonCompleto);
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();
        Collection<Compra> compras=galeria.getCompras().values();

        JSONArray empleados = raiz.getJSONArray("empleados");
        JSONArray compradores = raiz.getJSONArray("compradores");
        JSONArray propietarios = raiz.getJSONArray("propietarios");

        JSONObject administrador = raiz.getJSONObject("administrador");

        AdministradorGaleria administradorGaleria = cargarAdministrador(administrador, galeria);
        galeria.setAdministradorGaleria(administradorGaleria);

        for (int i = 0; i < empleados.length(); i++) {
            JSONObject jEmpleado = empleados.getJSONObject(i);
            Empleado empleado = cargarEmpleado(jEmpleado, galeria);
            controladorUsuarios.agregarEmpleado(empleado);

        }
        
        for (int i = 0; i < compradores.length(); i++) {
            String login = compradores.getJSONObject(i).getString("login");
            String password = compradores.getJSONObject(i).getString("password");
            String nombre = compradores.getJSONObject(i).getString("nombre");
            String telefono = compradores.getJSONObject(i).getString("telefono");
            String id = compradores.getJSONObject(i).getString("id");
            
            JSONObject jComprador = compradores.getJSONObject(i);
            Comprador comprador = cargarComprador(jComprador, login, password, nombre, telefono, id, galeria);
            for (Compra compra : compras) {
                if (compra.getIdComprador().equals(id)){
                    comprador.agregarCompra(compra);
                }
                
            }
            controladorUsuarios.agregarComprador(comprador);
        }
        
        for (int i = 0; i < propietarios.length(); i++) {
            String login = compradores.getJSONObject(i).getString("login");
            String password = compradores.getJSONObject(i).getString("password");
            String nombre = compradores.getJSONObject(i).getString("nombre");
            String telefono = compradores.getJSONObject(i).getString("telefono");
            String id = compradores.getJSONObject(i).getString("id");
            JSONObject jPropietario = propietarios.getJSONObject(i);
            Propietario propietario = cargarPropietario(jPropietario, login, password, nombre, telefono, id, galeria);
            controladorUsuarios.agregarPropietario(propietario);
        }
        
        galeria.setControladorUsuarios(controladorUsuarios);

        return galeria;
    }

    public static AdministradorGaleria cargarAdministrador(JSONObject administrador, Galeria galeria){
        String login = administrador.getString("login");
        String password = administrador.getString("password");
        String rol = administrador.getString("rol");
        String id = administrador.getString("id");

        AdministradorGaleria administradorGaleria = new AdministradorGaleria(login, password, rol, galeria, id);
        return administradorGaleria;
    }

    public static Empleado cargarEmpleado(JSONObject jEmpleado, Galeria galeria){
        String login = jEmpleado.getString("login");
        String password = jEmpleado.getString("password");
        String rol = jEmpleado.getString("rol");
        String id = jEmpleado.getString("id");

        Empleado rta = null;

        if (rol.equals("Operador")){
            rta = new OperadorSubasta(login, password, rol, galeria, id);
        }
        else if (rol.equals("Cajero")){
            rta = new Cajero(login, password, rol, galeria, id);
        } else if (rol.equals("Administrador")){
            rta = new AdministradorGaleria(login, password, rol, galeria, id);
        }
            else {
                // Handle unknown role
                throw new IllegalArgumentException("Unknown role: " + rol);
            }
        

        return rta;

    }

    public static Cliente cargarCliente(JSONObject jCliente, Galeria galeria){
        String login = jCliente.getString("login");
        String password = jCliente.getString("password");
        String nombre = jCliente.getString("nombre");
        String telefono = jCliente.getString("telefono");
        String id = jCliente.getString("id");

        Cliente cliente = null;

        if (jCliente.getString("tipoCliente").equals("comprador")){
            cliente = cargarComprador(jCliente, login, password, nombre, telefono, id, galeria);
        }
        else if (jCliente.getString("tipoCliente").equals("propietario")){
            cliente = cargarPropietario(jCliente, login, password, nombre, telefono, id, galeria);
        }
        return cliente;
    }

    public static Comprador cargarComprador(JSONObject jCliente, String login, String password, String nombre, String telefono, String id, Galeria galeria){
        int limiteCompras = jCliente.getInt("limiteCompras");
        JSONArray misCompras = jCliente.getJSONArray("misCompras");
        ArrayList<Compra> compras = new ArrayList<Compra>();
        for (int i = 0; i < misCompras.length(); i++) {
            String idCompra = misCompras.getString(i);
            Compra compra = galeria.encontrarCompra(idCompra);
            compras.add(compra);
        }
        Comprador comprador = new Comprador(login, password, nombre, telefono, limiteCompras, galeria.getInventario().getPiezasDisponibleVenta(), id);
        return comprador;

        
    }

    public static Propietario cargarPropietario(JSONObject jCliente, String login, String password, String nombre, String telefono, String id, Galeria galeria){
        JSONArray jMisPiezasActuales = jCliente.getJSONArray("misPiezasActuales");
        JSONArray jMisPiezasPasadas = jCliente.getJSONArray("misPiezasPasadas");

        Propietario propietario = new Propietario(login, password, nombre, telefono, id);

        for (int i = 0; i < jMisPiezasActuales.length(); i++) {

            String pieza = jMisPiezasActuales.getString(i);
            propietario.agregarPieza(galeria.getInventario().buscarPieza(pieza));
        }

        for (int i = 0; i < jMisPiezasPasadas.length(); i++) {
            String pieza = jMisPiezasPasadas.getString(i);
            propietario.agregarAPasadas(galeria.getInventario().buscarPieza(pieza));
        }

        return propietario;
    }

}
