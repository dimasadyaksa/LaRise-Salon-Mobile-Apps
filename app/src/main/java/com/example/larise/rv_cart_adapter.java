package com.example.larise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rv_cart_adapter extends RecyclerView.Adapter<rv_cart_adapter.rv_order_ViewHolder> {

  private ArrayList<Cart> cart;

  public rv_cart_adapter(ArrayList<Cart> cart){
    this.cart = cart;
  }

  @Override
  public rv_cart_adapter.rv_order_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.rv_order, parent, false);
    return new rv_order_ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(rv_order_ViewHolder holder, int position) {
    holder.txtid.setText(cart.get(position).getId());
    holder.txtBiaya.setText(String.valueOf(cart.get(position).getTotal()));
    holder.txtStatus.setText(cart.get(position).getStatus());
  }


  @Override
  public int getItemCount() {

    return (cart!=null) ? cart.size():0;
  }

  public class rv_order_ViewHolder extends RecyclerView.ViewHolder{
    private TextView txtid, txtBiaya,txtStatus;


    public rv_order_ViewHolder(View itemView) {
      super(itemView);
      txtid = (TextView) itemView.findViewById(R.id.ID);
      txtBiaya = (TextView) itemView.findViewById(R.id.Cost);
      txtStatus = itemView.findViewById(R.id.Status);
    }
  }
}
