package interfaz;




import javax.swing.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import galeria.inventarioYpiezas.Pieza;

public class vistaGeneral extends JPanel {
    
    private VentanaPrincipal pric;
    String[] columnNames = {"Título", "Autor", "Año", "Tipo"};
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);


    public vistaGeneral(VentanaPrincipal ventanaPrincipal) {

        pric = ventanaPrincipal;
        setLayout(new BorderLayout());
        
        // Add example data to the table model
        actualizar(null);
        
        
    }

    public void actualizar(Collection<Pieza> piezas){

        tableModel = new DefaultTableModel(columnNames, 0);
        if(piezas != null){
            for (Pieza pieza : piezas) {
                String titulo = pieza.getTitulo();
                String autores = pieza.getAutor();
                String anio = Integer.toString(pieza.getAnioCreacion());
                String tipo = pieza.getTipoPieza();
                tableModel.addRow(new Object[]{titulo, autores, anio, tipo});
            }
        }
        // Create the table with the table model
        JTable table = new JTable(tableModel);
        
        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add the scroll pane to the panel
        add(scrollPane);

        this.pric.actualizarVistaGeneral(this);


    }
}