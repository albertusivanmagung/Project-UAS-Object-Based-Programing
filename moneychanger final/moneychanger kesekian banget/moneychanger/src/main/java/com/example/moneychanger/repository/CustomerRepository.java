package com.example.moneychanger.repository; // Mendeklarasikan package tempat repository ini berada.

import com.example.moneychanger.entity.Customer; // Mengimpor entitas Customer yang akan digunakan dalam repository.

import org.springframework.data.jpa.repository.JpaRepository; // Mengimpor JpaRepository untuk operasi CRUD dasar.

public interface CustomerRepository extends JpaRepository<Customer, Long> { // Mendeklarasikan interface CustomerRepository yang mewarisi JpaRepository untuk entitas Customer dengan tipe ID Long.
    Customer findByUsername(String username); // Menambahkan metode untuk mencari Customer berdasarkan username.

    Customer findByIdCustomer(Long idCustomer); // Menambahkan metode untuk mencari Customer berdasarkan ID Customer.

    Customer findByEmail(String email); // Menambahkan metode untuk mencari Customer berdasarkan email.
}
