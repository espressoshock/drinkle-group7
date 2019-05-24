package com.espressoshock.drinkle.recoveryCodeGenerator;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {



    public static void sendEmail(String recepient, String code) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); //TLS

        String account = "XX@XX.com";
        String passsword = "XXX";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(account, passsword);
            }
        });

        Message message = prepareMessage(session, account, recepient, code);
        Transport.send(message);
    }

    private static Message prepareMessage(Session session, String owner, String recepient, String code){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(owner));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Drinkle password recovery");
            String htmlContent = "<h1>It seems you requested a password recovery</h1><p>Please type the following code in the application to confirm your identity</p><br/><code>"+code+"</code>";
            message.setContent(htmlContent,"text/html");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
