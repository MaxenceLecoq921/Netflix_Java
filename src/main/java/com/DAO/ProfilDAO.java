package com.DAO;

import java.util.ArrayList;
import com.Modele.Profil;

public interface ProfilDAO {
    ArrayList <Profil> recupere(); //à coder quand on en aura besoin
    void ajoute(Profil profil);
    void update(Profil profil);
    void supprime(Profil profil);
}
