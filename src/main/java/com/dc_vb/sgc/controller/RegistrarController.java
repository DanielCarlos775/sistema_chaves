package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/*public class RegistrarController {

    @FXML
    private TextField emailInput;

    @FXML
    private TextField nameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    void handleRegister(ActionEvent event) {
        System.out.printf(event + "\n");
        String nome = nameInput.getText();
        String email = emailInput.getText();
        String senha = passwordInput.getText();
        System.out.printf("Tentou fazer login com %s e %s\n", email, senha);

        String erro;
        try {
            if (email.isEmpty()) {
                erro = "O seu email está vazio\n";
                System.out.printf(erro);
            } else if (senha.isEmpty()) {
                erro = "A sua senha está vazia\n";
                System.out.printf(erro);
            } else {

                Usuario usuario = new Usuario();
                usuario.setEmail(email);
                usuario.setNome(nome);
                usuario.setSenha(senha);
                usuario.setTipoUsuario("ADM");

                UsuarioDAO usuarioDAO = new UsuarioDAO();
                System.out.printf("DAO: %s!\n", usuarioDAO);
                AuthService authService = new AuthService(usuarioDAO);
                System.out.printf("AuthService: %s!\n", authService);
                authService.registrarUsuario(usuario);
                System.out.println("Usuário cadastrado!");
               }
        } catch (SQLException e) {
            System.out.printf(String.valueOf(e));
            throw new RuntimeException(e);
        } finally {
            erro = "";
        }
    }

    @FXML
    void handleGoToLogin(ActionEvent event) {
        SceneManager.loadScene("Login");
    }
}*/
