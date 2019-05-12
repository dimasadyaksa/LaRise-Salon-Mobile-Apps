package com.example.larise;


import java.io.Serializable;

public class Cart implements Serializable {
    public  String no;
    private String nama;
    private int biaya;
    public boolean isOnChart;

    public Cart() {
    }


    public Cart(String nama, int biaya) {
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