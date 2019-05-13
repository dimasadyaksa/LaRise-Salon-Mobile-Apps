package com.example.larise;

import android.util.Log;
import android.view.LayoutInflater;
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
    private TextView costView;
    private Button pesan;
    public CartViewAdapter(ArrayList<Cart> cart){
        this.pesanan = cart;

    }

    public void setCost(TextView v){
        this.costView = v;
    }

    public void setPesan(Button b){
        this.pesan = b;
    }

    @Override
    public CartViewAdapter.rv_cart_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_cart, parent, false);
        return new rv_cart_ViewHolder(view,this,costView,pesan);
    }



    @Override
    public void onBindViewHolder(@NonNull final CartViewAdapter.rv_cart_ViewHolder holder, int position) {
        final int posisi = position;
        holder.txtnama.setText(pesanan.get(position).getNama());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        String result = format.format(pesanan.get(position).getBiaya());
        holder.txtBiaya.setText(result);

    }




    @Override
    public int getItemCount() {

        return (pesanan!=null) ? pesanan.size():0;
    }

    public class rv_cart_ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtnama, txtBiaya,costView;
        private Button Batal,pesan;
        final CartViewAdapter adapter;

        public rv_cart_ViewHolder(View itemView,CartViewAdapter adapter,TextView v,Button b) {
            super(itemView);
            Batal = itemView.findViewById(R.id.Konfirm);
            txtnama = (TextView) itemView.findViewById(R.id.order_id);
            txtBiaya = (TextView) itemView.findViewById(R.id.order_biaya);
            this.adapter = adapter;
            this.costView = v;
            this.pesan = b;

            Batal.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.e("CLICK CART VIEW", "TRUE");
            GLOBAL.carts.remove(getLayoutPosition());
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
            format.setCurrency(Currency.getInstance("IDR"));
            String result = format.format(cost());
            costView.setText(result);
            if (cost()==0){
                pesan.setEnabled(false);
            }else{
                pesan.setEnabled(true);
            }
            adapter.notifyDataSetChanged();
        }
        public int cost(){
            int sum=0;
            for(int i = 0; i<GLOBAL.carts.size(); i++){
                sum +=GLOBAL.carts.get(i).getBiaya();
            }
            return sum;
        }
    }
}