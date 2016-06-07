/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author stiven
 */
public class MailService {

    /**
     * Sends a subject and message to a recipient
     *
     * @param recipient Internet address of the recipient
     * @param subject the subject of the message
     * @param message the message
     * @throws MessagingException
     */
    public static void sendMessage(String recipient, String subject, String message) throws MessagingException, IOException {

        if (theService == null) {
            theService = new MailService();
        }

        MimeMessage mimeMessage = new MimeMessage(mailSession);

        mimeMessage.setFrom(new InternetAddress(FROM));
        mimeMessage.setSender(new InternetAddress(FROM));
        mimeMessage.setSubject(subject);
        mimeMessage.setContent(message, "text/plain");

        // Creo la parte del mensaje  
        /*MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setText("Estimado usuario le damos la bienvenida a SIGPE, 
        le informamos que su registro se reliazo con exito.");

        // Crear el multipart para agregar la parte del mensaje anterior  
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        // Agregar el multipart al cuerpo del mensaje  
        mimeMessage.setContent(multipart);*/


        //mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        String address = recipient;
        InternetAddress[] iAdressArray = InternetAddress.parse(address);
        mimeMessage.setRecipients(Message.RecipientType.CC, iAdressArray);

        Transport transport = mailSession.getTransport("smtps");
        transport.connect(HOST, PORT, USER, PASSWORD);

        transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
        transport.close();

    }

    private MailService() {
        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", HOST);
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtp.from", FROM);
        props.put("mail.smtps.quitwait", "false");

        mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
    }

    private static MailService theService = null;

    private static Session mailSession;

    private static final String HOST = "smtp.gmail.com";
    private static final int PORT = 465;
    private static final String USER = "sistemagestiondepedidos@gmail.com";     // Must be valid user in d.umn.edu domain, e.g. "smit0012"
    private static final String PASSWORD = "sistemasadsi"; // Must be valid password for smit0012
    private static final String FROM = "SIGPE <sistemagestiondepedidos@gmail.com>";     // Full info for user, e.g. "Fred Smith <smit0012@d.umn.edu>"
}
