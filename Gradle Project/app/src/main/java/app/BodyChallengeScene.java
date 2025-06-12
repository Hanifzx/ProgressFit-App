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
        
        // Set challenge title and get base exercises based on challenge type
        initializeChallengeData();
        
        // Create root container dengan ukuran terbatas
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        // PENTING: Set ukuran maksimum untuk root
        root.setPrefSize(width, height);
        root.setMaxSize(width, height);
        
        // Create header
        HBox header = createHeader();
        
        // Create main content with sidebar and exercise list
        HBox mainContent = createMainContent();
        
        // Add components to root
        root.setTop(header);
        root.setCenter(mainContent);
        
        // Create scene dengan ukuran eksplisit
        scene = new Scene(root, width, height);
        
        // Load initial week (Week 1)
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
        
        // User info in header
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", 13));
        userNameLabel.setTextFill(Color.WHITE);
        
        Circle userIcon = new Circle(15);
        userIcon.setFill(Color.web("#ff6b6b"));
        
        header.getChildren().addAll(backButton, headerTitle, spacer, userNameLabel, userIcon);
        return header;
    }
    
    private HBox createMainContent() {
        HBox mainContent = new HBox();
        mainContent.setSpacing(0);
        
        // Create sidebar
        sidebar = createSidebar();
        
        // Create exercise list container
        VBox exerciseSection = createExerciseSection();
        
        // Set sizing
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
        
        // Sidebar title
        Label sidebarTitle = new Label("Minggu Challenge");
        sidebarTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        sidebarTitle.setTextFill(Color.web("#dfe6e9"));
        sidebarTitle.setPadding(new Insets(0, 0, 15, 20));
        
        // Create week buttons
        VBox weekButtons = new VBox(5);
        for (int i = 1; i <= 4; i++) {
            Button weekButton = createWeekButton(i);
            weekButtons.getChildren().add(weekButton);
        }
        
        // Add challenge info
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
        
        // Set initial style
        updateWeekButtonStyle(weekButton, week == currentWeek);
        
        weekButton.setOnAction(e -> {
            currentWeek = week;
            loadExercisesForWeek(week);
            updateSidebarSelection();
        });
        
        return weekButton;
    }
    
    private void updateWeekButtonStyle(Button button, boolean isSelected) {
        if (isSelected) {
            button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-background-radius: 0; " +
                    "-fx-border-color: transparent;");
        } else {
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: #a0aec0; " +
                    "-fx-font-weight: normal; " +
                    "-fx-background-radius: 0; " +
                    "-fx-border-color: transparent;");
        }
        
        button.setOnMouseEntered(e -> {
            if (!isSelected) {
                button.setStyle("-fx-background-color: #636e72; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-weight: normal; " +
                        "-fx-background-radius: 0; " +
                        "-fx-border-color: transparent;");
            }
        });
        
        button.setOnMouseExited(e -> {
            updateWeekButtonStyle(button, isSelected);
        });
    }
    
    private VBox createChallengeInfo() {
        VBox infoBox = new VBox(10);
        infoBox.setPadding(new Insets(20, 20, 0, 20));
        
        // Challenge info card
        VBox infoCard = new VBox(8);
        infoCard.setPadding(new Insets(15));
        infoCard.setStyle("-fx-background-color: #353b48; " +
                "-fx-background-radius: 8; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
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
        // Update all week buttons
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
        
        // Week title
        weekTitleLabel = new Label("Minggu 1 - " + challengeTitle);
        weekTitleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        weekTitleLabel.setTextFill(Color.web("#dfe6e9"));
        weekTitleLabel.setPadding(new Insets(0, 0, 20, 0));
        
        // Exercise list container
        exerciseListContainer = new VBox(10);
        
        // Scroll pane for exercises
        ScrollPane scrollPane = new ScrollPane(exerciseListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1e272e; -fx-background-color: #1e272e;");
        scrollPane.setPrefHeight(400);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        exerciseSection.getChildren().addAll(weekTitleLabel, scrollPane);
        return exerciseSection;
    }
    
    private void loadExercisesForWeek(int week) {
        // Update week title
        weekTitleLabel.setText("Minggu " + week + " - " + challengeTitle);
        
        // Get exercises for this week
        List<Exercise> weekExercises = getExercisesForWeek(week);
        
        // Clear existing exercises
        exerciseListContainer.getChildren().clear();
        
        // Add week description
        // Label weekDescription = createWeekDescription(week);
        // exerciseListContainer.getChildren().add(weekDescription);
        
        // Add exercises to container
        for (int i = 0; i < weekExercises.size(); i++) {
            Exercise exercise = weekExercises.get(i);
            HBox exerciseItem = createExerciseItem(i + 1, exercise, week);
            exerciseListContainer.getChildren().add(exerciseItem);
        }
    }
    
    // private Label createWeekDescription(int week) {
    //     String description = "";
    //     switch (week) {
    //         case 1:
    //             description = "🚀 Minggu Pertama - Fokus pada teknik dasar dan membangun fondasi kekuatan";
    //             break;
    //         case 2:
    //             description = "💪 Minggu Kedua - Meningkatkan intensitas dan repetisi latihan";
    //             break;
    //         case 3:
    //             description = "🔥 Minggu Ketiga - Tantangan yang lebih besar dengan variasi gerakan";
    //             break;
    //         case 4:
    //             description = "🏆 Minggu Keempat - Uji kemampuan maksimal dan evaluasi progress";
    //             break;
    //     }
        
    //     Label descLabel = new Label(description);
    //     descLabel.setFont(Font.font("System", FontWeight.BOLD, 14));
    //     descLabel.setTextFill(Color.web("#4CAF50"));
    //     descLabel.setPadding(new Insets(0, 0, 15, 0));
    //     descLabel.setWrapText(true);
        
    //     return descLabel;
    // }
    
    private List<Exercise> getExercisesForWeek(int week) {
        List<Exercise> weekExercises = new ArrayList<>();
        
        // Adjust exercises based on week progression
        for (Exercise baseExercise : baseExercises) {
            String adjustedSets = adjustSetsForWeek(baseExercise.getSets(), week);
            Exercise adjustedExercise = new Exercise(baseExercise.getName(), adjustedSets);
            weekExercises.add(adjustedExercise);
        }
        
        // Shuffle for variety except week 1
        if (week > 1) {
            Collections.shuffle(weekExercises);
        }
        
        // Limit exercises based on week
        int maxExercises = Math.min(8 + week, 12); // Week 1: 9 exercises, Week 4: 12 exercises
        if (weekExercises.size() > maxExercises) {
            weekExercises = weekExercises.subList(0, maxExercises);
        }
        
        return weekExercises;
    }
    
    private String adjustSetsForWeek(String originalSets, int week) {
        // Progressively increase difficulty each week
        if (originalSets.contains("repetisi")) {
            // For repetition-based exercises
            String[] parts = originalSets.split(" ");
            if (parts.length >= 4) {
                try {
                    int sets = Integer.parseInt(parts[0]);
                    int reps = Integer.parseInt(parts[3]);
                    
                    // Increase reps by 2 each week
                    int newReps = reps + (2 * (week - 1));
                    return sets + " set x " + newReps + " repetisi";
                } catch (NumberFormatException e) {
                    return originalSets;
                }
            }
        } else if (originalSets.contains("detik")) {
            // For time-based exercises
            String[] parts = originalSets.split(" ");
            if (parts.length >= 2) {
                try {
                    int seconds = Integer.parseInt(parts[0]);
                    // Increase time by 10 seconds each week
                    int newSeconds = seconds + (10 * (week - 1));
                    return newSeconds + " detik";
                } catch (NumberFormatException e) {
                    return originalSets;
                }
            }
        } else if (originalSets.contains("menit")) {
            // For minute-based exercises
            String[] parts = originalSets.split(" ");
            if (parts.length >= 2) {
                try {
                    int minutes = Integer.parseInt(parts[0]);
                    // Increase time by 5 minutes each week
                    int newMinutes = minutes + (5 * (week - 1));
                    return newMinutes + " menit";
                } catch (NumberFormatException e) {
                    return originalSets;
                }
            }
        }
        
        return originalSets;
    }
    
    private HBox createExerciseItem(int number, Exercise exercise, int week) {
        HBox item = new HBox(15);
        item.setPadding(new Insets(15));
        item.setAlignment(Pos.CENTER_LEFT);
        item.setStyle("-fx-background-color: #2d3436; " +
                "-fx-background-radius: 8; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
        // Number circle with week-based color
        Circle numberCircle = new Circle(18);
        numberCircle.setFill(Color.web("#4CAF50"));
        
        Label numberLabel = new Label(String.valueOf(number));
        numberLabel.setTextFill(Color.WHITE);
        numberLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        
        // Exercise info
        VBox exerciseInfo = new VBox(4);
        
        Label exerciseName = new Label(exercise.getName());
        exerciseName.setFont(Font.font("System", FontWeight.BOLD, 16));
        exerciseName.setTextFill(Color.web("#dfe6e9"));
        
        Label exerciseSets = new Label(exercise.getSets());
        exerciseSets.setTextFill(Color.web("#a0aec0"));
        exerciseSets.setFont(Font.font("System", 13));
        
        // Difficulty indicator
        // Label difficultyLabel = new Label(getDifficultyText(week));
        // difficultyLabel.setTextFill(Color.web(circleColor));
        // difficultyLabel.setFont(Font.font("System", FontWeight.BOLD, 11));
        
        exerciseInfo.getChildren().addAll(exerciseName, exerciseSets);
        
        // Create a container for the circle and number
        VBox circleContainer = new VBox();
        circleContainer.setAlignment(Pos.CENTER);
        circleContainer.getChildren().addAll(numberCircle, numberLabel);
        numberLabel.setTranslateY(-31); // Position number over circle
        
        item.getChildren().addAll(circleContainer, exerciseInfo);
        
        // Add hover effect
        item.setOnMouseEntered(e -> {
            item.setStyle("-fx-background-color: #353b48; " +
                    "-fx-background-radius: 8; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 2);");
        });
        
        item.setOnMouseExited(e -> {
            item.setStyle("-fx-background-color: #2d3436; " +
                    "-fx-background-radius: 8; " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        });
        
        return item;
    }
    
    // private String getWeekColor(int week) {
    //     switch (week) {
    //         case 1: return "#4CAF50"; // Green
    //         case 2: return "#2196F3"; // Blue
    //         case 3: return "#FF9800"; // Orange
    //         case 4: return "#F44336"; // Red
    //         default: return "#4CAF50";
    //     }
    // }
    
    // private String getDifficultyText(int week) {
    //     switch (week) {
    //         case 1: return "• Pemula";
    //         case 2: return "•• Menengah";
    //         case 3: return "••• Lanjutan";
    //         case 4: return "•••• Expert";
    //         default: return "• Pemula";
    //     }
    // }

    public Scene getScene() {
        return scene;
    }
}