package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.util.DatabaseConnection;
import com.example.uiuhostelmanagement.util.FXMLScene;
import com.example.uiuhostelmanagement.util.SendEmail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class Registration {

    @FXML
    private ToggleGroup acCondition;

    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private TextField gNumber;

    @FXML
    private ToggleGroup gender;

    @FXML
    private TextField id;

    @FXML
    private TextField mobile;

    @FXML
    private TextField name;

    @FXML
    private ToggleGroup roomType;
    @FXML
    private ImageView imageview;
    private File file;
    private String generatePassword()
    {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 8);
    }

    @FXML
    void register(ActionEvent event) throws Exception {

        RadioButton seletedGender = (RadioButton) gender.getSelectedToggle();
        String genderText = seletedGender.getText();

        RadioButton seletedRoomType = (RadioButton) roomType.getSelectedToggle();
        String roomTypeText = seletedRoomType.getText();

        RadioButton seletedAC = (RadioButton) acCondition.getSelectedToggle();
        String acText = seletedAC.getText();
        FileInputStream fis = new FileInputStream(file);

        DatabaseConnection databaseConnection = new DatabaseConnection();
        String sql = "INSERT INTO students (id, name, email, mobile,gender,roomType,acOrNon,address,gNumber,image) VALUES (?, ?, ?, ?,?,?,?,?,?,?)";
        Connection connection = databaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id.getText());
        statement.setString(2,name.getText());
        statement.setString(3,email.getText());
        statement.setString(4,mobile.getText());
        statement.setString(5,genderText);
        statement.setString(6,roomTypeText);
        statement.setString(7,acText);
        statement.setString(8,address.getText());
        statement.setString(9,gNumber.getText());
        statement.setBinaryStream(10, fis, (int) file.length());

        int result = statement.executeUpdate();
        String password = generatePassword();
        SendEmail.sendMail(email.getText(),"UIU Hostel Account Credentials","Your Password : " + password);
        if(result==1)
        {
            String userCreateSQL = "INSERT INTO users (userID, email, password, role) VALUES (?, ?, ?, ?)";
            PreparedStatement statement2 = connection.prepareStatement(userCreateSQL);
            statement2.setString(1,id.getText());
            statement2.setString(2,email.getText());
            statement2.setString(3,password);
            statement2.setString(4,"student");
            int result2 = statement2.executeUpdate();
            if(result2==1)
            {
                FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/login.fxml");
                Scene scene = new Scene(fxmlScene.getRoot());
                Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,"Failed To Create Account", ButtonType.OK);
                alert.show();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Failed To Create Account", ButtonType.OK);
            alert.show();
        }
    }

    public void login(ActionEvent event) {
        FXMLScene fxmlScene = FXMLScene.load("/com/example/uiuhostelmanagement/login.fxml");
        Scene scene = new Scene(fxmlScene.getRoot());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void upload(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose your Picture");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPEG", "*.jpg");
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("PNG", "*.png");
        fileChooser.getExtensionFilters().addAll(filter, filter1);
        file = fileChooser.showOpenDialog(((Node) mouseEvent.getSource()).getScene().getWindow());

        if (file != null) {
            Image image = new Image(file.toURI().toString()); // Use toURI().toString()
            imageview.setImage(image);
            imageview.setPreserveRatio(true);

        } else {
            System.out.println("No file selected.");
        }

    }
}
