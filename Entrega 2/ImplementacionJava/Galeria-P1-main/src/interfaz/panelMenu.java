package interfaz;

import javax.swing.JPanel;

import galeria.Galeria;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class panelMenu extends JPanel implements ActionListener{

    private Galeria galeria;

    private JButton btnLoad;
    private JButton btnSave;
    private JButton btnLogin;

    private VentanaPrincipal pric;

    public panelMenu(VentanaPrincipal ventanaPrincipal) {
        pric = ventanaPrincipal;
        setLayout(new GridLayout(1, 3));

        btnLoad = new JButton("Load");
        btnLoad.addActionListener(this);
        btnLoad.setActionCommand("LOAD");
        add(btnLoad);

        btnSave = new JButton("Save");
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
        
        String command = e.getActionCommand();
        if(command.equals("LOAD")){

            pric.cargarGaleria(galeria);

        }else if(command.equals("SAVE")){

            pric.salvarGaleria();

        }else if(command.equals("LOGIN")){

            JOptionPane.showMessageDialog(this, "Login");
        
        }
    }

}
