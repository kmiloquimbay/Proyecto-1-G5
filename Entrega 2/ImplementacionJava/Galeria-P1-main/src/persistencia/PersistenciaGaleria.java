package persistencia;

import java.util.Map;

import galeria.Galeria;
import galeria.inventarioYpiezas.Inventario;
import usuarios.ControladorUsuarios;

public class PersistenciaGaleria {

    public static Galeria cargarGaleria() {
        Inventario inventario = new Inventario();
        PersistenciaInventario.cargarInventario(inventario);
        Galeria galeria = new Galeria(inventario, new ControladorUsuarios());
        PersistenciaUsuarios.cargarUsuarios(galeria);
        
        Map<String, galeria.compraYsubasta.Subasta> subastas = PersistenciaSubastasCompras.cargarSubastas();
        galeria.setSubastas(subastas);

        Map<String, galeria.compraYsubasta.Compra> compras = PersistenciaSubastasCompras.cargarCompras();
        galeria.setCompras(compras);

        return galeria;
        
    }

    public static void salvarGaleria(Galeria galeria) {
        // Lógica para salvar la galería
        PersistenciaInventario.guardarInventario(galeria.getInventario());
        PersistenciaUsuarios.guardarUsuarios(galeria);
        PersistenciaSubastasCompras.guardarComprasSubastas(galeria.getSubastas(), galeria.getCompras());
    }
}
