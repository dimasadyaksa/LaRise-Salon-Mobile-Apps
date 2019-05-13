package com.example.larise;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class register extends AppCompatActivity {
    private Button button;
    private EditText nama;
    private EditText email;
    private EditText hp;
    private EditText password;
    private TextView login;
    private String userID;
    private ArrayList<user> users;
    private user user;
    private FirebaseAuth auth;
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        registrasi();
        moveLogin();
        resetPassword();
    }

    public void resetPassword(){
        TextView textView = findViewById(R.id.lpPassword);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPass();
            }
        });
    }

    public void registrasi(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Loading");
                progressDialog.show();
                final String namauser = nama.getText().toString().trim();
                final String emailuser = email.getText().toString().trim();
                final String nohp = hp.getText().toString().trim();
                final String passworduser = password.getText().toString().trim();
                users = new ArrayList<>();
                if(namauser.isEmpty()||emailuser.isEmpty()||passworduser.isEmpty()){
                    Toast.makeText(register.this, "Nama, Email atau password tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }else if(!Patterns.EMAIL_ADDRESS.matcher(emailuser).matches()){
                    Toast.makeText(register.this, "Email Tidak Valid!",
                            Toast.LENGTH_SHORT).show();
                }else if (passworduser.length()<6){
                    Toast.makeText(register.this, "Password minimal harus terdiri dari 6 karakter!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    auth.createUserWithEmailAndPassword(emailuser,passworduser)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        user = new user(auth.getUid(),namauser,emailuser,nohp,passworduser);
                                        db = FirebaseDatabase.getInstance().getReference();

                                        userID = auth.getUid();
                                        db.child("users").child(userID).setValue(user);
                                        Toast.makeText(register.this, "Sukses mendaftar!",
                                                Toast.LENGTH_SHORT).show();

                                        Intent goToNextActivity = new Intent(register.this, mainmenu.class);

                                        goToNextActivity.putExtra("UID",auth.getUid() );

                                        if(progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                        }
                                        startActivity(goToNextActivity);
                                    }else{
                                        Toast.makeText(register.this, "Gagal Mendaftar!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

    public void moveLogin(){
        login = findViewById(R.id.login_in_reg);
        login.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent goToNextActivity = new Intent(register.this, login.class);
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
                    Toast.makeText(register.this, "Email tidak valid",
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

    public void init(){
        button = findViewById(R.id.register);
        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        hp = findViewById(R.id.noHP);
        password = findViewById(R.id.password);
        auth = FirebaseAuth.getInstance();
    }

}
