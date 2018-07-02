package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AbsRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    WorkoutsAdapter adapter;

    List<Workouts> workoutsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_recycler);

        workoutsList=new ArrayList<>();
        recyclerView=findViewById(R.id.absrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        workoutsList.add(new Workouts(1,"Side Bend On Stability Ball",R.drawable.sidebendonstabilityball));
        workoutsList.add(new Workouts(2,"High Reverse Plank",R.drawable.highrevereplank));
        workoutsList.add(new Workouts(3,"Captain's Chair Raise",R.drawable.captainschairraise));
        workoutsList.add(new Workouts(4,"Machine Leg Raise Crunch",R.drawable.machinelegraisecrunch));
        workoutsList.add(new Workouts(5,"Reverse Crunch",R.drawable.reversecrunch));
        workoutsList.add(new Workouts(6,"Vertical Leg Crunch",R.drawable.verticallegcrunch));

        adapter= new WorkoutsAdapter(this,workoutsList);
        recyclerView.setAdapter(adapter);
    }
}
