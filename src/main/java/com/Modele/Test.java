package com.Modele;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.*;
import java.io.*;




public class Test extends Application {


    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/Login.fxml"));
            primaryStage.setTitle("Netflix");
            primaryStage.setScene(new Scene(root, 930, 685));
            primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            primaryStage.show();

            // Charger et jouer la musique
            Media sound = new Media(getClass().getResource("/netflix.mp3").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}

