package com.example.moneychanger.service; // Menentukan package tempat kelas ini berada

import com.example.moneychanger.entity.Customer;
import com.example.moneychanger.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Service untuk bagian customer
public class CustomerService {

    @Autowired // Menyediakan objek CustomerRepository untuk mengakses data customer
    private CustomerRepository customerRepository;

    // Menyimpan data customer tanpa hashing password
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer); // Menyimpan customer ke repository
    }
    
    // Mencari customer berdasarkan ID
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id); // Mengembalikan customer jika ditemukan, jika tidak maka kosong
    }

    // Mencari customer berdasarkan username
    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username); // Mengembalikan customer berdasarkan username
    }

    // Mengambil customer berdasarkan ID
    public Optional<Customer> getCustomerById(Long idCustomer) {
        return customerRepository.findById(idCustomer); // Mengembalikan customer jika ditemukan, jika tidak maka kosong
    }

    // Validasi customer berdasarkan email dan password
    public Customer validateCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email); // Cari berdasarkan email
        if (customer != null && customer.getPassword().equals(password)) {
            return customer; // Jika password cocok, validasi berhasil
        }
        return null; // Jika validasi gagal
    }

    // Mengecek apakah email sudah terdaftar
    public boolean emailExists(String email) {
        return customerRepository.findByEmail(email) != null; // Mengembalikan true jika email ditemukan
    }

    // Mengecek apakah username sudah terdaftar
    public boolean usernameExists(String username) {
        return customerRepository.findByUsername(username) != null; // Mengembalikan true jika username ditemukan
    }

    // Mengambil nama customer berdasarkan ID
    public String getCustomerNameById(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        return (customer != null) ? customer.getUsername() : "Unknown"; // Mengembalikan nama customer atau "Unknown" jika tidak ditemukan
    }
}
