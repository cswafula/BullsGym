package com.example.charlie.bullsgym;

public class Workouts {
    private int id;
    private String workoutname;
    private int workoutImage;

    public Workouts(int id, String workoutname, int workoutImage) {
        this.id = id;
        this.workoutname = workoutname;
        this.workoutImage = workoutImage;
    }

    public int getId() {
        return id;
    }

    public String getWorkoutname() {
        return workoutname;
    }

    public int getWorkoutImage() {
        return workoutImage;
    }
}
