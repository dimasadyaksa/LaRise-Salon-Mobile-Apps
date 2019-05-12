package com.example.larise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartView extends AppCompatActivity {
    private ArrayList<Cart> list_pesanan;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private FirebaseHelper fb;
    private Intent i;
    private Button Pesan;
	 CartViewAdapter adapter;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        Pesan = findViewById(R.id.pesan);
		pd = new ProgressDialog(this);
        pd.setMessage("Sedang Memuat...");
        pd.setCanceledOnTouchOutside(false);
		pd.show();
        i  = new Intent();
		Intent intent = getIntent();
		user user = (user)intent.getSerializableExtra("USER");

		final TextView cost = findViewById(R.id.totalCost);
        Pesan.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent toMap = new Intent(CartView.this,MapsActivity.class);
                Log.e("COST", "="+cost());
                toMap.putExtra("TOTAL", cost());
                startActivity(toMap);
                return false;
            }
        });
		if(pd.isShowing()){
			pd.hide();
			pd.dismiss();

		}
		recyclerView = findViewById(R.id.rv_cart);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new CartViewAdapter(GLOBAL.carts);
		recyclerView.swapAdapter(adapter,false);
		cost.setText(Integer.toString(cost()));
        i.putExtra("USER", user);

    }
    public void onBackPressed(){
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public int cost(){
        int sum=0;
        for(int i = 0; i<GLOBAL.carts.size(); i++){
            sum +=GLOBAL.carts.get(i).getBiaya();
        }
        return sum;
    }
}
