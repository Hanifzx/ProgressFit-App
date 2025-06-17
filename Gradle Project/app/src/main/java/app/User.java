package app;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private String gender;
    private int age;
    private double weight;
    private double height;
    private double bmi;
    private double bmr;
    private String bmiCategory;

    private Map<String, Map<Integer, Boolean>> dailyExerciseCompletion = new HashMap<>();

    private Map<String, Map<Integer, Boolean>> bodyChallengeCompletion = new HashMap<>();
    
    public User() {
        
        dailyExerciseCompletion.put("weight_gain", new HashMap<>());
        dailyExerciseCompletion.put("weight_loss", new HashMap<>());
        dailyExerciseCompletion.put("stamina", new HashMap<>());

        bodyChallengeCompletion.put("upper_body", new HashMap<>());
        bodyChallengeCompletion.put("lower_body", new HashMap<>());

        for (String type : dailyExerciseCompletion.keySet()) {
            for (int i = 1; i <= 6; i++) {
                dailyExerciseCompletion.get(type).put(i, false);
            }
        }
        for (String type : bodyChallengeCompletion.keySet()) {
            for (int i = 1; i <= 4; i++) {
                bodyChallengeCompletion.get(type).put(i, false);
            }
        }
    }
    
    public void calculateBMI() {
        if (height > 0 && weight > 0) {
            double heightInMeters = height / 100.0;
            bmi = weight / (heightInMeters * heightInMeters);
            
            if (bmi < 18.5) {
                bmiCategory = "Berat Badan Kurang";
            } else if (bmi < 25) {
                bmiCategory = "Berat Badan Normal";
            } else if (bmi < 30) {
                bmiCategory = "Berat Badan Berlebih";
            } else {
                bmiCategory = "Obesitas";
            }
        }
    }
    
    public void calculateBMR() {
        if (height > 0 && weight > 0 && age > 0 && gender != null) {
            if (gender.equals("male")) {
                bmr = 10 * weight + 6.25 * height - 5 * age + 5;
            } else {
                bmr = 10 * weight + 6.25 * height - 5 * age - 161;
            }
        }
    }
    
    // getter dan setter
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public double getHeight() {
        return height;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public double getBmi() {
        return bmi;
    }
    
    public double getBmr() {
        return bmr;
    }
    
    public String getBmiCategory() {
        return bmiCategory;
    }

    // Metode untuk mengatur status selesai latihan harian
    public void setDailyExerciseDayCompleted(String programType, int day, boolean completed) {
        dailyExerciseCompletion.computeIfAbsent(programType, k -> new HashMap<>()).put(day, completed);
    }

    // Metode untuk memeriksa status selesai latihan harian
    public boolean isDailyExerciseDayCompleted(String programType, int day) {
        return dailyExerciseCompletion.getOrDefault(programType, new HashMap<>()).getOrDefault(day, false);
    }

    // Metode untuk mendapatkan semua status selesai latihan harian
    public Map<String, Map<Integer, Boolean>> getDailyExerciseCompletion() {
        return dailyExerciseCompletion;
    }

    // Metode untuk mengatur status selesai tantangan fokus tubuh
    public void setChallengeWeekCompleted(String challengeType, int week, boolean completed) {
        bodyChallengeCompletion.computeIfAbsent(challengeType, k -> new HashMap<>()).put(week, completed);
    }

    // Metode untuk memeriksa status selesai tantangan fokus tubuh
    public boolean isChallengeWeekCompleted(String challengeType, int week) {
        return bodyChallengeCompletion.getOrDefault(challengeType, new HashMap<>()).getOrDefault(week, false);
    }

    // Metode untuk mendapatkan semua status selesai tantangan fokus tubuh
    public Map<String, Map<Integer, Boolean>> getBodyChallengeCompletion() {
        return bodyChallengeCompletion;
    }
}
