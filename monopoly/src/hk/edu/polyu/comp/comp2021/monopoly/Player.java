package hk.edu.polyu.comp.comp2021.monopoly;
import java.security.SecureRandom;
import java.util.*;

/**
 * the player class
 */
public class Player{
    /**
     * player begin with $1500
     */
    public static final int beginMoney = 1500;
    /**
     * the total number of squares
     */
    public static final int mapLength = 20;
    private int No;
    private String name;
    private int money;
    private int location;
    private STATUS status;
    private int inJail;
    private int[] thisDiceNum;
    /**
     * the numbers of players who are alive
     */
    private static int alivePlayerNum=0;

    /**
     * the constructor of player
     * @param i the No of player
     */
    public Player(int i){
        name = "Player "+i+" ";
        money= beginMoney;
        location=0;
        status=STATUS.AUTO;
        inJail=0;
        this.No = i;
        incrementAlivePlayerNum();
    }

    /**
     * @param n the number of players alive now
     */
    public static void setAlivePlayerNum(int n){
        alivePlayerNum = n;
    }
    private static void incrementAlivePlayerNum(){
        alivePlayerNum++;
    }
    private static void decrementAlivePlayerNum(){alivePlayerNum--;}

    /**
     * to get alive player num
     * @return the alive player num
     */
    public static int getAlivePlayerNum(){
        return alivePlayerNum;    }

    /**
     * to get a random number of between 2 and 8 which is the sum of two dices
     * @return the number of dice
     */
    public int[] RollDice(){
        Random r=new SecureRandom();
        int dice1 = r.nextInt(4)+1;
        int dice2 = r.nextInt(4)+1;
        thisDiceNum =  new int[]{dice1,dice2};
        return thisDiceNum;
    }

    /**
     * get sum of this dice num
     * @return the sum of dices
     */
    public int[] getThisDiceNum(){
        return thisDiceNum;
    }

    /**
     * get name of the player
     * @return the name
     */
    public String getName(){
        return name;
    }

    /**
     * get the money
     * @return the money player has
     */
    public int getMoney(){
        return money;
    }

    /**
     * get the player No
     * @return the player No
     */
    public int getNo(){return No;}

    /**
     * the location of the player
     * @return the location of the player
     */
    public int getLocation(){
        return location;
    }

    /**
     * get the status
     * @return the status of player
     */
    public STATUS getStatus(){
        return status;
    }

    /**
     * to check whether the player is in jail
     * @return whether the player is in jail
     */
    public boolean isInJail(){
        if(inJail==0)return false;
        return true;
    }

    /**
     * to change the money of player
     * @param m money to be change
     */
    public void changeMoney(int m){
        money+=m;
    }

    /**
     * to set the money of player
     * @param m the money to be set
     */
    public void setMoney(int m){
        money=m;
    }

    /**
     * to set the location of player
     * @param l the location of player to go to
     */
    public void setLocation(int l){
        location=l;
    }

    /**
     * to change the status of player
     * @param s the status to be set
     */
    public void setStatus(STATUS s){
        status=s;
    }

    /**
     * change in jail
     * @param j the inJail to be set
     */
    public void setInjail(int j){
        inJail=j;
    }

    /**
     * to get InJail
     * @return the value of InJail
     */
    public int getInjail(){ return inJail;}

    /**
     * to make the player retired
     */
    public void getRetired(){
        status=STATUS.RETIRED;
        decrementAlivePlayerNum();
    }

    /**
     * to move the player on the map
     * @param step the step to move
     * @return whether the player has finished one rount
     */
    public boolean move(int step){
        location=location+step;
        if(location>= mapLength){
            location=location% mapLength;
            return true;
        }
        else return false;
    }

}
