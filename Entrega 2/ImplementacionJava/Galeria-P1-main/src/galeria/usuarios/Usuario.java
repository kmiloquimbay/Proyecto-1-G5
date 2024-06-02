package galeria.usuarios;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Usuario {
    private String login;
    private String password;
    public static Set<String> idsExistentes = new HashSet<>();

    // Constructor de Usuario
    public Usuario(String login, String password) {
        this.login = login;
        this.password = password;

    }

    // Getters y setters
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Método para obtener un nuevo ID para el usuario
    public static String obtenerNuevoID() {
            StringBuilder idGenerado = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < 6; i++) {
                int digito = random.nextInt(10); // Dígitos de 0 a 9
                idGenerado.append(digito);
            }

            return idGenerado.toString();
        }

        public static String generarIDUnico(Set<String> idsExistentes) {
            String id;
            do {
                id = obtenerNuevoID();
            } while (idsExistentes.contains(id));
            return id;
    }
}
