package com.Modele;


import java.util.ArrayList;
import java.util.Set;


public class Profil {

    private Users user;
    private int id;
    private String nom;
    private ArrayList <Film> filmslikes = new ArrayList<Film>();


    public Profil() {
    }

    public Profil(Users user, String nom) {
        this.user = user;
        this.nom = nom;
        }


    public void addfilmslikes(Film film){

        if (filmslikes.contains(film) == false) {
            filmslikes.add(film);
        }
    }
    
    public void removefilmslikes(Film film){
        filmslikes.remove(film);
    }

    public void displayfilmslikes(){
        for(int i = 0; i < filmslikes.size(); i++){
            System.out.println(filmslikes.get(i).getTitre());
        }
    }



    public ArrayList <Film> getfilmslikes(){
        return filmslikes;
    }
    public void setFilmslikes(ArrayList<Film> filmslikes) {
        this.filmslikes = filmslikes;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public ArrayList <Film> getFilmslikes() {
        return filmslikes;
    }

}