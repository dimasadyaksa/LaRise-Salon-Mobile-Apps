package com.example.larise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private Button login;
    private ProgressDialog pd;

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
    }

    public void signIn(){
        String email = "dimas@dim.co";//this.email.getText().toString();
        String password = "dimas123";//this.password.getText().toString();
        pd.setMessage("Logging In");
        pd.show();
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(pd.isShowing()){
                            pd.dismiss();
                        }
                        if(task.isSuccessful()){
                            Toast.makeText(login.this, "Authentication Succeded.",
                                    Toast.LENGTH_SHORT).show();
                            Intent goToNextActivity = new Intent(login.this, mainmenu.class);
                            startActivity(goToNextActivity);
                        }else{
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
