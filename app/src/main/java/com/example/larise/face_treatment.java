package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class face_treatment extends AppCompatActivity {
    private ArrayList<pesanan_obj> list_face;
    private user user;
    private Intent i;
    private RecyclerView recyclerView;
    private full_body_adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public face_treatment(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        list_face = new ArrayList<>();
        dataFaceT();
        Intent intent = getIntent();
        user = (user)intent.getSerializableExtra("USER");
        FirebaseHelper fb ;
        fb = (FirebaseHelper)intent.getSerializableExtra("FB");

        mAdapter = new full_body_adapter(list_face,fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);
    }
    public void onBackPressed(){
        finish();
    }
    void dataFaceT(){
        Log.d("M", "Added");
        list_face.add(new pesanan_obj("Face Treatment A", 101200));
        list_face.add(new pesanan_obj("Face Treatment B", 10200));
        list_face.add(new pesanan_obj("Face Treatment C", 112));
        list_face.add(new pesanan_obj("Face Treatment C", 102200));
        list_face.add(new pesanan_obj("Face Treatment D", 11200));
    }

}
