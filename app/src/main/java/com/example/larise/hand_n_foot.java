package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class hand_n_foot extends AppCompatActivity {
    private ArrayList<pesanan_obj> list_hnb;
    private Intent i;
    private RecyclerView recyclerView;
    private full_body_adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private user user;
    public hand_n_foot(){
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        list_hnb = new ArrayList<>();
        dataHnB();
        Intent intent = getIntent();
        user = (user)intent.getSerializableExtra("USER");
        FirebaseHelper fb ;
        fb = (FirebaseHelper)intent.getSerializableExtra("FB");
        mAdapter = new full_body_adapter(list_hnb,fb);
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
    void dataHnB(){
        Log.d("M", "Added");
        list_hnb.add(new pesanan_obj("HnB Treatment A", 101200));
        list_hnb.add(new pesanan_obj("HnB Treatment B", 10200));
        list_hnb.add(new pesanan_obj("HnB Treatment C", 112));
        list_hnb.add(new pesanan_obj("HnB Treatment C", 102200));
        list_hnb.add(new pesanan_obj("HnB Treatment D", 11200));
    }
}
