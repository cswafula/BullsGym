package com.example.charlie.bullsgym;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;

import io.paperdb.Paper;

public class Loginpage extends AppCompatActivity {

    private static final int REQUEST_CAMERA_PERMISSION=200;
    public static String LOGIN_URL;
    CallbackManager callbackManager;
    ProgressDialog mDialog;
    MaterialEditText User_Login_Email,User_Login_Password;
    String Email,Password;
    ProgressBar mProgressBar;
    Button Login;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        Login=(Button) findViewById(R.id.login_button);
        LoginButton BtnFacebook=findViewById(R.id.login_facebook);
        User_Login_Email=findViewById(R.id.LoginEmail);
        User_Login_Password=findViewById(R.id.LoginPassword);
        mProgressBar=findViewById(R.id.Login_ProgressBar);

        checkpermissions();
        Paper.init(this);

        callbackManager= CallbackManager.Factory.create();
        BtnFacebook.setReadPermissions(Collections.singletonList("email"));
        BtnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mDialog= new ProgressDialog(Loginpage.this);
                mDialog.setMessage("Retrieving User Data...");
                mDialog.show();

                String accesstoken = loginResult.getAccessToken().getToken();
                GraphRequest request= GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        getData(object);
                    }
                });
                //Request the graph api
                Bundle parameters= new Bundle();
                parameters.putString("field","email");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //If already logged in before
        if(AccessToken.getCurrentAccessToken() != null){
            //just set the user details
            User_Login_Email.setText(AccessToken.getCurrentAccessToken().getUserId());
        }

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
            }
        });

    }

    private void Validation() {
        Email=User_Login_Email.getText().toString().trim();
        Password=User_Login_Password.getText().toString().trim();
        if(Email.isEmpty()){
            User_Login_Email.setError("Email is Required");
        }else if(Password.isEmpty()){
            User_Login_Password.setError("Password is Required");
        }else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            User_Login_Email.setError("Please Enter a valid EmailAddress");
        }
        else {
            LOGIN_URL="https://bulls-gym-api.herokuapp.com/api/Login/"+Email+"/"+Password;
            mProgressBar.setVisibility(View.VISIBLE);
            Login.setVisibility(View.GONE);
            login();
        }
    }

    private void getData(JSONObject object) {
        try {
            User_Login_Email.setText(object.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void printKeyHash() {
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo("com.example.charlie.bullsgym", PackageManager.GET_SIGNATURES);
//            for(Signature signature:info.signatures){
//                MessageDigest md=MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
//            }
//        }catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }

    private void login() {
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,
                LOGIN_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                mProgressBar.setVisibility(View.GONE);
                Login.setVisibility(View.VISIBLE);
                try {
                    JSONArray jsonArray= jsonObject.getJSONArray("User");
                    String Email=jsonArray.get(0).toString();
                    String Password=jsonArray.get(1).toString();
                    String Gender=jsonArray.get(2).toString();
                    String Weight=jsonArray.get(3).toString();
                    String BMI=jsonArray.get(4).toString();
                    String GymName=jsonArray.get(5).toString();
                    String Latitude=jsonArray.get(6).toString();
                    String Longitude=jsonArray.get(7).toString();
                    String ImageURL=jsonArray.get(8).toString();
                    String UserID=jsonArray.get(9).toString();
                    Paper.book().write("UserEmail",Email);
                    Paper.book().write("UserPassword",Password);
                    Paper.book().write("UserGender",Gender);
                    Paper.book().write("UserWeight",Weight);
                    Paper.book().write("UserBMI",BMI);
                    Paper.book().write("UserGymName",GymName);
                    Paper.book().write("UserLatitude",Latitude);
                    Paper.book().write("Userlongitude",Longitude);
                    Paper.book().write("UserImageUrl",ImageURL);
                    Toast.makeText(getApplicationContext(),"Login Successful!",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Loginpage.this,HomepageNavigation.class));
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressBar.setVisibility(View.GONE);
                Login.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


    public void CreateAccount(View view){
        startActivity(new Intent(Loginpage.this,Register.class));
        finish();
    }

    private void checkpermissions() {
        //check real-time permissions if run higher API 23
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            },REQUEST_CAMERA_PERMISSION);
            return;
        }

    }
}
