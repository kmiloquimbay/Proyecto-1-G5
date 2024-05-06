package galeria.inventarioYpiezas;


public abstract class Pieza {
    private String titulo;
    private String autor;
    private int anioCreacion;
    private String lugarCreacion;
    private String fechaDevolucion;
    private boolean disponibleVentaValorFijo;
    private boolean bloqueada;
    private int precioFijo;
    public abstract String getTipoPieza();

    public Pieza(String titulo, int anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada) {
        this.titulo = titulo;
        this.anioCreacion = anioCreacion;
        this.lugarCreacion = lugarCreacion;
        this.fechaDevolucion = fechaDevolucion;
        this.disponibleVentaValorFijo = disponibleVentaValorFijo;
        this.bloqueada = bloqueada;
        this.precioFijo = 0;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public String getLugarCreacion() {
        return lugarCreacion;
    }

    public void setLugarCreacion(String lugarCreacion) {
        this.lugarCreacion = lugarCreacion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDisponibleVentaValorFijo() {
        return disponibleVentaValorFijo;
    }

    public void setDisponibleVentaValorFijo(boolean disponibleVentaValorFijo) {
        this.disponibleVentaValorFijo = disponibleVentaValorFijo;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }

    public int getPrecioFijo() {
        return precioFijo;
    }
    public void setPrecioFijo(int precio) {
        this.precioFijo=precio;
    }
}
