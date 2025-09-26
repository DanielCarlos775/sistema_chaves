package com.dc_vb.sgc.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SceneManager {

    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void loadScene(String title)  {

        // Se não tiver um stage, retorna
        if (primaryStage == null) {
            System.err.println("Erro: Primary Stage não foi definido. Chame setPrimaryStage(stage) no seu Main.");
            return;
        }

        try {
            // Busca qual o container raiz do arquivo selecionado e referencia como Parent
            // Ex: [VBox] ou [AnchorPane] do arquivo /view/Login.fxml
            Parent parent = FXMLLoader.load(SceneManager.class.getResource("/view/" + title + ".fxml"));

            // Cria uma cena a partir do Parent
            Scene scene = new Scene(parent);

            // Cria um caminho para a folha de estilo com mesmo nome e verifica se existe
            URL styleURL = SceneManager.class.getResource("/css/" + title + ".css");
            if (styleURL != null) {

                // Se encontrar uma folha de estilo, carrega ela
                scene.getStylesheets().add(styleURL.toExternalForm());
            } else {

                // Se não encontrar, carrega uma padrão
                scene.getStylesheets().add(SceneManager.class.getResource("/css/Global.css").toExternalForm());
            }

            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.centerOnScreen();
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Erro ao carregar a cena: " + title + ".fxml");
            e.printStackTrace();
        }
    }
}
