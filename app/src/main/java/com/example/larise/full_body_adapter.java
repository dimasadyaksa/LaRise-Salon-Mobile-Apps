package com.example.larise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

import java.util.ArrayList;

public class full_body_adapter extends RecyclerView.Adapter<full_body_adapter.rv_order_ViewHolder> {
    private ArrayList<pesanan_obj> mDataset;
    private user user;
    private FirebaseHelper fb;

    public full_body_adapter(ArrayList<pesanan_obj> mDataset,FirebaseHelper fb){
        this.mDataset = mDataset;
        this.fb = fb;
    }

    public user getUser(){
        return this.user;
    }

    @Override
    public rv_order_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_full_body, parent, false);

        return new rv_order_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(full_body_adapter.rv_order_ViewHolder holder, final int position) {
        holder.txtPesanan.setText(mDataset.get(position).getNama());
        holder.txtBiaya.setText(String.valueOf(mDataset.get(position).getBiaya()));
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final pesanan_obj pesanan_obj = new pesanan_obj(mDataset.get(position).getNama(),mDataset.get(position).getBiaya());
                GLOBAL.pesanans.add(pesanan_obj);
                final TaskCompletionSource<String> source = new TaskCompletionSource<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        fb.sendPesanan(pesanan_obj);
                        source.setResult("DONE");
                    }
                }).start();
                Task<String> task = source.getTask();
                task.addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        user = fb.getUs();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {

        return (mDataset!=null) ? mDataset.size():0;
    }
    public class rv_order_ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtPesanan, txtBiaya;
        private Button add;

        public rv_order_ViewHolder(View itemView) {
            super(itemView);
            add = (Button) itemView.findViewById(R.id.Add);
            txtPesanan = (TextView) itemView.findViewById(R.id.order_nama);
            txtBiaya = (TextView) itemView.findViewById(R.id.order_cost);

        }


    }
}
