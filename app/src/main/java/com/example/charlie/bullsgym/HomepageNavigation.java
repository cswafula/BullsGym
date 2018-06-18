package com.example.charlie.bullsgym;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
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

    public TextView RecordWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_navigation);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecordWorkout=(TextView) findViewById(R.id.TxtRecordWorkout);


        //initialize paper for the language selection
        Paper.init(this);

        //set default language to english
        String language= Paper.book().read("language");
        if(language == null)
            Paper.book().write("language","en");



        Fragment fragment2=new HomeFragment();
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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_workout_Music_2) {
            startActivity(new Intent(HomepageNavigation.this, Music.class));
        }else if(id == R.id.action_Language){
            popupdialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void popupdialog() {

        final String[] Languages= {"English","Spanish","Chinese","French"};

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
                }
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        }

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
        }
        return loadFragment(fragment);
    }
}
