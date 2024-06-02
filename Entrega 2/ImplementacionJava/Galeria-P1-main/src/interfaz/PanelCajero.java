package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class PanelCajero extends JPanel implements ActionListener{

    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;

    private VentanaPrincipal principal;

    public PanelCajero(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;

        setLayout(new GridLayout(3, 1));
        UIManager.put("Button.background", Color.decode("#E89275"));

        btnReq1 = new JButton("Registrar pago");
        btnReq1.setActionCommand("REQ1");
        btnReq1.addActionListener(this);
        add(btnReq1);

        btnReq2 = new JButton("Entregar pieza");
        btnReq2.setActionCommand("REQ2");
        btnReq2.addActionListener(this);
        add(btnReq2);

        btnReq3 = new JButton("Pago con tarjeta de crédito");
        btnReq3.setActionCommand("REQ3");
        btnReq3.addActionListener(this);
        add(btnReq3);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();

        if(comando.equals("REQ1")){
            // Registrar pago
        }else if(comando.equals("REQ2")){
            // Entregar pieza
        }else if(comando.equals("REQ3")){
            // Pago con tarjeta de crédito
        }
    }

}
