package persistencia;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;
import galeria.inventarioYpiezas.*;

import java.util.List;
public class PersistenciaInventario {
    
    public static void guardarInventario(Inventario inventario) {

        JSONObject inventarioJson = new JSONObject();

        JSONArray piezasEnBodega = new JSONArray();
        JSONArray piezasEnExhibicion = new JSONArray();
        JSONArray piezasPasadas = new JSONArray();

        for (Pieza pieza : inventario.getPiezasEnBodega()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasEnBodega.add(piezaJson);
        }

        for (Pieza pieza : inventario.getPiezasEnExhibicion()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasEnExhibicion.add(piezaJson);
        }

        for (Pieza pieza : inventario.getPiezasPasadas()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasPasadas.add(piezaJson);
        }

        inventarioJson.put("piezasEnBodega", piezasEnBodega);
        inventarioJson.put("piezasEnExhibicion", piezasEnExhibicion);
        inventarioJson.put("piezasPasadas", piezasPasadas);


        PrintWriter pw = new PrintWriter("inventario.json");
        pw.write(inventarioJson.toJSONString());
        pw.close();

    }

    public static JSONObject guardarPieza(Pieza pieza){
        JSONObject piezaJson = new JSONObject();
        piezaJson.put("titulo", pieza.getTitulo());
        piezaJson.put("anioCreacion", pieza.getAnioCreacion());
        piezaJson.put("lugarCreacion", pieza.getLugarCreacion());
        piezaJson.put("fechaDevolucion", pieza.getFechaDevolucion());
        piezaJson.put("disponibleVentaValorFijo", pieza.isDisponibleVentaValorFijo());
        piezaJson.put("bloqueada", pieza.isBloqueada());
        piezaJson.put("precioFijo", pieza.getPrecioFijo());
        JSONArray autores = new JSONArray();
        for (Autor autor : pieza.getAutores()) {
            JSONObject autorJson = guardarAutor(autor);
            autores.add(autorJson);
        }
        piezaJson.put("tipoPieza", pieza.getTipoPieza());

        if (pieza.getTipoPieza().equals("Pintura")) {
            Pintura pintura = (Pintura) pieza;
            piezaJson.put("alto", pintura.getAlto());
            piezaJson.put("ancho", pintura.getAncho());
            piezaJson.put("tecnica", pintura.getTecnica());
        } else if (pieza.getTipoPieza().equals("Fotografia")) {
            Fotografia fotografia = (Fotografia) pieza;
            piezaJson.put("resolucion", fotografia.getResolucion());
            piezaJson.put("tamanio", fotografia.getTamanio());
        } else if (pieza.getTipoPieza().equals("Video")) {
            Video video = (Video) pieza;
            piezaJson.put("duracion", video.getDuracion());
            piezaJson.put("tamanio", video.getTamanio());
        } else if (pieza.getTipoPieza().equals("Escultura")) {
            Escultura escultura = (Escultura) pieza;
            piezaJson.put("alto", escultura.getAlto());
            piezaJson.put("ancho", escultura.getAncho());
            piezaJson.put("material", escultura.getMaterialesConstruccion());
            piezaJson.put("peso", escultura.getPeso());
            piezaJson.put("profundidad", escultura.getProfundidad());
            piezaJson.put("necesitaElectricidad", escultura.isNecesitaElectricidad());
        }


        return piezaJson;
    }

    public JSONObject guardarAutor(Autor autor){
        JSONObject autorJson = new JSONObject();
        autorJson.put("nombre", autor.getNombre());
        autorJson.put("esAnonimo", autor.isEsAnonimo());

        return autorJson;
    }

    public static void cargarInventario(Inventario inventario){
        String jsonCompleto = new String(Files.readAllBytes(new File("inventario.json").toPath()));
        JSONObject inventarioJson = new JSONObject(jsonCompleto);

        JSONArray piezasEnBodega = inventarioJson.getJSONArray("piezasEnBodega");
        JSONArray piezasEnExhibicion = inventarioJson.getJSONArray("piezasEnExhibicion");
        JSONArray piezasPasadas = inventarioJson.getJSONArray("piezasPasadas");

        for (JSONObject piezaJson : piezasEnBodega) {
            Pieza pieza = cargarPieza(piezaJson);
            inventario.guardarEnBodega(pieza);
        }

        for (JSONObject piezaJson : piezasEnExhibicion) {
            Pieza pieza = cargarPieza(piezaJson);
            inventario.pasarAExhibicion(pieza);
        }  

        for (JSONObject piezaJson : piezasPasadas) {
            Pieza pieza = cargarPieza(piezaJson);
            inventario.pasarAPasadas(pieza);
        }
    }

    public Pieza cargarPieza(JSONObject piezaJson){
        String titulo = piezaJson.getString("titulo");
        int anioCreacion = piezaJson.getInt("anioCreacion");
        String lugarCreacion = piezaJson.getString("lugarCreacion");
        String fechaDevolucion = piezaJson.getString("fechaDevolucion");
        boolean disponibleVentaValorFijo = piezaJson.getBoolean("disponibleVentaValorFijo");
        boolean bloqueada = piezaJson.getBoolean("bloqueada");
        int precioFijo = piezaJson.getInt("precioFijo");
        JSONArray autoresJson = piezaJson.getJSONArray("autores");
        List<Autor> autores = new ArrayList<>();
        for (JSONObject autorJson : autoresJson) {
            Autor autor = cargarAutor(autorJson);
            autores.add(autor);
        }
        String tipoPieza = piezaJson.getString("tipoPieza");

        if (tipoPieza.equals("Pintura")) {
            int alto = piezaJson.getInt("alto");
            int ancho = piezaJson.getInt("ancho");
            String tecnica = piezaJson.getString("tecnica");
            return new Pintura(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, alto, ancho, tecnica);
        } else if (tipoPieza.equals("Fotografia")) {
            String resolucion = piezaJson.getString("resolucion");
            String tamanio = piezaJson.getString("tamanio");
            return new Fotografia(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, resolucion, tamanio);
        } else if (tipoPieza.equals("Video")) {
            String duracion = piezaJson.getString("duracion");
            String tamanio = piezaJson.getString("tamanio");
            return new Video(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, duracion, tamanio);
        } else if (tipoPieza.equals("Escultura")) {
            int alto = piezaJson.getInt("alto");
            int ancho = piezaJson.getInt("ancho");
            String material = piezaJson.getString("material");
            int peso = piezaJson.getInt("peso");
            int profundidad = piezaJson.getInt("profundidad");
            boolean necesitaElectricidad = piezaJson.getBoolean("necesitaElectricidad");
            return new Escultura(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, alto, ancho, profundidad, peso, material, necesitaElectricidad);
        }
    }


}
