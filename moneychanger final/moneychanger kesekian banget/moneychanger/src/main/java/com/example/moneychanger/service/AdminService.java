package com.example.moneychanger.service;  // Menentukan package untuk service ini

import com.example.moneychanger.entity.Admin;  
import com.example.moneychanger.entity.Transaction;  
import com.example.moneychanger.repository.AdminRepository;  
import com.example.moneychanger.repository.TransactionRepository; 
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service; 

import java.util.List;  // Mengimpor List untuk koleksi data

@Service  // Service untuk bagian admin
public class AdminService {
    
    @Autowired
    private TransactionRepository transactionRepository;  // Mengautowire TransactionRepository untuk operasi transaksi

    @Autowired
    private AdminRepository adminRepository;  // Mengautowire AdminRepository untuk operasi admin

    public List<Transaction> getAllTransactions() {  // Method untuk mengambil semua transaksi
        return transactionRepository.findAll();  // Mengambil semua transaksi dari repository
    }

    public Admin validateAdmin(String username, String password) {  // Method untuk validasi admin
        Admin admin = adminRepository.findByUsername(username);  // Mencari admin berdasarkan username
        if (admin != null && admin.getPassword().equals(password)) {  // Mengecek kecocokan password
            return admin;  // Jika valid, mengembalikan data admin
        }
        return null;  // Jika tidak valid, mengembalikan null
    }
}