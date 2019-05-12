package com.example.larise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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
	 CartViewAdapter adapter;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);

		pd = new ProgressDialog(this);
        pd.setMessage("Sedang Memuat...");
        pd.setCanceledOnTouchOutside(false);
		pd.show();

		i  = new Intent();
		Intent intent = getIntent();
		user user = (user)intent.getSerializableExtra("USER");
		TextView cost = findViewById(R.id.totalCost);

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

    public int cost(){
        int sum=0;
        for(int i = 0; i<GLOBAL.carts.size(); i++){
            sum +=GLOBAL.carts.get(i).getBiaya();
        }
        return sum;
    }
}
