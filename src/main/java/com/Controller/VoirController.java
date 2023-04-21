package com.Controller;
import com.DAO.*;
import com.Modele.*;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;


public class VoirController {
    @FXML
    private WebView webView;

    @FXML
    private ComboBox<String> ratingComboBox;

    FilmDAOImpl daofilms = new FilmDAOImpl();
    ProfilDAOImpl daoprofil = new ProfilDAOImpl();
    ArrayList<Profil> profils = daoprofil.recupere();
    Users user = new Users();
    ArrayList<Film> films = daofilms.recupere();
    Film filmview = new Film();
    Profil profil = new Profil();

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public void setImageId(String imageViewId) {


        String videoId = getVideoIdFromImageId(imageViewId);    


        // Charger la vidéo YouTube dans WebView
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.youtube.com/embed/" + videoId);


        filmview = setFilmview(imageViewId);    

        
        afficherinfos(filmview);
        System.out.println(filmview.getTitre());

    }

        

    public void afficherinfos(Film film) {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("\n\n\n")); // Ajout des 3 lignes vides
        
        Label titre = new Label("Titre : " + film.getTitre());
        titre.setTextFill(Color.WHITE);
        vbox.getChildren().add(titre);
        vbox.getChildren().add(new Label()); // Ajout de la ligne vide
    
        Label annee = new Label("Année : " + film.getDateSortie());
        annee.setTextFill(Color.WHITE);
        vbox.getChildren().add(annee);
        vbox.getChildren().add(new Label()); // Ajout de la ligne vide
    
        Label acteurs = new Label("Acteurs : " + film.getActeurs());
        acteurs.setTextFill(Color.WHITE);
        acteurs.setMaxWidth(500); // Largeur maximale de 100
        acteurs.setWrapText(true); // Activation du retour à la ligne automatique
        vbox.getChildren().add(acteurs);
        vbox.getChildren().add(new Label()); // Ajout de la ligne vide
    
        Label synopsis = new Label("Synopsis : " + film.getSynopsis());
        synopsis.setTextFill(Color.WHITE);
        synopsis.setMaxWidth(500); // Largeur maximale de 100
        synopsis.setWrapText(true); // Activation du retour à la ligne automatique
        vbox.getChildren().add(synopsis);
        vbox.getChildren().add(new Label()); // Ajout de la ligne vide

        Label ratingLabel = new Label("Notation:");
        ratingLabel.setTextFill(Color.WHITE);
        vbox.getChildren().add(ratingLabel);
    
        ComboBox<String> ratingComboBox = new ComboBox<>();
        ratingComboBox.getItems().addAll(
                "1 étoile",
                "2 étoiles",
                "3 étoiles",
                "4 étoiles",
                "5 étoiles"
        );
        vbox.getChildren().add(ratingComboBox);

        vbox.getChildren().add(new Label()); // Ajout de la ligne vide
        vbox.getChildren().add(new Label()); // Ajout de la ligne vide
        vbox.getChildren().add(new Label()); // Ajout de la ligne vide
        Button ajouterFavorisButton = new Button("Ajouter aux favoris");
        ajouterFavorisButton.setStyle("-fx-padding: 15px 0 15px 0; -fx-margin-right: 50px;");



        vbox.getChildren().add(ajouterFavorisButton);

    
    
    
        ((HBox) webView.getParent()).getChildren().add(vbox);

        ratingComboBox.setOnAction(event -> {
            String rating = ratingComboBox.getValue();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Note enregistrée !");
            alert.setContentText("Vous avez donné " + rating + " à ce film.");
            alert.showAndWait();
        });

