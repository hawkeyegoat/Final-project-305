package View;
import javax.swing.*;
import java.awt.*;

public class DrawDice extends JFrame {
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

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

}
