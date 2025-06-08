package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Exercise {
    private String name;
    private String sets;  // Could be "3 set x 12 repetisi" or "30 detik"
    
    public Exercise(String name, String sets) {
        this.name = name;
        this.sets = sets;
    }
    
    public String getName() { return name; }
    public String getSets() { return sets; }
}

// Catalog of all exercises
public class ExerciseKatalog {
    // Method to get exercises for weight gain
    public List<Exercise> getGerakanMenambahBB() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Squat", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Deadlift", "3 set x 6 repetisi"));
        exercises.add(new Exercise("Bench Press", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Overhead Press", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Barbell Row", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Pull-ups", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Dips", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Leg Press", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Lunges", "3 set x 10 repetisi per kaki"));
        exercises.add(new Exercise("Dumbbell Curl", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Skull Crushers", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Calf Raises", "3 set x 15 repetisi"));
        return exercises;
    }
    
    // Method to get exercises for weight loss
    public List<Exercise> getGerakanMenurunkanBB() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Burpees", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Jump Squats", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Mountain Climbers", "3 set x 30 detik"));
        exercises.add(new Exercise("High Knees", "3 set x 30 detik"));
        exercises.add(new Exercise("Jumping Jacks", "3 set x 45 detik"));
        exercises.add(new Exercise("Kettlebell Swings", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Box Jumps", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Battle Ropes", "3 set x 30 detik"));
        exercises.add(new Exercise("Jumping Lunges", "3 set x 12 repetisi per kaki"));
        exercises.add(new Exercise("Sprints", "8 set x 20 detik"));
        exercises.add(new Exercise("Plank to Push-up", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Bicycle Crunches", "3 set x 20 repetisi"));
        return exercises;
    }
    
    // Method to get exercises for stamina
    public List<Exercise> getGerakanMenjagaStamina() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Jogging", "20 menit"));
        exercises.add(new Exercise("Cycling", "15 menit"));
        exercises.add(new Exercise("Jump Rope", "3 set x 2 menit"));
        exercises.add(new Exercise("Swimming", "15 menit"));
        exercises.add(new Exercise("Stair Climbing", "10 menit"));
        exercises.add(new Exercise("Rowing", "10 menit"));
        exercises.add(new Exercise("Elliptical Training", "15 menit"));
        exercises.add(new Exercise("Circuit Training", "3 putaran"));
        exercises.add(new Exercise("HIIT", "15 menit"));
        exercises.add(new Exercise("Tempo Running", "15 menit"));
        exercises.add(new Exercise("Shuttle Runs", "3 set x 1 menit"));
        exercises.add(new Exercise("Ladder Drills", "5 menit"));
        return exercises;
    }
    
    // Method to get exercises for upper body
    public List<Exercise> getGerakanUpperBody() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Push-ups", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Pull-ups", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Dumbbell Shoulder Press", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Bench Press", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Bent Over Rows", "3 set x 10 repetisi"));
        exercises.add(new Exercise("Lateral Raises", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Tricep Dips", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Bicep Curls", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Face Pulls", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Chest Flyes", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Upright Rows", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Tricep Pushdowns", "3 set x 15 repetisi"));
        return exercises;
    }
    
    // Method to get exercises for lower body
    public List<Exercise> getGerakanLowerBody() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Squats", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Deadlifts", "3 set x 8 repetisi"));
        exercises.add(new Exercise("Lunges", "3 set x 10 repetisi per kaki"));
        exercises.add(new Exercise("Leg Press", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Calf Raises", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Leg Extensions", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Hamstring Curls", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Hip Thrusts", "3 set x 12 repetisi"));
        exercises.add(new Exercise("Step-ups", "3 set x 10 repetisi per kaki"));
        exercises.add(new Exercise("Bulgarian Split Squats", "3 set x 8 repetisi per kaki"));
        exercises.add(new Exercise("Glute Bridges", "3 set x 15 repetisi"));
        exercises.add(new Exercise("Sumo Squats", "3 set x 12 repetisi"));
        return exercises;
    }
}

// public class ExerciseKatalog {
    
