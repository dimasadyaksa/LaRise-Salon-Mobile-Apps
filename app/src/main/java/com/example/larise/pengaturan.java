package com.example.larise;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link pengaturan.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link pengaturan#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pengaturan, container, false);
    }

}
