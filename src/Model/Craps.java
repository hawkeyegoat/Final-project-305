/*
 * TCSS 305 - Final Project: Craps
 */
package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;

/**
 * Craps Class. Stores all of our model data and creates the game.
 * @author Logan Atkinson
 * @version 3/15/23
 */
public class Craps {
    /**
     * Property change support.
     */
    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    /**
     * String to store the winner of the previous game.
     */
    private String winner;
    //private int mySum;
    /**
     * Int to store how many times the player has won.
     */
    private int myPlayerWins;
    /**
     * Int to store how many times the house has won.
     */
    private int myHouseWins;
    /**
     * Int to store the players point they have to re-roll.
     */
    private int myPoint;
    /**
     * Boolean to store whether the game is currently active.
     */
    private boolean myGameActive;
    /**
     * Used to generate a random for the dice.
     */
    private final Random rand = new Random();
    //private static Dice myInstance = new Dice(1,6);
    //private boolean myGameWon;
    /**
     * Int to store the bottom value for the dice.
     */
    private final int myFloor = 1;
    /**
     * Int to store the highest value for the dice.
     */
    private final int myCeiling = 6;

    /**
     * Int to store the value of dice 1.
     */
    private int myDice1; //= rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    /**
     * Int to store the value of dice 2.
     */
    private int myDice2; //= rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    /**
     * Int to store the money of the player.
     */
    private int myBankAmount;
    /**
     * Int amount to store how much the player has bet.
     */
    private int myBetAmount;
    private Boolean myGameWon;
    public static Craps myInstance = new Craps();

    /**
     * Constructor.
     */
    public Craps() {
        startGame();
        //Random rand = new Random();

        //myValue = rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    }

    /**
     * Resets all values.
     */
    public void reset() {
        setMyBetAmount(0);
        setMyBankAmount(0);
        myHouseWins = 0;
        myPlayerWins = 0;
        myDice1 = 0;
        myDice2 = 0;
        startGame();
    }

    /**
     * Gets the only instance of the game.
     * @return The instance of the game
     */
    public static Craps getCrapsInstance() {
        return myInstance;
    }

    /**
     * Gets whether the game is active.
     * @return True if the game is active, false if it is not.
     */
    public boolean getGameActive() {
        return myGameActive;
    }

    /**
     * Gets the winner of the last game.
     * @return string value of the winner.
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Gets how many wins the player has.
     * @return the amount of wins for the player
     */
    public int getPlayerWins() {
        return myPlayerWins;
    }

    /**
     * Gets how many wins the house has.
     * @return the amount of wins for the house.
     */
    public int getHouseWins() {
        return myHouseWins;
    }

    /**
     * gets the value for dice 1.
     * @return the value of dice 1
     */
    public int getDice1() { //changed to int from Dice , refactoring class to Craps
        return myDice1;
    }

    /**
     * gets the value for dice 2.
     * @return the value of dice 2
     */
    public int getDice2() {
        return myDice2;
    }

    /**
     * Gets how much money the player has.
     * @return the players money
     */
    public int getMyBankAmount() {
        return myBankAmount;
    }

    /**
     * Gets how much the player has bet.
     * @return the players bet
     */
    public int getMyBetAmount() {
        return myBetAmount;
    }

    /**
     * Sets the point the player needs to re-roll.
     * @param thePoint the point to re-roll
     */
    public void setPoint(final int thePoint) {
        myPoint = thePoint;
    }

    /**
     * Decrements the players' money.
     * @param theDecrementAmount the amount to decrement
     */
    public void decrementMyBankAmount(final int theDecrementAmount) {
        myBankAmount = myBankAmount - theDecrementAmount;
    }

    /**
     * Sets how much the player is betting.
     * @param theAmount the amount to bet.
     */
    public void setMyBetAmount(final int theAmount) {
        myBetAmount = theAmount;
    }

    /**
     * Sets how much money the player has.
     * @param theAmount the amount of money to set it to
     */
    public void setMyBankAmount(final int theAmount) {
        myBankAmount = theAmount;
        System.out.println(theAmount);
    }

    /**
     * Sets whether the game is active.
     * @param value true if it is active, false if it is not
     */
    public void setGameActive(boolean value) {
        myGameActive = value;
        if (!myGameActive) {
            changes.firePropertyChange("active",null,false);
        }
    }

    /**
     * Sets whether the player has won.
     * @param value true if the player has won, false if not
     */
    public void setGameWon(boolean value) {
        myGameWon = value;
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        setGameActive(true);
        setGameWon(false);
        setPoint(0);
    }

    /**
     * To String.
     * @return returns the String value of our game.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Floor: ");
        sb.append(myFloor);
        sb.append("Ceiling: ");
        sb.append(myCeiling);
        return sb.toString();
    }

    /**
     * Adds our dice together for the sum.
     * @return the sum
     */
    public int sum() {
        return myDice1 + myDice2;
    }
    //int dice2 = rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    //Dice dice1 = new Dice(1,6);
    //Dice dice2 = new Dice(1,6);

    /**
     * Rolls our dice and checks to see if someone won.
     */
    public void rollDice() {
        if (myGameActive) {
            myDice1 = (rand.nextInt((myCeiling - myFloor) + 1) + myFloor);
            myDice2 = (rand.nextInt((myCeiling - myFloor) + 1) + myFloor);
        }
            if (myPoint == 0) {
                if(sum() == 2 || sum() == 3 || sum() == 12) {
                    winner = "House";
                    incrementHouseWins();
                    setGameWon(false);
                    setGameActive(false);
                }
                else if (sum() == 7 || sum() == 11) {
                    winner = "Player";
                    incrementPlayerWins();
                    setGameWon(true);
                    setGameActive(false);
                }
                else {
                    setPoint(sum());
                }
            }
            else {
                if (sum() == myPoint) {
                    winner = "Player";
                    incrementPlayerWins();
                    setGameWon(true);
                    setGameActive(false);
                }
                else if (sum() == 7) {
                    winner = "House";
                    incrementHouseWins();
                    setGameWon(false);
                    setGameActive(false);
                }
            }
    }

    /**
     * Gets the point the player needs to re-roll.
     * @return returns the point
     */
    public int getMyPoint() {
        return myPoint;
    }

    /**
     * Increments the players' wins by 1.
     */
    public void incrementPlayerWins() {
        myPlayerWins++;
    }

    /**
     * Increments the houses' wins by 1.
     */
    public void incrementHouseWins() {
        myHouseWins++;
    }

    /**
     * Adds property change listener.
     * @param l the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {changes.addPropertyChangeListener(l);}
}
