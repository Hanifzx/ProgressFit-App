package model;

import java.util.ArrayList;
import java.util.List;

public class KatalogGerakan {
    public static List<Gerakan> getGerakanSesuaiTujuan(String tujuan) {
        switch (tujuan.toLowerCase()) {
            case "menurunkan berat badan":
                return getGerakanMenurunkanBB();
            case "membentuk otot":
                return getGerakanMenambahOtot();
            case "menjaga stamina":
                return getGerakanStamina();
            default:
                return new ArrayList<>();
        }
    }
    
    public static List<Gerakan> getGerakanMenurunkanBB() {
        List<Gerakan> list = new ArrayList<>();
        list.add(new GerakanDur("Jumping Jacks", "Cardio", 30));
        list.add(new GerakanRep("Mountain Climber", "Cardio", 20));
        list.add(new GerakanDur("High Knees", "Cardio", 30));
        list.add(new GerakanRep("Burpee", "Full Body", 10));
        list.add(new GerakanDur("Plank", "Core", 30));
        list.add(new GerakanRep("Squat", "Bawah", 15));
        list.add(new GerakanRep("Push-Up", "Atas", 12));
        list.add(new GerakanDur("Lompat Tali", "Cardio", 40));
        list.add(new GerakanRep("Lunges", "Bawah", 10));
        list.add(new GerakanDur("Wall Sit", "Bawah", 30));
        return list;
    }

    public static List<Gerakan> getGerakanMenambahOtot() {
        List<Gerakan> list = new ArrayList<>();
        list.add(new GerakanRep("Push-Up", "Atas", 15));
        list.add(new GerakanRep("Pull-Up", "Atas", 10));
        list.add(new GerakanRep("Squat", "Bawah", 20));
        list.add(new GerakanRep("Glute Bridge", "Bawah", 15));
        list.add(new GerakanDur("Plank", "Core", 45));
        list.add(new GerakanRep("Lunge", "Bawah", 12));
        list.add(new GerakanRep("Crunch", "Perut", 20));
        list.add(new GerakanRep("Chair Dips", "Atas", 10));
        list.add(new GerakanDur("Side Plank", "Core", 30));
        list.add(new GerakanRep("Wall Push-Up", "Atas", 15));
        return list;
    }

    public static List<Gerakan> getGerakanStamina() {
        List<Gerakan> list = new ArrayList<>();
        list.add(new GerakanDur("Jogging di Tempat", "Cardio", 60));
        list.add(new GerakanDur("Jumping Jacks", "Cardio", 40));
        list.add(new GerakanDur("High Knees", "Cardio", 30));
        list.add(new GerakanDur("Burpee", "Cardio", 25));
        list.add(new GerakanDur("Lompat Tali", "Cardio", 60));
        list.add(new GerakanDur("Skater Jumps", "Cardio", 30));
        list.add(new GerakanDur("Plank Jacks", "Core", 30));
        list.add(new GerakanDur("Butt Kicks", "Cardio", 40));
        list.add(new GerakanDur("Fast Feet", "Cardio", 20));
        list.add(new GerakanDur("Shadow Boxing", "Cardio", 30));
        return list;
    }

    public static List<Gerakan> getGerakanFokusAtas() {
        List<Gerakan> list = new ArrayList<>();
        list.add(new GerakanRep("Push-Up", "Atas", 15));
        list.add(new GerakanRep("Shoulder Tap", "Atas", 20));
        list.add(new GerakanRep("Chair Dips", "Atas", 15));
        list.add(new GerakanDur("Arm Circles", "Atas", 30));
        list.add(new GerakanRep("Wall Push-Up", "Atas", 20));
        list.add(new GerakanDur("Plank to Push-Up", "Atas", 30));
        list.add(new GerakanRep("Incline Push-Up", "Atas", 12));
        list.add(new GerakanDur("Hand Raise Hold", "Atas", 30));
        list.add(new GerakanDur("Arm Pulse", "Atas", 30));
        list.add(new GerakanDur("Triceps Extension", "Atas", 30));
        return list;
    }

    public static List<Gerakan> getGerakanFokusBawah() {
        List<Gerakan> list = new ArrayList<>();
        list.add(new GerakanRep("Squat", "Bawah", 20));
        list.add(new GerakanRep("Lunge", "Bawah", 15));
        list.add(new GerakanRep("Glute Bridge", "Bawah", 20));
        list.add(new GerakanDur("Wall Sit", "Bawah", 40));
        list.add(new GerakanRep("Calf Raise", "Bawah", 25));
        list.add(new GerakanDur("Step Up", "Bawah", 30));
        list.add(new GerakanDur("Side Lunge", "Bawah", 30));
        list.add(new GerakanDur("Knee Up", "Bawah", 30));
        list.add(new GerakanRep("Donkey Kick", "Bawah", 15));
        list.add(new GerakanDur("Flutter Kick", "Bawah", 30));
        return list;
    }
}
