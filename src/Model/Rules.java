package Model;

public class Rules {
    private int playerWins;
    private int houseWins;
    private int myPoint;
    //Nulify constructor
    private Rules() {

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
     * @param sum
     * @return
     */
    public int checkWin(final int sum) {
        if (myPoint == 0) {
            if (sum == 7 || sum == 11) {
                return 1;
            }
            if (sum == 2 || sum == 3 || sum == 12) {
                return 2;
            } else {
                //setPoint(sum)
                return 3;
            }
        } else {
            if (sum == myPoint) {
                myPoint = 0;
                //return 3;
                checkWin(Dice.randomSum());
            } else if (sum == 7) {
                return 2;
            }
        }
        return 3;
    }
    public void general (final Dice dice1, final Dice dice2) {
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
    }

}
