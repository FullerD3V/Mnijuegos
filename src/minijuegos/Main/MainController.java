/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minijuegos.Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import static minijuegos.Aux.Helper.*;

/**
 *
 * @author alumno
 */
public class MainController implements Initializable {
    
    @FXML
    private ListView lstJuegos;
    
    @FXML
    private Button btnJugar;
    
    @FXML
    private TextField txtBuscar;
    
    private BufferedReader br;
    
    private String opt = null;
        
    @FXML
    public void btnJugarOnAction(Event event) throws IOException{
        opt = txtBuscar.getText().toLowerCase();
        switch(opt){
            case "suerte":
                AbrirSuerte(0);
            break;
            
            default:
                Alerta("Error", "Juego no disponible");
            break;
        }
    }
    
    @FXML
    public void lstJuegosOnMouseClicked(MouseEvent event) throws IOException{
        //Con un solo click se importa el nombre del juego a la barra de búsqueda
        if (event.getClickCount() == 1) {
            txtBuscar.setText(lstJuegos.getSelectionModel().getSelectedItem().toString());
        }
        
        //Con doble click se invoca al método btnJugarOnAction que abre el juego seleccionado
        if (event.getClickCount() == 2) {
            btnJugarOnAction(event);
        }
    };
    
    //Solo se permiten escribir letras en el buscador
    @FXML
    public void txtBuscarOnKeyTyped(KeyEvent event){
        if(!event.getCharacter().matches("[A-Z, a-z, ñ, Ñ]")){
            event.consume();
        }
    }
    
    //Filtro de búsqueda
    @FXML
    public void txtBuscarOnKeyReleased(KeyEvent event){}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
       
        //Leer juego.txt y escribirlo en el ListView
        try {
            br = new BufferedReader(new FileReader("src/minijuegos/Resources/juegos.txt"));

            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();  
                if(line != null){
                    lstJuegos.getItems().add(line);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            LeerRegistros();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
