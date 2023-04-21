package com.Controller;

import com.DAO.*;
import com.Modele.Users;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


import java.io.IOException;
import java.util.ArrayList;

public class LoginController2 {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    // méthode publique appelée par l'EventHandler pour se connecter
    public void handleLoginButton() {
        String email = emailField.getText();
        String password = passwordField.getText();
        Users user = new Users();
        UsersDAOImpl dao = new UsersDAOImpl();
        ArrayList<Users> usersList = dao.recupere();
        for (int i=0; i<usersList.size(); i++) {
            if (usersList.get(i).getEmail().equals(email) && usersList.get(i).getPassword().equals(password)) {
                user = usersList.get(i);
                for (int j=0; j<user.getProfils().size(); j++) {
                    System.out.println(user.getProfils().get(j).getNom());
                }
                System.out.println("Connexion réussie");
                // user.addProfil("testtt"); 
                showProfils(user);
                return;
            }
        }
        showErrorAlert("Connexion échouée", "Email ou mot de passe incorrect.");
        System.out.println("Connexion échouée (email ou mot de passe incorrect)");
    }
    
    private void showProfils(Users loggedInUser) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Profils.fxml"));

            // Utilisation de setControllerFactory pour créer une instance de ProfilsController
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == ProfilsController.class) {
                    return ProfilsController.createInstance(loggedInUser);
                } else {
                    try {
                        return controllerClass.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            stage.setTitle("Sélection du profil");
            stage.setScene(new Scene(root, 930, 685));

            //Fermeture de la fenêtre de connexion
            Stage currentStage = (Stage) emailField.getScene().getWindow();
            currentStage.hide();
    
            stage.show();
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement de la fenêtre de sélection du profil");
            e.printStackTrace();
        }

        
    }


    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    //methode qui permet d'utiliser sendinblue
    public void sendMail(String email) {
    	UsersDAOImpl dao = new UsersDAOImpl();
    	dao.envoieMail(email);
    }
                
    }
