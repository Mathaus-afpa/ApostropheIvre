package apostropheivre.services;


import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

import static com.mysql.cj.Session.*;

public class EmailService {

    private static final String SMTP_HOST = "smtp.gmail.com"; // serveur smtp
    private static final String SMTP_PORT = "587";
    private static final String FROM_EMAIL = "apostropheivre.staff@gmail.com"; // email expediteur
    private static final String FROM_PASSWORD = "vatyhqgqghlvnopr"; // mot de passe d'application

    public static void envoyerEmailSimple(String destinataire) throws MessagingException {
        // Configuration des propriétés SMTP
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        // Authentification avec ton compte email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });


        // Création du message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL)); // Adresse de l'expéditeur
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire)); // Destinataire
        message.setSubject("Test d'envoi d'email"); // Sujet
        message.setText("Ceci est un test d'envoi d'email simple."); // Corps du message

        // Envoi de l'email
        Transport.send(message);

        System.out.println("E-mail envoyé avec succès à " + destinataire);
    }
}

