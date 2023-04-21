package com.Controller;
import com.DAO.*;
import com.Modele.Users;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class InscriptionController2 {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField paysField;

    @FXML
    private void handleInscriptionButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        int role = 0;
        String pays = paysField.getText();

        Users user = new Users(username, password, email, role, pays);

        UsersDAOImpl dao = new UsersDAOImpl();
        dao.ajoute(user);

    }
}
