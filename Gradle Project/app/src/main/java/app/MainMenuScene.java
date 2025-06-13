package app;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
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
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        root.setTop(createHeader());
        
        VBox contentBox = new VBox(15);
        contentBox.setPadding(new Insets(20));
        VBox.setVgrow(contentBox, Priority.ALWAYS);
        
        HBox statsBox = new HBox(30);
        statsBox.setPadding(new Insets(12));
        statsBox.setStyle("-fx-background-color: #2d3436; -fx-background-radius: 5;");
        
        VBox bmiBox = new VBox(5);
        Label bmiLabel = new Label("BMI");
        bmiLabel.setTextFill(Color.web("#a0aec0"));
        
        DecimalFormat df = new DecimalFormat("0.0");
        Label bmiValue = new Label(df.format(user.getBmi()));
        bmiValue.setFont(Font.font("System", FontWeight.BOLD, 15));
        bmiValue.setTextFill(Color.web("#dfe6e9"));
        bmiBox.getChildren().addAll(bmiLabel, bmiValue);
        
        VBox bmrBox = new VBox(5);
        Label bmrLabel = new Label("BMR");
        bmrLabel.setTextFill(Color.web("#a0aec0"));
        
        Label bmrValue = new Label(String.valueOf(Math.round(user.getBmr())));
        bmrValue.setFont(Font.font("System", FontWeight.BOLD, 15));
        bmrValue.setTextFill(Color.web("#dfe6e9"));
        bmrBox.getChildren().addAll(bmrLabel, bmrValue);
        
        VBox statusBox = new VBox(5);
        Label statusLabel = new Label("Status");
        statusLabel.setTextFill(Color.web("#a0aec0"));
        
        Label statusValue = new Label(user.getBmiCategory());
        statusValue.setFont(Font.font("System", FontWeight.BOLD, 15));
        statusValue.setTextFill(Color.web("#dfe6e9"));
        statusBox.getChildren().addAll(statusLabel, statusValue);
        
        statsBox.getChildren().addAll(bmiBox, bmrBox, statusBox);
        
        Label menuTitle = new Label("Menu Utama");
        menuTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        menuTitle.setTextFill(Color.web("#dfe6e9"));
        
        GridPane menuGrid = new GridPane();
        menuGrid.setHgap(15);
        menuGrid.setVgap(15);
        VBox.setVgrow(menuGrid, Priority.ALWAYS);
        
        VBox dailyExercCard = createMenuCard("Latihan Harian", "Lihat jadwal dan program latihan sesuai kebutuhan Anda", "Pilih", "/latihan-icon.png");
        dailyExercCard.lookup("Button").setOnMouseClicked(e -> mainApp.showDailyExerciseScene());

        VBox bodyFocusCard = createMenuCard("Tantangan Fokus Tubuh", "Tantangan khusus untuk melatih bagian tubuh tertentu", "Mulai", "/fokus-tubuh-icon.png");
        bodyFocusCard.lookup("Button").setOnMouseClicked(e -> mainApp.showBodyChallengeMenuScene());
        
        VBox progressCard = createMenuCard("Progres Latihan", "Lihat perkembangan latihan anda", "Lihat", "/progres-icon.png");
        progressCard.lookup("Button").setOnMouseClicked(e -> mainApp.showProgressTrackingScene());
        
        VBox tipsCard = createMenuCard("Tips & Panduan", "Dapatkan tips dan panduan latihan", "Baca", "/book-icon.png");
        tipsCard.lookup("Button").setOnMouseClicked(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informasi");
            alert.setHeaderText(null);
            alert.setContentText("Fitur belum tersedia. Masih dalam tahap pengembangan oleh developer.");
            alert.showAndWait();
        });
        
        menuGrid.add(dailyExercCard, 0, 0);
        menuGrid.add(bodyFocusCard, 1, 0);
        menuGrid.add(progressCard, 0, 1);
        menuGrid.add(tipsCard, 1, 1);
        
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);
        menuGrid.getColumnConstraints().addAll(column1, column2);
        
        RowConstraints row1 = new RowConstraints();
        row1.setVgrow(Priority.ALWAYS);
        RowConstraints row2 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);
        menuGrid.getRowConstraints().addAll(row1, row2);
        
        HBox footer = new HBox();
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20, 0, 20, 0));
        Label footerLabel = new Label("© 2025 Group 16 - Home Workout App | Beta Version");
        footerLabel.setTextFill(Color.web("#636e72"));
        footer.getChildren().add(footerLabel);
        
        contentBox.getChildren().addAll(statsBox, menuTitle, menuGrid);
        
        root.setCenter(contentBox);
        root.setBottom(footer);
        
        scene = new Scene(root, width, height);
    }
    
    private HBox createHeader() {
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
        
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", 13));
        userNameLabel.setTextFill(Color.WHITE);
        
        Image userIconImage = new Image(getClass().getResourceAsStream("/user-icon.png"));
        ImageView userIconView = new ImageView(userIconImage);
        userIconView.setFitHeight(30);
        userIconView.setFitWidth(30);
        
        Circle clip = new Circle(15, 15, 15);
        userIconView.setClip(clip);
        
        header.getChildren().addAll(headerTitle, spacer, userNameLabel, userIconView);
        
        return header;
    }

    // --- PERUBAHAN UTAMA ADA DI SINI ---
    private VBox createMenuCard(String title, String description, String buttonText, String iconPath) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER);
        card.setPrefHeight(180); 
        card.setMinHeight(180);  
        card.setStyle("-fx-background-color: #2d3436; " +
                "-fx-background-radius: 5; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 0);");
        
        // Jika iconPath tidak null, gunakan ImageView yang dipotong menjadi bundar
        if (iconPath != null) {
            ImageView iconView = new ImageView(new Image(getClass().getResourceAsStream(iconPath)));
            double iconSize = 50; // Ukuran ikon bundar
            iconView.setFitHeight(iconSize);
            iconView.setFitWidth(iconSize);
            
            // Membuat klip bundar untuk memotong gambar
            Circle clip = new Circle(iconSize / 2);
            clip.setCenterX(iconSize / 2);
            clip.setCenterY(iconSize / 2);
            iconView.setClip(clip);
            
            card.getChildren().add(iconView);
        } else {
            // Ikon default jika tidak ada path gambar
            Circle iconCircle = new Circle(25);
            iconCircle.setFill(Color.web("rgba(255, 107, 107, 0.15)"));
            card.getChildren().add(iconCircle);
        }
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#dfe6e9"));
        
        Label descLabel = new Label(description);
        descLabel.setTextFill(Color.web("#a0aec0"));
        descLabel.setWrapText(true);
        descLabel.setAlignment(Pos.CENTER);
        
        Button button = new Button(buttonText);
        button.setPrefWidth(150);
        button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 6px 14px; " +
                "-fx-background-radius: 5px;");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: linear-gradient(to right, #1a3a4a, #3d3560); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6px 14px; -fx-background-radius: 5px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 6px 14px; -fx-background-radius: 5px;"));
        
        card.getChildren().addAll(titleLabel, descLabel, button);
        return card;
    }
    
    public Scene getScene() {
        return scene;
    }
}