package com.example.moneychanger.controller;

import com.example.moneychanger.entity.Admin;
import com.example.moneychanger.entity.Transaction;
import com.example.moneychanger.service.AdminService;
import com.example.moneychanger.service.CustomerService;
import com.example.moneychanger.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

// Controller untuk bagian admin
@Controller
@RequestMapping("/admin") // Base URL untuk semua endpoint di controller ini
public class AdminController {
    
    // Injeksi dependensi untuk AdminService
    @Autowired
    private AdminService adminService; // Instance AdminService untuk mengelola logika admin

    // Injeksi dependensi untuk TransactionService
    @Autowired
    private TransactionService transactionService; // Instance TransactionService untuk mengelola logika transaksi

    // Injeksi dependensi untuk CustomerService
    @Autowired
    private CustomerService customerService; // Instance CustomerService untuk mengelola logika customer

    // Mapping untuk halaman login admin
    @GetMapping // Menangani permintaan GET ke "/admin"
    public String adminLoginPage() {
        return "admin"; // Mengembalikan tampilan halaman login admin
    }

    // Endpoint untuk memproses login admin
    @PostMapping("/login") // Menangani permintaan POST ke "/admin/login"
    public String adminLogin(@RequestParam String username, // Parameter untuk username dari form login
                             @RequestParam String password, // Parameter untuk password dari form login
                             HttpSession session, Model model) { // Untuk menyimpan data sesi dan mengirim data ke tampilan
        Admin admin = adminService.validateAdmin(username, password); // Memvalidasi username dan password menggunakan service
        if (admin != null) { // Jika validasi berhasil
            session.setAttribute("admin", admin); // Menyimpan data admin yang berhasil login ke sesi
            return "redirect:/admin/dashboard"; // Mengarahkan ke halaman dashboard admin
        }
        model.addAttribute("error", "Username atau password salah"); // Menambahkan pesan error ke model jika login gagal
        return "admin"; // Kembali ke halaman login jika gagal
    }

    // Endpoint untuk menampilkan halaman dashboard admin
    @GetMapping("/dashboard") // Menangani permintaan GET ke "/admin/dashboard"
    public String getDashboard(Model model) { // Model untuk menambahkan data ke tampilan
        List<Transaction> transactions = transactionService.getAllTransactions(); // Mengambil semua transaksi dari database
    
        // Menambahkan nama customer berdasarkan ID customer ke setiap transaksi
        for (Transaction transaction : transactions) { // Iterasi untuk setiap transaksi dalam daftar
            String customerName = customerService.getCustomerNameById(transaction.getCustomerId()); // Mendapatkan nama customer berdasarkan ID
            transaction.setCustomerName(customerName); // Menyimpan nama customer ke dalam transaksi
        }
    
        model.addAttribute("transactions", transactions); // Menambahkan daftar transaksi ke model
        return "admin-dashboard"; // Mengembalikan tampilan dashboard admin
    }
}