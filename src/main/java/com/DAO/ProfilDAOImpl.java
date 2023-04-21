
package com.DAO;

import com.Modele.*;
import java.sql.*;
import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

public class ProfilDAOImpl implements ProfilDAO {

    private Connection con;

    public ProfilDAOImpl() {
        try {
            String url = "jdbc:mysql://localhost:3306/netflix_java";
            String user = "root";
            String password = "";

            this.con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données (ProfilDAOImpl.java)");
        }
    }

    @Override
    public ArrayList<Profil> recupere() {
        ArrayList<Profil> listeprofils = new ArrayList<>();
        return listeprofils;
    }
    
    @Override
    public void ajoute(Profil profil) {
        String tempoFilmslikes = filmListToString(profil.getFilmslikes());

        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO profils (id, user_id, nom, filmslikes) VALUES (?, ?, ?, ?)");
            statement.setInt(1, profil.getId());
            statement.setInt(2, profil.getUser().getId());
            statement.setString(3, profil.getNom());
            statement.setString(4, tempoFilmslikes);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du profil");
        }
    }

    @Override
    public void update(Profil profil) {
        String tempoFilmslikes = filmListToString(profil.getFilmslikes());


        try {
            PreparedStatement statement = con.prepareStatement("UPDATE profils SET nom=?, filmslikes=? WHERE nom=?");
            statement.setString(1, profil.getNom());
            statement.setString(2, tempoFilmslikes);
            statement.setString(3, profil.getNom());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour du profil");
        }
    }

    @Override
    public void supprime(Profil profil) {
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM profils WHERE id=?");
            statement.setInt(1, profil.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression du profil");
        }
    }


    private String filmListToString(ArrayList<Film> films) {
        StringBuilder sb = new StringBuilder();

        for (Film film : films) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(film.getTitre());
        }

        return sb.toString();
    }
}



