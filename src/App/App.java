package App;

import Model.Dice;
import Model.Rules;
import View.DrawDice;
import javax.swing.text.View;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class App {
    public static void main(String[] args) {
        Dice dice1 = new Dice(1, 6);
        Dice dice2 = new Dice(0, 6);
        //int sum = dice1.sum(dice2);*/
        /*for (int i = 0; i < 5; i++) {
            System.out.println(dice1.getMyValue());
            dice1.rollDice();
            System.out.println(dice1.getMyValue());
            System.out.println();
        }*/
        //System.out.println(dice1.checkWin(dice2));
    //dice1.checkWin(dice2);
        DrawDice.createGUI();
        //System.out.println(dice1.toString());
        //System.out.println(sum
        //DrawDice.paint();
        //draw(Rectangle2D);
    }
}
