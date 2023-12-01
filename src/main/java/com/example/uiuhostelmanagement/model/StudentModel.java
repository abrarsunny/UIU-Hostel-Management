package com.example.uiuhostelmanagement.model;

public class StudentModel {
    String id;
    String name;
    String email;
    String mobile;
    String gender;
    String roomType;
    String acOrNon;
    String address;
    String gNumber;

    public StudentModel(String id, String name, String email, String mobile, String gender, String roomType, String acOrNon, String address, String gNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.roomType = roomType;
        this.acOrNon = acOrNon;
        this.address = address;
        this.gNumber = gNumber;
    }

    public StudentModel(String id, String name, String email, String mobile, String gender, String roomType, String acOrNon) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.roomType = roomType;
        this.acOrNon = acOrNon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getgNumber() {
        return gNumber;
    }

    public void setgNumber(String gNumber) {
        this.gNumber = gNumber;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getAcOrNon() {
        return acOrNon;
    }

    public void setAcOrNon(String acOrNon) {
        this.acOrNon = acOrNon;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", roomType='" + roomType + '\'' +
                ", acOrNon='" + acOrNon + '\'' +
                '}';
    }
}
