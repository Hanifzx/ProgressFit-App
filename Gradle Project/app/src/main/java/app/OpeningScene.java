package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class OpeningScene {

    private Scene scene;
    private Main mainApp;

    public OpeningScene(Main mainApp, int width, int height) {
        this.mainApp = mainApp;

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #1e272e;");

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setMaxWidth(400);
        contentBox.setPadding(new Insets(40));
        contentBox.setStyle("-fx-background-color: #2d3436; -fx-background-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");


        Image logoImage = new Image(getClass().getResourceAsStream("/logo.jpg"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(100);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);

        Circle clip = new Circle(50);

        clip.setCenterX(50);
        clip.setCenterY(50);

        logoView.setClip(clip);


        Label titleLabel = new Label("PROGRESS FIT");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.WHITE);

        Label descLabel = new Label("Latihan di rumah dengan mudah dan efektif");
        descLabel.setTextFill(Color.web("#a0aec0"));

        Button startButton = new Button("MULAI");
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(50);
        startButton.setFont(Font.font("System", FontWeight.BOLD, 16));
        startButton.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                "-fx-text-fill: white; " +
                "-fx-background-radius: 5px; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");

        startButton.setOnMouseEntered(e -> {
            startButton.setStyle("-fx-background-color: linear-gradient(to right, #1a3a4a, #3d3560); " +
                    "-fx-text-fill: white; " +
                    "-fx-background-radius: 5px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 0); " +
                    "-fx-translate-y: -1px;");
        });

        startButton.setOnMouseExited(e -> {
            startButton.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                    "-fx-text-fill: white; " +
                    "-fx-background-radius: 5px; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");
        });

        startButton.setOnAction(e -> mainApp.showUserDataScene());

        Label footerLabel = new Label("© 2025 Group 16 - Home Workout App | Beta Version");
        footerLabel.setTextFill(Color.web("#636e72"));
        footerLabel.setFont(Font.font("System", 12));

        contentBox.getChildren().addAll(logoView, titleLabel, descLabel, startButton);

        VBox rootContent = new VBox(20);
        rootContent.setAlignment(Pos.CENTER);
        rootContent.getChildren().addAll(contentBox, footerLabel);

        root.getChildren().add(rootContent);

        scene = new Scene(root, width, height);
    }

    public Scene getScene() {
        return scene;
    }
}