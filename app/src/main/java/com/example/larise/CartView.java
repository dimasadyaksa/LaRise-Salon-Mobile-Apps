package com.example.larise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.ArrayList;

public class CartView extends AppCompatActivity {
    private ArrayList<pesanan_obj> list_pesanan;
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

/*		pd = new ProgressDialog(this);
        pd.setMessage("Sedang Memuat...");
        pd.setCanceledOnTouchOutside(false);
		pd.show();
*/
		i  = new Intent();
		Intent intent = getIntent();
		user user = (user)intent.getSerializableExtra("USER");
		TextView cost = findViewById(R.id.totalCost);


		final TaskCompletionSource<String> source = new TaskCompletionSource<>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				fb = new FirebaseHelper();
				fb.downloadPesanan();
				source.setResult("DONE");
			}
		}).start();
		Task<String> task = source.getTask();
		task.addOnCompleteListener(new OnCompleteListener<String>() {
			@Override
			public void onComplete(@NonNull Task<String> task) {
				adapter.notifyDataSetChanged();
			/*	if(pd.isShowing()){
					pd.hide();
					pd.dismiss();

				}*/
			}
		});
		recyclerView = findViewById(R.id.rv_cart);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		adapter = new CartViewAdapter(GLOBAL.pesanans);
		recyclerView.setAdapter(adapter);
		cost.setText(Integer.toString(cost()));
        i.putExtra("USER", user);

    }
    public void onBackPressed(){
        setResult(RESULT_OK, i);
        finish();
    }

    public int cost(){
        int sum=0;
        for(int i=0;i<GLOBAL.pesanans.size();i++){
            sum +=GLOBAL.pesanans.get(i).getBiaya();
        }
        return sum;
    }
}