//     private static final Map<String, List<String>> GERAKAN_MENAIKKAN_BERAT = new HashMap<>();
//     private static final Map<String, List<String>> GERAKAN_MENURUNKAN_BERAT = new HashMap<>();
//     private static final Map<String, List<String>> GERAKAN_MENJAGA_STAMINA = new HashMap<>();
    
//     static {
//         // Inisialisasi gerakan untuk menaikkan berat badan
//         initGerakanMenaikkanBerat();
        
//         // Inisialisasi gerakan untuk menurunkan berat badan
//         initGerakanMenurunkanBerat();
        
//         // Inisialisasi gerakan untuk menjaga stamina
//         initGerakanMenjagaStamina();
//     }
    
//     private static void initGerakanMenaikkanBerat() {
//         // Hari 1
//         List<String> hari1 = new ArrayList<>();
//         hari1.add("Push Up (3 set x 12 rep)");
//         hari1.add("Bench Press (4 set x 8 rep)");
//         hari1.add("Dumbbell Shoulder Press (3 set x 10 rep)");
//         hari1.add("Tricep Dips (3 set x 12 rep)");
//         hari1.add("Chest Fly (3 set x 10 rep)");
//         hari1.add("Overhead Tricep Extension (3 set x 12 rep)");
//         hari1.add("Lateral Raise (3 set x 15 rep)");
//         hari1.add("Front Raise (3 set x 15 rep)");
//         hari1.add("Skull Crusher (3 set x 12 rep)");
//         hari1.add("Diamond Push Up (3 set x 10 rep)");
//         GERAKAN_MENAIKKAN_BERAT.put("Hari 1", hari1);
        
//         // Hari 2
//         List<String> hari2 = new ArrayList<>();
//         hari2.add("Pull Up (3 set x 8 rep)");
//         hari2.add("Barbell Row (4 set x 8 rep)");
//         hari2.add("Lat Pulldown (3 set x 10 rep)");
//         hari2.add("Bicep Curl (3 set x 12 rep)");
//         hari2.add("Hammer Curl (3 set x 12 rep)");
//         hari2.add("Face Pull (3 set x 15 rep)");
//         hari2.add("Reverse Fly (3 set x 12 rep)");
//         hari2.add("Deadlift (4 set x 6 rep)");
//         hari2.add("Shrug (3 set x 15 rep)");
//         hari2.add("Preacher Curl (3 set x 10 rep)");
//         GERAKAN_MENAIKKAN_BERAT.put("Hari 2", hari2);
        
//         // Hari 3
//         List<String> hari3 = new ArrayList<>();
//         hari3.add("Squat (4 set x 8 rep)");
//         hari3.add("Leg Press (4 set x 10 rep)");
//         hari3.add("Leg Extension (3 set x 12 rep)");
//         hari3.add("Leg Curl (3 set x 12 rep)");
//         hari3.add("Calf Raise (4 set x 15 rep)");
//         hari3.add("Romanian Deadlift (3 set x 10 rep)");
//         hari3.add("Lunges (3 set x 10 rep per kaki)");
//         hari3.add("Hip Thrust (3 set x 12 rep)");
//         hari3.add("Glute Bridge (3 set x 15 rep)");
//         hari3.add("Adductor Machine (3 set x 15 rep)");
//         GERAKAN_MENAIKKAN_BERAT.put("Hari 3", hari3);
        
//         // Hari 4 - Istirahat
//         List<String> hari4 = new ArrayList<>();
//         hari4.add("Istirahat atau Kardio Ringan");
//         hari4.add("Peregangan Seluruh Tubuh");
//         hari4.add("Yoga Ringan");
//         hari4.add("Meditasi");
//         hari4.add("Berjalan Santai (30 menit)");
//         hari4.add("Renang Santai (20 menit)");
//         hari4.add("Foam Rolling");
//         hari4.add("Konsumsi Protein Tinggi");
//         hari4.add("Hidrasi Optimal");
//         hari4.add("Tidur Cukup (7-8 jam)");
//         GERAKAN_MENAIKKAN_BERAT.put("Hari 4", hari4);
        
