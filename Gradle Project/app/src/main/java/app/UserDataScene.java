package app;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class UserDataScene {
    
    private Scene scene;
    private Main mainApp;
    private User user;
    private TextField nameField;
    private ToggleGroup genderGroup;
    private TextField ageField;
    private TextField weightField;
    private TextField heightField;
    private Label bmiResultLabel;
    private Label bmiCategoryLabel;
    private Label bmrResultLabel;
    
    public UserDataScene(Main mainApp, int width, int height, User user) {
        this.mainApp = mainApp;
        this.user = user;
        
        // Create root container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e272e;");
        
        // Create header
        VBox header = new VBox(10);
        header.setPadding(new Insets(20));
        header.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376);");
        
        Label headerTitle = new Label("Data Pengguna");
        headerTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        headerTitle.setTextFill(Color.WHITE);
        
        // Progress bar
        // ProgressBar progressBar = new ProgressBar(0.33);
        // progressBar.setPrefWidth(Double.MAX_VALUE);
        // progressBar.setStyle("-fx-accent: #ff6b6b;");
        
        header.getChildren().addAll(headerTitle);
        
        // Create form
        VBox formContainer = new VBox(15);
        formContainer.setPadding(new Insets(20));
        formContainer.setStyle("-fx-background-color: #2d3436;");
        
        // Name field
        Label nameLabel = new Label("Nama");
        nameLabel.setTextFill(Color.web("#dfe6e9"));
        nameField = new TextField();
        nameField.setPromptText("Masukkan nama Anda");
        nameField.setStyle("-fx-background-color: #2d3436; " +
                "-fx-border-color: #636e72; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-text-fill: #dfe6e9; " +
                "-fx-padding: 8px;");
        
        // Gender field
        Label genderLabel = new Label("Jenis Kelamin");
        genderLabel.setTextFill(Color.web("#dfe6e9"));
        
        genderGroup = new ToggleGroup();
        RadioButton maleRadio = new RadioButton("Laki-laki");
        maleRadio.setToggleGroup(genderGroup);
        maleRadio.setUserData("male");
        maleRadio.setTextFill(Color.web("#dfe6e9"));
        
        RadioButton femaleRadio = new RadioButton("Perempuan");
        femaleRadio.setToggleGroup(genderGroup);
        femaleRadio.setUserData("female");
        femaleRadio.setTextFill(Color.web("#dfe6e9"));
        
        HBox genderBox = new HBox(20);
        genderBox.getChildren().addAll(maleRadio, femaleRadio);
        
        // Create grid for age, weight, height
        GridPane dataGrid = new GridPane();
        dataGrid.setHgap(15);
        dataGrid.setVgap(10);
        
        // Age field
        Label ageLabel = new Label("Usia (tahun)");
        ageLabel.setTextFill(Color.web("#dfe6e9"));
        ageField = new TextField();
        ageField.setPromptText("Usia");
        ageField.setStyle("-fx-background-color: #2d3436; " +
                "-fx-border-color: #636e72; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-text-fill: #dfe6e9; " +
                "-fx-padding: 8px;");
        
        // Weight field
        Label weightLabel = new Label("Berat Badan (kg)");
        weightLabel.setTextFill(Color.web("#dfe6e9"));
        weightField = new TextField();
        weightField.setPromptText("Berat");
        weightField.setStyle("-fx-background-color: #2d3436; " +
                "-fx-border-color: #636e72; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-text-fill: #dfe6e9; " +
                "-fx-padding: 8px;");
        
        // Height field
        Label heightLabel = new Label("Tinggi Badan (cm)");
        heightLabel.setTextFill(Color.web("#dfe6e9"));
        heightField = new TextField();
        heightField.setPromptText("Tinggi");
        heightField.setStyle("-fx-background-color: #2d3436; " +
                "-fx-border-color: #636e72; " +
                "-fx-border-width: 1px; " +
                "-fx-border-radius: 5px; " +
                "-fx-text-fill: #dfe6e9; " +
                "-fx-padding: 8px;");
        
        // Add to grid
        dataGrid.add(ageLabel, 0, 0);
        dataGrid.add(ageField, 0, 1);
        dataGrid.add(weightLabel, 1, 0);
        dataGrid.add(weightField, 1, 1);
        dataGrid.add(heightLabel, 2, 0);
        dataGrid.add(heightField, 2, 1);
        
        // Set column constraints
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.33);
        dataGrid.getColumnConstraints().addAll(column1, column2, column3);
        
        // Results section
        VBox resultsBox = new VBox(10);
        resultsBox.setPadding(new Insets(15));
        resultsBox.setStyle("-fx-background-color: #1a3a4a; -fx-background-radius: 5;");
        
        Label resultsTitle = new Label("Hasil Perhitungan");
        resultsTitle.setFont(Font.font("System", FontWeight.BOLD, 16));
        resultsTitle.setTextFill(Color.web("#dfe6e9"));
        
        // BMI result
        HBox bmiBmrBox = new HBox(10);
        VBox bmiLabelBox = new VBox(5);
        Label bmiLabel = new Label("BMI (Body Mass Index)");
        bmiLabel.setTextFill(Color.web("#a0aec0"));
        bmiResultLabel = new Label("-");
        bmiResultLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        bmiResultLabel.setTextFill(Color.web("#ff6b6b"));
        bmiCategoryLabel = new Label("-");
        bmiCategoryLabel.setTextFill(Color.web("#a0aec0"));
        bmiLabelBox.getChildren().addAll(bmiLabel, bmiResultLabel, bmiCategoryLabel);
        
        // BMR result
        VBox bmrLabelBox = new VBox(5);
        Label bmrLabel = new Label("BMR (Basal Metabolic Rate)");
        bmrLabel.setTextFill(Color.web("#a0aec0"));
        bmrResultLabel = new Label("-");
        bmrResultLabel.setFont(Font.font("System", FontWeight.BOLD, 20));
        bmrResultLabel.setTextFill(Color.web("#ff6b6b"));
        Label bmrUnitLabel = new Label("kalori/hari");
        bmrUnitLabel.setTextFill(Color.web("#a0aec0"));
        bmrLabelBox.getChildren().addAll(bmrLabel, bmrResultLabel, bmrUnitLabel);
        
        bmiBmrBox.getChildren().addAll(bmiLabelBox, bmrLabelBox);
        HBox.setHgrow(bmiLabelBox, Priority.ALWAYS);
        HBox.setHgrow(bmrLabelBox, Priority.ALWAYS);
        
        resultsBox.getChildren().addAll(resultsTitle, bmiBmrBox);
        
        // Buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        
        Button calculateButton = new Button("Hitung");
        calculateButton.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 20px; " +
                "-fx-background-radius: 5px;");
        
        calculateButton.setOnMouseEntered(e -> {
            calculateButton.setStyle("-fx-background-color: linear-gradient(to right, #1a3a4a, #3d3560); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 5px;");
        });
        
        calculateButton.setOnMouseExited(e -> {
            calculateButton.setStyle("-fx-background-color: linear-gradient(to right, #2b5876, #4e4376); " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 5px;");
        });
        
        calculateButton.setOnAction(e -> calculateResults());
        
        Button continueButton = new Button("Lanjutkan");
        continueButton.setStyle("-fx-background-color: #4a5568; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 10px 20px; " +
                "-fx-background-radius: 5px;");
        
        continueButton.setOnMouseEntered(e -> {
            continueButton.setStyle("-fx-background-color: #2d3748; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 5px;");
        });
        
        continueButton.setOnMouseExited(e -> {
            continueButton.setStyle("-fx-background-color: #4a5568; " +
                    "-fx-text-fill: white; " +
                    "-fx-font-weight: bold; " +
                    "-fx-padding: 10px 20px; " +
                    "-fx-background-radius: 5px;");
        });
        
        continueButton.setOnAction(e -> {
            if (validateAndSaveData()) {
                mainApp.showMainMenuScene();
            }
        });
        
        buttonBox.getChildren().addAll(calculateButton, continueButton);
        
        // Add all to form container
        formContainer.getChildren().addAll(
            nameLabel, nameField,
            genderLabel, genderBox,
            new Separator(),
            dataGrid,
            new Separator(),
            resultsBox,
            buttonBox
        );
        
        // Add to root
        root.setTop(header);
        root.setCenter(formContainer);
        
        // Create scene
        scene = new Scene(root, width, height);
    }
    
    private void calculateResults() {
        try {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            Toggle selectedGender = genderGroup.getSelectedToggle();
            
            if (selectedGender == null) {
                showAlert("Mohon pilih jenis kelamin");
                return;
            }
            
            // Update user data
            user.setWeight(weight);
            user.setHeight(height);
            user.setAge(age);
            user.setGender((String) selectedGender.getUserData());
            
            // Calculate BMI and BMR
            user.calculateBMI();
            user.calculateBMR();
            
            // Update UI
            DecimalFormat df = new DecimalFormat("0.0");
            bmiResultLabel.setText(df.format(user.getBmi()));
            bmiCategoryLabel.setText(user.getBmiCategory());
            bmrResultLabel.setText(String.valueOf(Math.round(user.getBmr())));
            
        } catch (NumberFormatException e) {
            showAlert("Mohon masukkan data yang valid");
        }
    }
    
    private boolean validateAndSaveData() {
        if (nameField.getText().isEmpty()) {
            showAlert("Mohon masukkan nama Anda");
            return false;
        }
        
        if (bmiResultLabel.getText().equals("-") || bmrResultLabel.getText().equals("-")) {
            showAlert("Mohon lakukan perhitungan terlebih dahulu");
            return false;
        }
        
        // Save name to user
        user.setName(nameField.getText());
        return true;
    }
    
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Peringatan");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public Scene getScene() {
        return scene;
    }
}