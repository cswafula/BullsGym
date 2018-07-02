package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class chestRecycler extends AppCompatActivity {
    RecyclerView recyclerView;
    WorkoutsAdapter adapter;

    List<Workouts> workoutsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_recycler);

        workoutsList=new ArrayList<>();
        recyclerView=findViewById(R.id.chestrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        workoutsList.add(new Workouts(1,"Flat Bench Press",R.drawable.flatbenchpress));
        workoutsList.add(new Workouts(2,"Incline Bench Press",R.drawable.inclinebenchpress));
        workoutsList.add(new Workouts(3,"Decline Bench Press",R.drawable.declinebenchpress));
        workoutsList.add(new Workouts(4,"Flat Flys",R.drawable.flatflys));
        workoutsList.add(new Workouts(5,"Machine Chest Press",R.drawable.machinechestpress));

        adapter= new WorkoutsAdapter(this,workoutsList);
        recyclerView.setAdapter(adapter);
    }
}
