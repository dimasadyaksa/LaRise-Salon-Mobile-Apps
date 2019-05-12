package com.example.larise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pesanan extends Fragment  {

  private ArrayList<PesananObjek> pesanans;

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
    pesanans = GLOBAL.pesananObjeks;
    View fView = inflater.inflate(R.layout.fragment_pesanan, container, false);
    rv_cart_adapter adapter = new rv_cart_adapter(pesanans);
    RecyclerView recyclerView = (RecyclerView) fView.findViewById(R.id.rv_pesanan);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    return fView;
  }

}
