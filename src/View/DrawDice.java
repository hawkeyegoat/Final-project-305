package View;
import Model.Dice;
import Model.Dice.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.JComponent;
import java.beans.*;

public class DrawDice extends JPanel {//implements PropertyChangeListener {
    private JButton myStart;
    private JButton myRoll;
    private JLabel myDice1;
    private JLabel myDice2;
    private JLabel myTotal;
    public DrawDice() {
        GridLayout theLayout = new GridLayout(0, 2);
        setLayout(theLayout);
        myStart = new JButton("Start Game");
        myRoll = new JButton("Roll Dice");
        myRoll.setMnemonic('r'); //sets shortcut
        myDice1 = new JLabel("Dice 1 value");
        myDice2 = new JLabel("Dice 2 value");
        myTotal = new JLabel("Total");
        //add all to frame
        myStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //myDice1.setText("Dice1" + Dice.getDice1());
                myDice2.setText("Dice2");
                myTotal.setText("total");
            }
        });
            }

    public static void createGUI() {

       /* final DrawDice mainPanel = new DrawDice();

        final JFrame window = new JFrame("Game of Crabs");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(mainPanel);
        window.pack();
        window.setVisible(true);*/
        DrawDice panel = new DrawDice();
        panel.setBounds(0,0,500,500);

        JFrame frame = new JFrame("Game of Crabs");
        frame.setContentPane(panel);
        frame.setBounds(0, 0, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        Button roll = new Button("Roll");
        roll.setBounds(50, 200, 100, 50);
        frame.add(roll);


        JLabel point = new JLabel("Point: ");
        point.setBounds(50, 300, 100, 50);
        frame.add(point);

        JLabel pointAmount = new JLabel("10000");
        pointAmount.setBounds(50, 300, 100, 50);
        //pointAmount.setEditable(false);
        pointAmount.setBounds(500, 300, 100, 50);
        frame.add(pointAmount);

        //paintComponent(Graphics2D);
        //Display the window.


        panel.setLayout(new GridLayout(3,2));
        //frame.pack();
        frame.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);  // fill with background color.
        g2.setColor( Color.blue );
        //g2.drawRect(0,0,,99);
        //g2.drawRect(1,1,97,97);
        g2.fillRect(100, 100, 200, 200);
    }

}
