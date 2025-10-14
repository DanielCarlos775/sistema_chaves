package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.service.AuthService;
import com.dc_vb.sgc.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private Label emailLabel;
    @FXML
    private TextField emailInput;
    @FXML
    private Text errorTextEmail;

    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Text errorTextPassword;


    @FXML
    void initialize() {
        removeError();
    }

    @FXML
    void handleLogin(ActionEvent event) {
        System.out.printf(event + "\n");
        String email = emailInput.getText();
        String password = passwordInput.getText();
        removeError();

        try {
            if (email.isEmpty()) {
                setError("email");
            } else if (password.isEmpty()) {
                setError("password");
            } else {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                AuthService authService = new AuthService(usuarioDAO);
                Usuario usuario = authService.login(email, password);
                if (usuario != null) {
                    SceneManager.loadScene("MainLayout");
                    removeError();
                } else {
                    setError("email");
                    setError("password");
                    errorTextEmail.setText("Os dados são inválidos");
                    errorTextPassword.setText("Os dados são inválidos");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void closeApp(ActionEvent event) {
        System.out.printf(event + "\n");
        javafx.application.Platform.exit();
    }

    @FXML
    void handleGoToRegister(ActionEvent event) {
        System.out.printf(event + "\n");
        SceneManager.loadScene("Registrar");
    }

    void setError(String source) {
        if (source.equals("email")) {
            errorTextEmail.setText("O seu email está vazio");
            emailLabel.getStyleClass().add("error-text");
            emailInput.getStyleClass().add("error-border");
        } else if (source.equals("password")){
            errorTextPassword.setText("A sua senha está vazia");
            passwordLabel.getStyleClass().add("error-text");
            passwordInput.getStyleClass().add("error-border");
        }
    }

    void removeError() {
        errorTextEmail.setText("");
        errorTextPassword.setText("");
        emailInput.getStyleClass().remove("error-border");
        emailLabel.getStyleClass().remove("error-text");
        passwordInput.getStyleClass().remove("error-border");
        passwordLabel.getStyleClass().remove("error-text");
    }
}
