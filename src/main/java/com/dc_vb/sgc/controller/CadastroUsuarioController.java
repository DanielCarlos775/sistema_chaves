package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.service.UsuarioService;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroUsuarioController {

    @FXML private TextField nomeInput;
    @FXML private TextField emailInput;
    @FXML private PasswordField senhaInput;
    @FXML private ComboBox<String> tipoUsuarioCombo;
    @FXML private Label mensagemLabel;

    private final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        // Popular opções de tipo
        tipoUsuarioCombo.getItems().addAll("ADM", "COORDENADOR", "GUARITA", "ADMINISTRATIVO");
    }

    @FXML
    private void handleCadastrar() {
        try {
            Usuario novo = new Usuario(
                    nomeInput.getText(),
                    emailInput.getText(),
                    senhaInput.getText(),
                    tipoUsuarioCombo.getValue(),
                    null, // id_predio ainda não tratado na tela
                    null  // id_sala ainda não tratado
            );

            usuarioService.cadastrarUsuario(novo);
            mensagemLabel.setText("Usuário cadastrado com ID: " + novo.getIdUsuario());

        } catch (Exception e) {
            mensagemLabel.setText("Erro: " + e.getMessage());
        }
    }
}