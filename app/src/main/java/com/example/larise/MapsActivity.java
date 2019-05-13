package com.example.larise;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback {
    List<Address> addresses;
    private GoogleMap mMap;
    private String userID;
    private DatabaseReference db;
    FirebaseAuth auth;
    Button getLocationBtn;
    Button checkout;
    MarkerOptions markerOptions = new MarkerOptions();
    TextView locationText;
    String Alamat;
    PesananObjek pesananObjek = new PesananObjek();
    int cost;
    Intent intent;
    private FusedLocationProviderClient fusedLocationClient;
    LocationManager locationManager;
    private user user;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        intent = getIntent();
        progressDialog =  new ProgressDialog(this);
        final AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this).create();
        checkout = findViewById(R.id.Checkout);
        getLocationBtn = (Button) findViewById(R.id.lokasiSaya);
        locationText = (TextView) findViewById(R.id.btn_kiri);
        getLocationBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        lat = location.getLatitude();
                        lang = location.getLongitude();
                        mMap.clear();
                        LatLng l = new LatLng(lat, lang);
                        markerOptions.position(l);
                        Alamat = getAlamat(new LatLng(lat, lang));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l, 18));
                        mMap.addMarker(markerOptions);
                    }
                });
                return false;
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
                format.setCurrency(Currency.getInstance("IDR"));
                String result = format.format(intent.getIntExtra("TOTAL", 0));
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Total Biaya : "+result+"\nDikirim ke Alamat : "+Alamat);
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "BATAL",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "KONFIRMASI",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                EditText c = findViewById(R.id.catatan);
                                String ctt = c.getText().toString();
                                progressDialog.show();
                                pesananObjek.Alamat = Alamat;
                                pesananObjek.setPesanan(GLOBAL.carts);
                                pesananObjek.setLatitude(lat);
                                pesananObjek.setLongitude(lang);
                                pesananObjek.setTotal(intent.getIntExtra("TOTAL",0));

                                pesananObjek.setStatus("PENDING");
                                pesananObjek.setId();
                                pesananObjek.setCatatan(ctt);
                                final TaskCompletionSource<String> source = new TaskCompletionSource<>();
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        GLOBAL.sendPesanan(pesananObjek);
                                        source.setResult("DONE");
                                    }
                                }).start();
                                Task<String> task = source.getTask();
                                task.addOnCompleteListener(new OnCompleteListener<String>() {
                                    @Override
                                    public void onComplete(@NonNull Task<String> task) {
                                        GLOBAL.carts.clear();
                                        GLOBAL.pesananObjeks.add(pesananObjek);
                                        startActivity(new Intent(MapsActivity.this,mainmenu.class));
                                        if(progressDialog.isShowing()){
                                            progressDialog.dismiss();
                                            progressDialog.hide();
                                        }
                                    }
                                });
                            }
                        });
                alertDialog.show();
                return false;
            }
        });
        if (ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(getApplicationContext(), android.Manifest.permission.
                        ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (Exception e) {

        }

        setLat(location.getLatitude());
        setLang(location.getLongitude());
    }

    double lat, lang;

    void setLat(double lat) {
        this.lat = lat;
    }

    void setLang(double lang) {
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

    public void loadMap() {
        LatLng myLoc = new LatLng(lat, lang);
        mMap.addMarker(new MarkerOptions().position(myLoc).title("Lokasi Saya"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 16));
        addDb(myLoc);
    }

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                lat = location.getLatitude();
                lang = location.getLongitude();
                LatLng l = new LatLng(lat, lang);
                markerOptions.position(l);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(l, 19));
                mMap.addMarker(markerOptions);
                progressDialog.hide();
            }
        });


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                lat = latLng.latitude;
                lang = latLng.longitude;
                markerOptions.position(latLng);
                mMap.addMarker(markerOptions);
               Alamat= getAlamat(latLng);
            }
        });
    }

    public String getAlamat(LatLng l){
        String s="";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(l.latitude, l.longitude, 1);
        } catch (Exception e) {
            Toast.makeText(MapsActivity.this, "Gagal Mendapatkan Lokasi, Periksa koneksi anda.",
                    Toast.LENGTH_SHORT).show();
            AlertDialog alertDialog = new AlertDialog.Builder(MapsActivity.this).create();
            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

        }
        if (addresses!=null){
            for (int i=0;i<addresses.size();i++){
                s+=addresses.get(i).getAddressLine(i);
            }
        }else {
            s = "Tidak dapat menentukan lokasi";
            Toast.makeText(MapsActivity.this, s,
                    Toast.LENGTH_SHORT).show();
        }

        return s;
    }

    public void addDb(LatLng l){
        String ctt = findViewById(R.id.catatan).toString().trim();
        pesananObjek = new PesananObjek();
        pesananObjek.setLatitude(1);
        pesananObjek.setLongitude(2);
        pesananObjek.setCatatan(ctt);
        db = FirebaseDatabase.getInstance().getReference();
        Query a = db.limitToLast(1);
        String x = db.child("pesanan").child("ADYtRlOHV5ODGoebM8iGCHN1tjG3").limitToLast(1).toString();
        Log.e("DATA",x);
        db.child("pesanan").child("ADYtRlOHV5ODGoebM8iGCHN1tjG3").child(x).setValue(pesananObjek);
    }


}