package com.example.larise;

public class user {
    private String Nama;
    private String Email;
    private String nomorhp;
    private String Password;

    public user(String nama, String email, String nomorhp, String password) {
        Nama = nama;
        Email = email;
        this.nomorhp = nomorhp;
        Password = password;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        this.nomorhp = nomorhp;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
