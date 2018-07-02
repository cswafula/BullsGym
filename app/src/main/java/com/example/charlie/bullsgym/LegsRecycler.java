package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LegsRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    WorkoutsAdapter adapter;

    List<Workouts> workoutsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs_recycler);

        workoutsList=new ArrayList<>();
        recyclerView=findViewById(R.id.legsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        workoutsList.add(new Workouts(1,"Dumbel Pistol Squat",R.drawable.dumbelpistolsquat));
        workoutsList.add(new Workouts(2,"Dumbel Sumo Squat",R.drawable.dumbelsumosquat));
        workoutsList.add(new Workouts(3,"Jefferson Squat",R.drawable.jeffersonsquat));
        workoutsList.add(new Workouts(4,"Leg Extension Crunch",R.drawable.legeextensioncrunch));
        workoutsList.add(new Workouts(5,"Weighted Sissy Squat",R.drawable.weightedsissysquat));

        adapter= new WorkoutsAdapter(this,workoutsList);
        recyclerView.setAdapter(adapter);
    }
}
