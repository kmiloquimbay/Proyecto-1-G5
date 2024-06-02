package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.List;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import galeria.Galeria;
import galeria.inventarioYpiezas.Pieza;
import persistencia.PersistenciaGaleria;

public class VentanaPrincipal extends JFrame {

    private PanelBajo panelOpciones;
    private Galeria galeria;

    private PanelCentral pCentral;
    private PanelSuperior pSuperior;

    public VentanaPrincipal() {

        cargarGaleria();
        
        setTitle("Galeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Entrega 2/ImplementacionJava/Galeria-P1-main/images/painting.png");
        setIconImage(icon.getImage());
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        panelOpciones = new PanelBajo(this);
        add(panelOpciones, BorderLayout.SOUTH);
        
        pCentral = new PanelCentral(this);
        add(pCentral, BorderLayout.CENTER);

        pSuperior = new PanelSuperior();
        pSuperior.setPreferredSize(new Dimension(getWidth(), 80));
        add(pSuperior, BorderLayout.NORTH);

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



}