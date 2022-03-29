import responses.Email;
import responses.ResponseKind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class SafetySystemGui extends JFrame implements ActionListener {
    private final JLabel emailLabel = new JLabel("responses.Email");
    private final JTextField emailPrompt = new JTextField(10);
    private final JButton send = new JButton("SEND");
    private final JButton inputEmailBtn = new JButton("Input responses.Email");
    private final JPanel emailLayout = new JPanel(new BorderLayout());
    private final JTextArea contentTemplate = new JTextArea();
    private final JTextArea variables = new JTextArea();
    private final ArrayList<ResponseKind> responses = new ArrayList<>();


    public SafetySystemGui() {
        emailLayout.setMinimumSize(new Dimension(400, 400));
        this.setLayout(new FlowLayout());  //added
        emailLayout.add(inputEmailBtn, BorderLayout.SOUTH);
        emailLayout.add(emailLabel, BorderLayout.NORTH);
        emailLayout.add(emailPrompt, BorderLayout.CENTER);


        this.add(emailLayout);
        emailPrompt.addActionListener(this);
        inputEmailBtn.addActionListener(this);


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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputEmailBtn) {
            var email = emailPrompt.getText();
            if (!email.matches("^(.+)@(.+)$")) {
                JOptionPane.showMessageDialog(new JFrame(),
                email + " is not a valid email.",
                "ERROR",
                JOptionPane.ERROR_MESSAGE
                );
                this.responses.add(new Email(emailPrompt.getText()));
                return;
            }
            emailPrompt.setText("");
        }
    }
}