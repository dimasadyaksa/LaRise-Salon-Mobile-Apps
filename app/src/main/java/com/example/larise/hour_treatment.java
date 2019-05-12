package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hour_treatment extends AppCompatActivity {
    private ArrayList<pesanan_obj> list_hour;

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

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        list_hour= new ArrayList<>();
        dataHourT();
        Intent intent = getIntent();
        user = (user)intent.getSerializableExtra("USER");
        FirebaseHelper fb ;
        fb = (FirebaseHelper)intent.getSerializableExtra("FB");
        mAdapter = new full_body_adapter(list_hour,fb);
        recyclerView = findViewById(R.id.full_body_rv);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        recyclerView.setAdapter(mAdapter);
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
        list_hour.add(new pesanan_obj("Hour Treatment A", 101200));
        list_hour.add(new pesanan_obj("Hour Treatment B", 10200));
        list_hour.add(new pesanan_obj("Hour Treatment C", 112));
        list_hour.add(new pesanan_obj("Hour Treatment C", 102200));
        list_hour.add(new pesanan_obj("Hour Treatment D", 11200));
    }
}
