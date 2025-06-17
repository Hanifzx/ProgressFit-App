package app;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");

        root.setTop(createHeader());
        
        VBox mainContent = createMainContent();

        root.setCenter(mainContent);
        
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
        
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", 13));
        userNameLabel.setTextFill(Color.WHITE);
        
        Image userIconImage = new Image(getClass().getResourceAsStream("/user-icon.png"));
        ImageView userIconView = new ImageView(userIconImage);

        userIconView.setFitHeight(30);
        userIconView.setFitWidth(30);
        
        Circle clip = new Circle(15);
        clip.setCenterX(15);
        clip.setCenterY(15);
        userIconView.setClip(clip);

        header.getChildren().addAll(backButton, headerTitle, spacer, userNameLabel, userIconView);
        return header;
    }
    
    private VBox createMainContent() {
        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(0, 20, 0, 20));
        contentBox.setAlignment(Pos.CENTER);
        
        Label welcomeLabel = new Label("Pilih Bagian Tubuh yang Ingin Anda Fokuskan");
        welcomeLabel.setFont(Font.font("System", FontWeight.BOLD, 18));
        welcomeLabel.setTextFill(Color.web("#dfe6e9"));
        
        VBox programMenu = new VBox(15);
        programMenu.setAlignment(Pos.CENTER);
        programMenu.setMaxWidth(400);
        
        VBox upperBodyCard = createBodyChallengeCard("Upper Body Challenge", "Fokus latihan untuk tubuh bagian atas", "Mulai Challenge", () -> showBodyChallenge("upper_body"));
        VBox lowerBodyCard = createBodyChallengeCard("Lower Body Challenge", "Fokus latihan untuk tubuh bagian bawah", "Mulai Challenge", () -> showBodyChallenge("lower_body"));
        
        upperBodyCard.setMaxWidth(350);
        upperBodyCard.setPrefWidth(350);
        lowerBodyCard.setMaxWidth(350);
        lowerBodyCard.setPrefWidth(350);
        
        programMenu.getChildren().addAll(upperBodyCard, lowerBodyCard);
        contentBox.getChildren().addAll(welcomeLabel, programMenu);
        return contentBox;
    }

    private VBox createBodyChallengeCard(String title, String description, String buttonText, Runnable onAction) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(150); 
        card.setMinHeight(150);  
        card.setStyle("-fx-background-color: #2d3436; -fx-background-radius: 5; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#dfe6e9"));
        
        Label descLabel = new Label(description);
        descLabel.setTextFill(Color.web("#a0aec0"));
        descLabel.setWrapText(true);
        descLabel.setAlignment(Pos.CENTER);
        
        Button button = new Button(buttonText);
        button.setPrefWidth(150);
        button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6px 14px; -fx-background-radius: 5px;");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: linear-gradient(to right, #1a3a4a, #3d3560); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6px 14px; -fx-background-radius: 5px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6px 14px; -fx-background-radius: 5px;"));
        
        button.setOnAction(e -> onAction.run());
        
        card.getChildren().addAll(titleLabel, descLabel, button);
        return card;
    }
    
    private void showBodyChallenge(String challengeType) {
        BodyChallengeScene challengeScene = new BodyChallengeScene(mainApp, 800, 600, user, challengeType);
        mainApp.getPrimaryStage().setScene(challengeScene.getScene());
    }

    public Scene getScene() {
        return scene;
    }
}