package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hour_treatment extends AppCompatActivity {
    private ArrayList<Cart> list_hour;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Intent i;
    private user user;
    public hour_treatment(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        list_hour= new ArrayList<>();
        dataHourT();
        Intent intent = getIntent();
        user = (user)intent.getSerializableExtra("USER");
        FirebaseHelper fb ;
        fb = (FirebaseHelper)intent.getSerializableExtra("FB");

        full_body_adapter mAdapter;
        mAdapter= new full_body_adapter(list_hour, fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
    public void onBackPressed(){
        startActivityForResult(new Intent(hour_treatment.this,mainmenu.class),10);
    }
    void dataHourT(){
        Log.e("HOUR", "Added");
        list_hour.add(new Cart("Creambath", 40000));
        list_hour.add(new Cart("Hair Mask", 45000));
        list_hour.add(new Cart("Hair Spa", 45000));
        list_hour.add(new Cart("Hair Color", 100000));
        list_hour.add(new Cart("Smoothing", 150000));
        list_hour.add(new Cart("Gunting", 25000));
        list_hour.add(new Cart("Cuci Rambot", 15000));
        list_hour.add(new Cart("Catok", 25000));
        list_hour.add(new Cart("Curly", 30000));
    }
}
