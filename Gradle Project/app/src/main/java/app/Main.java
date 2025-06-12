package app;

import javafx.application.Application;
import javafx.stage.Stage; 

public class Main extends Application {
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Stage primaryStage;
    private User user;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.user = new User();
        
        primaryStage.setTitle("Home Workout App");
        primaryStage.setResizable(true);
        
        // Set initial scene
        showOpeningScene();
        
        primaryStage.show();
    }
    
    public void showOpeningScene() {
        OpeningScene openingScene = new OpeningScene(this, WIDTH, HEIGHT);
        primaryStage.setScene(openingScene.getScene());
    }
    
    public void showUserDataScene() {
        UserDataScene userDataScene = new UserDataScene(this, WIDTH, HEIGHT, user);
        primaryStage.setScene(userDataScene.getScene());
    }
    
    public void showMainMenuScene() {
        MainMenuScene mainMenuScene = new MainMenuScene(this, WIDTH, HEIGHT, user);
        primaryStage.setScene(mainMenuScene.getScene());
    }

    public void showDailyExerciseScene() {
        DailyExerciseScene dailyExerciseScene = new DailyExerciseScene(this, WIDTH, HEIGHT, user);
        primaryStage.setScene(dailyExerciseScene.getScene());
    }
    
    public void showExerciseProgramScene(String programType) {
        ExerciseProgramScene exerciseProgramScene = new ExerciseProgramScene(this, WIDTH, HEIGHT, user, programType);
        
        // PENTING: Set ukuran stage secara eksplisit
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.centerOnScreen(); // Optional: center window
        
        primaryStage.setScene(exerciseProgramScene.getScene());
    }

    public void showBodyChallengeMenuScene() {
        BodyChallengeMenuScene bodyChallengeMenuScene = new BodyChallengeMenuScene(this, WIDTH, HEIGHT, user);
        primaryStage.setScene(bodyChallengeMenuScene.getScene());
    }



    public User getUser() {
        return user;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}