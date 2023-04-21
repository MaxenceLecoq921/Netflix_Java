package com.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AccueilController {

    @FXML
    private Button connexionButton;
    
    @FXML
    private Button inscriptionButton;


    @FXML
    private void handleConnexionButton() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) connexionButton.getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void handleInscriptionButton() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Inscription.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) inscriptionButton.getScene().getWindow();
        stage.setScene(scene);
    }
}
