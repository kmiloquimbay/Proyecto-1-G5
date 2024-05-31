package Pagos;

import galeria.Galeria;

public interface PasarelaPago {
 boolean procesarPago(String idComprador, String numeroTarjeta, int monto, String pin, Galeria galeria);
 boolean RealizarTraza(String idComprador, String numeroTarjeta, int monto, String pin, Galeria galeria);
}
