package com.example.moneychanger.controller;

import com.example.moneychanger.entity.Customer;
import com.example.moneychanger.entity.CurrencyRate;
import com.example.moneychanger.entity.Transaction;
import com.example.moneychanger.service.CustomerService;
import com.example.moneychanger.service.TransactionService;
import com.example.moneychanger.repository.CurrencyRateRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller // Controller untuk bagian customer
public class CustomerController {

    @Autowired // Menandakan bahwa Spring akan otomatis menyuntikkan objek CustomerService ke dalam variabel ini
    private CustomerService customerService;

    @Autowired // Menyuntikkan objek TransactionService untuk menangani transaksi
    private TransactionService transactionService;

    @Autowired // Menyuntikkan objek CurrencyRateRepository untuk mengakses data kurs mata uang
    private CurrencyRateRepository currencyRateRepository;

    @GetMapping("/customer") 
    public String showCustomerLoginForm() {
        return "customer"; // Mengembalikan tampilan form login customer
    }

    @PostMapping("/customer/login")
    public String loginCustomer(@RequestParam String email,
                                @RequestParam String password,
                                HttpSession session, Model model) {
        // Memvalidasi email dan password customer
        Customer customer = customerService.validateCustomer(email, password);
        if (customer != null) {
            session.setAttribute("customer", customer); // Menyimpan objek customer ke session
            return "redirect:/customer/dashboard"; // Mengarahkan ke dashboard setelah login berhasil
        }
        model.addAttribute("error", "Email atau Password salah"); // Menampilkan pesan error jika login gagal
        return "customer"; // Mengembalikan ke halaman login
    }

    @GetMapping("/customer/register")
    public String showCustomerRegisterForm(Model model) {
        return "customer-register"; // Mengembalikan tampilan form pendaftaran customer
    }
    
    @PostMapping("/customer/register")
    public String registerCustomer(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String no_telp,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   Model model) {
        // Memeriksa apakah email sudah terdaftar
        if (customerService.emailExists(email)) {
            model.addAttribute("error", "Email sudah terdaftar.");
            return "customer-register"; // Kembali ke halaman registrasi dengan pesan error
        }
    
        // Memeriksa apakah password dan konfirmasi password cocok
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Password dan konfirmasi password tidak cocok.");
            return "customer-register"; // Kembali ke halaman registrasi dengan pesan error
        }
    
        // Membuat objek customer baru
        Customer newCustomer = new Customer();
        newCustomer.setUsername(username);
        newCustomer.setEmail(email);
        newCustomer.setNoTelp(no_telp);
        newCustomer.setPassword(password);
    
        // Menyimpan customer baru ke database
        customerService.saveCustomer(newCustomer);
    
        // Menambahkan pesan sukses ke model
        model.addAttribute("success", "Selamat, registrasi Anda berhasil!");
    
