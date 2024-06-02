package galeria.compraYsubasta;

import galeria.usuarios.Comprador;

public class Oferta {
    private int valorOferta;
    private Comprador comprador;
    
    public Oferta(int valorOferta, Comprador comprador){
        this.valorOferta=valorOferta;
        this.comprador=comprador;
    }
    public int getValorOferta() {
        return valorOferta;
    }
    public Comprador getComprador() {
        return comprador;
    }
}
