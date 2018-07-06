package com.example.charlie.bullsgym;

public class LogsData {
    private String workoutname, reps,date,description;

    public LogsData(String workoutname, String reps, String date, String description) {
        this.workoutname = workoutname;
        this.reps = reps;
        this.date = date;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
}
