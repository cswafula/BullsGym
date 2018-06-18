package com.example.charlie.bullsgym;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class SecondMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final float DEFAULT_ZOOM = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Bulls gym in Sydney"));

        //this is the default location
        LatLng Madaraka = new LatLng(-1.283, 36.817);
        mMap.addMarker(new MarkerOptions().position(Madaraka).title("Bulls Gym in Madaraka").snippet("This is the first gym to be created"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));
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
        LatLng Westy = new LatLng(-1.28333, 36.816677);
        mMap.addMarker(new MarkerOptions().position(Westy).title("Bulls Gym in Westlands").snippet("Welcome to Westy gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Makadara = new LatLng(-1.283, 36.817);
        mMap.addMarker(new MarkerOptions().position(Makadara).title("Bulls Gym in Makadara").snippet("Welcome to Makadara gym"))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bull_icon));

        LatLng Langata = new LatLng(-1.3667, 36.7333);
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

    }
    private void moveCamera(LatLng latLng,float zoom){
//moving the camera to your location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }
}
