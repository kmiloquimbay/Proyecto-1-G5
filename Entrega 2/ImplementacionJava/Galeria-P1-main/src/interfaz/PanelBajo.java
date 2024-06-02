package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ui.ApplicationFrame;

public class PanelBajo extends JPanel implements ActionListener{
    private JButton btnUs;
    private JButton btnSave;
    private JButton btnLogin;
    private JButton btnVentas;

    private VentanaPrincipal principal;

    public PanelBajo(VentanaPrincipal ventanaPrincipal) {

        principal = ventanaPrincipal;

        setLayout(new GridLayout(1, 3));

        btnUs = new JButton("Sobre nosotros");
        btnUs.addActionListener(this);
        btnUs.setActionCommand("US");
        add(btnUs);

        btnVentas = new JButton("Ventas");
        btnVentas.addActionListener(this);
        btnVentas.setActionCommand("VENTAS");
        add(btnVentas);

        btnSave = new JButton("Guardar");
        btnSave.addActionListener(this);
        btnSave.setActionCommand("SAVE");
        add(btnSave);

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(this);
        btnLogin.setActionCommand("LOGIN");
        add(btnLogin);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        if(comando.equals("US")){
            JOptionPane.showMessageDialog(null, "Somos el grupo 5 para DPOO :)");
        } else if(comando.equals("SAVE")){
            principal.salvarGaleria();
        } else if(comando.equals("LOGIN")){
            principal.login();
        } else if(comando.equals("VENTAS")){
            Map<String, Integer> ventasDiarias = principal.getGaleria().contarVentasDiarias();
            VisualizarVentas frame = new VisualizarVentas("Ventas Diarias", ventasDiarias);
            frame.pack();
            frame.setVisible(true);
        }
    }
}
