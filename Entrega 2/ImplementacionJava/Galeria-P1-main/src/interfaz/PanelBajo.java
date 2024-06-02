package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelBajo extends JPanel implements ActionListener{
    private JButton btnUs;
    private JButton btnSave;
    private JButton btnLogin;

    private VentanaPrincipal principal;

    public PanelBajo(VentanaPrincipal ventanaPrincipal) {

        principal = ventanaPrincipal;

        setLayout(new GridLayout(1, 3));

        btnUs = new JButton("Sobre nosotros");
        btnUs.addActionListener(this);
        btnUs.setActionCommand("US");
        add(btnUs);

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
        }
    }
}
