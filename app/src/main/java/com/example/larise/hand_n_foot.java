package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hand_n_foot extends AppCompatActivity {
    private ArrayList<Cart> list_hnb;
    private Intent i;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private user user;
    public hand_n_foot(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        list_hnb = new ArrayList<>();
        dataHnB();
        Intent intent = getIntent();
        user = (user)intent.getSerializableExtra("USER");
        FirebaseHelper fb ;
        fb = (FirebaseHelper)intent.getSerializableExtra("FB");
        full_body_adapter mAdapter = new full_body_adapter(list_hnb, fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
    public void onBackPressed(){
        startActivityForResult(new Intent(hand_n_foot.this,mainmenu.class),10);
    }
    void dataHnB(){
        Log.e("M", "Added");
        list_hnb.add(new Cart("Manicure", 50000));
        list_hnb.add(new Cart("Pedicure", 50000));
        list_hnb.add(new Cart("Waxing Kaki", 100000));
        list_hnb.add(new Cart("Waxing Tangan", 80000));
        list_hnb.add(new Cart("Waxing Ketiak", 65000));
        list_hnb.add(new Cart("Nail Art", 30000));
        list_hnb.add(new Cart("Refelexiologi", 50000));
    }
}
