package com.example.moneychanger.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transaksi")  // Menandai kelas ini sebagai entitas yang berhubungan dengan tabel "transaksi" di database
public class Transaction extends MoneyChanger {  // Kelas ini mewarisi properti dari MoneyChanger
    
    @Id  // Menandai atribut ini sebagai primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID akan dihasilkan secara otomatis oleh database
    private Long idTransaksi;  // ID unik untuk transaksi

    @Column(name = "id_customer", nullable = false)  // Kolom untuk ID Customer yang tidak boleh null
    private Long customerId;  // ID Customer yang melakukan transaksi

    @Column(name = "tipe_transaksi")  // Kolom untuk jenis transaksi (misalnya beli atau jual)
    private String tipeTransaksi;

    @Column(name = "kurs_tujuan")  // Kolom untuk kurs mata uang tujuan
    private BigDecimal kursTujuan;  // Kurs untuk mata uang tujuan

    @Column(name = "jenis_transaksi")  // Kolom untuk jenis transaksi (misalnya transfer atau top-up)
    private String jenisTransaksi;

    @Column(name = "nomor_rekening")  // Kolom untuk nomor rekening
    private String nomorRekening;

    @Transient  // Menandakan bahwa atribut ini bukan kolom di database
    private String customerName;  // Nama Customer, tidak disimpan di database

    // Implementasi metode calculateConversion dari MoneyChanger
    @SuppressWarnings("deprecation")
    @Override
    public BigDecimal calculateConversion(BigDecimal mataUangAsal, BigDecimal targetCurrency, String tipeTransaksi) {
        // Menghitung konversi mata uang berdasarkan tipe transaksi (beli/jual)
        if ("beli".equals(tipeTransaksi)) {
            return nominal.multiply(mataUangAsal).divide(targetCurrency, 2, BigDecimal.ROUND_HALF_UP);
        } else if ("jual".equals(tipeTransaksi)) {
            return nominal.multiply(targetCurrency).divide(mataUangAsal, 2, BigDecimal.ROUND_HALF_UP);
        }
        return BigDecimal.ZERO;  // Jika tipe transaksi tidak sesuai, return 0
    }

    // Getter dan Setter untuk setiap atribut
    public Long getIdTransaksi() { return idTransaksi; }
    public void setIdTransaksi(Long idTransaksi) { this.idTransaksi = idTransaksi; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public String getTipeTransaksi() { return tipeTransaksi; }
    public void setTipeTransaksi(String tipeTransaksi) { this.tipeTransaksi = tipeTransaksi; }

    public BigDecimal getKursTujuan() { return kursTujuan; }
    public void setKursTujuan(BigDecimal kursTujuan) { this.kursTujuan = kursTujuan; }

    public String getJenisTransaksi() { return jenisTransaksi; }
    public void setJenisTransaksi(String jenisTransaksi) { this.jenisTransaksi = jenisTransaksi; }

    public String getNomorRekening() { return nomorRekening; }
    public void setNomorRekening(String nomorRekening) { this.nomorRekening = nomorRekening; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
}