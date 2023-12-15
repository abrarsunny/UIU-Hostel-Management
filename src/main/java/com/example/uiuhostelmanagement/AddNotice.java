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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class AddNotice {

    @FXML
    private TextField subject;

    @FXML
    private TextArea message;
    public void setMainContainer(BorderPane mainContainer) {
        this.mainContainer = mainContainer;
    }

    private BorderPane mainContainer;

    @FXML
    void submit(ActionEvent event) throws SQLException, ClassNotFoundException {


        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sql = "INSERT INTO notices (noticeID, date, subject, message) VALUES (?, ?, ?, ?)";
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,generateID());
        statement.setDate(2, Date.valueOf(LocalDate.now()));
        statement.setString(3,subject.getText());
        statement.setString(4,message.getText());
        int result = statement.executeUpdate();
        if(result==1)
        {
            FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/notices.fxml");
            mainContainer.setCenter(fxmlScene.getRoot());
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed To Add Hall", ButtonType.OK);
            alert.show();
        }

    }
    private String generateID()
    {
        UUID uuid = UUID.randomUUID();
        return "Notice_"+uuid.toString().substring(0, 4);
    }

}
