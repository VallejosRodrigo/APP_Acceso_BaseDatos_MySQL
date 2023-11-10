package controlador;

import vista.*;
import modelo.*;
import java.awt.event.*;
import java.sql.SQLException;

//TODO-------------CONTROLADOR CARGA COMBO BOX-------------
public class ControladorCargaMenu extends WindowAdapter {
    //--------------------------------------------
    FrameApp frameApp;
    CargaMenu cargaMenu;
    //--------------------------------------------

    public ControladorCargaMenu(FrameApp marco){
        frameApp = marco;
    }

    @Override
    public void windowOpened(WindowEvent e) { //controla cuando se abri el frame principal del paquete vista.

        cargaMenu = new CargaMenu();
        cargaMenu.ejecutaConsultas();

        try {
            while(cargaMenu.rs1.next()) {
                frameApp.secciones.addItem(cargaMenu.rs1.getString(1));
            }

            while(cargaMenu.rs2.next()) {
                frameApp.paises.addItem(cargaMenu.rs2.getString(1));
            }


            } catch (SQLException ex) {
                System.out.println("ERROR: \n" +ex.getMessage());
            }

    }


}
