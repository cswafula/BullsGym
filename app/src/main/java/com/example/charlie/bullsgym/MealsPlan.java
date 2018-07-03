package com.example.charlie.bullsgym;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MealsPlan extends AppCompatActivity {

    RecyclerView recyclerView;
    MealsAdapter adapter;

    List<Meals> mealsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals_plan);
        recyclerView=findViewById(R.id.MealsPlanrecyclerView);

        mealsList=new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        mealsList.add(new Meals(1,"Low Fat Diet",
                R.drawable.lowfatdiet,
                "An ultra low-fat (very low-fat) diet provides less than 10%"+
                        " of calories from fat. It limits the intake of most animal foods and even "+
                        "eliminates healthy higher-fat plant foods like nuts and avocados."));
        mealsList.add(new Meals(1,"Stay Hydrated Always",
                R.drawable.stayhydrated,
                "G Drink water and calorie-free beverages to keep your performance in the gym at its peak."+
                        " Avoid sugar-laden drinks that will fatten your waistline and sabotage your body's antioxidant"+
                        " defense systems."));
        mealsList.add(new Meals(1,"Limit Processed Foods",
                R.drawable.processedfoods,
                "Whether it comes in a box, a carton, or a bag, if it's got a label or brand name,"+
                        " it's likely highly processed and not worth eating. Remove these high-calorie, nutrient-poor"+
                        " foods from your life and you'll be much more likely to stick to your New Year's resolutions."));
        mealsList.add(new Meals(1,"Protein Rich Diet",
                R.drawable.proteinrichdiet,
                "Give your body a protein infusion every couple of hours to"+
                        " maximize muscle growth while stimulating the release of fat-burning hormones."+
                        " The best sources include lean beef, chicken, fish, lower-fat dairy foods, and soy."));

        adapter= new MealsAdapter(this,mealsList);
        recyclerView.setAdapter(adapter);

    }
}
