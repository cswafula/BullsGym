package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArmsRecycler extends AppCompatActivity {

    RecyclerView recyclerView;
    WorkoutsAdapter adapter;

    List<Workouts> workoutsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_recycler);

        workoutsList=new ArrayList<>();
        recyclerView=findViewById(R.id.armsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        workoutsList.add(new Workouts(1,"Barbel Spider Curl",R.drawable.barbelspidercurl));
        workoutsList.add(new Workouts(2,"Decline Skull Crusher",R.drawable.declineskullcrusher));
        workoutsList.add(new Workouts(3,"Tate Press",R.drawable.tatepress));
        workoutsList.add(new Workouts(4,"Dumbell Hammer Preacher Curl",R.drawable.dumbbellhammerpreachercurl));
        workoutsList.add(new Workouts(5,"Standing Concentration Curl",R.drawable.standingdumbellconcentrationcurl));
        workoutsList.add(new Workouts(7,"Decline Tricep Extension",R.drawable.declinebumbelltricepextension));

        adapter= new WorkoutsAdapter(this,workoutsList);
        recyclerView.setAdapter(adapter);
    }
}
