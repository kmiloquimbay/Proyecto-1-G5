package galeria.inventarioYpiezas;

public class Autor {
    private String nombre;
    private boolean esAnonimo;

    public Autor(String nombre, boolean esAnonimo) {
        this.nombre = nombre;
        this.esAnonimo = esAnonimo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsAnonimo() {
        return esAnonimo;
    }

    public void setEsAnonimo(boolean esAnonimo) {
        this.esAnonimo = esAnonimo;
    }

    
}
