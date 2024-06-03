package interfaz;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Escultura;
import galeria.inventarioYpiezas.Fotografia;
import galeria.inventarioYpiezas.Pieza;
import galeria.inventarioYpiezas.Pintura;
import galeria.usuarios.Comprador;

public class PanelAdministrador extends JPanel implements ActionListener{

    private VentanaPrincipal principal;


    private JButton btnReq1;
    private JButton btnReq2;
    private JButton btnReq3;
    private JButton btnReq4;
    private JButton btnReq5;
    private JButton btnReq6;
    private JButton btnReq7;
    private JButton btnReq8;
    private JButton btnReq9;
    private JButton btnReq10;
    private JButton btnReq11;
    private Galeria galeria;

    public PanelAdministrador(VentanaPrincipal ventanaPrincipal) {
        principal = ventanaPrincipal;
        galeria = principal.getGaleria();

        setLayout(new GridLayout(11, 1));
        UIManager.put("Button.background", Color.decode("#E89275"));

        btnReq1 = new JButton("Ingresar pieza");
        btnReq1.setActionCommand("REQ1");
        btnReq1.addActionListener(this);
        add(btnReq1);

        btnReq2 = new JButton("Confirmar venta");
        btnReq2.setActionCommand("REQ2");
        btnReq2.addActionListener(this);
        add(btnReq2);

        btnReq3 = new JButton("Devolución de pieza");
        btnReq3.setActionCommand("REQ3");
        btnReq3.addActionListener(this);
        add(btnReq3);

        btnReq4 = new JButton("Verificar comprador");
        btnReq4.setActionCommand("REQ4");
        btnReq4.addActionListener(this);
        add(btnReq4);

        btnReq5 = new JButton("Aumentar limite crédito");
        btnReq5.setActionCommand("REQ5");
        btnReq5.addActionListener(this);
        add(btnReq5);

        btnReq6 = new JButton("Verificar seriedad oferta");
        btnReq6.setActionCommand("REQ6");
        btnReq6.addActionListener(this);
        add(btnReq6);

        btnReq7 = new JButton("Bloquear pieza");
        btnReq7.setActionCommand("REQ7");
        btnReq7.addActionListener(this);
        add(btnReq7);

        btnReq8 = new JButton("Desbloquear pieza");
        btnReq8.setActionCommand("REQ8");
        btnReq8.addActionListener(this);
        add(btnReq8);

        btnReq9 = new JButton("Ver historia de una pieza");
        btnReq9.setActionCommand("REQ9");
        btnReq9.addActionListener(this);
        add(btnReq9);

        btnReq10 = new JButton("Ver historia de un artista");
        btnReq10.setActionCommand("REQ10");
        btnReq10.addActionListener(this);
        add(btnReq10);

        btnReq11 = new JButton("Ver historia de un comprador");
        btnReq11.setActionCommand("REQ11");
        btnReq11.addActionListener(this);
        add(btnReq11);

        UIManager.put("Button.background", Color.decode("#E89275"));


    }

