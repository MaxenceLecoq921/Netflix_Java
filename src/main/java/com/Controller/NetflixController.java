package com.Controller;

import java.util.*;
import com.DAO.*;
import com.Modele.Film;
import com.Modele.Profil;

import java.io.*;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class NetflixController {
    private Profil profil;
    private FilmDAOImpl dao = new FilmDAOImpl();
    private ArrayList<Film> films = dao.recupere();

    @FXML
    private Button alien;

    @FXML
    private TextField rechercheField;

    @FXML
    private ImageView loupe;



    @FXML
    private ImageView madmax, gladiator, indiana, anneaux, liste, evades, ligne, parrain, matrix,
                      inter, runner, inception, visiteurs, vadrouille, intouchables, cons, lion, walle, ratatouille;
    


    public void setProfil(Profil profil) {
        this.profil = profil;
        // Effectuez les opérations nécessaires avec le profil sélectionné
    }

    public void initialize() {
        actualiseFilm();
        // Ajouter des gestionnaires d'événements pour chaque image
    
        // Ajouter un gestionnaire d'événements pour le bouton "alien"
        alien.setOnMouseClicked(event -> onAlienClicked(event, alien.getId()));
        loupe.setOnMouseClicked(event -> {
            try {
                rechercher();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
 
        

        // Ajouter un gestionnaire d'événements pour le bouton "Quitter Netflix"
        Button boutonQuitterNetflix = new Button("Quitter Netflix");
        boutonQuitterNetflix.setOnAction(event -> fermerPage());


    }
    
    private void onImageClicked(MouseEvent event, String imageViewId) {
        System.out.println("Film sélectionnée: " + imageViewId);

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Voir.fxml"));
            Parent root = loader.load();
    
            // Récupérer le contrôleur et passer l'ID de l'image en paramètre
            VoirController controller = loader.getController();
            controller.setImageId(imageViewId);
            controller.setProfil(profil);
            
    
            // Créer une nouvelle scène et l'afficher
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

        private void onAlienClicked(MouseEvent event, String buttonId) {
            System.out.println("Film sélectionnée: " + buttonId);
        
            try {
                // Charger le fichier FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Voir.fxml"));
                Parent root = loader.load();
        
                // Récupérer le contrôleur et passer l'ID de l'image en paramètre
                VoirController controller = loader.getController();
                controller.setImageId(buttonId);
        
                // Créer une nouvelle scène et l'afficher
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public void rechercher() throws IOException {
        String recherche = rechercheField.getText();
        System.out.println("Recherche effectuée: " + recherche);
    
        Stage primaryStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Recherche.fxml"));
        Parent root = loader.load();
        RechercheController controller = loader.getController();
        primaryStage.setTitle("Netflix");
        primaryStage.setScene(new Scene(root, 930, 685));
        primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
        primaryStage.show();
    
        controller.setRecherche(recherche);
    
        
    // // Charger le fichier FXML de la nouvelle fenêtre
    // FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Login.fxml"));

    // // Créer un objet Parent qui représente le contenu de la fenêtre
    // Parent root = loader.load();

    // // Récupérer le contrôleur de Recherche.fxml
    // RechercheController controller = loader.getController();
    // controller.setRecherche(recherche);



    // // Créer une nouvelle Scene en utilisant le Parent comme contenu et définir sa taille
    // Scene scene = new Scene(root, 600, 400);

    // // Créer un nouvel objet Stage
    // Stage stage = new Stage();

    // // Associer la Scene au nouvel objet Stage
    // stage.setScene(scene);

    // // Afficher le Stage
    // stage.show();
}
        

        
        public void deconnexion() throws IOException {
            try {
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("../Vue/Login.fxml"));
                primaryStage.setTitle("Netflix");
                primaryStage.setScene(new Scene(root, 930, 685));
                primaryStage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
                primaryStage.show();
                fermerPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
    
        }
        
        
        
        public void fermerPage() {
            //afficher test
            System.out.println("Fermeture de la page");
            Stage stage = (Stage) alien.getScene().getWindow();
            stage.close();
        }
        
    public void actualiseFilm(){
        FilmDAOImpl dao = new FilmDAOImpl();
        ArrayList<Film> films = dao.recupere();
        ImageView[] imageViews = {madmax, gladiator, indiana, anneaux, liste, evades, ligne, parrain, matrix,
        inter, runner, inception, visiteurs, vadrouille, intouchables, cons, lion, walle, ratatouille};
        for(int i = 0; i < films.size()-1; i++){
            if(films.get(i+1).getDispo()==1){
                imageViews[i].setImage(new javafx.scene.image.Image("file:src/main/resources/"+ films.get(i+1).getTitre()+".png"));
                ImageView a = imageViews[i];
                a.setOnMouseClicked(event -> onImageClicked(event, a.getId()));
            }
        }

    }

    public void profilChange(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../Vue/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root, 930, 685));
            stage.getIcons().add(new javafx.scene.image.Image("file:src/main/resources/N.png"));
            stage.show();
            fermerPage();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFavoris(ActionEvent event) {
        try {
            // Charger le fichier FXML de la nouvelle fenêtre
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Favoris.fxml"));
    
            // Créer un objet Parent qui représente le contenu de la fenêtre
            Parent root = loader.load();
    
            // Récupérer le contrôleur de Favoris.fxml
            FavorisController controller = loader.getController();
    
            // Passer le profil en paramètre
            controller.setProfil(profil);
    
            // Créer une nouvelle Scene en utilisant le Parent comme contenu et définir sa taille
            Scene scene = new Scene(root, 700, 550);
    
            // Créer un nouvel objet Stage
            Stage stage = new Stage();
    
            // Associer la Scene au nouvel objet Stage
            stage.setScene(scene);
    
            // Afficher le Stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    
}
