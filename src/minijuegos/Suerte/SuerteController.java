/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minijuegos.Suerte;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import minijuegos.Aux.Helper;
import static minijuegos.Aux.Helper.JuegoTerminado;
import minijuegos.Minijuegos;

/**
 * FXML Controller class
 *
 * @author alumno
 */
public class SuerteController implements Initializable {
    
    private int puntos = 0;
    
    public static int puntuacion;

    Bloom bloom = new Bloom();

    private ArrayList<Integer> numeros = new ArrayList<>();
    
    Image trebol = new Image(Minijuegos.class.getResource("Resources/trebol.png").toString());
    Image check = new Image(Minijuegos.class.getResource("Resources/check.png").toString());
    Image cruz = new Image(Minijuegos.class.getResource("Resources/cruz.png").toString());

    @FXML
    private Button btnCerrarJuego, btnCerrarApp;

    @FXML
    private TilePane tPane;

    @FXML
    private TableView tbl;

    @FXML
    private TableColumn colJugador, colPuntuacion;

    @FXML private TabPane tabPane;

    @FXML
    private Tab tabHistorial, tabSuerte;

    @FXML
    public void btnCerrarJuegoOnAction(){
        Stage stage = (Stage) btnCerrarJuego.getScene().getWindow();
        stage.close();
    };

    @FXML
    public void btnCerrarAppOnAction(){
        Stage stage = (Stage) btnCerrarJuego.getScene().getWindow();        
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    };

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        tabPane.getSelectionModel().select(Helper.pest);
        
        tbl.setItems(Helper.jugadores);
        
        this.colJugador.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colPuntuacion.setCellValueFactory(new PropertyValueFactory("puntuacion"));

        for (int i = 1; i < 13; i++) {
            numeros.add(i);
        }
        
        Collections.shuffle(numeros);
        
        for (int i = 0; i < 12; i++) {
            int j = i;
            Button button = new Button();
            ImageView imageView = new ImageView();

            imageView.setImage(trebol);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: none;");
            button.setMaxHeight(100);
            button.setMaxWidth(100);
            button.setMinHeight(100);
            button.setMinWidth(100);

            tPane.getChildren().addAll(button);

            button.setOnMouseEntered((event) -> {
                button.setEffect(bloom);
                bloom.setThreshold(0.4);
                button.setCursor(Cursor.HAND);

            });

            button.setOnMouseExited((event) -> {
                button.setEffect(null);
            });

            button.setOnMouseClicked((event) -> {
                if(numeros.get(j) % 6 == 0){
                    puntuacion = puntos;
                    puntos = 0;
                    imageView.setImage(cruz);
                    button.setDisable(true);
                    button.setOpacity(100);
                    try{
                        Stage stage = (Stage) button.getScene().getWindow();
                        JuegoTerminado(stage);
                    } catch (IOException ex) {
                        Logger.getLogger(SuerteController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    imageView.setImage(check);
                    button.setDisable(true);
                    button.setOpacity(100);
                    puntos++;
                }
            });
        }
    }
}
