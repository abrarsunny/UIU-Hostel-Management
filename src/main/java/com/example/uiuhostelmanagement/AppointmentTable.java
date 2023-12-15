package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.AppointmentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.DatabaseReadCall;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AppointmentTable implements Initializable {

    @FXML
    private Button billSetupButton;

    @FXML
    private VBox contrainer;

    @FXML
    private Button dailyMealBillsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button newNoticeButton;

    @FXML
    private Button recieveBillButton;

    @FXML
    private Button registerStudentButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TextField searchBox;

    @FXML
    private Button settingsButton;

    @FXML
    void billSetupButtonAction(ActionEvent event) {

    }

    @FXML
    void complains(ActionEvent event) {

    }

    @FXML
    void dailyMealBillsButtonAction(ActionEvent event) {

    }

    @FXML
    void dashboad(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/adminDashboard.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void halls(ActionEvent event) {

    }

    @FXML
    void logoutButtonAction(ActionEvent event) {

    }

    @FXML
    void newNoticeButtonAction(ActionEvent event) {

    }

    @FXML
    void notices(ActionEvent event) {

    }

    @FXML
    void recieveBillButtonAction(ActionEvent event) {

    }

    @FXML
    void registerStudentButtonAction(ActionEvent event) {

    }

    @FXML
    void rooms(ActionEvent event) {

    }

    @FXML
    void search(KeyEvent event) {
        contrainer.getChildren().clear();
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(contrainer);
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        ArrayList<AppointmentModel> appointments = new ArrayList<>();
        String searchSQL = "SELECT a.appointmentID, a.studentID as studentID, a.studentName as studentName, a.gName,a.gRelation, h.hallName AS hallName,  r.roomID, a.message FROM appointments a JOIN rooms r ON a.studentID = r.bookedBy JOIN halls h ON r.hall_id = h.id WHERE studentID LIKE ? OR studentName LIKE ? OR hallName LIKE ?";
        HashMap<Integer,Object> searchHash = new HashMap<>();
        searchHash.put(1,"%"+((TextField)event.getSource()).getText()+"%");
        searchHash.put(2,"%"+((TextField)event.getSource()).getText()+"%");
        searchHash.put(3,"%"+((TextField)event.getSource()).getText()+"%");
        DatabaseReadCall databaseReadCall = new DatabaseReadCall(searchSQL,searchHash);
        databaseReadCall.setOnSucceeded(workerStateEvent -> {
            try {
                ResultSet resultSet = databaseReadCall.getValue();

                while (resultSet.next()) {
                    appointments.add(new AppointmentModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8)));
                }
                if (appointments.size() > 0) {

                    for (AppointmentModel appointment:appointments) {
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/appointmentCard.fxml"));
                            Parent root = loader.load();
                            AppointmentCard controller = loader.getController();
                            controller.setAppointment(appointment);
                            contrainer.getChildren().add(root);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                } else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/noResult.fxml"));
                    Parent root = loader.load();
                    contrainer.getChildren().add(root);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });
        Thread thread =new Thread(databaseReadCall);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    void settingsButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            if(searchBox.getText().isEmpty()) {
                for (AppointmentModel appointment:getAppointments()) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/appointmentCard.fxml"));
                        Parent root = loader.load();
                        AppointmentCard controller = loader.getController();
                        controller.setAppointment(appointment);
                        contrainer.getChildren().add(root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private ArrayList<AppointmentModel> getAppointments() {
        ArrayList<AppointmentModel> appointments = new ArrayList<>();
        String sql = "SELECT a.appointmentID, a.studentID, a.studentName, a.gName,a.gRelation, h.hallName AS HallName,  r.roomID, a.message FROM appointments a JOIN rooms r ON a.studentID = r.bookedBy JOIN halls h ON r.hall_id = h.id;";
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            ResultSet resultSet = databaseConnection.queryData(sql);
            while (resultSet.next()) {
                appointments.add(new AppointmentModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return appointments;
    }

}
