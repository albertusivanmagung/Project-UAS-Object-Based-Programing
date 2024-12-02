package com.example.moneychanger.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@MappedSuperclass // Menandakan bahwa kelas ini adalah superclass yang properti dan kolomnya diwarisi oleh kelas turunannya
public abstract class MoneyChanger {
    @Column(name = "mata_uang_asal") // Kolom untuk mata uang asal
    protected String mataUangAsal;  // Mata uang yang digunakan untuk transaksi

    @Column(name = "mata_uang_tujuan") // Kolom untuk mata uang tujuan
    protected String targetCurrency;  // Mata uang tujuan untuk konversi

    @Column(name = "nominal") // Kolom untuk nominal uang yang akan dikonversi
    protected BigDecimal nominal;  // Nominal uang yang akan dikonversi

    @Column(name = "nominal_konversi") // Kolom untuk nominal setelah konversi
    protected BigDecimal nominalKonversi;  // Hasil konversi nominal uang

    // Metode abstrak untuk menghitung nilai konversi
    public abstract BigDecimal calculateConversion(
        BigDecimal mataUangAsal, 
        BigDecimal targetCurrency, 
        String tipeTransaksi
    );

    // Getter dan Setter untuk setiap atribut
    public String getMataUangAsal() { return mataUangAsal; }
    public void setMataUangAsal(String mataUangAsal) { this.mataUangAsal = mataUangAsal; }

    public String getTargetCurrency() { return targetCurrency; }
    public void setTargetCurrency(String targetCurrency) { this.targetCurrency = targetCurrency; }

    public BigDecimal getNominal() { return nominal; }
    public void setNominal(BigDecimal nominal) { this.nominal = nominal; }

    public BigDecimal getNominalKonversi() { return nominalKonversi; }
    public void setNominalKonversi(BigDecimal nominalKonversi) { this.nominalKonversi = nominalKonversi; }
}