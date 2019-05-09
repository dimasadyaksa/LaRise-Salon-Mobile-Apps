package com.example.larise;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class full_body extends Activity {
    private ArrayList<full_body_obj> list_full_body;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public full_body (){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        list_full_body = new ArrayList<>();
        dataFullBody();

        full_body_adapter adapter = new full_body_adapter(list_full_body);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);
    }

    void dataFullBody(){
        Log.d("M", "Added");
        list_full_body.add(new full_body_obj("Full Body A", 101200));
        list_full_body.add(new full_body_obj("Full Body B", 10200));
        list_full_body.add(new full_body_obj("Full Body C", 112));
        list_full_body.add(new full_body_obj("Full Body C", 102200));
        list_full_body.add(new full_body_obj("Full Body D", 11200));
    }

}
