package com.example.kotlinviewkensyuu;

public class User {
    private String Email;
    private String Passwords;

    public User(String email, String passwords) {
        Email = email;
        Passwords = passwords;
    }

    public User() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String passwords) {
        Passwords = passwords;
    }
}
