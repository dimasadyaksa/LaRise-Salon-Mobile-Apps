package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class full_body extends AppCompatActivity {
    private ArrayList<Cart> list_full_body;
    private user user;
    private RecyclerView recyclerView;
    private full_body_adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Intent i;
    private FirebaseHelper fb;

    public full_body (){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);
        list_full_body = new ArrayList<>();
        dataFullBody();
        Intent intent = getIntent();
        user = (user)intent.getSerializableExtra("USER");
        fb = (FirebaseHelper)intent.getSerializableExtra("FB");
        mAdapter = new full_body_adapter(list_full_body,fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.swapAdapter(mAdapter,false);
    }
    public void onBackPressed(){

        finish();
    }

    void dataFullBody(){
        Log.d("B", "Added");
        list_full_body.add(new Cart("Pijat Tradisional", 75000));
        list_full_body.add(new Cart("Lulut", 100000));
        list_full_body.add(new Cart("Bleaching Kaki+Tangan", 70000));
        list_full_body.add(new Cart("Bleaching Full Body", 130000));
        list_full_body.add(new Cart("Kerik", 25000));
        list_full_body.add(new Cart("Ratus Vagina", 25000));
    }

}
