package com.example.charlie.bullsgym;

import android.Manifest;
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
import android.widget.Toast;

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

import io.paperdb.Paper;

public class SecondMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float DEFAULT_ZOOM = 10f;
    private static final int REQUEST_LOCATION_PERMISSION=200;
    private FusedLocationProviderClient mFusedLocationProviderClient;

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
            @Override
            public boolean onMarkerClick(Marker marker) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(SecondMapsActivity.this);
                final String name=marker.getTitle().toString();
                String position=marker.getPosition().toString();
                final float lat= (float) marker.getPosition().latitude;
                final float lng= (float) marker.getPosition().longitude;
                builder.setMessage("Are you sure you want: "+ name+" position(Lat/Lng): "+ lat+","+lng);
                builder.setCancelable(true);
                builder.setNegativeButton("No, Cancel!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes, Close!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(SecondMapsActivity.this, Register2.class));
                        String Latitude= String.valueOf(lat);
                        String Longitude= String.valueOf(lng);
                        Paper.book().write("SelectedGym",name);
                        Paper.book().write("Latitude",Latitude);
                        Paper.book().write("Longitude",Longitude);
                        finish();
                    }
                });
                AlertDialog alertDialog= builder.create();
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
}
