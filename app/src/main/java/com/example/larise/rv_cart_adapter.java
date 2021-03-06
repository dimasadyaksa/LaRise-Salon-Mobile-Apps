package com.example.larise;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class rv_cart_adapter extends RecyclerView.Adapter<rv_cart_adapter.rv_order_ViewHolder> {

  private ArrayList<PesananObjek> pesananObjek;

  private void update(ArrayList<PesananObjek> p){
    pesananObjek.clear();
    pesananObjek = p;
  }

  public rv_cart_adapter(ArrayList<PesananObjek> pesananObjek){
    this.pesananObjek = pesananObjek;
  }

  @Override
  public rv_cart_adapter.rv_order_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.rv_order, parent, false);
    return new rv_order_ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(rv_order_ViewHolder holder, int position) {
    if (getItemCount()!=0){
      holder.txtid.setText(pesananObjek.get(position).getId());
      holder.txtBiaya.setText(String.valueOf(pesananObjek.get(position).getTotal()));
      holder.txtStatus.setText(pesananObjek.get(position).getStatus());

    }

  }


  @Override
  public int getItemCount() {

    return (pesananObjek !=null) ? pesananObjek.size():0;
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
