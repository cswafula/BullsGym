package com.example.charlie.bullsgym;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class WorkoutsFragment extends Fragment {

    Button Chest,Legs,Arms,Abs;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_workouts,null);

        Chest= view.findViewById(R.id.button_chest);
        Legs=view.findViewById(R.id.button_legs);
        Arms=view.findViewById(R.id.button_arms);
        Abs=view.findViewById(R.id.Absbutton);
        fab=view.findViewById(R.id.fabAddWorkouts);

        Chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),chestRecycler.class));
            }
        });

        Legs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),LegsRecycler.class));
            }
        });

        Arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),ArmsRecycler.class));
            }
        });

        Abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AbsRecycler.class));
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),AddWorkout.class));
            }
        });
        return  view;
    }


    private void RecordWorkoutDialog() {
    }
}
