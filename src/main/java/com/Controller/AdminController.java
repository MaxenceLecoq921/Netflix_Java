package com.Controller;

import com.DAO.*;
import com.Modele.Film;
import com.Modele.Users;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class AdminController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField id;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField paysField;

    @FXML
    private TextArea outputTextArea;

    @FXML
    private VBox admin; // récupération de la référence à la VBox depuis le fichier FXML

    @FXML
    private TextArea userInformationTextArea;

    
    public void loadAdminPage(Users user) {
        Stage primaryStage = new Stage();
        UsersDAOImpl dao = new UsersDAOImpl();
        ArrayList<Users> usersList = dao.recupere();
    
        FilmDAOImpl dao2 = new FilmDAOImpl();
        ArrayList<Film> filmsList = dao2.recupere();
    
        String message = "JE SUIS ADMIN : " + " ID : " + user.getId() + " nom d'utilisateur : " + user.getUsername() + " email : " + user.getEmail() + " rôle : " + user.getRole() + " pays : " + user.getPays() + "\n\n Utilisateurs : \n\n";
        for (int j = 0; j < usersList.size(); j++) {
            message += "ID : " + usersList.get(j).getId() + " nom d'utilisateur : " + usersList.get(j).getUsername() + " email : " + usersList.get(j).getEmail() + " rôle : " + usersList.get(j).getRole() + " pays : " + usersList.get(j).getPays() + "\n";
        }   
        
    
        String message2 = "\nListe des films : \n\n";
        for (int j = 0; j < filmsList.size(); j++) {
            if(filmsList.get(j).getDispo()==1){
                message2 += "ID : " + filmsList.get(j).getId() + " titre : " + filmsList.get(j).getTitre()+ "\n";
            }
        }
    
        primaryStage.setTitle("Ma fenêtre");
    
        TextArea textArea = new TextArea();
        textArea.setPrefHeight(20);
        textArea.setPrefWidth(20);
        textArea.setPromptText("ID de l'utilisateur ou du film à supprimer");
    
        Label messageLabel = new Label();
        messageLabel.setText(message);
    
        Label messageLabel2 = new Label();
        messageLabel2.setText(message2);
    
        // Ajout d'un listener pour détecter quand la largeur du texte dépasse 800 pixels et faire passer à la ligne
        messageLabel2.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() > 700) {
                messageLabel2.setWrapText(true);
            } else {
                messageLabel2.setWrapText(false);
            }
        });
    
        Button button = new Button();
        button.setText("Ajouter un admin");
    
        Button button2 = new Button();
        button2.setText("Supprimer un admin");
    
        Button button3 = new Button();
        button3.setText("Supprimer un film");
    
        Button button4 = new Button();
        button4.setText("se déconnecter");
    
        Button button5 = new Button();
        button5.setText("Supprimer un utilisateur");

        Button button6 = new Button();
        button6.setText("Reset");

    
        button.setOnAction(event -> handleAddAdmin(textArea.getText(), user, primaryStage));
    
        button2.setOnAction(event -> supprimeAdmin(textArea.getText(), user, primaryStage));
    
        button3.setOnAction(event -> handleRemoveFilm(textArea.getText(), user, primaryStage));
    
        button4.setOnAction(event -> handleDisconnect(primaryStage));
    
        button5.setOnAction(event -> handleRemoveUser(textArea.getText(), user, primaryStage));

        button6.setOnAction(event -> handleReset(user, primaryStage));

    
        VBox labelsVBox = new VBox();
        labelsVBox.getChildren().addAll(textArea, messageLabel, messageLabel2);
    
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(labelsVBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefViewportWidth(700);
        scrollPane.setPrefViewportHeight(500);
    
        HBox buttonsHBox = new HBox();
        buttonsHBox.setSpacing(10);
        buttonsHBox.getChildren().addAll(button, button2, button3, button4, button5, button6);
    
        VBox mainVBox = new VBox();
        mainVBox.setSpacing(10);
        mainVBox.getChildren().addAll(scrollPane, buttonsHBox);
        mainVBox.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    
        Scene scene = new Scene(mainVBox, 930, 685);
    
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    

    @FXML
    public void handleAddAdmin(String idText, Users user, Stage primaryStage){
        UsersDAOImpl dao2 = new UsersDAOImpl();
        ArrayList<Users> userlist2 = dao2.recupere();
        int iduser = Integer.parseInt(idText);
        Users userAdmin = new Users();
        for(int i=0; i<userlist2.size(); i++){
            //ajouter un message utilisateur trouvr si il trouve utilisateur qui à le meme nom dans la liste 
            if(userlist2.get(i).getId()==iduser){
                userAdmin.addAdmin(userlist2.get(i));
                System.out.println("ADMIN AJOUTE");
                UsersDAOImpl dao = new UsersDAOImpl();
                dao.update(userlist2.get(i));
                loadAdminPage(user);
                primaryStage.close();
                return;
            }
        }
    }

    @FXML
    public void supprimeAdmin(String idText, Users user, Stage primaryStage){
        UsersDAOImpl dao2 = new UsersDAOImpl();
        ArrayList<Users> userlist2 = dao2.recupere();
        int iduser = Integer.parseInt(idText);
        Users userAdmin = new Users();
        for(int i=0; i<userlist2.size(); i++){
            //ajouter un message utilisateur trouvr si il trouve utilisateur qui à le meme nom dans la liste 
            if(userlist2.get(i).getId()==iduser){
                userAdmin.removeAdmin(userlist2.get(i));
                System.out.println("ADMIN SUPPRIME");
                UsersDAOImpl dao = new UsersDAOImpl();
                dao.update(userlist2.get(i));
                loadAdminPage(user);
                primaryStage.close();
                return;
            }
        }
    }

    @FXML
    public void handleRemoveFilm(String idText, Users user, Stage primaryStage){
        FilmDAOImpl dao = new FilmDAOImpl();
        ArrayList<Film> filmList = dao.recupere();
        int idfilm = Integer.parseInt(idText);
        for(int i=0; i<filmList.size(); i++){
            if(filmList.get(i).getId()==idfilm){
                filmList.get(i).setDispo(0);
                dao.update(filmList.get(i));
                System.out.println("FILM SUPPRIME");
                loadAdminPage(user);
                primaryStage.close();
                return;
            }
            //ajouter un message utilisateur trouvr si il trouve utilisateur qui à le meme nom dans la liste 
            // if(filmList.get(i).getId()==iduser){
            //     dao.delete(filmList.get(i));
            //     System.out.println("FILM SUPPRIME");
            //     loadAdminPage(user);
            //     primaryStage.close();
            //     return;
            // }
        }
    }

    @FXML
    public void handleDisconnect(Stage primaryStage){
        primaryStage.close();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/Login.fxml"));
            primaryStage.setTitle("Netflix");
            primaryStage.setScene(new Scene(root, 930, 685));
            primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param idText
     * @param user
     * @param primaryStage
     */
    @FXML
    public void handleRemoveUser(String idText, Users user, Stage primaryStage){
        UsersDAOImpl dao = new UsersDAOImpl();
        ArrayList<Users> userList = dao.recupere();
        int iduser = Integer.parseInt(idText);
        for(int i=0; i<userList.size(); i++){
            //ajouter un message utilisateur trouvr si il trouve utilisateur qui à le meme nom dans la liste 
            if(userList.get(i).getId()==iduser){
                dao.supprime(userList.get(i));
                System.out.println("UTILISATEUR SUPPRIME");
                loadAdminPage(user);
                primaryStage.close();
                return;
            }
        }
    }


    @FXML
    public void handleReset(Users user, Stage primaryStage){
        FilmDAOImpl dao = new FilmDAOImpl();
        ArrayList<Film> filmList = dao.recupere();
        for(int i=0; i<filmList.size(); i++){
            filmList.get(i).setDispo(1);
            dao.update(filmList.get(i));
        }
        loadAdminPage(user);
        primaryStage.close();
    }
    
}
