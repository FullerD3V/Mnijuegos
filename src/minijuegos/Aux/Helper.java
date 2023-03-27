/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minijuegos.Aux;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import minijuegos.Models.Jugador;

/**
 *
 * @author alumno
 * 
 * contenedor de funciones para evitar repetir código
 * 
 */
public class Helper {
    public static ObservableList<Jugador> jugadores = FXCollections.observableArrayList();
    public static int pest;
    
    //Se abre la ventana del juego Suerte
    public static void AbrirSuerte(int tab) throws IOException{        
        pest = tab;
        Parent root = FXMLLoader.load(minijuegos.Minijuegos.class.getResource("Suerte/FXML.fxml"));        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Suerte");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
    }
    
    //Se abre un diálogo de error si no existe el juego seleccionado
    public static void Alerta(String titulo, String mensaje) throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(mensaje);
        alert.showAndWait();
        Stage Stage = null;

        Stage alerta = (Stage) ;
        alerta.close();
    }
    
    public static void JuegoTerminado(Stage parent) throws IOException{
        Parent root = FXMLLoader.load(minijuegos.Minijuegos.class.getResource("GameOver/FXML.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Se acabó el juego");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((event) -> {
            parent.close();
        });
    }
    
    public static void RegistrarJugador(String nombre, int puntuacion) throws IOException{
        nombre = ("".equals(nombre) ? "Invitado" : nombre);
        Jugador j = new Jugador(nombre, puntuacion);
        jugadores.add(j);
        FileWriter myWriter = new FileWriter("src/minijuegos/Aux/Registros.txt", true);
        myWriter.append(nombre + ","+ puntuacion + "\n" );
        myWriter.close();
    }
    
    public static void LeerRegistros() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("src/minijuegos/Aux/Registros.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            String nombre;
            int puntuacion;
            List<String> myList = new ArrayList<>();

            while (line != null) {
                sb.append(line);
                
                myList = Arrays.asList(line.split(","));
                nombre =  myList.get(0);
                puntuacion = Integer.valueOf(myList.get(1));
                
                Jugador j = new Jugador(nombre, puntuacion);
                jugadores.add(j);
                
                sb.append(System.lineSeparator());                
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
 }
