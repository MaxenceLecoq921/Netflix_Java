package com.DAO;

import java.util.ArrayList;
import com.Modele.Users;

public interface UsersDAO {
    ArrayList <Users> recupere();
    void ajoute(Users user);
    void update(Users user);
    void supprime(Users user);
    void envoieMail(String email);
}


