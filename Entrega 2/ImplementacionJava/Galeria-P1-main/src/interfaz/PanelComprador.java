package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import galeria.Galeria;
import galeria.inventarioYpiezas.Pieza;
import galeria.usuarios.Comprador;



public class PanelComprador extends JPanel implements ActionListener{
    private VentanaPrincipal principal;
    private JButton req1;
    private JButton req2;
    private JButton req3;
    private JButton req4;
    private JButton req5;
    private JButton req6;
    private JButton req7;
    private Galeria galeria;
    private Comprador comprador;


    public PanelComprador(VentanaPrincipal ventanaPrincipal,Comprador comprador) {

        this.comprador=comprador;
        this.principal = ventanaPrincipal;
        this.galeria = ventanaPrincipal.getGaleria();

        setLayout(new GridLayout(7, 1));
        UIManager.put("Button.background", Color.decode("#E89275"));
        req1 = new JButton("Ver catálogo");
        req1.addActionListener(this);
        req1.setActionCommand("REQ1");
        add(req1);

        req2 = new JButton("Realizar una compra");
        req2.addActionListener(this);
        req2.setActionCommand("REQ2");
        add(req2);

        req3 = new JButton("Ver mi historial de compras");
        req3.addActionListener(this);
        req3.setActionCommand("REQ3");
        add(req3);

        req4 = new JButton("Ver historial de una pieza");
        req4.addActionListener(this);
        req4.setActionCommand("REQ4");
        add(req4);

        req5 = new JButton("Ver historial de un artista");
        req5.addActionListener(this);
        req5.setActionCommand("REQ5");
        add(req5);

        req6 = new JButton("Ver mis piezas actuales");
        req6.addActionListener(this);
        req6.setActionCommand("REQ6");
        add(req6);

        req7 = new JButton("Ver mis piezas pasadas");
        req7.addActionListener(this);
        req7.setActionCommand("REQ7");
        add(req7);



    }

        public void verHistorialPiezaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historial de pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();

            galeria.verHistorialPieza(tituloPieza);
        }
    }



    
    public void verHistorialArtistaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField nombreArtistaField = new JTextField();

        panel.add(new JLabel("Nombre del artista:"));
        panel.add(nombreArtistaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historial de artista", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String nombreArtista = nombreArtistaField.getText();

            galeria.verHistorialArtista(nombreArtista);
        }
    }
    

    public void realizarCompraFijaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Realizar compra fija", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String tituloPieza = tituloPiezaField.getText();
            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);

            String mensaje = comprador.realizarCompraFija(pieza);
            JOptionPane.showMessageDialog(this, mensaje, "Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if(comando.equals("REQ1")){
            principal.verCatalogo();
        } else if(comando.equals("REQ2")){
            realizarCompraFijaInterfaz();
        } else if(comando.equals("REQ3")){
            principal.getmisComprasInterfaz();
        } else if(comando.equals("REQ4")){
            verHistorialPiezaInterfaz();
        } else if(comando.equals("REQ5")){
            verHistorialArtistaInterfaz();
        } else if(comando.equals("REQ6")){
            principal.vermisPiezasActualesInterfaz();
        } else if(comando.equals("REQ7")){
            principal.vermisPiezasPasadasInterfaz();
        }
    }
}