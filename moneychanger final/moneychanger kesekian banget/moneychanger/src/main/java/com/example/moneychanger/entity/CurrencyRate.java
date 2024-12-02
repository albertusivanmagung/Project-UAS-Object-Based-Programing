package com.example.moneychanger.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "kurs_mata_uang")  // Menandai kelas ini sebagai entitas yang berhubungan dengan tabel "kurs_mata_uang" di database
public class CurrencyRate {
    @Id  // Menandai atribut ini sebagai primary key
    private String mataUang;

    @Column(name = "beli", nullable = false)  // Kolom "beli" untuk nilai beli mata uang, tidak boleh null
    private BigDecimal beli; // Tipe data diubah menjadi BigDecimal untuk akurasi lebih tinggi

    @Column(name = "jual", nullable = false)  // Kolom "jual" untuk nilai jual mata uang, tidak boleh null
    private BigDecimal jual; // Tipe data diubah menjadi BigDecimal untuk akurasi lebih tinggi

    // Getter dan Setter untuk setiap atribut
    public String getMataUang() {
        return mataUang;
    }
    public void setMataUang(String mataUang) {
        this.mataUang = mataUang;
    }
    public BigDecimal getBeli() {
        return beli;
    }
    public void setBeli(BigDecimal beli) {
        this.beli = beli;
    }
    public BigDecimal getJual() {
        return jual;
    }
    public void setJual(BigDecimal jual) {
        this.jual = jual;
    }
}