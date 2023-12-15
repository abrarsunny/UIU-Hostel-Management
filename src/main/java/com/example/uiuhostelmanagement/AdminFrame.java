package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminFrame implements Initializable {
    @FXML
    private BorderPane rootPane;

    @FXML
    private BorderPane mainContainer;


    public void setEmail(String email) {
        this.email = email;
    }

    private String email;



    @FXML
    void billSetupButtonAction(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/billSetup.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAdminContent();

    }

    @FXML
    void dashboard(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/adminDashboard.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    private void loadAdminContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/uiuhostelmanagement/adminDashboard.fxml"));
            Parent adminContent = loader.load();

            // Get the controller associated with the loaded FXML
            AdminDashboard adminContentController = loader.getController();

            // Set the content to the mainContainer
            mainContainer.setCenter(adminContent);

            // Pass any necessary data to the AdminContentController if needed
            //adminContentController.initializeData(); // Example method to pass data

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void appointmentButtonAction(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/appointmentTable.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @FXML
    void logoutButtonAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void newNoticeButtonAction(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/addNotice.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }


    @FXML
    void studentList(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/studentTable.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @FXML
    void settingsButtonAction(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/setting.fxml");
        ((Settings)fxmlScene.getController()).setEmail(email);
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    public void halls(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/halls.fxml");
        ((Halls)fxmlScene.getController()).setMainContainer(mainContainer);
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    public void notices(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/notices.fxml");
        Notices notices = (Notices) fxmlScene.getController();
        notices.setMainContainer(mainContainer);
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    public void complains(ActionEvent actionEvent) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/complain/adminComplainBox.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }

}