        return "customer-register"; // Tetap di halaman registrasi dengan pesan sukses
    }
    
    
    @GetMapping("/customer/success")
    public String registrationSuccess(Model model) {
    model.addAttribute("successMessage", "Selamat, registrasi Anda berhasil!");
    return "customer-register"; // Halaman tampilan pesan sukses
    }


    @GetMapping("/customer/dashboard")
    public String showCustomerDashboard(Model model, HttpSession session) {
        // Mengambil semua data kurs mata uang dari database
        List<CurrencyRate> currencyRates = currencyRateRepository.findAll();
        model.addAttribute("currencyRates", currencyRates); // Menambahkan kurs mata uang ke model

        // Mengambil data customer dari session
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("idCustomer", customer.getIdCustomer()); // Menambahkan ID customer ke model
            model.addAttribute("customerName", customer.getUsername()); // Menambahkan nama customer ke model
        }

        return "customer-dashboard"; // Mengembalikan tampilan dashboard customer
    }

    @PostMapping("/customer/transaction")
    public String handleTransaction(
        @RequestParam String transactionType,
        @RequestParam String sourceCurrency,
        @RequestParam String targetCurrency,
        @RequestParam double nominal,
        @RequestParam String paymentMethod,
        @RequestParam(required = false) String accountNumber,
        HttpSession session, 
        Model model) {
    
        // Memeriksa apakah customer sudah login
        Customer customer = (Customer) session.getAttribute("customer");
        if (customer == null) {
            model.addAttribute("error", "Anda harus login terlebih dahulu.");
            return "redirect:/customer"; // Mengarahkan ke halaman login jika belum login
        }
    
        // Mengambil data kurs mata uang asal dan tujuan
        CurrencyRate sourceCurrencyRate = currencyRateRepository.findByMataUang(sourceCurrency);
        CurrencyRate targetCurrencyRate = currencyRateRepository.findByMataUang(targetCurrency);
        
        if (sourceCurrencyRate == null || targetCurrencyRate == null) {
            throw new RuntimeException("Kurs untuk mata uang tidak ditemukan.");
        }
    
        // Menghitung nominal setelah konversi
        BigDecimal convertedAmount;
        if ("beli".equals(transactionType)) { // Jika transaksi beli
            convertedAmount = BigDecimal.valueOf(nominal)
                .multiply(sourceCurrencyRate.getJual())  // Menggunakan kurs jual mata uang asal
                .divide(targetCurrencyRate.getJual(), 2, RoundingMode.HALF_UP); // Menggunakan kurs jual mata uang tujuan
        } else { // Jika transaksi jual
            convertedAmount = BigDecimal.valueOf(nominal)
                .multiply(sourceCurrencyRate.getBeli())  // Menggunakan kurs beli mata uang asal
                .divide(targetCurrencyRate.getJual(), 2, RoundingMode.HALF_UP); // Menggunakan kurs jual mata uang tujuan
        }
        
        // Membuat objek transaksi baru dan menyimpannya
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customer.getIdCustomer());
        transaction.setTipeTransaksi(transactionType);
        transaction.setMataUangAsal(sourceCurrency);
        transaction.setTargetCurrency(targetCurrency);
        transaction.setNominal(BigDecimal.valueOf(nominal)); // Mengonversi nominal ke BigDecimal
        transaction.setJenisTransaksi(paymentMethod);
        transaction.setNomorRekening(accountNumber);
        transaction.setKursTujuan(transactionType.equals("beli") ? targetCurrencyRate.getJual() : targetCurrencyRate.getBeli());
        transaction.setNominalKonversi(convertedAmount); // Menyimpan nominal setelah konversi
    
        // Menyimpan transaksi ke database
        transactionService.saveTransaction(transaction);
    
        // Mengubah transactionType menjadi format yang diinginkan (Beli/Jual)
        String formattedTransactionType = transactionType.substring(0, 1).toUpperCase() + transactionType.substring(1).toLowerCase();
    
        // Mengarahkan ke halaman sukses transaksi
        return "redirect:/transaction-success?customerName=" + customer.getUsername() +
               "&ewalletNumber=" + ("cash".equalsIgnoreCase(paymentMethod) ? "-" : (accountNumber != null ? accountNumber : "N/A")) + 
               "&transactionType=" + formattedTransactionType + 
               "&sourceCurrency=" + sourceCurrency + 
               "&targetCurrency=" + targetCurrency + 
               "&convertedAmount=" + convertedAmount.doubleValue(); // Mengonversi BigDecimal menjadi double
    }

    @GetMapping("/transaction-success")
    public String showTransactionSuccess(
            @RequestParam String customerName,
            @RequestParam String ewalletNumber,
            @RequestParam String transactionType,
            @RequestParam String sourceCurrency,
            @RequestParam String targetCurrency,
            @RequestParam double convertedAmount,
            Model model) {
        
        // Menambahkan data transaksi ke model untuk ditampilkan
        model.addAttribute("customerName", customerName);
        model.addAttribute("ewalletNumber", ewalletNumber);
        model.addAttribute("transactionType", transactionType);
        model.addAttribute("sourceCurrency", sourceCurrency);
        model.addAttribute("targetCurrency", targetCurrency);
        model.addAttribute("convertedAmount", convertedAmount);
        
        return "transaction-success"; // Mengembalikan tampilan sukses transaksi
    }
}