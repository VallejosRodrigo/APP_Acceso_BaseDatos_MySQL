package controlador;

import modelo.CargaBotonConsulta;
import vista.*;
import modelo.*;
import java.awt.event.*;
import java.sql.ResultSet;

//TODO-------------CONTROLADOR CARGA BOTON CONSULTA---------------------
public class ControladorBotonConsulta implements ActionListener {
    //-----------------------------------------
    private CargaBotonConsulta cargaBotonConsulta;
    private FrameApp frameApp;
    private ResultSet resulsetConsultaBoton;
    //-----------------------------------------

    public ControladorBotonConsulta(FrameApp frameApp){

        this.frameApp = frameApp;

        cargaBotonConsulta = new CargaBotonConsulta();
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String seccionSelec = (String)frameApp.secciones.getSelectedItem();
        String paisSelec = (String)frameApp.paises.getSelectedItem();

        resulsetConsultaBoton = cargaBotonConsulta.ejecutaSQLboton(seccionSelec,paisSelec);

        try{
            frameApp.jTextArea.setText("");
            int cont = 1;
//----------------IMPRIME EN EL AREA DE TEXTO-------------------------
        while(resulsetConsultaBoton.next()) {
            frameApp.jTextArea.append(cont + "- " + resulsetConsultaBoton.getString(1));
            frameApp.jTextArea.append(" | ");
            frameApp.jTextArea.append(resulsetConsultaBoton.getString(2));
            frameApp.jTextArea.append(" | ");
            frameApp.jTextArea.append(resulsetConsultaBoton.getString(3));
            frameApp.jTextArea.append(" | ");
            frameApp.jTextArea.append(resulsetConsultaBoton.getString(4));
            frameApp.jTextArea.append("\n");

            cont ++;
        }

        }catch(Exception ex){
            frameApp.jTextArea.setText("");
            frameApp.jTextArea.append("Error: " + ex.getMessage());
        }



    }
}
