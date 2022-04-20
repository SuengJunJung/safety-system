import exceptions.InvalidEmails;
import responses.ResponseKind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
Name(s): Arvinder Dhanoa, Suengjun Jung
Assignment: Group Project
Goals: Create a GUI interface that is used to send emails (and potentially other responses) to the appropriate
people.
Inputs: Emails, content.
Outputs: Email.
 */
@SuppressWarnings("FieldCanBeLocal")
public class SafetySystemGui extends JFrame implements ActionListener {
    private final JButton send = new JButton("SEND");
    private final JTextArea content = new JTextArea();
    private final ArrayList<ResponseKind> responses = new ArrayList<>();
    private final JButton emailBtn = new JButton();
    private final EmailSettings emailSettings = new EmailSettings();


    public SafetySystemGui() {
        this.setLayout(new FlowLayout());  //added

        this.add(emailBtn);
        emailBtn.addActionListener(this);


        this.add(send);
        send.addActionListener(this);

        content.setMinimumSize(new Dimension(300, 300));
        content.setPreferredSize(new Dimension(500, 500));
        this.add(content);

        this.setSize(1920, 1080);
        this.setVisible(true);
    }
    //------------------------------
    //Displays an error to the user.
    //------------------------------
    static void errPrompt(String err) {
        JOptionPane.showMessageDialog(new JFrame(), err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == emailBtn) {
            this.emailSettings.setVisible(true);
        }
        if (e.getSource() == send) {
            try {
                this.responses.addAll(this.emailSettings.getRequests());
            } catch (InvalidEmails err) {
                errPrompt(err.getMessage());
                return;
            }
            for (var item : this.responses) {
                try {
                    item.setContent(content.getText());
                    item.send();
                } catch (Exception err) {
                    errPrompt(err.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}