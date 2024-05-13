package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

import org.json.JSONArray;
import org.json.JSONObject;
import galeria.inventarioYpiezas.*;


public class PersistenciaInventario {
    
    public static void guardarInventario(Inventario inventario) throws FileNotFoundException {
    	
        JSONObject inventarioJson = new JSONObject();

        JSONArray piezasEnBodega = new JSONArray();
        JSONArray piezasEnExhibicion = new JSONArray();
        JSONArray piezasPasadas = new JSONArray();
        JSONArray piezasDisponibleVenta = new JSONArray();

        for (Pieza pieza : inventario.getPiezasEnBodega()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasEnBodega.put(piezaJson);
        }

        for (Pieza pieza : inventario.getPiezasEnExhibicion()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasEnExhibicion.put(piezaJson);
        }

        for (Pieza pieza : inventario.getPiezasPasadas()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasPasadas.put(piezaJson);
        }

        for (Pieza pieza: inventario.getPiezasDisponibleVenta()) {
            JSONObject piezaJson = guardarPieza(pieza);
            piezasDisponibleVenta.put(piezaJson);
        }

        inventarioJson.put("piezasEnBodega", piezasEnBodega);
        inventarioJson.put("piezasEnExhibicion", piezasEnExhibicion);
        inventarioJson.put("piezasPasadas", piezasPasadas);
        inventarioJson.put("piezasDisponibleVenta", piezasDisponibleVenta);
        


        PrintWriter pw = new PrintWriter("inventario.json");
        pw.write(inventarioJson.toString());
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
        piezaJson.put("autor", pieza.getAutor());
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


    public static void cargarInventario(Inventario inventario) throws IOException{
        String jsonCompleto = new String(Files.readAllBytes(new File("inventario.json").toPath()));
        JSONObject inventarioJson = new JSONObject(jsonCompleto);

        JSONArray piezasEnBodega = inventarioJson.getJSONArray("piezasEnBodega");
        JSONArray piezasEnExhibicion = inventarioJson.getJSONArray("piezasEnExhibicion");
        JSONArray piezasPasadas = inventarioJson.getJSONArray("piezasPasadas");
        JSONArray piezasDisponibleVenta = inventarioJson.getJSONArray("piezasDisponibleVenta");

        for (Object piezaJson : piezasEnBodega) {
            Pieza pieza = cargarPieza((JSONObject) piezaJson);
            inventario.guardarEnBodega(pieza);
        }

        for (Object piezaJson : piezasEnExhibicion) {
            Pieza pieza = cargarPieza((JSONObject) piezaJson);
            inventario.pasarAExhibicion(pieza);
        }  

        for (Object piezaJson : piezasPasadas) {
            Pieza pieza = cargarPieza((JSONObject) piezaJson);
            inventario.pasarAPasadas(pieza);
        }

        for (Object piezaJson : piezasDisponibleVenta) {
            Pieza pieza = cargarPieza((JSONObject) piezaJson);
            inventario.ponerEnDisponibles(pieza);
        }
    }

    public static Pieza cargarPieza(JSONObject piezaJson){

        Pieza rta = null;

        String titulo = piezaJson.getString("titulo");
        int anioCreacion = piezaJson.getInt("anioCreacion");
        String lugarCreacion = piezaJson.getString("lugarCreacion");
        String fechaDevolucion = piezaJson.getString("fechaDevolucion");
        boolean disponibleVentaValorFijo = piezaJson.getBoolean("disponibleVentaValorFijo");
        boolean bloqueada = piezaJson.getBoolean("bloqueada");
        int precioFijo = piezaJson.getInt("precioFijo");
        String autor = piezaJson.getString("autor");
        String tipoPieza = piezaJson.getString("tipoPieza");

        if (tipoPieza.equals("Pintura")) {
            int alto = piezaJson.getInt("alto");
            int ancho = piezaJson.getInt("ancho");
            String tecnica = piezaJson.getString("tecnica");
            rta = new Pintura(titulo, autor, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo, ancho, alto, tecnica);
        } else if (tipoPieza.equals("Fotografia")) {
            String resolucion = piezaJson.getString("resolucion");
            String tamanio = piezaJson.getString("tamanio");
            rta = new Fotografia(titulo, autor, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, precioFijo, bloqueada, resolucion, tamanio);
        } else if (tipoPieza.equals("Video")) {
            String duracion = piezaJson.getString("duracion");
            String tamanio = piezaJson.getString("tamanio");
            rta = new Video(titulo, autor, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo, duracion, tamanio);
        } else if (tipoPieza.equals("Escultura")) {
            int alto = piezaJson.getInt("alto");
            int ancho = piezaJson.getInt("ancho");
            String material = piezaJson.getString("material");
            int peso = piezaJson.getInt("peso");
            int profundidad = piezaJson.getInt("profundidad");
            boolean necesitaElectricidad = piezaJson.getBoolean("necesitaElectricidad");
            rta = new Escultura(titulo, autor, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada, precioFijo, alto, ancho, profundidad, peso, material, necesitaElectricidad);
        }
        return rta;
    }
    


}
