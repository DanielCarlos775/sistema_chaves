package com.dc_vb.sgc.application;

import com.dc_vb.sgc.dao.UsuarioDAO;
import com.dc_vb.sgc.model.Usuario;
import com.dc_vb.sgc.util.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.setPrimaryStage(stage);
        SceneManager.loadScene("Login");
//        try {
//            Parent parent = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
//            Scene scene = new Scene(parent);
//
//            // Define caraterísticas da janela
//            stage.setTitle("Sistema de Gerenciamento de Chaves");
//            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Favicon.png")));
//
//            scene.getStylesheets().add(getClass().getResource("/css/Login.css").toExternalForm());
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*public class MainApp {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        // Inserir exemplo
        Usuario novo = new Usuario(0, "Daniel", "daniel4@email.com", "123456", "COORDENADOR");
        dao.insert(novo);

        // Listar todos
        for (Usuario u : dao.findAll()) {
            System.out.println(u.getId() + " - " + u.getNome() + " - " + u.getEmail());
        }
    }
}*/
