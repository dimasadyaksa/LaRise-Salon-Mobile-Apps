package com.example.larise;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class menu extends Fragment {
  private user user;
  private FirebaseHelper fb;
  public menu() {
    // Required empty public constructor
  }
  public static menu newInstance(user user,FirebaseHelper fb) {
    menu fragment = new menu();
    Bundle args = new Bundle();
    args.putSerializable("FB", fb);
    args.putSerializable("USER", user);
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
        Bundle args = getArguments();
        if(args!=null){
            this.user = (user) args.getSerializable("USER");
            this.fb = (FirebaseHelper)args.getSerializable("FB");
        }
        final View fView = inflater.inflate(R.layout.fragment_menu, container, false);
        final ImageView mFullBody = fView.findViewById(R.id.fullbody);

        mFullBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),full_body.class);
                goToNextActivity.putExtra("USER", user);
                goToNextActivity.putExtra("FB", fb);
                startActivity(goToNextActivity);
            }
        });
        final ImageView mFace = fView.findViewById(R.id.face);
        mFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),face_treatment.class);
                goToNextActivity.putExtra("USER", user);
                goToNextActivity.putExtra("FB", fb);
                startActivity(goToNextActivity);
            }
        });

        final ImageView mHandFoot = fView.findViewById(R.id.handFoot);
        mHandFoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),hand_n_foot.class);
                goToNextActivity.putExtra("USER", user);
                goToNextActivity.putExtra("FB", fb);
                startActivity(goToNextActivity);
            }
        });

        final ImageView mHourT = fView.findViewById(R.id.hour);
        mHourT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(getActivity(),hour_treatment.class);
                goToNextActivity.putExtra("USER", user);
                goToNextActivity.putExtra("FB", fb);
                startActivity(goToNextActivity);
            }
        });
        return fView;
    }

}