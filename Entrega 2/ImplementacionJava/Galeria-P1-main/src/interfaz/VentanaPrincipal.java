package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import galeria.Galeria;
import galeria.compraYsubasta.Compra;
import galeria.inventarioYpiezas.Pieza;
import persistencia.PersistenciaGaleria;
import galeria.usuarios.AdministradorGaleria;
import galeria.usuarios.Comprador;
import galeria.usuarios.Empleado;

public class VentanaPrincipal extends JFrame {

    private Galeria galeria;
    
    private PanelSuperior pSuperior;
    private PanelCentral pCentral;
    private PanelBajo panelOpciones;
    private JPanel pDerecha;

    public VentanaPrincipal() {

        cargarGaleria();
        
        setTitle("Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Entrega 2/ImplementacionJava/Galeria-P1-main/images/painting.png");
        setIconImage(icon.getImage());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setBackground(java.awt.Color.decode("#DBCDA4"));
        
        panelOpciones = new PanelBajo(this);
        add(panelOpciones, BorderLayout.SOUTH);
        
        pCentral = new PanelCentral(this);
        add(pCentral, BorderLayout.CENTER);

        pSuperior = new PanelSuperior();
        pSuperior.setPreferredSize(new Dimension(getWidth(), 80));
        add(pSuperior, BorderLayout.NORTH);

        pDerecha = new PanelDerecha();
        add(pDerecha, BorderLayout.EAST);


        mostrarObra(0);
        
    }
    
    public static void main(String[] args) {
        VentanaPrincipal princ = new VentanaPrincipal();
        princ.setVisible(true);
    }

    public void cargarGaleria() {
        try {
            galeria = PersistenciaGaleria.cargarGaleria();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la galeria", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void salvarGaleria() {
        try {
            PersistenciaGaleria.salvarGaleria(galeria);
            JOptionPane.showMessageDialog(this, "Galeria guardada con exito", "Guardado", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar la galeria", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarObra(int pIndice){
        
        try {
            LinkedList<Pieza> listaPiezas = (LinkedList<Pieza>) pCentral.getListaPiezas();
            Pieza piezaActual = listaPiezas.get(pIndice);
            pCentral.actualizar(piezaActual);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No se pudo mostrar la obra", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void login() {
    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4, 2));

    String[] userOptions = {"Comprador", "Empleado", "Administrador"};
    JComboBox<String> userComboBox = new JComboBox<>(userOptions);
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();

    panel.add(new JLabel("Usuario:"));
    panel.add(userComboBox);
    panel.add(new JLabel("Login:"));
    panel.add(loginField);
    panel.add(new JLabel("Contraseña:"));
    panel.add(passwordField);

    int result = JOptionPane.showConfirmDialog(null, panel, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
        String selectedUser = (String) userComboBox.getSelectedItem();
        String login = loginField.getText();
        String password = new String(passwordField.getPassword());

        if (selectedUser == "Comprador"){
            Comprador comprador = galeria.getControladorUsuarios().getMapaLoginCompradores().get(login);
            if (comprador != null && comprador.getPassword().equals(password)){
                pDerecha = new PanelComprador(this);
                add(pDerecha, BorderLayout.EAST);
                List<Pieza> listaPiezas = new ArrayList<Pieza>();
                for (Compra compra: comprador.getmisCompras()){
                    Pieza pieza = galeria.getInventario().buscarPieza(compra.getTituloPieza());
                    listaPiezas.add(pieza);
                }
                pCentral.setListaPiezas(listaPiezas);
                mostrarObra(0);
                    
                revalidate();
                repaint();
            } 
            
            else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
       } else if (selectedUser == "Empleado"){
            Empleado empleado = galeria.getControladorUsuarios().getMapaLoginEmpleados().get(login);
            if (empleado != null && empleado.getPassword().equals(password)){
                if (empleado.getRol().equals("Operador")){
                    pDerecha = new PanelOperador(this);
                    add(pDerecha, BorderLayout.EAST);
                    revalidate();
                    repaint();
                } else if (empleado.getRol().equals("Cajero")){
                    pDerecha = new PanelCajero(this);
                    add(pDerecha, BorderLayout.EAST);
                    revalidate();
                    repaint();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
       } else if (selectedUser == "Administrador"){
            AdministradorGaleria admin = galeria.getAdministrador();
            if (admin != null && admin.getPassword().equals(password)){
                pDerecha = new PanelAdministrador(this);
                add(pDerecha, BorderLayout.EAST);
                revalidate();
                repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
       }

    }
    }
}