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
import galeria.compraYsubasta.Oferta;
import galeria.usuarios.Comprador;
import galeria.usuarios.OperadorSubasta;

public class PanelOperador extends JPanel implements ActionListener{

    private VentanaPrincipal principal;

    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;
    private Galeria galeria = principal.getGaleria();
    private OperadorSubasta operadorSubasta;

    public PanelOperador(VentanaPrincipal ventanaPrincipal, OperadorSubasta operadorSubasta) {
        principal = ventanaPrincipal;
        this.operadorSubasta = operadorSubasta;

        setLayout(new GridLayout(3, 1));
        UIManager.put("Button.background", Color.decode("#E89275"));

        btnReq1 = new JButton("Terminar Subasta");
        btnReq1.setActionCommand("REQ1");
        btnReq1.addActionListener(this);
        add(btnReq1);

        btnReq2 = new JButton("Recibir/registrar oferta");
        btnReq2.setActionCommand("REQ2");
        btnReq2.addActionListener(this);
        add(btnReq2);

        btnReq3 = new JButton("Evaluar oferta");
        btnReq3.setActionCommand("REQ3");
        btnReq3.addActionListener(this);
        add(btnReq3);


    }


     public void terminarSubastaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idSubastaField = new JTextField();

        panel.add(new JLabel("ID de la subasta:"));
        panel.add(idSubastaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Terminar subasta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String idSubasta = idSubastaField.getText();

            String mensaje = operadorSubasta.terminarSubasta(idSubasta);
            JOptionPane.showMessageDialog(this, mensaje, "Subasta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void recibirRegistrarOfertaInterfaz(){

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField valorOfertaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField idSubastaField = new JTextField();

        panel.add(new JLabel("Valor de la oferta:"));
        panel.add(valorOfertaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("ID de la subasta:"));
        panel.add(idSubastaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Recibir oferta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            int valorOferta = Integer.parseInt(valorOfertaField.getText());
            String idComprador = idCompradorField.getText();
            String idSubasta = idSubastaField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);
            Oferta oferta = new Oferta(valorOferta, comprador);

            String mensaje = operadorSubasta.recibirRegistrarOferta(oferta, idSubasta);
            JOptionPane.showMessageDialog(this, mensaje, "Oferta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void evaluarOfertaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idSubastaField = new JTextField();
        JTextField valorOfertaField = new JTextField();
        JTextField idCompradorField = new JTextField();

        panel.add(new JLabel("ID de la subasta:"));
        panel.add(idSubastaField);
        panel.add(new JLabel("Valor Oferta: "));
        panel.add(valorOfertaField);
        panel.add(new JLabel("ID Comprador: "));
        panel.add(idCompradorField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Evaluar oferta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (result == JOptionPane.OK_OPTION) {
            String idSubasta = idSubastaField.getText();
            int valorOferta = Integer.parseInt(valorOfertaField.getText());
            String idComprador = idCompradorField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(idComprador);
            Oferta oferta = new Oferta(valorOferta, comprador);

            boolean resultado = operadorSubasta.evaluarOferta(oferta, idSubasta);
            if (resultado){
                JOptionPane.showMessageDialog(this, "Oferta aceptada", "Oferta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Oferta rechazada", "Oferta", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();

        if(comando.equals("REQ1")){
            // Terminar Subasta
            terminarSubastaInterfaz();
        }else if(comando.equals("REQ2")){
            // Recibir y registrar oferta
            recibirRegistrarOfertaInterfaz();
        }else if(comando.equals("REQ3")){
            // Evaluar oferta
            evaluarOfertaInterfaz();
        }
    }
}