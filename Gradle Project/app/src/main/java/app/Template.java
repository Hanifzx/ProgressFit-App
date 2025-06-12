package app;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public abstract class Template {
    protected Button createButtonCard(String buttonText) {
        Button button = new Button(buttonText);
        button.setPrefWidth(100);
        button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 14px; " +
                "-fx-background-radius: 5px;");
        
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: linear-gradient(to right, #1a3a4a, #3d3560); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 6px 14px; " +
                    "-fx-background-radius: 5px;");
        });
        
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 6px 14px; " +
                    "-fx-background-radius: 5px;");
        });
        
        return button;
    }

    protected abstract HBox createHeader();
}
