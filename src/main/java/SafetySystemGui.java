import exceptions.InvalidEmails;
import responses.ResponseKind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class SafetySystemGui extends JFrame implements ActionListener {
    private final JButton send = new JButton("SEND");
    private final JTextArea contentTemplate = new JTextArea();
    private final JTextArea variables = new JTextArea();
    private final ArrayList<ResponseKind> responses = new ArrayList<>();
    private final JButton emailBtn = new JButton();
    private final EmailSettings emailSettings = new EmailSettings();


    public SafetySystemGui() {
        this.setLayout(new FlowLayout());  //added

        this.add(emailBtn);
        emailBtn.addActionListener(this);


        this.add(send);
        send.addActionListener(this);

        contentTemplate.setMinimumSize(new Dimension(300, 300));
        contentTemplate.setPreferredSize(new Dimension(500, 500));
        this.add(contentTemplate);

        variables.setPreferredSize(new Dimension(200, 200));
        this.add(variables);

        this.setSize(1920, 1080);
        this.setVisible(true);
    }

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
                    item.setContent(contentTemplate.getText());
                    item.send();
                } catch (Exception err) {
                    errPrompt(err.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}