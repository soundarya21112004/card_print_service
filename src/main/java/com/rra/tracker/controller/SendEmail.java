/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rra.tracker.controller;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @author Santhosh
 */
public class SendEmail {
//from for live 109 200
    //username for local

    String strServerName;
    String strServerPort;
    String strFrom;
    String strUserName;
    String strPassword;

    public void configfile() {
        try {

            File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
            File propertyFile = new File(catalinaBase, "bin/Mail.conf");


//            File propertyFile = new File("D:\\Manopraba\\PROJECT\\Philipines\\PSA_DECENTRALIZED_PRINT\\Mail.conf");


            InputStream in = new FileInputStream(propertyFile);
            ResourceBundle resource = new PropertyResourceBundle(in);
            strServerName = resource.getString("Mail.Server");
            strServerPort = resource.getString("Mail.Port");
            strFrom = resource.getString("Mail.From");
            strUserName = resource.getString("Mail.User");
            strPassword = resource.getString("Mail.Password");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendExternalMail(String tm, String subject, String mailMsg) throws IOException {
        boolean flag = false;
        final String from = strFrom;
        final String username = strUserName;
        final String password = strPassword;

        String host = strServerName;
        String port = strServerPort;

        Properties properties = new Properties();
//        Local
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
//        LIVE
//        properties.setProperty("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.put("mail.smtp.socketFactory.fallback", "true");
//        properties.put("mail.transport.protocol", "smtp");

        // Get the Session object.
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
            String imagePath = new File(catalinaBase, "bin/SignImage/psa_logo.png").toString(); //test

//            String imagePath = "D:\\Manopraba\\PROJECT\\Philipines\\PSA_DECENTRALIZED_PRINT\\SignImage\\psa_logo.png";  //local

            MimeMessage mimeMessage = new MimeMessage(session);
            InternetAddress[] fromAddress = InternetAddress.parse(from);
            InternetAddress[] toAddresses = InternetAddress.parse(tm);
            // mimeMessage.setFrom(fromAddress[0]);
            mimeMessage.setFrom(new InternetAddress(fromAddress[0].toString(), "PSA"));
            mimeMessage.setRecipients(RecipientType.TO, toAddresses);
            mimeMessage.setSubject(subject, "UTF-8");

            MimeMultipart mainPart = new MimeMultipart("related");
            MimeBodyPart messageWrapper = new MimeBodyPart();
            MimeMultipart messagesPart = new MimeMultipart("alternative");
            MimeBodyPart html = new MimeBodyPart();
            messagesPart.addBodyPart(html);

            messageWrapper.setContent(messagesPart);
            mainPart.addBodyPart(messageWrapper);
            MimeBodyPart sigAttachment = new MimeBodyPart();
            mainPart.addBodyPart(sigAttachment);

            // create the details for the sig content
            String embeddedAttachmentId = UUID.randomUUID().toString();
            String mailHTMLWithSig = "<html><body>" + mailMsg
                    + "<p><img src=\"cid:" + embeddedAttachmentId
                    + "\" alt=\"ATTACHMENT\"></p></body></html>";
//            String sigPath = "D:/sys/RRA_Logo.png";
            File sigFile = new File(imagePath);
            sigAttachment.attachFile(sigFile);
            sigAttachment.setContentID("<" + embeddedAttachmentId + ">");
            sigAttachment.setHeader("Content-Type", "image/jpg");
            sigAttachment.setFileName(sigFile.getName());

            html.setText(mailHTMLWithSig, "utf-8", "html");
            mimeMessage.setContent(mainPart);

            Transport.send(mimeMessage);

            flag = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean sendExternalMailtoMultiple(String[] tm, String subject, String mailMsg) throws IOException {
        boolean flag;
        String[] to = tm;//TODO
//        String to = "mnprb2011@gmail.com";
        final String from = strFrom;
        final String username = strUserName;
        final String password = strPassword;

        String host = strServerName;
        String port = strServerPort;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);


        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
//TODO

            File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
            String imagePath = new File(catalinaBase, "bin/SignImage/psa_logo.png").toString();
//            String imagePath = "D:\\Manopraba\\PROJECT\\Philipines\\PSA_DECENTRALIZED_PRINT\\SignImage\\psa_logo.png";  //local

            MimeMessage mimeMessage = new MimeMessage(session);
            InternetAddress[] fromAddress = InternetAddress.parse(from);
//            InternetAddress[] toAddresses = InternetAddress.parse(String.valueOf(to));

            InternetAddress[] toAddresses = new InternetAddress[to.length];
            int counter = 0;
            for (String recipient : to) {
                toAddresses[counter] = new InternetAddress(recipient.trim());
                counter++;
            }


            mimeMessage.setFrom(new InternetAddress(fromAddress[0].toString(), "PRODUCT AUTHENTICATION CODE"));
            mimeMessage.setRecipients(RecipientType.TO, toAddresses);
            mimeMessage.setSubject(subject, "UTF-8");

            MimeMultipart mainPart = new MimeMultipart("related");
            MimeBodyPart messageWrapper = new MimeBodyPart();
            MimeMultipart messagesPart = new MimeMultipart("alternative");
            MimeBodyPart html = new MimeBodyPart();
            messagesPart.addBodyPart(html);

            messageWrapper.setContent(messagesPart);
            mainPart.addBodyPart(messageWrapper);
            MimeBodyPart sigAttachment = new MimeBodyPart();
            mainPart.addBodyPart(sigAttachment);


            String embeddedAttachmentId = UUID.randomUUID().toString();
            String mailHTMLWithSig = "<html><body>" + mailMsg
                    + "<p><img width=\"25%\" src=\"cid:" + embeddedAttachmentId
                    + "\" alt=\"ATTACHMENT\"></p></body></html>";
            File sigFile = new File(imagePath);
            sigAttachment.attachFile(sigFile);
            sigAttachment.setContentID("<" + embeddedAttachmentId + ">");
            sigAttachment.setHeader("Content-Type", "image/jpg");
            sigAttachment.setFileName(sigFile.getName());

            html.setText(mailHTMLWithSig, "utf-8", "html");
            mimeMessage.setContent(mainPart);
//TODO
            Transport.send(mimeMessage);

            flag = true;
        } catch (MessagingException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

}
