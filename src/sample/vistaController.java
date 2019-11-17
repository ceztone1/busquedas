package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
/*
 * class vistaController
 * */

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class vistaController implements Initializable {
    @FXML
    Button btnLF, btnAnchura, btnProfundidad,btnCoste;
    @FXML
    TextField txtRaiz,txtObjetivo;
    String matriz[][];
    busqueda busqueda = new busqueda();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicio();
    }

    void inicio() {
        btnCoste.setOnAction(x->{
            if (!txtRaiz.getText().equalsIgnoreCase("")){
                busqueda.coste_uniforme(new nodo(0,txtRaiz.getText()),txtObjetivo.getText(),matriz);
            }
        });
        btnProfundidad.setOnAction(x -> {

            if (!txtRaiz.getText().equalsIgnoreCase(""))
                busqueda.profundidad(Integer.parseInt(txtRaiz.getText()), matriz);
            busqueda.imprimirArbol();
        });
        btnAnchura.setOnAction(x ->
        {

            if (!txtRaiz.getText().equalsIgnoreCase(""))
                busqueda.anchura(Integer.parseInt(txtRaiz.getText()), matriz);
            busqueda.imprimirArbol();

        });
        btnLF.setOnAction(x -> {
            File file = null;
            file = openFile();
            String line, array[];
            int i = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                line = br.readLine();
                array = line.split(" ");
                long len = file.length();
                i = 0;
                matriz = new String[array.length][array.length];
                while (line != null) {
                    array = line.split(" ");
                    for (int j = 0; j < array.length; j++) {
                        matriz[i][j] = array[j];
                    }
                    line = br.readLine();
                    i++;
                }

                br.close();
            } catch (Exception e) {
                System.out.println("Error " + e);
            }

        });
    }


    public File openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("File");
        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("txt", "*.txt")
        );
        // Obtener la imagen seleccionada
        File file = fileChooser.showOpenDialog(null);
        return file;
    }
}
