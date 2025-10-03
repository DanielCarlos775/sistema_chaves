package com.dc_vb.sgc.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    @FXML
    VBox contentPane;

    @FXML private void loadDashboard() {
        loadComponent("Dashboard");;
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
