package app;

import javafx.application.Application;
import javafx.stage.Stage; 

public class Main extends Application {
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Stage primaryStage;
    private User user;
    
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

    public void showLatihanHarianScene() {
        DailyExerciseScene latihanHarianScene = new DailyExerciseScene(this, WIDTH, HEIGHT, user);
        primaryStage.setScene(latihanHarianScene.getScene());
    }
    
    public User getUser() {
        return user;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}