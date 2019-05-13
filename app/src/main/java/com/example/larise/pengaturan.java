package com.example.larise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class pengaturan extends Fragment {

    public pengaturan() {
        // Required empty public constructor
    }

    public static pengaturan newInstance() {
        pengaturan fragment = new pengaturan();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fview = inflater.inflate(R.layout.fragment_pengaturan, container, false);
        SharedPreferences mSettings = getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = mSettings.edit();
        TextView logout = fview.findViewById(R.id.LogoutSetting);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("email", "missing");
                editor.putString("password", "missing");
                editor.putString("UID", "misssing");
                editor.apply();
                startActivity(new Intent(getActivity(),login.class));
            }
        });
        return fview;
    }

}
