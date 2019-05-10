package com.example.larise;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.HashMap;

public class FirebaseHelper implements Serializable {
    private transient DatabaseReference db;
    private String uid;
    private user us;
    private pesanan_obj ps;

    public FirebaseHelper() {
        this.us = new user("","" ,"" ,"" ,"" );
        us.setTglDaftar("");
    }

    public DatabaseReference getDb() {
        return db;
    }

    public void setDb(DatabaseReference db) {
        this.db = db;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public user getUs() {
        return us;
    }

    public void setUs(user us) {
        this.us = us;
    }

    public pesanan_obj getPs() {
        return ps;
    }

    public void setPs(pesanan_obj ps) {
        this.ps = ps;
    }

    public void getDB(){
        db.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                    switch (singleSnapshot.getKey()){
                        case "email":
                            us.setEmail(singleSnapshot.getValue(String.class));
                            break;
                        case "nama":
                            us.setNama(singleSnapshot.getValue(String.class));
                            break;
                        case "nomorhp":
                            us.setNomorhp(singleSnapshot.getValue(String.class));
                            break;
                        case "password":
                            us.setPassword(singleSnapshot.getValue(String.class));
                            break;
                        case "tglDaftar":
                            us.setTglDaftar(singleSnapshot.getValue(String.class));
                            break;
                        case "uid":
                            us.setUID(singleSnapshot.getValue(String.class));
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("fail", databaseError.getDetails());
            }
        });
    }

}
