package com.example.larise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.rv_cart_ViewHolder>{
    private ArrayList<pesanan_obj> pesanan;
    public CartViewAdapter(ArrayList<pesanan_obj> cart){
        this.pesanan = cart;

    }

    @Override
    public CartViewAdapter.rv_cart_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_cart, parent, false);
        return new rv_cart_ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull CartViewAdapter.rv_cart_ViewHolder holder, int position) {
        holder.txtnama.setText(pesanan.get(position).getNama());
        holder.txtBiaya.setText(Integer.toString(pesanan.get(position).getBiaya()));

    }


    @Override
    public int getItemCount() {

        return (pesanan!=null) ? pesanan.size():0;
    }

    public class rv_cart_ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtnama, txtBiaya;


        public rv_cart_ViewHolder(View itemView) {
            super(itemView);
            txtnama = (TextView) itemView.findViewById(R.id.order_id);
            txtBiaya = (TextView) itemView.findViewById(R.id.order_biaya);

        }
    }
}