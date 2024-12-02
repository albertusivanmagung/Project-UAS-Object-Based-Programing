package com.example.moneychanger.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")  // Menandai kelas ini sebagai entitas yang berhubungan dengan tabel "admin" di database
public class Admin {
    @Id  // Menandai atribut ini sebagai primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ID akan dihasilkan secara otomatis oleh database
    private Long idAdmin;  // ID unik untuk Admin

    @Column(nullable = false, unique = true)  // Kolom ini tidak boleh null dan harus unik
    private String username;  // Username Admin

    @Column(nullable = false)  // Kolom ini tidak boleh null
    private String password;  // Password Admin

    // Getter dan Setter untuk setiap atribut
    public Long getIdAdmin() { return idAdmin; }
    public void setIdAdmin(Long idAdmin) { this.idAdmin = idAdmin; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
