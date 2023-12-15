package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

public class Settings {

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    void changeEmail(ActionEvent event) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();
        String sql = "UPDATE users SET breakfast = ?";

    }

    @FXML
    void changePassword(ActionEvent event) {

    }

}
