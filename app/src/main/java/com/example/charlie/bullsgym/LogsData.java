package com.example.charlie.bullsgym;

public class LogsData {
    private String workoutname, reps,date;

    public LogsData(String workoutname, String reps, String date) {
        this.workoutname = workoutname;
        this.reps = reps;
        this.date = date;
    }

    public String getWorkoutname() {
        return workoutname;
    }

    public String getReps() {
        return reps;
    }

    public String getDate() {
        return date;
    }
}
