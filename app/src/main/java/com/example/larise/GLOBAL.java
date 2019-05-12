package com.example.larise;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class GLOBAL {
	public static user user = new user();
	public static ArrayList<PesananObjek> pesananObjeks = new ArrayList<>();
	public static ArrayList<Cart> carts = new ArrayList<>();
	public static FirebaseHelper fb = new FirebaseHelper();
    public static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
	public static void setListener(final String uid){

		Log.e("SET LISTENER", "TRUE");
		db.child("pesanan").child(uid).addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Log.e("PESANAN LISTEN ADD", "ADD");
				pesananObjeks.add(dataSnapshot.getValue(PesananObjek.class));
			}

			@Override
			public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Log.e("PESANAN LISTEN CHANGE", "Change");
			}

			@Override
			public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
				Log.e("CART LISTEN REMOVE", "TRUE");
				pesananObjeks.remove(dataSnapshot.getValue(PesananObjek.class));
			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Log.e("CART LISTEN MOVED", "Moved");
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}

		});
		db.child("Cart").child(uid).addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Log.e("CART LISTEN ADD", "ADD");
				carts.add(dataSnapshot.getValue(Cart.class));
			}

			@Override
			public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Log.e("CART LISTEN Changed", "CHANGE");
			}

			@Override
			public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
				Log.e("CART LISTEN REMOVE", "TRUE");
				carts.remove(dataSnapshot.getValue(Cart.class));
			}

			@Override
			public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
				Log.e("CART LISTEN MOVE", "TRUE");
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {


			}

		});
	}
    public static boolean init(String uid){
		Log.e("INIT", "TRUE");
    	fb.setUid(uid);
		fb.getDB();
		carts.clear();
		pesananObjeks.clear();

    	return true;
	}

	public static void hapusCart(Cart p){
		fb.deleteCart(p);
	}

	public static void tambahCart(Cart p){
    	fb.sendCart(p);
	}
}
