package com.dc_vb.sgc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.service.UsuarioService;

public class CadastroUsuarioController {

    @FXML private Button btnCadastrar;
    @FXML private TextField nomeInput;
    @FXML private TextField cpfInput;
    @FXML private TextField predioInput;
    @FXML private TextField salaInput;
    @FXML private TextField telefoneInput;
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
                    null,
                    null
            );

            usuarioService.cadastrarUsuario(novo);
            mensagemLabel.setText("Usuário cadastrado com ID: " + novo.getIdUsuario());
            mensagemLabel.setStyle("-fx-text-fill: green;");

        } catch (Exception e) {
            mensagemLabel.setText("Erro: " + e.getMessage());
            mensagemLabel.setStyle("-fx-text-fill: red;");
        }
    }
}