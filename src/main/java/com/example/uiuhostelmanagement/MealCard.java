package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.Meal;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MealCard implements Initializable {

    @FXML
    private Label date;

    @FXML
    private Label breakfast;

    @FXML
    private Label lunch;

    @FXML
    private Label dinner;
    private Meal meal;
    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            date.setText(meal.getDate().toString());
            breakfast.setText(String.valueOf(meal.getBreakfast()));
            lunch.setText(String.valueOf(meal.getLunch()));
            dinner.setText(String.valueOf(meal.getDinner()));

        });
    }


}
