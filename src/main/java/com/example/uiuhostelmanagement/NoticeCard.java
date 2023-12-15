package com.example.uiuhostelmanagement;

import com.example.uiuhostelmanagement.model.Notice;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class NoticeCard implements Initializable {

    @FXML
    private Label date;

    @FXML
    private Label subject;

    @FXML
    private Label message;
    private Notice notice;

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{
            date.setText(notice.getDate().toString());
            subject.setText(notice.getSubject());
            message.setText(notice.getMessage());
        });
    }
}
