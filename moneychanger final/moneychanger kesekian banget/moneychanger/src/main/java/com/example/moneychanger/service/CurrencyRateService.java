package com.example.moneychanger.service; // Menentukan package tempat kelas ini berada

import com.example.moneychanger.entity.CurrencyRate;
import com.example.moneychanger.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Service untuk bagian currencyrate
public class CurrencyRateService {

    @Autowired // Menyediakan objek CurrencyRateRepository untuk mengakses data kurs mata uang
    private CurrencyRateRepository currencyRateRepository;

    // Mengambil semua data kurs mata uang
    public List<CurrencyRate> getAllCurrencyRates() {
        return currencyRateRepository.findAll(); // Mengembalikan daftar kurs mata uang dari repository
    }

    // Mengambil data kurs mata uang berdasarkan kode mata uang
    public CurrencyRate getCurrencyRate(String mataUang) {
        return currencyRateRepository.findById(mataUang).orElse(null); // Mengambil kurs mata uang berdasarkan ID atau mengembalikan null jika tidak ditemukan
    }

    // Menyimpan data kurs mata uang ke database
    public CurrencyRate saveCurrencyRate(CurrencyRate currencyRate) {
        return currencyRateRepository.save(currencyRate); // Menyimpan kurs mata uang ke repository
    }
}
