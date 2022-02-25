import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafetySystemGui extends JFrame implements ActionListener
{
    private JLabel prompt = new JLabel("Next Grade");
    private JTextField inField;
    private JTextArea display;
    private JButton reset, displayStats;

    public SafetySystemGui()
    {

        setLayout(new FlowLayout());  //added
        inField = new JTextField(4);
        display = new JTextArea("", 5,20);
        reset = new JButton("RESET");
        displayStats = new JButton("STATS");

        add(prompt);
        add(inField);
        inField.addActionListener( this );

        add(display);

        add(reset);
        reset.addActionListener( this );
        add(displayStats);
        displayStats.addActionListener( this );
        setSize(600,200);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {

    }
}