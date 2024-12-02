package com.example.moneychanger.repository; // Mendeklarasikan package tempat repository ini berada.

import com.example.moneychanger.entity.Admin; // Mengimpor entitas Admin yang akan digunakan dalam repository.

import org.springframework.data.jpa.repository.JpaRepository; // Mengimpor JpaRepository untuk operasi CRUD dasar.

public interface AdminRepository extends JpaRepository<Admin, Long> { // Mendeklarasikan interface AdminRepository yang mewarisi JpaRepository untuk entitas Admin dengan tipe ID Long.
    Admin findByUsername(String username); // Menambahkan metode untuk mencari Admin berdasarkan username.
}
