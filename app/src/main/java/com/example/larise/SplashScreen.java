package com.example.larise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        SharedPreferences mSettings = this.getSharedPreferences("userdata", Context.MODE_PRIVATE);
        String email = mSettings.getString("email", "missing");
        String password = mSettings.getString("password", "missing");
        String UID = mSettings.getString("UID", "missing");
        if(email.equals("missing")||password.equals("missing")||UID.equals("missing")){
            int secondsDelayed = 2;
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashScreen.this,login.class));
                    finish();
                }
            }, secondsDelayed * 1000);
        }else {
            Toast.makeText(SplashScreen.this, "Login sebagai "+email,
                    Toast.LENGTH_SHORT).show();
            Intent goToNextActivity = new Intent(SplashScreen.this, mainmenu.class);
            goToNextActivity.putExtra("UID",UID );
            startActivity(goToNextActivity);
        }

    }
}
