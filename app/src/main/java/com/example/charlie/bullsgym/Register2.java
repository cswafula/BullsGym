package com.example.charlie.bullsgym;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import io.paperdb.Paper;

public class Register2 extends AppCompatActivity {

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

    private static final String TAG = "Register2";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID= 0;

    private Button SelectGym;
    MaterialEditText Weight,Height;
    String Gender="Female";
    ProgressBar mProgressBar;
    MaterialSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        SelectGym= findViewById(R.id.Select_Gym);
//        CreateAccount=findViewById(R.id.CreateAccount);
        Weight=findViewById(R.id.RegisterWeight);
        Height=findViewById(R.id.RegisterHeight);
        spinner=findViewById(R.id.RegisterSpinner);

        spinner.setItems("Female","Male");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Gender=item.toString();
            }
        });


        Paper.init(this);


        SelectGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });
//        CreateAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Validation();
//            }
//        });
    }

    private void Validation() {
        String Reg_Weight,Reg_Height;
        Reg_Weight=Weight.getText().toString().trim();
        Reg_Height=Height.getText().toString().trim();
        if(Reg_Weight.isEmpty()){
            Weight.setError("Weight is Required");
        }else if(Reg_Height.isEmpty()){
            Height.setError("Height is Required");
        }else{
            Paper.book().write("UserGender",Gender);
            Paper.book().write("UserWeight",Reg_Weight);
            Paper.book().write("UserHeight",Reg_Height);
            startActivity(new Intent(Register2.this,SecondMapsActivity.class));
        }
    }

//    private void RegisterUser() {
//        final String email,password,gender,weight,height,bmi,gymname,latitude,longitude,ImageURL;
//        email= Paper.book().read("Email").toString();
//        password=Paper.book().read("Password").toString();
//        gender=Gender;
//        height=Height.getText().toString().trim();
//        weight=Weight.getText().toString().trim();
//        int wei=Integer.valueOf(weight);
//        int hei=Integer.valueOf(height);
//        float bmi1=wei/hei;
//        bmi=String.valueOf(bmi1);
//        gymname=getIntent().getStringExtra("SelectedGym");
//        latitude=Paper.book().read("Latitude").toString();
//        longitude=Paper.book().read("Longitude").toString();
//        ImageURL="test";
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, REGISTER_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
//
//                        Paper.book().write("UserEmail",email);
//                        Paper.book().write("UserPassword",password);
//                        Paper.book().write("UserGender",gender);
//                        Paper.book().write("UserWeight",weight);
//                        Paper.book().write("UserBMI",bmi);
//                        Paper.book().write("UserGymName",gymname);
//                        Paper.book().write("UserLatitude",latitude);
//                        Paper.book().write("Userlongitude",longitude);
//                        Paper.book().write("UserImageUrl",ImageURL);
//                        notification();
//                        mProgressBar.setVisibility(View.GONE);
//                        finish();
//                        startActivity(new Intent(Register2.this, HomepageNavigation.class));
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=new HashMap<>();
//                params.put(KEY_EMAIL,email);
//                params.put(KEY_PASS,password);
//                params.put(KEY_GENDER,gender);
//                params.put(KEY_WEIGHT,weight);
//                params.put(KEY_BMI,bmi);
//                params.put(KEY_GYMNAME,gymname);
//                params.put(KEY_LATITUDE,latitude);
//                params.put(KEY_LONGITUDE,longitude);
//                params.put(KEY_IMAGEURL,ImageURL);
//                return params;
//            }
//        };
//
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                5000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }

    private void notification() {
        mNotificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("Bulls Gym")
                .setContentText("Welcome to Bull's Gym, let's Bull this up!")
                .setSmallIcon(R.drawable.bull_icon);

        Notification mynotification = notifyBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID,mynotification);
    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder= new AlertDialog.Builder(Register2.this);
        builder.setMessage("Are you sure you want to go back, you're almost done");
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
                startActivity(new Intent(Register2.this, Loginpage.class));
                finish();
            }
        });
        AlertDialog alertDialog= builder.create();
        alertDialog.show();
    }

}
