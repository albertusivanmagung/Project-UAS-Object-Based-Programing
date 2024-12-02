package com.example.moneychanger.service; // Menentukan package tempat kelas ini berada

import com.example.moneychanger.entity.CurrencyRate;
import com.example.moneychanger.entity.Transaction;
import com.example.moneychanger.repository.CurrencyRateRepository;
import com.example.moneychanger.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service // Service untuk bagian transaction
public class TransactionService {

    @Autowired // Menyediakan objek TransactionRepository untuk mengakses data transaksi
    private TransactionRepository transactionRepository;

    @Autowired // Menyediakan objek CurrencyRateRepository untuk mengakses data kurs mata uang
    private CurrencyRateRepository currencyRateRepository;
    
    // Menyimpan transaksi dan melakukan konversi mata uang
    public Transaction saveTransaction(Transaction transaction) {
        // Mengambil kurs mata uang asal dari repository
        CurrencyRate sourceCurrencyRate = currencyRateRepository.findById(transaction.getMataUangAsal()).orElse(null);
        if (sourceCurrencyRate == null) {
            throw new RuntimeException("Kurs untuk mata uang asal tidak ditemukan."); // Jika kurs asal tidak ditemukan, throw exception
        }

        // Mengambil kurs mata uang tujuan dari repository
        CurrencyRate targetCurrencyRate = currencyRateRepository.findById(transaction.getTargetCurrency()).orElse(null);
        if (targetCurrencyRate == null) {
            throw new RuntimeException("Kurs untuk mata uang tujuan tidak ditemukan."); // Jika kurs tujuan tidak ditemukan, throw exception
        }

        // Logika konversi mata uang
        BigDecimal nominal = transaction.getNominal(); // Menyimpan nominal transaksi dalam BigDecimal
        BigDecimal convertedAmount;

        // Memilih logika konversi berdasarkan tipe transaksi (beli/jual)
        if ("beli".equals(transaction.getTipeTransaksi())) { // Jika transaksi tipe "beli"
            convertedAmount = nominal.multiply(sourceCurrencyRate.getJual())  // Menggunakan kurs jual mata uang asal
                                 .divide(targetCurrencyRate.getJual(), 2, RoundingMode.HALF_UP); // Menggunakan kurs jual mata uang tujuan
        } else { // Jika transaksi tipe "jual"
            convertedAmount = nominal.multiply(sourceCurrencyRate.getBeli())  // Menggunakan kurs beli mata uang asal
                                 .divide(targetCurrencyRate.getJual(), 2, RoundingMode.HALF_UP); // Menggunakan kurs jual mata uang tujuan
        }

        transaction.setNominalKonversi(convertedAmount); // Menyimpan hasil konversi ke dalam transaksi

        return transactionRepository.save(transaction); // Menyimpan transaksi yang sudah dihitung konversinya
    }

    // Mengambil semua data transaksi
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll(); // Mengembalikan daftar semua transaksi dari repository
    }
}
