package interfaz;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import galeria.Galeria;
import persistencia.PersistenciaGaleria;

public class VentanaPrincipal extends JFrame {

    private PanelBajo panelOpciones;
    private Galeria galeria;

    private PanelCentral pCentral;

    public VentanaPrincipal() {

        
        setTitle("Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Entrega 2/ImplementacionJava/Galeria-P1-main/images/painting.png");
        setIconImage(icon.getImage());
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        panelOpciones = new PanelBajo(this);
        add(panelOpciones, BorderLayout.SOUTH);
        
        pCentral = new PanelCentral(this);
        add(pCentral, BorderLayout.CENTER);
        
    }
    
    public static void main(String[] args) {
        VentanaPrincipal princ = new VentanaPrincipal();
        princ.setVisible(true);
    }

}