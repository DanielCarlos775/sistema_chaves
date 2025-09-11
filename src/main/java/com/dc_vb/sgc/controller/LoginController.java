package com.dc_vb.sgc.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginController {

    @FXML
    private VBox loginContainer;

    @FXML
    private VBox root;

    @FXML
    private TextField usernameInput;

    @FXML
    PasswordField passwordInput;

    @FXML
    private Button loginButton;

    @FXML
    void handleLogin(ActionEvent event) {
        System.out.printf("Fez Login\nUsuário: %s\nSenha: %s", usernameInput.getText(), passwordInput.getText());
    }

    @FXML
    void closeApp(ActionEvent event) {
        javafx.application.Platform.exit();
    }
}
