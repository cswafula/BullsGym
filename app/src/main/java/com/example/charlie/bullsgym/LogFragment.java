package com.example.charlie.bullsgym;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class LogFragment extends Fragment {

    RecyclerView recyclerView;
    LogsDataAdapter adapter;
    List<LogsData> logsDataList;

    private static String FETCH_URL;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_log,null);

        Paper.init(view.getContext());
        String Email=Paper.book().read("UserEmail").toString();
        FETCH_URL="https://bulls-gym-api.herokuapp.com/api/UserWorkouts/"+Email;



        logsDataList= new ArrayList<>();
        recyclerView= view.findViewById(R.id.LogsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        final ProgressDialog progressDialog=new ProgressDialog(view.getContext());
        progressDialog.setMessage("Loading Workout History....");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, FETCH_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("Workouts");

                            for(int i=0 ; i<jsonArray.length();i++){
                                JSONObject object=jsonArray.getJSONObject(i);
                                logsDataList.add(new LogsData(object.getString("WorkoutName"),object.getString("Reps")
                                        ,object.getString("Date"),object.getString("Description")));
                            }

                            adapter= new LogsDataAdapter(view.getContext(),logsDataList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            Toast.makeText(view.getContext(), e.getMessage(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(view.getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(view.getContext());
        requestQueue.add(stringRequest);

        return view;
    }
}
