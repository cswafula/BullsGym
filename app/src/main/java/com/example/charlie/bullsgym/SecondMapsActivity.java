package com.example.charlie.bullsgym;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class SecondMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float DEFAULT_ZOOM = 10f;
    private static final int REQUEST_LOCATION_PERMISSION=200;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    View mView;
    Button Finish;
    ProgressBar mProgressBar;

    public static final String REGISTER_URL="https://bulls-gym-api.herokuapp.com/api/Register";
    public static final String KEY_EMAIL="Email";
    public static final String KEY_PASS="Password";
    public static final String KEY_GENDER="Gender";
    public static final String KEY_WEIGHT="Weight";
    public static final String KEY_BMI="BMI";
    public static final String KEY_GYMNAME="GymName";
    public static final String KEY_LATITUDE="Latitude";
    public static final String KEY_LONGITUDE="Longitude";
    public static final String KEY_IMAGEURL="ImageURL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_maps);
        //check if the permissions have been granted
        checkpermissions();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Paper.init(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Bulls gym in Sydney"));

        //this is the default location
        LatLng Madaraka = new LatLng(-1.283, 36.817);
        mMap.addMarker(new MarkerOptions().position(Madaraka));
        moveCamera(new LatLng(-1.283, 36.817),DEFAULT_ZOOM);

        LatLng SouthB = new LatLng(-1.3103,36.8337);
        mMap.addMarker(new MarkerOptions().position(SouthB).title("Bulls Gym in South B").snippet("Welcome to South B gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Uthiru = new LatLng(-1.255,36.711);
        mMap.addMarker(new MarkerOptions().position(Uthiru).title("Bulls Gym in Uthiru").snippet("Welcome to Uthiru gym"))
        .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Ngong = new LatLng(-1.362, 36.657);
        mMap.addMarker(new MarkerOptions().position(Ngong).title("Bulls Gym in Ngon'g").snippet("Welcome to Ngon'g gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));


        final LatLng Langata = new LatLng(-1.3667, 36.7333);
        mMap.addMarker(new MarkerOptions().position(Langata).title("Bulls Gym in Lang'ata").snippet("Welcome to Lang'ata gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Dagoretti = new LatLng(-1.300, 36.767);
        mMap.addMarker(new MarkerOptions().position(Dagoretti).title("Bulls Gym in Dagoretti").snippet("Welcome to Dagoretti gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Kasarani = new LatLng(-1.217, 36.901);
        mMap.addMarker(new MarkerOptions().position(Kasarani).title("Bulls Gym in Kasarani").snippet("Welcome to Kasarani gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Embakasi = new LatLng( -1.323,36.9003);
        mMap.addMarker(new MarkerOptions().position(Embakasi).title("Bulls Gym in Embakasi").snippet("Welcome to Embakasi"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onMarkerClick(Marker marker) {
                final String name= marker.getTitle();
                String position=marker.getPosition().toString();
                final float lat= (float) marker.getPosition().latitude;
                final float lng= (float) marker.getPosition().longitude;
                String latitude=String.valueOf(lat);
                String longitude=String.valueOf(lng);

                //popup dialog
                final AlertDialog.Builder builder= new AlertDialog.Builder(SecondMapsActivity.this);
                mView = getLayoutInflater().inflate(R.layout.maps_dialog,null);
                builder.setView(mView);
                TextView GymName,GymLat,GymLng;
                GymName=mView.findViewById(R.id.GymName);
                GymLat=mView.findViewById(R.id.GymLatitude);
                GymLng=mView.findViewById(R.id.GymLongitude);
                Finish=mView.findViewById(R.id.Create_account_Gym);
                mProgressBar=mView.findViewById(R.id.Maps_Progress);

                GymName.setText(name);
                GymLat.setText("Gym Latitude: "+latitude);
                GymLng.setText("Gym Longitude: "+longitude);
                Paper.book().write("SelectedGym",name);
                Paper.book().write("Latitude",latitude);
                Paper.book().write("Longitude",longitude);

                Finish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Finish.setVisibility(View.GONE);
                        mProgressBar.setVisibility(View.VISIBLE);
                        RegisterUser();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();


                return false;
            }
        });

    }
    private void moveCamera(LatLng latLng,float zoom){
//moving the camera to your location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void checkpermissions() {
        //check real-time permissions if run higher API 23
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, REQUEST_LOCATION_PERMISSION);
            return;
        }
    }


    private void getDeviceLocation(){
        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        try{
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Location currentLocation= (Location) task.getResult();
                            moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),DEFAULT_ZOOM);
                        }else{
                            Toast.makeText(getApplicationContext(),"Your location could not be found",Toast.LENGTH_LONG).show();
                        }
                    }
                });
        }catch(SecurityException e){
        }
    }

    private void RegisterUser() {
        final String email,password,gender,weight,height,bmi,gymname,latitude,longitude,ImageURL;
        email= Paper.book().read("Email").toString();
        password=Paper.book().read("Password").toString();
        gender=Paper.book().read("UserGender");
        height=Paper.book().read("UserHeight");
        weight=Paper.book().read("UserWeight");
        int wei=Integer.valueOf(weight);
        int hei=Integer.valueOf(height);
        float bmi1=wei/hei;
        bmi=String.valueOf(bmi1);
        gymname=Paper.book().read("SelectedGym").toString();
        latitude=Paper.book().read("Latitude").toString();
        longitude=Paper.book().read("Longitude").toString();
        ImageURL="test";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, REGISTER_URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        String login="Yes";
                        Paper.book().write("UserEmail",email);
                        Paper.book().write("UserPassword",password);
                        Paper.book().write("UserGender",gender);
                        Paper.book().write("UserWeight",weight);
                        Paper.book().write("UserBMI",bmi);
                        Paper.book().write("UserGymName",gymname);
                        Paper.book().write("UserLatitude",latitude);
                        Paper.book().write("Userlongitude",longitude);
                        Paper.book().write("UserImageUrl",ImageURL);
                        mProgressBar.setVisibility(View.GONE);
                        finish();
                        startActivity(new Intent(SecondMapsActivity.this, HomepageNavigation.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Finish.setVisibility(View.VISIBLE);
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(KEY_EMAIL,email);
                params.put(KEY_PASS,password);
                params.put(KEY_GENDER,gender);
                params.put(KEY_WEIGHT,weight);
                params.put(KEY_BMI,bmi);
                params.put(KEY_GYMNAME,gymname);
                params.put(KEY_LATITUDE,latitude);
                params.put(KEY_LONGITUDE,longitude);
                params.put(KEY_IMAGEURL,ImageURL);
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
