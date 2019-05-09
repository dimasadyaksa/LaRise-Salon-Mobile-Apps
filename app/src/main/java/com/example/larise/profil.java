package com.example.larise;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.logging.Logger;

public class profil extends Fragment {
    private user user;

    public profil() {
        // Required empty public constructor
    }


    public static profil newInstance() {
        profil fragment = new profil();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fview = inflater.inflate(R.layout.fragment_profil, container, false);
        mainmenu mm = (mainmenu) getActivity();
        String uid = mm.getUID();
        user = new user();
        setuser(uid);
        TextView nama = fview.findViewById(R.id.namaProfil);
        TextView email = fview.findViewById(R.id.emaildata);
        TextView phone = fview.findViewById(R.id.phonedata);

        nama.setText(uid);
        email.setText(user.getEmail());
        return fview;
    }
    private void setuser(String UID){
        DatabaseReference db;
        final String uid = UID;
        db = FirebaseDatabase.getInstance().getReference();
        db.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String,user> stringuserHashMap = new HashMap<>();
                for(DataSnapshot singleSnapshot : dataSnapshot.getChildren()){
                     Log.e("TES", singleSnapshot.getKey());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("fail", databaseError.getDetails());
            }
        });
    }
}