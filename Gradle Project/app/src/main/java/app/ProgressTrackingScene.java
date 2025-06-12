package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProgressTrackingScene extends Template {
    private Scene scene;
    private Main mainApp;
    private User user;
    
    private VBox weightInputForm;
    private VBox evaluationResults;
    private TextField weightInput;
    private ScrollPane mainScrollPane;
    
    private double previousWeight;
    private double currentWeight;
    
    private int completedWorkouts = 4;
    private int totalWorkouts = 6;
    private int completedFocusWorkouts = 2;
    private int totalFocusWorkouts = 4;
    
    private List<Double> weightHistory = new ArrayList<>();
    private List<LocalDate> dateHistory = new ArrayList<>();
    
    private Label weightChangeValueLabel;
    private Label weightChangeBadgeLabel;
    private Label motivationTextLabel;
    private VBox previousWeightMetric;
    private VBox currentWeightMetric;
    private Label bmiValueLabel;
    private Label bmiCategoryLabel;
    private Label bmrValueLabel;
    
    private static final String CARD_STYLE = "-fx-background-color: #2d3436; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 3, 0, 0, 1);";
    private static final String INPUT_STYLE = "-fx-background-color: #2d3436; -fx-border-color: #636e72; -fx-border-width: 1px; -fx-border-radius: 5px; -fx-text-fill: #dfe6e9; -fx-padding: 8px;";
    
    public ProgressTrackingScene(Main mainApp, int width, int height, User user) {
        this.mainApp = mainApp;
        this.user = user;
        this.previousWeight = user.getWeight();
        this.currentWeight = user.getWeight();
        this.weightHistory.add(user.getWeight());
        this.dateHistory.add(LocalDate.now());
        
        BorderPane rootContainer = new BorderPane();
        rootContainer.setStyle("-fx-background-color: #1e272e;");
        rootContainer.setPrefSize(width, height);
        rootContainer.setMaxSize(width, height);
        
        rootContainer.setTop(createHeader());
        rootContainer.setCenter(createScrollableContent());
        
        scene = new Scene(rootContainer, width, height);
        showInitialEvaluation();
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
        
        Label headerTitle = new Label("Progres Latihan");
        headerTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        headerTitle.setTextFill(Color.WHITE);
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label userNameLabel = new Label(user.getName());
        userNameLabel.setFont(Font.font("System", FontWeight.NORMAL, 13));
        userNameLabel.setTextFill(Color.WHITE);
        Circle userIcon = new Circle(15, Color.web("#ff6b6b"));
        
        header.getChildren().addAll(backButton, headerTitle, spacer, userNameLabel, userIcon);
        return header;
    }
    
    private ScrollPane createScrollableContent() {
        VBox mainContent = new VBox();
        mainContent.setStyle("-fx-background-color: #1e272e;");
        mainContent.setPadding(new Insets(20));
        mainContent.setSpacing(20);
        mainContent.setAlignment(Pos.TOP_CENTER);
        
        weightInputForm = createWeightInputForm();
        evaluationResults = createEvaluationResults();
        
        mainContent.getChildren().addAll(weightInputForm, evaluationResults);
        
        mainScrollPane = new ScrollPane(mainContent);
        mainScrollPane.setFitToWidth(true);
        mainScrollPane.setStyle("-fx-background: #1e272e; -fx-background-color: #1e272e;");
        mainScrollPane.setVvalue(0.0);
        mainScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        mainScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        
        return mainScrollPane;
    }
    
    private VBox createWeightInputForm() {
        VBox form = new VBox(15);
        form.setPadding(new Insets(20));
        form.setMaxWidth(600);
        form.setAlignment(Pos.TOP_CENTER);
        form.setStyle(CARD_STYLE);
        
        Label title = new Label("Update Berat Badan");
        title.setFont(Font.font("System", FontWeight.BOLD, 18));
        title.setTextFill(Color.web("#dfe6e9"));
        
        Label description = new Label("Masukkan berat badan Anda saat ini untuk memperbarui progres latihan Anda.");
        description.setFont(Font.font("System", FontWeight.NORMAL, 13));
        description.setTextFill(Color.web("#a0aec0"));
        description.setWrapText(true);
        
        VBox weightInputContainer = new VBox(8);
        Label weightLabel = new Label("BERAT BADAN (KG)");
        weightLabel.setFont(Font.font("System", FontWeight.BOLD, 12));
        weightLabel.setTextFill(Color.web("#a0aec0"));
        
        weightInput = new TextField();
        weightInput.setPromptText("Berat badan saat ini: " + String.format("%.1f", user.getWeight()) + " kg");
        weightInput.setStyle(INPUT_STYLE);
        
        weightInputContainer.getChildren().addAll(weightLabel, weightInput);
        
        Button evaluateButton = createButtonCard("Update");
        evaluateButton.setOnAction(e -> updateProgress());
        
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(Pos.CENTER_RIGHT);
        buttonContainer.setPadding(new Insets(10, 0, 0, 0));
        buttonContainer.getChildren().add(evaluateButton);
        
        form.getChildren().addAll(title, description, weightInputContainer, buttonContainer);
        return form;
    }
    
    private VBox createEvaluationResults() {
        VBox resultsContainer = new VBox(20);
        resultsContainer.setPadding(new Insets(0, 0, 40, 0));
        resultsContainer.setAlignment(Pos.TOP_CENTER);
        
        // Summary Card
        VBox summaryCard = new VBox(15);
        summaryCard.setPadding(new Insets(20));
        summaryCard.setMaxWidth(600);
        summaryCard.setAlignment(Pos.TOP_CENTER);
        summaryCard.setStyle(CARD_STYLE);
        
        VBox weightChangeSection = new VBox(10);
        weightChangeSection.setPadding(new Insets(0, 0, 20, 0));
        
        Label sectionTitle = new Label("PERUBAHAN BERAT BADAN");
        sectionTitle.setFont(Font.font("System", FontWeight.BOLD, 12));
        sectionTitle.setTextFill(Color.web("#a0aec0"));
        
        HBox weightChangeInfo = new HBox(10);
        weightChangeInfo.setAlignment(Pos.CENTER_LEFT);
        
        weightChangeValueLabel = new Label("0.0 kg");
        weightChangeValueLabel.setFont(Font.font("System", FontWeight.BOLD, 24));
        weightChangeValueLabel.setTextFill(Color.WHITE);
        
        weightChangeBadgeLabel = new Label("Stabil");
        weightChangeBadgeLabel.setStyle("-fx-background-color: rgba(33, 150, 243, 0.2); -fx-text-fill: #2196F3; -fx-padding: 4 10; -fx-background-radius: 12; -fx-font-weight: bold; -fx-font-size: 12;");
        
        weightChangeInfo.getChildren().addAll(weightChangeValueLabel, weightChangeBadgeLabel);
        weightChangeSection.getChildren().addAll(sectionTitle, weightChangeInfo);
        
        VBox motivationSection = new VBox(10);
        Label motivationTitle = new Label("MOTIVASI");
        motivationTitle.setFont(Font.font("System", FontWeight.BOLD, 12));
        motivationTitle.setTextFill(Color.web("#a0aec0"));
        
        motivationTextLabel = new Label(generateMotivationText(0.0, user.getBmi()));
        motivationTextLabel.setFont(Font.font("System", FontWeight.NORMAL, 13));
        motivationTextLabel.setTextFill(Color.web("#dfe6e9"));
        motivationTextLabel.setStyle("-fx-font-style: italic;");
        motivationTextLabel.setWrapText(true);
        
        motivationSection.getChildren().addAll(motivationTitle, motivationTextLabel);
        summaryCard.getChildren().addAll(weightChangeSection, motivationSection);
        
        // Weight Metrics Card
        VBox weightCard = new VBox(15);
        weightCard.setPadding(new Insets(20));
        weightCard.setMaxWidth(600);
        weightCard.setAlignment(Pos.TOP_CENTER);
        weightCard.setStyle(CARD_STYLE);
        
        Label weightCardTitle = new Label("Metrik Berat Badan");
        weightCardTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        weightCardTitle.setTextFill(Color.WHITE);
        
        HBox metricsGrid = new HBox(15);
        
        previousWeightMetric = createMetricBox("Berat Badan Terakhir", String.format("%.1f kg", previousWeight), "BB Awal");
        currentWeightMetric = createMetricBox("Berat Badan Sekarang", String.format("%.1f kg", currentWeight), "BB Hari Ini");
        
        HBox.setHgrow(previousWeightMetric, Priority.ALWAYS);
        HBox.setHgrow(currentWeightMetric, Priority.ALWAYS);
        metricsGrid.getChildren().addAll(previousWeightMetric, currentWeightMetric);
        
        weightCard.getChildren().addAll(weightCardTitle, metricsGrid);
        
        // Body Metrics Card
        VBox bodyCard = new VBox(15);
        bodyCard.setPadding(new Insets(20));
        bodyCard.setMaxWidth(600);
        bodyCard.setAlignment(Pos.TOP_CENTER);
        bodyCard.setStyle(CARD_STYLE);
        
        Label bodyCardTitle = new Label("Metrik Tubuh");
        bodyCardTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        bodyCardTitle.setTextFill(Color.WHITE);
        
        HBox bodyMetricsGrid = new HBox(15);
        bodyMetricsGrid.setPadding(new Insets(0, 0, 15, 0));
        
        String bmiColor = getBMIColor(user.getBmi());
        VBox bmiMetric = createBodyMetric("BMI (Body Mass Index)", String.format("%.1f", user.getBmi()), user.getBmiCategory(), bmiColor);
        VBox bmrMetric = createBodyMetric("BMR (Basal Metabolic Rate)", String.format("%.0f", user.getBmr()), "kalori/hari", null);
        
        bmiValueLabel = (Label) bmiMetric.getChildren().get(1);
        bmiCategoryLabel = (Label) bmiMetric.getChildren().get(2);
        bmrValueLabel = (Label) bmrMetric.getChildren().get(1);
        
        HBox.setHgrow(bmiMetric, Priority.ALWAYS);
        HBox.setHgrow(bmrMetric, Priority.ALWAYS);
        bodyMetricsGrid.getChildren().addAll(bmiMetric, bmrMetric);
        
        bodyCard.getChildren().addAll(bodyCardTitle, bodyMetricsGrid);
        
        // Workout Progress Card
        VBox workoutCard = new VBox(15);
        workoutCard.setPadding(new Insets(20));
        workoutCard.setMaxWidth(600);
        workoutCard.setAlignment(Pos.TOP_CENTER);
        workoutCard.setStyle(CARD_STYLE);
        
        Label workoutCardTitle = new Label("Progres Latihan");
        workoutCardTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        workoutCardTitle.setTextFill(Color.WHITE);
        
        VBox sessionsSection = createProgressSection("SESI LATIHAN HARIAN", completedWorkouts, totalWorkouts, "#2196F3");
        VBox focusSection = createProgressSection("TANTANGAN FOKUS TUBUH", completedFocusWorkouts, totalFocusWorkouts, "#FF9800");
        
        workoutCard.getChildren().addAll(workoutCardTitle, sessionsSection, focusSection);
        
        resultsContainer.getChildren().addAll(summaryCard, weightCard, bodyCard, workoutCard);
        return resultsContainer;
    }
    
    private VBox createProgressSection(String title, int completed, int total, String color) {
        VBox section = new VBox(10);
        section.setPadding(new Insets(0, 0, 15, 0));
        
        Label sectionTitle = new Label(title);
        sectionTitle.setFont(Font.font("System", FontWeight.BOLD, 12));
        sectionTitle.setTextFill(Color.web("#a0aec0"));
        
        HBox progressInfo = new HBox(10);
        progressInfo.setAlignment(Pos.CENTER_LEFT);
        progressInfo.setPadding(new Insets(0, 0, 10, 0));
        
        Label progressValue = new Label(completed + "/" + total);
        progressValue.setFont(Font.font("System", FontWeight.BOLD, 20));
        progressValue.setTextFill(Color.WHITE);
        
        Label progressLabel = new Label("Sesi");
        progressLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        progressLabel.setTextFill(Color.web(color));
        
        progressInfo.getChildren().addAll(progressValue, progressLabel);
        
        HBox indicators = new HBox(6);
        for (int i = 1; i <= total; i++) {
            StackPane indicator = new StackPane();
            Rectangle rect = new Rectangle(35, 35);
            rect.setArcWidth(4);
            rect.setArcHeight(4);
            rect.setFill(i <= completed ? Color.web("#4CAF50") : Color.web("rgba(255, 255, 255, 0.1)"));
            
            Label dayNumber = new Label(String.valueOf(i));
            dayNumber.setFont(Font.font("System", FontWeight.BOLD, 12));
            dayNumber.setTextFill(Color.WHITE);
            
            indicator.getChildren().addAll(rect, dayNumber);
            indicators.getChildren().add(indicator);
        }
        
        section.getChildren().addAll(sectionTitle, progressInfo, indicators);
        return section;
    }
    
    private VBox createMetricBox(String label, String value, String subtext) {
        VBox metricBox = new VBox(5);
        metricBox.setAlignment(Pos.CENTER);
        metricBox.setPadding(new Insets(15));
        metricBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.05); -fx-background-radius: 8;");
        
        Label metricLabel = new Label(label);
        metricLabel.setFont(Font.font("System", FontWeight.NORMAL, 12));
        metricLabel.setTextFill(Color.web("#a0aec0"));
        
        Label metricValue = new Label(value);
        metricValue.setFont(Font.font("System", FontWeight.BOLD, 20));
        metricValue.setTextFill(Color.WHITE);
        
        Label metricSubtext = new Label(subtext);
        metricSubtext.setFont(Font.font("System", FontWeight.NORMAL, 11));
        metricSubtext.setTextFill(Color.web("#a0aec0"));
        
        metricBox.getChildren().addAll(metricLabel, metricValue, metricSubtext);
        return metricBox;
    }
    
    private VBox createBodyMetric(String label, String value, String subtext, String badgeColor) {
        VBox metric = new VBox(5);
        metric.setAlignment(Pos.CENTER);
        metric.setPadding(new Insets(15));
        metric.setStyle("-fx-background-color: rgba(255, 255, 255, 0.05); -fx-background-radius: 8;");
        
        Label metricLabel = new Label(label);
        metricLabel.setFont(Font.font("System", FontWeight.NORMAL, 12));
        metricLabel.setTextFill(Color.web("#a0aec0"));
        
        Label metricValue = new Label(value);
        metricValue.setFont(Font.font("System", FontWeight.BOLD, 20));
        metricValue.setTextFill(Color.WHITE);
        
        if (badgeColor != null) {
            Label badge = new Label(subtext);
            badge.setStyle(String.format("-fx-background-color: rgba(%s, 0.2); -fx-text-fill: %s; -fx-padding: 4 10; -fx-background-radius: 12; -fx-font-weight: bold; -fx-font-size: 12;",
                    getRGBFromHex(badgeColor), badgeColor));
            metric.getChildren().addAll(metricLabel, metricValue, badge);
        } else {
            Label metricSubtext = new Label(subtext);
            metricSubtext.setFont(Font.font("System", FontWeight.NORMAL, 11));
            metricSubtext.setTextFill(Color.web("#a0aec0"));
            metric.getChildren().addAll(metricLabel, metricValue, metricSubtext);
        }
        
        return metric;
    }
    
    private String getRGBFromHex(String hex) {
        Color color = Color.web(hex);
        return String.format("%.0f, %.0f, %.0f", color.getRed() * 255, color.getGreen() * 255, color.getBlue() * 255);
    }
    
    private String getBMIColor(double bmi) {
        if (bmi < 18.5) return "#FFA500";
        else if (bmi < 25) return "#4CAF50";
        else if (bmi < 30) return "#FF9800";
        else return "#F44336";
    }
    
    private void showInitialEvaluation() {
        updateEvaluationUI(0.0, user.getBmi(), user.getBmr(), previousWeight);
    }
    
    private void updateProgress() {
        try {
            String inputText = weightInput.getText().trim();
            if (inputText.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Silakan masukkan berat badan terlebih dahulu.").showAndWait();
                return;
            }
            
            double newWeight = Double.parseDouble(inputText);
            if (newWeight <= 0) {
                new Alert(Alert.AlertType.ERROR, "Berat badan harus lebih dari 0 kg.").showAndWait();
                return;
            }
            
            double oldWeight = user.getWeight();
            user.setWeight(newWeight);
            user.calculateBMI();
            user.calculateBMR();
            
            double weightChange = newWeight - oldWeight;
            
            weightHistory.add(newWeight);
            dateHistory.add(LocalDate.now());
            currentWeight = newWeight;
            
            updateEvaluationUI(weightChange, user.getBmi(), user.getBmr(), oldWeight);
            previousWeight = oldWeight;
            
            weightInput.clear();
            weightInput.setPromptText("Berat badan saat ini: " + String.format("%.1f", newWeight) + " kg");
            
            if (mainScrollPane != null) mainScrollPane.setVvalue(0.3);
            
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Silakan masukkan berat badan yang valid.").showAndWait();
        }
    }
    
    private void updateEvaluationUI(double weightChange, double bmi, double bmr, double oldWeight) {
        String changeText = String.format("%+.1f kg", weightChange);
        String badgeText = weightChange > 0 ? "Naik" : weightChange < 0 ? "Turun" : "Stabil";
        String badgeColor = weightChange > 0 ? "#4CAF50" : weightChange < 0 ? "#F44336" : "#2196F3";
        
        if (weightChangeValueLabel != null) weightChangeValueLabel.setText(changeText);
        if (weightChangeBadgeLabel != null) {
            weightChangeBadgeLabel.setText(badgeText);
            weightChangeBadgeLabel.setStyle(String.format("-fx-background-color: rgba(%s, 0.2); -fx-text-fill: %s; -fx-padding: 4 10; -fx-background-radius: 12; -fx-font-weight: bold; -fx-font-size: 12;",
                    getRGBFromHex(badgeColor), badgeColor));
        }
        
        if (previousWeightMetric != null && previousWeightMetric.getChildren().size() >= 2) {
            ((Label) previousWeightMetric.getChildren().get(1)).setText(String.format("%.1f kg", oldWeight));
        }
        if (currentWeightMetric != null && currentWeightMetric.getChildren().size() >= 2) {
            ((Label) currentWeightMetric.getChildren().get(1)).setText(String.format("%.1f kg", currentWeight));
        }
        
        if (bmiValueLabel != null) bmiValueLabel.setText(String.format("%.1f", bmi));
        if (bmiCategoryLabel != null) {
            String bmiCategory = user.getBmiCategory();
            String bmiColor = getBMIColor(bmi);
            bmiCategoryLabel.setText(bmiCategory);
            bmiCategoryLabel.setStyle(String.format("-fx-background-color: rgba(%s, 0.2); -fx-text-fill: %s; -fx-padding: 4 10; -fx-background-radius: 12; -fx-font-weight: bold; -fx-font-size: 12;",
                    getRGBFromHex(bmiColor), bmiColor));
        }
        if (bmrValueLabel != null) bmrValueLabel.setText(String.format("%.0f", bmr));
        
        if (motivationTextLabel != null) motivationTextLabel.setText(generateMotivationText(weightChange, bmi));
    }
    
    private String generateMotivationText(double weightChange, double bmi) {
        if (weightChange > 0 && bmi < 25) {
            return "Kenaikan berat badan Anda menunjukkan progres yang baik dalam program penambahan massa otot. Tetap konsisten dengan latihan dan pola makan Anda!";
        } else if (weightChange > 0 && bmi >= 25) {
            return "Berat badan Anda meningkat, namun BMI Anda di atas normal. Fokus pada latihan kardio dan perhatikan asupan kalori untuk mencapai berat badan ideal.";
        } else if (weightChange < 0 && bmi > 18.5) {
            return "Penurunan berat badan Anda menunjukkan progres yang baik dalam program penurunan berat badan. Tetap konsisten dengan latihan dan pola makan Anda!";
        } else if (weightChange < 0 && bmi <= 18.5) {
            return "Berat badan Anda menurun, namun BMI Anda di bawah normal. Fokus pada latihan kekuatan dan tingkatkan asupan kalori untuk mencapai berat badan ideal.";
        } else {
            return "Berat badan Anda stabil. Tetap konsisten dengan latihan dan pola makan Anda untuk mencapai tujuan kebugaran Anda!";
        }
    }
    
    public Scene getScene() { return scene; }
    
    public void updateWorkoutProgress(int completed, int total, int completedFocus, int totalFocus) {
        this.completedWorkouts = completed;
        this.totalWorkouts = total;
        this.completedFocusWorkouts = completedFocus;
        this.totalFocusWorkouts = totalFocus;
    }
    
    public void refreshUserData() {
        user.calculateBMI();
        user.calculateBMR();
        currentWeight = user.getWeight();
        
        double weightChange = currentWeight - previousWeight;
        updateEvaluationUI(weightChange, user.getBmi(), user.getBmr(), previousWeight);
        
        weightInput.setPromptText("Berat badan saat ini: " + String.format("%.1f", currentWeight) + " kg");
    }
    
    public void scrollToTop() {
        if (mainScrollPane != null) mainScrollPane.setVvalue(0.0);
    }
}