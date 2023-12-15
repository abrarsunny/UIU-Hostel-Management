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
    public void setMainContainer(BorderPane mainContainer) {
        this.mainContainer = mainContainer;
    }

    private BorderPane mainContainer;


    @FXML
    private VBox contrainer;


    @FXML
    private TextField searchBox;


    @FXML
    void addHall(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/addHall.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
//        Scene scene = new Scene(fxmlScene.getRoot());
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();

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
                        controller.setMainContainer(mainContainer);
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
