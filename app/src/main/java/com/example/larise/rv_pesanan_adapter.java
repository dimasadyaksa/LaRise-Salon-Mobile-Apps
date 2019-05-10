package com.example.larise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class rv_pesanan_adapter extends RecyclerView.Adapter<rv_pesanan_adapter.rv_order_ViewHolder> {

  private ArrayList<pesanan_obj> pesanan;

  public rv_pesanan_adapter(ArrayList<pesanan_obj> pesanan){
    this.pesanan = pesanan;
  }

  @Override
  public rv_pesanan_adapter.rv_order_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.rv_pesanan, parent, false);
    return new rv_order_ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(rv_order_ViewHolder holder, int position) {
    holder.txtPesanan.setText(pesanan.get(position).getNama());
    holder.txtBiaya.setText(String.valueOf(pesanan.get(position).getBiaya()));
  }


  @Override
  public int getItemCount() {

    return (pesanan!=null) ? pesanan.size():0;
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
