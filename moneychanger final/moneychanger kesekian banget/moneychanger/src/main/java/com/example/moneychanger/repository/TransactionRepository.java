package com.example.moneychanger.repository; // Mendeklarasikan paket tempat repository ini berada.

import com.example.moneychanger.entity.Transaction; // Mengimpor entitas Transaction yang akan digunakan dalam repository.

import org.springframework.data.jpa.repository.JpaRepository; // Mengimpor JpaRepository untuk operasi CRUD dasar.

import org.springframework.stereotype.Repository; // Mengimpor anotasi Repository dari Spring untuk menandakan interface ini sebagai repository.

@Repository // Repository untuk bagian transaction
public interface TransactionRepository extends JpaRepository<Transaction, Long> { // Mendeklarasikan interface TransactionRepository yang mewarisi JpaRepository untuk entitas Transaction dengan tipe ID Long.
}