        ajouterFavorisButton.setOnAction(event -> {
            
            profil.addfilmslikes(film);
            System.out.println("__________");
            profil.displayfilmslikes();
            System.out.println("__________");
            daoprofil.update(profil);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Ajouté aux favoris !");
            alert.setContentText("Ce film a été ajouté à vos favoris.");
            alert.showAndWait();

            
            

        });
    
        
        
    }
        
    
                
    public Film setFilmview(String imageId) {
        
        Film film = new Film();
        System.out.println(imageId);
        switch (imageId) {
            case "alien":
            case "Alien":
                film = films.get(0);
                break;
            case "madmax":
            case "Madmax":
                film = films.get(1);
                break;
            case "gladiator":
            case "Gladiator":
                film = films.get(2);
                break;
            case "indiana":
            case "Indiana Jones":
                film = films.get(3);
                break;
            case "anneaux":
            case "Le Seigneur des anneaux":
                film = films.get(4);
                break;
            case "liste":
            case "La Liste de Schindler":
                film = films.get(5);
                break;
            case "evades":
            case "Les Evades":
                film = films.get(6);
                break;
            case "ligne":
            case "La Ligne verte":
                film = films.get(7);
                break;
            case "parrain":
            case "Le Parrain":
                film = films.get(8);
                break;
            case "matrix":
            case "Matrix":
                film = films.get(9);
                break;
            case "inter":
            case "Interstellar":
                film = films.get(10);
                break;
            case "runner":
            case "Blade Runner":
                film = films.get(11);
                break;
            case "inception":
            case "Inception":
                film = films.get(12);
                break;
            case "visiteurs":
            case "Les Visiteurs":
                film = films.get(13);
                break;
            case "vadrouille":
            case "La Grande Vadrouille":
                film = films.get(14);
                break;
            case "intouchables":
            case "Intouchables":
                film = films.get(15);
                break;
            case "cons":
            case "Le Diner de cons":
                film = films.get(16);
                break;
            case "lion":
            case "Le Roi Lion":
                film = films.get(17);
                break;
            case "walle":
            case "wall-e":
                film = films.get(18);
                break;
            case "ratatouille":
            case "Ratatouille":
                film = films.get(19);
                break;
            default:
                film = films.get(0);
                break;
        }
        

                return film; 

    }
    

    public String getVideoIdFromImageId(String imageId) {

        switch (imageId) {
            case "alien":
            case "Alien":
                return "svnAD0TApb8";
            case "madmax":
            case "Madmax":
                return "hEJnMQG9ev8";
            case "gladiator":
            case "Gladiator":
                return "owK1qxDselE";
            case "indiana":
            case "Indiana Jones":
                return "ZfVYgWYaHmE";
            case "anneaux":
            case "Le Seigneur des anneaux":
                return "x8UAUAuKNcU";
            case "liste":
            case "La Liste de Schindler":
                return "ONWtyxzl-GE";
            case "evades":
            case "Les Evades":
                return "2e8Otbbcowc";
            case "ligne":
            case "La Ligne verte":
                return "mccs8Ql8m8o";
            case "parrain":
            case "Le Parrain":
                return "bmtuIhesQWA";
            case "matrix":
            case "Matrix":
                return "8xx91zoASLY";
            case "inter":
            case "Interstellar":
                return "VaOijhK3CRU";
            case "runner":
            case "Blade Runner":
                return "gCcx85zbxz4";
            case "inception":
            case "Inception":
                return "CPTIgILtna8";
            case "visiteurs":
            case "Les Visiteurs":
                return "wOMZzFIhPL0";
            case "vadrouille":
            case "La Grande Vadrouille":
                return "ZmWcxvB1j_E";
            case "intouchables":
            case "Intouchables":
                return "cXu2MhWYUuE";
            case "cons":
            case "Le Diner de cons":
                return "4FANGIUNbiA";
            case "lion":
            case "Le Roi Lion":
                return "tvvQitXftGk";
            case "walle":
            case "wall-e":
                return "CZ1CATNbXg0";
            case "ratatouille":
            case "Ratatouille":
                return "KpiDM6I4x_Q";
            default:
                return "17XqBdCiHOI";
        }
    }

    }
