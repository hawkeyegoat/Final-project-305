/*
 * TCSS 305 - Final Project: Craps
 */
package View;

import Model.Craps;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;


import static Model.Craps.getCrapsInstance;
import static Model.Craps.myInstance;

/**
 * Has our view code to create our gui.
 * @author Logan Atkinson
 * @version 3/15/23
 */

public class DrawDice extends JPanel implements PropertyChangeListener {
    /**
     * The button to start the game.
     */
    private final JButton myStart;
    /**
     * JLabel to show the players point.
     */
    private final JLabel myPoint;
    /**
     * JButton to roll the dice.
     */
    private final JButton myRoll;
    /**
     * JLabel to show Dice1.
     */
    private final JLabel myDice1;
    /**
     * JLabel to show Dice2.
     */
    private final JLabel myDice2;
    //private JLabel myPoint;
    /**
     * JLabel to show the sum of Dice1 and Dice2.
     */
    private final JLabel myTotal;
    /**
     * JLabel to show the houses' wins.
     */
    private final JLabel myHouseWins;
    /**
     * JLabel to show the players' wins.
     */
    private final JLabel myPlayerWins;
    /**
     * JTextfield to get and show the players bank amount.
     */
    private final JTextField myBanksAmount;
    /**
     * JButton to submit players bank amount.
     */
    private final JButton myBankButton;
    /**
     * JTextField to get and show the bet amount.
     */
    private final JTextField myBetAmount;
    /**
     * JButton to increment the players bet by 1.
     */
    private final JButton myIncrement1;
    /**
     * JButton to increment the players bet by 5.
     */
    private final JButton myIncrement5;
    /**
     * JButton to increment the players bet by 10.
     */
    private final JButton myIncrement10;
    /**
     * JButton to increment the players bet by 50.
     */
    private final JButton myIncrement50;
    /**
     * JButton to increment the players bet by 100.
     */
    private final JButton myIncrement100;
    /**
     * JButton to increment the players bet by 500.
     */
    private final JButton myIncrement500;
    /**
     * JMenu to hold game options.
     */
    private final JMenu gameMenu;
    /**
     * JMenuItem to start the game.
     */
    private final JMenuItem Start;
    /**
     * JMenuItem to reset the game.
     */
    private final JMenuItem myReset;
    /**
     * JMenuItem to exit the game.
     */
    private final JMenuItem myExit;
    /**
     * JMenu to hold the help options.
     */
    private final JMenu helpMenu;
    /**
     * JMenuItem to show details about the program.
     */
    private final JMenuItem myAbout;
    /**
     * JMenuItem to show the game rules.
     */
    private final JMenuItem myRules;
    /**
     * JMenuBar to hold the game and help menus.
     */
    private final JMenuBar menuB;
    /**
     * JFrame to hold everything.
     */
    private final JFrame window;
    /**
     * Constant to store warning for betting too much.
     */
    private final static String debtWarning = "Please do not bet more than you can afford!";
    /**
     * Constant for new lines.
     */
    private final static String NEWLINE = System.lineSeparator();
    /**
     * Constant to show the rules.
     */
    private final static String RULE_SET = "For the game of Craps, you have two die. On each roll, your score " + NEWLINE +
            "is the sum of your two die. If the score on the first roll is a 7 or 11 then you win." + NEWLINE + "However, if your score " +
            "is 2, 3, or 12 then the house wins. If the score is neither of these, then you will have to roll until you " +  NEWLINE +
            "re-roll your original score to win. However, if you roll a 7 before rolling your original value, then the house wins.";
    /**
     * Constant to show everything about the program.
     */
    private final static String ABOUT = "Author: Logan Atkinson" + NEWLINE + "App version: 1.1" + NEWLINE
        + "Java Version: 19" + NEWLINE + "Play testers: Mom and Dad";
    //Clip sound; Not being used, saving it to show my idea for the audio.

