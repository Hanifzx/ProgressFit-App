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

public class ExerciseProgramScene extends Template {
    private Scene scene;
    private Main mainApp;
    private User user;
    private ExerciseKatalog exerciseKatalog;
    private String programType;
    private String programTitle;
    private List<Exercise> baseExercises;
    private int currentDay = 1;
    
    private VBox sidebar;
    private VBox exerciseListContainer;
    private Label dayTitleLabel;

    public ExerciseProgramScene(Main mainApp, int width, int height, User user, String programType) {
        this.mainApp = mainApp;
        this.user = user;
        this.exerciseKatalog = new ExerciseKatalog();
        this.programType = programType;
        
        initializeProgramData();
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        root.setPrefSize(width, height);
        root.setMaxSize(width, height);
        
        root.setTop(createHeader());
        root.setCenter(createMainContent());
        
        scene = new Scene(root, width, height);
        
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
        
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", 13));
        userNameLabel.setTextFill(Color.WHITE);
        
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
        HBox mainContent = new HBox(0);
        
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
        
        Label sidebarTitle = new Label("Hari Latihan");
        sidebarTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        sidebarTitle.setTextFill(Color.web("#dfe6e9"));
        sidebarTitle.setPadding(new Insets(0, 0, 15, 20));
        
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
        
        updateDayButtonStyle(dayButton, day == currentDay);
        
        dayButton.setOnAction(e -> {
            currentDay = day;
            loadExercisesForDay(day);
            updateSidebarSelection();
        });
        
        return dayButton;
    }
    
    private void updateDayButtonStyle(Button button, boolean isSelected) {
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
        
        button.setOnMouseExited(e -> updateDayButtonStyle(button, isSelected));
    }

    private VBox createExerciseInfo() {
        VBox infoBox = new VBox(10);
        infoBox.setPadding(new Insets(20, 20, 0, 20));
        
        VBox infoCard = new VBox(8);
        infoCard.setPadding(new Insets(15));
        infoCard.setStyle("-fx-background-color: #353b48; -fx-background-radius: 8; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);");
        
        Label infoTitle = new Label("Info Latihan");
        infoTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        infoTitle.setTextFill(Color.web("#dfe6e9"));
        
        Label infoDesc = new Label("Program latihan yang sesuai dengan tujuan dan kebutuhanmu.");
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
        VBox dayButtons = (VBox) sidebar.getChildren().get(1);
        for (int i = 0; i < dayButtons.getChildren().size(); i++) {
            Button dayButton = (Button) dayButtons.getChildren().get(i);
            updateDayButtonStyle(dayButton, (i + 1) == currentDay);
        }
    }
    
    private VBox createExerciseSection() {
        VBox exerciseSection = new VBox(20);
        exerciseSection.setStyle("-fx-background-color: #1e272e;");
        exerciseSection.setPadding(new Insets(20));
        
        dayTitleLabel = new Label("Hari 1 - " + programTitle);
        dayTitleLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        dayTitleLabel.setTextFill(Color.web("#dfe6e9"));
        
        exerciseListContainer = new VBox(10);
        
        ScrollPane scrollPane = new ScrollPane(exerciseListContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #1e272e; -fx-background-color: #1e272e;");
        scrollPane.setPrefHeight(500);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        exerciseSection.getChildren().addAll(dayTitleLabel, scrollPane);
        return exerciseSection;
    }
    
    private void loadExercisesForDay(int day) {
        dayTitleLabel.setText("Hari " + day + " - " + programTitle);
        List<Exercise> dayExercises = getExercisesForDay(day);
        exerciseListContainer.getChildren().clear();
        for (int i = 0; i < dayExercises.size(); i++) {
            HBox exerciseItem = createExerciseItem(i + 1, dayExercises.get(i));
            exerciseListContainer.getChildren().add(exerciseItem);
        }
    }
    
    private List<Exercise> getExercisesForDay(int day) {
        List<Exercise> dayExercises = new ArrayList<>(baseExercises);
        Collections.shuffle(dayExercises);
        
        return dayExercises.size() > 10 ? dayExercises.subList(0, 10) : dayExercises;
    }
    
    private HBox createExerciseItem(int number, Exercise exercise) {
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