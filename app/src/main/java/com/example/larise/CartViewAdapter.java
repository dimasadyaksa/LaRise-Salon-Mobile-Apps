package com.example.larise;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.rv_cart_ViewHolder>{
    private ArrayList<Cart> pesanan;
    public CartViewAdapter(ArrayList<Cart> cart){
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
        final int posisi = position;
        holder.txtnama.setText(pesanan.get(position).getNama());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        String result = format.format(pesanan.get(position).getBiaya());
        holder.txtBiaya.setText(result);
        holder.Batal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pesanan.remove(posisi);
                notifyDataSetChanged();
                return false;
            }
        });

    }


    @Override
    public int getItemCount() {

        return (pesanan!=null) ? pesanan.size():0;
    }

    public class rv_cart_ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtnama, txtBiaya;
        private Button Batal;


        public rv_cart_ViewHolder(View itemView) {
            super(itemView);
            Batal = itemView.findViewById(R.id.Konfirm);
            txtnama = (TextView) itemView.findViewById(R.id.order_id);
            txtBiaya = (TextView) itemView.findViewById(R.id.order_biaya);

        }
    }
}