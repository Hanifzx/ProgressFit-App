package app;

public class User {
    private String name;
    private String gender;
    private int age;
    private double weight;
    private double height;
    private double bmi;
    private double bmr;
    private String bmiCategory;
    
    public User() {
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
}