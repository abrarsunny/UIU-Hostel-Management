package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BillSetup implements Initializable {

    @FXML
    private TextField breakfast;

    @FXML
    private TextField dinner;

    @FXML
    private TextField singleAC;

    @FXML
    private TextField singleNonAC;

    @FXML
    private TextField sharedAC;

    @FXML
    private TextField sharedNonAC;

    @FXML
    private TextField lunch;

    @FXML
    private TextField others;

    DatabaseConnection databaseConnection;
    @FXML
    void submit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Connection connection = databaseConnection.getConnection();
        String billSetupUpdateQuery = "UPDATE billsconfig SET breakfast = ?, dinner = ?, lunch = ?, others = ?, singleAC = ?, singleNonAC = ?, sharedAC = ?, sharedNonAC = ? WHERE billConfigID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(billSetupUpdateQuery);
        preparedStatement.setInt(1, Integer.parseInt(breakfast.getText()));
        preparedStatement.setInt(2, Integer.parseInt(dinner.getText()));
        preparedStatement.setInt(3, Integer.parseInt(lunch.getText()));
        preparedStatement.setInt(4, Integer.parseInt(others.getText()));
        preparedStatement.setInt(5, Integer.parseInt(singleAC.getText()));
        preparedStatement.setInt(6, Integer.parseInt(singleNonAC.getText()));
        preparedStatement.setInt(7, Integer.parseInt(sharedAC.getText()));
        preparedStatement.setInt(8, Integer.parseInt(sharedNonAC.getText()));
        preparedStatement.setInt(9, 1);
        if (preparedStatement.executeUpdate() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Bill has been updated", ButtonType.OK);
            alert.show();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            try {
                databaseConnection = new DatabaseConnection();
                String billConfigQuery = "SELECT * FROM billsconfig WHERE billConfigID =1";
                ResultSet resultSet = databaseConnection.queryData(billConfigQuery);
                if(resultSet.next())
                {
                    breakfast.setText(String.valueOf(resultSet.getInt(2)));
                    dinner.setText(String.valueOf(resultSet.getInt(3)));
                    lunch.setText(String.valueOf(resultSet.getInt(4)));
                    others.setText(String.valueOf(resultSet.getInt(5)));
                    singleAC.setText(String.valueOf(resultSet.getInt(6)));
                    singleNonAC.setText(String.valueOf(resultSet.getInt(7)));
                    sharedAC.setText(String.valueOf(resultSet.getInt(8)));
                    sharedNonAC.setText(String.valueOf(resultSet.getInt(9)));
                }





            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
