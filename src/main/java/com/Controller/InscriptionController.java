package com.Controller;
import com.DAO.*;
import com.Modele.Users;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;


public class InscriptionController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField paysField;

    @FXML
    private void handleInscriptionButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        int role = 0;
        String pays = paysField.getText();
        UsersDAOImpl dao = new UsersDAOImpl();
        ArrayList<Users> usersList = dao.recupere();

        for (int i=0; i<usersList.size(); i++) {
            if (usersList.get(i).getEmail().equals(email)|| usersList.get(i).getUsername().equals(username)||username.equals("")||email.equals("")||password.equals("")||pays.equals("")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Email ou pseudo déjà utilisé ou champ vide");
                alert.setContentText("Veuillez saisir un autre email ou pseudo");
                alert.showAndWait();
                return;
            }
            else{
                Users user = new Users(username, password, email, role, pays);
                dao.ajoute(user);
                        //close current window
        Stage currentStage = (Stage) usernameField.getScene().getWindow();
        currentStage.close();
        //open login window
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/login.fxml"));
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            stage.setTitle("Connexion");
            stage.setScene(new Scene(root, 930, 685));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

                return;
            }
        }

}
