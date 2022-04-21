import exceptions.InvalidEmails;
import responses.ConsoleLogger;
import responses.ResponseKind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
    private final JButton emailBtn = new JButton("Email Settings");
    private final EmailSettings emailSettings = new EmailSettings();
    private final JTextField timer = new JTextField();
    private final JLabel timerLabel = new JLabel("timer");


    public SafetySystemGui() {
        this.setLayout(new FlowLayout());  //added
        final var timerPanel = new JPanel() {{
            add(timer);
            add(timerLabel);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }};

        this.add(timerPanel);

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
            new Thread(() -> {
                var s = timer.getText();
                var unit = s.charAt(s.length() - 1);
                try {
                    int userPassedNumber = Integer.parseInt(s.substring(0, s.length() - 1));
                    int seconds = switch (unit) {
                        case 's' -> userPassedNumber;
                        case 'm' -> userPassedNumber * 60;
                        case 'h' -> userPassedNumber * 3600;
                        case 'd' -> userPassedNumber * 3600 * 24;
                        default -> throw new Exception("invalid unit: " + unit);
                    };
                    Thread.sleep(seconds * 1000L);
                } catch (NumberFormatException err) {
                    errPrompt("invalid number passed: please use 1 character for unit.");
                    return;
                } catch (Exception err) {
                    errPrompt(err.getMessage());
                    return;
                }

                try {
                    this.responses.addAll(this.emailSettings.getRequests());
                } catch (InvalidEmails err) {
                    errPrompt(err.getMessage());
                    return;
                }
                this.responses.add(new ConsoleLogger());
                for (var item : this.responses) {
                    try {
                        item.setContent(content.getText());
                        item.send();
                    } catch (Exception err) {
                        errPrompt(err.getMessage());
                        System.exit(1);
                    }
                }
            }).start();
        }
    }
}