package com.example.larise;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class cart {
    private int total;
    private String id;
    private String status;
    private LatLng lokasi;
    private ArrayList<pesanan_obj> pesanan;

    public cart(){
        this.id = generateId();
    }

    public cart(int total, ArrayList<pesanan_obj> pesanan) {
        this.id = generateId();
        this.total = total;
        this.pesanan = pesanan;
    }

    public String generateId(){
        return "#"+String.valueOf(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatLng getLokasi() {
        return lokasi;
    }

    public void setLokasi(LatLng lokasi) {
        this.lokasi = lokasi;
    }

    public void add(pesanan_obj pesanan){
        this.pesanan.add(pesanan);
        total+=pesanan.getBiaya();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
