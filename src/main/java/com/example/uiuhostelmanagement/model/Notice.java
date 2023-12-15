package com.example.uiuhostelmanagement.model;

import java.sql.Date;

public class Notice {
    String noticeID;
    Date date;
    String subject;
    String message;

    public Notice(String noticeID, Date date, String subject, String message) {
        this.noticeID = noticeID;
        this.date = date;
        this.subject = subject;
        this.message = message;
    }

    public String getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(String noticeID) {
        this.noticeID = noticeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
