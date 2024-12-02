package com.example.moneychanger.controller; // Menentukan package tempat kelas ini berada

// Mengimpor kelas yang diperlukan untuk controller
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller untuk bagian home
public class HomeController {
    @GetMapping("/")
    public String homePage() {
        return "home"; // Mengembalikan nama view "home" untuk dirender
    }
}