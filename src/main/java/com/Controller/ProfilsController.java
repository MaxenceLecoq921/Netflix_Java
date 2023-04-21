package com.Controller;

import javafx.scene.control.TextInputDialog;
import java.util.Optional;

import com.DAO.*;
import com.Modele.Profil;
import com.Modele.Users;

import java.io.IOException;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javafx.scene.image.*;
import javafx.scene.image.Image;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ProfilsController {

    @FXML
    private HBox profileContainer;

    @FXML
    private Button addProfile;

    private int profileCount = 0;
    private Users user;


        // Ajout d'un constructeur qui prend un objet Users en paramètre
        public ProfilsController(Users user) {
            this.user = user;
        }
                
        public static ProfilsController createInstance(Users user) {
            return new ProfilsController(user);
        }
                    
    

    @FXML
    public void initialize() {
        System.out.println("Initialisation de ProfilsController...");
        
        try {
            if (user != null ) {
                for (Profil profil : user.getProfils()) {

                    profileCount++;
                    if (profileCount < 5) {
                        System.out.println("Création du bouton pour : " + profil.getNom());
                        createProfileButton(profil.getNom());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche des profils: " + e.getMessage());
            e.printStackTrace();
        }
        
        addProfile.setOnAction(event -> handleAddProfileButton());
    }
                
    private void createProfileButton(String profileName) {
        Button profileButton = new Button(profileName);
        profileButton.setGraphic(createAvatarImage());
        profileButton.setContentDisplay(ContentDisplay.TOP);
        profileButton.setMinWidth(150.0);
        profileButton.setOnAction(event -> handleProfileButton(profileName));
        profileContainer.getChildren().add(profileButton);
    }
            
    private void handleProfileButton(String profileName) {
        System.out.println("Profil de " + profileName + " sélectionné");
    
        try {
            // Trouvez le profil correspondant au nom sélectionné
            Profil selectedProfil = new Profil();
            for (Profil profil : user.getProfils()) {
                if (profil.getNom().equals(profileName)) {
                    selectedProfil = profil;
                    break;
                }
            }
    
            // Chargez le fichier FXML et créez un nouvel objet FXMLLoader
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Vue/Netflix.fxml"));
            Parent root = fxmlLoader.load();
    
            // Obtenir le contrôleur et passez le profil sélectionné en paramètre
            NetflixController netflixController = fxmlLoader.getController();
            netflixController.setProfil(selectedProfil);
    
            // Créez une nouvelle scène et ouvrez-la dans une nouvelle fenêtre ou remplacez la scène actuelle

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            stage.setScene(scene);
            stage.setTitle("Netflix");

            
            stage.show();

    
            addProfile.getScene().getWindow().hide();
    
        } catch (IOException e) {
            System.out.println("Erreur lors de l'ouverture de la page Netflix: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void handleAddProfileButton() {
        if (profileCount < 4) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Ajouter un profil");
            dialog.setHeaderText("Entrez le nom du profil :");
            dialog.setContentText("Nom du profil:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(profileName -> {
                profileCount++;
                Profil addedProfil = new Profil();
                addedProfil.setNom(profileName); // Ajoute le profil au tableau "profils" du user avec le "nom" uniquement
                addedProfil.setUser(user);
                user.addProfil(profileName);
                createProfileButton(profileName);
                ProfilDAOImpl profilDao = new ProfilDAOImpl();
                profilDao.ajoute(addedProfil); // Ajoute le profil à la base de données
                UsersDAOImpl userDao = new UsersDAOImpl();
                userDao.update(user);

            });
        } else {
            showAlert("Nombre maximum de profils atteint");
        }
    }        
    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private ImageView createAvatarImage() {
        Image avatarImage = new Image("avatar.png");
        Image avatarImage2 = new Image("avatar2.png");
        Image avatarImage3 = new Image("avatar3.png");

        ImageView avatarImageView = new ImageView(avatarImage);
        ImageView avatarImageView2 = new ImageView(avatarImage2);
        ImageView avatarImageView3 = new ImageView(avatarImage3);

        
        avatarImageView.setFitHeight(50);
        avatarImageView.setFitWidth(50);
        avatarImageView2.setFitHeight(50);
        avatarImageView2.setFitWidth(50);
        avatarImageView3.setFitHeight(50);
        avatarImageView3.setFitWidth(50);
        return avatarImageView;
    }

    public void setProfil(Profil profil) {
    }
    
    
    }
