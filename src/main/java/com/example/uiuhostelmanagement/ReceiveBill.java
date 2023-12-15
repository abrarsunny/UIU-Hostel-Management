package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ReceiveBill implements Initializable {

    @FXML
    private TextField qBreakfast;

    @FXML
    private TextField qLunch;

    @FXML
    private TextField qDinner;

    @FXML
    private TextField perBreakfast;

    @FXML
    private TextField perLunch;

    @FXML
    private TextField perDinner;

    @FXML
    private TextField totalBreakfast;

    @FXML
    private TextField totaLunch;

    @FXML
    private TextField totalDinner;

    @FXML
    private TextField totalMeal;

    @FXML
    private TextField roomBill;

    @FXML
    private TextField otherBill;

    @FXML
    private TextField intotal;
    private StudentModel student;

    public void setStudent(StudentModel student) {
        this.student = student;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{

            try {
                LocalDate currentDate = LocalDate.now();

                // Get the first day of the previous month
                LocalDate firstDayOfPreviousMonth = currentDate.withDayOfMonth(1);

                // Get the last day of the previous month
                LocalDate lastDayOfPreviousMonth = firstDayOfPreviousMonth.plusMonths(1).minusDays(1);


                DatabaseConnection databaseConnection = new DatabaseConnection();
                String mealDetailsQuery = "SELECT SUM(breakfast) as total_breakfast, SUM(lunch) as total_lunch, SUM(dinner) as total_dinner FROM meals WHERE studentID = ? AND date BETWEEN ? AND ?;";
                Connection connection = databaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(mealDetailsQuery);
                preparedStatement.setString(1,student.getId());
                preparedStatement.setString(2,firstDayOfPreviousMonth.toString());
                preparedStatement.setString(3,lastDayOfPreviousMonth.toString());
                ResultSet resultSet = preparedStatement.executeQuery();
                String billConfigSQL = "SELECT * FROM billsconfig";
                ResultSet resultSet1 = databaseConnection.queryData(billConfigSQL);
                String roomSQL = "SELECT * FROM rooms WHERE bookedBy = '"+student.getId()+"'";
                ResultSet resultSet2 = databaseConnection.queryData(roomSQL);
                if(resultSet.next() && resultSet1.next() && resultSet2.next())
                {
                    qBreakfast.setText(String.valueOf(resultSet.getInt(1)));
                    qLunch.setText(String.valueOf(resultSet.getInt(2)));
                    qDinner.setText(String.valueOf(resultSet.getInt(3)));
                    perBreakfast.setText(String.valueOf(resultSet1.getInt(2)));
                    perDinner.setText(String.valueOf(resultSet1.getInt(3)));
                    perLunch.setText(String.valueOf(resultSet1.getInt(4)));
                    otherBill.setText(String.valueOf(resultSet1.getInt(5)));
                    if(resultSet2.getString(2).equals("Single Room") && resultSet2.getString(3).equals("AC"))
                        roomBill.setText(String.valueOf(resultSet1.getInt(6)));
                    else if(resultSet2.getString(2).equals("Single Room") && resultSet2.getString(3).equals("Non-AC"))
                        roomBill.setText(String.valueOf(resultSet1.getInt(7)));
                    else if(resultSet2.getString(2).equals("Shared Room") && resultSet2.getString(3).equals("AC"))
                        roomBill.setText(String.valueOf(resultSet1.getInt(8)));
                    else if(resultSet2.getString(2).equals("Shared Room") && resultSet2.getString(3).equals("Non-AC"))
                        roomBill.setText(String.valueOf(resultSet1.getInt(9)));

                    totalBreakfast.setText(String.valueOf(Integer.parseInt(qBreakfast.getText())*Integer.parseInt(perBreakfast.getText())));
                    totaLunch.setText(String.valueOf(Integer.parseInt(qLunch.getText())*Integer.parseInt(perLunch.getText())));
                    totalDinner.setText(String.valueOf(Integer.parseInt(qDinner.getText())*Integer.parseInt(perDinner.getText())));
                    totalMeal.setText(String.valueOf(Integer.parseInt(totalBreakfast.getText())+Integer.parseInt(totaLunch.getText())+Integer.parseInt(totalDinner.getText())));
                    intotal.setText(String.valueOf(Integer.parseInt(totalMeal.getText())+Integer.parseInt(roomBill.getText())+Integer.parseInt(otherBill.getText())));

                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
