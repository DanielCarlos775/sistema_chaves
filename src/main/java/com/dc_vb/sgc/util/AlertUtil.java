package com.dc_vb.sgc.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertUtil {

    //Alerta de erro (ex: Login Inválido)
    public static void showError(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); //remove cabeçalho
        alert.setContentText(message);
        alert.showAndWait();
    }

    //Alerta de informação (ex: Cadastro Concluído)
    public static void showInfo(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null); //remove cabeçalho
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Alerta de aviso (ex: campos obrigatórios)
    public static void showWarning(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null); //remove cabeçalho
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Alerta de confirmação (retorna se usuário clicou em OK)
    public static boolean showConfirmation(String title, String message) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        return alert.showAndWait()
                .filter(button -> button == javafx.scene.control.ButtonType.OK)
                .isPresent();
    }
}
