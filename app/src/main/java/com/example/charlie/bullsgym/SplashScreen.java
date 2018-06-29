package com.example.charlie.bullsgym;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import io.paperdb.Paper;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    ProgressBar mProgressbar;
    String Email,Password,Gender,Weight,BMI,GymName,Latitude,Longitude,ImageURL;

    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mProgressbar=findViewById(R.id.Splash_Progress);

        prefs = getSharedPreferences("com.example.charlie.bullsgym", MODE_PRIVATE);

        Paper.init(this);





    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String empty="";
                    Paper.book().write("UserEmail",empty);
                    Paper.book().write("UserPassword",empty);
                    Paper.book().write("UserGender",empty);
                    Paper.book().write("UserWeight",empty);
                    Paper.book().write("UserBMI",empty);
                    Paper.book().write("UserGymName",empty);
                    Paper.book().write("UserLatitude",empty);
                    Paper.book().write("Userlongitude",empty);
                    Paper.book().write("UserImageUrl",empty);
                    startActivity(new Intent(SplashScreen.this,Loginpage.class));
                    finish();
                }
            },SPLASH_TIME_OUT);
            prefs.edit().putBoolean("firstrun", false).apply();
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    checkSession();
                }
            },SPLASH_TIME_OUT);
        }
    }

    private void checkSession() {
        Email=Paper.book().read("UserEmail").toString();
        Password=Paper.book().read("UserPassword").toString();
        Gender=Paper.book().read("UserGender").toString();
        Weight=Paper.book().read("UserWeight").toString();
        BMI=Paper.book().read("UserBMI").toString();
        GymName=Paper.book().read("UserGymName").toString();
        Latitude=Paper.book().read("UserLatitude").toString();
        Longitude=Paper.book().read("Userlongitude").toString();
        ImageURL=Paper.book().read("UserImageUrl").toString();
        if(Email.isEmpty() || Password.isEmpty() || Gender.isEmpty() || Weight.isEmpty()
                || BMI.isEmpty() || GymName.isEmpty() || Latitude.isEmpty() || Longitude.isEmpty()
                ||ImageURL.isEmpty()){
            startActivity(new Intent(SplashScreen.this,Loginpage.class));
            finish();
        }else{
            startActivity(new Intent(SplashScreen.this,HomepageNavigation.class));
            finish();
        }
    }
}
