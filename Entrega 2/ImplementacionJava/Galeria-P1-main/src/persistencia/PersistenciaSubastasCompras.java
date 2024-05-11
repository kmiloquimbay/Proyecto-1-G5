package persistencia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Map;

import usuarios.Comprador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import galeria.compraYsubasta.Oferta;
import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.compraYsubasta.Subasta;

public class PersistenciaSubastasCompras {

    public static void guardarComprasSubastas(Map<String, Subasta> subastas, Map<String, Compra> compras) throws FileNotFoundException {
        JSONObject obj = new JSONObject();
        JSONArray subastasArray = new JSONArray();
        JSONArray comprasArray = new JSONArray();

        for (Map.Entry<String, Subasta> entrySubasta : subastas.entrySet()) {
            JSONObject jSubasta = guardarSubasta(entrySubasta.getValue());
            subastasArray.put(jSubasta);
        }
        
        for (Map.Entry<String, Compra> entryCompra : compras.entrySet()) {
            JSONObject compra = guardarCompra(entryCompra.getValue());
            comprasArray.put(compra);
        }

        obj.put("subastas", subastasArray);
        obj.put("compras", comprasArray);

        PrintWriter pw = new PrintWriter("comprasSubastas.json");
        pw.write(obj.toString());
        pw.close();

    }

    public static JSONObject guardarSubasta(Subasta subasta){
        JSONObject obj = new JSONObject();
        obj.put("id", subasta.getId());
        obj.put("valorInicial", subasta.getValorInicial());
        obj.put("valorMinimo", subasta.getValorMinimo());
        obj.put("pieza", subasta.getPieza().getTitulo());
        obj.put("vendida", subasta.esVendida());

        JSONArray jOfertas = new JSONArray();
        for (int i = 0; i < subasta.getOfertas().size(); i++) {
            JSONObject jOferta = new JSONObject();
            Oferta oferta = subasta.getOfertas().get(i);

            jOferta.put("valorOferta", oferta.getValorOferta());
            jOferta.put("comprador", oferta.getComprador().getId());
            jOfertas.put(jOferta);
        }

        obj.put("ofertas", jOfertas);

        return obj;
    }

    public static JSONObject guardarOferta(Oferta oferta){
        JSONObject obj = new JSONObject();
        obj.put("valorOferta", oferta.getValorOferta());
        obj.put("compradorId", oferta.getComprador().getId());
        return obj;
    }

    public static JSONObject guardarCompra(Compra compra) {
        JSONObject obj = new JSONObject();
        obj.put("id", compra.getId());
        obj.put("valorPagado", compra.getValorPagado());
        obj.put("tipoPago", compra.getTipoPago());
        obj.put("pieza", compra.getTituloPieza());
        obj.put("idComprador", compra.getIdComprador());
        return obj;
    }

    public static void cargarComprasSubastas(Galeria galeria) throws IOException {

        String jsonCompleto = new String(Files.readAllBytes(new File("comprasSubastas.json").toPath()));
        JSONObject raiz = new JSONObject(jsonCompleto);
        Map<String, Compra> compras = galeria.getCompras();
        Map<String, Subasta> subastas = galeria.getSubastas();

        JSONArray subastasArray = raiz.getJSONArray("subastas");

        for (int i = 0; i < subastasArray.length(); i++) {
            JSONObject subasta = subastasArray.getJSONObject(i);
            Subasta sub = cargarSubasta(subasta, galeria);
            subastas.put(subasta.getString("id"), sub);
            galeria.agregarSubasta(sub);


        }

        JSONArray comprasArray = raiz.getJSONArray("compras");

        for (int j = 0; j < comprasArray.length(); j++) {
            JSONObject jOferta = comprasArray.getJSONObject(j);
            Compra comp = cargarCompra(jOferta, galeria);
            compras.put(jOferta.getString("id"), comp);
            galeria.agregarCompra(comp);
            
        }
    }

    public static Subasta cargarSubasta(JSONObject subasta, Galeria galeria) {
        Subasta sub = new Subasta(subasta.getString("id"), subasta.getInt("valorMinimo"), subasta.getInt("valorInicial"), galeria.getInventario().buscarPieza(subasta.getString("pieza")));
        sub.setVendida(subasta.getBoolean("vendida"));
        JSONArray ofertas = subasta.getJSONArray("ofertas");

        for (int j = 0; j < ofertas.length(); j++) {
            JSONObject jOferta = ofertas.getJSONObject(j);
            Comprador comprador = new Comprador("", "", jOferta.getString("comprador"), "", 0, null, "");
            Oferta oferta = new Oferta(jOferta.getInt("valorOferta"), comprador);
            sub.agregarOferta(oferta);
        }

        return sub;
    }

    public static Compra cargarCompra(JSONObject compra, Galeria galeria) {
        Compra comp = new Compra(compra.getString("id"), compra.getInt("valorPagado"), compra.getString("tipoPago"), compra.getString("pieza"),compra.getString("idComprador"));
        return comp;
    }



}
