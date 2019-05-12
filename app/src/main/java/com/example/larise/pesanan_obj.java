package com.example.larise;


import java.io.Serializable;

public class pesanan_obj implements Serializable {
    private String nama;
    private int biaya;

    public pesanan_obj() {
    }

    public pesanan_obj(String nama, int biaya) {
        this.nama = nama;
        this.biaya = biaya;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getBiaya() {
        return biaya;
    }

    public void setBiaya(int biaya) {
        this.biaya = biaya;
    }

}