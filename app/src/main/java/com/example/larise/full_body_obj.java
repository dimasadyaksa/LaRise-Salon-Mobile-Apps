package com.example.larise;

public class full_body_obj {
    private String nama;
    private int biaya;

    public full_body_obj(String nama, int biaya) {
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
