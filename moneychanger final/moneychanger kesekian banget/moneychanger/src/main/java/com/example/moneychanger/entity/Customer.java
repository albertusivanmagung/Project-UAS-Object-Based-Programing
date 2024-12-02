package com.example.moneychanger.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")  // Menandai kelas ini sebagai entitas yang berhubungan dengan tabel "customer" di database
public class Customer {
    @Id  // Menandai atribut ini sebagai primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID akan dihasilkan secara otomatis oleh database
    private Long idCustomer;  // ID unik untuk Customer

    @Column(nullable = false, unique = true)  // Kolom ini tidak boleh null dan harus unik
    private String username;  // Username untuk Customer

    @Column(nullable = false)  // Kolom ini tidak boleh null
    private String email;  // Email Customer

    @Column(nullable = false)  // Kolom ini tidak boleh null
    private String no_telp;  // Nomor telepon Customer

    @Column(nullable = false)  // Kolom ini tidak boleh null
    private String password;  // Password Customer

    // Getter dan Setter untuk setiap atribut
    public Long getIdCustomer() { return idCustomer; }
    public void setIdCustomer(Long idCustomer) { this.idCustomer = idCustomer; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNoTelp() { return no_telp; }
    public void setNoTelp(String no_telp) { this.no_telp = no_telp; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}