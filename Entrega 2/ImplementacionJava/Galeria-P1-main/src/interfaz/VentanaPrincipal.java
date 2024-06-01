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

    public VentanaPrincipal() {

        setTitle("Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        panelOpciones = new panelMenu(this);
        add(panelOpciones, BorderLayout.NORTH); // Add the panelOpciones to the JFrame
        
    }
    
    public static void main(String[] args) {
        VentanaPrincipal princ = new VentanaPrincipal();
        princ.setVisible(true);
    }

    public void cargarGaleria(Galeria galeria) {
        try {
            galeria = PersistenciaGaleria.cargarGaleria();
            JOptionPane.showMessageDialog(null, "Galería cargada correctamente");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la galería");
        }
    }
}