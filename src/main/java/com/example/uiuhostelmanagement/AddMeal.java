package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;

public class AddMeal {

    @FXML
    private ToggleGroup breakfast;

    @FXML
    private ToggleGroup lunch;

    @FXML
    private ToggleGroup dinner;
    private StudentModel studentModel;

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    @FXML
    void submit(ActionEvent event)  {
        try
        {
            String  breakfastText = ((RadioButton) breakfast.getSelectedToggle()).getText();
            String  lunchText = ((RadioButton) lunch.getSelectedToggle()).getText();
            String  dinnerText = ((RadioButton) dinner.getSelectedToggle()).getText();
            DatabaseConnection databaseConnection = new DatabaseConnection();
            String sql = "INSERT INTO meals (mealID, studentID, gender, breakfast, lunch, dinner, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection connection = databaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,generateID());
            preparedStatement.setString(2,studentModel.getId());
            preparedStatement.setString(3,studentModel.getGender());
            preparedStatement.setInt(4,(breakfastText.equals("On"))?1:0);
            preparedStatement.setInt(5,(lunchText.equals("On"))?1:0);
            preparedStatement.setInt(6,(dinnerText.equals("On"))?1:0);
            preparedStatement.setDate(7, Date.valueOf(LocalDate.now()));
            int result = preparedStatement.executeUpdate();
            if(result==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Meal Added", ButtonType.OK);
                alert.show();
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
            }

        }catch (SQLIntegrityConstraintViolationException e)
        {
                Alert alert = new Alert(Alert.AlertType.WARNING,"Meal Already Added", ButtonType.OK);
                alert.show();
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

        }
        catch (Exception e)
        {

        }

    }
    private String generateID()
    {
        UUID uuid = UUID.randomUUID();
        return "Meal_"+uuid.toString().substring(0, 4);
    }
}
