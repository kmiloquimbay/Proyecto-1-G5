package galeria.compraYsubasta;
import java.util.Date;
import java.text.SimpleDateFormat;
import galeria.inventarioYpiezas.Pieza;

public class Compra {
    private String id;
    private int valorPagado;
    private String tipoPago;
    private String tituloPieza;
    private String fecha;
    private String idComprador;

    public Compra(String id, int valorPagado, String tipoPago, String pieza,String idComprador) {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaString = formatoFecha.format(fechaActual);
        this.id = id;
        this.valorPagado = valorPagado;
        this.tipoPago = tipoPago;
        this.tituloPieza = pieza;
        this.idComprador=idComprador;
        fecha=fechaString;
    }
    public Compra(String id, int valorPagado, String tipoPago, String pieza,String idComprador,String fecha) {
        this.id = id;
        this.valorPagado = valorPagado;
        this.tipoPago = tipoPago;
        this.tituloPieza = pieza;
        this.idComprador=idComprador;
        this.fecha=fecha;
    }

    public String getId() {
        return id;
    }

    public int getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(int valorPagado) {
        this.valorPagado = valorPagado;
    }

    public String getTipoPago() {
        return tipoPago;
    }
    public String getTituloPieza() {
        return tituloPieza;
    }


    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getIdComprador() {
        return idComprador;
    }
    public void setIdComprador(String id) {
        idComprador=id;
    }

    public String getFecha() {
        return fecha;
    }
    public boolean verificarVentaValorFijo(Pieza pieza, int valorPagado){
        if( valorPagado >= pieza.getPrecioFijo() && pieza.isDisponibleVentaValorFijo() && !pieza.isBloqueada()){
            return true;
        }
        return false;
        
        
    }
    

}
