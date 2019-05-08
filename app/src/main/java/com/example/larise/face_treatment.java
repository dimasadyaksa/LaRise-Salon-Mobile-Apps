package com.example.larise;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class face_treatment extends Activity {
    private ArrayList<full_body_obj> list_face;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public face_treatment(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        list_face = new ArrayList<>();
        dataFaceT();

        full_body_adapter adapter = new full_body_adapter(list_face);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(adapter);
    }

    void dataFaceT(){
        Log.d("M", "Added");
        list_face.add(new full_body_obj("Face Treatment A", 101200));
        list_face.add(new full_body_obj("Face Treatment B", 10200));
        list_face.add(new full_body_obj("Face Treatment C", 112));
        list_face.add(new full_body_obj("Face Treatment C", 102200));
        list_face.add(new full_body_obj("Face Treatment D", 11200));
    }

}
