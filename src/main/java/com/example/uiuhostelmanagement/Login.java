package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class  Login {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void createAccount(ActionEvent event) {

        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/reg.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void login(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        String emailText = email.getText();
        String passwordText = password.getText();
        if((emailText.equals("") || emailText==null) || (passwordText.equals("") || passwordText==null))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Wrong Email Password", ButtonType.OK);
            alert.show();
        }
        else {
            String sql = "SELECT * FROM users WHERE email ='"+ emailText+"' AND password = '"+passwordText+"'";
            ResultSet resultSet = databaseConnection.queryData(sql);
            if(resultSet.next())
            {
                if(resultSet.getString(4).equals("admin"))
                {
                    FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/adminFrame.fxml");
                    Scene scene = new Scene(fxmlScene.getRoot());
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/studentDashboad.fxml"));
                    Parent root = loader.load();
                    StudentDashboad controller = loader.getController();
                    controller.setStudentEmail(emailText);
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Wrong Email Password", ButtonType.OK);
                alert.show();
            }


        }

    }

    public void gAppointment(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/appointment.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
