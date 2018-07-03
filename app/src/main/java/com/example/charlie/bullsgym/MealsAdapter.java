package com.example.charlie.bullsgym;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealsViewHolder> {

    private Context mCtx;
    private List<Meals> mealsList;

    public MealsAdapter(Context mCtx, List<Meals> mealsList) {
        this.mCtx = mCtx;
        this.mealsList = mealsList;
    }

    @NonNull
    @Override
    public MealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.meals_layout,null);
        return new MealsAdapter.MealsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealsViewHolder holder, int position) {
        final Meals meals=mealsList.get(position);
        holder.imageDesc.setText(meals.getMealname());
        holder.MealImage.setImageDrawable(mCtx.getResources().getDrawable(meals.getMealImage()));

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(mCtx);
                builder.setTitle(meals.getMealname());
                builder.setMessage(meals.getDescription());
                AlertDialog alertDialog= builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    class MealsViewHolder extends RecyclerView.ViewHolder{

        ImageView MealImage;
        TextView imageDesc;
        ConstraintLayout constraintLayout;

        public MealsViewHolder(View itemView) {
            super(itemView);

            MealImage=itemView.findViewById(R.id.Meals_Image);
            imageDesc=itemView.findViewById(R.id.Meals_name_list);
            constraintLayout=itemView.findViewById(R.id.MealsConstraint);
        }
    }
}
