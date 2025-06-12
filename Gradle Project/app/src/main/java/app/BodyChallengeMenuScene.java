package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class BodyChallengeMenuScene extends Template {
    private Scene scene;
    private Main mainApp;
    private User user;

    public BodyChallengeMenuScene(Main mainApp, int width, int height, User user) {
        this.mainApp = mainApp;
        this.user = user;
        
        // Create root container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        // Create header
        HBox header = createHeader();
        
        // Create main content
        VBox mainContent = createMainContent();
        
        // Add components to root
        root.setTop(header);
        root.setCenter(mainContent);
        
        // Create scene with root
        scene = new Scene(root, width, height);
    }
    
    @Override
    protected HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(10);
        header.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376);");

        Button backButton = createButtonCard("Kembali");
        backButton.setOnAction(e -> mainApp.showMainMenuScene());
        
        Label headerTitle = new Label("Tantangan Fokus Tubuh");
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
        return header;
    }
    
    private VBox createMainContent() {
        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(0, 20, 0, 20));
        contentBox.setAlignment(Pos.CENTER);
        
        // Welcome message
        Label welcomeLabel = new Label("Pilih Bagian Tubuh yang Ingin Anda Fokuskan");
        welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        welcomeLabel.setTextFill(Color.web("#dfe6e9"));
        
        // Create body challenge program buttons
        VBox programMenu = new VBox(15); // 15 spacing between items
        programMenu.setAlignment(Pos.CENTER); // Center alignment
        programMenu.setMaxWidth(400);
        
        // Upper body program
        VBox upperBodyCard = createBodyChallengeCard(
            "Upper Body Challenge",
            "Fokus latihan untuk tubuh bagian atas",
            "Mulai Challenge",
            () -> showBodyChallenge("upper_body")
        );
        
        // Lower body program
        VBox lowerBodyCard = createBodyChallengeCard(
            "Lower Body Challenge",
            "Fokus latihan untuk tubuh bagian bawah",
            "Mulai Challenge",
            () -> showBodyChallenge("lower_body")
        );
        
        // Set consistent width for all cards
        upperBodyCard.setMaxWidth(350);
        upperBodyCard.setPrefWidth(350);
        lowerBodyCard.setMaxWidth(350);
        lowerBodyCard.setPrefWidth(350);
        
        // Add cards to VBox
        programMenu.getChildren().addAll(upperBodyCard, lowerBodyCard);
        
        contentBox.getChildren().addAll(welcomeLabel, programMenu);
        return contentBox;
    }

    private VBox createBodyChallengeCard(String title, String description, String buttonText, Runnable onAction) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(5));
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(150); 
        card.setMinHeight(150);  
        card.setStyle("-fx-background-color: #2d3436; " +
                "-fx-background-radius: 5; " +
                "-fx-border-color: transparent; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 5; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");
        
        // Title
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#dfe6e9"));
        
        // Description
        Label descLabel = new Label(description);
        descLabel.setTextFill(Color.web("#a0aec0"));
        descLabel.setWrapText(true);
        descLabel.setAlignment(Pos.CENTER);
        
        // Button
        Button button = new Button(buttonText);
        button.setPrefWidth(150);
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
        
        // Set button action
        button.setOnAction(e -> onAction.run());
        
        card.getChildren().addAll(titleLabel, descLabel, button);
        return card;
    }
    
    private void showBodyChallenge(String challengeType) {
        // Navigate to BodyChallengeScene with the specific challenge type
        BodyChallengeScene challengeScene = new BodyChallengeScene(mainApp, 800, 600, user, challengeType);
        mainApp.getPrimaryStage().setScene(challengeScene.getScene());
    }

    public Scene getScene() {
        return scene;
    }
}