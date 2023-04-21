package com.DAO;

import java.util.ArrayList;
import com.Modele.Film;

public interface FilmDAO {
    ArrayList <Film> recupere();
    void ajoute(Film Film);
    void update(Film film); // Tu mets le film à modifier, la colonne que tu veux modifier et la nouvelle valeur que tu veux lui donner
    void supprime(Film film);
}
