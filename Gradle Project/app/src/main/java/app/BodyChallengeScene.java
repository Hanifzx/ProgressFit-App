package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class BodyChallengeScene extends Template {
    private Scene scene;
    private Main mainApp;
    private User user;
    private ExerciseKatalog exerciseKatalog;
    private String challengeType;
    private String challengeTitle;
    private List<Exercise> baseExercises;
    private int currentWeek = 1;
    
    // Sidebar components
    private VBox sidebar;
    private VBox exerciseListContainer;
    private Label weekTitleLabel;

    public BodyChallengeScene(Main mainApp, int width, int height, User user, String challengeType) {
        this.mainApp = mainApp;
        this.user = user;
        this.exerciseKatalog = new ExerciseKatalog();
        this.challengeType = challengeType;
        
        initializeChallengeData();
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        root.setPrefSize(width, height);
        root.setMaxSize(width, height);
        
        root.setTop(createHeader());
        root.setCenter(createMainContent());
        
        scene = new Scene(root, width, height);
        
        loadExercisesForWeek(1);
    }
    
    private void initializeChallengeData() {
        switch (challengeType.toLowerCase()) {
            case "upper_body":
                challengeTitle = "Upper Body Challenge";
                baseExercises = exerciseKatalog.getGerakanUpperBody();
                break;
            case "lower_body":
                challengeTitle = "Lower Body Challenge";
                baseExercises = exerciseKatalog.getGerakanLowerBody();
                break;
            default:
                challengeTitle = "Body Challenge";
                baseExercises = exerciseKatalog.getGerakanUpperBody();
        }
    }
    
    @Override
    protected HBox createHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(15));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setSpacing(10);
        header.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376);");

        Button backButton = createButtonCard("Kembali");
        backButton.setOnAction(e -> mainApp.showBodyChallengeMenuScene());
        
        Label headerTitle = new Label(challengeTitle);
        headerTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        headerTitle.setTextFill(Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", 13));
        userNameLabel.setTextFill(Color.WHITE);
        
        // --- PERUBAHAN UTAMA DI SINI ---
        Image userIconImage = new Image(getClass().getResourceAsStream("/user-icon.png"));
        ImageView userIconView = new ImageView(userIconImage);
        userIconView.setFitHeight(30);
        userIconView.setFitWidth(30);
        
        Circle clip = new Circle(15, 15, 15);
        userIconView.setClip(clip);
        
        header.getChildren().addAll(backButton, headerTitle, spacer, userNameLabel, userIconView);
        return header;
    }
    
    private HBox createMainContent() {
        HBox mainContent = new HBox();
        mainContent.setSpacing(0);
        
        sidebar = createSidebar();
        VBox exerciseSection = createExerciseSection();
        
        sidebar.setPrefWidth(200);
        sidebar.setMinWidth(200);
        sidebar.setMaxWidth(200);
        
        HBox.setHgrow(exerciseSection, Priority.ALWAYS);
        
        mainContent.getChildren().addAll(sidebar, exerciseSection);
        return mainContent;
    }
    
    private VBox createSidebar() {
        VBox sidebar = new VBox();
        sidebar.setStyle("-fx-background-color: #2d3436; -fx-border-color: #636e72; -fx-border-width: 0 1 0 0;");
        sidebar.setPrefWidth(200);
        sidebar.setPadding(new Insets(20, 0, 20, 0));
        
        Label sidebarTitle = new Label("Minggu Challenge");
        sidebarTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        sidebarTitle.setTextFill(Color.web("#dfe6e9"));
        sidebarTitle.setPadding(new Insets(0, 0, 15, 20));
        
        VBox weekButtons = new VBox(5);
        for (int i = 1; i <= 4; i++) {
            Button weekButton = createWeekButton(i);
            weekButtons.getChildren().add(weekButton);
        }
        
        VBox challengeInfo = createChallengeInfo();
        
        sidebar.getChildren().addAll(sidebarTitle, weekButtons, challengeInfo);
        return sidebar;
    }
    
    private Button createWeekButton(int week) {
        Button weekButton = new Button("Minggu " + week);
        weekButton.setPrefWidth(160);
        weekButton.setPrefHeight(40);
        weekButton.setAlignment(Pos.CENTER_LEFT);
        weekButton.setPadding(new Insets(0, 0, 0, 20));
        
        updateWeekButtonStyle(weekButton, week == currentWeek);
        
        weekButton.setOnAction(e -> {
            currentWeek = week;
            loadExercisesForWeek(week);
            updateSidebarSelection();
        });
        
        return weekButton;
    }
    
    private void updateWeekButtonStyle(Button button, boolean isSelected) {
        String baseStyle = "-fx-background-radius: 0; -fx-border-color: transparent;";
        if (isSelected) {
            button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); -fx-text-fill: white; -fx-font-weight: bold; " + baseStyle);
        } else {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-weight: normal; " + baseStyle);
        }
        
        button.setOnMouseEntered(e -> {
            if (!isSelected) {
                button.setStyle("-fx-background-color: #636e72; -fx-text-fill: white; -fx-font-weight: normal; " + baseStyle);
            }
        });
        
        button.setOnMouseExited(e -> updateWeekButtonStyle(button, isSelected));
    }
    
    private VBox createChallengeInfo() {
        VBox infoBox = new VBox(10);
        infoBox.setPadding(new Insets(20, 20, 0, 20));
        
        VBox infoCard = new VBox(8);
        infoCard.setPadding(new Insets(15));
        infoCard.setStyle("-fx-background-color: #353b48; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
        Label infoTitle = new Label("Info Challenge");
        infoTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        infoTitle.setTextFill(Color.web("#dfe6e9"));
        
        String challengeDescription = challengeType.equals("upper_body") 
            ? "Fokus pada penguatan otot tubuh bagian atas seperti dada, punggung, bahu, dan lengan."
            : "Fokus pada penguatan otot tubuh bagian bawah seperti paha, betis, dan glutes.";
        
        Label infoDesc = new Label(challengeDescription);
        infoDesc.setTextFill(Color.web("#a0aec0"));
        infoDesc.setFont(Font.font("System", 12));
        infoDesc.setWrapText(true);
        
        Label durationLabel = new Label("Durasi: 4 Minggu");
        durationLabel.setTextFill(Color.web("#4CAF50"));
        durationLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        
        infoCard.getChildren().addAll(infoTitle, infoDesc, durationLabel);
        infoBox.getChildren().add(infoCard);
        
        return infoBox;
    }
    
    private void updateSidebarSelection() {
        VBox weekButtons = (VBox) sidebar.getChildren().get(1);
        for (int i = 0; i < weekButtons.getChildren().size(); i++) {
            Button weekButton = (Button) weekButtons.getChildren().get(i);
            updateWeekButtonStyle(weekButton, (i + 1) == currentWeek);
        }
    }
    
    private VBox createExerciseSection() {
        VBox exerciseSection = new VBox();
        exerciseSection.setStyle("-fx-background-color: #1e272e;");
        exerciseSection.setPadding(new Insets(20));
        
        weekTitleLabel = new Label("Minggu 1 - " + challengeTitle);
        weekTitleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        weekTitleLabel.setTextFill(Color.web("#dfe6e9"));
        weekTitleLabel.setPadding(new Insets(0, 0, 20, 0));
        
        exerciseListContainer = new VBox(10);
        
        ScrollPane scrollPane = new ScrollPane(exerciseListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1e272e; -fx-background-color: #1e272e;");
        scrollPane.setPrefHeight(400);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        exerciseSection.getChildren().addAll(weekTitleLabel, scrollPane);
        return exerciseSection;
    }
    
    private void loadExercisesForWeek(int week) {
        weekTitleLabel.setText("Minggu " + week + " - " + challengeTitle);
        List<Exercise> weekExercises = getExercisesForWeek(week);
        exerciseListContainer.getChildren().clear();
        for (int i = 0; i < weekExercises.size(); i++) {
            Exercise exercise = weekExercises.get(i);
            HBox exerciseItem = createExerciseItem(i + 1, exercise, week);
            exerciseListContainer.getChildren().add(exerciseItem);
        }
    }
    
    private List<Exercise> getExercisesForWeek(int week) {
        List<Exercise> weekExercises = new ArrayList<>();
        for (Exercise baseExercise : baseExercises) {
            String adjustedSets = adjustSetsForWeek(baseExercise.getSets(), week);
            Exercise adjustedExercise = new Exercise(baseExercise.getName(), adjustedSets);
            weekExercises.add(adjustedExercise);
        }
        
        if (week > 1) {
            Collections.shuffle(weekExercises);
        }
        
        int maxExercises = Math.min(8 + week, 12);
        if (weekExercises.size() > maxExercises) {
            weekExercises = weekExercises.subList(0, maxExercises);
        }
        
        return weekExercises;
    }
    
    private String adjustSetsForWeek(String originalSets, int week) {
        if (originalSets.contains("repetisi")) {
            String[] parts = originalSets.split(" ");
            if (parts.length >= 4) {
                try {
                    int sets = Integer.parseInt(parts[0]);
                    int reps = Integer.parseInt(parts[3]);
                    int newReps = reps + (2 * (week - 1));
                    return sets + " set x " + newReps + " repetisi";
                } catch (NumberFormatException e) { /* return original */ }
            }
        } else if (originalSets.contains("detik")) {
            String[] parts = originalSets.split(" ");
            if (parts.length >= 2) {
                try {
                    int seconds = Integer.parseInt(parts[0]);
                    int newSeconds = seconds + (10 * (week - 1));
                    return newSeconds + " detik";
                } catch (NumberFormatException e) { /* return original */ }
            }
        } else if (originalSets.contains("menit")) {
            String[] parts = originalSets.split(" ");
            if (parts.length >= 2) {
                try {
                    int minutes = Integer.parseInt(parts[0]);
                    int newMinutes = minutes + (5 * (week - 1));
                    return newMinutes + " menit";
                } catch (NumberFormatException e) { /* return original */ }
            }
        }
        return originalSets;
    }
    
    private HBox createExerciseItem(int number, Exercise exercise, int week) {
        HBox item = new HBox(15);
        item.setPadding(new Insets(15));
        item.setAlignment(Pos.CENTER_LEFT);
        item.setStyle("-fx-background-color: #2d3436; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
        Circle numberCircle = new Circle(18, Color.web("#4CAF50"));
        Label numberLabel = new Label(String.valueOf(number));
        numberLabel.setTextFill(Color.WHITE);
        numberLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        
        VBox exerciseInfo = new VBox(4);
        Label exerciseName = new Label(exercise.getName());
        exerciseName.setFont(Font.font("System", FontWeight.BOLD, 16));
        exerciseName.setTextFill(Color.web("#dfe6e9"));
        
        Label exerciseSets = new Label(exercise.getSets());
        exerciseSets.setTextFill(Color.web("#a0aec0"));
        exerciseSets.setFont(Font.font("System", 13));
        
        exerciseInfo.getChildren().addAll(exerciseName, exerciseSets);
        
        VBox circleContainer = new VBox(numberCircle, numberLabel);
        circleContainer.setAlignment(Pos.CENTER);
        numberLabel.setTranslateY(-31);
        
        item.getChildren().addAll(circleContainer, exerciseInfo);
        
        String baseStyle = "-fx-background-color: #2d3436; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);";
        String hoverStyle = "-fx-background-color: #353b48; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);";
        item.setOnMouseEntered(e -> item.setStyle(hoverStyle));
        item.setOnMouseExited(e -> item.setStyle(baseStyle));
        
        return item;
    }

    public Scene getScene() {
        return scene;
    }
}