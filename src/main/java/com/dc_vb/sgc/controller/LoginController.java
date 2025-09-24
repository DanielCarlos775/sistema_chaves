package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.service.AuthService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameInput;

    @FXML
    PasswordField passwordInput;


    String erro = "";

    @FXML
    void handleLogin(ActionEvent event) {
        System.out.printf(event + "\n");
        String email = usernameInput.getText();
        String senha = passwordInput.getText();
        System.out.printf("Tentou fazer login com %s e %s\n", email, senha);

        try {
            if (email.isEmpty()) {
                erro = "O seu email está vazio\n";
                System.out.printf(erro);
            } else if (senha.isEmpty()) {
                erro = "A sua senha está vazia\n";
                System.out.printf(erro);
            } else {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                System.out.printf("DAO: %s!\n", usuarioDAO);
                AuthService authService = new AuthService(usuarioDAO);
                System.out.printf("AuthService: %s!\n", authService);
                Usuario usuario =  authService.login(email, senha);
                if (usuario != null) {
                    System.out.printf("Bem vindo, %s!\n", usuario.getNome());
                } else {
                    erro = "Os dados são inválidos.";
                    System.out.printf(erro);
                }

            }
        } catch (SQLException e) {
            System.out.printf(String.valueOf(e));
            throw new RuntimeException(e);
        } finally {
            erro = "";
        }
    }

    @FXML
    void closeApp(ActionEvent event) {
        System.out.printf(event + "\n");
        javafx.application.Platform.exit();
    }
}
