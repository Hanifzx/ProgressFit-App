package model;

public class Progress implements Evaluasi {
    private Pengguna user;
    private Sesi sesi;
    private int jumlahSesiSelesai = 0;
    private int totalHari = 6;
    private double konsistensi;
    // private double 
    
    public void setSesi(Sesi sesi) {
        this.sesi = sesi;
    }

    public void setUser(Pengguna user) {
        this.user = user;
    }

    public void setJumlahSesiSelesai() {
        this.jumlahSesiSelesai = sesi.getJumlahSesi();
    }

    public int getJumlahSesiSelesai() {
        return jumlahSesiSelesai;
    }

    public void setKonsistensi() {
        this.konsistensi = ((double) jumlahSesiSelesai / totalHari) * 100;
    }

    public double getKonsistensi() {
        return konsistensi;
    }

    public double getPerubahanBB(double bbBaru) {
        return bbBaru - user.getBeratBadan();
    }

    @Override
    public void evalBB(double bbBaru) {        
        double BBbaru = getPerubahanBB(bbBaru);
        
        // switch (tujuan.toLowerCase()) {
        //     case "membentuk otot" -> evalBulking(perubahanBB);
        //     case "menurunkan berat badan" -> evalCutting(perubahanBB);
        //     case "menjaga stamina" -> evalStamina(perubahanBB);
        //     default -> System.out.println( "Tujuan tidak dikenali" );
        // }

        user.setBeratBadan(bbBaru);
    }

    @Override
    public void evalBulking(double perubahanBB) {
        if (perubahanBB < 0) {
            System.out.printf("Berat turun %.1f kg%n", Math.abs(perubahanBB));
            System.out.println("Perbanyak protein dan surplus kalori");
        } else if (perubahanBB > 0) {
            System.out.printf("Berat naik %.1f kg%n", perubahanBB);
            System.out.println("Progress baik! Lanjutkan!");
        } else {
            System.out.println("Berat stabil");
            System.out.println("Tingkatkan intensitas latihan");
        }
    }

    @Override
    public void evalCutting(double perubahanBB) {
        if (perubahanBB < 0) {
            System.out.printf("Berat turun %.1f kg%n", Math.abs(perubahanBB));
            System.out.println("Progress baik! Lanjutkan!");
        } else if (perubahanBB > 0) {
            System.out.printf("Berat naik %.1f kg%n", perubahanBB);
            System.out.println("Perbanyak cardio dan defisit kalori");
        } else {
            System.out.println("Berat stabil");
            System.out.println("Perbaiki defisit kalori");
        }
    }

    @Override
    public void evalStamina(double perubahanBB) {
        if (perubahanBB != 0) {
            System.out.printf("Berat %s %.1f kg%n", 
                perubahanBB < 0 ? "turun" : "naik", 
                Math.abs(perubahanBB));
        } else {
            System.out.println("Berat stabil");
        }
        System.out.println("Fokus pada peningkatan stamina");
    }
}