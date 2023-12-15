package com.example.uiuhostelmanagement.model;

import java.sql.Date;

public class Meal {
    String mealID;
    String studentID;
    String gender;
    int breakfast;
    int lunch;
    int dinner;

    public String getMealID() {
        return mealID;
    }

    public void setMealID(String mealID) {
        this.mealID = mealID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getLunch() {
        return lunch;
    }

    public void setLunch(int lunch) {
        this.lunch = lunch;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    Date date;

    public Meal(String mealID, String studentID, String gender, int breakfast, int lunch, int dinner, Date date) {
        this.mealID = mealID;
        this.studentID = studentID;
        this.gender = gender;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.date = date;
    }
}
