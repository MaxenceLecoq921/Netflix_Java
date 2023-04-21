package com.DAO;

// import com.Modele.Film;
import com.Modele.*;
import java.sql.*;
import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.Map;

public class UsersDAOImpl implements UsersDAO {

    private Connection con;

    public UsersDAOImpl() {
        try {
            String url = "jdbc:mysql://localhost:3306/netflix_java";
            String user = "root";
            String password = "";

            this.con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Erreur de connexion à la base de données (UserDAOImpl.java)");
        }
    }

    @Override
    public ArrayList<Users> recupere() {
        ArrayList<Users> listeUsers = new ArrayList<>();
    
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM users");
            ResultSet resultat = statement.executeQuery();
    
            while (resultat.next()) {
                Users user = new Users();
                user.setId(resultat.getInt("id"));
                user.setUsername(resultat.getString("username"));
                user.setPassword(resultat.getString("password"));
                user.setEmail(resultat.getString("email"));
                user.setRole(resultat.getInt("role"));
                user.setPays(resultat.getString("pays"));
    
            // Récupérer les profils de l'utilisateur et les stocker dans un tableau
            String profilsStr = resultat.getString("profils");
            ArrayList<Profil> profils = new ArrayList<>();
            if (profilsStr != null && !profilsStr.isEmpty()) {
                String[] profilsArray = profilsStr.split(",");
                for (String profilNom : profilsArray) {
                    Profil profil = new Profil();
                    profil.setNom(profilNom);
                    profils.add(profil);
                }
            }
            user.setProfils(profils);
        
                // Vous pouvez ajouter des méthodes pour gérer les autres attributs de la classe Users (filmLike, filmDispo, historiqueFilm, filmVu, noteFilm, filmEnCours)
    
                listeUsers.add(user);
            }
    
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des utilisateurs");
        }
    
        System.out.println("Récupération des utilisateurs terminée");
    
        return listeUsers;
    }
    
    @Override
    public void ajoute(Users user) {
        try {
            PreparedStatement statement = con.prepareStatement("INSERT INTO users (username, password, email, role, pays) VALUES (?, ?, ?, ?, ?)");
    
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getRole());
            statement.setString(5, user.getPays());
    
            statement.executeUpdate();
            
            System.out.println("L'utilisateur " + user.getUsername() + " a été ajouté avec succès");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur " + user.getUsername());
        }
    }
    
    @Override
    public void update(Users user) {
        try {

            ArrayList<String> profilsnoms = new ArrayList<>();
            for (int i=0; i<user.getProfils().size(); i++) {
                profilsnoms.add(user.getProfils().get(i).getNom());
            }
            String tempoProfils = String.join(",", profilsnoms);
            try (PreparedStatement statement = con.prepareStatement("UPDATE users SET username=?, password=?, email=?, role=?, pays=? , profils=? WHERE id=?")) {
                statement.setString(1, user.getUsername());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getEmail());
                statement.setInt(4, user.getRole());
                statement.setString(5, user.getPays());
                statement.setString(6, tempoProfils);
                statement.setInt(7, user.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'update de l'utilisateur " + user.getUsername());
        }
    
        System.out.println("Update de l'utilisateur " + user.getUsername() + " terminée");
    }
    
    

    @Override
    public void supprime(Users user) {

        String username = user.getUsername();
        try {
            PreparedStatement statement = con.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de l'utilisateur " + username);
        }

        System.out.println("Suppression de l'utilisateur '" + username + "' terminée");
    }

    @Override
    public void envoieMail(String email) {
        System.out.println("Envoi d'un mail à l'utilisateur " + email);
    }
}
