package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.HallModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class EditHall implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField address;

    @FXML
    private TextField singleAC;

    @FXML
    private TextField singleNonAC;

    @FXML
    private TextField sharedAC;

    @FXML
    private TextField sharedNonAC;

    @FXML
    private TextField number;

    @FXML
    private ToggleGroup gender;
    private HallModel hallModel;

    public void setHallModel(HallModel hallModel) {
        this.hallModel = hallModel;
    }

    @FXML
    void submit(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
