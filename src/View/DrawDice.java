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


public class DrawDice extends JPanel implements PropertyChangeListener {
    private JButton myStart;
    private JButton myRoll;
    private JLabel myDice1;
    private JLabel myDice2;
    private JLabel myPoint;
    private JLabel myTotal;
    private JLabel myHouseWins;
    private JLabel myPlayerWins;
    private JTextField myBanksAmount;
    private JButton myBankButton;
    private JTextField myBetAmount;
    private JButton myIncrement1;
    private JButton myIncrement5;
    private JButton myIncrement10;
    private JButton myIncrement50;
    private JButton myIncrement100;
    private JButton myIncrement500;
    private JMenu gameMenu;
    private JMenuItem Start;
    private JMenuItem myReset;
    private JMenuItem myExit;
    private JMenu helpMenu;
    private JMenuItem myAbout;
    private JMenuItem myRules;
    private JMenuBar menuB;
    private JFrame window;
    private final static String NEWLINE = System.lineSeparator();
    private final static String RULE_SET = "For the game of Craps, you have two die. On each roll, your score " + NEWLINE +
            "is the sum of your two die. If the score on the first roll is a 7 or 11 then you win." + NEWLINE + "However, if your " +
            "is 2, 3, or 12 then the house wins. If the score is neither of these, then you will have to roll until you " +  NEWLINE +
            "re-roll your original score to win. However, if you roll a 7 before rolling your original value, then the house wins.";
    private final static String ABOUT = "Author: Logan Atkinson" + NEWLINE + "App version: 1.1" + NEWLINE
        + "Java Version: 19";
    Clip sound;
    public DrawDice() {
        //JPanel panel = new JPanel();
        //JFrame frame = new JFrame("Game of Craps");
        //frame.setContentPane(this);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel panel = new JPanel();
        //ImageIcon img = new ImageIcon("")
        window = new JFrame("The game of craps");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(this);
        window.pack();
        window.setVisible(true);

        GridLayout theLayout = new GridLayout(4, 3);
        setLayout(theLayout);
        menuB = new JMenuBar();
        gameMenu = new JMenu("Game");
        Start = new JMenuItem("Start");
        myReset = new JMenuItem("Reset");
        myExit = new JMenuItem("Exit");
        gameMenu.add(Start);
        gameMenu.add(myReset);
        gameMenu.add(myExit);

        helpMenu = new JMenu("Help");
        myAbout = new JMenuItem("About");
        myRules = new JMenuItem("Rules");
        helpMenu.add(myAbout);
        helpMenu.add(myRules);

        menuB.add(gameMenu);
        menuB.add(helpMenu);
        window.setJMenuBar(menuB);
        //setMenuB(window);

        myStart = new JButton("Start Game");
        //myStart.setBounds(0,0,100,50);
        myRoll = new JButton("Roll Dice");
        //myRoll.setBounds(800,0,100,50);
        myRoll.setMnemonic('r'); //sets shortcut
        //ImageIcon D = new ImageIcon("Icons//Dice1.png");
       /* ImageIcon I = new ImageIcon("Icons//Dice1.png");
        Image Im = I.getImage();
        Image newIm = Im.getScaledInstance(80,80, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(newIm);*/
        myDice1 = new JLabel("Dice 1", resizeIcon("Icons//Dice1.png"), JLabel.LEFT); //used stack overflow to figure out how to resize an icon
        //myDice1.setIcon(new ImageIcon("dice1.png"));
        //myDice1.setBounds(0,500,100,50);
        myDice2 = new JLabel("Dice 2", resizeIcon("Icons//Dice1.png"), JLabel.LEFT);
        //myDice2.setBounds(0,800,100,50);
        myTotal = new JLabel("Total");
        myPoint = new JLabel("Point");
        myHouseWins = new JLabel("House Wins: 0");
        myPlayerWins = new JLabel("Player Wins: 0");
        //add all to frame
        add(myStart);
        add(myRoll);
        myRoll.setEnabled(false);
        add(myDice1);
        add(myDice2);
        add(myTotal);
        add(myHouseWins);
        add(myPlayerWins);

        JPanel myBank = new JPanel();
        myBanksAmount = new JTextField("0");
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
        myBets.add(myBetAmount); //Make it so that if myBetAmount = 0 show JOption panel, do this in myRoll button listener
        myBets.add(myIncrement1);
        myBets.add(myIncrement5);
        myBets.add(myIncrement10);
        myBets.add(myIncrement50);
        myBets.add(myIncrement100);
        myBets.add(myIncrement500);
        add(myBets);
        //setLayout(theLayout);
        setVisible(true);

        addListeners();

            }

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
                myRoll.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Model.Craps craps1 = getCrapsInstance();
                        if(craps1.getGameActive()) {
                            //setRollSound("Icons//RollingDiceSound.mp3");
                            craps1.rollDice();
                            myDice1.setText("Dice 1: " + craps1.getDice1());
                            //ImageIcon d1 = new ImageIcon("Dice"+craps1.getDice1() + "png");
                            myDice1.setIcon(resizeIcon("Icons//Dice" + craps1.getDice1() +".png")); //ITS ACTUALLY WORKING!!!
                            myDice2.setText("Dice 2: " + craps1.getDice2());
                            myDice2.setIcon(resizeIcon("Icons//Dice" + craps1.getDice2() + ".png"));
                            myTotal.setText("Total: " + craps1.sum());
                            if (craps1.getMyPoint() != 0) {
                                myPoint.setText("point: " + craps1.getMyPoint());
                            }
                            else {
                                myPoint.setText("point");
                            }
                            if (!craps1.getGameActive()) {
                                JOptionPane.showMessageDialog(null,"Winner: " + craps1.getWinner());
                                myPlayerWins.setText("Player Wins: " + craps1.getPlayerWins());
                                myHouseWins.setText("House Wins: " + craps1.getHouseWins());
                                if(craps1.getWinner() == "Player") {
                                    craps1.setMyBankAmount(2 * craps1.getMyBetAmount() + craps1.getMyBankAmount());
                                    myBanksAmount.setText(String.valueOf(craps1.getMyBankAmount()));
                                    craps1.setMyBetAmount(0);
                                    myBetAmount.setText("0");
                                }
                                else {
                                    //craps1.setMyBankAmount(craps1.getMyBankAmount() - craps1.getMyBetAmount());
                                    //myBanksAmount.setText(String.valueOf(craps1.getMyBankAmount()));
                                    craps1.setMyBetAmount(0);
                                    myBetAmount.setText("0");
                                }
                            }
                        }
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
                            JOptionPane.showMessageDialog(null, "Please do not bet more than you can afford!");

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
                            JOptionPane.showMessageDialog(null, "Please do not bet more than you can afford!");

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
                            JOptionPane.showMessageDialog(null, "Please do not bet more than you can afford!");

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
                            JOptionPane.showMessageDialog(null, "Please do not bet more than you can afford!");

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
                            JOptionPane.showMessageDialog(null, "Please do not bet more than you can afford!");

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
                            JOptionPane.showMessageDialog(null, "Please do not bet more than you can afford!");
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
                        int answer = JOptionPane.showConfirmDialog(null, "Are you sure you would like to quit the game?", "Exit", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            //exit
                            window.dispose();
                        }
                            else {
                                //do nothing
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
            public void playRollSound() {
                sound.setFramePosition(0);
                sound.start();
            }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName() == "active") {
            if ((boolean)evt.getNewValue() == false) {
                myRoll.setEnabled(false);
                myStart.setEnabled(true);
                //JOptionPane.showMessageDialog(null, "Game not active!");
            }
        }
    }
    public ImageIcon resizeIcon(String fileName) {
        ImageIcon I = new ImageIcon(fileName);
        Image Im = I.getImage();
        Image newIm = Im.getScaledInstance(80,80, Image.SCALE_SMOOTH);
        ImageIcon newImageIcon = new ImageIcon(newIm);
        return newImageIcon;
    }
    /*public void setMenuB (JFrame frame) {
        menuB = new JMenuBar();
        gameMenu = new JMenu("Game");
        Start = new JMenuItem("Start");
        myReset = new JMenuItem("Reset");
        myExit = new JMenuItem("Exit");

        gameMenu.add(Start);
        gameMenu.add(myReset);
        gameMenu.add(myExit);
        helpMenu = new JMenu("Help");
        myAbout = new JMenuItem("About");
        myRules = new JMenuItem("Rules");
        helpMenu.add(myAbout);
        helpMenu.add(myRules);

        menuB.add(gameMenu);
        menuB.add(helpMenu);

        frame.setJMenuBar(menuB);
    }*/

    /*public static void createGUI() {

        final DrawDice mainPanel = new DrawDice();

        final JFrame window = new JFrame("Game of Crabs");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(mainPanel);
        window.pack();
        window.setVisible(true);
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
    }*/
   /* @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);  // fill with background color.
        g2.setColor( Color.blue );
        //g2.drawRect(0,0,,99);
        //g2.drawRect(1,1,97,97);
        g2.fillRect(100, 100, 200, 200);
    }*/

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    //@Override
    //public void propertyChange(PropertyChangeEvent evt) {

    //}
}
