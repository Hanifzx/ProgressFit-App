# ProgressFit 🏋‍♂

*ProgressFit* adalah aplikasi kebugaran sederhana berbasis konsol yang membantu Anda memantau kemajuan kebugaran pribadi. Aplikasi ini memungkinkan Anda untuk mencatat data tubuh, menghitung metrik kesehatan penting seperti BMI dan BMR, serta mendapatkan rekomendasi latihan yang disesuaikan dengan tujuan Anda.

## ✨ Fitur Utama

Aplikasi ini hadir dengan beberapa fitur inti untuk membantu perjalanan kebugaran Anda:

1.  *📝 Input Data Pengguna*
    * Menyimpan informasi dasar seperti:
        * Nama
        * Jenis Kelamin (Pria/Wanita)
        * Usia (dalam tahun)
        * Tinggi Badan (dalam cm)
        * Berat Badan (dalam kg)

2.  *📊 Kalkulator Kesehatan Otomatis*
    * *BMI (Body Mass Index):* Secara otomatis menghitung Indeks Massa Tubuh Anda untuk mengetahui apakah berat badan Anda ideal.
    * *BMR (Basal Metabolic Rate):* Menghitung jumlah kalori minimum yang dibutuhkan tubuh Anda saat istirahat.
    * *Kategori Tubuh:* Memberikan kategori status berat badan Anda (misalnya, Underweight, Normal, Overweight, atau Obesity) berdasarkan hasil BMI.

3.  *💪 Menu Latihan Interaktif*
    * *Latihan Harian:* Menyediakan tiga pilihan program latihan berdasarkan tujuan Anda:
        * Menurunkan Berat Badan (fokus pada latihan kardio intensitas tinggi)
        * Menaikkan Berat Badan (fokus pada latihan kekuatan/beban)
        * Menjaga Stamina (fokus pada latihan kardio)
    * *Tantangan Fokus Tubuh:* Memberikan tantangan spesifik untuk melatih bagian tubuh tertentu:
        * Upper Body (Tubuh Bagian Atas)
        * Lower Body (Tubuh Bagian Bawah)

4.  *📈 Progres Evaluasi*
    * *Evaluasi Perubahan Berat Badan:* Memungkinkan Anda mencatat berat badan terbaru dan melihat riwayat perubahan dari waktu ke waktu.
    * *Riwayat Latihan:* Menyimpan semua sesi latihan dan tantangan yang telah Anda selesaikan, lengkap dengan tanggalnya.

## 🔬 Rumus yang Digunakan

Transparansi adalah kunci. Berikut adalah rumus yang kami gunakan dalam aplikasi ini:

* *Body Mass Index (BMI)*
    $$
    BMI = \frac{\text{berat}(\text{kg})}{(\text{tinggi}(\text{m}))^2}
    $$

* *Basal Metabolic Rate (BMR)* - Menggunakan formula Mifflin-St Jeor yang diakui akurat.
    * Untuk Pria:
        $$
        BMR = (10 \times \text{berat}(\text{kg})) + (6.25 \times \text{tinggi}(\text{cm})) - (5 \times \text{usia}(\text{tahun})) + 5
        $$
    * Untuk Wanita:
        $$
        BMR = (10 \times \text{berat}(\text{kg})) + (6.25 \times \text{tinggi}(\text{cm})) - (5 \times \text{usia}(\text{tahun})) - 161
        $$

## 🚀 Cara Menjalankan Aplikasi

Aplikasi ini dibuat menggunakan *Java* dan dapat dijalankan pada lingkungan konsol (terminal/command prompt) apa pun yang memiliki Java Development Kit (JDK).

1.  *Prasyarat:*
    * Pastikan Anda telah menginstal [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/).

2.  *Simpan Kode:*
    * Salin kode dari file ProgressFit.java dan simpan dalam sebuah file dengan nama yang sama.

3.  *Kompilasi:*
    * Buka terminal atau command prompt, navigasikan ke direktori tempat Anda menyimpan file, dan jalankan perintah berikut:
      bash
      javac ProgressFit.java
      

4.  *Jalankan:*
    * Setelah kompilasi berhasil, jalankan program dengan perintah:
      bash
      java ProgressFit
      

5.  *Selesai!*
    * Ikuti instruksi yang muncul di layar untuk mulai menggunakan aplikasi.

## ⚙ Contoh Alur Penggunaan

1.  Saat program dimulai, Anda akan diminta memasukkan data diri.
2.  Setelah data diisi, program akan menampilkan ringkasan *BMI* dan *BMR* Anda.
3.  Anda akan disajikan *Menu Utama*.
4.  Pilih *Latihan Harian* atau *Tantangan Fokus Tubuh* untuk melihat rekomendasi latihan.
5.  Konfirmasikan jika Anda telah menyelesaikan latihan untuk menyimpannya ke *Riwayat Latihan*.
6.  Pilih *Progres Evaluasi* untuk mencatat berat badan baru atau melihat riwayat kemajuan Anda.
7.  Pilih *Keluar* untuk mengakhiri program.