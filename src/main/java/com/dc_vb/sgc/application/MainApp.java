package com.dc_vb.sgc.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Scene scene = new Scene(parent);

//            Define caraterísticas da janela
            stage.setTitle("Sistema de Gerenciamento de Chaves");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Favicon.png")));

            scene.getStylesheets().add(getClass().getResource("/css/Login.css").toExternalForm());
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
