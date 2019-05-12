package com.example.larise;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pesanan extends Fragment  {

  private ArrayList<Cart> pesanans;

  public pesanan() {
  }

  public static pesanan newInstance() {
    pesanan fragment = new pesanan();
    Bundle args = new Bundle();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View fView = inflater.inflate(R.layout.fragment_pesanan, container, false);
    pesanans = new ArrayList<>();
    addData();
    rv_cart_adapter adapter = new rv_cart_adapter(GLOBAL.carts);
    RecyclerView recyclerView = (RecyclerView) fView.findViewById(R.id.rv_pesanan);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    return fView;
  }


  void addData(){
    Log.d("M", "Added");
    pesanans.add(new Cart( "OK", 23,3));
    pesanans.add(new Cart( "OK", 2,33));
    pesanans.add(new Cart( "NO", 62,3));
    pesanans.add(new Cart( "NO", 22,3));
    pesanans.add(new Cart( "OK", 2,32));
  }
}
