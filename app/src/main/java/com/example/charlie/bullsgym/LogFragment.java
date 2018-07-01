package com.example.charlie.bullsgym;

import android.app.AlertDialog;
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

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_log,null);

        logsDataList= new ArrayList<>();
        recyclerView= view.findViewById(R.id.LogsrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        logsDataList.add(new LogsData("Chest Workout","12 by 4 Reps (15 mins)","Thur Jul 11"));
        logsDataList.add(new LogsData("Legs Workout","24 by 8 Reps (30 mins)","Thur Jul 05"));
        logsDataList.add(new LogsData("Mixed Workout","12 by 4 Reps (15 mins)","Thur Jul 27"));
        logsDataList.add(new LogsData("Triceps and Biceps","8 by 16 Reps (20 mins)","Thur Jul 31"));

        adapter=new LogsDataAdapter(getContext(),logsDataList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
