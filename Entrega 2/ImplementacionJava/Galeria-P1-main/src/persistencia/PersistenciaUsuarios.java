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

import java.util.List;
import java.util.ArrayList;

import java.io.File;

import usuarios.ControladorUsuarios;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;




public class PersistenciaUsuarios {

    public static void guardarUsuarios(Galeria galeria){
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
            empleados.add(empleado);
        }

        for (Usuario usuario : mapaCompradores.values()) {
            JSONObject comprador = guardarUsuario(usuario);
            compradores.add(comprador);
        }

        for (Usuario usuario : mapaPropietarios.values()) {
            JSONObject propietario = guardarUsuario(usuario);
            propietarios.add(propietario);
        }

        usuariosJSON.put("empleados", empleados);
        usuariosJSON.put("compradores", compradores);
        usuariosJSON.put("propietarios", propietarios);


        PrintWriter pw = new PrintWriter("usuarios.json");
        pw.write(usuariosJSON.toJSONString());
        pw.close();

    }

    public JSONObject guardarAdministrador(AdministradorGaleria administrador){
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


    public void guardarEmpleado(Empleado empleado, JSONObject usuarioJSON){

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
        JSONArray piezasDisponibles = new JSONArray();
        JSONArray misCompras = new JSONArray();


        for (Pieza pieza : comprador.getpiezasDisponibles()) {
            JSONObject piezaJSON = new JSONObject();
            piezasDisponibles.add(PersistenciaInventario.guardarPieza(pieza));
        }
        usuarioJSON.put("piezasDisponibles", piezasDisponibles);

        for (Compra compra : comprador.getmisCompras()) {
            JSONObject compraJSON = new JSONObject();
            compraJSON.put("id", compra.getId());
            compraJSON.put("valorPagado", compra.getValorPagado());
            compraJSON.put("tipoPago", compra.getTipoPago());
            compraJSON.put(PersistenciaInventario.guardarPieza(compra.getPieza()));
            misCompras.add(compraJSON);
        }
        usuarioJSON.put("misCompras", misCompras);

    }

    public static void guardarPropietario(Propietario propietario, JSONObject usuarioJSON){
        JSONArray misPiezasActuales = new JSONArray();
        JSONArray misPiezasPasadas = new JSONArray();

        for (Pieza pieza : propietario.getMisPiezasActuales()) {
            misPiezasActuales.add(PersistenciaInventario.guardarPieza(pieza));
        }
        usuarioJSON.put("misPiezasActuales", misPiezasActuales);

        for (Pieza pieza : propietario.getMisPiezasPasadas()) {
            misPiezasPasadas.add(PersistenciaInventario.guardarPieza(pieza));
        }
        usuarioJSON.put("misPiezasPasadas", misPiezasPasadas);
    }


    public static Galeria cargarUsuarios(Galeria galeria){
        String jsonCompleto = new String(Files.readAllBytes(new File("usuarios.json").toPath()));
        JSONObject raiz = new  JSONObject(jsonCompleto);
        ControladorUsuarios controladorUsuarios = new ControladorUsuarios();

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
            JSONObject jComprador = compradores.getJSONObject(i);
            Comprador comprador = cargarComprador(jComprador);
            controladorUsuarios.agregarComprador(comprador);
        }

        for (int i = 0; i < propietarios.length(); i++) {
            JSONObject jPropietario = propietarios.getJSONObject(i);
            Propietario propietario = cargarPropietario(jPropietario);
            controladorUsuarios.agregarPropietario(propietario);
        }
        
        galeria.setControladorUsuarios(controladorUsuarios);

        return galeria;
    }

    public AdministradorGaleria cargarAdministrador(JSONObject administrador, Galeria galeria){
        String login = administrador.getString("login");
        String password = administrador.getString("password");
        String rol = administrador.getString("rol");
        String id = administrador.getString("id");

        AdministradorGaleria administradorGaleria = new AdministradorGaleria(login, password, rol, galeria, id);
        return administradorGaleria;
    }

    public Empleado cargarEmpleado(JSONObject jEmpleado, Galeria galeria){
        String login = jEmpleado.getString("login");
        String password = jEmpleado.getString("password");
        String rol = jEmpleado.getString("rol");
        String id = jEmpleado.getString("id");

        if (rol.equals("operador")){
            return new OperadorSubasta(login, password, rol, galeria, id);
        }
        else if (rol.equals("cajero")){
            return new Cajero(login, password, rol, galeria, id);
        }
    }

    public Cliente cargarCliente(JSONObject jCliente){
        String login = jCliente.getString("login");
        String password = jCliente.getString("password");
        String nombre = jCliente.getString("nombre");
        String telefono = jCliente.getString("telefono");
        String id = jCliente.getString("id");

        if (jCliente.getString("tipoCliente").equals("comprador")){
            Comprador cliente = cargarComprador(jCliente, cliente, login, password, nombre, telefono, id);
            return cliente;
        }
        else if (jCliente.getString("tipoCliente").equals("propietario")){
            Propietario cliente = cargarPropietario(jCliente, cliente, login, password, nombre, telefono, id);
            return cliente;
        }
    }

    public Comprador cargarComprador(JSONObject jCliente, Comprador comprador, String login, String password, String nombre, String telefono, String id){
        int limiteCompras = jCliente.getInt("limiteCompras");
        JSONArray jPiezasDisponibles = jCliente.getJSONArray("piezasDisponibles");
        JSONArray misCompras = jCliente.getJSONArray("misCompras");

        List<Pieza> piezasDisponibles = new ArrayList<Pieza>();
        for (int i = 0; i < jPiezasDisponibles.length(); i++) {
            JSONObject pieza = jPiezasDisponibles.getJSONObject(i);
            piezasDisponibles.add(PersistenciaInventario.cargarPieza(pieza));
        }

        for (int i = 0; i < misCompras.length(); i++) {
            JSONObject compra = misCompras.getJSONObject(i);
            comprador.agregarCompra(cargarCompra(compra));
        }

        comprador = new Comprador(login, password, nombre, telefono, limiteCompras, piezasDisponibles, id);
        return comprador;

        
    }

    public Propietario cargarPropietario(JSONObject jCliente, Propietario propietario, String login, String password, String nombre, String telefono, String id){
        JSONArray jMisPiezasActuales = jCliente.getJSONArray("misPiezasActuales");
        JSONArray jMisPiezasPasadas = jCliente.getJSONArray("misPiezasPasadas");

        propietario = new Propietario(login, password, nombre, telefono, id);

        List<Pieza> misPiezasActuales = new ArrayList<Pieza>();
        for (int i = 0; i < jMisPiezasActuales.length(); i++) {
            JSONObject pieza = jMisPiezasActuales.getJSONObject(i);
            propietario.agregarPieza(PersistenciaInventario.cargarPieza(pieza));
        }

        List<Pieza> misPiezasPasadas = new ArrayList<Pieza>();
        for (int i = 0; i < jMisPiezasPasadas.length(); i++) {
            JSONObject pieza = jMisPiezasPasadas.getJSONObject(i);
            propietario.agregarAPasadas(PersistenciaInventario.cargarPieza(pieza));
        }

        return propietario;
    }

}
