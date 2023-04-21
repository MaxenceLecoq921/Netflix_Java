package com.Modele;

import java.util.ArrayList;

public class Film{
    private int id;
    private String titre;
    private String realisateur;
    private String genre;
    private int annee;
    private double duree;
    private String synopsis;
    private String pathAffiche;
    private String acteurs;// car film present pas dans tout les pays
    private ArrayList<String> pays = new ArrayList<String>();// car film present pas dans tout les pays
    private String langueActu;
    private String teaser;
    private String pathVideo;
    private String pathTeaser;
    // changer base de données pour mettre un tableau de string
    private String qualityVideoActu;
    private String langueSousTitreActu;
    private ArrayList<String> langue = new ArrayList<String>();
    private ArrayList<String> qualityVideo = new ArrayList<String>();
    private ArrayList<String> langueSousTitre = new ArrayList<String>();
    private int dispo;

    public int getDispo() {
        return dispo;
    }

    public void setDispo(int newdispo) {
        this.dispo = newdispo;
    }

    public void setLangue(String langue){
        this.langue.add(langue);
    }

    public ArrayList<String> getLangue(){
        return langue;
    }

    public String getLangueSousTitreActu() {
        return langueSousTitreActu;
    }

    public void setLangueSousTitreActu(String langueSousTitreActu) {
        this.langueSousTitreActu = langueSousTitreActu;
    }

    public String getQualityVideoActu() {
        return qualityVideoActu;
    }

    public void setQualityVideoActu(String qualityVideoActu) {
        this.qualityVideoActu = qualityVideoActu;
    }

    public void setQualityVideo(String qualityVideo){
        this.qualityVideo.add(qualityVideo);
    }

    public void setLangueSousTitre(String langueSousTitre){
        this.langueSousTitre.add(langueSousTitre);
    }
    
    public ArrayList<String> getLangueSousTitre(){
        return langueSousTitre;
    }

    public ArrayList<String> getQualityVideo(){
        return qualityVideo;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Film() {
    }

    public Film(String titre, String realisateur, String genre, int annee, double duree, String synopsis, String pathAffiche, String acteurs, String langueActu, String teaser, String qualityVideo, String langueSousTitre, String pathVideo, String pathTeaser, String pays, String langueSousTitreActu, String qualityVideoActu, String langue, int dispo) {
        this.titre = titre;
        this.realisateur = realisateur;
        this.genre = genre;
        this.annee = annee;
        this.duree = duree;
        this.synopsis = synopsis;
        this.pathAffiche = pathAffiche;
        this.acteurs = acteurs;
        this.langueActu = langueActu;
        this.teaser = teaser;
        this.qualityVideo.add(qualityVideo);
        this.langueSousTitre.add(langueSousTitre);
        this.pathVideo = pathVideo;
        this.pathTeaser = pathTeaser;
        this.pays.add(pays);
        this.langueSousTitreActu = langueSousTitreActu;
        this.qualityVideoActu = qualityVideoActu;
        this.langue.add(langue);
        this.dispo = dispo;
    }

    public void displayFilm(){
        System.out.println("Titre: " + titre);
        System.out.println("Realisateur: " + realisateur);
        System.out.println("Genre: " + genre);
        System.out.println("Date de sortie: " + annee);
        System.out.println("Duree: " + duree);
        System.out.println("Synopsis: " + synopsis);
        System.out.println("Affiche: " + pathAffiche);
        System.out.println("Acteurs: " + acteurs);
        System.out.println("Pays: " + pays);
        System.out.println("Langue: " + langue);
        System.out.println("Teaser: " + teaser);
        System.out.println("Qualite de la video: " + qualityVideo);
        System.out.println("Langue des sous-titre: " + langueSousTitre);
        System.out.println("Path de la video: " + pathVideo);
        System.out.println("Path du teaser: " + pathTeaser);
        System.out.println("Langue actuelle: " + langueActu);
        System.out.println("Langue des sous-titre actuelle: " + langueSousTitreActu);
        System.out.println("Qualite de la video actuelle: " + qualityVideoActu);
        System.out.println("");
        System.out.println("dispo : "+ dispo);
    }
    
    public void setPathTeaser(String pathTeaser) {
        this.pathTeaser = pathTeaser;
    }

    public String getPathTeaser() {
        return pathTeaser;
    }

    public void setPathVideo(String pathVideo) {
        this.pathVideo = pathVideo;
    }

    public String getPathVideo() {
        return pathVideo;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDateSortie() {
        return annee;
    }

    public void setDateSortie(int annee) {
        this.annee = annee;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getAffiche() {
        return pathAffiche;
    }

    public void setAffiche(String pathAffiche) {
        this.pathAffiche = pathAffiche;
    }

    public String getActeurs() {
        return acteurs;
    }

    public void setActeurs(String acteurs) {
        this.acteurs = acteurs;
    }

    public ArrayList<String> getPays() {
        return pays;
    }

    public void addPays(String pays) {
        this.pays.add(pays);
    }

    public String getLangueActu() {
        return langueActu;
    }

    public void setLangueActu(String langueActu) {
        this.langueActu = langueActu;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

}

