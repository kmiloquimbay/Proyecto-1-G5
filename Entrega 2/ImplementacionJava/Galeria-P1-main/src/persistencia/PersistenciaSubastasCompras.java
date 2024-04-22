package persistencia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import galeria.inventarioYpiezas.Pieza;
import java.io.File;



import galeria.compraYsubasta.Oferta;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Subasta;

public class PersistenciaSubastasCompras {

    public static void guardarComprasSubastas(Map<String, Subasta> subastas, Map<String, Compra> compras) {
        JSONObject obj = new JSONObject();
        JSONArray subastasArray = new JSONArray();
        JSONArray comprasArray = new JSONArray();

        for (Map.Entry<String, Subasta> entry : subastas.entrySet()) {
            JSONObject subasta = new JSONObject();
            subasta.put("id", entry.getValue().getId());
            subasta.put("valorInicial", entry.getValue().valorInicial);
            subasta.put("valorMinimo", entry.getValue().getValorMinimo());
            subasta.put("vendida", entry.getValue().esVendida());
            subasta.put("pieza", PersistenciaInventario.guardarPieza(entry.getValue().getPieza()));

            JSONArray ofertas = new JSONArray();
            for (int i = 0; i < entry.getValue().getOfertas().size(); i++) {
                JSONObject jOferta = new JSONObject();
                Oferta oferta = entry.getValue().getOfertas().get(i);

                jOferta.put("valorOferta", oferta.getValorOferta());
                jOferta.put("comprador", PersistenciaUsuarios.guardarComprador(oferta.getComprador()));

                ofertas.add(jOferta);
                
            }

            subasta.put("ofertas", ofertas);
            subastasArray.add(subasta);
        
            for (Map.Entry<String, Compra> entryCompra : compras.entrySet()) {
                JSONObject compra = new JSONObject();
                compra.put("id", entryCompra.getValue().getId());
                compra.put("valorPagado", entryCompra.getValue().getValorPagado());
                compra.put("tipoPago", entryCompra.getValue().getTipoPago());
                compra.put("pieza", PersistenciaInventario.guardarPieza(entryCompra.getValue().getPieza()));
                comprasArray.add(compra);
            }

        obj.put("subastas", subastasArray);
        obj.put("compras", comprasArray);

        PrintWriter pw = new PrintWriter("comprasSubastas.json");
        pw.write(obj.toJSONString());
        pw.close();

        }
    }

    public static Map<String, Subasta> cargarSubastas() {
        Map<String, Subasta> subastas = new HashMap<>();
        String jsonCompleto = new String(Files.readAllBytes(new File("comprasSubastas.json").toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        JSONArray subastasArray = raiz.getJSONArray("subastas");

        for (int i = 0; i < subastasArray.size(); i++) {
            JSONObject subasta = subastasArray.getJSONObject(i);
            Subasta sub = new Subasta(subasta.getString("id"), subasta.getInt("valorMinimo"), subasta.getInt("valorInicial"), PersistenciaInventario.cargarPieza(subasta.getJSONObject("pieza")));
            JSONArray ofertas = subasta.getJSONArray("ofertas");

            for (int j = 0; j < ofertas.size(); j++) {
                JSONObject oferta = ofertas.getJSONObject(j);
                sub.agregarOferta(new Oferta(oferta.getInt("valorOferta"), PersistenciaUsuarios.cargarComprador(oferta.getJSONObject("comprador"))));
            }

            subastas.put(sub.getId(), sub);
        }



        return subastas;
    }

    public static Map<String, Compra> cargarCompras() {
        Map<String, Compra> compras = new HashMap<>();
        String jsonCompleto = new String(Files.readAllBytes(new File("comprasSubastas.json").toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);

        JSONArray comprasArray = raiz.getJSONArray("compras");

        for (int i = 0; i < comprasArray.size(); i++) {
            JSONObject compra = comprasArray.getJSONObject(i);
            Compra comp = new Compra(compra.getString("id"), compra.getInt("valorPagado"), compra.getString("tipoPago"), PersistenciaInventario.cargarPieza(compra.getJSONObject("pieza")));
            compras.put(comp.getId(), comp);
        }

        return compras;
    }

}
