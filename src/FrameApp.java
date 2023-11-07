import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class FrameApp extends JFrame {

    public FrameApp(){

        iniciarFrame();
        consultasSQL();

        setVisible(true);
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

        //---------CREA BOTON "CONSULTAR"------
        botonConsultar = new JButton("Consultar");
        botonConsultar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){

                ejecutarSQL();
            }
        });

        //----------AGREGA AL PANEL PRINCIPAL--------------
        panelPrincipal.add(panelComboBox,BorderLayout.NORTH);
        panelPrincipal.add(jScrollPane, BorderLayout.CENTER);
        panelPrincipal.add(botonConsultar, BorderLayout.SOUTH);

        //-----------------AGREGA AL FRAME-----------------
        add(panelPrincipal);
    }

    private void consultasSQL(){

        try {
            //-------CONEXIÓN CON LA BASE DE DATOS, EN ESTE CASO LOCAL--------------
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "37262rorro");

            Statement statement = connection.createStatement(); //SENTENCIA SQL

            String solicitudSeccionComboBox = "SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS";

            ResultSet resultSet = statement.executeQuery(solicitudSeccionComboBox); //OBTIENE LA RESPUESTA EJECUTANDO LA SENTENCIA

            while(resultSet.next()){ //mientras que exista un elemento siguiente dentro de este resulset...

                secciones.addItem(resultSet.getString(1)); //agrega a secciones el primer elemento de la primer columna
            }
            resultSet.close();

            //-----------REPETIMOS PROCESO PARA EL COMBO BOX DE LOS PAISES-------------------

            statement = connection.createStatement();

            String solicitudPaisComboBox = "SELECT DISTINCTROW PAÍSDEORIGEN FROM PRODUCTOS";

            resultSet = statement.executeQuery(solicitudPaisComboBox);

            while(resultSet.next()){
                paises.addItem(resultSet.getString(1));
            }

            resultSet.close();
            statement.close();
            connection.close();

        }catch (SQLException e){
            System.out.println("ERROR AL CONECTAR: " + e.getMessage());
        }

    }

    private void ejecutarSQL(){

        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "37262rorro");

            ResultSet resultSet = null;
            jTextArea.setText("");

            //-------------------------------------------
            String campoIngresadoSecciones = (String) secciones.getSelectedItem();
            String campoIngresadoPaises = (String) paises.getSelectedItem();

            if(!campoIngresadoSecciones.equals("Todos") && campoIngresadoPaises.equals("Todos")){

                preparedSeccion = connection.prepareStatement(solicitudSeccion);
                preparedSeccion.setString(1, campoIngresadoSecciones);

                resultSet = preparedSeccion.executeQuery();

            }else if(campoIngresadoSecciones.equals("Todos") && !campoIngresadoPaises.equals("Todos")){
                preparedPais = connection.prepareStatement(solicitudPais);
                preparedPais.setString(1, campoIngresadoPaises);

                resultSet = preparedPais.executeQuery();

            }else if(!campoIngresadoSecciones.equals("Todos") && !campoIngresadoPaises.equals("Todos")){
                preparedAmbos = connection.prepareStatement(solicitudAmbos);
                preparedAmbos.setString(1, campoIngresadoSecciones);
                preparedAmbos.setString(2, campoIngresadoPaises);

                resultSet = preparedAmbos.executeQuery();

            }else if(campoIngresadoSecciones.equals("Todos") && campoIngresadoPaises.equals("Todos")){

                Statement statement1 = connection.createStatement();

                resultSet = statement1.executeQuery("SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS");
            }

            int contador = 1;

            while(resultSet.next()){

                jTextArea.append(contador+" - "+resultSet.getString(1));
                jTextArea.append(" | ");
                jTextArea.append(resultSet.getString(2));
                jTextArea.append(" | ");
                jTextArea.append(resultSet.getString(3));
                jTextArea.append(" | ");
                jTextArea.append(" ["+resultSet.getString(4)+"]");
                jTextArea.append("\n");
                contador++;
            }

            resultSet.close();
            connection.close();


        }catch (Exception e){
            System.out.println("Fallo en conección: " + e.getMessage());
        }

    }

    private Connection connection;
    private JComboBox secciones, paises;
    private PreparedStatement preparedSeccion, preparedPais, preparedAmbos;
    private JTextArea jTextArea;
    private JButton botonConsultar;
    private String solicitudSeccion = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=?";
    private String solicitudPais = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE PAÍSDEORIGEN=?";
    private String solicitudAmbos = "SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=? AND PAÍSDEORIGEN=?";


}
