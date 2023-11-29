package com.example.uiuhostelmanagement.util;

import javafx.concurrent.Task;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Transport;
public class SendEmail  extends Task{

    private String recepient;
    private String subject;
    private String content;

    public SendEmail(String recepient, String subject, String content) {
        this.recepient = recepient;
        this.subject = subject;
        this.content = content;
    }



    public static void sendMail (String recepient, String subject, String content) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");


        String myAccountEmail = "uiuhostel@gmail.com";
        String password = "bnra mhkp qkbl ylfe";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        Message message = prepareMessage(session,myAccountEmail,recepient,subject,content);
        Transport.send(message);
    }
    private static Message prepareMessage(Session session,String myAccountEmail,String recepient,String subject, String content) throws Exception {
        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject(subject);
            message.setContent(content,"text/html");
            return message;
        }
        catch (Exception e)
        {

        }
        return null;
    }


    @Override
    protected Object call() throws Exception {
        sendMail(recepient,subject,content);
        return null;
    }
}