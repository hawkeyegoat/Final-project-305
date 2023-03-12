package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Random;
public class Craps {
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private String winner;
    private int mySum;
    private int myPlayerWins;
    private int myHouseWins;
    private int myPoint;
    private boolean myGameActive;
    private final Random rand = new Random();
    //private static Dice myInstance = new Dice(1,6);
    private boolean myGameWon;
    private int myFloor = 1;
    private int myCeiling = 6;
    private int myValue;
    private int myDice1; //= rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    private int myDice2; //= rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    public static Craps myInstance = new Craps();
   // private Dice dice1 = new Dice(1, 6);
   // private Dice dice2 = new Dice(1,6);
    public Craps() {
        startGame();
        //Random rand = new Random();

        //myValue = rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    }
    public static Craps getCrapsInstance() {
        return myInstance;
    }
    public int getMyFloor() {
        return myFloor;
    }

    public int getMyCeiling() {
        return myCeiling;
    }

    public int getMyValue() {
        return myValue;
    }
   /* public void general(Dice input) {
        //if(inpu)
    }*/
    public boolean getGameActive() {
        return myGameActive;
    }
    public String getWinner() {
        return winner;
    }
    public int getPlayerWins() {
        return myPlayerWins;
    }
    public int getHouseWins() {
        return myHouseWins;
    }
    public int getDice1() { //changed to int from Dice , refactoring class to Craps
        return myDice1;
    }
    public int getDice2() {
        return myDice2;
    }
    public void setValue(final int theValue) {
        myValue = theValue;
    }
    public void setPoint(final int thePoint) {
        myPoint = thePoint;
    }
    public void setMySum(final int theSum) {
    mySum = theSum;
    }

    public void setGameActive(boolean value) {
        myGameActive = value;
        if (!myGameActive) {
            changes.firePropertyChange("active",null,false);
        }
    }
    public void setGameWon(boolean value) {
        myGameWon = value;
    }
    public void startGame() {
        setGameActive(true);
        setGameWon(false);
        setPoint(0);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Floor: ");
        sb.append(myFloor);
        sb.append("Ceiling: ");
        sb.append(myCeiling);
        sb.append("Value: ");
        sb.append(myValue);
        return sb.toString();
    }

    public int sum() {
        return myDice1 + myDice2;
    }
    //int dice2 = rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    //Dice dice1 = new Dice(1,6);
    //Dice dice2 = new Dice(1,6);
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
    /*public static int randomSum() {
        Craps dice1 = new Craps(1,6);
        Craps dice2 = new Craps(1,6);
        return dice1.sum(dice2);
    }*/
    public int getMyPoint() {
        return myPoint;
    }
    public void incrementPlayerWins() {
        myPlayerWins++;
    }
    public void incrementHouseWins() {
        myHouseWins++;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {changes.addPropertyChangeListener(l);}
    /**
     * Returns 1 for player win, 2 for house win, 3 for continue
     * @param
     * @return
     */
    /*public void checkWin() { //set to void from int, trying recursion
        //int sum = this.sum(dice2);

        System.out.println(sum);
        if (myPoint == 0) {
            if (sum == 7 || sum == 11) {
                incrementPlayerWins();
                return;
                //return 1;
            }
            if (sum == 2 || sum == 3 || sum == 12) {
                incrementHouseWins();;
                return;
            } else {
                setPoint(sum);
                this.rollDice();
                dice2.rollDice();
                checkWin(dice2);
                //return 3;
            }
        } else {
            if (sum == myPoint) {
                myPoint = 0;
                //return 3;
                this.rollDice();
                dice2.rollDice();
                checkWin(dice2);
            } else if (sum == 7) {
                incrementHouseWins();
                return;
            }
            else {
                this.rollDice();
                dice2.rollDice();
                checkWin(dice2);
            }
        }
        //return 3;
    }*/
    /*public void general (final Dice dice1, final Dice dice2) {
        if(checkWin(dice1.sum(dice2)) == 1) {
            incrementPlayerWins();
            //main method
        }
        if(checkWin(dice1.sum(dice2)) == 2) {
            incrementHouseWins();
            //end game
        }
        else {
            //point = dice1.sum(dice2);

        }
    }*/
    //public int generateSum () {
        //return dice1 + dice2;
    //}
    //public static int sum = generateSum();
}
