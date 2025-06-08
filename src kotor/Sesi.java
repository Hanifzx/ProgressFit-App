package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Sesi {
    private Pengguna user;
    private Custom latihanCustom;
    private String namaHari;
    private int pilihanHari;
    private boolean selesai;
    private int jumlahSesi = 0;
    
    public void latihanHarian(Scanner input) {
        System.out.println("\n╔══════════════════════════╗");
        System.out.println("║     LATIHAN HARIAN       ║");
        System.out.println("╠══════════════════════════╣");
        for (int hari = 1; hari <= 6; hari++) {
            System.out.printf("║ %d. Hari %-15d  ║%n", hari, hari);
        }
        System.out.println("╠══════════════════════════╣");
        System.out.println("║ 0. Kembali               ║");
        System.out.println("╚══════════════════════════╝" );
        System.out.print( "\n> " );
        
        try {
            int pilihHari = input.nextInt();
            if (pilihHari == 0) return;
            
            if (pilihHari < 1 || pilihHari > 6) {
                System.out.println("\nHari tidak valid! Pilih 1-6" );
                return;
            }
            
            this.setPilihanHari(pilihHari);
            this.mulai(input);
        } catch (InputMismatchException e) {
            System.out.println("\nInput harus angka!" );
            input.nextLine();
        }
    }

    public void mulai(Scanner input) {
        try {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.printf("║   LATIHAN HARIAN HARI %-4d   ║%n", pilihanHari);
            System.out.println("╚══════════════════════════════╝" );
            
            List<Gerakan> daftarGerakan = Gerakan.getGerakanSesuaiTujuan(user.getTujuanLatihan());
            
            if (daftarGerakan == null || daftarGerakan.isEmpty()) {
                System.out.println( "Tidak ada gerakan tersedia" );
                return;
            }

            daftarGerakan(daftarGerakan);

            System.out.print( "\nTekan [ENTER] untuk mulai..." );
            input.nextLine(); input.nextLine();

            gerakan(daftarGerakan, input);

            System.out.println( "\n✔ Latihan selesai! Good job!" );
            tambahSesi();
            selesai = true;
        } catch (Exception e) {
            System.out.println( "Error: " + e.getMessage() );
        }
    }

    public void tantangan(Scanner input, String pilihan) {
        try {
            System.out.println("\n╔══════════════════════════════╗");
            System.out.printf("║   TANTANGAN %-12s    ║%n", pilihan.toUpperCase());
            System.out.println("╚══════════════════════════════╝" );

            List<Gerakan> gerakanTantangan = new ArrayList<>();
            
            switch (pilihan.toLowerCase()) {
                case "upper body" -> gerakanTantangan = Gerakan.getGerakanFokusAtas();
                case "lower body" -> gerakanTantangan = Gerakan.getGerakanFokusBawah();
                case "cardio" -> gerakanTantangan = Gerakan.getGerakanStamina();
                default -> {
                    System.out.println( "Pilihan tidak tersedia" );
                    return;
                }
            }
            
            if (gerakanTantangan.isEmpty()) {
                System.out.println( "Tidak ada gerakan tersedia" );
                return;
            }

            daftarGerakan(gerakanTantangan);

            System.out.print( "\nTekan [ENTER] untuk mulai..." );
            input.nextLine();

            gerakan(gerakanTantangan, input);
        
            System.out.println( "\n✔ Latihan selesai! Good job!" );
            tambahSesi();
            System.out.print( "\nTekan [ENTER] untuk keluar..." );
            input.nextLine();
        } catch (Exception e) {
            System.out.println( "Error: " + e.getMessage() );
        }
    }

    public void latihanCustom(Scanner input, int pilihanHari) {
        try {
            if (latihanCustom == null) {
                System.out.println( "Latihan custom belum diatur" );
                return;
            }
            
            System.out.println("\n╔══════════════════════════════╗");
            System.out.printf("║   LATIHAN CUSTOM HARI %-3d    ║%n", pilihanHari);
            System.out.println("╚══════════════════════════════╝" );

            List<Gerakan> daftarGerakanCustom = latihanCustom.getJadwalHari(pilihanHari);

            if (daftarGerakanCustom == null || daftarGerakanCustom.isEmpty()) {
                System.out.println( "Jadwal belum ada" );
                return;
            }

            daftarGerakan(daftarGerakanCustom);

            System.out.print( "\nTekan [ENTER] untuk mulai..." );
            input.nextLine(); 
            gerakan(daftarGerakanCustom, input);
            
            tambahSesi();
        } catch (Exception e) {
            System.out.println( "Error: " + e.getMessage() );
        }
    }

    public void daftarGerakan(List<Gerakan> gerakanList) {
        try {
            System.out.println("\nDAFTAR GERAKAN:" );
            Collections.shuffle(gerakanList);
            int nomor = 1;
            for (Gerakan g : gerakanList) {
                if (g != null) {
                    System.out.printf("%2d. %s%n", nomor++, g.getNamaGerakan());
                }
            }
        } catch (Exception e) {
            System.out.println( "Error menampilkan gerakan" );
        }
    }

    public void gerakan(List<Gerakan> gerakanList, Scanner input) {
        try {
            int nomor = 1;
            for (Gerakan g : gerakanList) {
                if (g == null) continue;
                
                System.out.println( "\nGerakan #" + nomor + ":" );
                g.detail();
                System.out.print( "Tekan [ENTER] jika selesai..." );
                input.nextLine();
                nomor++;
            }
        } catch (Exception e) {
            System.out.println( "Error memulai gerakan" );
        }
    }

    public void setUser(Pengguna user) {
        this.user = user;
    }

    public void setLatihanCustom(Custom latihanCustom) {
        this.latihanCustom = latihanCustom;
    }

    public void tambahSesi() {
        jumlahSesi++;
    }

    public int getJumlahSesi() {
        return jumlahSesi;
    }

    public String getNamaHari() {
        return namaHari;
    }

    public int getPilihanHari() {
        return pilihanHari;
    }

    public void setPilihanHari(int pilihanHari) {
        this.pilihanHari = pilihanHari;
    }

    public boolean isSelesai() {
        return selesai;
    }

    public void selesai() {
        this.selesai = true;
    }
}