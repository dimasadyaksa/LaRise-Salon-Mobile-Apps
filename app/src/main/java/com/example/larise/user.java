package com.example.larise;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class user implements Serializable {
    private String UID;
    private String Nama;
    private String Email;
    private String nomorhp;
    private String Password;
    private String tglDaftar;
    
    public user(){

    }

    public user(String UID, String nama, String email, String nomorhp, String password) {
        this.UID = UID;
        Nama = nama;
        Email = email;
        this.nomorhp = nomorhp;
        Password = password;
        this.tglDaftar = generateLast();
    }

    public String getNama() {
        return Nama;
    }
    public String generateLast(){
        return new SimpleDateFormat("dd-MM-yyyy 'pukul' HH:mm:ss z", Locale.getDefault()).format(new Date());
    }
    public void setNama(String nama) {
        Nama = nama;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getTglDaftar() {
        return tglDaftar;
    }

    public void setTglDaftar(String lastLogin) {
        this.tglDaftar = lastLogin;
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
