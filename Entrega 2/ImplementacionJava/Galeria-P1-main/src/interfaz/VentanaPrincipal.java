package interfaz;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import galeria.Galeria;
import persistencia.PersistenciaGaleria;

public class VentanaPrincipal extends JFrame {

    private panelMenu panelOpciones;
    private Galeria galeria;

    private vistaGeneral content;

    public VentanaPrincipal() {

        
        setTitle("Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        panelOpciones = new panelMenu(this);
        add(panelOpciones, BorderLayout.NORTH);
        
        content = new vistaGeneral(this);
        add(content);
        
        cargarGaleria(galeria);

        
    }
    
    public static void main(String[] args) {
        VentanaPrincipal princ = new VentanaPrincipal();
        princ.setVisible(true);
    }

    public void cargarGaleria(Galeria galeria) {
        try {
            this.galeria = PersistenciaGaleria.cargarGaleria();
            content.actualizar(this.galeria.getInventario().getPiezasDisponibleVenta());
            JOptionPane.showMessageDialog(null, "Galería cargada correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la galería");
        }
    }

    public void salvarGaleria() {
        try {
            PersistenciaGaleria.salvarGaleria(galeria);
            JOptionPane.showMessageDialog(null, "Galería salvada correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al salvar la galería");
        }
    }

    public void ingresarComoUsuario() {
        JOptionPane.showMessageDialog(null, "Ingresar como usuario");
    }

    public void actualizarVistaGeneral(vistaGeneral content){
        this.content = content;
    }
}