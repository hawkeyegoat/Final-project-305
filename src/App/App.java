package App;

import Model.Craps;
import View.DrawDice;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final DrawDice mainPanel =
                        new DrawDice();
                Craps.getCrapsInstance().addPropertyChangeListener(mainPanel);
                final JFrame window = new JFrame("The game of craps");
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setContentPane(mainPanel);
                window.pack();
                window.setVisible(true);
            }
        });
       // Dice dice1 = new Dice(1, 6);
       // Dice dice2 = new Dice(0, 6);
        //int sum = dice1.sum(dice2);*/
        /*for (int i = 0; i < 5; i++) {
            System.out.println(dice1.getMyValue());
            dice1.rollDice();
            System.out.println(dice1.getMyValue());
            System.out.println();
        }*/
        //System.out.println(dice1.checkWin(dice2));
    //dice1.checkWin(dice2);
        //DrawDice.createGUI();
        //System.out.println(dice1.toString());
        //System.out.println(sum
        //DrawDice.paint();
        //draw(Rectangle2D);
    }
}
