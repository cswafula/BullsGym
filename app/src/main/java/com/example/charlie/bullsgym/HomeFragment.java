package com.example.charlie.bullsgym;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {
//    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());
//    TextView RecordWorkout, ProgressPhotos, PersonalTrainers, dateshower;
//

    RecyclerView recyclerView;
    WorkoutsAdapter adapter;

    List<Workouts> workoutsList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_home,null);

        workoutsList=new ArrayList<>();
        recyclerView=view.findViewById(R.id.UtilityrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        workoutsList.add(new Workouts(1,"Meals Plan",R.drawable.mealsplan));
        workoutsList.add(new Workouts(2,"Calories Calculator",R.drawable.calories));
        workoutsList.add(new Workouts(3,"BMI Calculator",R.drawable.bmi));

        adapter= new WorkoutsAdapter(getContext(),workoutsList);
        recyclerView.setAdapter(adapter);
//        RecordWorkout= view.findViewById(R.id.TxtRecordWorkout);
//        ProgressPhotos=view.findViewById(R.id.TxtProgressPhotos);
//        PersonalTrainers=view.findViewById(R.id.TxtPersonalTrainers);
//        LayoutRecordWorkout=view.findViewById(R.id.LayoutRecordWorkout);
//        LayoutProgressPhotos=view.findViewById(R.id.LayoutProgressPhotos);
//        LayoutPersonalTrainers=view.findViewById(R.id.LayoutPersonalTrainers);

//        RecordWorkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getContext(),AddWorkout.class));
//            }
//        });
//        LayoutRecordWorkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        ProgressPhotos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OpenGallery();
//            }
//        });
//        LayoutProgressPhotos.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                OpenGallery();
//            }
//        });

        return view;
    }

//    private void OpenGallery() {
//        startActivity(new Intent(getContext(),Camera.class));
//    }

//    private void RecordWorkoutDialog() {
//        AlertDialog.Builder mBuilder=new AlertDialog.Builder(getContext());
//        View mView = getLayoutInflater().inflate(R.layout.dialog_record_workout,null);
//        Button BtnCreateWorkout= mView.findViewById(R.id.BtnCreateWorkout);
//        EditText TxtCreateWorkout= mView.findViewById(R.id.TxtEnterWorkout);
//        dateshower= mView.findViewById(R.id.createWorkoutMonth);
//        CompactCalendarView compactCalendarView=mView.findViewById(R.id.Workout_compactcalendar_view);
//        mBuilder.setView(mView);
//        AlertDialog dialog= mBuilder.create();
//        dialog.show();
//
//
//
//        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
//
//            @Override
//            public void onDayClick(Date dateClicked) {
//                String date_Clicked=dateClicked.toString();
//                        Toast.makeText(getContext(), date_Clicked, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onMonthScroll(Date firstDayOfNewMonth) {
//                dateshower.setVisibility(View.VISIBLE);
//                dateshower.setText(dateFormatMonth.format(firstDayOfNewMonth));
//            }
//        });
//
//    }

}
