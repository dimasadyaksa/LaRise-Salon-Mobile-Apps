package com.example.larise;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class FirebaseHelper implements Serializable {
    private transient DatabaseReference db;
    private user us;
    Cart cart;
    ArrayList<Cart> carts = new ArrayList<>();
    private pesanan_obj ps;
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
    public Cart getCart(){
        return this.cart;
    }
    public ArrayList<Cart> getCarts() {
        return carts;
    }

    public void tes(){
        ArrayList<pesanan_obj>pesanan_objs = new ArrayList<>();
        Cart cart = new Cart();
        cart.setId();
        cart.setLatitude(2);
        cart.setLongitude(2);
        cart.setStatus("WAIT");
        cart.setTotal(1000);
        pesanan_objs.add(new pesanan_obj("wr4qwaw", 414));
        pesanan_objs.add(new pesanan_obj("wrqwaw", 41422));
        pesanan_objs.add(new pesanan_obj("wrqwaw", 411424));
        pesanan_objs.add(new pesanan_obj("wrqwfaw", 4141221));
        pesanan_objs.add(new pesanan_obj("wfwwrqw", 41114));
        cart.setPesanan(pesanan_objs);
        db = FirebaseDatabase.getInstance().getReference();
        db.child("pesanan").child(uid).child(cart.getId()).setValue(cart);
    }
	public void downloadPesanan(){
		final Semaphore semaphore = new Semaphore(0);
		//tes();
		GLOBAL.pesanans.clear();
		db = FirebaseDatabase.getInstance().getReference();
		db.child("Cart").child(GLOBAL.user.getUID()).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
					GLOBAL.pesanans.add(singleSnapshot.getValue(pesanan_obj.class));
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

    public Cart downloadCart(){
        final Semaphore semaphore = new Semaphore(0);
        //tes();
        final ArrayList<Cart> carts =new ArrayList<>();
        GLOBAL.carts.clear();
        db = FirebaseDatabase.getInstance().getReference();
        db.child("pesanan").child(uid).addValueEventListener(new ValueEventListener() {
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
        return this.cart;
    }

    public void sendPesanan(final pesanan_obj p){
        db = FirebaseDatabase.getInstance().getReference();
        db.child("pesanan").child(GLOBAL.user.getUID()).child(GLOBAL.carts.get(0).getId()).child("pesanan").child(Integer.toString(GLOBAL
				.carts.get(0).getPesanan().size())).setValue(p);
		db.child("pesanan").child(uid).child(GLOBAL.carts.get(0).getId()).child("total").setValue(GLOBAL.carts.get(0).getTotal());
		db.child("Cart").child(uid).child(Integer.toString(GLOBAL.pesanans.size())).setValue(p);
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
