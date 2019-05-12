package com.example.larise;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class FirebaseHelper implements Serializable {
    private transient DatabaseReference db;
    private user us;
    PesananObjek pesananObjek;
    ArrayList<PesananObjek> pesananObjeks = new ArrayList<>();
    private Cart ps;
    private String nc;
    private String uid ;
    public FirebaseHelper() {
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public user getUs() {
        return us;
    }
    public PesananObjek getPesananObjek(){
        return this.pesananObjek;
    }
    public ArrayList<PesananObjek> getPesananObjeks() {
        return pesananObjeks;
    }

    public void tes(){
        ArrayList<Cart> Carts = new ArrayList<>();
        PesananObjek pesananObjek = new PesananObjek();
        pesananObjek.setId();
        pesananObjek.setLatitude(2);
        pesananObjek.setLongitude(2);
        pesananObjek.setStatus("WAIT");
        pesananObjek.setTotal(1000);
        Carts.add(new Cart("wr4qwaw", 414));
        Carts.add(new Cart("wrqwaw", 41422));
        Carts.add(new Cart("wrqwaw", 411424));
        Carts.add(new Cart("wrqwfaw", 4141221));
        Carts.add(new Cart("wfwwrqw", 41114));
        pesananObjek.setPesanan(Carts);
        db = FirebaseDatabase.getInstance().getReference();
        db.child("pesanan").child(uid).child(pesananObjek.getId()).setValue(pesananObjek);
    }
	public void downloadCart(){
		final Semaphore semaphore = new Semaphore(0);
		GLOBAL.carts.clear();
		db = FirebaseDatabase.getInstance().getReference();
		db.child("cart").child(GLOBAL.user.getUID()).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
					GLOBAL.carts.add(singleSnapshot.getValue(Cart.class));
				}
				semaphore.release();
			}
			@Override
			public void onCancelled(DatabaseError databaseError) {
				Log.e("fail", databaseError.getDetails());
			}
		});
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			Log.e("ERR","BIARIN" );
		}
	}

    public void downloadPesanan(){
        final Semaphore semaphore = new Semaphore(0);
        final HashMap<String, PesananObjek>cartMap = new HashMap<>();
        GLOBAL.pesananObjeks.clear();
        db = FirebaseDatabase.getInstance().getReference();
        db.child("pesanan").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pesananObjek =new PesananObjek();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    Log.e("download PesananObjek", singleSnapshot.getKey());
                    cartMap.put(singleSnapshot.getKey(), singleSnapshot.getValue(PesananObjek.class));
                    GLOBAL.pesananObjeks.add(cartMap.get(singleSnapshot.getKey()));
                }
                semaphore.release();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("fail", databaseError.getDetails());
            }
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Log.e("ERR","BIARIN" );
        }
    }

    public void sendCart(final Cart p){
        DatabaseReference db;
        db = FirebaseDatabase.getInstance().getReference();
        p.no = "No - "+(GLOBAL.carts.size()+1);
        db.child("Cart").child(GLOBAL.user.getUID()).child("No - "+(GLOBAL.carts.size()+1)).setValue(p);
    }
    public void deleteCart(Cart p){
        DatabaseReference db;
        db = FirebaseDatabase.getInstance().getReference();
        db.child("Cart").child(GLOBAL.user.getUID()).child(p.no).child("biaya").removeValue();
    }


    public void getDB(){
        final Semaphore semaphore = new Semaphore(0);
        db = FirebaseDatabase.getInstance().getReference();
        db.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    switch (singleSnapshot.getKey()){
                        case "email":
                            GLOBAL.user.setEmail(singleSnapshot.getValue(String.class));
                            break;
                        case "nama":
							GLOBAL.user.setNama(singleSnapshot.getValue(String.class));
                            break;
                        case "nomorhp":
							GLOBAL.user.setNomorhp(singleSnapshot.getValue(String.class));
                            break;
                        case "password":
							GLOBAL.user.setPassword(singleSnapshot.getValue(String.class));
                            break;
                        case "tglDaftar":
							GLOBAL.user.setTglDaftar(singleSnapshot.getValue(String.class));
                            break;
                        case "uid":
							GLOBAL.user.setUID(singleSnapshot.getValue(String.class));
                            break;
                    }
                    Log.e("KEY", singleSnapshot.getKey());

                }
                semaphore.release();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("fail", databaseError.getDetails());
            }
        });
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            Log.e("ERR","BIARIN" );
        }
    }

}
