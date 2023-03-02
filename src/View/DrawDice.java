package View;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.JComponent;

public class DrawDice extends JPanel {
    public static void createGUI() {

        JFrame frame = new JFrame("Game of Crabs");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Button roll = new Button("Roll");
        roll.setBounds(50, 200, 100, 50);
        frame.add(roll);

        JLabel point = new JLabel("Point: ");
        point.setBounds(50, 300, 100, 50);
        frame.add(point);

        //JLabel pointAmount = new JLabel("10000");
        //pointAmount.setBounds(50, 300, 100, 50);
        //pointAmount.setEditable(false);
        //pointAmount.setBounds(500, 300, 100, 50);
        //frame.add(pointAmount);
        //Add the ubiquitous "Hello World" label.
        /*JLabel label = new JLabel("Dice");
        frame.getContentPane().add(label);*/
        //paintComponent(Graphics2D);
        //Display the window.
        frame.pack();
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
