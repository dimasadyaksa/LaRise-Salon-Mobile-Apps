package com.example.larise;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Cart implements Serializable {
    private int total;
    private String id;
    private String status;
    private double latitude;
    private double longitude;
    private ArrayList<pesanan_obj> pesanan = new ArrayList<>();

    public Cart(){

    }

    public Cart(String status, double latitude, double longitude) {
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    public String generateId(){
        return new SimpleDateFormat("dd-MM-yyyy-HH:mm", Locale.getDefault()).format(new Date());
    }

    public String getId() {
        return id;
    }

    public void setId() {
        this.id = generateId();
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
    	total=0;
    	for (int i=0;i<pesanan.size();i++){
    		total+=pesanan.get(i).getBiaya();
		}
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