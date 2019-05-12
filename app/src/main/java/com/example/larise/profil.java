package com.example.larise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class profil extends Fragment {
    private String UID,nama,email,nohp;
    private FirebaseHelper fb;
    private View fview;
    public profil() {
        // Required empty public constructor
    }


    public static profil newInstance(String UID,String nama,String email,String nohp) {
        profil fragment = new profil();
        Bundle args = new Bundle();
        args.putString("UID",UID );
        args.putString("nama",nama );
        args.putString("email",email );
        args.putString("nohp",nohp );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle args = getArguments();
        fview = inflater.inflate(R.layout.fragment_profil, container, false);
        final TextView nama = fview.findViewById(R.id.namaProfil);
        final TextView email = fview.findViewById(R.id.emaildata);
        final TextView phone = fview.findViewById(R.id.phonedata);
        if (args != null) {
            UID = args.getString("UID", "");
            this.nama = args.getString("nama");
            this.email = args.getString("email");
            this.nohp = args.getString("nohp");

        }
        nama.setText(GLOBAL.user.getNama());
        email.setText(this.email);
        phone.setText(this.nohp);
        return fview;
    }

}
