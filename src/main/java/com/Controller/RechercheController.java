package com.Controller;

import com.DAO.*;
import com.Modele.*;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.ArrayList;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RechercheController {
    @FXML
    private Label resultat;

    @FXML
    private GridPane imageGrid;

    @FXML
    public void setRecherche(String recherche) {
        imageGrid.getChildren().clear();

        FilmDAOImpl daofilms = new FilmDAOImpl();
        ArrayList<Film> films = daofilms.recupere();
        System.out.println("Recherche: " + recherche);

        int imagesPerRow = 4;
        int currentRow = 0;
        int currentColumn = 0;

        for (Film film : films) {


            if (film.getTitre().toLowerCase().contains(recherche.toLowerCase()) && film.getDispo() == 1) {
                System.out.println("TROUVé " + film.getTitre());
                ImageView imageView = new ImageView(new javafx.scene.image.Image("file:src/main/resources/" + film.getTitre() + ".png"));
                imageView.setFitWidth(215.0);
                imageView.setFitHeight(118.0);

                imageView.setId(film.getTitre()); // Ajouter un ID aux ImageView pour les identifier
                System.out.println("IDDDDDDDDD2 : " + imageView.getId());
                imageView.setOnMouseClicked(event -> onImageClicked(event, imageView.getId())); // Ajouter un gestionnaire d'événements aux ImageView

                imageGrid.add(imageView, currentColumn, currentRow);

                currentColumn++;

                if (currentColumn >= imagesPerRow) {
                    currentColumn = 0;
                    currentRow++;
                }
            }
        }
    }

    public void initialize() {
        // Initialisez ici les éléments graphiques de votre fenêtre de recherche
        resultat = new Label();
    }



    // public set_Id(ImageView imageView) {



    // }

    public void onImageClicked(MouseEvent event, String imageViewId) {
        System.out.println("Film sélectionné: " + imageViewId);

        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Vue/Voir.fxml"));
            Parent root = loader.load();

            // Récupérer le contrôleur et passer l'ID de l'image en paramètre
            VoirController controller = loader.getController();
            controller.setImageId(imageViewId);

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
}
