package galeria.usuarios;

import java.util.Collection;
import java.util.List;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;

public class AdministradorGaleria extends Empleado{
    public Galeria galeria;
    public AdministradorGaleria(String login, String password, String rol, Galeria galeria, String id){
        super(login, password, rol,id);
        this.galeria=galeria;
    }
    public void registrarIngresoPieza(Pieza pieza){
        if(this.galeria.getInventario().getPiezasEnExhibicion().contains(pieza)){
            this.galeria.getInventario().getPiezasEnExhibicion().remove(pieza);
            this.galeria.getInventario().getPiezasEnBodega().add(pieza);
        }
        else if(this.galeria.getInventario().getPiezasPasadas().contains(pieza)){
            this.galeria.getInventario().getPiezasPasadas().remove(pieza);
            this.galeria.getInventario().getPiezasEnBodega().add(pieza);
        }
    }
    public String confirmarVenta(Compra compra, Pieza pieza, String idComprador){
        // Confirma una venta
        String nombre = pieza.getTitulo();
        bloquearPieza(nombre);
        if (compra.verificarVentaValorFijo(pieza , compra.getValorPagado()) && verificarComprador(idComprador) && this.galeria.getControladorUsuarios().obtenerComprador(idComprador).getLimiteCompras() >= compra.getValorPagado()){
            this.galeria.getInventario().getPiezasDisponibleVenta().remove(pieza);
            this.galeria.getInventario().getPiezasPasadas().add(pieza);
            //agregar la pieza a misCompras del comprador 
            this.galeria.getControladorUsuarios().obtenerComprador(idComprador).agregarCompra(compra);
            //remover de piezasDisponiblesVenta
            this.galeria.getInventario().getPiezasDisponibleVenta().remove(pieza);
            //agregar a piezasPasadas
            this.galeria.getInventario().getPiezasPasadas().add(pieza);
            //agregar a misPiezasActuales de priopetario
            if (this.galeria.getControladorUsuarios().obtenerPropietario(idComprador) != null){
                this.galeria.getControladorUsuarios().obtenerPropietario(idComprador).getMisPiezasActuales().add(pieza);
            }
            else{
                Comprador comprador = this.galeria.getControladorUsuarios().obtenerComprador(idComprador);
                pieza.setPrecioFijo(compra.getValorPagado());
                Propietario propietario=new Propietario(comprador.getLogin(),comprador.getPassword(),comprador.getNombre(),comprador.getTelefono(), idComprador);
                galeria.getControladorUsuarios().agregarPropietario(propietario);
                propietario.agregarPieza(pieza);
            }
            return "Venta confirmada";
        }
        else{
            desbloquearPieza(nombre);
            return "Venta no confirmada";
        }
    }
    
    public void devolucionPieza(Pieza pieza, String idPropietario){
        // Realiza una devolución de una pieza
        
            this.galeria.getInventario().getPiezasPasadas().remove(pieza);
            this.galeria.getInventario().getPiezasEnBodega().add(pieza);
            String nombre = pieza.getTitulo();
            desbloquearPieza(nombre);
            //remover de misPiezasActuales de propietario
            this.galeria.getControladorUsuarios().obtenerPropietario(idPropietario).getMisPiezasActuales().remove(pieza);
        

    }

    public void aumentarLimite(String id, int aumento){
         Comprador comprador = this.galeria.getControladorUsuarios().obtenerComprador(id);
         if (comprador != null && comprador.getLimiteCompras() + aumento >= 0)
            comprador.setLimiteCompras(comprador.getLimiteCompras() + aumento);
    }
    public boolean verificarComprador(String id){
        if ( this.galeria.getControladorUsuarios().obtenerComprador(id) == null ) 
            return false;
        return true;
    }
    public String verificarSeriedadOferta(String id, int valorApagar){
        // Verifica la seriedad de una oferta
        boolean x = verificarComprador(id);
        if (x == false)
            return "El comprador no está registrado";
        else{
            Comprador comprador = this.galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador.getLimiteCompras() < valorApagar)
                return "El comprador no tiene suficiente crédito";
            else
                return "Oferta seria, pues la oferta del comprador "+id+" no supera su límite de compras";
            }
        }
    public void bloquearPieza(String titulo){
        this.galeria.getInventario().bloquearPieza(titulo);
    }
    public void desbloquearPieza(String titulo){
        this.galeria.getInventario().desbloquearPieza(titulo);
    }
    
    public void historiaComprador(String id){
        Collection<Compra> listacompras= galeria.getCompras().values();
        Comprador comprador = this.galeria.getControladorUsuarios().obtenerComprador(id);
        Propietario propietario = this.galeria.getControladorUsuarios().obtenerPropietario(id);
        int valorColección=0;

        if (comprador != null && propietario != null){
           System.out.println("\n--- EL HISTORIAL DEL COMPRADOR CON ID "+id+" ---\n");
           
           int numC=0;
           int numP=0;
           List<Pieza> piezas=propietario.getMisPiezasActuales();
           
           System.out.println("Las piezas compradas por el comprador son: ");
            for (Compra compra : listacompras) {

                if(compra.getIdComprador().equals(id)){
                    numC+=1;
                    String titulo=compra.getTituloPieza();
                    System.out.println("Fehca de compra: "+compra.getFecha()+" | Pieza Comprada: "+titulo );
                    
                }
            }
            if(numC==0){
                System.out.println("El comprador no tiene compras registradas");
            }
            
            System.out.println("\nLas piezas de las que es dueño el comprador son:");
            for (Pieza pieza : piezas) {
                numP+=1;
                valorColección+=pieza.getPrecioFijo();
                String titulo=pieza.getTitulo();
                System.out.println("Titulo de la pieza: "+titulo);
            
            }
            if(numP==0){
                System.out.println("El comprador no tiene piezas a su nombre");
            }


            System.out.println("\nEl valor total de la colección del comprador con id "+id+" teniendo en cuenta el valor de lo que posee es: "+valorColección);

        }

        else{
            System.out.println("No existe comprador con el id: "+id); 
        }
   }

}
