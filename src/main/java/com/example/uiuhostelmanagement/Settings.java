package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Settings {

    @FXML
    private TextField email;

    @FXML
    private TextField password;


    public void setEmail(String emailText) {
        this.emailText = emailText;
    }

    private String emailText;

    @FXML
    void changeEmail(ActionEvent event) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String sql = "UPDATE users SET `email` = ? WHERE `users`.`userID` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,email.getText());
        preparedStatement.setString(2,"ADMIN");
        int result =preparedStatement.executeUpdate();
        if(result==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Email has been changed", ButtonType.OK);
            alert.show();
        }

    }

    @FXML
    void changePassword(ActionEvent event) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String sql = "UPDATE users SET `password` = ? WHERE `users`.`userID` = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,password.getText());
        preparedStatement.setString(2,"ADMIN");
        int result =preparedStatement.executeUpdate();
        if(result==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Password has been changed", ButtonType.OK);
            alert.show();
        }
    }

}
