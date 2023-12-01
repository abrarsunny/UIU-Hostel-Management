package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.StudentModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentCard implements Initializable {

    @FXML
    private Label address;

    @FXML
    private Label email;

    @FXML
    private Label gender;

    @FXML
    private Label id;

    @FXML
    private Label mobile;

    @FXML
    private Label name;

    @FXML
    private Label number;
    private StudentModel student;

    void setStudent(StudentModel student)
    {
        this.student = student;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            name.setText(student.getName());
            id.setText(student.getId());
            email.setText(student.getEmail());
            gender.setText(student.getGender());
            mobile.setText(student.getMobile());
            number.setText(student.getgNumber());
            address.setText(student.getAddress());
        });
    }
}
