# Getting Started

## Information

> Service ini dibuat untuk memenuhi Test Hiring, adapun beberapa feature untuk melengkapi nya. dibuat oleh Syahrul Ataufik

## Running

Ada beberapa step yang perlu dilakukan untuk menjalankan service ini.

1. Pertama pastikan sudah menjalankan Mysql
2. Sesuaikan nama url dan nama database pada application.properties. Untuk membuat database bisa menjalankan script database.sql
3. Pastikan mvn dan java sudah terinstall. MINIMUM JAVA 17
4. Kemudian jalankan aplikasi, untuk pengguna `linux` atau `mac` bisa menggunakan script dibawah

   ```bash
   sh start.sh
   ```

   atau untuk pengguna windows bisa menjalankan menggunakan `IDE` kalian atau menjalankan command

   ```bash
   mvn clean install
   mvn spring-boot:run -pl bootstrap
   ```

5. Service akan otomatis membuat table dan relation
# purchase-order
