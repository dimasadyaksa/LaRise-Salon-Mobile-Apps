package com.example.larise;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class pesanan extends Fragment  {

    private ArrayList<pesanan_obj> pesanans;

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
        rv_pesanan_adapter adapter = new rv_pesanan_adapter(pesanans);
        RecyclerView recyclerView = (RecyclerView) fView.findViewById(R.id.rv_pesanan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return fView;
    }


    void addData(){
        Log.d("M", "Added");
        pesanans.add(new pesanan_obj("Dimas Maulana", 101200));
        pesanans.add(new pesanan_obj("Dimas wqna", 10200));
        pesanans.add(new pesanan_obj("Dimasqwlana", 112));
        pesanans.add(new pesanan_obj("Diqwna", 102200));
        pesanans.add(new pesanan_obj("Dimas qwna", 11200));

    }
}
