package modelo;

import javax.swing.*;
import java.sql.*;

//TODO-------------CLASE PARA CREAR UNA CONEXION A DB LOCAL EN ESTE CASO MYSQL-------------
public class ConexionBD {
    //-------------------------------------
    private Connection connection;
    //------------------------------------

    public ConexionBD(){

    }

    public Connection dameConexion(){
        try {
            //-------CONEXIÓN CON LA BASE DE DATOS, EN ESTE CASO LOCAL--------------
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pruebas", "root", "37262rorro");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Fallo en conexión: \n" + e.getMessage());
        }

        return connection;
    }

}
