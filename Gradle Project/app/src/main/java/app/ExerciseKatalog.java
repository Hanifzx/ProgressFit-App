package app;

import java.util.ArrayList;
import java.util.List;

class Exercise {
    private String name;
    private String sets; // Could be "3 set x 12 repetisi" or "30 detik"

    public Exercise(String name, String sets) {
        this.name = name;
        this.sets = sets;
    }

    public String getName() { return name; }
    public String getSets() { return sets; }
}

// Catalog of all exercises
public class ExerciseKatalog {

    // Method to get exercises for weight gain (Menambah BB)
// Method to get exercises for weight gain (Menambah BB)
    public List<Exercise> getGerakanMenambahBB() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Push-up", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Incline Push-up (kursi)", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Dips (pakai kursi)", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Wall Push-up", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Bodyweight Squat", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Lunges", "3 set x 12 repetisi (per kaki)"));
        exercises.add(new Exercise("Glute Bridge", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Calf Raise", "3 set x 20 repetisi"));
        exercises.add(new Exercise("Wall Sit", "3 set x 30 detik"));
        exercises.add(new Exercise("Leg Raise", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Superman", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Bird Dog", "3 set x 10 repetisi (per sisi)"));
        exercises.add(new Exercise("Arm Circles", "3 set x 30 detik"));
        exercises.add(new Exercise("Shoulder Tap", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Knee Push-up", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Reverse Crunch", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Step-up (tangga/kursi)", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Wall Plank", "3 set x 30 detik"));
        exercises.add(new Exercise("Squat Hold", "3 set x 20 detik"));
        exercises.add(new Exercise("Side-Lying Leg Raise", "3 set x 15 repetisi"));
        return exercises;
    }


    // Method to get exercises for weight loss (Menurunkan BB)
    public List<Exercise> getGerakanMenurunkanBB() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Jumping Jacks", "3 set x 45 detik"));
        exercises.add(new Exercise("High Knees", "3 set x 45 detik"));
        exercises.add(new Exercise("Burpees", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Mountain Climbers", "3 set x 45 detik"));
        exercises.add(new Exercise("Skater Jumps", "3 set x 30 detik"));
        exercises.add(new Exercise("Squat Jumps", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Lunges", "3 set x 10 repetisi (per kaki)"));
        exercises.add(new Exercise("Plank to Push-up", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Step-up (kursi/tangga)", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Shadow Boxing", "3 set x 2 menit"));
        exercises.add(new Exercise("Side Lunges", "3 set x 10 repetisi (per sisi)"));
        exercises.add(new Exercise("Standing Oblique Crunch", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Bicycle Crunches", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Leg Raises", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Plank", "3 set x 45 detik"));
        exercises.add(new Exercise("Wall Sit", "3 set x 45 detik"));
        exercises.add(new Exercise("Bear Crawls", "3 set x 30 detik"));
        exercises.add(new Exercise("Flutter Kicks", "3 set x 30 detik"));
        exercises.add(new Exercise("Tuck Jumps", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Side Plank", "3 set x 30 detik"));
        return exercises;
    }


    // Method to get exercises for stamina (Menjaga Stamina)
    public List<Exercise> getGerakanMenjagaStamina() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Jogging di tempat", "3 set x 2 menit"));
        exercises.add(new Exercise("Jumping Jacks", "3 set x 45 detik"));
        exercises.add(new Exercise("High Knees", "3 set x 45 detik"));
        exercises.add(new Exercise("Butt Kicks", "3 set x 45 detik"));
        exercises.add(new Exercise("Mountain Climbers", "3 set x 45 detik"));
        exercises.add(new Exercise("Skater Jumps", "3 set x 30 detik"));
        exercises.add(new Exercise("Step-up (tangga/kursi)", "3 set x 1 menit"));
        exercises.add(new Exercise("Shadow Boxing", "3 set x 2 menit"));
        exercises.add(new Exercise("Burpees (versi ringan)", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Side-to-Side Steps", "3 set x 1 menit"));
        exercises.add(new Exercise("Jump Rope (tanpa tali)", "3 set x 1 menit"));
        exercises.add(new Exercise("Knee Lift Combo", "3 set x 12 per sisi"));
        exercises.add(new Exercise("Arm Circles + March", "3 set x 1 menit"));
        exercises.add(new Exercise("Fast Feet Shuffle", "3 set x 30 detik"));
        exercises.add(new Exercise("Decline Plank", "3 set x 30 detik"));
        exercises.add(new Exercise("Jump Squats", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Lateral Shuffle", "3 set x 30 detik"));
        exercises.add(new Exercise("Standing Punches", "3 set x 45 detik"));
        exercises.add(new Exercise("Tuck Jumps (opsional)", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Plank Jacks", "3 set x 30 detik"));
        return exercises;
    }

    // Method to get exercises for Upper Body challenge
    public List<Exercise> getGerakanUpperBody() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Wall Push-up", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Incline Push-up (kursi)", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Knee Push-up", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Push-up", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Tricep Dips (kursi)", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Shoulder Tap", "3 set x 10 per sisi"));
        exercises.add(new Exercise("Arm Circles", "3 set x 30 detik"));
        exercises.add(new Exercise("Arm Pulses", "3 set x 30 detik"));
        exercises.add(new Exercise("Plank Shoulder Tap", "3 set x 10 per sisi"));
        exercises.add(new Exercise("Superman Hold", "3 set x 30 detik"));
        exercises.add(new Exercise("Pike Push-up", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Side Arm Raises", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Arm Front Raise", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Chest Opener Stretch", "3 set x 30 detik"));
        exercises.add(new Exercise("Isometric Arm Hold", "3 set x 30 detik"));
        exercises.add(new Exercise("Plank", "3 set x 45 detik"));
        exercises.add(new Exercise("Shoulder Squeeze", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Reverse Plank", "3 set x 30 detik"));
        exercises.add(new Exercise("Wall Walk", "3 set x 5 repetisi"));
        exercises.add(new Exercise("Dynamic Hug", "3 set x 15 repetisi"));
        return exercises;
    }

    // Method to get exercises for Lower Body challenge
    public List<Exercise> getGerakanLowerBody() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Bodyweight Squats", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Wall Sit", "3 set x 45 detik"));
        exercises.add(new Exercise("Lunges", "3 set x 12 repetisi per kaki"));
        exercises.add(new Exercise("Glute Bridge", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Step-up (tangga/kursi)", "3 set x 12 per kaki"));
        exercises.add(new Exercise("Donkey Kicks", "3 set x 15 per kaki"));
        exercises.add(new Exercise("Fire Hydrants", "3 set x 15 per sisi"));
        exercises.add(new Exercise("Side-Lying Leg Raises", "3 set x 15 per kaki"));
        exercises.add(new Exercise("Calf Raises", "3 set x 20 repetisi"));
        exercises.add(new Exercise("Sumo Squats", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Squat Pulse", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Leg Circles", "3 set x 10 per arah/kaki"));
        exercises.add(new Exercise("Curtsy Lunges", "3 set x 12 per kaki"));
        exercises.add(new Exercise("Good Mornings (Bodyweight)", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Bridge March", "3 set x 10 per kaki"));
        exercises.add(new Exercise("Wall Calf Raise", "3 set x 20 repetisi"));
        exercises.add(new Exercise("Standing Side Kick", "3 set x 12 per kaki"));
        exercises.add(new Exercise("Squat Hold", "3 set x 30 detik"));
        exercises.add(new Exercise("Reverse Lunges", "3 set x 12 per kaki"));
        exercises.add(new Exercise("Jump Squats", "3 set x 10 repetisi"));
        return exercises;
    }
}