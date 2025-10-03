package com.dc_vb.sgc.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MainController {

    @FXML private Button CadastroButton;
    @FXML private HBox content;
    @FXML private VBox contentPane;
    @FXML private HBox header;
    @FXML private StackPane root;
    @FXML private VBox sidebar;
    @FXML private HBox statusBar;
    @FXML private Label systemStatus;

    @FXML
    private void GotoCadastrarUsuario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dc_vc/sgc/view/CadastroUsuario.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Cadastro de Usuário");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadComponent(String componentName) {
        try {
            // Cria um caminho para o arquivo do componente
            String componentPath = "/components/" + componentName + ".fxml";

            // Cria uma node que vai receber o arquivo
            Node newComponent = FXMLLoader.load(getClass().getResource(componentPath));

            // Se o componente existe
            if (contentPane != null && newComponent != null) {
                // Usa o setAll() para substituir o conteúdo principal
                contentPane.getChildren().setAll(newComponent);
            }
        } catch (IOException e) {
            System.err.println("Falha ao carregar o componente FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
