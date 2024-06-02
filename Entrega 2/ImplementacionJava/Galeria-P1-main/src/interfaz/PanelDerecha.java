package interfaz;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PanelDerecha extends JPanel{

    private VentanaPrincipal principal;
    private JLabel lblStart;

    public PanelDerecha(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;
        lblStart = new JLabel("Bienvenido a la Galeria");
        lblStart.setPreferredSize(new Dimension(200, 100));
        add(lblStart);
    }
}
