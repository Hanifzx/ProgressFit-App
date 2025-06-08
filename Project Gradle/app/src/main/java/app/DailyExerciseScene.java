package app;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DailyExerciseScene {
    private Scene scene;
    private Main mainApp;
    private User user;

    public DailyExerciseScene(Main mainApp, int width, int height, User user) {
        this.mainApp = mainApp;
        this.user = user;
        
        // Create root container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        // Create header
        HBox header = new HBox();
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(10);
        header.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376);");

        Button backButton = new Button("Kembali");
        backButton.setStyle("-fx-background-color: #ff6b6b; -fx-text-fill: #fff;");
        backButton.setOnAction(e -> mainApp.showMainMenuScene());
        header.getChildren().add(backButton);
        
        Label headerTitle = new Label("Latihan Harian");
        headerTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        headerTitle.setTextFill(Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        // User info in header
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", 13));

        userNameLabel.setTextFill(Color.WHITE);
        
        Circle userIcon = new Circle(15);
        userIcon.setFill(Color.web("#ff6b6b"));
        
        header.getChildren().addAll(backButton, headerTitle, spacer, userNameLabel, userIcon);

        scene = new Scene(root, width, height);
    }

    public Scene getScene() {
        return scene;
    }
}
