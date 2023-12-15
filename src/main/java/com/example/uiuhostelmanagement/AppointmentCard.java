package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.AppointmentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.SendEmail;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    void approve(ActionEvent event) throws Exception {
        String sql = "SELECT email FROM students WHERE id = (SELECT studentID FROM appointments WHERE appointmentID = '"+appointment.getAppointmentID()+"')";
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ResultSet resultSet = databaseConnection.queryData(sql);
        if(resultSet.next())
        {
            SendEmail.sendMail(resultSet.getString(1),"New Appointment", appointment.getgName() + " want to meet you." + "\nHere is some information:\nRelation: "+appointment.getgRelation()+"\nMessage: "+ appointment.getMessage());
            Connection connection =  databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM appointments WHERE appointmentID = ?");
            preparedStatement.setString(1,appointment.getAppointmentID());
            int result = preparedStatement.executeUpdate();
            if(result == 1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointment Approved", ButtonType.OK);
                alert.show();
            }

        }
    }

    @FXML
    void cancel(ActionEvent event) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM appointments WHERE appointmentID = ?");
        preparedStatement.setString(1,appointment.getAppointmentID());
        int result = preparedStatement.executeUpdate();
        if(result == 1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointment Canceled", ButtonType.OK);
            alert.show();
        }
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
