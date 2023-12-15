package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.HallModel;
import com.example.uiuhostelmanagement.model.Notice;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.DatabaseReadCall;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Notices implements Initializable {

    @FXML
    private TextField searchBox;

    @FXML
    private VBox contrainer;
    public void setMainContainer(BorderPane mainContainer) {
        this.mainContainer = mainContainer;
    }

    private BorderPane mainContainer;

    @FXML
    void addNotices(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/addNotice.fxml");
        AddNotice notices = (AddNotice) fxmlScene.getController();
        notices.setMainContainer(mainContainer);
        mainContainer.setCenter(fxmlScene.getRoot());
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
        ArrayList<Notice> notices = new ArrayList<>();
        String searchSQL = "select * from notices where subject LIKE ? OR message LIKE ?";
        HashMap<Integer,Object> searchHash = new HashMap<>();
        searchHash.put(1,"%"+((TextField)event.getSource()).getText()+"%");
        searchHash.put(2,"%"+((TextField)event.getSource()).getText()+"%");
        DatabaseReadCall databaseReadCall = new DatabaseReadCall(searchSQL,searchHash);
        databaseReadCall.setOnSucceeded(workerStateEvent -> {
            try {
                ResultSet resultSet = databaseReadCall.getValue();

                while (resultSet.next()) {
                    notices.add(new Notice(resultSet.getString(1),resultSet.getDate(2),resultSet.getString(3),resultSet.getString(4)));
                }
                if (notices.size() > 0) {

                    for (Notice notice:notices) {
                        try {
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/NoticeCard.fxml"));
                            Parent root = loader.load();
                            NoticeCard controller = loader.getController();
                            controller.setNotice(notice);
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
                for (Notice notice:getNotices()) {
                    try {
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/NoticeCard.fxml"));
                        Parent root = loader.load();
                        NoticeCard controller = loader.getController();
                        controller.setNotice(notice);
                        contrainer.getChildren().add(root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private ArrayList<Notice> getNotices() {
        ArrayList<Notice> notices = new ArrayList<>();
        String sql = "SELECT * FROM notices";
        try {
            DatabaseConnection databaseConnection = new DatabaseConnection();
            ResultSet resultSet = databaseConnection.queryData(sql);
            while (resultSet.next()) {
                notices.add(new Notice(resultSet.getString(1),resultSet.getDate(2),resultSet.getString(3),resultSet.getString(4)));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return notices;
    }

}
