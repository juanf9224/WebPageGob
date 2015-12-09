/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.gobernacionsd.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author JBG INC
 */
@Named
@ViewScoped
public class MailBean implements Serializable{

    private static String name;
    private static String fromEmail;
    private static String messageFromVisitor;

    public MailBean() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        MailBean.name = name;
    }

    public String getfromEmail() {
        return fromEmail;
    }

    public void setfromEmail(String fromEmail) {
        MailBean.fromEmail = fromEmail;
    }

    public String getMessageFromVisitor() {
        return messageFromVisitor;
    }

    public void setMessageFromVisitor(String messageFromVisitor) {
        MailBean.messageFromVisitor = messageFromVisitor;
    }

    public static boolean send() {
        try {
            final String username = "camellomobusi4@gmail.com";
            final String password = "Soylaclave";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(fromEmail));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse("jfernandez@gobernacionsd.gob.do"));
                message.setSubject(name);
                message.setText(messageFromVisitor);

                Transport.send(message);

                FacesMessage msg = new FacesMessage("Mensaje enviado satisfactoriamente!");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, msg);
                
                name = null;
                fromEmail = null;
                messageFromVisitor = null;
                
                return true;

            } catch (MessagingException e) {
                FacesMessage msg = new FacesMessage("Error enviando el mensaje.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, msg);
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            FacesMessage msg = new FacesMessage("Error enviando el mensaje.");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, msg);
            e.toString();
            return false;
        }

    }
}
