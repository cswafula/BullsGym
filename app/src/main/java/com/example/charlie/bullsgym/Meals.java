package com.example.charlie.bullsgym;

public class Meals {
    private int id;
    private String Mealname;
    private int MealImage;
    private String Description;

    public Meals(int id, String mealname, int mealImage, String description) {
        this.id = id;
        Mealname = mealname;
        MealImage = mealImage;
        Description=description;
    }

    public int getId() {
        return id;
    }

    public String getMealname() {
        return Mealname;
    }

    public int getMealImage() {
        return MealImage;
    }

    public String getDescription() {
        return Description;
    }
}
