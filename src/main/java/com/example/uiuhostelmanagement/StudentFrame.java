package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.complain.ClientController;
import com.example.uiuhostelmanagement.model.StudentModel;
import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StudentFrame implements Initializable {

    @FXML
    private BorderPane rootPane;
    @FXML
    private  BorderPane mainContainer;
    private String email;
    private StudentModel studentModel;
    DatabaseConnection databaseConnection;

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    void addMeal(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/uiuhostelmanagement/addMeal.fxml"));
        Parent root = loader.load();
        AddMeal controller = loader.getController();
        controller.setStudentModel(studentModel);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void complains(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/complain/studentComplainBox.fxml");
        ((ClientController)fxmlScene.getController()).setStudentID(studentModel.getId());
        ((ClientController)fxmlScene.getController()).setClientName(studentModel.getName());
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @FXML
    void dashboard(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/studentDashboad.fxml");
        ((StudentDashboad)fxmlScene.getController()).setStudent(studentModel);
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
    void mealDetails(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/mealsTable.fxml");
        ((MealTable)fxmlScene.getController()).setStudentID(studentModel.getId());
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @FXML
    void notices(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/studentNotices.fxml");
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    @FXML
    void recieveBills(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/receiveBill.fxml");
        ((ReceiveBill)fxmlScene.getController()).setStudent(studentModel);
        mainContainer.setCenter(fxmlScene.getRoot());
    }

    private void loadAdminContent() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/uiuhostelmanagement/studentDashboad.fxml"));
            Parent adminContent = loader.load();

            // Get the controller associated with the loaded FXML
            StudentDashboad controller = loader.getController();
            controller.setStudent(studentModel);

            // Set the content to the mainContainer
            mainContainer.setCenter(adminContent);

            // Pass any necessary data to the AdminContentController if needed
            //adminContentController.initializeData(); // Example method to pass data

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            try {
                databaseConnection = new DatabaseConnection();
                String sql = "SELECT * FROM students WHERE email ='"+ email+"'";
                ResultSet resultSet = databaseConnection.queryData(sql);
                if(resultSet.next())
                {
                    setStudentModel(new StudentModel(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR,"Student Not found", ButtonType.OK);
                    alert.show();
                }
                loadAdminContent();



            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
