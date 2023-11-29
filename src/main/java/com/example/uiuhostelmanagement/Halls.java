package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.HallModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.DatabaseReadCall;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Halls implements Initializable {

    @FXML
    private Button billSetupButton;

    @FXML
    private VBox contrainer;

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
    private TextField searchBox;

    @FXML
    private Button settingsButton;

    @FXML
    void addHall(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/addHall.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void billSetupButtonAction(ActionEvent event) {

    }

    @FXML
    void dailyMealBillsButtonAction(ActionEvent event) {

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
    void search(KeyEvent event) throws SQLException, ClassNotFoundException, IOException {
        contrainer.getChildren().clear();
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(contrainer);
        fadeTransition.setDuration(Duration.seconds(0.5));
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        ArrayList<HallModel> halls = new ArrayList<>();
        String searchSQL = "select * from halls where hallName LIKE ?";
        HashMap<Integer,Object> searchHash = new HashMap<>();
        searchHash.put(1,"%"+((TextField)event.getSource()).getText()+"%");
        DatabaseReadCall databaseReadCall = new DatabaseReadCall(searchSQL,searchHash);
        databaseReadCall.setOnSucceeded(workerStateEvent -> {
            try {
                ResultSet resultSet = databaseReadCall.getValue();

                while (resultSet.next()) {
                    halls.add(new HallModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7)));
                }
                if (halls.size() > 0) {

                    for (HallModel hall:halls) {
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/HallCard.fxml"));
                            Parent root = loader.load();
                            HallCard controller = loader.getController();
                            controller.setHall(hall);
                            contrainer.getChildren().add(root);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                } else {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/noResult.fxml"));
                    Parent root = loader.load();
                    contrainer.getChildren().add(root);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        });
        Thread thread =new Thread(databaseReadCall);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    void settingsButtonAction(ActionEvent event) {

    }

    public void halls(ActionEvent actionEvent) {
    }

    public void rooms(ActionEvent actionEvent) {
    }

    public void notices(ActionEvent actionEvent) {
    }

    public void complains(ActionEvent actionEvent) {
    }

    public void dashboad(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/admin.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            if(searchBox.getText().isEmpty()) {
                for (HallModel hall:getHalls()) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/HallCard.fxml"));
                        Parent root = loader.load();
                        HallCard controller = loader.getController();
                        controller.setHall(hall);
                        contrainer.getChildren().add(root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private ArrayList<HallModel> getHalls() {
        ArrayList<HallModel> halls = new ArrayList<>();
        String sql = "SELECT * FROM halls";
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            ResultSet resultSet = databaseConnection.queryData(sql);
            while (resultSet.next()) {
                halls.add(new HallModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5),resultSet.getInt(6),resultSet.getInt(7)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return halls;
    }


}
