package model;

public class Pengguna {
    private String nama;
    private String jenisKelamin;
    private int usia;
    private double beratBadan;
    private double tinggiBadan;
    private double bmi;
    private double bmr;
    private String tujuanLatihan;
    private String kategoriBMI;

    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            this.nama = "Anonym User";
        } else {
            this.nama = nama;
        }
    }

    public String getNama() {
        return this.nama;
    }

    public void setJenisKelamin(String jenis) {
        if (jenis.equals("Laki-laki") || jenis.equals("Perempuan")) {
            this.jenisKelamin = jenis;
        }
    }

    public void setUsia(int usia) {
        if (usia > 12 && usia <= 120) {
            this.usia = usia;
        }
    }

    public void setBeratBadan(double bb) {
        if (bb > 0 && bb <= 300) {
            this.beratBadan = bb;
        }
    }

    public double getBeratBadan() {
        return this.beratBadan;
    }

    public void setTinggiBadan(double tb) {
        if (tb > 0 && tb <= 250) {
            this.tinggiBadan = tb;
        }
    }

    public void setTujuanLatihan(String tujuan) {
        this.tujuanLatihan = tujuan;
    }

    public String getTujuanLatihan() {
        return this.tujuanLatihan;
    }
    
    public double hitungBMR() {
        if (jenisKelamin.equalsIgnoreCase("Laki-laki")) {
            bmr = 66 + (13.7 * beratBadan) + (5 * tinggiBadan) - (6.8 * usia);
        } else {
            bmr = 655 + (9.6 * beratBadan) + (1.8 * tinggiBadan) - (4.7 * usia);
        }
        return bmr;
    }

    public double hitungBMI() {
        double tinggiMeter = tinggiBadan / 100;
        bmi = beratBadan / (tinggiMeter * tinggiMeter);
        return bmi;
    }

    public void setKategoriBMI() {
        if (bmi < 18.5) {
            kategoriBMI = "Underweight";
        } else if (bmi <= 24.9) {
            kategoriBMI = "Normal";
        } else if (bmi <= 29.9) {
            kategoriBMI = "Overweight";
        } else {
            kategoriBMI = "Obese";
        }
    }

    public String getKategoriBMI() {
        return kategoriBMI;
    }



    // public void input(Scanner input) {
    //     System.out.println( "\n╔══════════════════════════════════╗");
    //     System.out.println("║   SELAMAT DATANG DI APLIKASI     ║");
    //     System.out.println("║         HOME WORKOUT             ║");
    //     System.out.println("╚══════════════════════════════════╝" );
    //     System.out.println("\nSilakan masukkan data diri Anda:");
    //     System.out.println("──────────────────────────────────");
        
    //     System.out.print("Nama Lengkap: ");
    //     this.nama = input.nextLine();
    //     if (this.nama.trim().isEmpty()) {
    //         this.nama = "User";
    //         System.out.println("Nama default digunakan: User" );
    //     }
        
    //     boolean validJenisKelamin = false;
    //     while (!validJenisKelamin) {
    //         System.out.print("Jenis Kelamin (L/P): ");
    //         this.jenisKelamin = input.nextLine().toUpperCase();
    //         if (this.jenisKelamin.equals("L") || this.jenisKelamin.equals("P")) {
    //             validJenisKelamin = true;
    //         } else {
    //             System.out.println( "Masukkan L atau P" );
    //         }
    //     }
        
    //     boolean validUsia = false;
    //     while (!validUsia) {
    //         try {
    //             System.out.print("Usia: ");
    //             this.usia = input.nextInt();
    //             input.nextLine(); 
    //             if (this.usia <= 12 || this.usia > 120) {
    //                 System.out.println( "Usia minimal 13 tahun ke atas" );
    //             } else {
    //                 validUsia = true;
    //             }
    //         } catch (InputMismatchException e) {
    //             System.out.println( "Input harus angka" );
    //             input.nextLine(); 
    //         }
    //     }

    //     boolean validBB = false;
    //     while (!validBB) {
    //         try {
    //             System.out.print("Berat Badan (kg): ");
    //             this.beratBadan = input.nextDouble();
    //             input.nextLine(); 
    //             if (this.beratBadan <= 0 || this.beratBadan > 300) {
    //                 System.out.println( "Berat harus 1-300 kg" );
    //             } else {
    //                 validBB = true;
    //             }
    //         } catch (InputMismatchException e) {
    //             System.out.println( "Input harus angka" );
    //             input.nextLine();
    //         }
    //     }

    //     boolean validTB = false;
    //     while (!validTB) {
    //         try {
    //             System.out.print("Tinggi Badan (cm): ");
    //             this.tinggiBadan = input.nextDouble();
    //             input.nextLine(); 
    //             if (this.tinggiBadan <= 0 || this.tinggiBadan > 250) {
    //                 System.out.println( "Tinggi harus 1-250 cm" );
    //             } else {
    //                 validTB = true;
    //             }
    //         } catch (InputMismatchException e) {
    //             System.out.println( "Input harus angka" );
    //             input.nextLine();
    //         }
    //     }

    //     System.out.println("\nHASIL ANALISIS:" );
    //     analisisTubuh();
    //     saran();

    //     System.out.println( "\n╔══════════════════════════╗");
    //     System.out.println("║   PILIH TUJUAN LATIHAN   ║");
    //     System.out.println("╠══════════════════════════╣");
    //     System.out.println("║ 1. Menurunkan berat badan║");
    //     System.out.println("║ 2. Membentuk otot        ║");
    //     System.out.println("║ 3. Menjaga stamina       ║");
    //     System.out.println("╚══════════════════════════╝" );
        
    //     boolean validPilihan = false;
    //     while (!validPilihan) {
    //         try {
    //             System.out.print("> " );
    //             int pilihan = input.nextInt();
    //             input.nextLine(); 
                
    //             if (pilihan >= 1 && pilihan <= 3) {
    //                 pilihTujuan(pilihan);
    //                 validPilihan = true;
    //             } else {
    //                 System.out.println( "Pilih 1-3" );
    //             }
    //         } catch (InputMismatchException e) {
    //             System.out.println( "Input harus angka" );
    //             input.nextLine();
    //         }
    //     }
        
    //     System.out.print("\nTekan ENTER untuk melanjutkan..." );
    //     input.nextLine();
    // }


    // private String kategoriBMI() {
    //     if (bmi < 18.5) return "Underweight";
    //     if (bmi < 24.9) return "Normal";
    //     if (bmi < 29.9) return "Overweight";
    //     return "Obese";
    // }

    // private void pilihTujuan(int pilihan) {
    //     switch (pilihan) {
    //         case 1 -> tujuanLatihan = "Menurunkan berat badan";
    //         case 2 -> tujuanLatihan = "Membentuk otot";
    //         case 3 -> tujuanLatihan = "Menjaga stamina";
    //     }
    //     System.out.println( "\nTujuan dipilih: " + tujuanLatihan );
    // }
    
    // private void analisisTubuh() {
    //     hitungBMI();
    //     hitungBMR();
    //     System.out.printf("BMI: %.1f (%s)%n", bmi, kategoriBMI);
    //     System.out.printf("BMR: %.0f kcal/hari%n", bmr);
    // }

    // private void saran() {
    //     String kategori = kategoriBMI;
    //     switch (kategori) {
    //         case "Underweight" -> 
    //             System.out.println("Fokus: Menambah berat badan dan massa otot");
    //         case "Normal" -> 
    //             System.out.println("Fokus: Membentuk otot atau menjaga stamina");
    //         default -> 
    //             System.out.println("Fokus: Menurunkan berat badan");
    //     }
    // }
}
