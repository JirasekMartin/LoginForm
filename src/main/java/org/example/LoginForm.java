package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;

public class LoginForm extends JFrame {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton loginButton, forgotPasswordButton;
    private int attempts;

    private static final String CORRECT_USERNAME = "user";
    private static final String CORRECT_PASSWORD = "abcdef";

    public LoginForm() {
        super("Přihlašovací formulář");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        nameLabel = new JLabel("Uživatelské jméno:");
        add(nameLabel);

        nameField = new JTextField();
        add(nameField);

        passwordLabel = new JLabel("Heslo:");
        add(passwordLabel);

        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Přihlásit se");
        loginButton.addActionListener(e -> {
            String username = nameField.getText();
            char[] password = passwordField.getPassword();

            // Kontrola prázdných polí
            if (username.isEmpty() || password.length == 0) {
                JOptionPane.showMessageDialog(this, "Prosím, vyplňte všechny pole", "Chyba", JOptionPane.ERROR_MESSAGE);
            } else {
                // Zabezpečení hesla
                String hashedPassword = hashPassword(password);

                // Simulace přihlašování (zde by byla skutečná ověřovací logika)
                if (authenticate(username, hashedPassword)) {
                    JOptionPane.showMessageDialog(this, "Přihlášení úspěšné");
                    dispose(); // Zavřít okno po úspěšném přihlášení
                } else {
                    attempts++;
                    if (attempts >= 3) {
                        JOptionPane.showMessageDialog(this, "Byl překročen maximální počet pokusů", "Chyba", JOptionPane.ERROR_MESSAGE);
                        dispose(); // Zavřít okno po překročení maximálního počtu pokusů
                    } else {
                        JOptionPane.showMessageDialog(this, "Neplatné přihlašovací údaje, zbývá pokusů: " + (3 - attempts), "Chyba", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(loginButton);

        forgotPasswordButton = new JButton("Zapomenuté heslo?");
        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "http://127.0.0.1:5500/HTMLLoginForm.html"; // Vaše URL adresa // Vaše URL adresa

                // Výstup pro kontrolu správnosti URL adresy
                System.out.println("Otevíraná URL adresa: " + url);

                openPasswordResetPage(url);
            }
        });
        add(forgotPasswordButton);

        setVisible(true);
    }

    private void openPasswordResetPage(String url) {
        System.out.println("Otevíraná URL adresa: " + url);
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            System.out.println("Desktop browsing is not supported on this platform.");
        }
    }

    private boolean authenticate(String username, String enteredPassword) {
        // Zde by byla skutečná ověřovací logika
        // Pro demonstrační účely předpokládáme, že údaje jsou uloženy někde v databázi
        // a že provádíme porovnání s předem uloženým heslem
        String storedPassword = hashPassword(CORRECT_PASSWORD.toCharArray()); // Zahashování uloženého hesla
        return storedPassword.equals(enteredPassword); // Porovnání zahashovaných hesel
    }

    private String hashPassword(char[] password) {
        // Zde budeme šifrovat heslo pomocí hashovacího algoritmu
        // Například SHA-256
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(new String(password).getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes); // Konverze na Base64 řetězec
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginForm::new);
    }
}