//         // Hari 5 - Ulangi Hari 1
//         GERAKAN_MENAIKKAN_BERAT.put("Hari 5", hari1);
        
//         // Hari 6 - Ulangi Hari 2
//         GERAKAN_MENAIKKAN_BERAT.put("Hari 6", hari2);
//     }
    
//     private static void initGerakanMenurunkanBerat() {
//         // Hari 1
//         List<String> hari1 = new ArrayList<>();
//         hari1.add("HIIT: Sprint 30 detik, Jalan 30 detik (10 set)");
//         hari1.add("Jumping Jack (3 set x 30 detik)");
//         hari1.add("Mountain Climber (3 set x 30 detik)");
//         hari1.add("Burpee (3 set x 10 rep)");
//         hari1.add("Squat Jump (3 set x 15 rep)");
//         hari1.add("High Knees (3 set x 30 detik)");
//         hari1.add("Plank (3 set x 45 detik)");
//         hari1.add("Russian Twist (3 set x 20 rep)");
//         hari1.add("Bicycle Crunch (3 set x 20 rep)");
//         hari1.add("Jump Rope (5 menit)");
//         GERAKAN_MENURUNKAN_BERAT.put("Hari 1", hari1);
        
//         // Hari 2
//         List<String> hari2 = new ArrayList<>();
//         hari2.add("Lari 5 km");
//         hari2.add("Walking Lunge (3 set x 20 langkah)");
//         hari2.add("Push Up (3 set x 15 rep)");
//         hari2.add("Bodyweight Squat (3 set x 20 rep)");
//         hari2.add("Plank to Push Up (3 set x 10 rep)");
//         hari2.add("Side Plank (3 set x 30 detik per sisi)");
//         hari2.add("Glute Bridge (3 set x 20 rep)");
//         hari2.add("Leg Raise (3 set x 15 rep)");
//         hari2.add("Superman (3 set x 15 rep)");
//         hari2.add("Jumping Lunge (3 set x 20 rep)");
//         GERAKAN_MENURUNKAN_BERAT.put("Hari 2", hari2);
        
//         // Hari 3
//         List<String> hari3 = new ArrayList<>();
//         hari3.add("Circuit Training (5 putaran):");
//         hari3.add("- Box Jump (12 rep)");
//         hari3.add("- Push Up (15 rep)");
//         hari3.add("- Kettlebell Swing (15 rep)");
//         hari3.add("- Battle Rope (30 detik)");
//         hari3.add("- Medicine Ball Slam (12 rep)");
//         hari3.add("- TRX Row (15 rep)");
//         hari3.add("- Wall Ball (15 rep)");
//         hari3.add("- Sit Up (20 rep)");
//         hari3.add("- Plank (45 detik)");
//         GERAKAN_MENURUNKAN_BERAT.put("Hari 3", hari3);
        
//         // Hari 4
//         List<String> hari4 = new ArrayList<>();
//         hari4.add("Yoga untuk Penurunan Berat (60 menit)");
//         hari4.add("Peregangan Dinamis (10 menit)");
//         hari4.add("Meditasi Mindful (10 menit)");
//         hari4.add("Berjalan Cepat (30 menit)");
//         hari4.add("Foam Rolling (10 menit)");
//         hari4.add("Peregangan Statis (10 menit)");
//         hari4.add("Latihan Pernapasan (5 menit)");
//         hari4.add("Konsumsi Air Putih (2-3 liter)");
//         hari4.add("Makan Makanan Tinggi Protein");
//         hari4.add("Tidur Cukup (7-8 jam)");
//         GERAKAN_MENURUNKAN_BERAT.put("Hari 4", hari4);
        
//         // Hari 5 - Ulangi Hari 1
//         GERAKAN_MENURUNKAN_BERAT.put("Hari 5", hari1);
        
//         // Hari 6 - Ulangi Hari 2
//         GERAKAN_MENURUNKAN_BERAT.put("Hari 6", hari2);
//     }
    
