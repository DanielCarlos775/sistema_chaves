package com.dc_vb.sgc.controller;

import com.dc_vb.sgc.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class CadastroGeralController {

    @FXML void CadastrarUsuario() {
        SceneManager.loadScene("CadastroUsuario");
    }
}
