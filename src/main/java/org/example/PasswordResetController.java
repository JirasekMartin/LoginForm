package org.example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordResetController {

    @GetMapping("/reset-password")
    public String showPasswordResetPage() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String handlePasswordReset(@RequestParam("password") String newPassword) {
        // Zpracování nového hesla, např. uložení do databáze, odeslání potvrzovacího emailu, atd.
        return "redirect:/login"; // Přesměrování na stránku přihlášení po úspěšné obnově hesla
    }
}