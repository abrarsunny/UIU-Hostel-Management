package com.example.uiuhostelmanagement.model;

public class HallModel {
    String hallId;
    String name;
    String gender;
    int singleAC;
    int singleNonAC;
    int sharedAC;
    int sharedNonAC;

    public String getHallId() {
        return hallId;
    }

    public void setHallId(String hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSingleAC() {
        return singleAC;
    }

    public void setSingleAC(int singleAC) {
        this.singleAC = singleAC;
    }

    public int getSingleNonAC() {
        return singleNonAC;
    }

    public void setSingleNonAC(int singleNonAC) {
        this.singleNonAC = singleNonAC;
    }

    public int getSharedAC() {
        return sharedAC;
    }

    public void setSharedAC(int sharedAC) {
        this.sharedAC = sharedAC;
    }

    public int getSharedNonAC() {
        return sharedNonAC;
    }

    public void setSharedNonAC(int sharedNonAC) {
        this.sharedNonAC = sharedNonAC;
    }

    public HallModel(String hallId, String name, String gender, int singleAC, int singleNonAC, int sharedAC, int sharedNonAC) {
        this.hallId = hallId;
        this.name = name;
        this.gender = gender;
        this.singleAC = singleAC;
        this.singleNonAC = singleNonAC;
        this.sharedAC = sharedAC;
        this.sharedNonAC = sharedNonAC;
    }
}