    public void ingresarPiezaInterfaz(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,10));

        JTextField tipo = new JTextField();
        JTextField tituloPiezaField = new JTextField();
        JTextField autorField = new JTextField();
        JTextField anioField = new JTextField();
        JTextField precioField = new JTextField();
        JTextField lugarField = new JTextField();
        JTextField fechaDevolucionField = new JTextField();
        JCheckBox disponibleValorFijoField = new JCheckBox();
        JCheckBox bloqueadaField = new JCheckBox();
        JTextField precioFijoField = new JTextField();
        JTextField altoField = new JTextField();
        JTextField anchoField = new JTextField();
        JTextField tecnicaField = new JTextField();
        JTextField duracionField = new JTextField();
        JTextField tamanioField = new JTextField();
        JTextField resolucionField = new JTextField();
        JTextField profundidadField = new JTextField();
        JTextField pesoField = new JTextField();
        JTextField materialesConstruccionField = new JTextField();
        JTextField necesitaElectricidadField = new JTextField();

        panel.add(new JLabel("Tipo de pieza: (Escultura, Pintura, Fotografia, Video)"));
        panel.add(tipo);
        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("Autor:"));
        panel.add(autorField);
        panel.add(new JLabel("Año de creación:"));
        panel.add(anioField);
        panel.add(new JLabel("Precio:"));
        panel.add(precioField);
        panel.add(new JLabel("Lugar de creación:"));
        panel.add(lugarField);
        panel.add(new JLabel("Fecha de devolución:"));
        panel.add(fechaDevolucionField);
        panel.add(new JLabel("Disponible para venta:"));
        panel.add(disponibleValorFijoField);
        panel.add(new JLabel("Bloqueada:"));
        panel.add(bloqueadaField);
        panel.add(new JLabel("Precio fijo:"));
        panel.add(precioFijoField);
        panel.add(new JLabel("Alto: (Opcional)"));
        panel.add(altoField);
        panel.add(new JLabel("Ancho: (Opcional)"));
        panel.add(anchoField);
        panel.add(new JLabel("Técnica: (Opcional)"));
        panel.add(tecnicaField);
        panel.add(new JLabel("Duración: (Opcional)"));
        panel.add(duracionField);
        panel.add(new JLabel("Tamaño: (Opcional)"));
        panel.add(tamanioField);
        panel.add(new JLabel("Resolución: (Opcional)"));
        panel.add(resolucionField);
        panel.add(new JLabel("Profundidad: (Opcional)"));
        panel.add(profundidadField);
        panel.add(new JLabel("Peso: (Opcional)"));
        panel.add(pesoField);
        panel.add(new JLabel("Materiales de construcción: (Opcional)"));
        panel.add(materialesConstruccionField);
        panel.add(new JLabel("Necesita electricidad: (Opcional)"));
        panel.add(necesitaElectricidadField);


        int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tipoPieza = tipo.getText();
            String tituloPieza = tituloPiezaField.getText();
            String autor = autorField.getText();
            int anio = Integer.parseInt(anioField.getText());
            int precio = Integer.parseInt(precioField.getText());
            String lugar = lugarField.getText();
            String fechaDevolucion = fechaDevolucionField.getText();
            boolean disponibleValorFijo = disponibleValorFijoField.isSelected();
            boolean bloqueada = bloqueadaField.isSelected();
            int precioFijo = Integer.parseInt(precioFijoField.getText());
            int alto = Integer.parseInt(altoField.getText());
            int ancho = Integer.parseInt(anchoField.getText());
            String tecnica = tecnicaField.getText();
            int duracion = Integer.parseInt(duracionField.getText());
            int tamanio = Integer.parseInt(tamanioField.getText());
            int resolucion = Integer.parseInt(resolucionField.getText());
            int profundidad = Integer.parseInt(profundidadField.getText());
            int peso = Integer.parseInt(pesoField.getText());
            String materiales = materialesConstruccionField.getText();
            boolean necesitaElectricidad = Boolean.parseBoolean(necesitaElectricidadField.getText());

            

            //segun el tipo (Escultura, Pintura, Fotografia, Video) se creara un objeto de ese tipo y se le pasan solo los argumentos que corresponden a ese constructor

            if (tipoPieza.equals("Pintura")){
                Pieza pintura = new Pintura(tituloPieza, autor, anio, lugar, fechaDevolucion, disponibleValorFijo, bloqueada, precioFijo, ancho, alto, tecnica);
                galeria.getAdministrador().registrarIngresoPieza(pintura);
            } else if (tipoPieza.equals("Escultura")){
                Pieza escultura = new Escultura(tituloPieza, autor, anio, lugar, fechaDevolucion, disponibleValorFijo, bloqueada, precioFijo, alto, ancho, profundidad, peso, materiales, necesitaElectricidad);
                galeria.getAdministrador().registrarIngresoPieza(escultura);
            } else if (tipoPieza.equals("Fotografia")){
                Pieza fotografia = new Fotografia(tituloPieza, autor, anio, lugar, fechaDevolucion, disponibleValorFijo, precioFijo, bloqueada, fechaDevolucion, materiales);
                galeria.getAdministrador().registrarIngresoPieza(fotografia);
            } else if (tipoPieza.equals("Video")){
                Pieza video = new Escultura(tituloPieza, autor, anio, lugar, fechaDevolucion, disponibleValorFijo, bloqueada, precioFijo, result, anio, precio, precioFijo, materiales, bloqueada);
                galeria.getAdministrador().registrarIngresoPieza(video);
            }
        }

        JOptionPane.showMessageDialog(this, "Pieza ingresada con exito", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void confirmarVentaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idCompradorField = new JTextField();
        JTextField valorPagadoField = new JTextField();
        JTextField tipoPagoField = new JTextField();
        JTextField idObraField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del comprador:"));
        panel.add(idCompradorField);
        panel.add(new JLabel("Valor pagado:"));
        panel.add(valorPagadoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Confirmar venta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idComprador = idCompradorField.getText();
            int valorPagado = Integer.parseInt(valorPagadoField.getText());
            String tipoPago = tipoPagoField.getText();
            String idObra = idObraField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);

            Compra compra = new Compra(idObra, valorPagado, tipoPago, tituloPieza, idComprador);
            String mensaje = galeria.getAdministrador().confirmarVenta(compra, pieza, idComprador);
            JOptionPane.showMessageDialog(this, mensaje, "Venta", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void devolucionPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField tituloPiezaField = new JTextField();
        JTextField idPropietarioField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);
        panel.add(new JLabel("ID del propietario:"));
        panel.add(idPropietarioField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Devolucion de pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();
            String idPropietario = idPropietarioField.getText();

            Pieza pieza = galeria.getInventario().buscarPieza(tituloPieza);
            galeria.getAdministrador().devolucionPieza(pieza, idPropietario);
            JOptionPane.showMessageDialog(this, "Devolucion realizada con exito", "Devolucion", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void aumentarLimiteInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JTextField idField = new JTextField();
        JTextField aumentoField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);
        panel.add(new JLabel("Aumento:"));
        panel.add(aumentoField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Aumentar limite", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();
            int aumento = Integer.parseInt(aumentoField.getText());

            galeria.getAdministrador().aumentarLimite(id, aumento);
            JOptionPane.showMessageDialog(this, "Limite aumentado con exito", "Aumento", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void verificarCompradorInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Verificar comprador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, "Comprador encontrado", "Comprador", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Comprador no encontrado", "Comprador", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void verificarSeriedadOfertaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del comprador para verificar su seriedad:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Verificar seriedad de oferta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, "Comprador serio", "Seriedad", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Comprador no serio", "Seriedad", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void bloquearPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Bloquear pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();

            galeria.getInventario().bloquearPieza(tituloPieza);
            JOptionPane.showMessageDialog(this, "Pieza bloqueada con exito", "Bloqueo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void desbloquearPiezaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField tituloPiezaField = new JTextField();

        panel.add(new JLabel("Titulo de la pieza:"));
        panel.add(tituloPiezaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Desbloquear pieza", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String tituloPieza = tituloPiezaField.getText();

            galeria.getInventario().desbloquearPieza(tituloPieza);
            JOptionPane.showMessageDialog(this, "Pieza desbloqueada con exito", "Desbloqueo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
    public void historiaCompradorInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del comprador:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historia del comprador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, comprador.toString(), "Historia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Comprador no encontrado", "Historia", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    public void historiaArtistaInterfaz() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));

        JTextField idField = new JTextField();

        panel.add(new JLabel("ID del artista:"));
        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Historia del artista", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {

            String id = idField.getText();

            Comprador comprador = galeria.getControladorUsuarios().obtenerComprador(id);
            if (comprador != null){
                JOptionPane.showMessageDialog(this, comprador.toString(), "Historia", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Artista no encontrado", "Historia", JOptionPane.INFORMATION_MESSAGE);
            }
        }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();

        if (comando.equals("REQ1")){
            // Ingresar Venta
            ingresarPiezaInterfaz();
        }else if(comando.equals("REQ2")){
            // Confirmar venta
            confirmarVentaInterfaz();
        }else if(comando.equals("REQ3")){
            // Devolución de pieza
            devolucionPiezaInterfaz();
        }else if(comando.equals("REQ5")){
            // Aumentar limite crédito
            aumentarLimiteInterfaz();
        }else if(comando.equals("REQ4")){
            // Verificar comprador
            verificarCompradorInterfaz();
        }else if(comando.equals("REQ6")){
            // Verificar seriedad oferta
            verificarSeriedadOfertaInterfaz();
        }else if(comando.equals("REQ7")){
            // Bloquear pieza
            bloquearPiezaInterfaz();
        }else if(comando.equals("REQ8")){
            // Desbloquear pieza
            desbloquearPiezaInterfaz();
        }else if(comando.equals("REQ11")){
            // Ver historia de un Comprador
            historiaCompradorInterfaz();
        }
        else if(comando.equals("REQ9")){
            // Ver historia de una pieza
            verHistorialPiezaInterfaz();
        }
        else if(comando.equals("REQ10")){
            // Ver historia de un artista
            historiaArtistaInterfaz();
        }
    }
}