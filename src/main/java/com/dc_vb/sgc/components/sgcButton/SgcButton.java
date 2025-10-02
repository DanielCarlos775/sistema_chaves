package com.dc_vb.sgc.components.sgcButton;

import com.dc_vb.sgc.util.SceneManager;
import javafx.scene.control.Button;

public class SgcButton extends Button {

    public SgcButton() {
        super("Button");

        this.getStyleClass().add("sgc-button");

            String styleSheetPath = "/css/components/SgcButton.css";
        try {
            String cssPath = getClass().getResource(styleSheetPath).toExternalForm();
            this.getStylesheets().add(cssPath);
        } catch (NullPointerException e) {
            System.err.println("ERROR: Could not find stylesheet resource at path: " + styleSheetPath);
        }
    }
}