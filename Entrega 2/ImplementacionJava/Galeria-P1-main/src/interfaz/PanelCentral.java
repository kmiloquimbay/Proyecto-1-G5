package interfaz;

import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JComponent;

public class PanelCentral extends JPanel{
    private JLabel panelImagen;
    private JPanel panelDatosObra;
    private JPanel panelNavegacion;

    private JLabel lblTitulo;
    private JLabel lblAutor;
    private JLabel lblTipo;
    
    private TextField txtTitulo;
    // ...
    private VentanaPrincipal ventanaPrincipal;

    public PanelCentral(VentanaPrincipal pPrincipal){
        ventanaPrincipal = pPrincipal;

        setBorder(new TitledBorder("Información de la obra"));
        setLayout(new BorderLayout());

        panelNavegacion = new JPanel();
        add(panelNavegacion, BorderLayout.SOUTH);
        panelNavegacion.setLayout( new GridLayout( 1, 4 ) );
        panelNavegacion.setBorder( new TitledBorder( "Navegacion" ) );
        
        panelImagen = new JLabel();
        add(panelImagen, BorderLayout.WEST);

    } 

    
}
