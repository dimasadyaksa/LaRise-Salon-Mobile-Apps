package com.example.larise;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class menu extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public menu() {
        // Required empty public constructor
    }
    public static menu newInstance(String param1, String param2) {
        menu fragment = new menu();
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
        // Inflate the layout for this fragment
        final View fView = inflater.inflate(R.layout.fragment_menu, container, false);
        final ImageView mFullBody = fView.findViewById(R.id.fullbody);
        mFullBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),full_body.class);
                startActivity(goToNextActivity);
            }
        });

        final ImageView mFace = fView.findViewById(R.id.face);
        mFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),face_treatment.class);
                startActivity(goToNextActivity);
            }
        });

        final ImageView mHandFoot = fView.findViewById(R.id.handFoot);
        mHandFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),hand_n_foot.class);
                startActivity(goToNextActivity);
            }
        });

        final ImageView mHourT = fView.findViewById(R.id.hour);
        mHourT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),hour_treatment.class);
                startActivity(goToNextActivity);
            }
        });

        return fView;
    }

}
