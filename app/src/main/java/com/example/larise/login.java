package com.example.larise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button login;
    private ProgressDialog pd;
    private TextView dftr;
    private DatabaseReference db;
    boolean done = false;
    private ArrayList<user> users;
    private String uid;
    private user userTmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password  = findViewById(R.id.password);
        login   = findViewById(R.id.login);
        pd = new ProgressDialog(login.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
                    Toast.makeText(login.this, "Email atau password tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }else{
                    signIn();
                }
            }
        });

        dftr = findViewById(R.id.Daftar);
        dftr.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(login.this, register.class);
                startActivity(goToNextActivity);
            }
        });

    }

    public void signIn(){
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        pd.setMessage("Logging In");
        pd.show();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pd.setCanceledOnTouchOutside(false);

                        if(task.isSuccessful()){
                            uid = mAuth.getUid();
                            Toast.makeText(login.this, "Authentication Succeded.",
                                    Toast.LENGTH_SHORT).show();

                            Intent goToNextActivity = new Intent(login.this, mainmenu.class);

                            goToNextActivity.putExtra("UID",uid );

                            if(pd.isShowing()){
                                pd.dismiss();
                            }
                            startActivity(goToNextActivity);

                        }else{
                            Toast.makeText(login.this, "USERNAME ATAU PASSWORD SALAH",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}
