package com.example.charlie.bullsgym;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charlie.bullsgym.helper.LocaleHelper;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.paperdb.Paper;

public class HomepageNavigation extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener {

    public TextView RecordWorkout,nav_email,nav_bmi,nav_gym;
    String Email,Password,Gender,Weight,BMI,GymName,Latitude,Longitude,ImageURL;

    boolean doubleBackToExitPressedOnce = false;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_navigation);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        RecordWorkout=(TextView) findViewById(R.id.TxtRecordWorkout);



        //initialize paper for the language selection
        Paper.init(this);
        Email=Paper.book().read("UserEmail").toString();
        Password=Paper.book().read("UserPassword").toString();
        Gender=Paper.book().read("UserGender").toString();
        Weight=Paper.book().read("UserWeight").toString();
        BMI=Paper.book().read("UserBMI").toString();
        GymName=Paper.book().read("UserGymName").toString();
        Latitude=Paper.book().read("UserLatitude").toString();
        Longitude=Paper.book().read("Userlongitude").toString();
        ImageURL=Paper.book().read("UserImageUrl").toString();




        //set default language to english
        String language= Paper.book().read("language");
        if(language == null)
            Paper.book().write("language","en");



        Fragment fragment2=new WorkoutsFragment();
        loadFragment(fragment2);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView= navigationView.getHeaderView(0);
        nav_email=headerView.findViewById(R.id.nav_header_email);
        nav_bmi=headerView.findViewById(R.id.nav_header_BMI);
        nav_gym=headerView.findViewById(R.id.nav_header_currentGym);

        nav_email.setText("Email: "+Email);
        nav_gym.setText("Current Gym: "+GymName);
        nav_bmi.setText("Current BMI: "+BMI);

    }

    //update view for the translation of language clicked
    public void updateView(String lang) {
        Context context = LocaleHelper.setLocale(this,lang);
        context.getResources();
        startActivity(new Intent(HomepageNavigation.this, HomepageNavigation.class));
        finish();
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment !=null){
            getSupportFragmentManager()
                    .beginTransaction().replace(R.id.fragment_container,fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "click back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_workout_Music_2) {
            startActivity(new Intent(HomepageNavigation.this, Music.class));
        }
//        else if(id == R.id.action_Language){
//            popupdialog();
//        }

        return super.onOptionsItemSelected(item);
    }

    private void popupdialog() {

        final String[] Languages= {"English","Swahili","Spanish","Chinese","French"};

        AlertDialog.Builder builder= new AlertDialog.Builder(HomepageNavigation.this);
        builder.setTitle("Select Language");
        builder.setItems(Languages, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), Languages[which], Toast.LENGTH_LONG).show();
                if(Languages[which] == "English"){
                    Paper.book().write("language","en");
                    updateView((String) Paper.book().read("language"));
                }else if(Languages[which] == "Spanish"){
                    Paper.book().write("language","es");
                    updateView((String) Paper.book().read("language"));
                }else if(Languages[which] == "Chinese"){
                    Paper.book().write("language","zh");
                    updateView((String) Paper.book().read("language"));
                }else if(Languages[which] == "French"){
                    Paper.book().write("language","fr");
                    updateView((String) Paper.book().read("language"));
                }else if(Languages[which] == "Swahili"){
                    Paper.book().write("language","sw");
                    updateView((String) Paper.book().read("language"));
                }
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment=null;
        switch (item.getItemId()){
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;
            case R.id.nav_workout:
                fragment = new WorkoutsFragment();
                break;
            case R.id.nav_logs:
                fragment = new LogFragment();
                break;
            case R.id.nav_logout:
                logout();
                break;
            case R.id.nav_Profile:
                startActivity(new Intent(HomepageNavigation.this, UserProfile.class));
                break;
            case R.id.nav_language:
                popupdialog();
                break;
            case R.id.nav_Music:
                startActivity(new Intent(HomepageNavigation.this, Music.class));
                break;
        }
        return loadFragment(fragment);
    }

    private void logout() {

        final android.support.v7.app.AlertDialog.Builder builder= new android.support.v7.app.AlertDialog.Builder(HomepageNavigation.this);
        builder.setMessage("Are you sure you want to Logout?");
        builder.setCancelable(true);
        builder.setNegativeButton("No, Stay!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes, Logout!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
                startActivity(new Intent(HomepageNavigation.this, Loginpage.class));
                finish();
            }
        });
        android.support.v7.app.AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }

}
