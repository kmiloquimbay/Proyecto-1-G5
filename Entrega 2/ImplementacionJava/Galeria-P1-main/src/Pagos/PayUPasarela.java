package Pagos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Random;
import galeria.Galeria;
import usuarios.Comprador;


public class PayUPasarela implements PasarelaPago
{
    
    public static String obtenerNuevoIdTransaccionPayU() {
        StringBuilder idGenerado = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int digito = random.nextInt(10); // Dígitos de 0 a 9
            idGenerado.append(digito);
        }

        return "TPU"+idGenerado.toString();
    }

    @Override
    public boolean procesarPago(String idComprador, String numeroTarjeta, int monto, String pin, Galeria galeria) {
        Comprador comprador=  galeria.getControladorUsuarios().getMapaCompradores().get(idComprador);
        int credito =comprador.getLimiteCompras();
         if (comprador!= null && credito>= monto && numeroTarjeta.length()==16 && pin.length()==3){
            return true;
         }

        return false;
    }

    @Override
    public boolean RealizarTraza(String idComprador, String numeroTarjeta, int monto, String pin, Galeria galeria) {
        String nT=obtenerNuevoIdTransaccionPayU(); 
        String resultado= "";
        String nombreComprador="";
        boolean bool=procesarPago( idComprador,  numeroTarjeta,  monto,  pin,  galeria);

       
        if (bool){
            Comprador comprador=  galeria.getControladorUsuarios().getMapaCompradores().get(idComprador);
            resultado="Aprobada";
            nombreComprador= comprador.getNombre();
        }
        else {
            resultado="Rechazada";
            nombreComprador= "N/A";
        }   
            
    
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("DocsPersistencia/DocsPagos/PayUTraza.txt" , true))) {
        writer.write("Numero de Transacción: "+nT+" | Nombre Comprador: " + nombreComprador + " | Tarjeta: " + numeroTarjeta + " | Monto: " + monto + " | Resultado: " + resultado + "\n");
    } catch (IOException e) {
        e.printStackTrace();
    }

    return bool;
   

    }
}
