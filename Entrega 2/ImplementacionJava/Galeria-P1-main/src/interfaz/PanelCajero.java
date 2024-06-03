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
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;
import galeria.usuarios.Cajero;

public class PanelCajero extends JPanel implements ActionListener{

    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;
    private Cajero cajero;
    private VentanaPrincipal principal;
    private Galeria galeria = principal.getGaleria();

    public PanelCajero(VentanaPrincipal ventanaPrincipal, Cajero cajero) {
        principal = ventanaPrincipal;
        this.cajero = cajero;

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

     public void registrarPagoInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField valorPagadoField = new JTextField();
        JTextField tipoPagoField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("Valor pagado:"));
        panel.add(valorPagadoField);
        panel.add(new JLabel("Tipo de pago:"));
        panel.add(tipoPagoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Registrar pago", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idComprador = idCompradorField.getText();
            int valorPagado = Integer.parseInt(valorPagadoField.getText());
            String tipoPago = tipoPagoField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);

            Compra compra = new Compra(tituloPieza, valorPagado, tipoPago, tituloPieza, idComprador);
            String mensaje = cajero.registrarPago(compra, pieza, idComprador);
            JOptionPane.showMessageDialog(this, mensaje, "Pago", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void entregarPiezaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idCompradorField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Entregar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idComprador = idCompradorField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);
            String mensaje = cajero.entregarPieza(pieza, idComprador);
            JOptionPane.showMessageDialog(this, mensaje, "Entrega", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void pagoConTarjetaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JTextField pasarelaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField numeroTarjetaField = new JTextField();
        JTextField montoField = new JTextField();
        JTextField pinField = new JTextField();

        panel.add(new JLabel("Pasarela:"));
        panel.add(pasarelaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("Numero de tarjeta:"));
        panel.add(numeroTarjetaField);
        panel.add(new JLabel("Monto:"));
        panel.add(montoField);
        panel.add(new JLabel("Pin:"));
        panel.add(pinField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Pago con tarjeta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String pasarela = pasarelaField.getText();
            String idComprador = idCompradorField.getText();
            String numeroTarjeta = numeroTarjetaField.getText();
            int monto = Integer.parseInt(montoField.getText());
            String pin = pinField.getText();

            String mensaje = cajero.RealizarPagoTarjeta(pasarela, idComprador, numeroTarjeta, monto, pin);
            JOptionPane.showMessageDialog(this, mensaje, "Pago", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();

        if(comando.equals("Registrar Pago")){
            // Registrar pago
            registrarPagoInterfaz();
        }else if(comando.equals("Entregar Pieza")){
            // Entregar pieza
            entregarPiezaInterfaz();
        }else if(comando.equals("Pago con Tarjeta de Credito")){
            // Pago con tarjeta de crédito
            pagoConTarjetaInterfaz();
        }
    }

}
