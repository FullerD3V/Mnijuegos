/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minijuegos.GameOver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static minijuegos.Aux.Helper.*;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class GameOverController implements Initializable {
    
    private int puntuacion = minijuegos.Suerte.SuerteController.puntuacion;
    
    @FXML
    private Button btnRestart, btnHistory;
    
    @FXML
    private Label lblScore;
    
    @FXML
    private TextField txtNombre;

    public static String nombre;
    
    
    
    @FXML
    public void btnRestartOnAction() throws IOException{
        nombre = txtNombre.getText();
        
        RegistrarJugador(nombre, puntuacion);
        AbrirSuerte(0);
        
        Stage stage = (Stage) btnRestart.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    public void btnHistoryOnAction() throws IOException{
        nombre = txtNombre.getText();
        
        RegistrarJugador(nombre, puntuacion);
        AbrirSuerte(1);
                
        Stage stage = (Stage) btnHistory.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblScore.setText("Tu puntuación es: " + puntuacion);
    }        
}