    /**
     * Constructor.
     */
    public DrawDice() {
        window = new JFrame("The game of craps");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(this);
        window.pack();
        window.setVisible(true);

        GridLayout theLayout = new GridLayout(2, 4);
        setLayout(theLayout);
        menuB = new JMenuBar();
        gameMenu = new JMenu("Game");
        Start = new JMenuItem("Start");
        Start.setMnemonic(KeyEvent.VK_S);
        Start.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  ActionEvent.CTRL_MASK));
        myReset = new JMenuItem("Reset");
        myReset.setMnemonic(KeyEvent.VK_D);
        myReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,  ActionEvent.CTRL_MASK));
        myExit = new JMenuItem("Exit");
        myExit.setMnemonic(KeyEvent.VK_E);
        myExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,  ActionEvent.CTRL_MASK));
        gameMenu.add(Start);
        gameMenu.add(myReset);
        gameMenu.add(myExit);

        helpMenu = new JMenu("Help");
        myAbout = new JMenuItem("About");
        myAbout.setMnemonic(KeyEvent.VK_A);
        myAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,  ActionEvent.CTRL_MASK));
        myRules = new JMenuItem("Rules");
        myRules.setMnemonic(KeyEvent.VK_R);
        myRules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,  ActionEvent.CTRL_MASK));
        helpMenu.add(myAbout);
        helpMenu.add(myRules);

        menuB.add(gameMenu);
        menuB.add(helpMenu);
        window.setJMenuBar(menuB);

        myStart = new JButton("Start Game");
        myStart.setMnemonic(KeyEvent.VK_SPACE);
        myRoll = new JButton("Roll Dice");

        myRoll.setMnemonic(KeyEvent.VK_SPACE); //sets shortcut

        myDice1 = new JLabel("Dice 1", resizeIcon("Icons//Dice1.png"), JLabel.LEFT); //used stack overflow to figure out how to resize an icon

        myDice2 = new JLabel("Dice 2", resizeIcon("Icons//Dice1.png"), JLabel.LEFT);

        myTotal = new JLabel("Total");
        myPoint = new JLabel("Point");
        myHouseWins = new JLabel("House Wins: 0");
        myPlayerWins = new JLabel("Player Wins: 0");
        add(myStart);
        add(myRoll);
        myRoll.setEnabled(false);
        add(myDice1);
        add(myDice2);
        add(myTotal);
        add(myPoint);
        add(myHouseWins);
        add(myPlayerWins);

        JPanel myBank = new JPanel();
        myBanksAmount = new JTextField("Enter Bank here");
        myBanksAmount.setEditable(true);
        myBanksAmount.setColumns(10);
        myBankButton = new JButton("Set Bank");
        myBank.add(myBanksAmount);
        myBank.add(myBankButton);
        add(myBank);

        JPanel myBets = new JPanel();
        myBetAmount = new JTextField("0", 10);
        myBetAmount.setEditable(true);
        myIncrement1 = new JButton("+$1");
        myIncrement5 = new JButton("+$5");
        myIncrement10 = new JButton("+$10");
        myIncrement50 = new JButton("+$50");
        myIncrement100 = new JButton("+$100");
        myIncrement500 = new JButton("+$500");
        myBets.add(myBetAmount);
        myBets.add(myIncrement1);
        myBets.add(myIncrement5);
        myBets.add(myIncrement10);
        myBets.add(myIncrement50);
        myBets.add(myIncrement100);
        myBets.add(myIncrement500);
        add(myBets);
        setVisible(true);

        addListeners();

            }

    /**
     * Method to add all the listeners.
     */

    public void addListeners() {
                myStart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (myInstance.getMyBankAmount() == 0) {
                            JOptionPane.showMessageDialog(null,"Please set your bank account before playing!");
                        }
                        else if (myInstance.getMyBankAmount() < 0) {
                            JOptionPane.showMessageDialog(null, "Your bank account cannot be negative!");
                        }
                        else {
                            disableBetButtons();
                            getCrapsInstance().startGame();
                            myDice1.setText("Dice 1: ");
                            myDice2.setText("Dice 2: ");
                            myTotal.setText("total");
                            myPoint.setText("Point: ");
                            myRoll.setEnabled(true);
                            myStart.setEnabled(false);
                        }
                    }
                });
                myRoll.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Model.Craps craps1 = getCrapsInstance();
                        if(craps1.getGameActive()) {
                            craps1.rollDice();
                            myDice1.setText("Dice 1: " + craps1.getDice1());
                            myDice1.setIcon(resizeIcon("Icons//Dice" + craps1.getDice1() +".png"));
                            myDice2.setText("Dice 2: " + craps1.getDice2());
                            myDice2.setIcon(resizeIcon("Icons//Dice" + craps1.getDice2() + ".png"));
                            myTotal.setText("Total: " + craps1.sum());

                            if(myPoint.getText().equals("Point: ")) {
                                myPoint.setText("Point: " + craps1.getMyPoint());
                            }
                            if (!craps1.getGameActive()) {
                                JOptionPane.showMessageDialog(null,"Winner: " + craps1.getWinner());
                                myPlayerWins.setText("Player Wins: " + craps1.getPlayerWins());
                                myHouseWins.setText("House Wins: " + craps1.getHouseWins());
                                if(craps1.getWinner().equals("Player")) {
                                    craps1.setMyBankAmount(2 * craps1.getMyBetAmount() + craps1.getMyBankAmount());
                                    myBanksAmount.setText(String.valueOf(craps1.getMyBankAmount()));
                                    craps1.setMyBetAmount(0);
                                    myBetAmount.setText("0");
                                }
                                else {
                                    craps1.setMyBetAmount(0);
                                    myBetAmount.setText("0");
                                }
                            }
                        }
                    }
                });
                //Empties textbox when clicked
                myBanksAmount.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        myBanksAmount.setText("");
                    }
                });
                myBankButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBanksAmount.getText();
                        try {
                            int moneyTemp = Integer.parseInt(money);
                            if (moneyTemp == 0) {
                                JOptionPane.showMessageDialog(null, "Your bank account cannot be zero");
                            }
                            else if (moneyTemp < 0) {
                                JOptionPane.showMessageDialog(null, "Your Bank account cannot be negative");
                            }
                            else {
                                Craps.myInstance.setMyBankAmount(moneyTemp);
                                myBanksAmount.setEditable(false);
                                myBankButton.setEnabled(false);
                            }
                            //System.out.println(money);
                        } catch (final NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                        } catch (final Exception er) {
                            JOptionPane.showMessageDialog(null, "Error");
                        }
                    }
                });
                myBetAmount.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        try {
                            Craps.myInstance.setMyBetAmount(Integer.parseInt(money));
                        }
                        catch (final NumberFormatException numberFormatException) {
                            JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                        }
                        catch (final Exception exe) {
                            JOptionPane.showMessageDialog(null,"Error");
                        }
                    }
                });
                myIncrement1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        int money1 = Integer.parseInt(money);
                        money1 += 1;
                        if (myInstance.getMyBankAmount() - 1 < 0) {
                            JOptionPane.showMessageDialog(null, debtWarning);

                        }
                        else {
                            myInstance.setMyBetAmount(money1);
                            myBetAmount.setText(String.valueOf(money1));
                            myInstance.decrementMyBankAmount(1);
                            myBanksAmount.setText(String.valueOf(myInstance.getMyBankAmount()));
                        }
                    }
                });
                myIncrement5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        int money1 = Integer.parseInt(money);
                        money1 += 5;
                        if (myInstance.getMyBankAmount() - 5 < 0) {
                            JOptionPane.showMessageDialog(null, debtWarning);

                        }
                        else {
                            myInstance.setMyBetAmount(money1);
                            myBetAmount.setText(String.valueOf(money1));
                            myInstance.decrementMyBankAmount(5);
                            myBanksAmount.setText(String.valueOf(myInstance.getMyBankAmount()));
                        }
                        //updateUI();
                    }
                });
                myIncrement10.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        int money1 = Integer.parseInt(money);
                        money1 += 10;

                        //myInstance.decrementMyBankAmount(10);
                        if (myInstance.getMyBankAmount() - 10 < 0) {
                            JOptionPane.showMessageDialog(null, debtWarning);

                        }
                        else {
                            myInstance.setMyBetAmount(money1);
                            myBetAmount.setText(String.valueOf(money1));
                            myInstance.decrementMyBankAmount(10);
                            myBanksAmount.setText(String.valueOf(myInstance.getMyBankAmount()));
                        }
                    }
                });
                myIncrement50.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        int money1 = Integer.parseInt(money);
                        money1 += 50;
                        myInstance.setMyBetAmount(money1);
                        myBetAmount.setText(String.valueOf(money1));

                        myInstance.decrementMyBankAmount(50);
                        if (myInstance.getMyBankAmount() - 50 < 0) {
                            JOptionPane.showMessageDialog(null, debtWarning);

                        }
                        else {
                            myInstance.setMyBetAmount(money1);
                            myBetAmount.setText(String.valueOf(money1));
                            myInstance.decrementMyBankAmount(50);
                            myBanksAmount.setText(String.valueOf(myInstance.getMyBankAmount()));
                        }
                    }
                });
                myIncrement100.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        int money1 = Integer.parseInt(money);
                        money1 += 100;

                        myInstance.decrementMyBankAmount(100);
                        if (myInstance.getMyBankAmount() - 100 < 0) {
                            JOptionPane.showMessageDialog(null, debtWarning);

                        }
                        else {
                            myInstance.setMyBetAmount(money1);
                            myBetAmount.setText(String.valueOf(money1));
                            myInstance.decrementMyBankAmount(100);
                            myBanksAmount.setText(String.valueOf(myInstance.getMyBankAmount()));
                        }
                    }
                });
                myIncrement500.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        final String money = myBetAmount.getText();
                        int money1 = Integer.parseInt(money);
                        money1 += 500;
                        //myInstance.setMyBetAmount(money1);
                        //myBetAmount.setText(String.valueOf(money1));

                        //myInstance.decrementMyBankAmount(500);
                        if (myInstance.getMyBankAmount() - 500 < 0) {
                            JOptionPane.showMessageDialog(null, debtWarning);
                            //myInstance.decrementMyBankAmount(-500);

                        }
                        else {
                            myInstance.setMyBetAmount(money1);
                            myBetAmount.setText(String.valueOf(money1));
                            myInstance.decrementMyBankAmount(500);
                            myBanksAmount.setText(String.valueOf(myInstance.getMyBankAmount()));


                            //myInstance.decrementMyBankAmount(500);
                        }
                    }
                });
                Start.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (myInstance.getMyBankAmount() == 0) {
                            JOptionPane.showMessageDialog(null,"Please set your bank account before playing!");
                        }
                        else if (myInstance.getMyBankAmount() < 0) {
                            JOptionPane.showMessageDialog(null, "Your bank account cannot be negative!");
                        }
                        else {
                            disableBetButtons();
                            getCrapsInstance().startGame();
                            myDice1.setText("Dice 1: "); //+ getCrapsInstance().getDice1());
                            myDice2.setText("Dice 2: "); //+ getCrapsInstance().getDice1());
                            myTotal.setText("total");
                            myPoint.setText("Point");
                            myRoll.setEnabled(true);
                            myStart.setEnabled(false);
                        }
                    }
                });
                myReset.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myInstance.reset();
                    }
                });
                myExit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to quit the game?",
                                "Exit", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            //exit
                            window.dispose();
                        }
                    }
                });
                myRules.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, RULE_SET);
                    }
                });
                myAbout.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, ABOUT);
                    }
                });
            }
            //could not get this to work
            public void setRollSound(String file) {
                Clip sound;
                try {
                    File f = new File(file);
                    AudioInputStream audio = AudioSystem.getAudioInputStream(f);
                    sound = AudioSystem.getClip();
                    sound.open(audio);
                    sound.start();
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error"); //Too many errors to get more specific
                }
                //sound.start();
            }
            /*public void playRollSound() {
                sound.setFramePosition(0);
                sound.start();
            }*/

    /**
     * Method to get receive property changes.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("active")) {
            if (!(boolean)evt.getNewValue()) {
                myRoll.setEnabled(false);
                myStart.setEnabled(true);
                enableBetButtons();
            }
        }
    }

    /**
     * Gets and resizes the given icon.
     * Warning: I did write the original get icon method, however spent a solid hour trying to
     * resize the image to no avail. So here is the source for the fix, https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
     * @param fileName The image to resize
     * @return The image icon to use
     */
    public ImageIcon resizeIcon(String fileName) {
        ImageIcon I = new ImageIcon(fileName);
        Image Im = I.getImage();
        Image newIm = Im.getScaledInstance(80,80, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(newIm);
        return newImageIcon;
    }

    /**
     * Disables the buttons to edit the players bet.
     */
    public void disableBetButtons() {
        myIncrement1.setEnabled(false);
        myIncrement5.setEnabled(false);
        myIncrement10.setEnabled(false);
        myIncrement50.setEnabled(false);
        myIncrement100.setEnabled(false);
        myIncrement500.setEnabled(false);
        myBetAmount.setEnabled(false);
    }

    /**
     * Enables the buttons to edit the players bet.
     */
    public void enableBetButtons() {
        myIncrement1.setEnabled(true);
        myIncrement5.setEnabled(true);
        myIncrement10.setEnabled(true);
        myIncrement50.setEnabled(true);
        myIncrement100.setEnabled(true);
        myIncrement500.setEnabled(true);
        myBetAmount.setEnabled(true);
    }
}
