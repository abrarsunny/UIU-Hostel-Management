package com.example.uiuhostelmanagement.model;

public class AppointmentModel {
    private String studentID;
    private String studentName;
    private String gName;
    private String gRelation;
    private String hallName;
    private String roomID;
    private String message;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgRelation() {
        return gRelation;
    }

    public void setgRelation(String gRelation) {
        this.gRelation = gRelation;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppointmentModel(String studentID, String studentName, String gName, String gRelation, String hallName, String roomID, String message) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.gName = gName;
        this.gRelation = gRelation;
        this.hallName = hallName;
        this.roomID = roomID;
        this.message = message;
    }
}
