package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.HallModel;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HallCard implements Initializable {

    @FXML
    private Label gender;

    @FXML
    private Label name;

    @FXML
    private Label sharedAC;

    @FXML
    private Label sharedNonAC;

    @FXML
    private Label singleAC;

    @FXML
    private Label singleNonAC;

    private HallModel hall;
    private String hallId;


    public void setMainContainer(BorderPane mainContainer) {
        this.mainContainer = mainContainer;
    }

    private BorderPane mainContainer;

    @FXML
    void edit(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/editHall.fxml");
        ((EditHall)fxmlScene.getController()).setHallModel(hall);
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @FXML
    void view(ActionEvent event) {

    }
    public void setHall(HallModel hall) {
        this.hall = hall;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            name.setText(hall.getName());
            gender.setText(hall.getGender().toUpperCase());
            singleAC.setText(String.valueOf(hall.getSingleAC()));
            singleNonAC.setText(String.valueOf(hall.getSingleNonAC()));
            sharedAC.setText(String.valueOf(hall.getSharedAC()));
            sharedNonAC.setText(String.valueOf(hall.getSharedNonAC()));
        });
    }
}
