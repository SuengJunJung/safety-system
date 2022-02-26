import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafetySystemGui extends JFrame implements ActionListener
{
    private JLabel emailLabel = new JLabel("Email");
    private JTextField emailPrompt= new JTextField(10);
    private JButton send = new JButton("SEND");
    private JButton inputEmail = new JButton("Input Email");
    private JPanel emailLayout = new JPanel(new BorderLayout());


    public SafetySystemGui()
    {
        emailLayout.setMaximumSize(new Dimension(10000, 100000));
        emailLayout.setMinimumSize(new Dimension(400, 400));
        setLayout(new FlowLayout());  //added
        emailLayout.add(inputEmail, BorderLayout.SOUTH );
        emailLayout.add(emailLabel, BorderLayout.NORTH );
        emailLayout.add(emailPrompt, BorderLayout.CENTER );



        add(emailLayout);
        emailPrompt.addActionListener( this );
        inputEmail.addActionListener(this);


        add(send);
        send.addActionListener( this );

        setSize(1920,1080);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == inputEmail) {
            System.out.println("ran!");
            emailPrompt.setText("");
        }
    }
}