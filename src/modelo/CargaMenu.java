package modelo;

import java.sql.*;

//TODO-------------CLASE EJECUTA SQL LOS DOS COMBO BOX CON LAS SECCIONES Y LOS PAISES DE LA TABLA EN BD-----------------
public class CargaMenu {
    //----------------------------------
    ConexionBD conexionBD;
    public ResultSet rs1, rs2;
    Producto producto;
    //----------------------------------

    public CargaMenu(){
        conexionBD = new ConexionBD();
    }

    public String ejecutaConsultas(){

        Connection conexion = conexionBD.dameConexion();

        try {
            Statement secciones = conexion.createStatement();
            Statement paises = conexion.createStatement();

            rs1 = secciones.executeQuery("SELECT DISTINCTROW SECCIÓN FROM PRODUCTOS");
            rs2 = paises.executeQuery("SELECT DISTINCTROW PAÍSDEORIGEN FROM PRODUCTOS");

            producto = new Producto();

            producto.setSeccionArticulo(rs1.getString(1));
            producto.setPaisOrigenArticulo(rs2.getString(1));

            rs1.close();
            rs2.close();
            conexion.close();

        }catch (Exception e){
            System.out.println("Fallo: \n" + e.getMessage());
        }


         return producto.getSeccionArticulo();
    }


}
