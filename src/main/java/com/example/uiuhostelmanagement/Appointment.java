package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Appointment implements Initializable {

    @FXML
    private TextField gName;

    @FXML
    private TextField gRelation;

    @FXML
    private TextArea message;

    @FXML
    private TextField sId;

    @FXML
    private TextField sName;

    DatabaseConnection databaseConnection;

    @FXML
    void back(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/login.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void submit(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(!gName.getText().equals("") && !gRelation.getText().equals("") && !sId.getText().equals(""))
        {
            String sql = "INSERT INTO appointments (studentID, studentName, gName, gRelation,message) VALUES (?, ?, ?, ?,?)";
            Connection connection = databaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,sId.getText());
            statement.setString(2,sName.getText());
            statement.setString(3,gName.getText());
            statement.setString(4,gRelation.getText());
            statement.setString(5,message.getText());
            int result = statement.executeUpdate();
            if(result==1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Appointment Submited", ButtonType.OK);
                alert.show();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Something Wrong", ButtonType.OK);
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Fill the  data", ButtonType.OK);
            alert.show();
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            databaseConnection = new DatabaseConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void search(ActionEvent event) throws SQLException {
        String sql = "SELECT name FROM students WHERE id = '"+ sId.getText()+"'";
        ResultSet resultSet = databaseConnection.queryData(sql);

        if(resultSet.next())
        {
            sName.setText(resultSet.getString(1));

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Student Not Found", ButtonType.OK);
            alert.show();
        }
    }
}