//     private static void initGerakanMenjagaStamina() {
//         // Hari 1
//         List<String> hari1 = new ArrayList<>();
//         hari1.add("Lari Interval (20 menit)");
//         hari1.add("Jumping Jack (3 set x 30 detik)");
//         hari1.add("Push Up (3 set x 12 rep)");
//         hari1.add("Air Squat (3 set x 15 rep)");
//         hari1.add("Plank (3 set x 45 detik)");
//         hari1.add("Mountain Climber (3 set x 30 detik)");
//         hari1.add("Burpee (3 set x 8 rep)");
//         hari1.add("Lunges (3 set x 10 rep per kaki)");
//         hari1.add("Bicycle Crunch (3 set x 15 rep)");
//         hari1.add("Jump Rope (5 menit)");
//         GERAKAN_MENJAGA_STAMINA.put("Hari 1", hari1);
        
//         // Hari 2
//         List<String> hari2 = new ArrayList<>();
//         hari2.add("Berenang (30 menit)");
//         hari2.add("Pull Up (3 set x 8 rep)");
//         hari2.add("Dips (3 set x 10 rep)");
//         hari2.add("Inverted Row (3 set x 12 rep)");
//         hari2.add("Hollow Hold (3 set x 30 detik)");
//         hari2.add("Superman (3 set x 15 rep)");
//         hari2.add("Leg Raise (3 set x 12 rep)");
//         hari2.add("Russian Twist (3 set x 20 rep)");
//         hari2.add("Plank Shoulder Tap (3 set x 20 rep)");
//         hari2.add("Peregangan Dinamis (10 menit)");
//         GERAKAN_MENJAGA_STAMINA.put("Hari 2", hari2);
        
//         // Hari 3
//         List<String> hari3 = new ArrayList<>();
//         hari3.add("Bersepeda (45 menit)");
//         hari3.add("Squat Jump (3 set x 12 rep)");
//         hari3.add("Box Jump (3 set x 10 rep)");
//         hari3.add("Kettlebell Swing (3 set x 15 rep)");
//         hari3.add("Lateral Bound (3 set x 10 rep per sisi)");
//         hari3.add("Speed Skater (3 set x 20 rep)");
//         hari3.add("Wall Ball (3 set x 15 rep)");
//         hari3.add("Battle Rope (3 set x 30 detik)");
//         hari3.add("Sled Push (3 set x 20 meter)");
//         hari3.add("Peregangan Statis (10 menit)");
//         GERAKAN_MENJAGA_STAMINA.put("Hari 3", hari3);
        
//         // Hari 4
//         List<String> hari4 = new ArrayList<>();
//         hari4.add("Yoga (60 menit)");
//         hari4.add("Meditasi (15 menit)");
//         hari4.add("Latihan Pernapasan (10 menit)");
//         hari4.add("Berjalan Santai (30 menit)");
//         hari4.add("Peregangan Seluruh Tubuh (15 menit)");
//         hari4.add("Foam Rolling (10 menit)");
//         hari4.add("Konsumsi Air Putih (2-3 liter)");
//         hari4.add("Makan Makanan Seimbang");
//         hari4.add("Tidur Cukup (7-8 jam)");
//         hari4.add("Relaksasi Mental");
//         GERAKAN_MENJAGA_STAMINA.put("Hari 4", hari4);
        
//         // Hari 5 - Ulangi Hari 1
//         GERAKAN_MENJAGA_STAMINA.put("Hari 5", hari1);
        
//         // Hari 6 - Ulangi Hari 2
//         GERAKAN_MENJAGA_STAMINA.put("Hari 6", hari2);
//     }
    
//     public static Map<String, List<String>> getGerakanMenaikkanBerat() {
//         return GERAKAN_MENAIKKAN_BERAT;
//     }
    
//     public static Map<String, List<String>> getGerakanMenurunkanBerat() {
//         return GERAKAN_MENURUNKAN_BERAT;
//     }
    
//     public static Map<String, List<String>> getGerakanMenjagaStamina() {
//         return GERAKAN_MENJAGA_STAMINA;
//     }
// }