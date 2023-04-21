package com.Controller;

import java.io.IOException;

import com.Modele.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class FavorisController {

    private Profil profil;

    @FXML
    private Label resultat;

    @FXML
    private GridPane imageGrid;


    @FXML
    private ListView<String> listeFilms;

    @FXML
    private Label profilLabel;

    public void setProfil(Profil profil) {
        this.profil = profil;
        int imagesPerRow = 3;
        int currentRow = 0;
        int currentColumn = 0;

        //profilLabel.setText("Profil : " + profil.getNom()); // Affiche le nom du profil dans l'objet Label

        // Définit le contenu de l'objet ListView avec les noms des films aimés du profil
        for (Film film : profil.getFilmslikes()) {


            ImageView imageView = new ImageView(new javafx.scene.image.Image("file:src/main/resources/" + film.getTitre() + ".png"));
            imageView.setFitWidth(215.0);
            imageView.setFitHeight(118.0);

            imageView.setId(film.getTitre()); // Ajouter un ID aux ImageView pour les identifier
            imageView.setOnMouseClicked(event -> onImageClicked(event, imageView.getId())); // Ajouter un gestionnaire d'événements aux ImageView

            imageGrid.add(imageView, currentColumn, currentRow);

            currentColumn++;

            if (currentColumn >= imagesPerRow) {
                currentColumn = 0;
                currentRow++;
            }
        }


            


        }

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
