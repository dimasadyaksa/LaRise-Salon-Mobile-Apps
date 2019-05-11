package com.example.larise;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.larise.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback{

    private GoogleMap mMap;
    private String userID;
    private DatabaseReference db;
    FirebaseAuth auth;
    Button getLocationBtn;
    TextView locationText;
    String Alamat;

    LocationManager locationManager;
    private user user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps);
        getLocationBtn = (Button)findViewById(R.id.lokasiSaya);
        locationText = (TextView)findViewById(R.id.btn_kiri);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        getLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                loadMap();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        }catch(Exception e)
        {

        }

        setLat(location.getLatitude());
        setLang(location.getLongitude());
    }

    double lat,lang;
    void setLat(double lat){
        this.lat = lat;
    }

    void setLang (double lang){
        this.lang = lang;
    }
    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MapsActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    public void loadMap(){
        LatLng myLoc = new LatLng(lat, lang);
        mMap.addMarker(new MarkerOptions().position(myLoc).title("Lokasi Saya"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc,16));
        addDb(myLoc);
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    public void addDb(LatLng l){
        String ctt = findViewById(R.id.catatan).toString().trim();
        user = new user(l, ctt);
        db = FirebaseDatabase.getInstance().getReference();
        Query a = db.limitToLast(1);
        String x = db.child("pesanan").child("ADYtRlOHV5ODGoebM8iGCHN1tjG3").limitToLast(1).toString();
        Log.e("DATA",x);
        db.child("pesanan").child("ADYtRlOHV5ODGoebM8iGCHN1tjG3").child(x).setValue(user);
    }
}