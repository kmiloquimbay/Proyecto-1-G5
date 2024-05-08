package galeria.inventarioYpiezas;

public class Video extends Pieza{
    private String duracion;
    private String tamanio;

    public Video(String titulo, String autor, int anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada, int precioFijo, String duracion, String tamanio) {
        super(titulo, autor, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo);
        this.duracion = duracion;
        this.tamanio = tamanio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getTipoPieza(){
        return "Video";
    }
}
