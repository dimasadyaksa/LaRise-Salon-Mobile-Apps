package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class face_treatment extends AppCompatActivity {
    private ArrayList<Cart> list_face;
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
        cekOnChart();
        mAdapter = new full_body_adapter(list_face,fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(mAdapter);
    }
    void cekOnChart(){
        for(int i=0;i<list_face.size();i++){
            if(GLOBAL.carts.contains(list_face.get(i))){
                list_face.get(i).isOnChart = true;
            }else{
                list_face.get(i).isOnChart = false;
            }
        }
    }
    public void onBackPressed(){
        finish();
    }
    void dataFaceT(){
        Log.d("M", "Added");
        list_face.add(new Cart("Setrika Wajah", 200000));
        list_face.add(new Cart("Facial Biokos", 80000));
        list_face.add(new Cart("Facial La Tulipe", 70000));
        list_face.add(new Cart("Totok Wajah", 50000));
        list_face.add(new Cart("Perawatan Kantung Mata", 60000));
        list_face.add(new Cart("Ear Candle", 40000));
        list_face.add(new Cart("Keriting Bulu Mata", 45000));
        list_face.add(new Cart("Bentuk Alis", 10000));
        list_face.add(new Cart("Make Up", 100000));
        list_face.add(new Cart("Tanam Bulu Mata", 150000));
        list_face.add(new Cart("Facial Jafra", 110000));
    }

}
