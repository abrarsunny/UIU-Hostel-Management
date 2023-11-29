package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Admin_Dashboard implements Initializable {
    @FXML
    private Button billSetupButton;

    @FXML
    private Button dailyMealBillsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button newNoticeButton;

    @FXML
    private Button recieveBillButton;

    @FXML
    private Button registerStudentButton;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Button settingsButton;
    @FXML
    private Text femaleACAvailable;

    @FXML
    private Text femaleACTotal;

    @FXML
    private Text femaleNonACAvailable;

    @FXML
    private Text femaleNonACTotal;


    @FXML
    private Text maleACAvailable;

    @FXML
    private Text maleACTotal;

    @FXML
    private Text maleNonACAvailable;

    @FXML
    private Text maleNonACTotal;

    DatabaseConnection databaseConnection;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {

            databaseConnection = new DatabaseConnection();

            String sql = "SELECT 'male' as gender, SUM(singleAC) as singleAC, SUM(singleNonAC) as singleNonAC, SUM(sharedAC) as sharedAC, SUM(sharedNonAC) as sharedNonAC FROM halls WHERE maleOrFemale = 'male' GROUP BY gender UNION SELECT 'female' as gender, SUM(singleAC) as singleAC, SUM(singleNonAC) as singleNonAC, SUM(sharedAC) as sharedAC, SUM(sharedNonAC) as sharedNonAC FROM halls WHERE   maleOrFemale = 'female' GROUP BY gender;";
            ResultSet resultSet = databaseConnection.queryData(sql);
            String sql2 = "SELECT 'male' as gender,SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedACSingleRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedNonACSingleRooms, SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedACSharedRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedNonACSharedRooms FROM (SELECT * FROM rooms INNER JOIN halls ON rooms.hall_id = halls.id AND halls.maleOrFemale = 'male') as rooms WHERE bookedBy IS NOT NULL UNION SELECT 'female' as gender, SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedACSingleRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Single Room' THEN 1 ELSE 0 END) as bookedNonACSingleRooms, SUM(CASE WHEN acOrNon = 'AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedACSharedRooms, SUM(CASE WHEN acOrNon = 'Non-AC' AND roomType = 'Shared Room' THEN 1 ELSE 0 END) as bookedNonACSharedRooms\n" +
                    "FROM (SELECT * FROM rooms INNER JOIN halls ON rooms.hall_id = halls.id AND halls.maleOrFemale = 'female') as rooms WHERE bookedBy IS NOT NULL;";
            ResultSet resultSet2 = databaseConnection.queryData(sql2);

            while(resultSet.next() && resultSet2.next())
            {
                if(resultSet.getString(1).equals("male")) {

                    int totalACRoomBooked = resultSet2.getInt(2)+resultSet2.getInt(4);
                    int totalACRoom = resultSet.getInt(2)+resultSet.getInt(4);

                    int totalNonACRoomBooked = resultSet2.getInt(3)+resultSet2.getInt(5);
                    int totalNonACRoom = resultSet.getInt(3)+resultSet.getInt(5);
                    maleACTotal.setText(String.valueOf(totalACRoom));
                    maleNonACTotal.setText(String.valueOf(totalNonACRoom));
                    maleACAvailable.setText(String.valueOf(totalACRoom-totalACRoomBooked));
                    maleNonACAvailable.setText(String.valueOf(totalNonACRoom-totalNonACRoomBooked));
                }
                {

                    int totalACRoomBooked = resultSet2.getInt(2)+resultSet2.getInt(4);
                    int totalACRoom = resultSet.getInt(2)+resultSet.getInt(4);

                    int totalNonACRoomBooked = resultSet2.getInt(3)+resultSet2.getInt(5);
                    int totalNonACRoom = resultSet.getInt(3)+resultSet.getInt(5);
                    femaleACTotal.setText(String.valueOf(totalACRoom));
                    femaleNonACTotal.setText(String.valueOf(totalNonACRoom));
                    femaleACAvailable.setText(String.valueOf(totalACRoom-totalACRoomBooked));
                    femaleNonACAvailable.setText(String.valueOf(totalNonACRoom-totalNonACRoomBooked));
                }
            }





        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void billSetupButtonAction(ActionEvent event) {

    }

    @FXML
    void appointmentButtonAction(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/appointmentTable.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logoutButtonAction(ActionEvent event) {

    }

    @FXML
    void newNoticeButtonAction(ActionEvent event) {

    }

    @FXML
    void recieveBillButtonAction(ActionEvent event) {

    }

    @FXML
    void registerStudentButtonAction(ActionEvent event) {

    }

    @FXML
    void settingsButtonAction(ActionEvent event) {

    }

    public void halls(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/halls.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void rooms(ActionEvent actionEvent) {
    }

    public void notices(ActionEvent actionEvent) {
    }

    public void complains(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/complain/adminComplainBox.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
