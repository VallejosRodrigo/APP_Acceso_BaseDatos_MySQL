package vista;

import controlador.*;
import javax.swing.*;
import java.awt.*;

//TODO-----FRONT--------CLASE DONDE SE CREA LA INTERFACE DE USUARIO---------------
public class FrameApp extends JFrame {

    //----------------------------------------------
    public JComboBox secciones, paises;
    public JTextArea jTextArea;
    public JButton botonConsultar;
    //---------------------------------------------

    public FrameApp(){
        iniciarFrame();
    }


    private void iniciarFrame(){

        //----------CONFIGURA EL FRAME-------
        setBounds(500,150,430,500);
        setTitle("Vallejos Rodrigo App Test acceso BASE DATOS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //---------CARGA LOS PANELES----------
        JPanel panelComboBox = new JPanel();
        panelComboBox.setLayout(new FlowLayout());
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        //---------CARGAN LOS COMBO BOX----------
        secciones = new JComboBox();
        secciones.addItem("Todos");
        secciones.setEditable(false);
        paises = new JComboBox();
        paises.addItem("Todos");
        paises.setEditable(false);

        //-----------AGREGA AL PANEL COMBO BOX----------
        panelComboBox.add(secciones);
        panelComboBox.add(paises);

        //---------------CREA TEXAREA-----------------
        jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);

        //---------CREA BOTON "CONSULTAR"------
        botonConsultar = new JButton("Consultar");

        botonConsultar.addActionListener(new ControladorBotonConsulta(this));

        //----------AGREGA AL PANEL PRINCIPAL--------------
        panelPrincipal.add(panelComboBox,BorderLayout.NORTH);
        panelPrincipal.add(jScrollPane, BorderLayout.CENTER);
        panelPrincipal.add(botonConsultar, BorderLayout.SOUTH);

        //-----------------AGREGA AL FRAME-----------------
        add(panelPrincipal);

        //--------------PONE A LA ESCUCHA EVENTO VENTANA------
        addWindowListener(new ControladorCargaMenu(this));
    }

}
