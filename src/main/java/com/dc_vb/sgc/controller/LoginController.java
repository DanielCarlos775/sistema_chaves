package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.util.AlertUtil;
import com.dc_vb.sgc.util.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class LoginController {

    @FXML private TextField usernameInput;

    @FXML private PasswordField passwordInput;

    @FXML private Text errorText;


    @FXML
    void handleLogin(ActionEvent event) {
        System.out.printf(event + "\n");
        String email = usernameInput.getText();
        String senha = passwordInput.getText();

        try {
            if (email.isEmpty() || senha.isEmpty()) {
                AlertUtil.showWarning("Campos obrigatórios", "Por favor, preencha email e senha.");
            } else {
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuario =  usuarioDAO.findByEmailSenha(email, senha);

                if (usuario != null) {
                    SceneManager.loadScene("MainLayout");
                    errorText.setText("");
                } else {
                    AlertUtil.showError("Falha no Login", "Por favor, informe os dados corretos.");
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
