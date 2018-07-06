package com.example.charlie.bullsgym;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import io.paperdb.Paper;

public class AddWorkout extends AppCompatActivity {

    public static String ADD_URL="https://bulls-gym-api.herokuapp.com/api/InsertWorkout";
    public static final String KEY_EMAIL="Email";
    public static final String KEY_WORKOUTNAME="WorkoutName";
    public static final String KEY_Reps="Reps";
    public static final String KEY_DESCRIPTION="Description";
    public static final String KEY_DATE="Date";


    MaterialEditText Description;
    String WorkoutName,Reps,Email,Date;
    Button AddWorkout;
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);


        Date = DateFormat.getDateTimeInstance().format(new Date());



        MaterialSpinner spinner= findViewById(R.id.spinner);
        MaterialSpinner spinner2= findViewById(R.id.spinner2);
        Description=findViewById(R.id.WorkoutDescription);
        AddWorkout=findViewById(R.id.BtnCreateWorkout);
        mProgressBar=findViewById(R.id.WorkoutProgress);

        Paper.init(this);
        Email=Paper.book().read("UserEmail").toString();


        WorkoutName="Chest Muscle";
        spinner.setItems("Chest Muscle","Legs Muscle","Arms Muscle","Abs");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                WorkoutName=item.toString();
                Snackbar.make(view,"Chose: "+item.toString(),Snackbar.LENGTH_LONG).show();
            }
        });

        Reps="12 by 4 reps (15 mins)";
        spinner2.setItems("12 by 4 reps (15 mins)","24 by 8 reps (30 mins)","4 by 8 reps (10 mins)","8 by 16 reps (20 mins)");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Reps=item.toString();
                Snackbar.make(view,"Chose: "+item.toString(),Snackbar.LENGTH_LONG).show();
            }
        });


        AddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });


    }

    private void validate() {
        String fetch_desc;
        fetch_desc=Description.getText().toString();
       if(fetch_desc.isEmpty()){
            Description.setError("Description is required");
        }else{
           mProgressBar.setVisibility(View.VISIBLE);
           AddWorkout.setVisibility(View.GONE);
            addWorkout();
        }
    }

    private void addWorkout() {
        final String fetch_desc;
        fetch_desc=Description.getText().toString();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, ADD_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mProgressBar.setVisibility(View.GONE);
                        AddWorkout.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressBar.setVisibility(View.GONE);
                AddWorkout.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put(KEY_EMAIL,Email);
                params.put(KEY_WORKOUTNAME,WorkoutName);
                params.put(KEY_DATE,Date);
                params.put(KEY_DESCRIPTION,fetch_desc);
                params.put(KEY_Reps,Reps);
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
