package com.example.larise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class full_body_adapter extends RecyclerView.Adapter<full_body_adapter.rv_order_ViewHolder> {
    private ArrayList<full_body_obj> mDataset;

    public full_body_adapter(ArrayList<full_body_obj> mDataset){
        this.mDataset = mDataset;
    }


    @Override
    public rv_order_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_full_body, parent, false);
        return new rv_order_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(full_body_adapter.rv_order_ViewHolder holder, int position) {
        holder.txtPesanan.setText(mDataset.get(position).getNama());
        holder.txtBiaya.setText(String.valueOf(mDataset.get(position).getBiaya()));
    }

    @Override
    public int getItemCount() {

        return (mDataset!=null) ? mDataset.size():0;
    }

    public class rv_order_ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtPesanan, txtBiaya;


        public rv_order_ViewHolder(View itemView) {
            super(itemView);
            txtPesanan = (TextView) itemView.findViewById(R.id.order_id);
            txtBiaya = (TextView) itemView.findViewById(R.id.order_biaya);
        }
    }
}