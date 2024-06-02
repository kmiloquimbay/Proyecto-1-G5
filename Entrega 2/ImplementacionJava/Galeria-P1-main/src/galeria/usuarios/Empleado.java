package galeria.usuarios;

public class Empleado extends Usuario {
    private String rol;
    private String id;

    // Constructor de Empleado
    public Empleado(String login, String password, String rol, String id) {
        super(login, password);
        this.rol = rol;
        this.id = id;
    }

    // Getters y setters
    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getId() {
        return id;
    }
}
