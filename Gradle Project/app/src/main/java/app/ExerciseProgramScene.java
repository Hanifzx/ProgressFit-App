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

public class ExerciseProgramScene extends Template {
    private Scene scene;
    private Main mainApp;
    private User user;
    private ExerciseKatalog exerciseKatalog;
    private String programType;
    private String programTitle;
    private List<Exercise> baseExercises;
    private int currentDay = 1;
    
    // Sidebar components
    private VBox sidebar;
    private VBox exerciseListContainer;
    private Label dayTitleLabel;

    public ExerciseProgramScene(Main mainApp, int width, int height, User user, String programType) {
        this.mainApp = mainApp;
        this.user = user;
        this.exerciseKatalog = new ExerciseKatalog();
        this.programType = programType;
        
        // Set program title and get base exercises based on program type
        initializeProgramData();
        
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
        
        // Load initial day (Day 1)
        loadExercisesForDay(1);
    }
    
    private void initializeProgramData() {
        switch (programType.toLowerCase()) {
            case "weight_gain":
                programTitle = "Program Menambah Berat Badan";
                baseExercises = exerciseKatalog.getGerakanMenambahBB();
                break;
            case "weight_loss":
                programTitle = "Program Menurunkan Berat Badan";
                baseExercises = exerciseKatalog.getGerakanMenurunkanBB();
                break;
            case "stamina":
                programTitle = "Program Menjaga Stamina";
                baseExercises = exerciseKatalog.getGerakanMenjagaStamina();
                break;
            default:
                programTitle = "Program Latihan";
                baseExercises = exerciseKatalog.getGerakanMenambahBB();
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
        backButton.setOnAction(e -> mainApp.showDailyExerciseScene());
        
        Label headerTitle = new Label(programTitle);
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
        Label sidebarTitle = new Label("Hari Latihan");
        sidebarTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        sidebarTitle.setTextFill(Color.web("#dfe6e9"));
        sidebarTitle.setPadding(new Insets(0, 0, 15, 20));
        
        // Create day buttons
        VBox dayButtons = new VBox(5);
        for (int i = 1; i <= 6; i++) {
            Button dayButton = createDayButton(i);
            dayButtons.getChildren().add(dayButton);
        }

        VBox exerciseInfo = createExerciseInfo();
        
        sidebar.getChildren().addAll(sidebarTitle, dayButtons, exerciseInfo);
        return sidebar;
    }
    
    private Button createDayButton(int day) {
        Button dayButton = new Button("Hari " + day);
        dayButton.setPrefWidth(160);
        dayButton.setPrefHeight(40);
        dayButton.setAlignment(Pos.CENTER_LEFT);
        dayButton.setPadding(new Insets(0, 0, 0, 20));
        
        // Set initial style
        updateDayButtonStyle(dayButton, day == currentDay);
        
        dayButton.setOnAction(e -> {
            currentDay = day;
            loadExercisesForDay(day);
            updateSidebarSelection();
        });
        
        return dayButton;
    }
    
    private void updateDayButtonStyle(Button button, boolean isSelected) {
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
            updateDayButtonStyle(button, isSelected);
        });
    }

    private VBox createExerciseInfo() {
        VBox infoBox = new VBox(10);
        infoBox.setPadding(new Insets(20, 20, 0, 20));
        
        // Challenge info card
        VBox infoCard = new VBox(8);
        infoCard.setPadding(new Insets(15));
        infoCard.setStyle("-fx-background-color: #353b48; " +
                "-fx-background-radius: 8; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
        Label infoTitle = new Label("Info Latihan");
        infoTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        infoTitle.setTextFill(Color.web("#dfe6e9"));
        
        String challengeDescription = "Program latihan yang sesuai dengan tujuan dan kebutuhanmu.";
        
        Label infoDesc = new Label(challengeDescription);
        infoDesc.setTextFill(Color.web("#a0aec0"));
        infoDesc.setFont(Font.font("System", 12));
        infoDesc.setWrapText(true);
        
        Label durationLabel = new Label("Durasi: 6 Hari");
        durationLabel.setTextFill(Color.web("#4CAF50"));
        durationLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        
        infoCard.getChildren().addAll(infoTitle, infoDesc, durationLabel);
        infoBox.getChildren().add(infoCard);
        
        return infoBox;
    }
    
    private void updateSidebarSelection() {
        // Update all day buttons
        VBox dayButtons = (VBox) sidebar.getChildren().get(1);
        for (int i = 0; i < dayButtons.getChildren().size(); i++) {
            Button dayButton = (Button) dayButtons.getChildren().get(i);
            updateDayButtonStyle(dayButton, (i + 1) == currentDay);
        }
    }
    
    private VBox createExerciseSection() {
        VBox exerciseSection = new VBox();
        exerciseSection.setStyle("-fx-background-color: #1e272e;");
        exerciseSection.setPadding(new Insets(20));
        
        // Day title
        dayTitleLabel = new Label("Hari 1 - " + programTitle);
        dayTitleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        dayTitleLabel.setTextFill(Color.web("#dfe6e9"));
        dayTitleLabel.setPadding(new Insets(0, 0, 20, 0));
        
        // Exercise list container
        exerciseListContainer = new VBox(10);
        
        // Scroll pane for exercises
        ScrollPane scrollPane = new ScrollPane(exerciseListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1e272e; -fx-background-color: #1e272e;");
        scrollPane.setPrefHeight(500);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        exerciseSection.getChildren().addAll(dayTitleLabel, scrollPane);
        return exerciseSection;
    }
    
    private void loadExercisesForDay(int day) {
        // Update day title
        dayTitleLabel.setText("Hari " + day + " - " + programTitle);
        
        // Get exercises for this day
        List<Exercise> dayExercises = getExercisesForDay(day);
        
        // Clear existing exercises
        exerciseListContainer.getChildren().clear();
        
        // Add exercises to container
        for (int i = 0; i < dayExercises.size(); i++) {
            Exercise exercise = dayExercises.get(i);
            HBox exerciseItem = createExerciseItem(i + 1, exercise);
            exerciseListContainer.getChildren().add(exerciseItem);
        }
    }
    
    private List<Exercise> getExercisesForDay(int day) {
        List<Exercise> dayExercises = new ArrayList<>();
        
        if (day == 1) {
            // Day 1 uses original order
            dayExercises.addAll(baseExercises);
        } else {
            // Other days use shuffled order
            dayExercises.addAll(baseExercises);
            Collections.shuffle(dayExercises);
        }
        
        // Limit to 10 exercises maximum
        if (dayExercises.size() > 10) {
            dayExercises = dayExercises.subList(0, 10);
        }
        
        return dayExercises;
    }
    
    private HBox createExerciseItem(int number, Exercise exercise) {
        HBox item = new HBox(15);
        item.setPadding(new Insets(15));
        item.setAlignment(Pos.CENTER_LEFT);
        item.setStyle("-fx-background-color: #2d3436; " +
                "-fx-background-radius: 8; " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
        // Number circle
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
    
    // @Override
    // protected Button createButtonCard(String buttonText) {
    //     Button button = new Button(buttonText);
    //     button.setPrefWidth(100);
    //     button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
    //             "-fx-text-fill: white; " +
    //             "-fx-font-weight: bold; " +
    //             "-fx-padding: 6px 14px; " +
    //             "-fx-background-radius: 5px;");
        
    //     button.setOnMouseEntered(e -> {
    //         button.setStyle("-fx-background-color: linear-gradient(to right, #1a3a4a, #3d3560); " +
    //                 "-fx-text-fill: white; " +
    //                 "-fx-font-weight: bold; " +
    //                 "-fx-padding: 6px 14px; " +
    //                 "-fx-background-radius: 5px;");
    //     });
        
    //     button.setOnMouseExited(e -> {
    //         button.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
    //                 "-fx-text-fill: white; " +
    //                 "-fx-font-weight: bold; " +
    //                 "-fx-padding: 6px 14px; " +
    //                 "-fx-background-radius: 5px;");
    //     });
        
    //     return button;
    // }

    public Scene getScene() {
        return scene;
    }
}