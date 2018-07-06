package com.example.charlie.bullsgym;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import io.paperdb.Paper;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float DEFAULT_ZOOM = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

                String position=marker.getPosition().toString();
                final float lat= (float) marker.getPosition().latitude;
                final float lng= (float) marker.getPosition().longitude;
                final String latitude=String.valueOf(lat);
                final String longitude=String.valueOf(lng);
                final String name= marker.getTitle();

                final AlertDialog.Builder builder= new AlertDialog.Builder(MapsActivity.this);
                builder.setMessage("Are you sure you want to Change your Location to: "+name);
                builder.setCancelable(true);
                builder.setNegativeButton("No, Stay!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes, Close!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Paper.book().write("UserGymName",name);
                        Paper.book().write("UserLatitude",latitude);
                        Paper.book().write("Userlongitude",longitude);
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

}
