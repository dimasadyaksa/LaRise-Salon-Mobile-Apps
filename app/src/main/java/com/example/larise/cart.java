package com.example.larise;

import java.util.ArrayList;

public class cart {
    private int total;
    private ArrayList<pesanan_obj> pesanan;

    public cart(){

    }

    public cart(int total, ArrayList<pesanan_obj> pesanan) {
        this.total = total;
        this.pesanan = pesanan;
    }

    public void add(pesanan_obj pesanan){
        this.pesanan.add(pesanan);
        total+=pesanan.getBiaya();
    }

    public void hapus(pesanan_obj pesanan){
        this.pesanan.remove(pesanan);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<pesanan_obj> getPesanan() {
        return pesanan;
    }

    public void setPesanan(ArrayList<pesanan_obj> pesanan) {
        this.pesanan = pesanan;
    }
}
