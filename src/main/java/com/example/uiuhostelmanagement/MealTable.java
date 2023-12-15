package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.AppointmentModel;
import com.example.uiuhostelmanagement.model.Meal;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.DatabaseReadCall;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MealTable implements Initializable {

    @FXML
    private TextField searchBox;

    @FXML
    private VBox contrainer;
    private String studentID;

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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
        ArrayList<Meal> meals = new ArrayList<>();
        String searchSQL = "SELECT * FROM meals WHERE date LIKE ? AND studentID LIKE ?";
        HashMap<Integer,Object> searchHash = new HashMap<>();
        searchHash.put(1,"%"+((TextField)event.getSource()).getText()+"%");
        searchHash.put(2,studentID);
        DatabaseReadCall databaseReadCall = new DatabaseReadCall(searchSQL,searchHash);
        databaseReadCall.setOnSucceeded(workerStateEvent -> {
            try {
                ResultSet resultSet = databaseReadCall.getValue();

                while (resultSet.next()) {
                    meals.add(new Meal(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getDate(7)));
                }
                if (meals.size() > 0) {

                    for (Meal meal:meals) {
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/mealCard.fxml"));
                            Parent root = loader.load();
                            MealCard controller = loader.getController();
                            controller.setMeal(meal);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            if(searchBox.getText().isEmpty()) {
                for (Meal meal:getMeals()) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/mealCard.fxml"));
                        Parent root = loader.load();
                        MealCard controller = loader.getController();
                        controller.setMeal(meal);
                        contrainer.getChildren().add(root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private ArrayList<Meal> getMeals() {
        ArrayList<Meal> meals = new ArrayList<>();
        String sql = "SELECT * FROM meals WHERE studentID = '"+studentID+"'";
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            ResultSet resultSet = databaseConnection.queryData(sql);
            while (resultSet.next()) {
                meals.add(new Meal(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getDate(7)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return meals;
    }

}
