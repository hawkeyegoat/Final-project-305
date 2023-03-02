package Model;

import java.awt.geom.Rectangle2D;
import java.util.Random;
public class Dice {
    private int playerWins;
    private int houseWins;
    private int myPoint;
    private final Random rand = new Random();

    private int myFloor;
    private int myCeiling;
    private int myValue;
    public Dice(final int theFloor, final int theCeiling) {
        myFloor = theFloor;
        myCeiling = theCeiling;
        //Random rand = new Random();

        myValue = rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
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
    public void setValue(final int theValue) {
        myValue = theValue;
    }
    public void setPoint(final int thePoint) {
        myPoint = thePoint;
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

    public int sum(Dice other) {
        return this.myValue + other.myValue;
    }
    //int dice2 = rand.nextInt((myCeiling - myFloor) + 1) + myFloor;
    //Dice dice1 = new Dice(1,6);
    //Dice dice2 = new Dice(1,6);
    public void rollDice() {
        this.setValue(rand.nextInt((myCeiling - myFloor) + 1) + myFloor);
    }
    public static int randomSum() {
        Dice dice1 = new Dice(1,6);
        Dice dice2 = new Dice(1,6);
        return dice1.sum(dice2);
    }
    public int getMyPoint() {
        return myPoint;
    }
    public void incrementPlayerWins() {
        playerWins++;
    }
    public void incrementHouseWins() {
        houseWins++;
    }

    /**
     * Returns 1 for player win, 2 for house win, 3 for continue
     * @param
     * @return
     */
    public void checkWin(Dice dice2) { //set to void from int, trying recursion
        int sum = this.sum(dice2);
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
    }
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
