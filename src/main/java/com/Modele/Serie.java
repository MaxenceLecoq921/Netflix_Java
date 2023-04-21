package com.Modele;
import java.util.*;

public class Serie {

    private String titre;
    private ArrayList<Film> episodes = new ArrayList<Film>();

    public Serie() {
    }

    public Serie(String titre, ArrayList<Film> episodes) {
        this.titre = titre;
        this.episodes = episodes;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public ArrayList<Film> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Film> episodes) {
        this.episodes = episodes;
    }
}
