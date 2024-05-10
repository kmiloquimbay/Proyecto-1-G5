package usuarios;

import java.util.Map;

import galeria.Galeria;
import java.util.HashMap;

public class ControladorUsuarios {
    
    private  Map<String, Empleado> mapaEmpleados;
    private  Map<String, Comprador> mapaCompradores ;
    private  Map<String, Propietario> mapaPropietarios;
    private Map<String, Empleado> mapaLoginEmpleados;
    private Map<String, Comprador> mapaLoginCompradores;
    private Galeria galeria;
    
    public ControladorUsuarios(){
        
        mapaEmpleados = new HashMap<String, Empleado>();
        mapaCompradores = new HashMap<String, Comprador>();
        mapaPropietarios = new HashMap<String, Propietario>();

        mapaLoginEmpleados = new HashMap<String, Empleado>();
        mapaLoginCompradores = new HashMap<String, Comprador>();
    }

    public void setGaleria(Galeria galeria){
        this.galeria=galeria;
    }
    public OperadorSubasta crearOperador(String login, String password, String rol){
        String Nuevoid = Usuario.obtenerNuevoID();
        OperadorSubasta operador = new OperadorSubasta(login, password, rol, this.galeria,Nuevoid);
        mapaEmpleados.put(Nuevoid, operador);
        mapaLoginEmpleados.put(login, operador);
        return operador;
    }
     
    public Cajero crearCajero(String login, String password, String rol){
        String Nuevoid = Usuario.obtenerNuevoID();
        Cajero cajero = new Cajero(login, password, rol, this.galeria, Nuevoid);
        mapaEmpleados.put(Nuevoid, cajero);
        mapaLoginEmpleados.put(login, cajero);
        return cajero;
    }

    public AdministradorGaleria crearAdmin(String login, String password, String rol){
        String Nuevoid = Usuario.obtenerNuevoID();
        AdministradorGaleria administrador = new AdministradorGaleria(login, password, rol, this.galeria, Nuevoid);
        mapaEmpleados.put(Nuevoid, administrador);
        mapaLoginEmpleados.put(login, administrador);
        return administrador;
    }

    public Comprador crearComprador(String login, String password, String nombre, String telefono, int limiteCompras){
        String Nuevoid = Usuario.obtenerNuevoID();
        Comprador comprador = new Comprador(login, password, nombre, telefono, limiteCompras, this.galeria.getInventario().getPiezasDisponibleVenta(), Nuevoid);
        mapaCompradores.put(Nuevoid, comprador);
        mapaLoginCompradores.put(login, comprador);
        return comprador;
    }

    public Propietario crearPropietario(String login, String password, String nombre, String telefono){
        String Nuevoid = Usuario.obtenerNuevoID();
        Propietario propietario = new Propietario(login, password, nombre, telefono,Nuevoid);
        mapaPropietarios.put(Nuevoid, propietario);
        return propietario;
    }
    

    public Empleado obtenerEmpleado(String id){
        return mapaEmpleados.get(id);
    }

    public Comprador obtenerComprador(String id){
        return mapaCompradores.get(id);
    }

    public Propietario obtenerPropietario(String id){
        return mapaPropietarios.get(id);
    }

    public void agregarComprador (Comprador comprador){

        mapaCompradores.put(comprador.getId(), comprador);
        mapaLoginCompradores.put(comprador.getLogin(), comprador);
    }
    public void agregarPropietario (Propietario propietario){

        mapaPropietarios.put(propietario.getId(), propietario);
    }
    public void agregarEmpleado (Empleado empleado){

        mapaEmpleados.put(empleado.getId(), empleado);
        mapaLoginEmpleados.put(empleado.getLogin(), empleado);
    }

    public Map<String, Empleado> getMapaEmpleados(){
        return mapaEmpleados;
    }

    public Map<String, Comprador> getMapaCompradores(){
        return mapaCompradores;
    }

    public Map<String, Propietario> getMapaPropietarios(){
        return mapaPropietarios;
    }

    public void setMapaEmpleados(Map<String, Empleado> mapaEmpleados){
        this.mapaEmpleados = mapaEmpleados;
    }

    public void setMapaCompradores(Map<String, Comprador> mapaCompradores){
        this.mapaCompradores = mapaCompradores;
    }

    public void setMapaPropietarios(Map<String, Propietario> mapaPropietarios){
        this.mapaPropietarios = mapaPropietarios;
    }

    public Map<String, Empleado> getMapaLoginEmpleados(){
        return mapaLoginEmpleados;
    }

    public Map<String, Comprador> getMapaLoginCompradores(){
        return mapaLoginCompradores;
    }

}
