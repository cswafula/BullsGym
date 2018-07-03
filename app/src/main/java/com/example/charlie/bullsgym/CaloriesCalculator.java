package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.rengwuxian.materialedittext.MaterialEditText;

public class CaloriesCalculator extends AppCompatActivity {

    MaterialSpinner GenderSpinner,SportSpinner;
    MaterialEditText Age,Weight,Height;
    Button Calculate;
    TextView  BMR,maintain_Weight,Lose_weight;
    TableLayout tableLayout;

    float get_age,get_weight,get_height;
    String Gender="Male";
    String Sports="No Sports Activity";
    String fetchAge,fetchHeight,fetchWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_calculator);

        GenderSpinner=findViewById(R.id.CaloriesSpinner);
        SportSpinner=findViewById(R.id.CaloriesSportsSpinner);
        Age=findViewById(R.id.CaloriesAge);
        Weight=findViewById(R.id.CaloriesWeight);
        Height=findViewById(R.id.CaloriesHeight);
        Calculate=findViewById(R.id.Calculate_Calories);
        BMR=findViewById(R.id.Total_BMR);
        maintain_Weight=findViewById(R.id.maintain_weight);
        Lose_weight=findViewById(R.id.lose_weight);
        tableLayout=findViewById(R.id.tableLayout);

        GenderSpinner.setItems("Male","Female");
        SportSpinner.setItems("No Sports Activity","Light Activity(1-2 days/week)","Moderate(3-5 days/week)","Very Active(Daily)");

        GenderSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Gender=item.toString();
            }
        });
        SportSpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                Sports=item.toString();
            }
        });

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate() {
        fetchAge=Age.getText().toString().trim();
        fetchHeight=Height.getText().toString().trim();
        fetchWeight=Weight.getText().toString().trim();
        if(fetchAge.isEmpty()){
            Age.setError("Age is Required");
        }else if(fetchHeight.isEmpty()){
            Height.setError("Height is Required");
        }else if(fetchWeight.isEmpty()){
            Weight.setError("Weight is Required");
        }else{
            get_age=Float.valueOf(fetchAge);
            get_height=Float.valueOf(fetchHeight);
            get_weight=Float.valueOf(fetchWeight);
            if(get_age <= 0){
                Age.setError("Enter a valid Age");
            }else if(get_height <= 0){
                Height.setError("Enter a valid Height");
            }else if(get_weight <= 0){
                Weight.setError("Enter a valid Weight");
            }else{
                calculate();
            }
        }
    }

    private void calculate() {
        get_age=Float.valueOf(fetchAge);
        get_height=Float.valueOf(fetchHeight);
        get_weight=Float.valueOf(fetchWeight);
        float weight_pounds,height_inches,rate,bmr,totalbmr,losecal;
        weight_pounds= (float) (get_weight*2.20462);
        height_inches= (float) (get_height*39.37);
        if(Gender.equals("Male")){

            bmr= (float) ((weight_pounds*6.23)+(height_inches*12.7)-(get_age*6.8));
            totalbmr=bmr+66;
            String castbmr=String.valueOf(totalbmr);
            BMR.setText(castbmr);
            if(Sports.equals("No Sports Activity")){
                rate= (float) (totalbmr*1.2);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }else if(Sports.equals("Light Activity(1-2 days/week)")){
                rate= (float) (totalbmr*1.375);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }else if(Sports.equals("Moderate(3-5 days/week)")){
                rate= (float) (totalbmr*1.55);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }else if(Sports.equals("Very Active(Daily)")){
                rate= (float) (totalbmr*1.725);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }
            tableLayout.setVisibility(View.VISIBLE);


        }else if(Gender.equals("Female")){

            bmr= (float) ((weight_pounds*4.35)+(height_inches*4.7)-(get_age*4.7));
            totalbmr=bmr+655;
            String castbmr=String.valueOf(totalbmr);
            BMR.setText(castbmr);
            if(Sports.equals("No Sports Activity")){
                rate= (float) (totalbmr*1.2);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }else if(Sports.equals("Light Activity(1-2 days/week)")){
                rate= (float) (totalbmr*1.375);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }else if(Sports.equals("Moderate(3-5 days/week)")){
                rate= (float) (totalbmr*1.55);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }else if(Sports.equals("Very Active(Daily)")){
                rate= (float) (totalbmr*1.725);
                losecal=(rate-500);
                String castmaintain=String.valueOf(rate);
                String castlose=String.valueOf(losecal);
                Lose_weight.setText(castlose);
                maintain_Weight.setText(castmaintain);
            }
            tableLayout.setVisibility(View.VISIBLE);
        }
    }
}
