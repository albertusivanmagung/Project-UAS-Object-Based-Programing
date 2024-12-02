package com.example.moneychanger.repository; // Mendeklarasikan package tempat repository ini berada.

import com.example.moneychanger.entity.CurrencyRate; // Mengimpor entitas CurrencyRate yang akan digunakan dalam repository.

import org.springframework.data.jpa.repository.JpaRepository; // Mengimpor JpaRepository untuk operasi CRUD dasar.

import org.springframework.stereotype.Repository; // Mengimpor anotasi Repository dari Spring untuk menandakan interface ini sebagai repository.

import java.util.List; // Mengimpor List untuk menangani kumpulan data.

@Repository // Repository untuk bagian currencyrate
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, String> { // Mendeklarasikan interface CurrencyRateRepository yang mewarisi JpaRepository untuk entitas CurrencyRate dengan tipe ID String.
    List<CurrencyRate> findAll(); // Menambahkan metode untuk mengambil semua data CurrencyRate dalam bentuk List.

    // Metode untuk menemukan kurs berdasarkan mata uang
    CurrencyRate findByMataUang(String mataUang); // Menambahkan metode untuk mencari CurrencyRate berdasarkan nama mata uang.
}
