package org.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reset-password")
public class PasswordResetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Zpracování formuláře pro resetování hesla
        String newPassword = request.getParameter("password");

        // Příklad: Aktualizace hesla v databázi (zde je pouze simulace)
        // TODO: Nahraďte tuto simulaci skutečným kódem pro aktualizaci hesla v databázi
        System.out.println("Nové heslo: " + newPassword);

        // Přesměrování na stránku s potvrzením resetování hesla
        response.sendRedirect("password-reset-success.html");
    }
}