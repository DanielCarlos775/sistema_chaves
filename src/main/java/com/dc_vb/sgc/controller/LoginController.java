package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.service.AuthService;
import com.dc_vb.sgc.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameInput;

    @FXML private PasswordField passwordInput;

    @FXML private Text errorText;

    @FXML void initialize() {
        errorText.setText("");
    }

    @FXML
    void handleLogin(ActionEvent event) {
        System.out.printf(event + "\n");
        String email = usernameInput.getText();
        String senha = passwordInput.getText();

        try {
            if (email.isEmpty()) {
                errorText.setText("O seu email está vazio");

            } else if (senha.isEmpty()) {
                errorText.setText("A sua senha está vazia");
            } else {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                AuthService authService = new AuthService(usuarioDAO);
                Usuario usuario =  authService.login(email, senha);
                if (usuario != null) {
                    SceneManager.loadScene("MainLayout");
                    errorText.setText("");
                } else {
                    errorText.setText("Os dados são inválidos");
                }

            }
        } catch (SQLException e) {
            System.out.printf(String.valueOf(e));
            throw new RuntimeException(e);
        } finally {
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
}
