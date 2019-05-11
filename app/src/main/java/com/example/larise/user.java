package com.example.larise;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class user implements Serializable {
    private String UID;
    private String Nama;
    private String Email;
    private String nomorhp;
    private String Password;
    private String tglDaftar;
    private ArrayList<cart> cart;
    private String catatan;
    private LatLng l;
    public user(){

    }

    public user(LatLng l, String catatan){
        this.l = l;
        this.catatan = catatan;
    }

    public user(String UID, String nama, String email, String nomorhp, String password, String tglDaftar, ArrayList<com.example.larise.cart> cart) {
        this.UID = UID;
        Nama = nama;
        Email = email;
        this.nomorhp = nomorhp;
        Password = password;
        this.tglDaftar = tglDaftar;
        this.cart = cart;
    }

    public user(String UID, String nama, String email, String nomorhp, String password, String tglDaftar) {
        this.UID = UID;
        Nama = nama;
        Email = email;
        this.nomorhp = nomorhp;
        Password = password;
        this.tglDaftar = tglDaftar;
    }

    public user(String nama, String email, String nomorhp, String password, String uid) {
        this.UID = uid;
        this.tglDaftar = generateLast();
        Nama = nama;
        Email = email;
        this.nomorhp = nomorhp;
        Password = password;
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

    public ArrayList<com.example.larise.cart> getCart() {
        return cart;
    }

    public void setCart(ArrayList<com.example.larise.cart> cart) {
        this.cart = cart;
    }

    public void addCart(cart cart){
        this.cart.add(cart);
    }
}
