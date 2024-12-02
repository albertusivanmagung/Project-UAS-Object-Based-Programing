package com.example.moneychanger.controller;

import com.example.moneychanger.entity.CurrencyRate;
import com.example.moneychanger.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller // Menandai kelas ini sebagai controller Spring yang menangani permintaan HTTP
public class CurrencyController {

    // Injeksi dependensi untuk CurrencyRateService
    @Autowired // Menghubungkan CurrencyRateService secara otomatis ke instance yang ada
    private CurrencyRateService currencyService; // Instance CurrencyRateService untuk logika bisnis terkait kurs mata uang

    // Mendefinisikan metode yang menangani permintaan GET di endpoint "/currencies"
    @GetMapping("/currencies") // Menangani permintaan GET ke "/currencies"
    public String getCurrencies(Model model) { // Model digunakan untuk mengirimkan data ke tampilan (view)
        // Mengambil semua data rate mata uang melalui service
        List<CurrencyRate> currencies = currencyService.getAllCurrencyRates(); // Memanggil metode di service untuk mendapatkan daftar kurs mata uang
        model.addAttribute("currencyRates", currencies); // Menambahkan data kurs mata uang ke model agar dapat diakses di tampilan
        return "customer-dashboard"; // Mengembalikan nama tampilan untuk ditampilkan ke pengguna
    }
}