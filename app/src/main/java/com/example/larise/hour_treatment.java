package com.example.larise;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class hour_treatment extends AppCompatActivity {
    private ArrayList<full_body_obj> list_hour;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public hour_treatment(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        list_hour= new ArrayList<>();
        dataHourT();

        full_body_adapter adapter = new full_body_adapter(list_hour);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);
    }

    void dataHourT(){
        Log.d("M", "Added");
        list_hour.add(new full_body_obj("Creambath", 40000));
        list_hour.add(new full_body_obj("Hair Mask", 45000));
        list_hour.add(new full_body_obj("Hair Spa", 45000));
        list_hour.add(new full_body_obj("Hair Color", 100000));
        list_hour.add(new full_body_obj("Smoothing", 150000));
        list_hour.add(new full_body_obj("Gunting", 25000));
        list_hour.add(new full_body_obj("Cuci Rambot", 15000));
        list_hour.add(new full_body_obj("Catok", 25000));
        list_hour.add(new full_body_obj("Curly", 30000));
    }
}
