package com.Modele;

// import java.util.*;

import java.util.ArrayList;

public class Users {
    private int id;
    private String username;
    private String password;
    private String email;
    private int role;
    private String pays;
    private ArrayList <Film> filmsdispos = new ArrayList<Film>();
    private ArrayList <Profil> profils = new ArrayList<Profil>();



    public Users() {
    }

    public Users(String username, String password, String email, int role, String pays) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.pays = pays;
    }

    // CES DEUX FONCTIONS SONT lIER SOUVENT QUAND ON SUPPRIMME DANS UNE ON SUPPRIMME DANS L'AUTRE
    // faire une fonction desactiver la reprise de film pour faire ca on supprime tout les film vu
    // faire une fonction pour supprimmer l'historique ou certains film de l'historique 

    public Profil addProfil(String nom) {
        Profil newProfil = new Profil();
        newProfil.setNom(nom);
        // Configurez les autres attributs du profil ici, si nécessaire
        profils.add(newProfil);
        return newProfil;
    }
    


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }



    public void displayUser(){
        System.out.println("Username: " + username);
        System.out.println("password: " + password);
        System.out.println("email: " + email);
        System.out.println("role: " + role);
        System.out.println("pays: " + pays);
    }

    
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public ArrayList<Film> getFilmsdispos() {
        return filmsdispos;
    }

    public void setFilmsdispos(ArrayList<Film> films) {
        this.filmsdispos = films;
    }

    public ArrayList<Profil> getProfils() {
        return profils;
    }

    public void setProfils(ArrayList<Profil> profils) {
        this.profils = profils;
    }

    public void addAdmin(Users user){ 
        user.setRole(1);
    }

    public void removeAdmin(Users user){
        user.setRole(0);
    }


    // //Pays langues
    // public void addFilmDispo(Film film){
    //     filmDispo.add(film);
    // }

    // public void removeFilmDispo(Film film){
    //     filmDispo.remove(film);
    // }

    // public void displayFilmDispo(){
    //     for(int i = 0; i < filmDispo.size(); i++){
    //         System.out.println("\n");
    //         filmDispo.get(i).displayFilm();
    //     }
    // }


    // public boolean verifAccessFilm(ArrayList<String> filmpays, Film film){
    //     if (filmpays.contains(this.pays)){
    //         addFilmDispo(film);
    //         return true;
    //     }
    //     else{
    //         return false;
    //     }
    // }

    // //////

    
}
