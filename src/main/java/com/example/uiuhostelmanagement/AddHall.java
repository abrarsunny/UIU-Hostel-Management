package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AddHall {

    @FXML
    private TextField address;

    @FXML
    private Button billSetupButton;

    @FXML
    private Button dailyMealBillsButton;

    @FXML
    private ToggleGroup gender;

    @FXML
    private Button logoutButton;

    @FXML
    private TextField name;

    @FXML
    private Button newNoticeButton;

    @FXML
    private TextField number;

    @FXML
    private Button recieveBillButton;

    @FXML
    private Button registerStudentButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Button settingsButton;

    @FXML
    private TextField sharedAC;

    @FXML
    private TextField sharedNonAC;

    @FXML
    private TextField singleAC;

    @FXML
    private TextField singleNonAC;

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
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/admin.fxml");
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
    void settingsButtonAction(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) throws SQLException, ClassNotFoundException {
        RadioButton seletedGender = (RadioButton) gender.getSelectedToggle();
        String genderText = seletedGender.getText();

        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sql = "INSERT INTO halls (id, hallName, maleOrFemale, singleAC,singleNonAC,sharedAC,sharedNonAC,address,number) VALUES (?, ?, ?, ?,?,?,?,?,?)";
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,generateID(genderText));
        statement.setString(2,name.getText());
        statement.setString(3,genderText.toLowerCase());
        statement.setInt(4,Integer.parseInt(singleAC.getText()));
        statement.setInt(5,Integer.parseInt(singleNonAC.getText()));
        statement.setInt(6,Integer.parseInt(sharedAC.getText()));
        statement.setInt(7,Integer.parseInt(sharedNonAC.getText()));
        statement.setString(8,address.getText());
        statement.setString(9,number.getText());
        int result = statement.executeUpdate();
        if(result==1)
        {
            FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/halls.fxml");
            Scene scene = new Scene(fxmlScene.getRoot());
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed To Add Hall", ButtonType.OK);
            alert.show();
        }

    }
    private String generateID(String gender)
    {
        UUID uuid = UUID.randomUUID();
        return gender+"_"+"Hall_"+uuid.toString().substring(0, 4);
    }

}
