package View;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;


import Model.*;
import static Model.Craps.getCrapsInstance;


public class DrawDice extends JPanel implements PropertyChangeListener {
    private JButton myStart;
    private JButton myRoll;
    private JLabel myDice1;
    private JLabel myDice2;
    private JLabel myPoint;
    private JLabel myTotal;
    private JLabel myHouseWins;
    private JLabel myPlayerWins;
    Clip sound;
    public DrawDice() {
        //JPanel panel = new JPanel();
        //JFrame frame = new JFrame("Game of Craps");
        //frame.setContentPane(this);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //JPanel panel = new JPanel();
        //ImageIcon img = new ImageIcon("")
        GridLayout theLayout = new GridLayout(3, 2);
        setLayout(theLayout);
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
        myDice1 = new JLabel("Dice 1", resizeIcon("Icons//Dice1.png"), JLabel.LEFT); //finally got this to work now just need to resize
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
        //setLayout(theLayout);
        setVisible(true);

        addListeners();
        /*myStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myDice1.setText("Dice1" + getCrapsInstance().getDice1());
                myDice2.setText("Dice2" + getCrapsInstance().getDice1());
                myTotal.setText("total");
            }
        });*/
            }

            public void addListeners() {
                myStart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        getCrapsInstance().startGame();
                        myDice1.setText("Dice 1: "); //+ getCrapsInstance().getDice1());
                        myDice2.setText("Dice 2: "); //+ getCrapsInstance().getDice1());
                        myTotal.setText("total");
                        myPoint.setText("Point");
                        myRoll.setEnabled(true);
                        myStart.setEnabled(false);
                    }
                });
                myRoll.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Model.Craps craps1 = getCrapsInstance();
                        if(craps1.getGameActive()) {
                            //setRollSound("RollingDiceSound");
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
                            }
                        }
                    }
                });

            }
            /*public void setRollSound(String file) {
                //Clip sound;
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
            }*/
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
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g2);  // fill with background color.
        g2.setColor( Color.blue );
        //g2.drawRect(0,0,,99);
        //g2.drawRect(1,1,97,97);
        g2.fillRect(100, 100, 200, 200);
    }

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
