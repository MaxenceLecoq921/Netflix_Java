package com.DAO;

import com.Modele.Film;
import java.sql.*;
import java.util.ArrayList;

public class FilmDAOImpl implements FilmDAO {

    private Connection con;

    public FilmDAOImpl() { 
        try {
            String url = "jdbc:mysql://localhost:3306/netflix_java";
            String user = "root";
            String password = "";

            this.con = DriverManager.getConnection(url, user, password); // stockage de la connexion dans la variable d'instance "con"
        } catch (Exception  e) {
            System.out.println("Erreur de connexion à la base de données (FilmDAOImpl.java)"); 
        }
    }
    
    @Override
    public ArrayList<Film> recupere() {
        ArrayList<Film> listeFilm = new ArrayList<>();
    
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM film");
            ResultSet resultat = statement.executeQuery();
    
            while (resultat.next()) {
                Film film = new Film();
                film.setId(resultat.getInt("id"));
                film.setTitre(resultat.getString("titre"));
                film.setRealisateur(resultat.getString("realisateur"));
                film.setDateSortie(resultat.getInt("anne_sortie"));
                film.setSynopsis(resultat.getString("synopsis"));
                film.setPathVideo(resultat.getString("path_video"));
                film.setActeurs(resultat.getString("acteurs"));
                film.setDispo(resultat.getInt("dispo"));


                //Stocker dans une ArrayList <Film> les films

                // c'est un arraylist de stringdonc cas particulier


                listeFilm.add(film);
            }

        }
        //Gestion des execptions si la requête ne s'execute pas
        catch (SQLException e)
        {
            System.out.println("Erreur lors de la récupération des films");
        }

        System.out.println("Récupération des films terminée");

        return listeFilm;
    }


    @Override
    public void ajoute(Film film) {
        try {
            // Conversion des ArrayLists en chaînes de caractères séparées par des virgules
            String tempoActeurs = String.join(",", film.getActeurs());
            String tempoPays = String.join(",", film.getPays());
            String tempoQualites = String.join(",", film.getQualityVideo());
            String tempoLangues = String.join(",", film.getLangue());
            String tempoLangueSousTitres = String.join(",", film.getLangueSousTitre());
    
            try (PreparedStatement statement = con.prepareStatement("INSERT INTO film (titre, realisateur, genre, anne_sortie, synopsis, path_affiche, langue_actu, teaser_titre, quality_video_actu, langue_sous_titre_actu, path_video, path_teaser, duree, acteurs, pays, qualites, Langues, langue_sous_titre, dispo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                // Définir les valeurs des paramètres
                statement.setString(1, film.getTitre());
                statement.setString(2, film.getRealisateur());
                statement.setString(3, film.getGenre());
                statement.setInt(4, film.getDateSortie());
                statement.setString(5, film.getSynopsis());
                statement.setString(6, film.getAffiche());
                statement.setString(7, film.getLangueActu());
                statement.setString(8, film.getTeaser());
                statement.setString(9, film.getQualityVideoActu());
                statement.setString(10, film.getLangueSousTitreActu());
                statement.setString(11, film.getPathVideo());
                statement.setString(12, film.getPathTeaser());
                statement.setDouble(13, film.getDuree());
                statement.setString(14, tempoActeurs);
                statement.setString(15, tempoPays);
                statement.setString(16, tempoQualites);
                statement.setString(17, tempoLangues);
                statement.setString(18, tempoLangueSousTitres);
                statement.setInt(19, film.getDispo());
    
                // Exécuter la requête d'insertion
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du film" + film.getTitre());
        }
    
        System.out.println("Le film " + film.getTitre() + " a été ajouté avec succès");
    }
    
    @Override
    public void update(Film film) {
        try {
            String tempoActeurs = String.join(",", film.getActeurs());
            String tempoPays = String.join(",", film.getPays());
            String tempoQualites = String.join(",", film.getQualityVideo());
            String tempoLangues = String.join(",", film.getLangue());
            String tempoLangueSousTitres = String.join(",", film.getLangueSousTitre());
    
            try (PreparedStatement statement = con.prepareStatement("UPDATE film SET titre=?, realisateur=?, anne_sortie=?, synopsis=?, path_video=?, acteurs=?, dispo=? WHERE id=?")) {
                // statement.setString(1, film.getTitre());
                // statement.setString(2, film.getRealisateur());
                // statement.setString(3, film.getGenre());
                // statement.setInt(4, film.getDateSortie());
                // statement.setString(5, film.getSynopsis());
                // statement.setString(6, film.getAffiche());
                // statement.setString(7, film.getLangueActu());
                // statement.setString(8, film.getTeaser());
                // statement.setString(9, film.getQualityVideoActu());
                // statement.setString(10, film.getLangueSousTitreActu());
                // statement.setString(11, film.getPathVideo());
                // statement.setString(12, film.getPathTeaser());
                // statement.setDouble(13, film.getDuree());
                // statement.setString(14, tempoActeurs);
                // statement.setString(15, tempoPays);
                // statement.setString(16, tempoQualites);
                // statement.setString(17, tempoLangues);
                // statement.setString(18, tempoLangueSousTitres);
                // statement.setInt(19, film.getDispo());
                // statement.setInt(20, film.getId());
                statement.setString(1, film.getTitre());
                statement.setString(2, film.getRealisateur());
                statement.setInt(3, film.getDateSortie());
                statement.setString(4, film.getSynopsis());
                statement.setString(5, film.getPathVideo());
                statement.setString(6, tempoActeurs);
                statement.setInt(7, film.getDispo());
                statement.setInt(8, film.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification du film" + film.getTitre());
            e.printStackTrace();
        }
    
        System.out.println("Modification du film " + film.getTitre() + " terminée");
    
    }

    // public void modifie(Film film, String attribut, String newval) {       
    // try {
    //         try (PreparedStatement statement = con.prepareStatement("UPDATE film SET "+attribut+"=? WHERE id=?")) {
    //             statement.setString(1, newval);
    //             statement.setInt(2, film.getId());
    //             statement.executeUpdate();
    //         }
    //     } catch (SQLException e) {
    //         System.out.println("Erreur lors de la modification de l'attribut "+attribut+" pour le film + " + film.getTitre());
    //     System.out.println("Modification de l'attribut " + attribut + " pour le film " + film.getTitre() + " terminée");
    // }
    
// }
    @Override
    public void supprime(Film film) {
        int id_film = film.getId();
        String filmTitre = film.getTitre();
    
        try {
            // Requete SQL
            String sql = "DELETE FROM film WHERE id = ?";
            try (PreparedStatement statement = con.prepareStatement(sql)) {
                // Définir la valeur du paramètre ID
                statement.setInt(1, id_film);
    
                // Exécution de la requête
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du film " + filmTitre);
        }
    
        System.out.println("Suppression du film '" + filmTitre + " terminée");
    }

}