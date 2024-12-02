package com.example.moneychanger.controller; // Menentukan package tempat kelas ini berada

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Controller untuk bagian profil
public class ProfilController {

    @GetMapping("/profil") // Menangani permintaan GET untuk endpoint "/profil"
    public String profil() {
        return "profil"; // Mengembalikan nama view "profil" untuk ditampilkan
    }
}
