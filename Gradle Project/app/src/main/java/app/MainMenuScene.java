package app;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class MainMenuScene {
    
    private Scene scene;
    private Main mainApp;
    private User user;
    
    public MainMenuScene(Main mainApp, int width, int height, User user) {
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
        
        Label headerTitle = new Label("Home Workout");
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
        
        header.getChildren().addAll(headerTitle, spacer, userNameLabel, userIcon);
        
        // Main content
        VBox contentBox = new VBox(15);
        contentBox.setPadding(new Insets(20));
        
        // User stats
        HBox statsBox = new HBox(30);
        statsBox.setPadding(new Insets(12));
        statsBox.setStyle("-fx-background-color: #2d3436; -fx-background-radius: 5;");
        
        // BMI stat
        VBox bmiBox = new VBox(5);
        Label bmiLabel = new Label("BMI");
        bmiLabel.setTextFill(Color.web("#a0aec0"));
        
        DecimalFormat df = new DecimalFormat("0.0");
        Label bmiValue = new Label(df.format(user.getBmi()));
        bmiValue.setFont(Font.font("System", FontWeight.BOLD, 15));
        bmiValue.setTextFill(Color.web("#dfe6e9"));
        
        bmiBox.getChildren().addAll(bmiLabel, bmiValue);
        
        // BMR stat
        VBox bmrBox = new VBox(5);
        Label bmrLabel = new Label("BMR");
        bmrLabel.setTextFill(Color.web("#a0aec0"));
        
        Label bmrValue = new Label(String.valueOf(Math.round(user.getBmr())));
        bmrValue.setFont(Font.font("System", FontWeight.BOLD, 15));
        bmrValue.setTextFill(Color.web("#dfe6e9"));
        
        bmrBox.getChildren().addAll(bmrLabel, bmrValue);
        
        // Status stat
        VBox statusBox = new VBox(5);
        Label statusLabel = new Label("Status");
        statusLabel.setTextFill(Color.web("#a0aec0"));
        
        Label statusValue = new Label(user.getBmiCategory());
        statusValue.setFont(Font.font("System", FontWeight.BOLD, 15));
        statusValue.setTextFill(Color.web("#dfe6e9"));
        
        statusBox.getChildren().addAll(statusLabel, statusValue);
        
        statsBox.getChildren().addAll(bmiBox, bmrBox, statusBox);
        
        // Menu title 
        Label menuTitle = new Label("Menu Utama");
        menuTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        menuTitle.setTextFill(Color.web("#dfe6e9"));
        // VBox.setMargin(menuTitle, new Insets(0, 0, 2, 0));
        
        // Menu grid
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(15);
        menuGrid.setVgap(15);
        menuGrid.setMinHeight(200); 
        
        // Create menu cards
        VBox dailyExercCard = createMenuCard(
            "Latihan Harian",
            "Lihat jadwal dan program latihan sesuai kebutuhan Anda",
            "Pilih"
        );
        // Find the button in the dailyExercCard and add an action
        Button programButton = (Button) dailyExercCard.getChildren().get(dailyExercCard.getChildren().size() - 1);
        programButton.setOnAction(e -> mainApp.showDailyExerciseScene());

        VBox bodyFocusCard = createMenuCard(
            "Tantangan Fokus Tubuh",
            "Tantangan khusus untuk melatih bagian tubuh tertentu",
            "Mulai"
        );
        // Find the button in the bodyFocusCard and add an action
        Button bodyFocusButton = (Button) bodyFocusCard.getChildren().get(bodyFocusCard.getChildren().size() - 1);
        bodyFocusButton.setOnAction(e -> mainApp.showBodyChallengeMenuScene());
        
        VBox progressCard = createMenuCard(
            "Progres Latihan",
            "Lihat perkembangan latihan anda",
            "Lihat"
        );

        Button progressButton = (Button) progressCard.getChildren().get(progressCard.getChildren().size() - 1);
        // Tambahkan aksi ketika tombol ditekan
        progressButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Fitur belum tersedia. Masih dalam tahap pengembangan oleh developer.");
            alert.showAndWait();
        });
        
        VBox tipsCard = createMenuCard(
            "Tips & Panduan",
            "Dapatkan tips dan panduan latihan",
            "Baca"
        );

        Button tipsButton = (Button) tipsCard.getChildren().get(tipsCard.getChildren().size() - 1);
        // Tambahkan aksi ketika tombol ditekan
        tipsButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Fitur belum tersedia. Masih dalam tahap pengembangan oleh developer.");
            alert.showAndWait();
        });
        
        // Add cards to grid
        menuGrid.add(dailyExercCard, 0, 0);
        menuGrid.add(bodyFocusCard, 1, 0);
        menuGrid.add(progressCard, 0, 1);
        menuGrid.add(tipsCard, 1, 1);
        
        // Set column constraints
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        menuGrid.getColumnConstraints().addAll(column1, column2);
        
        // Footer
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20, 0, 20, 0));
        
        Label footerLabel = new Label("© 2025 Group 16 - Home Workout App | Beta Version");
        footerLabel.setTextFill(Color.web("#636e72"));
        
        footer.getChildren().add(footerLabel);
        
        // Add all to content box
        contentBox.getChildren().addAll(statsBox, menuTitle, menuGrid);
        
        // Add to root
        root.setTop(header);
        root.setCenter(contentBox);
        root.setBottom(footer);
        
        // Create scene
        scene = new Scene(root, width, height);
    }
    
    private VBox createMenuCard(String title, String description, String buttonText) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(180); 
        card.setMinHeight(180);  
        card.setStyle("-fx-background-color: #2d3436; " +
                "-fx-background-radius: 5; " +
                "-fx-border-color: transparent; " +
                "-fx-border-width: 1; " +
                "-fx-border-radius: 5; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");
        
        // Card hover effect
        // card.setOnMouseEntered(e -> {
        //     card.setStyle("-fx-background-color: #2d3436; " +
        //             "-fx-background-radius: 5; " +
        //             "-fx-border-color: #ff6b6b; " +
        //             "-fx-border-width: 1; " +
        //             "-fx-border-radius: 5; " +
        //             "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0); " +
        //             "-fx-translate-y: -2px;");
        // });
        
        // card.setOnMouseExited(e -> {
        //     card.setStyle("-fx-background-color: #2d3436; " +
        //             "-fx-background-radius: 5; " +
        //             "-fx-border-color: transparent; " +
        //             "-fx-border-width: 1; " +
        //             "-fx-border-radius: 5; " +
        //             "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");
        // });
        
        // Icon circle
        Circle iconCircle = new Circle(25);
        iconCircle.setFill(Color.web("rgba(255, 107, 107, 0.15)"));
        
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
        
        card.getChildren().addAll(iconCircle, titleLabel, descLabel, button);
        return card;
    }
    
    public Scene getScene() {
        return scene;
    }
}