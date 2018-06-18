package com.example.charlie.bullsgym;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.paperdb.Paper;

public class LogFragment extends Fragment {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.fragment_log,null);

        compactCalendar=(CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        final TextView dateshower= view.findViewById(R.id.TxtLog_Date);

        //if a date in the log calender is clicked function
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
//                Toast.makeText(view.getContext(), dateClicked.toString(), Toast.LENGTH_SHORT).show();
                String date_Clicked=dateClicked.toString();

                final String[] Languages= {"No workouts were done on this day", date_Clicked};
                AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext());
                builder.setTitle("Workouts Log");
                builder.setItems(Languages, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(), Languages[which], Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alertDialog= builder.create();
                alertDialog.show();

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                dateshower.setVisibility(View.VISIBLE);
                dateshower.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

        return view;
    }
}
