package galeria.inventarioYpiezas;

import java.util.LinkedList;
import java.util.List;

public abstract class Pieza {
    private String titulo;
    private List<Autor> autores;
    private int anioCreacion;
    private String lugarCreacion;
    private String fechaDevolucion;
    private boolean disponibleVentaValorFijo;
    private boolean bloqueada;
    private int precioFijo;
    public abstract String getTipoPieza();

    public Pieza(String titulo, int anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada) {
        this.titulo = titulo;
        this.autores = new LinkedList<Autor>( );
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

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
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

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public int getPrecioFijo() {
        return precioFijo;
    }
}
