package com.example.larise;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
        resetPassword();
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
    public void forgotPass(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Email anda");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!Patterns.EMAIL_ADDRESS.matcher(input.getText().toString()).matches()){
                    Toast.makeText(login.this, "Email tidak valid",
                            Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String emailAddress = input.getText().toString();

                    auth.sendPasswordResetEmail(emailAddress)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("EMAIL", input.getText().toString());
                                    }
                                }
                            });

                }
            }
        });
        builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public void resetPassword(){
        TextView textView = findViewById(R.id.lpPasswordLog);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPass();
            }
        });
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void signIn(){
        SharedPreferences mSettings = this.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = mSettings.edit();
        final String email = this.email.getText().toString();
        final String password = this.password.getText().toString();
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
                            editor.putString("email", email);
                            editor.putString("password", password);
                            editor.putString("UID", uid);
                            editor.apply();
                            Intent goToNextActivity = new Intent(login.this, mainmenu.class);

                            goToNextActivity.putExtra("UID",uid );

                            if(pd.isShowing()){
                                pd.dismiss();
                            }
                            startActivity(goToNextActivity);

                        }else{
                            Toast.makeText(login.this, "USERNAME ATAU PASSWORD SALAH",
                                    Toast.LENGTH_SHORT).show();
                            if(pd.isShowing()){
                                pd.dismiss();
                            }
                        }
                    }
                });

    }

}
