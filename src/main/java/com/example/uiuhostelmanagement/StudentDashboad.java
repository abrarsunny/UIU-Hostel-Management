package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.complain.ClientController;
import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class StudentDashboad implements Initializable {
    private String studentEmail;
    private StudentModel student;
    DatabaseConnection databaseConnection;
    @FXML
    private Text name;

    @FXML
    private Text studentID;

    @FXML
    private Text email;

    @FXML
    private Text mobile;

    @FXML
    private Text roomType;

    @FXML
    private Text acOrNon;

    @FXML
    private Text hallName;

    @FXML
    private Text roomID;

    @FXML
    private Text breakfast;

    @FXML
    private Text lunch;

    @FXML
    private Text dinner;

    @FXML
    private Text todayCost;

    @FXML
    private Text mealCost;

    @FXML
    private Text roomBill;

    @FXML
    private Text otherBill;

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
                String sql = "SELECT * FROM students WHERE email ='"+ student.getEmail()+"'";
                ResultSet resultSet = databaseConnection.queryData(sql);
                if(resultSet.next())
                {
                    setStudent(new StudentModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
                    name.setText(student.getName());
                    studentID.setText(student.getId());
                    email.setText(student.getEmail());
                    mobile.setText(student.getMobile());
                    roomType.setText(student.getRoomType());
                    acOrNon.setText(student.getAcOrNon());
                    String mealDetailsQuery = "SELECT SUM(breakfast) as total_breakfast, SUM(lunch) as total_lunch, SUM(dinner) as total_dinner FROM meals WHERE studentID = ? AND date = ?;";
                    Connection connection = databaseConnection.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement(mealDetailsQuery);
                    preparedStatement.setString(1,student.getId());
                    preparedStatement.setString(2, LocalDate.now().toString());
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    String billConfigSQL = "SELECT * FROM billsconfig";
                    ResultSet resultSet2 = databaseConnection.queryData(billConfigSQL);
                    if(resultSet1.next() && resultSet2.next())
                    {
                        breakfast.setText(String.valueOf(resultSet1.getInt(1)));
                        lunch.setText(String.valueOf(resultSet1.getInt(2)));
                        dinner.setText(String.valueOf(resultSet1.getInt(3)));
                        todayCost.setText(String.valueOf(resultSet1.getInt(1)*resultSet2.getInt(2)+resultSet1.getInt(2)*resultSet2.getInt(4)+resultSet1.getInt(3)*resultSet2.getInt(3)));
                    }


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
