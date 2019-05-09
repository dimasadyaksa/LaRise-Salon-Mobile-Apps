package com.example.larise;


public class pesanan_obj {
    private String Nama;
    private int Biaya;

    public pesanan_obj(String nama, int biaya) {
        Nama = nama;
        Biaya = biaya;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public int getBiaya() {
        return Biaya;
    }

    public void setBiaya(int biaya) {
        Biaya = biaya;
    }
}