package com.example.charlie.bullsgym;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LogsDataAdapter extends RecyclerView.Adapter<LogsDataAdapter.LogsDataViewHolder>{

    private Context mCtx;
    private List<LogsData> LogsList;

    public LogsDataAdapter(Context mCtx, List<LogsData> logsList) {
        this.mCtx = mCtx;
        LogsList = logsList;
    }

    @NonNull
    @Override
    public LogsDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(mCtx);
        View view= inflater.inflate(R.layout.list_layout,null);
        return new LogsDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogsDataViewHolder holder, int position) {
        LogsData logsData= LogsList.get(position);
        holder.textViewWorkout.setText(logsData.getWorkoutname());
        holder.textViewReps.setText(logsData.getReps());
        holder.textViewDate.setText(logsData.getDate());
        holder.textViewDesc.setText(logsData.getDescription());
    }

    @Override
    public int getItemCount() {
        return LogsList.size();
    }

    class LogsDataViewHolder extends RecyclerView.ViewHolder{

        TextView textViewWorkout,textViewReps,textViewDate,textViewDesc;

        public LogsDataViewHolder(View itemView) {
            super(itemView);

            textViewWorkout=itemView.findViewById(R.id.textViewWorkoutDone);
            textViewReps=itemView.findViewById(R.id.textViewShortDesc);
            textViewDate=itemView.findViewById(R.id.textViewDate);
            textViewDesc=itemView.findViewById(R.id.textViewDescription);

        }
    }
}
