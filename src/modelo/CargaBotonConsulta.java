package modelo;

import java.sql.*;

//TODO-------------CLASE PARA EJECUTAR LAS CONSULTAS PRODUCIDAS POR EL EVENTO DEL BOTON "CONSULTAR"
public class CargaBotonConsulta {
    //-----------------------------------
    ConexionBD conexionBD;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement preparedSeccion, preparedPais, preparedAmbos;
    String exception;
    //-----------------------------------

    public  CargaBotonConsulta(){
        conexionBD = new ConexionBD();
    }

    public ResultSet ejecutaSQLboton(String seccion, String pais) {

        connection = conexionBD.dameConexion();

        resultSet = null;

        try {
            if (!seccion.equals("Todos") && pais.equals("Todos")) {
                //-----------prepara la consulta------------
                preparedSeccion = connection.prepareStatement("SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=?");

                preparedSeccion.setString(1, seccion);

                resultSet = preparedSeccion.executeQuery();

            } else if (seccion.equals("Todos") && !pais.equals("Todos")) {
                preparedPais = connection.prepareStatement("SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE PAÍSDEORIGEN=?");
                preparedPais.setString(1, pais);

                resultSet = preparedPais.executeQuery();

            } else if (!seccion.equals("Todos") && !pais.equals("Todos")) {
                preparedAmbos = connection.prepareStatement("SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS WHERE SECCIÓN=? AND PAÍSDEORIGEN=?");
                preparedAmbos.setString(1, seccion);
                preparedAmbos.setString(2, pais);

                resultSet = preparedAmbos.executeQuery();

            } else if (seccion.equals("Todos") && pais.equals("Todos")) {
                Statement statement1 = connection.createStatement();

                resultSet = statement1.executeQuery("SELECT NOMBREARTÍCULO, SECCIÓN, PRECIO, PAÍSDEORIGEN FROM PRODUCTOS");

            }


        }catch (NullPointerException | SQLException eN){
            System.out.println("Error-> " + eN.getMessage());
        }

        return resultSet;

    }

}
