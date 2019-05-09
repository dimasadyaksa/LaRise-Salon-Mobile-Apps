package com.example.larise;


public class pesanan_obj {
    private String Nama;
    private int Biaya;
    private int jumlah;

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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}