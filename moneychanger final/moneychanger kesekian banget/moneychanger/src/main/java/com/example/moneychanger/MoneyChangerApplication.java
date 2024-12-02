package com.example.moneychanger; // Menentukan package tempat kelas ini berada

import org.springframework.boot.SpringApplication; // Mengimpor SpringApplication untuk menjalankan aplikasi Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Mengimpor anotasi untuk menandakan kelas utama aplikasi Spring Boot

@SpringBootApplication // Menandakan bahwa kelas ini adalah aplikasi Spring Boot yang akan dijalankan
public class MoneyChangerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyChangerApplication.class, args); // Menjalankan aplikasi Spring Boot
    }
}
