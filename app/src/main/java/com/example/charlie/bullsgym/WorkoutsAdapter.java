package com.example.charlie.bullsgym;

import android.content.Context;
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

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutsViewHolder>{

    private Context mCtx;
    private List<Workouts> workoutsList;

    public WorkoutsAdapter(Context mCtx, List<Workouts> workoutsList) {
        this.mCtx = mCtx;
        this.workoutsList = workoutsList;
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.workouts_list_layout,null);
        return new WorkoutsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, int position) {
        final Workouts workouts=workoutsList.get(position);
        holder.imageDesc.setText(workouts.getWorkoutname());
        holder.workoutImage.setImageDrawable(mCtx.getResources().getDrawable(workouts.getWorkoutImage()));

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(workouts.getWorkoutname()=="Meals Plan"){
                    mCtx.startActivity(new Intent(mCtx,MealsPlan.class));
                }else if(workouts.getWorkoutname()=="Calories Calculator"){
                    mCtx.startActivity(new Intent(mCtx,CaloriesCalculator.class));
                }else if(workouts.getWorkoutname()=="BMI Calculator"){
                    Toast.makeText(mCtx,"You Are a Guru",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workoutsList.size();
    }

    class WorkoutsViewHolder extends RecyclerView.ViewHolder{

        ImageView workoutImage;
        TextView imageDesc;
        ConstraintLayout constraintLayout;

        public WorkoutsViewHolder(View itemView) {
            super(itemView);

            workoutImage=itemView.findViewById(R.id.Workout_Image);
            imageDesc=itemView.findViewById(R.id.Workout_name_list);
            constraintLayout=itemView.findViewById(R.id.workoutsConstraint);
        }
    }
}
