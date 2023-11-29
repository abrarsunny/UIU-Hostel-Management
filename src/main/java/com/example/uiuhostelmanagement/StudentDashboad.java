package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.complain.ClientController;
import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentDashboad implements Initializable {
    private String studentEmail;
    private StudentModel student;
    DatabaseConnection databaseConnection;
    public void addMealButtonAction(ActionEvent actionEvent) {
    }

    public void recieveBillButtonAction(ActionEvent actionEvent) {
    }

    public void dailyMealBillsButtonAction(ActionEvent actionEvent) {
    }

    public void logoutButtonAction(ActionEvent actionEvent) {
    }

    public void Complain(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/complain/studentComplainBox.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        ClientController controller = (ClientController) fxmlScene.getController();
        controller.setClientName(student.getName());
        controller.setStudentID(student.getId());
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    void setStudentEmail(String studentEmail)
    {
        this.studentEmail = studentEmail;
    }
    void setStudent(StudentModel student)
    {
        this.student =student;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            try {
                databaseConnection = new DatabaseConnection();
                String sql = "SELECT * FROM students WHERE email ='"+ studentEmail+"'";
                ResultSet resultSet = databaseConnection.queryData(sql);
                if(resultSet.next())
                {
                    setStudent(new StudentModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
                    System.out.println(student);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Student Not found", ButtonType.OK);
                    alert.show();
                }



            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });



    }
}
