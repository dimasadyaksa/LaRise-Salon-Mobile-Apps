package com.example.larise;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class profil extends Fragment {
    private user user;
    private FirebaseHelper fb;

    public profil() {
        // Required empty public constructor
    }


    public static profil newInstance(FirebaseHelper fb) {
        profil fragment = new profil();
        Bundle args = new Bundle();
        args.putSerializable("FB",fb );
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
        TextView nama = fview.findViewById(R.id.namaProfil);
        TextView email = fview.findViewById(R.id.emaildata);
        TextView phone = fview.findViewById(R.id.phonedata);
        this.fb = new FirebaseHelper();
        this.fb = (FirebaseHelper)getArguments().getSerializable("FB");
            nama.setText(fb.getUs().getNama());
            email.setText(fb.getUs().getEmail());
            phone.setText(fb.getUs().getNomorhp());
        return fview;
    }

}