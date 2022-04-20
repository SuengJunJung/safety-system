import exceptions.InvalidEmails;
import responses.Email;
import responses.ResponseKind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmailSettings extends JFrame implements ActionListener {
    private final JTextArea emails = new JTextArea();
    private final JButton save = new JButton();

    public EmailSettings() {
        try(var file = new BufferedReader(new FileReader("emails.txt"))) {
            String emails = file.lines().map(line -> line + "\n").collect(Collectors.joining());
            this.emails.setText(emails);
        } catch (IOException e) {
            this.emails.setText("");
        }
        this.setLayout(new FlowLayout());
        this.setSize(new Dimension(800, 800));
        emails.setPreferredSize(new Dimension(500, 500));
        save.setText("save Emails");
        this.add(emails);
        this.add(save);
        save.addActionListener(this);
    }

    static void errPrompt(String err) {
        JOptionPane.showMessageDialog(new JFrame(), err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    static void infoPrompt(String info) {
        JOptionPane.showMessageDialog(new JFrame(), info, "INFO", JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean isValidEmail(String email) {
        return email.matches("^(.+)@(.+)$");
    }

    public List<ResponseKind> getRequests() throws InvalidEmails {
        String[] content = this.emails.getText().split("\n");
        String malformedEmails = Arrays.stream(content)
                                       .filter((e) -> !isValidEmail(e))
                                       .collect(Collectors.joining());
        if (malformedEmails.length() != 0) {
            throw new InvalidEmails(malformedEmails);
        }
        return Arrays.stream(content).map((email) -> (ResponseKind) new Email(email)).toList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("emails.txt"))) {
            if (e.getSource() == save) {
                System.out.println(this.emails.getText());
                w.write(this.emails.getText());
            }
        } catch (IOException err) {
            errPrompt("Got unexpected IO error: \n" + err + "\n closing.");
            System.exit(1);
        }
        infoPrompt("saved emails successfully!");
    }
}