# Aplikasi ProgressFit

ProgressFit adalah aplikasi desktop yang dibangun dengan JavaFX, dirancang untuk membantu pengguna melacak perjalanan kebugaran mereka. Aplikasi ini memungkinkan pengguna untuk memasukkan data pribadi, menghitung BMI dan BMR, serta menawarkan berbagai program latihan dan tantangan tubuh. Pengguna juga dapat melacak perubahan berat badan mereka seiring waktu.

## Fitur Aplikasi

1.  **Input Data Pengguna**: Pengguna dapat memasukkan nama, jenis kelamin, usia, tinggi badan, dan berat badan.
2.  **Perhitungan BMI & BMR**: Aplikasi secara otomatis menghitung Body Mass Index (BMI) dan Basal Metabolic Rate (BMR) berdasarkan data yang diinput, serta menampilkan kategori tubuh (misalnya, kekurangan berat badan, normal, kelebihan berat badan, obesitas).
3.  **Tiga Menu Utama**:
    * **Latihan Harian**: Menyediakan program latihan untuk tujuan menurunkan berat badan, menaikkan berat badan, dan menjaga stamina/kardio.
    * **Tantangan Fokus Tubuh**: Menawarkan tantangan latihan yang berfokus pada bagian tubuh tertentu (misalnya, _upper body_ dan _lower body_).
    * **Progres Evaluasi Perubahan Berat Badan**: Memungkinkan pengguna untuk melacak perubahan berat badan mereka dari waktu ke waktu dan melihat evaluasi progres.

## Cara Menjalankan Aplikasi

Untuk menjalankan aplikasi ProgressFit, ikuti langkah-langkah berikut:

1.  **Pastikan Anda memiliki Java Development Kit (JDK) terinstal**: Aplikasi ini membutuhkan Java 11 atau yang lebih baru. Anda bisa mengunduh JDK dari situs web Oracle atau melalui _package manager_ seperti SDKMAN.
2.  **Kloning Repositori**:
    ```bash
    git clone https://github.com/Hanifzx/Home-Workout-App.git
    cd ProgressFit
    ```
3.  **Buka Proyek di IDE**: Impor proyek ke Integrated Development Environment (IDE) pilihan Anda (misalnya, IntelliJ IDEA, Eclipse).
4.  **Konfigurasi JavaFX**: Pastikan IDE Anda dikonfigurasi dengan JavaFX SDK. Anda mungkin perlu menambahkan modul JavaFX ke _classpath_ proyek Anda.
5.  **Jalankan Aplikasi**: Jalankan kelas `Main.java` sebagai aplikasi Java.

## Struktur Kode

Struktur kode aplikasi ProgressFit diorganisir dengan pendekatan modular untuk memisahkan tanggung jawab dan meningkatkan keterbacaan.

* `app/`: Direktori utama yang berisi semua kelas Java aplikasi.
    * `Main.java`: Kelas utama untuk memulai aplikasi JavaFX.
    * `User.java`: Merepresentasikan objek pengguna dan menyimpan data pribadi serta progres latihan.
    * `Exercise.java`: Kelas model untuk merepresentasikan sebuah latihan.
    * `ExerciseKatalog.java`: Berisi katalog latihan yang berbeda untuk program dan tantangan.
    * `Template.java`: Kelas abstrak yang berfungsi sebagai _base_ untuk _scenes_ yang memiliki elemen UI umum (seperti _header_).
    * `OpeningScene.java`: Tampilan awal aplikasi.
    * `UserDataScene.java`: Tampilan untuk input data pengguna dan perhitungan BMI/BMR.
    * `MainMenuScene.java`: Tampilan menu utama aplikasi.
    * `DailyExerciseScene.java`: Tampilan untuk program latihan harian.
    * `BodyChallengeMenuScene.java`: Tampilan menu untuk memilih jenis tantangan fokus tubuh.
    * `BodyChallengeScene.java`: Tampilan untuk tantangan fokus tubuh.
    * `ProgressTrackingScene.java`: Tampilan untuk melacak progres berat badan dan evaluasi.

## Penerapan Pilar OOP

Aplikasi ProgressFit dirancang dan dikembangkan dengan menerapkan pilar-pilar utama Object-Oriented Programming (OOP) untuk mencapai struktur kode yang terorganisir, _maintainable_, dan _scalable_. Pilar-pilar tersebut meliputi:

### 1. Encapsulation (Enkapsulasi)

Aplikasi ini menerapkan enkapsulasi dengan memastikan bahwa data internal objek terlindungi dan hanya dapat diakses atau dimodifikasi melalui metode yang telah ditentukan (getter dan setter). Ini menjaga integritas data dan mempromosikan _modularity_ dalam pengembangan.

### 2. Inheritance (Pewarisan)

Pewarisan digunakan untuk meningkatkan _reusability_ kode dan menciptakan hierarki kelas yang logis. Kelas-kelas tertentu mewarisi atribut dan perilaku dari kelas dasar, sehingga mengurangi duplikasi dan memungkinkan pengembangan fungsionalitas yang lebih spesifik pada subkelas.

### 3. Abstraction (Abstraksi)

Abstraksi diimplementasikan untuk menyembunyikan detail implementasi yang kompleks dan hanya menampilkan fungsionalitas yang esensial kepada pengguna atau bagian lain dari aplikasi. Ini memungkinkan pengembang untuk berinteraksi dengan objek pada tingkat konseptual yang lebih tinggi tanpa perlu memahami mekanisme internalnya.

### 4. Polymorphism (Polimorfisme)

Polimorfisme memungkinkan objek dari kelas yang berbeda untuk diperlakukan sebagai objek dari kelas induk yang sama. Ini tercapai melalui _method overriding_ dan _interface implementation_, memungkinkan fleksibilitas dalam penanganan objek dan respons yang berbeda terhadap panggilan metode yang sama, tergantung pada tipe objek sebenarnya.