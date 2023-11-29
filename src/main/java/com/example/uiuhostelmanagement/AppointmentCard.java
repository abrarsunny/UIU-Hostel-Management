package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.AppointmentModel;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentCard implements Initializable {

    @FXML
    private Label gName;

    @FXML
    private Label gRelation;

    @FXML
    private Label hallName;

    @FXML
    private Label id;

    @FXML
    private Label sName;

    private AppointmentModel appointment;


    public void setAppointment(AppointmentModel appointment) {
        this.appointment = appointment;
    }

    @FXML
    void approve(ActionEvent event) {

    }

    @FXML
    void cancel(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            sName.setText(appointment.getStudentName());
            id.setText(appointment.getStudentID());
            gName.setText(String.valueOf(appointment.getgName()));
            gRelation.setText(String.valueOf(appointment.getgRelation()));
            hallName.setText(String.valueOf(appointment.getHallName()+"("+appointment.getRoomID()+")"));
        });
    }
}
