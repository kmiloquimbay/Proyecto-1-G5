package Pagos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import galeria.Galeria;

public class ProcesadorPagos {
    private List<PasarelaPago> pasarelas;
    String pasarelasFilePath="DocsPagos/Pasarelas.txt";

    public ProcesadorPagos() {
        pasarelas = new ArrayList<>();
        cargarPasarelas();
    }

    private void cargarPasarelas( ) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pasarelasFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Class<?> clazz = Class.forName(line);
                PasarelaPago pasarela = (PasarelaPago) clazz.getDeclaredConstructor().newInstance();
                pasarelas.add(pasarela);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String procesarPagoTraza(String pasarela, String idComprador, String numeroTarjeta, int monto, String pin, Galeria galeria) {
        for (PasarelaPago tipo : pasarelas) {
            if (tipo.getClass().getSimpleName().equals(pasarela)) {
                return ""+tipo.RealizarTraza(idComprador, numeroTarjeta, monto, pin, galeria);
            }
            
        }
        return "Pasarela no encontrada";
        
    }

}
