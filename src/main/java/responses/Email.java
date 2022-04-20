package responses;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/*
Name(s): Arvinder Dhanoa, Suengjun Jung
Assignment: Group Project
Goals: A class that represents and sends an email.
Inputs: stuff used to make an email.
Outputs: EMAILS!!!!!
 */
public class Email implements ResponseKind {
    private static Credentials creds;
    private final String to_user;
    private String content = "";

    public Email(String text) {
        this.to_user = text;
    }

    //------------------------------
    //solves what the credentials for gmail are.
    //------------------------------
    private static Credentials getCredentials() throws IOException {
        try (var m = new BufferedReader(new FileReader("credentials.txt"))) {
            var credentials = m.lines().limit(2).toList();
            return new Credentials(credentials.get(0), credentials.get(1));
        }
    }

    //------------------------------
    //Returns a constructed MimeMessage to be sent.
    //------------------------------
    public static MimeMessage createEmail(
    String to, String from, String subject, String bodyText
    ) throws MessagingException {
        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        System.out.println(creds.username + " " + creds.password);

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(creds.username, creds.password);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);
        var m = new MimeMessage(session);
        m.setFrom(new InternetAddress(from));
        m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        m.setSubject(subject);
        m.setText(bodyText);
        return m;
    }

    @Override
    public void send() throws IOException, MessagingException {
        if (creds == null) {
            creds = getCredentials();
        }
        var mail = createEmail(to_user, creds.username(), "Automated Safety Email", content);
        Transport.send(mail);
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }


    //------------------------------
    //Class to represent credentials.
    //------------------------------
    private record Credentials(String username, String password) {}
}