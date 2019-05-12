package com.example.larise;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class full_body_adapter extends RecyclerView.Adapter<full_body_adapter.rv_order_ViewHolder> {
    private ArrayList<Cart> mDataset;
    private user user;
    private FirebaseHelper fb;
    Cart Cart;
    public full_body_adapter(ArrayList<Cart> mDataset, FirebaseHelper fb){
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
    public void onBindViewHolder(final full_body_adapter.rv_order_ViewHolder holder, int position) {
        Cart = new Cart(mDataset.get(position).getNama(),mDataset.get(position).getBiaya());
        Cart.isOnChart = mDataset.get(position).isOnChart;
        holder.txtPesanan.setText(mDataset.get(position).getNama());
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("Rp. "));
        String result = format.format(Cart.getBiaya());
        holder.txtBiaya.setText(result);
        holder.add.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                    GLOBAL.tambahCart(Cart);
                return false;
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
