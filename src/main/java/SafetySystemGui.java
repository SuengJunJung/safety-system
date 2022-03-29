import responses.ResponseKind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SafetySystemGui extends JFrame implements ActionListener
{
    private JLabel emailLabel = new JLabel("responses.Email");
    private JTextField emailPrompt= new JTextField(10);
    private JButton send = new JButton("SEND");
    private JButton inputEmailBtn = new JButton("Input responses.Email");
    private JPanel emailLayout = new JPanel(new BorderLayout());
    private JTextArea contentTemplate = new JTextArea();
    private JTextArea variables = new JTextArea();
    private ArrayList<ResponseKind> responses;


    public SafetySystemGui()
    {
        emailLayout.setMinimumSize(new Dimension(400, 400));
        setLayout(new FlowLayout());  //added
        emailLayout.add(inputEmailBtn, BorderLayout.SOUTH );
        emailLayout.add(emailLabel, BorderLayout.NORTH );
        emailLayout.add(emailPrompt, BorderLayout.CENTER );


        add(emailLayout);
        emailPrompt.addActionListener( this );
        inputEmailBtn.addActionListener(this);


        add(send);
        send.addActionListener( this );

        contentTemplate.setMinimumSize(new Dimension(300, 300));
        contentTemplate.setPreferredSize(new Dimension(500, 500));
        add(contentTemplate);

        variables.setPreferredSize(new Dimension(200, 200));
        add(variables);

        setSize(1920,1080);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == inputEmailBtn) {
            System.out.println("ran!");
            emailPrompt.setText("");
        }
    }
}