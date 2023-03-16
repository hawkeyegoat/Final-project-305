/*
 * TCSS 305 - Final Project: Craps
 */
package Controller;

import Model.Craps;
import View.DrawDice;

import java.awt.*;

/**
 * Creates and runs our craps game and gui.
 * @author Logan Atkinson
 * @version 3/15/23
 */
public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                final DrawDice mainPanel =
                        new DrawDice();
                Craps.getCrapsInstance().addPropertyChangeListener(mainPanel);

            }
        });
    }
}
