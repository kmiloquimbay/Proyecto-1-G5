package galeria.usuarios;

import java.util.LinkedList;
import java.util.List;
import galeria.inventarioYpiezas.Pieza;

public class Propietario extends Cliente{
    private List<Pieza> misPiezasActuales; 
    private List<Pieza> misPiezasPasadas;

    public Propietario(String login, String password, String nombre, String telefono, String id){
        super(login, password, nombre, telefono,id);
        misPiezasActuales = new LinkedList<Pieza>( );
        misPiezasPasadas = new LinkedList<Pieza>( );

    }

    public List<Pieza> getMisPiezasActuales(){
        return misPiezasActuales;
    }

    public List<Pieza> getMisPiezasPasadas(){
        return misPiezasPasadas;
    }
    public void agregarPieza(Pieza pieza){
        misPiezasActuales.add(pieza);
    }

    public void pasarAPasadas(Pieza pieza){
        misPiezasActuales.remove(pieza);
        misPiezasPasadas.add(pieza);
    }

    public void agregarAPasadas(Pieza pieza){
        misPiezasPasadas.add(pieza);
    }





}
