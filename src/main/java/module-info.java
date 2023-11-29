module com.example.uiuhostelmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;


    opens com.example.uiuhostelmanagement to javafx.fxml;
    exports com.example.uiuhostelmanagement;
    opens com.example.uiuhostelmanagement.complain to javafx.fxml;
    exports com.example.uiuhostelmanagement.complain;

}