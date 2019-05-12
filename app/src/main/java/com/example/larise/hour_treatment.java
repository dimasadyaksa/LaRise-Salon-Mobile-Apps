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
    private full_body_adapter mAdapter;
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
        cekOnChart();
        mAdapter = new full_body_adapter(list_hour,fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(mAdapter);
    }
    void cekOnChart(){
        for(int i=0;i<list_hour.size();i++){
            if(GLOBAL.carts.contains(list_hour.get(i))){
                list_hour.get(i).isOnChart = true;
            }else{
                list_hour.get(i).isOnChart = false;
            }
        }
    }
    public void onBackPressed(){
/*        user = mAdapter.getUser();
        Log.e("USERM", user.getEmail());
        i = new Intent();
        i.putExtra("UID", user.getUID());
        i.putExtra("USER", user);
        setResult(RESULT_OK, i);
        finish();
*/
        finish();
    }
    void dataHourT(){
        Log.d("M", "Added");
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
