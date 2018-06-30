package com.example.charlie.bullsgym;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddWorkout extends AppCompatActivity {

    TextView dateshower;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM-yyyy", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        MaterialSpinner spinner= findViewById(R.id.spinner);
        MaterialSpinner spinner2= findViewById(R.id.spinner2);

        spinner.setItems("Chest workout","Legs workout","Arms workout","Mixed workout","Shoulders",
                "Abs and Back","Triceps and Biceps");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view,"Chose: "+item.toString(),Snackbar.LENGTH_LONG).show();
            }
        });

        spinner2.setItems("12 by 4 reps (15 mins)","24 by 8 reps (30 mins)","4 by 8 reps (10 mins)","8 by 16 reps (20 mins)");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Snackbar.make(view,"Chose: "+item.toString(),Snackbar.LENGTH_LONG).show();
            }
        });

        dateshower= findViewById(R.id.addWorkoutMonth);
        CompactCalendarView compactCalendarView = findViewById(R.id.addWorkout_compactcalendar);
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {

            @Override
            public void onDayClick(Date dateClicked) {
                String date_Clicked=dateClicked.toString();
                Toast.makeText(getApplicationContext(), date_Clicked, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                dateshower.setVisibility(View.VISIBLE);
                dateshower.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });
    }
}
