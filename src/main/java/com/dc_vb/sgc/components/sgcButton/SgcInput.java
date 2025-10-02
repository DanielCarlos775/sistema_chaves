package com.dc_vb.sgc.components.sgcButton;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class SgcInput extends VBox {

    private final SgcLabel componentLabel;
    private final SgcTextField componentTextField;
    private final SgcText componentErrorText;

    // Expõe o TextField para que ele possa ser alterado remotamente
    public TextField getTextField() {
        return componentTextField;
    }

    public SgcInput() {

        // 1. Inicializa os componentes internos
        this.componentLabel = new SgcLabel();
        this.componentTextField = new SgcTextField();
        this.componentErrorText = new SgcText();

        // Esconde a mensagem de erro inicialmente
        this.componentErrorText.setText("");
        this.componentErrorText.setVisible(false);

        // 2. Adiciona os componentes nessa instancia de uma VBox (this.getChildren())
        // A ordem determina o layout (Label, TextField, ErrorText)
        this.getChildren().addAll(componentLabel, componentTextField, componentErrorText);

        // 3. Aplica a classe que segura os estilos do pai
        this.getStyleClass().add("sgc-input-container");

        String styleSheetPath = "/css/components/SgcInput.css";
        try {
            String cssPath = getClass().getResource(styleSheetPath).toExternalForm();
            this.getStylesheets().add(cssPath);
        } catch (NullPointerException e) {
            System.err.println("ERROR: Could not find stylesheet resource at path: " + styleSheetPath);
        }
    }

    // --- Métodos públicos para customização ---
    public void setLabelText(String text) {
        this.componentLabel.setText(text);
    }

    public void setError(String errorMessage) {
        this.componentErrorText.setText(errorMessage);
        this.componentErrorText.setVisible(true);
    }

    public void clearError() {
        this.componentErrorText.setText("");
        this.componentErrorText.setVisible(false);
    }
}

class SgcTextField extends TextField {

    public SgcTextField() {
        super();
        this.getStyleClass().add("sgc-text-field");
    }
}

class SgcLabel extends Label {
    SgcLabel() {
        super("Label");
        this.getStyleClass().add("sgc-label");
    }
}

class SgcText extends Text {
    SgcText() {
        super("");
        this.getStyleClass().add("sgc-text");
    }
}
