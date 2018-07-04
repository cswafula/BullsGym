package com.example.charlie.bullsgym;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;

public class BMICalculator extends AppCompatActivity {

    MaterialEditText Weight,Height;
    float get_weight,get_height;
    String fetchWeight,fetchHeight;
    Button Calculate;
    TextView TotalBMI,Category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmicalculator);

        Weight=findViewById(R.id.BMIWeight);
        Height=findViewById(R.id.BMIHeight);
        Calculate=findViewById(R.id.Calculate_BMI);
        TotalBMI=findViewById(R.id.Total_BMI);
        Category=findViewById(R.id.BMICategory);

        Calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate() {
        fetchWeight=Weight.getText().toString().trim();
        fetchHeight=Height.getText().toString().trim();
        if(fetchWeight.isEmpty()){
            Weight.setError("Weight is Required");
        }else if(fetchHeight.isEmpty()){
            Height.setError("Height is Required");
        }else{
            get_height=Float.valueOf(fetchHeight);
            get_weight=Float.valueOf(fetchWeight);
            if(get_height<=0){
                Height.setError("Enter a Valid Height");
            }else if(get_weight<=0){
                Weight.setError("Enter a Valid Weight");
            }else{
                calculate(get_height,get_weight);
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculate(float get_height, float get_weight) {
        float castheight,castweight,bmi;
        castheight=get_height;
        castweight=get_weight;
        bmi=(castweight/(castheight*castheight));
        String category,BMI;
        BMI=String.valueOf(bmi);
        if(bmi<18.5){
            category="UnderWeight";
            Category.setText("You are :" +category);
        }else if(bmi>=18.5 && bmi<=24.9){
            category="Healthy";
            Category.setText("You are :" +category);
        }else if(bmi>=25 && bmi<=29.9){
            category="OverWeight";
            Category.setText("You are :" +category);
        }else if(bmi>=30){
            category="Obese";
            Category.setText("You are :" +category);
        }
        TotalBMI.setText("Your BMI is: "+BMI);
        TotalBMI.setVisibility(View.VISIBLE);
        Category.setVisibility(View.VISIBLE);
    }
}
