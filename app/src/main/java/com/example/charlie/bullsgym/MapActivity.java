package com.example.charlie.bullsgym;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final float DEFAULT_ZOOM = 15f;
    private static final int PLACE_PICKER_REQUEST = 1;

    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

//        getLocationPermission();

        initplaces();


    }

    private void addMarkers() {
        Marker marker=mMap.addMarker(new MarkerOptions().position(new LatLng(51.5072, -0.1275)));
        marker.setVisible(true);
        marker.setTitle("Bulls Gym");
        marker.setSnippet("This is just the first snippet");

    }

    private void initplaces() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try {
            startActivityForResult(builder.build(MapActivity.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            Log.e(TAG, "initplaces: GooglePlayServicesRepairableException" + e.getMessage() );
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.e(TAG, "initplaces: GooglePlayServicesNotAvailableException" + e.getMessage() );
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);

                String toastMsg = String.format("Place: %s", place.getName());
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void initMap(){
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(getApplicationContext(),"Map is Ready",Toast.LENGTH_LONG).show();
                mMap=googleMap;
                if(mLocationPermissionGranted){
                    getDeviceLocation();
                }
            }
        });
    }

    private void getDeviceLocation(){
        mFusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        try{
            if(mLocationPermissionGranted){
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                   if(task.isSuccessful()){
                       Log.d(TAG, "onComplete: Found Location");
                       Location currentLocation= (Location) task.getResult();
                       moveCamera(new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude()),DEFAULT_ZOOM);
                   }else{
                       Log.d(TAG, "onComplete: Current Location is null");
                       Toast.makeText(getApplicationContext(),"Your location could not be found",Toast.LENGTH_LONG).show();
                   }
                    }
                });
            }
        }catch(SecurityException e){
        }
    }

    private void moveCamera(LatLng latLng,float zoom){
//moving the camera to your location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void getLocationPermission(){
        String[] permissions ={Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted=true;
                initMap();
            }else{
                ActivityCompat.requestPermissions(this, permissions,
                        1234);
            }

        }else{
            ActivityCompat.requestPermissions(this,permissions,1234);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        switch (requestCode){
            case  1234:{
                if(grantResults.length > 0){
                    for(int i=0; i<grantResults.length;i++){
                        if(grantResults[i] ==PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted=false;
                            return;
                        }
                    }
                    mLocationPermissionGranted=true;
                    //initialize the map
                    initMap();
                }
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
